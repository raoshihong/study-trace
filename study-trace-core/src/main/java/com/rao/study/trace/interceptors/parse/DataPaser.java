package com.rao.study.trace.interceptors.parse;

import com.rao.study.trace.interceptors.mybatis.MybatisInvocation;

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
	List<Object> parseBefore(MybatisInvocation mybatisInvocation) throws Throwable;
	
	/**
	 * 在执行update方法之后解析新数据
	 * @param mybatisInvocation
	 * @param changeDatas
	 * @return
	 * @throws Throwable
	 */
	List<Object> parseAfter(MybatisInvocation mybatisInvocation, List<Object> changeDatas)throws Throwable;
	
}
