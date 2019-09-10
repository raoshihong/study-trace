package com.rao.study.trace.interceptors.listener;

import com.rao.study.trace.interceptors.enums.DBActionTypeEnum;
import com.rao.study.trace.interceptors.mybatis.ChangeData;
import com.rao.study.trace.utils.SpringApplicationContextUtil;
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

	public void listenModification(String commandName, List<ChangeData> changeDatas) {
		DoChangeEventListener doChangeEventListener = (DoChangeEventListener) SpringApplicationContextUtil.getInstance()
				.getBean("doChangeEventListener");
		if (StringUtils.equals(DBActionTypeEnum.UPDATE.getValue(), commandName)) {
			doChangeEventListener.onPostUpdate(changeDatas);
		} else if (StringUtils.equals(DBActionTypeEnum.INSERT.getValue(), commandName)) {
			doChangeEventListener.onPostInsert(changeDatas);
		} else if (StringUtils.equals(DBActionTypeEnum.DELETE.getValue(), commandName)) {
			doChangeEventListener.onPostDelete(changeDatas);
		}
	}

}
