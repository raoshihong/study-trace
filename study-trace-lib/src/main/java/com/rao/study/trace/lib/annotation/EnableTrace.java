package com.rao.study.trace.lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableTrace {
    String name();//事件名
    String place();//发生地
    String system();//系统(根据系统来获取对应的用户id)
    String type();//事件类型
}
