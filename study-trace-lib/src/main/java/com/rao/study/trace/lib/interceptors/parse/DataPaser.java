package com.rao.study.trace.lib.interceptors.parse;

import com.rao.study.trace.lib.interceptors.mybatis.ChangeEntity;
import com.rao.study.trace.lib.interceptors.mybatis.MybatisInvocation;

import java.util.List;

/**
 * 数据解析器
 */
public interface DataPaser {
	
	/**
	 * 在执行update方法之前解析旧数据
	 * @param mybatisInvocation
	 * @return
	 * @throws Throwable
	 */
	List<ChangeEntity> parseBefore(MybatisInvocation mybatisInvocation) throws Throwable;
	
	/**
	 * 在执行update方法之后解析新数据
	 * @param mybatisInvocation
	 * @param changeDatas
	 * @return
	 * @throws Throwable
	 */
	List<ChangeEntity> parseAfter(MybatisInvocation mybatisInvocation, List<ChangeEntity> changeDatas)throws Throwable;
	
}
