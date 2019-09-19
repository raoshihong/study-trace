package com.rao.study.trace.lib.utils;

import com.rao.study.trace.lib.AbstractSpan;

import java.util.UUID;

public class ContextManager {
    private static final InheritableThreadLocal<AbstractSpan> THREAD_LOCAL = new InheritableThreadLocal<AbstractSpan>();

    public static void set(AbstractSpan value){
        THREAD_LOCAL.set(value);
    }

    public static AbstractSpan get(){
        return THREAD_LOCAL.get();
    }

    public static AbstractSpan createSpan(){
        AbstractSpan abstractSpan = new AbstractSpan();
        abstractSpan.setSpanId(UUID.randomUUID().toString());
        ContextManager.set(abstractSpan);
        return abstractSpan;
    }

    public static void stopSpan(){
        AbstractSpan abstractSpan = THREAD_LOCAL.get();

        //发送通知去处理相关的数据


        THREAD_LOCAL.remove();
    }

}
