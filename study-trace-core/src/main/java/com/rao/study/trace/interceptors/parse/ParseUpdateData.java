package com.rao.study.trace.interceptors.parse;

import com.rao.study.trace.interceptors.enums.DBActionTypeEnum;
import com.rao.study.trace.interceptors.mybatis.MybatisInvocation;
import com.rao.study.trace.interceptors.mybatis.MybatisParameterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ParseUpdateData extends ParseContext {
	
	@Override
	public List<Object> parseBefore(MybatisInvocation mybatisInvocation) throws Throwable {
		List<Object> results = null;

		MappedStatement mappedStatement = mybatisInvocation.getMappedStatement();
		Object updateParameterObject = mybatisInvocation.getParameter();
		BoundSql boundSql = mappedStatement.getBoundSql(mybatisInvocation.getParameter());
		String sql = boundSql.getSql();
		
		SqlParserInfo sqlParserInfo = new SqlParserInfo(sql, DBActionTypeEnum.UPDATE);
		
		//查询更新前的数据
		ArrayList<HashMap<String, Object>> queryResults = query(mybatisInvocation,boundSql, sqlParserInfo);
		
		//获取更新字段列表
		Map<String, Object> updateDataMap = MybatisParameterUtils.getParameter(mappedStatement, boundSql, updateParameterObject);
		//组装变更列表
		results =  buildEvents(updateDataMap, queryResults,sqlParserInfo);
		return results;
	}
	
	private List<Object> buildEvents(final Map<String, Object> updateDataMap,
			final ArrayList<HashMap<String, Object>> queryResults,SqlParserInfo sqlParserInfo){
		List<Object> events = new ArrayList<>();
		if(queryResults != null && !queryResults.isEmpty()){
			for(HashMap<String, Object> queryDataMap : queryResults){
//				Event Event = ChangeDataUtil.buildEventForUpdate(updateDataMap,queryDataMap);
//				Event.setTableName(sqlParserInfo.getTableName());
//				Events.add(Event);
			}
		}
		return events;
	}
	
}
