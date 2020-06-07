package com.nirvasoft.fi.users.data;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoleDataset {

	private String searchText;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	public ArrayList<RoleData> getArlData() {
		return arlData;
	}

	public void setArlData(ArrayList<RoleData> arlData) {
		this.arlData = arlData;
	}

	private ArrayList<RoleData> arlData;
	private RoleData[] roledata;

	public RoleData[] getRoledata() {
		return roledata;
	}

	public void setRoledata(RoleData[] roledata) {
		this.roledata = roledata;
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
