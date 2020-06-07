package com.nirvasoft.fi.users.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoleMenuDataset {

	private ArrayList<RoleMenuData> arlData;
	private RoleMenuData[] roledata;
	public ArrayList<RoleMenuData> getArlData() {
		return arlData;
	}
	public void setArlData(ArrayList<RoleMenuData> arlData) {
		this.arlData = arlData;
	}
	public RoleMenuData[] getRoledata() {
		return roledata;
	}
	public void setRoledata(RoleMenuData[] roledata) {
		this.roledata = roledata;
	}

}
