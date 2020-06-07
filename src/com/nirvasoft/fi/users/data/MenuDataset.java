package com.nirvasoft.fi.users.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MenuDataset {

	private String searchText;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private ArrayList<MenuData> arlData;

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

	public ArrayList<MenuData> getArlData() {
		return arlData;
	}

	public void setArlData(ArrayList<MenuData> ret) {
		this.arlData = ret;
	}


}
