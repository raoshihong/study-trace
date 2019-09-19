package com.rao.study.trace.service;


import com.rao.study.trace.dto.BaseData;

public interface EventHandler<E extends BaseData> {
    void onEvent(E event);
}
