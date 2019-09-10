package com.rao.study.trace.interceptors.mybatis;

public class PropertyHistory {
	
	private String property;
	private String oldVal;
	private String newVal;

	public PropertyHistory(String property, String oldVal, String newVal) {
		this.property = property;
		this.oldVal = oldVal;
		this.newVal = newVal;
	}

	public PropertyHistory() {
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getOldVal() {
		return oldVal;
	}

	public void setOldVal(String oldVal) {
		this.oldVal = oldVal;
	}

	public String getNewVal() {
		return newVal;
	}

	public void setNewVal(String newVal) {
		this.newVal = newVal;
	}

}
