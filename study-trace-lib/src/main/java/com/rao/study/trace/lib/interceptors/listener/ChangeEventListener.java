package com.rao.study.trace.lib.interceptors.listener;

import com.rao.study.trace.lib.interceptors.enums.DBActionTypeEnum;
import com.rao.study.trace.lib.interceptors.mybatis.ChangeEntity;
import com.rao.study.trace.lib.interceptors.mybatis.MybatisInvocation;
import com.rao.study.trace.lib.utils.SpringApplicationContextUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ChangeEventListener {

	public ChangeEventListener() {
	}

	private static ChangeEventListener factory = null;

	public static ChangeEventListener getInstance() {
		if (factory == null) {
			factory = new ChangeEventListener();
		}
		return factory;
	}

	public void listenModification(MybatisInvocation mybatisInvocation, List<ChangeEntity> changeDatas) {
		DoChangeEventListener doChangeEventListener = (DoChangeEventListener) SpringApplicationContextUtil.getInstance().getBean("doChangeEventListener");
		String commandName = mybatisInvocation.getMappedStatement().getSqlCommandType().name();
		if (StringUtils.equals(DBActionTypeEnum.UPDATE.getValue(), commandName)) {
			doChangeEventListener.onPostUpdate(changeDatas);
		} else if (StringUtils.equals(DBActionTypeEnum.INSERT.getValue(), commandName)) {
			doChangeEventListener.onPostInsert(changeDatas);
		} else if (StringUtils.equals(DBActionTypeEnum.DELETE.getValue(), commandName)) {
			doChangeEventListener.onPostDelete(changeDatas);
		}
	}

}
