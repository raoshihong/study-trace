package com.rao.study.trace.lib.interceptors.parse;

import com.google.common.collect.Lists;
import com.rao.study.trace.lib.interceptors.mybatis.MSUtils;
import com.rao.study.trace.lib.interceptors.mybatis.MybatisInvocation;
import com.rao.study.trace.lib.utils.TableConfig;
import com.rao.study.trace.lib.utils.TablesConfigUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
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

	public List<HashMap<String, Object>> queryAtInsert(MybatisInvocation mybatisInvocation, BoundSql boundSql, SqlParserInfo sqlParserInfo) throws SQLException, JSQLParserException {
		Table table = sqlParserInfo.getTable();
		MappedStatement mappedStatement = mybatisInvocation.getMappedStatement();
		//构建一个查询的statement
		MappedStatement selectMappedStatement = MSUtils.newHashMapMappedStatement(mappedStatement);
		Statement stmt = CCJSqlParserUtil.parse("select LAST_INSERT_ID() as id");
		Select select = (Select) stmt;
		BoundSql queryBoundSql = new BoundSql(mybatisInvocation.getMappedStatement().getConfiguration(), select.toString(), null, null);
		List<HashMap<String, BigInteger>> queryLastIdResultList = mybatisInvocation.getExecutor().query(selectMappedStatement, mybatisInvocation.getParameter(), RowBounds.DEFAULT, null, null, queryBoundSql);

		List<HashMap<String, Object>> queryResultList = Lists.newArrayList();
		TableConfig tableConfig = TablesConfigUtils.getTableConfigByTableName(sqlParserInfo.getTableName());
		queryLastIdResultList.stream().forEach(stringObjectHashMap -> {
			stringObjectHashMap.forEach((key, value) -> {
				List<Column> updateColumns = new ArrayList<>();
				Column column = new Column();
				column.setColumnName("*");
				updateColumns.add(column);
				EqualsTo equalsTo = new EqualsTo();
				equalsTo.setLeftExpression(new Column(tableConfig.getPrimaryKeyName()));
				equalsTo.setRightExpression(new LongValue(value.longValue()));
				Select select1 = JsqlParserHelper.getSelect(table, updateColumns, equalsTo);

				try {
					BoundSql queryBoundSql1 = new BoundSql(mybatisInvocation.getMappedStatement().getConfiguration(), select1.toString(), null, null);
					List<HashMap<String, Object>> queryResultList2 = mybatisInvocation.getExecutor().query(selectMappedStatement, mybatisInvocation.getParameter(), RowBounds.DEFAULT, null, null, queryBoundSql1);
					queryResultList.addAll(queryResultList2);
				}catch (Exception e){

				}

			});
		});

		return queryResultList;
	}
	
}
