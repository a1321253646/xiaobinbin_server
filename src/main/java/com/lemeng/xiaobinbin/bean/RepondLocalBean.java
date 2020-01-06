package com.lemeng.xiaobinbin.bean;

import java.util.ArrayList;

public class RepondLocalBean {
	public String userId = "";
	public String money = "0";
	public String each_money = "0";
	public String zichang = "0";
	public String zuanshi = "0";
	public long current_map = 0;
	public long leave_time = 0;

	public long net_time = 0;
	
	public String history = "0";
	public ArrayList<BuilderStatusBean> builders = null;
	public ArrayList<SqlHaveMapInfo> mMapHave = null;
	public ArrayList<ShopItemBean> shop = null;
	public ArrayList<ZichangInfoBean> zichang_to_jingbin = null;
	public MapInfoBean mapInfo = null;
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getEach_money() {
		return each_money;
	}
	public void setEach_money(String each_money) {
		this.each_money = each_money;
	}
	public long getCurrent_map() {
		return current_map;
	}
	public void setCurrent_map(long current_map) {
		this.current_map = current_map;
	}
	public long getLeave_time() {
		return leave_time;
	}
	public void setLeave_time(long leave_time) {
		this.leave_time = leave_time;
	}
	public ArrayList<BuilderStatusBean> getBuilders() {
		return builders;
	}
	public void setBuilders(ArrayList<BuilderStatusBean> builders) {
		this.builders = builders;
	}
	public String getZichang() {
		return zichang;
	}
	public void setZichang(String zichang) {
		this.zichang = zichang;
	}
	public String getZuanshi() {
		return zuanshi;
	}
	public void setZuanshi(String zuanshi) {
		this.zuanshi = zuanshi;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public MapInfoBean getMapInfo() {
		return mapInfo;
	}
	public void setMapInfo(MapInfoBean mapInfo) {
		this.mapInfo = mapInfo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ArrayList<SqlHaveMapInfo> getmMapHave() {
		return mMapHave;
	}
	public void setmMapHave(ArrayList<SqlHaveMapInfo> mMapHave) {
		this.mMapHave = mMapHave;
	}
	public long getNet_time() {
		return net_time;
	}
	public void setNet_time(long net_time) {
		this.net_time = net_time;
	}
	public ArrayList<ShopItemBean> getShop() {
		return shop;
	}
	public void setShop(ArrayList<ShopItemBean> shop) {
		this.shop = shop;
	}
	public ArrayList<ZichangInfoBean> getZichang_to_jingbin() {
		return zichang_to_jingbin;
	}
	public void setZichang_to_jingbin(ArrayList<ZichangInfoBean> zichang_to_jingbin) {
		this.zichang_to_jingbin = zichang_to_jingbin;
	}

	
	
}
