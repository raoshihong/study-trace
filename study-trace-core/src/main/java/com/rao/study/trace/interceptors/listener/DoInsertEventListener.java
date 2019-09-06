package com.rao.study.trace.interceptors.listener;

import java.util.List;

public interface DoInsertEventListener {

	void onPostInsert(List<Object> changeTable);

}
