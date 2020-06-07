package com.nirvasoft.fi.mgr;

import java.sql.Connection;
import java.sql.SQLException;

import com.nirvasoft.database.SysKeyMgr;
import com.nirvasoft.fi.dao.PersonDao;
import com.nirvasoft.fi.dao.UserDao;
import com.nirvasoft.fi.dao.UserRoleViewDao;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.MrBean;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.users.data.UserData;
import com.nirvasoft.fi.users.data.UserRoleViewData;


public class UserRoleViewDataMgr {
	
	
	public static UserRoleViewData[] getUserRoleList() {
		
		UserRoleViewData[] dataarray;
		Connection conn = ConnAdmin.getConn("001", "");
		dataarray = UserRoleViewDao.getUserRoleList(conn);
		return dataarray ;
	}
	
	public static UserRoleViewData[] getMerchantUserList(MrBean user) {
		
		UserRoleViewData[] dataarray;
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID(), "");
		dataarray = UserRoleViewDao.getMerchantUserList(conn);
		return dataarray ;
	}
	
	//zmth
	public long[] getCustomerRole(MrBean user) {
	        
	        long[] rsyskey;
	        Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID(), "");
	        UserRoleViewDao dao = new UserRoleViewDao();
	        rsyskey = dao.getCustomerRole(conn);
	        return rsyskey;
	        
	    }
}
