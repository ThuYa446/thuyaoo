package com.nirvasoft.fi.users.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MenuViewDataset {
	
	private ArrayList<MenuViewData> arlData;	

	
	public ArrayList<MenuViewData> getArlData() {
		return arlData;
	}

	public void setArlData(ArrayList<MenuViewData> ret) {
		this.arlData = ret;
	}


}
