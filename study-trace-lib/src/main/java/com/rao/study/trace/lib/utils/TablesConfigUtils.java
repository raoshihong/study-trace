package com.rao.study.trace.lib.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TablesConfigUtils {

    /**
     * 读取配置,TODO 可以交给数据库配置,采用缓存存储
     * @return
     */
    public static List<TableConfig> parseTableConfig(){

        String content = "";
        try(InputStream is = TablesConfigUtils.class.getClassLoader().getResourceAsStream("config/tables.json")){
            content = IOUtils.toString(is, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseArray(content,TableConfig.class);
    }

    /**
     * 根据表名获取当前表配置
     * @param tableName
     * @return
     */
    public static TableConfig getTableConfigByTableName(String tableName){
        List<TableConfig> tableConfigs = parseTableConfig();
        Optional<TableConfig> tableConfigOptional = tableConfigs.stream().filter(tableConfig -> tableConfig.getTableName().equals(tableName)).findAny();
        if (tableConfigOptional.isPresent()) {
            return tableConfigOptional.get();
        }
        return null;
    }

    /**
     * 根据当前表名获取它的父表,TODO 目前只支持一级父表
     * @param childTableName
     * @return
     */
    public static TableConfig getParentTableConfigByChildTableName(String childTableName){
        TableConfig tableConfig = getTableConfigByTableName(childTableName);
        if (Objects.nonNull(tableConfig)) {
            return getTableConfigByTableName(tableConfig.getParentTableName());
        }
        return null;
    }

    /**
     * 根据表名-属性名 获取对应的属性配置
     * @param tableName
     * @param property
     * @return
     */
    public static TablePropertyConfig getTablePropertyConfigByTableNameAndProperty(String tableName,String property){
        TableConfig tableConfig = getTableConfigByTableName(tableName);
        if (Objects.nonNull(tableConfig)) {
            List<TablePropertyConfig> properties = tableConfig.getProperties();
            Optional<TablePropertyConfig> optionalTablePropertyConfig = properties.stream().filter(tablePropertyConfig -> tablePropertyConfig.getProEnName().equals(property)).findAny();

            TablePropertyConfig tablePropertyConfig = null;
            if (optionalTablePropertyConfig.isPresent()) {
                return optionalTablePropertyConfig.get();
            }
            return null;
        }
        return null;
    }

}
