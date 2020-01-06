package com.lemeng.xiaobinbin.bean;

import java.util.ArrayList;

public class MapInfoBean {
	public ArrayList<SqlDituPeizhiBean> allMapInfo = null;
	public ArrayList<BuilderInfoBean> builderInfoList = new ArrayList<BuilderInfoBean>();
	public ArrayList<SqlDituPeizhiBean> getAllMapInfo() {
		return allMapInfo;
	}
	public void setAllMapInfo(ArrayList<SqlDituPeizhiBean> allMapInfo) {
		this.allMapInfo = allMapInfo;
	}

	public ArrayList<BuilderInfoBean> getBuilderInfoList() {
		return builderInfoList;
	}
	public void setBuilderInfoList(ArrayList<BuilderInfoBean> builderInfoList) {
		this.builderInfoList = builderInfoList;
	}
	
}
