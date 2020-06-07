package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserReportData {
	private String msgCode = "";
	private String msgDesc = "";
    private String sessionID = "";	    
	private String aFromDate;
	private String aToDate;	
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private String select;
	private String msgstatus;
	private String username;
	private String nrc;
	private String phoneno;
	private String usertype;
	private String status;
	

	



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
	
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}	
	
	public UserReportData() {
		clearProperty();
	}

	private void clearProperty() {
		aFromDate = "";
		aToDate = "";
		totalCount = 0;
		currentPage = 0;
		pageSize = 0;
		username = "";
		nrc = "";
		phoneno = "";
		status = "";
		usertype = "";
	}
	
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getaFromDate() {
		return aFromDate;
	}

	public void setaFromDate(String aFromDate) {
		this.aFromDate = aFromDate;
	}

	public String getaToDate() {
		return aToDate;
	}

	public void setaToDate(String aToDate) {
		this.aToDate = aToDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getMsgstatus() {
		return msgstatus;
	}

	public void setMsgstatus(String msgstatus) {
		this.msgstatus = msgstatus;
	}

}
