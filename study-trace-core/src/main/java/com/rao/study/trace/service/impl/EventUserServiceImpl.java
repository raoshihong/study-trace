package com.rao.study.trace.service.impl;

import com.rao.study.trace.entity.EventUser;
import com.rao.study.trace.mapper.EventUserMapper;
import com.rao.study.trace.service.IEventUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 事件用户体系表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-09-05
 */
@Service
public class EventUserServiceImpl extends ServiceImpl<EventUserMapper, EventUser> implements IEventUserService {

}
