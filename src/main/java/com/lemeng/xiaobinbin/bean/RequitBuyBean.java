package com.lemeng.xiaobinbin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequitBuyBean {
	public int type=0;//1 为购买建筑 2为升级建筑  3 购买道具 4 为购买地图 5为数值更新 6 为购买宝箱
	public int mapid;
	public String allMoney = "";
	public String allZichang = "";
	public String histroy = "";
	public String mapCreat = "";
	public String mapCost = "";
	public String user = "";
	public String zibenbeilv = "";
	public String moneybeilv = "";
	public String timebeilv = "";
	//购买建筑的参数 升级建筑的参数
	public BuilderStatusBean builderInfo = null;
	// 购买道具的参数
	public String daojuInfo = "";
	// 购买地图的参数
	public int buyMapId = 0;
	//宝箱先不管
	
	public int unlock = 0;

	public int getType() {
		return type;
	}
	@JsonProperty
	public void setType(int type) {
		this.type = type;
	}

	public int getMapid() {
		return mapid;
	}
	@JsonProperty
	public void setMapid(int mapid) {
		this.mapid = mapid;
	}

	public String getAllMoney() {
		return allMoney;
	}
	@JsonProperty
	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}

	public String getAllZichang() {
		return allZichang;
	}
	@JsonProperty
	public void setAllZichang(String allZichang) {
		this.allZichang = allZichang;
	}

	public String getHistroy() {
		return histroy;
	}
	@JsonProperty
	public void setHistroy(String histroy) {
		this.histroy = histroy;
	}

	public String getMapCreat() {
		return mapCreat;
	}
	@JsonProperty
	public void setMapCreat(String mapCreat) {
		this.mapCreat = mapCreat;
	}

	public String getMapCost() {
		return mapCost;
	}
	@JsonProperty
	public void setMapCost(String mapCost) {
		this.mapCost = mapCost;
	}

	public String getUser() {
		return user;
	}
	@JsonProperty
	public void setUser(String user) {
		this.user = user;
	}

	public BuilderStatusBean getBuilderInfo() {
		return builderInfo;
	}
	@JsonProperty
	public void setBuilderInfo(BuilderStatusBean builderInfo) {
		this.builderInfo = builderInfo;
	}

	public String getDaojuInfo() {
		return daojuInfo;
	}
	@JsonProperty
	public void setDaojuInfo(String daojuInfo) {
		this.daojuInfo = daojuInfo;
	}

	public int getBuyMapId() {
		return buyMapId;
	}
	@JsonProperty
	public void setBuyMapId(int buyMapId) {
		this.buyMapId = buyMapId;
	}

	public int getUnlock() {
		return unlock;
	}
	@JsonProperty
	public void setUnlock(int unlock) {
		this.unlock = unlock;
	}
	public String getZibenbeilv() {
		return zibenbeilv;
	}
	@JsonProperty
	public void setZibenbeilv(String zibenbeilv) {
		this.zibenbeilv = zibenbeilv;
	}
	public String getMoneybeilv() {
		return moneybeilv;
	}
	@JsonProperty
	public void setMoneybeilv(String moneybeilv) {
		this.moneybeilv = moneybeilv;
	}
	public String getTimebeilv() {
		return timebeilv;
	}
	@JsonProperty
	public void setTimebeilv(String timebeilv) {
		this.timebeilv = timebeilv;
	}
	
	
}
