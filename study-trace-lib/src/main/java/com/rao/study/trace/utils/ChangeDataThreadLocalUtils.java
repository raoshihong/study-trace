package com.rao.study.trace.utils;

public class ChangeDataThreadLocalUtils {
    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<Object>();

    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }

    public static Object get(){
        return THREAD_LOCAL.get();
    }
}
