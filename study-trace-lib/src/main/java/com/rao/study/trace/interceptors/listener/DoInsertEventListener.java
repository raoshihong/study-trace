package com.rao.study.trace.interceptors.listener;

import com.rao.study.trace.interceptors.mybatis.ChangeData;

import java.util.List;

public interface DoInsertEventListener {

	void onPostInsert(List<ChangeData> changeTable);

}