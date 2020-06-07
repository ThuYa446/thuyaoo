package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MSigninRequestData {
private String userID;
private String password;
private String deviceID;
private String location;
public MSigninRequestData()
{
	clearProperties();
}
public void clearProperties()
{
	this.userID = "";
	this.password = "";
	this.deviceID = "";
	this.location = "";
}
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getDeviceID() {
	return deviceID;
}
public void setDeviceID(String deviceID) {
	this.deviceID = deviceID;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
@Override
public String toString() {
	return "{userID=" + userID + ", password=" + password + ", deviceID=" + deviceID + ", location="
			+ location + "}";
}

}
