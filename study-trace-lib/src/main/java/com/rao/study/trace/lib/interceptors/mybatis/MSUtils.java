package com.rao.study.trace.lib.interceptors.mybatis;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MSUtils {
	public static final String CUSTOMQUERY = "_CUSTOMQUERY_EVENT";
    private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<>(0);


    public static MappedStatement newHashMapMappedStatement(MappedStatement ms) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId() + CUSTOMQUERY, ms.getSqlSource(), ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
//
//
//
//            StringBuilder keyProperties = new StringBuilder();
//            for (String keyProperty : ms.getKeyProperties()) {
//                keyProperties.append(keyProperty).append(",");
//            }
//            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(Arrays.stream(ms.getKeyProperties()).collect(Collectors.joining(",")));
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());

        //构建结果集
        List<ResultMap> resultMaps = new ArrayList<>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), HashMap.class, EMPTY_RESULTMAPPING).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }
}

