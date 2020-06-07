package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BankData {
private String createddate;
private String modifieddate;
private String userid;
private String username;
private int recordstatus;
private int syncstatus ;
private long syncbatch;
private String shortbankname;
private String bankname;
private String bankcode;
private String bankmyanmarname;
private int noOfbranch;
private String t1;
private String t2;
private int n1;
private String sessionId;
private String msgCode;
private String msgDesc;
private String isdebtor;
public BankData(){
	clearProperties();
}
private void clearProperties()
{
	this.createddate = "";
	this.modifieddate = "";
	this.userid = "";
	this.username = "";
	this.recordstatus = 0;
	this.syncstatus = 0;
	this.syncbatch = 0;
	this.shortbankname = "";
	this.bankname = "";
	this.bankcode = "";
	this.bankmyanmarname = "";
	this.noOfbranch = 0;
	this.t1 = "";
	this.t2 = "";
	this.n1 = 0;
	this.sessionId = "";
	this.msgCode = "";
	this.msgDesc = "";
	this.isdebtor = "";
}
public String getBankname() {
	return bankname;
}
public void setBankname(String bankname) {
	this.bankname = bankname;
}
public String getBankcode() {
	return bankcode;
}
public void setBankcode(String bankcode) {
	this.bankcode = bankcode;
}
public String getCreateddate() {
	return createddate;
}
public void setCreateddate(String createddate) {
	this.createddate = createddate;
}
public String getModifieddate() {
	return modifieddate;
}
public void setModifieddate(String modifieddate) {
	this.modifieddate = modifieddate;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public int getRecordstatus() {
	return recordstatus;
}
public void setRecordstatus(int recordstatus) {
	this.recordstatus = recordstatus;
}
public int getSyncstatus() {
	return syncstatus;
}
public void setSyncstatus(int syncstatus) {
	this.syncstatus = syncstatus;
}
public long getSyncbatch() {
	return syncbatch;
}
public void setSyncbatch(long syncbatch) {
	this.syncbatch = syncbatch;
}
public String getShortbankname() {
	return shortbankname;
}
public void setShortbankname(String shortbankname) {
	this.shortbankname = shortbankname;
}
public String getBankmyanmarname() {
	return bankmyanmarname;
}
public void setBankmyanmarname(String bankmyanmarname) {
	this.bankmyanmarname = bankmyanmarname;
}
public int getNoOfbranch() {
	return noOfbranch;
}
public void setNoOfbranch(int noOfbranch) {
	this.noOfbranch = noOfbranch;
}
public String getT1() {
	return t1;
}
public void setT1(String t1) {
	this.t1 = t1;
}
public String getT2() {
	return t2;
}
public void setT2(String t2) {
	this.t2 = t2;
}
public int getN1() {
	return n1;
}
public void setN1(int n1) {
	this.n1 = n1;
}
public String getSessionId() {
	return sessionId;
}
public void setSessionId(String sessionId) {
	this.sessionId = sessionId;
}
public String getMsgCode() {
	return msgCode;
}
public void setMsgCode(String msgCode) {
	this.msgCode = msgCode;
}
public String getMsgDesc() {
	return msgDesc;
}
public void setMsgDesc(String msgDesc) {
	this.msgDesc = msgDesc;
}
public String getIsdebtor() {
	return isdebtor;
}
public void setIsdebtor(String isdebtor) {
	this.isdebtor = isdebtor;
}
@Override
public String toString() {
	return "{createddate=" + createddate + ", modifieddate=" + modifieddate + ", userid=" + userid
			+ ", username=" + username + ", recordstatus=" + recordstatus + ", syncstatus=" + syncstatus
			+ ", syncbatch=" + syncbatch + ", shortbankname=" + shortbankname + ", bankname=" + bankname + ", bankcode="
			+ bankcode + ", bankmyanmarname=" + bankmyanmarname + ", noOfbranch=" + noOfbranch + ", t1=" + t1 + ", t2="
			+ t2 + ", n1=" + n1 + ", sessionId=" + sessionId + ", msgCode=" + msgCode + ", msgDesc=" + msgDesc
			+ ", isdebtor=" + isdebtor + "}";
}


}
