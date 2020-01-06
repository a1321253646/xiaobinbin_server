package com.lemeng.xiaobinbin.bean;

import java.util.ArrayList;

public class BuilderInfoBean {
	public long buildid = 0;
	public SqlJianZhuShuXingBean current= null;
	public SqlJianZhuShuXingBean next= null;
	public ArrayList<RepondBuildStatusBaseBean> list= null;
	public ArrayList<SqlDituWeizhiBean> resource = null;
	public long getBuildid() {
		return buildid;
	}
	public void setBuildid(long buildid) {
		this.buildid = buildid;
	}
	public SqlJianZhuShuXingBean getCurrent() {
		return current;
	}
	public void setCurrent(SqlJianZhuShuXingBean current) {
		this.current = current;
	}
	public SqlJianZhuShuXingBean getNext() {
		return next;
	}
	public void setNext(SqlJianZhuShuXingBean next) {
		this.next = next;
	}
	public ArrayList<RepondBuildStatusBaseBean> getList() {
		return list;
	}
	public void setList(ArrayList<RepondBuildStatusBaseBean> list) {
		this.list = list;
	}
	public ArrayList<SqlDituWeizhiBean> getResource() {
		return resource;
	}
	public void setResource(ArrayList<SqlDituWeizhiBean> resource) {
		this.resource = resource;
	}
	
}
