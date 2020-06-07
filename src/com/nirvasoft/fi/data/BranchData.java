package com.nirvasoft.fi.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BranchData {
	
	private long syskey;
	private String createddate;
	private String modifieddate;
	private String userid;
	private String username;
	private int recordstatus;
	private String branchCode;
	private String branchName;
	private String address;
	private String phone;
	private String fax;
	private int isOtherBranch;
	private String email;
	private String t1;
	private String t2;
	private String t3;
	private String t4;
	private long banksyskey;
	private long n1;
	private int n2;
	private int n3;
	private String bankname;
	private String bankcode;
	private String msgCode = "";
	private String msgDesc = "";
	private String sessionId;
	
	public BranchData(){
		clearProperties();
	}
	
	private void clearProperties(){
		
		syskey = 0;
		sessionId = "";
		createddate ="";
		modifieddate = "";
		userid = "";
		username ="";
		recordstatus =0;
		branchCode ="";
		branchName ="";
		address="";
		phone="";
		fax="";
		isOtherBranch=0;
		t1="";
		t2="";
		t3="";
		t4="";
		banksyskey=0;
		n1 = 0;
		n2 =0;
		n3 = 0;
		bankname = "";
		bankcode = "";
		email = "";
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getMsgCode() {
		return msgCode;
	}


	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
	

	public long getSyskey() {
		return syskey;
	}

	public void setSyskey(long syskey) {
		this.syskey = syskey;
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

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getIsOtherBranch() {
		return isOtherBranch;
	}

	public void setIsOtherBranch(int isOtherBranch) {
		this.isOtherBranch = isOtherBranch;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getT3() {
		return t3;
	}

	public void setT3(String t3) {
		this.t3 = t3;
	}

	public String getT4() {
		return t4;
	}

	public void setT4(String t4) {
		this.t4 = t4;
	}

	public long getBanksyskey() {
		return banksyskey;
	}

	public void setBanksyskey(long banksyskey) {
		this.banksyskey = banksyskey;
	}

	public long getN1() {
		return n1;
	}

	public void setN1(long n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public int getN3() {
		return n3;
	}

	public void setN3(int n3) {
		this.n3 = n3;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "BranchData [syskey=" + syskey + ", createddate=" + createddate + ", modifieddate=" + modifieddate
				+ ", userid=" + userid + ", username=" + username + ", recordstatus=" + recordstatus + ", branchCode="
				+ branchCode + ", branchName=" + branchName + ", address=" + address + ", phone=" + phone + ", fax="
				+ fax + ", isOtherBranch=" + isOtherBranch + ", email=" + email + ", t1=" + t1 + ", t2=" + t2 + ", t3="
				+ t3 + ", t4=" + t4 + ", banksyskey=" + banksyskey + ", n1=" + n1 + ", n2=" + n2 + ", n3=" + n3
				+ ", bankname=" + bankname + ", bankcode=" + bankcode + ", msgCode=" + msgCode + ", msgDesc=" + msgDesc
				+ ", sessionId=" + sessionId + "]";
	}

}


