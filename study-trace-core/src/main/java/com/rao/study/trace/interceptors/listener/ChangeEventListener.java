package com.rao.study.trace.interceptors.listener;

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

	public void listenModification(String commandName, List<Object> changeDatas) {
//		DoChangeEventListener doChangeEventListener = (DoChangeEventListener) SpringApplicationContextUtil.getInstance()
//				.getBean("doChangeEventListener");
//		if (StringUtils.equals(DBActionTypeEnum.UPDATE.getValue(), commandName)) {
//			doChangeEventListener.onPostUpdate(changeDatas);
//		} else if (StringUtils.equals(DBActionTypeEnum.INSERT.getValue(), commandName)) {
//			doChangeEventListener.onPostInsert(changeDatas);
//		} else if (StringUtils.equals(DBActionTypeEnum.DELETE.getValue(), commandName)) {
//			doChangeEventListener.onPostDelete(changeDatas);
//		}
	}

}
