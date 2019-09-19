package com.rao.study.trace.dto;

import java.io.Serializable;

public class EventContentDto implements Serializable {
    private Long id;

    /**
     * 链路标识
     */
    private String spanId;

    /**
     * 操作数据唯一标识id
     */
    private Long dataId;

    /**
     * 操作数据标识名称
     */
    private String dataName;

    /**
     * 数据操作类型update,insert,delete
     */
    private String dataActionType;

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


    private String tableName;

    private String tableNameDesc;

    private Long recordId;

    private String dataTableName;

    private String beforeBody;
    private String afterBody;

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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getTableNameDesc() {
        return tableNameDesc;
    }

    public void setTableNameDesc(String tableNameDesc) {
        this.tableNameDesc = tableNameDesc;
    }

    public String getDataActionType() {
        return dataActionType;
    }

    public void setDataActionType(String dataActionType) {
        this.dataActionType = dataActionType;
    }

    public String getDataTableName() {
        return dataTableName;
    }

    public void setDataTableName(String dataTableName) {
        this.dataTableName = dataTableName;
    }

    public String getBeforeBody() {
        return beforeBody;
    }

    public void setBeforeBody(String beforeBody) {
        this.beforeBody = beforeBody;
    }

    public String getAfterBody() {
        return afterBody;
    }

    public void setAfterBody(String afterBody) {
        this.afterBody = afterBody;
    }
}
