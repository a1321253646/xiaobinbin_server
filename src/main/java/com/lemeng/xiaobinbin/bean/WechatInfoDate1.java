package com.lemeng.xiaobinbin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WechatInfoDate1 {
	public String session_key="";
	public String openid="";
	public String getSession_key() {
		return session_key;
	}
	@JsonProperty
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getOpenid() {
		return openid;
	}
	@JsonProperty
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
}
