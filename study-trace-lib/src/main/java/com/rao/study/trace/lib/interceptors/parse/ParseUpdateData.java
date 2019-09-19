package com.rao.study.trace.lib.interceptors.parse;

import com.google.common.collect.Lists;
import com.rao.study.trace.lib.interceptors.enums.DBActionTypeEnum;
import com.rao.study.trace.lib.interceptors.mybatis.ChangeEntity;
import com.rao.study.trace.lib.interceptors.mybatis.ChangeDataUtil;
import com.rao.study.trace.lib.interceptors.mybatis.MybatisInvocation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Slf4j
public class ParseUpdateData extends ParseContext {

	//可能批量更新
	@Override
	public List<ChangeEntity> parseBefore(MybatisInvocation mybatisInvocation) throws Throwable {

		MappedStatement mappedStatement = mybatisInvocation.getMappedStatement();
		BoundSql boundSql = mappedStatement.getBoundSql(mybatisInvocation.getParameter());
		String sql = boundSql.getSql();

		SqlParserInfo sqlParserInfo = new SqlParserInfo(sql, DBActionTypeEnum.UPDATE);

		//查询更新前的数据
		List<HashMap<String, Object>> updateBeforeResults = query(mybatisInvocation,boundSql, sqlParserInfo);

		return buildUpdateBeforeEvents(updateBeforeResults,sqlParserInfo);
	}

	@Override
	public List<ChangeEntity> parseAfter(MybatisInvocation mybatisInvocation, List<ChangeEntity> updateBeforeDatas) throws Throwable {

		MappedStatement mappedStatement = mybatisInvocation.getMappedStatement();
		BoundSql boundSql = mappedStatement.getBoundSql(mybatisInvocation.getParameter());
		String sql = boundSql.getSql();

		SqlParserInfo sqlParserInfo = new SqlParserInfo(sql, DBActionTypeEnum.UPDATE);

		//查询更新前的数据
		List<HashMap<String, Object>> updateAfterResults = query(mybatisInvocation,boundSql, sqlParserInfo);

		List<ChangeEntity> updateAfterDatas = buildUpdateAfterEvents(updateAfterResults,sqlParserInfo);

		return buildUpdateBeforeAndAfterEvents(updateBeforeDatas,updateAfterDatas);
	}


	private List<ChangeEntity> buildUpdateBeforeEvents(final List<HashMap<String, Object>> updateBeforeResults, SqlParserInfo sqlParserInfo){

		List<ChangeEntity> changeDatas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(updateBeforeResults)){
			for(HashMap<String, Object> beforeDataMap : updateBeforeResults){
				changeDatas.add(ChangeDataUtil.buildChangeDataForBefore(beforeDataMap,sqlParserInfo));
			}
		}
		return changeDatas;
	}

	private List<ChangeEntity> buildUpdateAfterEvents(final List<HashMap<String, Object>> updateAfterResults, SqlParserInfo sqlParserInfo){

		List<ChangeEntity> changeDatas = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(updateAfterResults)){
			for(HashMap<String, Object> afterDataMap : updateAfterResults){
				changeDatas.add(ChangeDataUtil.buildChangeDataForAfter(afterDataMap,sqlParserInfo));
			}
		}

		return changeDatas;
	}


	private List<ChangeEntity> buildUpdateBeforeAndAfterEvents(List<ChangeEntity> updateBeforeDatas, List<ChangeEntity> updateAfterDatas){

		updateBeforeDatas.stream().forEach(changeData -> {
			Optional<ChangeEntity> optionalChangeData = updateAfterDatas.stream().filter(afterChangeData -> afterChangeData.getEntityId().equals(changeData.getEntityId())).findAny();
			if (optionalChangeData.isPresent()) {
				ChangeEntity afterChangeData = optionalChangeData.get();
				changeData.setAfterColumnList(afterChangeData.getAfterColumnList());
				changeData.setCurrentEntityMap(afterChangeData.getCurrentEntityMap());
			}
		});

		return updateBeforeDatas;
	}
	
}
