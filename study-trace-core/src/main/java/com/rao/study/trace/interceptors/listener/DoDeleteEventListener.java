package com.rao.study.trace.interceptors.listener;

import java.util.List;

public interface DoDeleteEventListener {

	void onPostDelete(List<Object> changeTable);
}
