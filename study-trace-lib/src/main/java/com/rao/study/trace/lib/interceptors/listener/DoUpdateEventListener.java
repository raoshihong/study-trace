package com.rao.study.trace.lib.interceptors.listener;

import com.rao.study.trace.lib.interceptors.mybatis.ChangeEntity;

import java.util.List;

public interface DoUpdateEventListener{

	void onPostUpdate(List<ChangeEntity> changeTable);
}