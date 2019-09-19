package com.rao.study.trace.lib.interceptors.mybatis;

import com.google.common.collect.Lists;
import com.rao.study.trace.lib.interceptors.parse.SqlParserInfo;
import com.rao.study.trace.lib.utils.TableConfig;
import com.rao.study.trace.lib.utils.TablePropertyConfig;
import com.rao.study.trace.lib.utils.TablesConfigUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ChangeDataUtil {

	public static ChangeEntity buildChangeDataForBefore(final Map<String, Object> beforeDataMap, SqlParserInfo sqlParserInfo) {
		ChangeEntity changeData = new ChangeEntity();
		List<ChangeColumn> afterColumnList = new ArrayList<>();
		changeData.setAfterColumnList(afterColumnList);
		changeData.setBeforeColumnList(buildChangeObject(beforeDataMap,sqlParserInfo));
		changeData.setCurrentEntityMap(beforeDataMap);
		changeData.setTableName(sqlParserInfo.getTableName());
		TableConfig tableConfig = TablesConfigUtils.getTableConfigByTableName(sqlParserInfo.getTableName());
		if (Objects.nonNull(tableConfig) && Objects.nonNull(tableConfig.getPrimaryKeyName())) {
			changeData.setEntityId((Long) beforeDataMap.get(tableConfig.getPrimaryKeyName()));
		}
		return changeData;
	}

	public static ChangeEntity buildChangeDataForAfter(final Map<String, Object> afterDataMap, SqlParserInfo sqlParserInfo) {
		ChangeEntity changeData = new ChangeEntity();
		List<ChangeColumn> beforeColumnList = new ArrayList<>();
		changeData.setAfterColumnList(buildChangeObject(afterDataMap,sqlParserInfo));
		changeData.setBeforeColumnList(beforeColumnList);
		changeData.setCurrentEntityMap(afterDataMap);
		changeData.setTableName(sqlParserInfo.getTableName());
		TableConfig tableConfig = TablesConfigUtils.getTableConfigByTableName(sqlParserInfo.getTableName());
		if (Objects.nonNull(tableConfig) && Objects.nonNull(tableConfig.getPrimaryKeyName())) {
			changeData.setEntityId((Long) afterDataMap.get(tableConfig.getPrimaryKeyName()));
		}

		return changeData;
	}


	private static List<ChangeColumn> buildChangeObject(final Map<String, Object> dataMap, SqlParserInfo sqlParserInfo){
		List<ChangeColumn> changeObjects = Lists.newArrayList();
		dataMap.forEach((key, value) -> {
			ChangeColumn changeColumn = new ChangeColumn();
			changeColumn.setName(key);
			TablePropertyConfig tablePropertyConfig = TablesConfigUtils.getTablePropertyConfigByTableNameAndProperty(sqlParserInfo.getTableName(),key);
			changeColumn.setCnName(Objects.isNull(tablePropertyConfig)?"":tablePropertyConfig.getProCnName());
			changeColumn.setValue(value);
			changeObjects.add(changeColumn);
		});
		return changeObjects;
	}
}
