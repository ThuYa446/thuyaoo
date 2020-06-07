package com.nirvasoft.fi.users.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserViewDataset {
	
	private int totalCount;
	private int currentPage;
	private int pageSize;
	
	private ArrayList<UserViewData> arlData;	

	
	public ArrayList<UserViewData> getArlData() {
		return arlData;
	}

	public void setArlData(ArrayList<UserViewData> ret) {
		this.arlData = ret;
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

	private void clearProperty(){
		totalCount = 0;
		currentPage = 0 ;
		pageSize = 0;
	}

}
