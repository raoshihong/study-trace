package com.rao.study.trace.interceptors.listener;

import java.util.List;

public interface DoUpdateEventListener{

	void onPostUpdate(List<Object> changeTable);
}
