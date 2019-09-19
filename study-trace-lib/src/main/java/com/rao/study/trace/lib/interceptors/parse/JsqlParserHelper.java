package com.rao.study.trace.lib.interceptors.parse;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.SelectUtils;

import java.util.ArrayList;
import java.util.List;

public class JsqlParserHelper {

	/**
	 * 获取where条件的字段
	 * @param whereExpression
	 * @return
	 */
	public static List<String> getWhereColumn(Expression whereExpression) {
		final List<String> result = new ArrayList<>();
		whereExpression.accept(new ExpressionVisitorAdapter() {
			@Override
			public void visit(Column expr) {

				//在这里收集where条件中的列字段
				if (!result.contains(expr.getColumnName())) {
					result.add(expr.getColumnName());
				}

//				if (!result.contains(expr.getColumnName())) {
//					if (expr.getColumnName().indexOf(".") == -1 && expr.getColumnName().toLowerCase().indexOf("id") > -1) {
//						result.add("id");
//					}
////					result.add(expr.getColumnName().toLowerCase());
//				}
			}

		});
		return result;
	}
	
	public static Select getSelect(Table table, List<Column> column, Expression whereExpression){
		Column[] selectColumns = (Column[]) column.toArray(new Column[column.size()]);
		Select select = SelectUtils.buildSelectFromTableAndExpressions(table, selectColumns);
		PlainSelect selectPlain = (PlainSelect) select.getSelectBody(); 
		selectPlain.setWhere(whereExpression);
		return select;
	}
	
}

