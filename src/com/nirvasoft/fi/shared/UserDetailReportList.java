package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDetailReportList {

	private UserDetailReportData[] data;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	
	private String msgCode = "";
	private String msgDesc = "";

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
	
	public UserDetailReportList(){
		clearProperty();
	}
	private void clearProperty(){
		data = null;
		totalCount = 0;
		currentPage = 0 ;
		pageSize = 0;
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

	public UserDetailReportData[] getData() {
		return data;
	}

	public void setData(UserDetailReportData[] data) {
		this.data = data;
	}

	
    
}
