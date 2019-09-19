package com.rao.study.trace.service;


import com.google.common.eventbus.Subscribe;
import com.rao.study.trace.dto.BaseData;
import org.springframework.util.ReflectionUtils;

public abstract class EventAdapterHandler<E extends BaseData> implements EventHandler<E> {

    private static final String METHOD_NAME = "process";

    public abstract boolean process(E e);

    @Subscribe
    @Override
    public void onEvent(E event){
        if (ReflectionUtils.findMethod(this.getClass(), METHOD_NAME, event.getClass()) != null) {
            try {
                if (!process(event)) {
                }
            } catch (Exception e) {
            }
        }
    }

}
