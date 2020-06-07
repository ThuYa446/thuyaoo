package com.nirvasoft.fi.mgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nirvasoft.database.SysKeyMgr;
import com.nirvasoft.fi.dao.RoleDao;
import com.nirvasoft.fi.dao.RoleMenuDao;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.MrBean;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.users.data.MenuData;
import com.nirvasoft.fi.users.data.MenuRole;
import com.nirvasoft.fi.users.data.MenuViewData;
import com.nirvasoft.fi.users.data.MenuViewDataArr;
import com.nirvasoft.fi.users.data.RoleData;
import com.nirvasoft.fi.users.data.RoleDatas;
import com.nirvasoft.fi.users.data.RoleDataset;
import com.nirvasoft.fi.users.data.RoleMenuData;
import com.nirvasoft.fi.util.ServerUtil;


public class RoleDataMgr {
	public static Result saveRoleData(RoleData data) {
		Result res = new Result();

		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			res = saveRoleData(data, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}

	public static Result saveRoleData(RoleData data, Connection conn) throws SQLException {
		Result res = new Result();
		Result res1 = new Result();
		
		data = initData(data,conn);
		ServerUtil.closeConnection(conn);
		conn = ConnAdmin.getConn("001", "");
		if (data.getSyskey() == 0) {
			data.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));
			res = RoleDao.insert(data, conn);
			if(res.isState())
			{
				res1= insertRoleMenu(data, conn);
				
				if(res1.isState())
					res1.setMsgDesc("Saved successfully");
				else
					res1.setMsgDesc("Cannot save");
			}
			  
			else
				res1.setMsgDesc("Data already exist");
			
		} else {
			res = RoleDao.update(data, conn);
			
			if(res.isState())
			{
				res1 = updateRoleMenu(data, conn);
				if(res1.isState())
					res1.setMsgDesc("Updated successfully");
			}
			else
				res1.setMsgDesc("Data already exist");
			
			
		}
		if (res1.isState()) {
			res1.setKeyResult(data.getSyskey());
		}
		return res1;
	}
	
	public static RoleData initData(RoleData data,Connection con) {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		try {
			data.setUserName(getUserName(data.getUserId(),con));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setModifiedDate(date);
		data.setRecordStatus(1);
		data.setSyncBatch(0);
		data.setSyncStatus(1);
		data.setT3(new String("51"));  //for Module
		
		if(data.getSyskey()==0)
		{
			data.setCreatedDate(date);
		}
		
		return data;
	}
	
	public static String getUserName(String userid,Connection con) throws SQLException {
		String st="";
		RoleDao r_dao = new RoleDao();
		
		try {
			st=r_dao.getUserName(userid, con);
			
		} finally {
			ServerUtil.closeConnection(con);
		}
		return st;
	}
	
	
	public static RoleDatas getAllRoleData() {
		RoleDatas res = new RoleDatas();
		RoleDataset dataSet = new RoleDataset();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			dataSet = RoleDao.getAllRoleData(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		
		RoleData[] dataarray = new RoleData[dataSet.getArlData().size()];
		
		if(dataarray.length==1)
		{
			dataarray = new RoleData[dataSet.getArlData().size()+1];
			dataarray[0] = dataSet.getArlData().get(0);
			dataarray[1] = new RoleData();
		}
		
		for(int i=0;i<dataSet.getArlData().size();i++){
			dataarray[i] = dataSet.getArlData().get(i);
		}
		
		res.setdata(dataarray);
		
		return res;
	}
	
	public static RoleData readDataBySyskey(long pKey) {
		RoleData res = new RoleData();
		Connection conn = null;
		try {
			  conn = ConnAdmin.getConn("001", "");
			  res = RoleDao.read(pKey, conn);
			  RoleMenuData[] dataarray; 
			  dataarray= RoleMenuDao.getRoleMenuList(pKey, conn);
			  long pvalue[]=RoleMenuDao.getMenuResult(pKey,conn);
			  long cvalue[]=RoleMenuDao.getChildResult(pKey,conn);
			  res.setMenu(dataarray);
			  res.setParentsyskey(pvalue);
			  res.setChildsyskey(cvalue);
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public static Result deleteRoleData(long syskey) {
		Result res = new Result();
		Connection conn = null;
		
		try {
			conn = ConnAdmin.getConn("001", "");
			Result ret=RoleDao.deleteRoleMenu(syskey, conn);
			if(ret.isState())
			{
				res = RoleDao.delete(syskey, conn);
				res.setMsgDesc("Deleted successfully");
			}
			else
				res.setMsgDesc("Cannot Delete");
				
			
		} catch (SQLException e) {
			res.setMsgCode("0014");
			res.setMsgDesc(e.getMessage());
		}
		return res;
	}
	
	public static Result insertRoleMenu (RoleData ur, Connection conn)
	{
		Result res = new Result();
		try
		{
			if(ur.getParentsyskey()!=null && ur.getParentsyskey().length > 0)
			for(int i=0; i< ur.getParentsyskey().length; i++)
			{
				res = new Result();
				MenuRole obj = new MenuRole( );
				String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
				obj.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));
				obj.setUserId(ur.getUserId());
				obj.setUserName(ur.getUserName());
				obj.setCreatedDate(date);
				obj.setModifiedDate(date);
				obj.setRecordStatus(1);
				obj.setN1(ur.getSyskey());  //row syskey
				obj.setN2(ur.getParentsyskey()[i]);	//menu syskey
				obj.setN3(ur.getN3());	//2 --> admin ,3 --> merchant
				res=RoleDao.insertRoleMenu(obj,conn);
			   
				
			}
			
			if(ur.getChildsyskey()!=null && ur.getChildsyskey().length > 0)
			for(int i=0; i< ur.getChildsyskey().length; i++)
			{
			
			  if(RoleDao.checkParentContain(ur.getParentsyskey(),ur.getChildsyskey()[i],conn)){
				res = new Result();
				MenuRole obj = new MenuRole( );
				String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
				obj.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));
				obj.setUserId(ur.getUserId());
				obj.setUserName(ur.getUserName());
				obj.setCreatedDate(date);
				obj.setModifiedDate(date);
				obj.setRecordStatus(1);
				obj.setN1(ur.getSyskey());  //row syskey
				obj.setN2(ur.getChildsyskey()[i]);	//menu syskey
				res=RoleDao.insertRoleMenu(obj,conn);
			
			  }
				
			}
			
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		return res;
	}
	
	public static Result updateRoleMenu (RoleData ur, Connection conn)
	{
		Result res = new Result();
		try
		{
			
			res = RoleDao.updateRoleMenu(ur,conn);
			if(res.isState())
			{
				res=insertRoleMenu(ur, conn);	
			}
			   
		
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		return res;
	}
	
	public RoleDatas getAllRoleListData(String searchText,int pageSize,int currentPage) {
		RoleDatas res = new RoleDatas();
		RoleDataset dataSet = new RoleDataset();
		Connection conn = ConnAdmin.getConn("001", "");
		try {
			dataSet = RoleDao.getAllRoleListData(searchText,pageSize , currentPage,  conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		int startPage = (currentPage - 1) * pageSize;
		int endPage = pageSize + startPage;
		res.setTotalCount(dataSet.getArlData().size());
		dataSet.setArlData(new ArrayList<RoleData>(dataSet.getArlData().subList(startPage,
				(endPage > dataSet.getArlData().size()) ? dataSet.getArlData().size() : endPage)));
		RoleData[] dataarray = new RoleData[dataSet.getArlData().size()];
		
		if(dataarray.length==1)
		{
			dataarray = new RoleData[dataSet.getArlData().size()+1];
			dataarray[0] = dataSet.getArlData().get(0);
			dataarray[1] = new RoleData();
		}
		
		for(int i=0;i<dataSet.getArlData().size();i++){
			dataarray[i] = dataSet.getArlData().get(i);
		}
		res.setdata(dataarray);
		res.setSearchText(searchText);
		
		res.setCurrentPage(currentPage);
		res.setPageSize(pageSize);
		
		
		return res;
	}
}