package com.rao.study.trace.service.handler;

import com.alibaba.fastjson.JSON;
import com.rao.study.trace.dto.ContentData;
import com.rao.study.trace.service.EventAdapterHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ContentEventHandlerImpl extends EventAdapterHandler<ContentData> {


    @Transactional
    @Override
    public boolean process(ContentData eventDto) {
        log.info("EventServiceHandler.process:{}", JSON.toJSONString(eventDto));

        return true;
    }
}
