package com.rao.study.trace.interceptors.parse;

import com.rao.study.trace.interceptors.mybatis.ChangeData;
import com.rao.study.trace.interceptors.mybatis.MybatisInvocation;

import java.util.List;

public class ParseDeleteData extends ParseContext {

	@Override
	public List<ChangeData> parseBefore(MybatisInvocation mybatisInvocation) throws Throwable {
//		MappedStatement mappedStatement = mybatisInvocation.getMappedStatement();
//		BoundSql boundSql = mappedStatement.getBoundSql(mybatisInvocation.getParameter());
//		String sql = boundSql.getSql();
//
//		SqlParserInfo sqlParserInfo = new SqlParserInfo(sql, DBActionTypeEnum.DELETE);
//
//		// 获取要更新数据
//		ArrayList<HashMap<String, Object>> beforeRsults = query(mybatisInvocation, boundSql, sqlParserInfo);
//
//		List<ChangeData> results = buildChangeDatas(beforeRsults, sqlParserInfo);
//
//		return results;
        return null;
	}

//	private List<ChangeData> buildChangeDatas(final ArrayList<HashMap<String, Object>> beforeResults,
//			SqlParserInfo sqlParserInfo) {
//		List<ChangeData> changeDatas = new ArrayList<>();
//		if (beforeResults != null && !beforeResults.isEmpty()) {
//			for (HashMap<String, Object> queryDataMap : beforeResults) {
//				ChangeData changeData = ChangeDataUtil.buildChangeDataForDelete(queryDataMap);
//				changeData.setTableName(sqlParserInfo.getTableName());
//				changeData.setCurrentEntityMap(queryDataMap);
//				changeDatas.add(changeData);
//			}
//		}
//		return changeDatas;
//	}

}
