package com.nirvasoft.fi.users.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserRoleViewDataset {

	
	private ArrayList<UserRoleViewData> arlData;
	

	public ArrayList<UserRoleViewData> getArlData() {
		return arlData;
	}

	public void setArlData(ArrayList<UserRoleViewData> ret) {
		this.arlData = ret;
	}


}
