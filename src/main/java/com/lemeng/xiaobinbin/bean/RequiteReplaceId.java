package com.lemeng.xiaobinbin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequiteReplaceId {

	public String old;
	public String newId;

	public String getOld() {
		return old;
	}
	@JsonProperty
	public void setOld(String old) {
		this.old = old;
	}
	public String getNewId() {
		return newId;
	}
	@JsonProperty
	public void setNewId(String newId) {
		this.newId = newId;
	}
}
