package com.rao.study.trace.faced;

/**
 * 不同系统实现自己的返回用户信息的功能
 */
public class DefaultTraceFaced implements TraceFaced {
    @Override
    public TraceEventUser getTraceEventUser() {
        return null;
    }
}
