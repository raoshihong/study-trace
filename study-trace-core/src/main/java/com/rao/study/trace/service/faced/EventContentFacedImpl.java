package com.rao.study.trace.service.faced;

import com.alibaba.dubbo.config.annotation.Service;
import com.rao.study.trace.dto.EventContentDto;
import com.rao.study.trace.entity.EventContent;
import com.rao.study.trace.service.IEventContentFaced;
import com.rao.study.trace.service.IEventContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class EventContentFacedImpl implements IEventContentFaced {

    @Autowired
    private IEventContentService iEventContentService;

    @Override
    public void save(EventContentDto eventContentDto) {
        EventContent eventContent = new EventContent();
        BeanUtils.copyProperties(eventContentDto,eventContent);
        iEventContentService.save(eventContent);
    }
}
