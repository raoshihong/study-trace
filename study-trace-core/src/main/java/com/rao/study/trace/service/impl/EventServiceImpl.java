package com.rao.study.trace.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rao.study.trace.entity.Event;
import com.rao.study.trace.mapper.EventMapper;
import com.rao.study.trace.service.IEventService;
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

}
