package com.nirvasoft.fi.users.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoleDatas {
    private RoleData[] data;
    private String searchText;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private String msgCode;
	private String msgDesc;
    
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

	public RoleDatas(){
    	super();
    	data = new RoleData[1];
    	/*
    	UserData d = new UserData();
    	//d.sethub("hayyyyyyyyyyyyyyyyy success");    	
    	data[0] = d;
    	d = new UserData();
    	//d.sethub("hayyyyyyyyyyyyyyyyy success2");    	
    	data[1] = d;*/
    	
    }

	public RoleData[] getdata() {
		return data;
	}

	public void setdata(RoleData[] data) {
		this.data = data;
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
    
    
}
