package com.nirvasoft.fi.mgr;

import java.sql.Connection;

import com.nirvasoft.fi.dao.RoleMenuDao;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.MrBean;
import com.nirvasoft.fi.users.data.RoleMenuData;


public class RoleMenuDataMgr {
	
	
	public static RoleMenuData[] getRoleMenuList() {
		
		RoleMenuData[] dataarray = null;
		Connection conn = null;
		try
		{
			conn = ConnAdmin.getConn("001", "");
			dataarray = RoleMenuDao.getRoleMenuList(conn);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dataarray ;
	}
	

}
