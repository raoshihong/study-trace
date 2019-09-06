package com.rao.study.trace.annotation;

import com.rao.study.trace.utils.ThreadLocalUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Objects;

@Component
@Aspect
public class TraceAspectJ {

    //使用环绕通知
    @Around("@annotation(EnableTrace)")
    public Object trace(ProceedingJoinPoint pjp) throws Throwable{

//        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
//        Method method = methodSignature.getMethod();

        Object[] args = pjp.getArgs();

        //先创建事件id,保存到threadLocal中
        Long eventId = ThreadLocalUtils.get();

        if (Objects.isNull(eventId)) {
            //保存事件

            SecureRandom random = new SecureRandom();
            eventId = random.nextLong();
            ThreadLocalUtils.set(eventId);
        }else{
            //更新事件
        }

        Object retVal = pjp.proceed(args);

        //再从threadLocal中取出事件id,更新数据

        return retVal;
    }

}
