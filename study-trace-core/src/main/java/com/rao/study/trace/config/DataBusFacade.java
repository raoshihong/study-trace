package com.rao.study.trace.config;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.EventBus;
import com.rao.study.trace.dto.BaseData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataBusFacade {

    private static final EventBus EVENT_BUS = new EventBus();

    public static void register(Object object){
        if (object != null) {
            EVENT_BUS.register(object);
        }
    }

    public static void execute(BaseData event){
        log.info("EventBusFacade.execute event:{}", JSON.toJSONString(event));
        EVENT_BUS.post(event);
    }

    public static void unregister(Object object){
        EVENT_BUS.unregister(object);
    }

}
