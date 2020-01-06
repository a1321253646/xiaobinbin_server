package com.lemeng.xiaobinbin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuilderStatusBean {
	public long level = 0;
	public String allmoney = "0";
	public float time_pre = 0;
	public float money_pre = 0;
	public long id = 0;
	public long lastime = 0;
	public long isAuto = 0;
	public String creatBase = "";
	public long creatTime = 0;
	
	public long getLevel() {
		return level;
	}
	@JsonProperty
	public void setLevel(long level) {
		this.level = level;
	}

	@JsonProperty
	public float getTime_pre() {
		return time_pre;
	}
	@JsonProperty
	public void setTime_pre(float time_pre) {
		this.time_pre = time_pre;
	}
	public float getMoney_pre() {
		return money_pre;
	}
	@JsonProperty
	public void setMoney_pre(float money_pre) {
		this.money_pre = money_pre;
	}

	public long getId() {
		return id;
	}
	@JsonProperty
	public void setId(long id) {
		this.id = id;
	}
	public String getAllmoney() {
		return allmoney;
	}
	@JsonProperty
	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}
	public long getLastime() {
		return lastime;
	}
	@JsonProperty
	public void setLastime(long lastime) {
		this.lastime = lastime;
	}

	public long getIsAuto() {
		return isAuto;
	}
	@JsonProperty
	public void setIsAuto(long isAuto) {
		this.isAuto = isAuto;
	}
	public String getCreatBase() {
		return creatBase;
	}
	@JsonProperty
	public void setCreatBase(String creatBase) {
		this.creatBase = creatBase;
	}
	public long getCreatTime() {
		return creatTime;
	}
	@JsonProperty
	public void setCreatTime(long creatTime) {
		this.creatTime = creatTime;
	}

	
}
