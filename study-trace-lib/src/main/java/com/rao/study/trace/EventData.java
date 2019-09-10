package com.rao.study.trace;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rao.study.trace.annotation.EnableTrace;
import com.rao.study.trace.dto.EventContentDto;
import com.rao.study.trace.dto.EventDto;
import com.rao.study.trace.dto.EventPlaceDto;
import com.rao.study.trace.dto.EventUserDto;
import com.rao.study.trace.faced.DefaultTraceFaced;
import com.rao.study.trace.faced.TraceEventUser;
import com.rao.study.trace.faced.TraceFaced;
import com.rao.study.trace.service.IEventContentFaced;
import com.rao.study.trace.service.IEventFaced;
import com.rao.study.trace.service.IEventPlaceFaced;
import com.rao.study.trace.service.IEventUserFaced;
import com.rao.study.trace.utils.SpanContextUtils;
import org.springframework.stereotype.Component;

@Component
public class EventData {

    @Reference(timeout = 50000)
    private IEventFaced iEventFaced;

    @Reference(timeout = 50000)
    private IEventUserFaced iEventUserFaced;

    @Reference(timeout = 50000)
    private IEventPlaceFaced iEventPlaceFaced;

    @Reference(timeout = 50000)
    private IEventContentFaced iEventContentFaced;

    public EventData buildEvent(EnableTrace enableTrace){
        AbstractSpan abstractSpan = SpanContextUtils.get();

        EventDto eventDto = new EventDto();
        //保存基本事件
        eventDto.setName(enableTrace.name());
        Long id = iEventFaced.save(eventDto);
        abstractSpan.setEventId(id);
        return this;
    }

    public EventData buildEventUser(EnableTrace enableTrace){
        AbstractSpan abstractSpan = SpanContextUtils.get();

        //保存事件用户
        TraceFaced traceUserFaced = new DefaultTraceFaced();
        TraceEventUser traceUser = traceUserFaced.getTraceEventUser();

        EventUserDto eventUserDto = new EventUserDto();
        eventUserDto.setEventId(abstractSpan.getEventId());
        eventUserDto.setUserId(traceUser.getId());
        eventUserDto.setUserName(traceUser.getName());
        eventUserDto.setUserMobile(traceUser.getMobile());
        eventUserDto.setUserOrigin(traceUser.getUserOrigin());
        iEventUserFaced.save(eventUserDto);
        return this;
    }

    public EventData buildEventPlace(EnableTrace enableTrace){
        AbstractSpan abstractSpan = SpanContextUtils.get();

        EventPlaceDto eventPlaceDto = new EventPlaceDto();
        eventPlaceDto.setEventId(abstractSpan.getEventId());
        eventPlaceDto.setPlacePageCode(enableTrace.place());
        iEventPlaceFaced.save(eventPlaceDto);
        return this;
    }

    public EventData buildEventContent(EventContentDto eventContentDto){
        iEventContentFaced.save(eventContentDto);
        return this;
    }
}
