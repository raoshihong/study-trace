package com.rao.study.trace.lib.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext ctxt;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctxt = applicationContext;
    }

    public static ApplicationContext getInstance(){
        return ctxt;
    }
}
