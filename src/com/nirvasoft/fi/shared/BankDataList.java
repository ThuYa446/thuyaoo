package com.nirvasoft.fi.shared;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BankDataList {
private BankData[] data;
private int totalCount;
private int currentPage;
private int pageSize;
private String searchText;
private String msgCode;
private String msgDesc;

public BankDataList(){
	clearProperties();
}
private void clearProperties(){
	this.data = null;
	this.totalCount = 0;
	this.currentPage = 1;
	this.pageSize = 10;
	this.msgCode = "";
	this.msgDesc = "";
}
public BankData[] getData() {
	return data;
}
public void setData(BankData[] data) {
	this.data = data;
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
public String getSearchText() {
	return searchText;
}
public void setSearchText(String searchText) {
	this.searchText = searchText;
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
@Override
public String toString() {
	return "{data=" + Arrays.toString(data) + ", totalCount=" + totalCount + ", currentPage=" + currentPage
			+ ", pageSize=" + pageSize + ", searchText=" + searchText + ", msgCode=" + msgCode + ", msgDesc=" + msgDesc
			+ "}";
}

}
