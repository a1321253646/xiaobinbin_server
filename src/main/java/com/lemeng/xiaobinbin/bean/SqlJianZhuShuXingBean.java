package com.lemeng.xiaobinbin.bean;

public class SqlJianZhuShuXingBean {
	public long ID = 0;
	public long level = 0;
	public String level_up_cost = "0";
	public String creatBase = "";
	public long resid  =0;
	public long skill = 0;
	public long param = 0;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}

	public long getLevel() {
		return level;
	}
	public void setLevel(long level) {
		this.level = level;
	}
	public String getLevel_up_cost() {
		return level_up_cost;
	}
	public void setLevel_up_cost(String level_up_cost) {
		this.level_up_cost = level_up_cost;
	}
	public String getCreatBase() {
		return creatBase;
	}
	public void setCreatBase(String creatBase) {
		this.creatBase = creatBase;
	}
	public long getSkill() {
		return skill;
	}
	public void setSkill(long skill) {
		this.skill = skill;
	}
	public long getParam() {
		return param;
	}
	public void setParam(long param) {
		this.param = param;
	}
	public long getResid() {
		return resid;
	}
	public void setResid(long resid) {
		this.resid = resid;
	}	
	
	
}
