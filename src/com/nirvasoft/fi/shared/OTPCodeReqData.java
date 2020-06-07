package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OTPCodeReqData {
private String type;
private String userID;
private String sessionID;

public OTPCodeReqData()
{
	clearProperties();
}
private void clearProperties()
{
	this.type = "";
	this.userID = "";
	this.sessionID = "";
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
public String getSessionID() {
	return sessionID;
}
public void setSessionID(String sessionID) {
	this.sessionID = sessionID;
}
@Override
public String toString() {
	return "{ type=" + type + ", userID=" + userID + ", sessionID=" + sessionID + "}";
}


}
