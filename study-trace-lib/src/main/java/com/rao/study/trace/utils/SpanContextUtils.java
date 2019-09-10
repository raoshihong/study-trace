package com.rao.study.trace.utils;

import com.rao.study.trace.AbstractSpan;

public class SpanContextUtils {
    private static final ThreadLocal<AbstractSpan> THREAD_LOCAL = new ThreadLocal<AbstractSpan>();

    public static void set(AbstractSpan value){
        THREAD_LOCAL.set(value);
    }

    public static AbstractSpan get(){
        return THREAD_LOCAL.get();
    }
}
