package com.rao.study.trace.annotation;

import com.rao.study.trace.AbstractSpan;
import com.rao.study.trace.EventData;
import com.rao.study.trace.utils.SpanContextUtils;
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

    //使用环绕通知
    @Around("@annotation(EnableTrace)")
    public Object trace(ProceedingJoinPoint pjp) throws Throwable{

        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        Method method = methodSignature.getMethod();
        EnableTrace enableTrace = method.getAnnotation(EnableTrace.class);

        Object[] args = pjp.getArgs();

        //先创建事件id,保存到threadLocal中

        AbstractSpan abstractSpan = SpanContextUtils.get();

        if (Objects.isNull(abstractSpan)) {
            abstractSpan = new AbstractSpan();
            abstractSpan.setSpanId(UUID.randomUUID().toString());
            SpanContextUtils.set(abstractSpan);
            eventData.buildEvent(enableTrace).buildEventUser(enableTrace).buildEventPlace(enableTrace);
        }else{
            //更新事件

        }

        return  pjp.proceed(args);
    }

}
