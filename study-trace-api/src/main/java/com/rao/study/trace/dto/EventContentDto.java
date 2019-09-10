package com.rao.study.trace.dto;

import java.io.Serializable;

public class EventContentDto implements Serializable {
    private Long id;

    /**
     * 事件id
     */
    private Long eventId;

    /**
     * 操作数据唯一标识id
     */
    private Long dataId;

    /**
     * 操作数据标识名称
     */
    private String dataName;

    /**
     * 修改字段英文名
     */
    private String keyEn;

    /**
     * 修改字段中文名
     */
    private String keyZh;

    /**
     * 修改前的值
     */
    private String olValue;

    /**
     * 修改后的值
     */
    private String neValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getKeyEn() {
        return keyEn;
    }

    public void setKeyEn(String keyEn) {
        this.keyEn = keyEn;
    }

    public String getKeyZh() {
        return keyZh;
    }

    public void setKeyZh(String keyZh) {
        this.keyZh = keyZh;
    }

    public String getOlValue() {
        return olValue;
    }

    public void setOlValue(String olValue) {
        this.olValue = olValue;
    }

    public String getNeValue() {
        return neValue;
    }

    public void setNeValue(String neValue) {
        this.neValue = neValue;
    }
}
