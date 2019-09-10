package com.rao.study.trace.interceptors;

import com.google.common.collect.Lists;
import com.rao.study.trace.AbstractSpan;
import com.rao.study.trace.interceptors.listener.ChangeEventListener;
import com.rao.study.trace.interceptors.mybatis.ChangeData;
import com.rao.study.trace.interceptors.mybatis.MybatisInvocation;
import com.rao.study.trace.interceptors.parse.DataPaser;
import com.rao.study.trace.interceptors.parse.ParseFactory;
import com.rao.study.trace.utils.SpanContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Intercepts(value = {
        @Signature(type = Executor.class,
                method = "update",
                args = {MappedStatement.class,Object.class})
})
//@Conditional(MybatisCondition.class)
@Component
public class ChangeMonitorInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("ChangeMonitorInterceptor.intercept");
        //获取拦截目标
        Object target = realTarget(invocation.getTarget());

        Object result = null;
        if (target instanceof Executor) {
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object parameter = args[1];
            String commandName = ms.getSqlCommandType().name();
            AbstractSpan abstractSpan = SpanContextUtils.get();

            MybatisInvocation mybatisInvocation = new MybatisInvocation(args, ms, parameter, (Executor) target);

            List<ChangeData> changeTable = Lists.newArrayList();

            //根据sql命令名称创建相应数据解析器
            DataPaser dataPaser = ParseFactory.getInstance().creator(commandName);

            if (Objects.nonNull(abstractSpan)) {
                //在执行sql操作前,原数据的处理
                changeTable = beforeProceed(dataPaser,mybatisInvocation);
            }

            // 执行Update方法，除了查询之外的Insert，Delete，Update都是属于Update方法
            result = invocation.proceed();

            if (Objects.nonNull(abstractSpan)) {
                //执行完sql操作后,的数据处理
                afterProceed(dataPaser,mybatisInvocation,changeTable);
            }

        }

        return result;
    }

    /**
     * 方法执行之前解析旧数据
     * @param dataPaser
     * @param mybatisInvocation
     */
    public List<ChangeData> beforeProceed(DataPaser dataPaser,MybatisInvocation mybatisInvocation){
        log.info("ChangeMonitorInterceptor.beforeProceed");
        try {
            return dataPaser.parseBefore(mybatisInvocation);
        } catch (Throwable t){
            //捕获异常,不影响目标应用的更新语句
            log.warn("ChangeMonitorInterceptor.intercept before update exception:{}",t);
        }
        return null;
    }

    public void afterProceed(DataPaser dataPaser,MybatisInvocation mybatisInvocation,List<ChangeData> changeTable){
        log.info("ChangeMonitorInterceptor.afterProceed");
        try {
            // 方法执行之后处理数据
            changeTable = dataPaser.parseAfter(mybatisInvocation, changeTable);

            if (changeTable != null) {
                ChangeEventListener changeEventListener = ChangeEventListener.getInstance();
                changeEventListener.listenModification(mybatisInvocation.getMappedStatement().getSqlCommandType().name(), changeTable);
            }
        } catch (Throwable t){
            //捕获异常,不影响目标应用的更新语句
            log.warn("ChangeMonitorInterceptor.intercept before update exception:{}",t);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private static Object realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        } else {
            return target;
        }
    }
}
