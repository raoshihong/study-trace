package com.rao.study.trace.service.faced;

import com.alibaba.dubbo.config.annotation.Service;
import com.rao.study.trace.dto.EventUserDto;
import com.rao.study.trace.entity.EventUser;
import com.rao.study.trace.service.IEventUserFaced;
import com.rao.study.trace.service.IEventUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class EventUserFacedImpl implements IEventUserFaced {
    @Autowired
    private IEventUserService iEventUserService;
    @Override
    public void save(EventUserDto eventUserDto) {
        EventUser eventUser = new EventUser();
        BeanUtils.copyProperties(eventUserDto,eventUser);
        iEventUserService.save(eventUser);
    }
}
