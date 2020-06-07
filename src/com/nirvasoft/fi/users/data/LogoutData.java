package com.nirvasoft.fi.users.data;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogoutData {
private String userid;
private String sessionID;
private String lastLogintime;
private String lastlogouttime;
private String duration;
private String code;
private String desc;
private String ActivityHistoryarr[];
public LogoutData()
{
	clearProperties();
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getSessionID() {
	return sessionID;
}
public void setSessionID(String sessionID) {
	this.sessionID = sessionID;
}
public String getLastLogintime() {
	return lastLogintime;
}
public void setLastLogintime(String lastLogintime) {
	this.lastLogintime = lastLogintime;
}
public String getLastlogouttime() {
	return lastlogouttime;
}
public void setLastlogouttime(String lastlogouttime) {
	this.lastlogouttime = lastlogouttime;
}
public String getDuration() {
	return duration;
}
public void setDuration(String duration) {
	this.duration = duration;
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
public String[] getActivityHistoryarr() {
	return ActivityHistoryarr;
}
public void setActivityHistoryarr(String[] activityHistoryarr) {
	ActivityHistoryarr = activityHistoryarr;
}
private void clearProperties()
{
	this.userid = "";
	this.sessionID = "";
	this.lastLogintime = "";
	this.lastlogouttime = "";
	this.duration = "";
	this.code = "";
	this.desc = "";
	this.ActivityHistoryarr = null ;
}
@Override
public String toString() {
	return "LogoutData [userid=" + userid + ", sessionID=" + sessionID + ", lastLogintime=" + lastLogintime
			+ ", lastlogouttime=" + lastlogouttime + ", duration=" + duration + ", code=" + code + ", desc=" + desc
			+ ", ActivityHistoryarr=" + Arrays.toString(ActivityHistoryarr) + "]";
}

}
