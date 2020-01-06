package com.lemeng.xiaobinbin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequiteBuilderStatusBean {
	public String user = "";
	public BuilderStatusBean date = new BuilderStatusBean();
	public String getUser() {
		return user;
	}
	@JsonProperty
	public void setUser(String user) {
		this.user = user;
	}
	public BuilderStatusBean getDate() {
		return date;
	}
	@JsonProperty
	public void setDate(BuilderStatusBean date) {
		this.date = date;
	}
	
}
