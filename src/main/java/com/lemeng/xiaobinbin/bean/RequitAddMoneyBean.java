package com.lemeng.xiaobinbin.bean;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequitAddMoneyBean {
	public String user = "";
	public ArrayList<AddMoneyBean> data  = null;
	public String getUser() {
		return user;
	}
	@JsonProperty
	public void setUser(String user) {
		this.user = user;
	}
	public ArrayList<AddMoneyBean> getData() {
		return data;
	}
	@JsonProperty
	public void setData(ArrayList<AddMoneyBean> data) {
		this.data = data;
	}
	
}
