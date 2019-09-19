package com.rao.study.trace.lib.utils;

import java.util.List;

/**
 * 对整个表的描述
 */
public class TableConfig {
    /**
     * 表名名称
     */
    private String tableName;
    /**
     * 表的描述
     */
    private String tableNameDesc;
    /**
     * 表的主键名称(目前只支持单个主键)
     */
    private String primaryKeyName;
    /**
     * 表的外键(目前只支持单个外键)
     */
    private String foreignKeyName;
    /**
     * 表的外键对应的主表名称
     */
    private String parentTableName;
    /**
     * 表的属性的映射
     */
    private List<TablePropertyConfig> properties;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableNameDesc() {
        return tableNameDesc;
    }

    public void setTableNameDesc(String tableNameDesc) {
        this.tableNameDesc = tableNameDesc;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public String getForeignKeyName() {
        return foreignKeyName;
    }

    public void setForeignKeyName(String foreignKeyName) {
        this.foreignKeyName = foreignKeyName;
    }

    public String getParentTableName() {
        return parentTableName;
    }

    public void setParentTableName(String parentTableName) {
        this.parentTableName = parentTableName;
    }

    public List<TablePropertyConfig> getProperties() {
        return properties;
    }

    public void setProperties(List<TablePropertyConfig> properties) {
        this.properties = properties;
    }
}
