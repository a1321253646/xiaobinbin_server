package com.lemeng.xiaobinbin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddMoneyBean {
	public long builderId = 0;
	public String money = "";
	public long getBuilderId() {
		return builderId;
	}
	@JsonProperty
	public void setBuilderId(long builderId) {
		this.builderId = builderId;
	}
	public String getMoney() {
		return money;
	}
	@JsonProperty
	public void setMoney(String money) {
		this.money = money;
	}
	
}
