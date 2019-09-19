package com.rao.study.trace.lib;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rao.study.trace.lib.annotation.EnableTrace;
import com.rao.study.trace.dto.EventContentDto;
import com.rao.study.trace.dto.EventDto;
import com.rao.study.trace.dto.EventPlaceDto;
import com.rao.study.trace.dto.EventUserDto;
import com.rao.study.trace.lib.faced.DefaultTraceFaced;
import com.rao.study.trace.lib.faced.TraceEventUser;
import com.rao.study.trace.lib.faced.TraceFaced;
import com.rao.study.trace.service.IEventContentFaced;
import com.rao.study.trace.service.IEventFaced;
import com.rao.study.trace.service.IEventPlaceFaced;
import com.rao.study.trace.service.IEventUserFaced;
import com.rao.study.trace.lib.utils.ContextManager;
import org.springframework.stereotype.Component;

@Component
public class EventData {

    @Reference(timeout = 50000,check = false)
    private IEventFaced iEventFaced;

    @Reference(timeout = 50000,check = false)
    private IEventUserFaced iEventUserFaced;

    @Reference(timeout = 50000,check = false)
    private IEventPlaceFaced iEventPlaceFaced;

    @Reference(timeout = 50000,check = false)
    private IEventContentFaced iEventContentFaced;

    public EventData buildEvent(EnableTrace enableTrace){
        AbstractSpan abstractSpan = ContextManager.get();

        EventDto eventDto = new EventDto();
        //保存基本事件
        eventDto.setName(enableTrace.name());
        eventDto.setType(enableTrace.type());
        eventDto.setSpanId(abstractSpan.getSpanId());
        iEventFaced.save(eventDto);
        return this;
    }

    public EventData buildEventUser(EnableTrace enableTrace){
        AbstractSpan abstractSpan = ContextManager.get();

        //保存事件用户
        TraceFaced traceUserFaced = new DefaultTraceFaced();
        TraceEventUser traceUser = traceUserFaced.getTraceEventUser();

        EventUserDto eventUserDto = new EventUserDto();
        eventUserDto.setSpanId(abstractSpan.getSpanId());
        eventUserDto.setUserId(traceUser.getId());
        eventUserDto.setUserName(traceUser.getName());
        eventUserDto.setUserMobile(traceUser.getMobile());
        eventUserDto.setUserOrigin(traceUser.getUserOrigin());
        iEventUserFaced.save(eventUserDto);
        return this;
    }

    public EventData buildEventPlace(EnableTrace enableTrace){
        AbstractSpan abstractSpan = ContextManager.get();

        EventPlaceDto eventPlaceDto = new EventPlaceDto();
        eventPlaceDto.setSpanId(abstractSpan.getSpanId());
        eventPlaceDto.setPlacePageCode(enableTrace.place());
        iEventPlaceFaced.save(eventPlaceDto);
        return this;
    }

    public EventData buildEventContent(EventContentDto eventContentDto){
        iEventContentFaced.save(eventContentDto);
        return this;
    }
}
