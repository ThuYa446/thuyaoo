package com.nirvasoft.fi.mgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.nirvasoft.database.SysKeyMgr;
import com.nirvasoft.fi.dao.PersonDao;
import com.nirvasoft.fi.dao.UserDao;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.MrBean;
import com.nirvasoft.fi.users.data.PersonData;
import com.nirvasoft.fi.users.data.Result;
import com.nirvasoft.fi.users.data.UserDataArr;
import com.nirvasoft.fi.users.data.UserDataset;
import com.nirvasoft.fi.users.data.UserRole;
import com.nirvasoft.fi.util.ServerUtil;



public class PersonDataMgr {
	
	/*public static PersonData readPersonData(MrBean user) {
		PersonData res = new PersonData();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			String userid = user.getUser().getUserId();
			String userpsw = user.getUser().getPassword();
			res = PersonDao.read(userid, userpsw, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}*/

	
}
