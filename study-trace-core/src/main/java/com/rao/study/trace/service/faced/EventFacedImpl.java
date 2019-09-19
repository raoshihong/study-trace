package com.rao.study.trace.service.faced;

import com.alibaba.dubbo.config.annotation.Service;
import com.rao.study.trace.dto.EventDto;
import com.rao.study.trace.entity.Event;
import com.rao.study.trace.service.IEventFaced;
import com.rao.study.trace.service.IEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Service
@Component
public class EventFacedImpl implements IEventFaced {

    @Autowired
    private IEventService iEventService;

    @Override
    public void save(EventDto eventDto) {
        Event event = new Event();
        BeanUtils.copyProperties(eventDto,event);
        event.setTime(LocalDateTime.now());
        //保存事件
        iEventService.save(event);
    }
}
