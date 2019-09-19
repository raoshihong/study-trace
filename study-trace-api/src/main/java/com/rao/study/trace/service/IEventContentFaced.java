package com.rao.study.trace.service;

import com.rao.study.trace.dto.EventContentDto;

public interface IEventContentFaced {
    void save(EventContentDto eventContentDto);

    /**
     * 加工对应链路的数据
     * @param eventContentDto
     */
    void processContent(EventContentDto eventContentDto);
}
