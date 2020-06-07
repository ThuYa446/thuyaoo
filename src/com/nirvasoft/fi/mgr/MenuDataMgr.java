package com.nirvasoft.fi.mgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.nirvasoft.database.SysKeyMgr;
import com.nirvasoft.fi.dao.MenuDao;
import com.nirvasoft.fi.dao.UserDao;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.Menu;
import com.nirvasoft.fi.framework.MrBean;
import com.nirvasoft.fi.framework.Profile;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.users.data.MenuData;
import com.nirvasoft.fi.users.data.MenuDataArr;
import com.nirvasoft.fi.users.data.MenuDataset;
import com.nirvasoft.fi.users.data.MenuRole;
import com.nirvasoft.fi.users.data.MenuViewData;
import com.nirvasoft.fi.users.data.MenuViewDataArr;
import com.nirvasoft.fi.users.data.MenuViewDataset;
import com.nirvasoft.fi.users.data.RoleMenuData;
import com.nirvasoft.fi.users.data.UserRole;
import com.nirvasoft.fi.util.ServerUtil;

public class MenuDataMgr {
	public static Result saveMenuData(MenuData data) {
		Result res = new Result();

		Connection conn = null;
		
		try {
			conn = ConnAdmin.getConn("001", "");
			long rolemenu= data.getSyskey();
			res = saveMenuData(data, conn);
			if(res.isState() &&  rolemenu==0)
			{
			      res= saveRoleMenuData(data,conn);	
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}

	public static MenuDataArr getmainmenulist() {
		MenuDataArr res = new MenuDataArr();
		MenuDataset dataSet = new MenuDataset();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001","");
			dataSet = MenuDao.getmainmenulist(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		
		MenuData[] dataarry = new MenuData[dataSet.getArlData().size()];
		if(dataarry.length==1) { 
		dataarry = new MenuData[dataSet.getArlData().size()+1]; 
		dataarry[0] = dataSet.getArlData().get(0); 
		dataarry[1] = new MenuData(); 
		}
		if(dataarry.length==1)
		{
			dataarry = new MenuData[dataSet.getArlData().size()+1];
			dataarry[0] = dataSet.getArlData().get(0);
			dataarry[1] = new MenuData();
		}
		
		//RoleData[] dataarry = dataSet.getRoledata();
		
		for(int i=0;i<dataSet.getArlData().size();i++){
			dataarry[i] = dataSet.getArlData().get(i);
		}
		
		res.setdata(dataarry);
		
		return res;
	}
	
	
	public static Result saveRoleMenuData(MenuData data, Connection conn) throws SQLException {
	Result res=new Result();
	MenuRole mr=new MenuRole();
	mr = initMenuRoleData(mr,data,conn);
	ServerUtil.closeConnection(conn);
	conn = ConnAdmin.getConn("001", "");;
	if (mr.getSyskey() == 0) {
		
		mr.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));
		res=MenuDao.insertMenuRole(mr, conn);
	} else {
		res = MenuDao.update(data, conn);	/// need to edit		
	}
	if (res.isState()) {
		
		res.getLongResult().add(data.getN2());
		res.getLongResult().add(data.getSyskey());
		long[] keyarry = new long[res.getLongResult().size()];

        for (int i = 0; i < res.getLongResult().size(); i++) {
            keyarry[i] = res.getLongResult().get(i); 
            System.out.println("HELLO KEY ARRAY"+keyarry[i]);
        }

        res.setKey(keyarry);

	}
	return res;

	}
	
	public static Result saveMenuData(MenuData data, Connection conn) throws SQLException {
		Result res = new Result();
		
		data = initData(data, conn);
		ServerUtil.closeConnection(conn);
		conn = ConnAdmin.getConn("001", "");
		 
		if (data.getSyskey() == 0) {
			data.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));
			res = MenuDao.insert(data, conn);
		
		} else {
			
			res = MenuDao.update(data, conn);			
		}
		if (res.isState()) {
			
			
		}
		return res;
	}
	
	public static String getUserName(String userid,Connection con) throws SQLException {
		String st="";
		MenuDao m_dao = new MenuDao();
		
		try {
			st=m_dao.getUserName(userid, con);
			
		} finally {
			ServerUtil.closeConnection(con);
		}
		return st;
	}
	
	
	public static MenuRole initMenuRoleData(MenuRole mr, MenuData data, Connection con) 
	{
		
		String date23 = new SimpleDateFormat("yyyyMMdd").format(new Date());
		mr.setUserId(data.getUserId());
		mr.setUserName(data.getUserName());
		mr.setModifiedDate(date23);	
		mr.setN1(1);
		mr.setN2(data.getSyskey());
		
		if (mr.getSyskey() == 0) {
			mr.setCreatedDate(date23);
			mr.setRecordStatus(1);
			mr.setSyncBatch(0);
			mr.setSyncStatus(1);	
		}

		
		return  mr;
	}
	
	public static MenuData initData(MenuData data,Connection con) {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		try {
			data.setUserName(getUserName(data.getUserId(),con));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setModifiedDate(date);
		if (data.getSyskey() == 0) {
			data.setCreatedDate(date);
			data.setRecordStatus(1);
			data.setSyncBatch(0);
			data.setSyncStatus(1);
		}

		return data;
	}
	
	public static MenuViewDataArr getAllMenuData(String searchText,int pageSize,int currentPage) {
		MenuViewDataArr res = new MenuViewDataArr();
		MenuViewDataset dataSet = new MenuViewDataset();
		Connection conn = ConnAdmin.getConn("001", "");
		try {
			dataSet = MenuDao.getAllMenuData(searchText, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		int startPage = (currentPage - 1) * pageSize;
		int endPage = pageSize + startPage;
		res.setTotalCount(dataSet.getArlData().size());
		dataSet.setArlData(new ArrayList<MenuViewData>(dataSet.getArlData().subList(startPage,
				(endPage > dataSet.getArlData().size()) ? dataSet.getArlData().size() : endPage)));
		
		MenuViewData[] dataarry = new MenuViewData[dataSet.getArlData().size()];
		if(dataarry.length==1) 
		{ 
		dataarry = new MenuViewData[dataSet.getArlData().size()+1]; 
		dataarry[0] = dataSet.getArlData().get(0); 
		dataarry[1] = new MenuViewData(); 
		}
		for(int i=0;i<dataSet.getArlData().size();i++)
		{
			dataarry[i] = dataSet.getArlData().get(i);
		}
		
		res.setdata(dataarry);
		res.setSearchText(searchText);
		res.setCurrentPage(currentPage);
		res.setPageSize(pageSize);
		
		return res;
	}

	public static MenuData readDataBySyskey(long pKey) {
		MenuData res = new MenuData();
		Connection conn = ConnAdmin.getConn("001", "");
		try {
			res = MenuDao.read(pKey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static Result deleteMenuData(long syskey,String isParent) {
		Result res = new Result();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			res = MenuDao.delete(syskey,isParent, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
