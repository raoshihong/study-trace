package com.rao.study.trace.lib.utils;

import java.util.List;

public class TableExportConfig {
    private String tableName;
    private List<String> properties;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }
}
