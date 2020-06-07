package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PwdData {

private long syskey;	
private String password;
private String newpassword;
private String confirmnewpassword;
private String userid;
private String sessionID;



public PwdData() {
	clearProperties();
	// TODO Auto-generated constructor stub
}
private void clearProperties() {
	// TODO Auto-generated method stub
	syskey=0;
	password="";
	newpassword="";
	confirmnewpassword="";
	userid = "";
	sessionID = "";
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
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getNewpassword() {
	return newpassword;
}
public void setNewpassword(String newpassword) {
	this.newpassword = newpassword;
}
public String getConfirmnewpassword() {
	return confirmnewpassword;
}
public void setConfirmnewpassword(String confirmnewpassword) {
	this.confirmnewpassword = confirmnewpassword;
}
public long getSyskey() {
	return syskey;
}
public void setSyskey(long syskey) {
	this.syskey = syskey;
}
@Override
public String toString() {
	return "PwdData [syskey=" + syskey + ", password=" + password + ", newpassword=" + newpassword
			+ ", confirmnewpassword=" + confirmnewpassword + ", userid=" + userid + ", sessionId=" + sessionID
			+ "]";
}

}
