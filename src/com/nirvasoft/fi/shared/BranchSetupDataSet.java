package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

import com.nirvasoft.fi.data.BranchData;

@XmlRootElement
public class BranchSetupDataSet {
	private BranchData[] data;
    private String searchText;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private String msgCode = "";
	private String msgDesc = "";
	private String sessionID;

	public BranchSetupDataSet() {
		clearProperty();
	}
	private void clearProperty()
	{
		searchText = "";
		totalCount = 0;
		currentPage = 0;
		pageSize = 0;
		msgCode = "";
		msgDesc = "";
		sessionID = "";
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

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public BranchData[] getData() {
		return data;
	}
	public void setData(BranchData[] data) {
		this.data = data;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
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
	
}
