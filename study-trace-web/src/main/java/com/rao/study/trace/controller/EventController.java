package com.rao.study.trace.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rao.study.trace.annotation.EnableTrace;
import com.rao.study.trace.constants.Urls;
import com.rao.study.trace.entity.Event;
import com.rao.study.trace.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 事件记录总表 前端控制器
 * </p>
 *
 * @author 
 * @since 2019-09-05
 */
@RestController
//@RequestMapping("/event")
public class EventController {

    @Autowired
    private IEventService iEventService;

    @GetMapping(value = Urls.V1.Event.SAVE)
    @EnableTrace
    public void save(){
        Event event = new Event();
        event.setName("sdfsdf");
        event.setTime(LocalDateTime.now());
        iEventService.saveEvent(event);
    }

    @GetMapping(value = Urls.V1.Event.LIST)
    public void list(){
        QueryWrapper<Event> queryWrapper = new QueryWrapper<Event>();
        queryWrapper.lambda().eq(Event::getId,1L);
        iEventService.list(queryWrapper);
    }

}
