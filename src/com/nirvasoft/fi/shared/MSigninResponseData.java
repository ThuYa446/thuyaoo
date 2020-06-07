package com.nirvasoft.fi.shared;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MSigninResponseData {
private String code;
private String desc;
private String sessionID;
private String userid;
public MSigninResponseData()
{
	this.code = "";
	this.desc = "";
	this.sessionID = "";
	this.userid = "";
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public String getSessionID() {
	return sessionID;
}
public void setSessionID(String sessionID) {
	this.sessionID = sessionID;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}


}
