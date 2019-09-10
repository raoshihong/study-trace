package com.rao.study.trace.service;

import com.rao.study.trace.dto.EventDto;

public interface IEventFaced {

    Long save(EventDto eventDto);

}
