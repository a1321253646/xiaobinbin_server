package com.lemeng.xiaobinbin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequiteWxLoginInfo {
	public String encryptedData = "";
	public String iv = "";
	public String code = "";
	public String getEncryptedData() {
		return encryptedData;
	}
	@JsonProperty
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	public String getIv() {
		return iv;
	}
	@JsonProperty
	public void setIv(String iv) {
		this.iv = iv;
	}
	public String getCode() {
		return code;
	}
	@JsonProperty
	public void setCode(String code) {
		this.code = code;
	} 
	
}
