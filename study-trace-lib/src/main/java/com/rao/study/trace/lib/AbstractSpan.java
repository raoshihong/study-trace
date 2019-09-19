package com.rao.study.trace.lib;

public class AbstractSpan {
    /**
     * 链路id
     */
    private String spanId;
    /**
     * 链路入口页面
     */
    private String placeCode;

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }
}
