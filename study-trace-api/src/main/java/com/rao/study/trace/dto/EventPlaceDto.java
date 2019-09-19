package com.rao.study.trace.dto;

import java.io.Serializable;

public class EventPlaceDto implements Serializable {
    private Long id;

    /**
     * 链路标识
     */
    private String spanId;

    /**
     * 操作页面代码
     */
    private String placePageCode;

    /**
     * 操作页面名称
     */
    private String placePageName;

    /**
     * 操作平台代码
     */
    private String placePlatformCode;

    /**
     * 操作平台名称
     */
    private String placePlatformName;

    /**
     * 操作资源URL
     */
    private String placeUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getPlacePageCode() {
        return placePageCode;
    }

    public void setPlacePageCode(String placePageCode) {
        this.placePageCode = placePageCode;
    }

    public String getPlacePageName() {
        return placePageName;
    }

    public void setPlacePageName(String placePageName) {
        this.placePageName = placePageName;
    }

    public String getPlacePlatformCode() {
        return placePlatformCode;
    }

    public void setPlacePlatformCode(String placePlatformCode) {
        this.placePlatformCode = placePlatformCode;
    }

    public String getPlacePlatformName() {
        return placePlatformName;
    }

    public void setPlacePlatformName(String placePlatformName) {
        this.placePlatformName = placePlatformName;
    }

    public String getPlaceUrl() {
        return placeUrl;
    }

    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }
}
