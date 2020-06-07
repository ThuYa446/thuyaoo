package com.nirvasoft.fi.users.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserViewDataArr {
    private UserViewData[] data;
    private String searchText;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private String msgCode = "";
	private String msgDesc = "";
	private String sessionID = "";

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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public UserViewDataArr(){
    	super();
    	data = new UserViewData[1];
    }

	public UserViewData[] getdata() {
		return data;
	}

	public void setdata(UserViewData[] data) {
		this.data = data;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
