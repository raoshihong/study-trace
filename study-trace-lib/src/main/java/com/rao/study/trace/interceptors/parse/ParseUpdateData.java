package com.rao.study.trace.interceptors.parse;

import com.rao.study.trace.interceptors.enums.DBActionTypeEnum;
import com.rao.study.trace.interceptors.mybatis.ChangeData;
import com.rao.study.trace.interceptors.mybatis.ChangeDataUtil;
import com.rao.study.trace.interceptors.mybatis.MybatisInvocation;
import com.rao.study.trace.interceptors.mybatis.MybatisParameterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ParseUpdateData extends ParseContext {
	
	@Override
	public List<ChangeData> parseBefore(MybatisInvocation mybatisInvocation) throws Throwable {
		List<ChangeData> results = null;

		MappedStatement mappedStatement = mybatisInvocation.getMappedStatement();
		Object updateParameterObject = mybatisInvocation.getParameter();
		BoundSql boundSql = mappedStatement.getBoundSql(mybatisInvocation.getParameter());
		String sql = boundSql.getSql();
		
		SqlParserInfo sqlParserInfo = new SqlParserInfo(sql, DBActionTypeEnum.UPDATE);
		
		//查询更新前的数据
		List<HashMap<String, Object>> queryResults = query(mybatisInvocation,boundSql, sqlParserInfo);
		
		//获取更新字段列表及数据映射
		Map<String, Object> updateDataMap = MybatisParameterUtils.getParameter(mappedStatement, boundSql, updateParameterObject);
		//组装变更列表
		results =  buildEvents(updateDataMap, queryResults,sqlParserInfo);
		return results;
	}

    /**
     *
	 * @param updateDataMap //要更新的新数据字段
	 * @param queryResults 更新前查询的老数据
	 * @param sqlParserInfo
     * @return
     */
	private List<ChangeData> buildEvents(final Map<String, Object> updateDataMap,final List<HashMap<String, Object>> queryResults,SqlParserInfo sqlParserInfo){
		List<ChangeData> changeDatas = new ArrayList<>();
		if(!CollectionUtils.isEmpty(queryResults)){
			for(HashMap<String, Object> queryDataMap : queryResults){
				ChangeData changeData = ChangeDataUtil.buildChangeDataForUpdate(updateDataMap,queryDataMap);
				changeData.setTableName(sqlParserInfo.getTableName());
				changeDatas.add(changeData);
			}
		}
		return changeDatas;
	}
	
}
