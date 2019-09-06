package com.rao.study.trace.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rao.study.trace.dubbo.dto.EventDto;
import com.rao.study.trace.dubbo.service.IEventFaced;
import com.rao.study.trace.entity.Event;
import com.rao.study.trace.mapper.EventMapper;
import com.rao.study.trace.service.IEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rao.study.trace.utils.ThreadLocalUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 事件记录总表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-09-05
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements IEventService {

    @Reference(timeout = 5000)
    private IEventFaced iEventFaced;

    @Override
    public void saveEvent(Event event) {
        event.setId(1L);
        Long eventId = ThreadLocalUtils.get();
//        EventDto eventDto = new EventDto();
//        eventDto.setName(event.getName());
//        eventDto.setType(event.getType());
//        iEventFaced.save(eventDto);
//        this.save(event);
        this.updateById(event);
    }

}
