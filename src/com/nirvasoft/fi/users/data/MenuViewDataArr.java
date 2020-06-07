package com.nirvasoft.fi.users.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MenuViewDataArr {
    private MenuViewData[] data;
    private String searchText;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private String msgCode;
	private String msgDesc;
    
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

	public MenuViewDataArr(){
    	super();
    	data = new MenuViewData[1];	
    	
    }

	public MenuViewData[] getdata() {
		return data;
	}

	public void setdata(MenuViewData[] data) {
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
