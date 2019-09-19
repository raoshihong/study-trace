package com.rao.study.trace.lib.annotation;

import com.rao.study.trace.lib.AbstractSpan;
import com.rao.study.trace.lib.EventData;
import com.rao.study.trace.lib.utils.ContextManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;

@Component
@Aspect
public class TraceAspectJ {

    @Autowired
    private EventData eventData;

    //使用AOP拦截线程方法,线程间传递链路

    //使用环绕通知,直接拦截所有@PostMapping,@GetMapping,@RequestMapping,带有自定义EnableTrace的
    @Around("@annotation(EnableTrace)")//TODO 这块可以做成配置化,配置拦截哪些方法,哪些url
    public Object trace(ProceedingJoinPoint pjp) throws Throwable{

        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        Method method = methodSignature.getMethod();
        EnableTrace enableTrace = method.getAnnotation(EnableTrace.class);

        Object[] args = pjp.getArgs();

        // 事件开始
        AbstractSpan abstractSpan = ContextManager.createSpan();
        abstractSpan.setPlaceCode(enableTrace.place());
        if(Objects.nonNull(abstractSpan)) {
            eventData.buildEvent(enableTrace).buildEventUser(enableTrace).buildEventPlace(enableTrace);
        }

        Object result = pjp.proceed(args);

        //事件处理完毕,回传事件id,异步整理收集到的日志信息
        ContextManager.stopSpan();
        return  result;
    }

}
