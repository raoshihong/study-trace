package com.rao.study.trace.lib.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class TableExportUtils {
    public static List<TableExportConfig> parseTableExportConfig(){

        String content = "";
        try(InputStream is = TablesConfigUtils.class.getClassLoader().getResourceAsStream("config/exports.json")){
            content = IOUtils.toString(is, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseArray(content,TableExportConfig.class);
    }

    public static TableExportConfig getTableExportConfigByTableName(String tableName){
        List<TableExportConfig> tableExportConfigs = parseTableExportConfig();
        Optional<TableExportConfig> optionalTableExportConfig = tableExportConfigs.stream().filter(tableExportConfig -> tableExportConfig.getTableName().equals(tableName)).findAny();
        if (optionalTableExportConfig.isPresent()) {
            return optionalTableExportConfig.get();
        }
        return null;
    }
}
