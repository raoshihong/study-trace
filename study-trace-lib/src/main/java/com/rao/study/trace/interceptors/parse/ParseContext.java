package com.rao.study.trace.interceptors.parse;

import com.rao.study.trace.interceptors.mybatis.ChangeData;
import com.rao.study.trace.interceptors.mybatis.MSUtils;
import com.rao.study.trace.interceptors.mybatis.MybatisInvocation;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ParseContext implements DataPaser {
	
	/**
	 * 查询操作前的数据
	 * @param mybatisInvocation
	 * @param boundSql
	 * @param sqlParserInfo
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> query(MybatisInvocation mybatisInvocation, BoundSql boundSql, SqlParserInfo sqlParserInfo) throws SQLException {
		Table table = sqlParserInfo.getTable();
		MappedStatement mappedStatement = mybatisInvocation.getMappedStatement();
		//构建一个查询的statement
		MappedStatement selectMappedStatement = MSUtils.newHashMapMappedStatement(mappedStatement);

		//通过jsqlparse构建查询语句
		List<Column> updateColumns = new ArrayList<>();
		Column column = new Column();
		column.setColumnName("*");
		updateColumns.add(column);
		Expression whereExpression = sqlParserInfo.getWhereExpression();

		//构建 select * from user where id = ?
		Select select = JsqlParserHelper.getSelect(table, updateColumns, whereExpression);
		String selectSqlString = select.toString();

		//根据update的条件参数,构建查询参数 ,比如操作是update set note = ? where name = ? and mobile = ?  那么where条件参数列表中的字段为name , mobile
		List<ParameterMapping> selectParamMap = new ArrayList<>();
		//获取where条件中列字段名列表(可能需要考虑子查询)
		List<String> whereColumnsList = JsqlParserHelper.getWhereColumn(whereExpression);
		//parameterMappings中存放的是用户传递所有参数及值,如上面的upate set note = ? where name = ? and mobile = ?,则parameterMappings存放的是 note,name,mobile三个参数及他们的值
		for (ParameterMapping paramMap : boundSql.getParameterMappings()) {
			if (paramMap.getProperty() != null) {
				String property = paramMap.getProperty().contains("et")?paramMap.getProperty().split("\\.")[1]:paramMap.getProperty();
				if (whereColumnsList.contains(property)) {//根据
					selectParamMap.add(paramMap);
					whereColumnsList.remove(property);
				}
			}
		}

		//构建查询语句
		BoundSql queryBoundSql = new BoundSql(mybatisInvocation.getMappedStatement().getConfiguration(), selectSqlString, selectParamMap, mybatisInvocation.getParameter());
		List<HashMap<String, Object>> queryResultList = mybatisInvocation.getExecutor().query(selectMappedStatement, mybatisInvocation.getParameter(), RowBounds.DEFAULT, null, null, queryBoundSql);

		return queryResultList;
	}
	
	@Override
	public List<ChangeData> parseAfter(MybatisInvocation mybatisInvocation, List<ChangeData> changeDatas) throws Exception {
		Object parameter = mybatisInvocation.getParameter();
		for (ChangeData changeData : changeDatas) {
			Long entityId = changeData.getEntityId();
			if (entityId == null) {
				//TODO 这里需要做到对某个表的某个主键进行映射
				entityId = (Long) changeData.getCurrentEntityMap().get("id");
				changeData.setEntityId(entityId);
			}
//			if (entityId == null && parameter instanceof BaseEntity) {
//				entityId = ((BaseEntity) parameter).getId();
//				changeData.setEntityId(entityId);
//			}
		}
		return changeDatas;
	}
	
}
