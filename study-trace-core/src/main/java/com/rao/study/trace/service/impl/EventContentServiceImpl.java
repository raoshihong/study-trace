package com.rao.study.trace.service.impl;

import com.rao.study.trace.entity.EventContent;
import com.rao.study.trace.mapper.EventContentMapper;
import com.rao.study.trace.service.IEventContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 事件操作内容记录表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-09-05
 */
@Service
public class EventContentServiceImpl extends ServiceImpl<EventContentMapper, EventContent> implements IEventContentService {

}
