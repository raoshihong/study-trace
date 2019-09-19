package com.rao.study.trace.config;

import com.rao.study.trace.service.EventHandler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DataBusConfiguration implements InitializingBean, DisposableBean {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, EventHandler> beans = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        beans = applicationContext.getBeansOfType(EventHandler.class);
        //获取
        beans.forEach((beanName, iEventHandler) ->  DataBusFacade.register(iEventHandler));
    }

    @Override
    public void destroy() throws Exception {
        beans.forEach((beanName, iEventHandler) -> DataBusFacade.unregister(iEventHandler));
    }

}
