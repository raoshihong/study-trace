package com.rao.study.trace.service.faced;

import com.alibaba.dubbo.config.annotation.Service;
import com.rao.study.trace.dto.EventPlaceDto;
import com.rao.study.trace.entity.EventPlace;
import com.rao.study.trace.service.IEventPlaceFaced;
import com.rao.study.trace.service.IEventPlaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class EventPlaceFacedImpl implements IEventPlaceFaced {
    @Autowired
    private IEventPlaceService iEventPlaceService;
    @Override
    public void save(EventPlaceDto eventPlaceDto) {
        EventPlace eventPlace = new EventPlace();
        BeanUtils.copyProperties(eventPlaceDto,eventPlace);
        iEventPlaceService.save(eventPlace);
    }
}
