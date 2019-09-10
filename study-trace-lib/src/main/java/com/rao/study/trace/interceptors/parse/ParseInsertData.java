package com.rao.study.trace.interceptors.parse;

import com.rao.study.trace.interceptors.mybatis.ChangeData;
import com.rao.study.trace.interceptors.mybatis.MybatisInvocation;

import java.util.List;

public class ParseInsertData extends ParseContext {

	@Override
	public List<ChangeData> parseBefore(MybatisInvocation mybatisInvocation) throws Throwable {
//		MappedStatement mappedStatement = mybatisInvocation.getMappedStatement();
//		Object updateParameterObject = mybatisInvocation.getParameter();
//		BoundSql boundSql = mappedStatement.getBoundSql(mybatisInvocation.getParameter());
//		String sql = boundSql.getSql();
//		SqlParserInfo sqlParserInfo = new SqlParserInfo(sql, DBActionTypeEnum.INSERT);
//
//		// 获取更新字段列表
//		Map<String, Object> updateDataMap = MybatisParameterUtils.getParameter(mappedStatement, boundSql,
//				updateParameterObject);
//
//		List<ChangeData> changeDatas = new ArrayList<>();
//		ChangeData changeData = ChangeDataUtil.buildChangeDataForInsert(updateDataMap);
//		changeData.setTableName(sqlParserInfo.getTableName());
//		changeData.setCurrentEntityMap(updateDataMap);
//		changeDatas.add(changeData);
//
//		return changeDatas;
        return null;
	}

}
