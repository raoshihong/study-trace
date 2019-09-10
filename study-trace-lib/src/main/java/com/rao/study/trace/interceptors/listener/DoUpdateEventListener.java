package com.rao.study.trace.interceptors.listener;

import com.rao.study.trace.interceptors.mybatis.ChangeData;

import java.util.List;

public interface DoUpdateEventListener{

	void onPostUpdate(List<ChangeData> changeTable);
}
