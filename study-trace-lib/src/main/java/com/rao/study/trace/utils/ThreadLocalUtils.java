package com.rao.study.trace.utils;

public class ThreadLocalUtils {
    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<Long>();

    public static void set(Long value){
        THREAD_LOCAL.set(value);
    }

    public static Long get(){
        return THREAD_LOCAL.get();
    }
}
