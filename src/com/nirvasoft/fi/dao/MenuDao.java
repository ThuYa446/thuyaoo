package com.nirvasoft.fi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nirvasoft.database.DBField;
import com.nirvasoft.database.DBMgr;
import com.nirvasoft.database.DBRecord;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.MrBean;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.users.data.MenuData;
import com.nirvasoft.fi.users.data.MenuDataset;
import com.nirvasoft.fi.users.data.MenuRole;
import com.nirvasoft.fi.users.data.MenuViewData;
import com.nirvasoft.fi.users.data.MenuViewDataset;

public class MenuDao {
	public static DBRecord define() {
		DBRecord ret = new DBRecord();
		ret.setTableName("UVM022_A");
		ret.setFields(new ArrayList<DBField>());
		ret.getFields().add(new DBField("syskey", (byte) 2));
		ret.getFields().add(new DBField("CreatedDate", (byte) 5));
		ret.getFields().add(new DBField("ModifiedDate", (byte) 5));
		ret.getFields().add(new DBField("UserId", (byte) 5));
		ret.getFields().add(new DBField("UserName", (byte) 5));
		ret.getFields().add(new DBField("RecordStatus", (byte) 1));
		ret.getFields().add(new DBField("SyncStatus", (byte) 1));
		ret.getFields().add(new DBField("SyncBatch", (byte) 2));
		ret.getFields().add(new DBField("usersyskey", (byte) 2));
		ret.getFields().add(new DBField("t1", (byte) 5));
		ret.getFields().add(new DBField("t2", (byte) 5));
		ret.getFields().add(new DBField("t3", (byte) 5));
		ret.getFields().add(new DBField("t4", (byte) 5));
		ret.getFields().add(new DBField("t5", (byte) 5));
		ret.getFields().add(new DBField("t6", (byte) 5));
		ret.getFields().add(new DBField("n1", (byte) 1));
		ret.getFields().add(new DBField("n2", (byte) 2));
		ret.getFields().add(new DBField("n3", (byte) 2));
		ret.getFields().add(new DBField("n4", (byte) 1));
		ret.getFields().add(new DBField("n5", (byte) 1));
		ret.getFields().add(new DBField("n6", (byte) 2));
		ret.getFields().add(new DBField("n7", (byte) 1));
		
		
		return ret;
	}
	public static MenuData getDBRecord(DBRecord adbr) {
		MenuData ret = new MenuData();
		ret.setSyskey(adbr.getLong("syskey"));
		ret.setCreatedDate(adbr.getString("CreatedDate"));
		ret.setModifiedDate(adbr.getString("ModifiedDate"));
		ret.setUserId(adbr.getString("UserId"));
		ret.setUserName(adbr.getString("UserName"));
		ret.setRecordStatus(adbr.getInt("RecordStatus"));
		ret.setSyncStatus(adbr.getInt("SyncStatus"));
		ret.setSyncBatch(adbr.getLong("SyncBatch"));
		
		ret.setUsersyskey(adbr.getLong("usersysKey"));
		ret.setT1(adbr.getString("t1"));
		ret.setT2(adbr.getString("t2"));
		ret.setT3(adbr.getString("t3"));
		ret.setT4(adbr.getString("t4"));
		ret.setT5(adbr.getString("t5"));
		ret.setT6(adbr.getString("t6"));
		ret.setN1(adbr.getInt("n1"));
		ret.setN2(adbr.getLong("n2"));
		ret.setN3(adbr.getLong("n3"));
		ret.setN4(adbr.getInt("n4"));
		ret.setN5(adbr.getInt("n5"));
		ret.setN6(adbr.getLong("n6"));
		ret.setN7(adbr.getInt("n7"));		
		return ret;
	}

	public static DBRecord setDBRecord(MenuData data) {
		DBRecord ret = define();
		ret.setValue("syskey", data.getSyskey());
		ret.setValue("CreatedDate", data.getCreatedDate());
		ret.setValue("ModifiedDate", data.getModifiedDate());
		ret.setValue("UserId", data.getUserId());
		ret.setValue("UserName", data.getUserName());
		ret.setValue("RecordStatus", data.getRecordStatus());
		ret.setValue("SyncStatus", data.getSyncStatus());
		ret.setValue("SyncBatch", data.getSyncBatch());
		
		ret.setValue("usersysKey", data.getUsersyskey());
		ret.setValue("t1", data.getT1());
		ret.setValue("t2", data.getT2());
		ret.setValue("t3", data.getT3());
		ret.setValue("t4", data.getT4());
		ret.setValue("t5", data.getT5());
		ret.setValue("t6", data.getT6());
		ret.setValue("n1", data.getN1());
		ret.setValue("n2", data.getN2());
		ret.setValue("n3", data.getN3());
		ret.setValue("n4", data.getN4());
		ret.setValue("n5", data.getN5());
		ret.setValue("n6", data.getN6());
		ret.setValue("n7", data.getN7());
		
		return ret;
	}
	
	
	public static DBRecord define23() {
		DBRecord ret = new DBRecord();
		ret.setTableName("UVM023_A");
		ret.setFields(new ArrayList<DBField>());
		ret.getFields().add(new DBField("syskey", (byte) 2));
		ret.getFields().add(new DBField("autokey", (byte) 2));
		ret.getFields().add(new DBField("CreatedDate", (byte) 5));
		ret.getFields().add(new DBField("ModifiedDate", (byte) 5));
		ret.getFields().add(new DBField("UserId", (byte) 5));
		ret.getFields().add(new DBField("UserName", (byte) 5));
		ret.getFields().add(new DBField("RecordStatus", (byte) 1));
		ret.getFields().add(new DBField("SyncStatus", (byte) 1));
		ret.getFields().add(new DBField("SyncBatch", (byte) 2));
		ret.getFields().add(new DBField("t1", (byte) 5));
		ret.getFields().add(new DBField("t2", (byte) 5));
		ret.getFields().add(new DBField("t3", (byte) 5));
		ret.getFields().add(new DBField("n1", (byte) 2));
		ret.getFields().add(new DBField("n2", (byte) 2));
		ret.getFields().add(new DBField("n3", (byte) 2));
		ret.getFields().add(new DBField("n4", (byte) 1));
		ret.getFields().add(new DBField("n5", (byte) 1));
		ret.getFields().add(new DBField("n6", (byte) 1));
		ret.getFields().add(new DBField("usersyskey", (byte) 2));
		
		
		return ret;
	}
	
	public static MenuRole getDBRecord23(DBRecord adbr) {
		MenuRole ret = new MenuRole();
		ret.setSyskey(adbr.getLong("syskey"));
		ret.setCreatedDate(adbr.getString("CreatedDate"));
		ret.setModifiedDate(adbr.getString("ModifiedDate"));
		ret.setUserId(adbr.getString("UserId"));
		ret.setUserName(adbr.getString("UserName"));
		ret.setRecordStatus(adbr.getInt("RecordStatus"));
		ret.setSyncStatus(adbr.getInt("SyncStatus"));
		ret.setSyncBatch(adbr.getLong("SyncBatch"));
		ret.setT1(adbr.getString("t1"));
		ret.setT2(adbr.getString("t2"));
		ret.setT3(adbr.getString("t3"));
		ret.setN1(adbr.getLong("n1"));
		ret.setN2(adbr.getLong("n2"));
		ret.setN3(adbr.getLong("n3"));
		ret.setN4(adbr.getInt("n4"));
		ret.setN5(adbr.getInt("n5"));
		ret.setN6(adbr.getInt("n6"));
		ret.setUsersyskey(adbr.getLong("usersysKey"));
		
		return ret;
	}
	
	/*
	public static DBRecord setDBRecord(MenuRole data) {
		DBRecord ret = define23();
		System.out.println("MenuRole="+data.getSyskey()+"\t"+data.getN1()+"\t"+data.getN2());
		
		ret.setValue("syskey", data.getSyskey());
		ret.setValue("autokey", data.getAutokey());
		ret.setValue("CreatedDate", data.getCreatedDate());
		ret.setValue("ModifiedDate", data.getModifiedDate());
		ret.setValue("UserId", data.getUserId());
		ret.setValue("UserName", data.getUserName());
		ret.setValue("RecordStatus", data.getRecordStatus());
		ret.setValue("SyncStatus", data.getSyncStatus());
		ret.setValue("SyncBatch", data.getSyncBatch());
		ret.setValue("t1", data.getT1());
		ret.setValue("t2", data.getT2());
		ret.setValue("t3", data.getT3());
		ret.setValue("n1", data.getN1());
		ret.setValue("n2", data.getN2());
		ret.setValue("n3", data.getN3());
		ret.setValue("n4", data.getN4());
		ret.setValue("n5", data.getN5());
		ret.setValue("n6", data.getN6());
		ret.setValue("usersysKey", data.getUsersyskey());
		
		return ret;
	}
	*/
	
	
	

	
	
	public static DBRecord define(String tabName) {
		DBRecord ret = new DBRecord();
		ret.setTableName(tabName);
		ret.setFields(new ArrayList<DBField>());
		ret.getFields().add(new DBField("syskey", (byte) 2));
		ret.getFields().add(new DBField("CreatedDate", (byte) 5));
		ret.getFields().add(new DBField("ModifiedDate", (byte) 5));
		ret.getFields().add(new DBField("UserId", (byte) 5));
		ret.getFields().add(new DBField("UserName", (byte) 5));
		ret.getFields().add(new DBField("RecordStatus", (byte) 1));
		ret.getFields().add(new DBField("SyncStatus", (byte) 1));
		ret.getFields().add(new DBField("SyncBatch", (byte) 2));
		ret.getFields().add(new DBField("usersyskey", (byte) 2));
		ret.getFields().add(new DBField("t1", (byte) 5));
		ret.getFields().add(new DBField("t2", (byte) 5));
		ret.getFields().add(new DBField("t3", (byte) 5));
		ret.getFields().add(new DBField("n1", (byte) 2));
		ret.getFields().add(new DBField("n2", (byte) 2));
		ret.getFields().add(new DBField("n3", (byte) 2));
		ret.getFields().add(new DBField("n4", (byte) 1));
		ret.getFields().add(new DBField("n5", (byte) 1));
		ret.getFields().add(new DBField("n6", (byte) 2));
		
		
		return ret;
	}

	public static DBRecord setDBRecord(MenuRole data) {
		DBRecord ret = define("UVM023_A");
		ret.setValue("syskey", data.getSyskey());
		ret.setValue("CreatedDate", data.getCreatedDate());
		ret.setValue("ModifiedDate", data.getModifiedDate());
		ret.setValue("UserId", data.getUserId());
		ret.setValue("UserName", data.getUserName());
		ret.setValue("RecordStatus", data.getRecordStatus());
		ret.setValue("SyncStatus", data.getSyncStatus());
		ret.setValue("SyncBatch", data.getSyncBatch());
		
		ret.setValue("usersysKey", data.getUsersyskey());
		ret.setValue("t1", data.getT1());
		ret.setValue("t2", data.getT2());
		ret.setValue("t3", data.getT3());
		ret.setValue("n1", data.getN1());
		ret.setValue("n2", data.getN2());
		ret.setValue("n3", data.getN3());
		ret.setValue("n4", data.getN4());
		ret.setValue("n5", data.getN5());
		ret.setValue("n6", data.getN6());
		
		return ret;
	}

	public static DBRecord defineView() {
		DBRecord ret = new DBRecord();
		ret.setTableName("View_Menu_A");
		ret.setFields(new ArrayList<DBField>());
		ret.getFields().add(new DBField("syskey", (byte) 2));
		/*ret.getFields().add(new DBField("autokey", (byte) 2));
		ret.getFields().add(new DBField("CreatedDate", (byte) 5));
		ret.getFields().add(new DBField("ModifiedDate", (byte) 5));
		ret.getFields().add(new DBField("UserId", (byte) 5));
		ret.getFields().add(new DBField("UserName", (byte) 5));
		ret.getFields().add(new DBField("RecordStatus", (byte) 1));
		ret.getFields().add(new DBField("SyncStatus", (byte) 1));
		ret.getFields().add(new DBField("SyncBatch", (byte) 2));*/
		ret.getFields().add(new DBField("t1", (byte) 5));
		ret.getFields().add(new DBField("t2", (byte) 5));
		ret.getFields().add(new DBField("t3", (byte) 5));	
		ret.getFields().add(new DBField("t5", (byte) 5));
		ret.getFields().add(new DBField("t6", (byte) 5));
		ret.getFields().add(new DBField("n2", (byte) 2));
		ret.getFields().add(new DBField("ParentMenu", (byte) 5));	
		return ret;
	}
	
	public static MenuViewData getDBViewRecord(DBRecord adbr) {
		MenuViewData ret = new MenuViewData();
		ret.setSyskey(adbr.getLong("syskey"));
		/*ret.setAutokey(adbr.getLong("autokey"));
		ret.setCreatedDate(adbr.getString("CreatedDate"));
		ret.setModifiedDate(adbr.getString("ModifiedDate"));
		ret.setUserId(adbr.getString("UserId"));
		ret.setUserName(adbr.getString("UserName"));
		ret.setRecordStatus(adbr.getInt("RecordStatus"));
		ret.setSyncStatus(adbr.getInt("SyncStatus"));
		ret.setSyncBatch(adbr.getLong("SyncBatch"));		
		ret.setUsersyskey(adbr.getLong("usersysKey"));*/
		ret.setT1(adbr.getString("t1"));
		ret.setT2(adbr.getString("t2"));
		ret.setT5(adbr.getString("t5"));
		ret.setT6(adbr.getString("t6"));
		ret.setN2(adbr.getLong("n2"));
		ret.setParentMenu(adbr.getString("ParentMenu"));

		return ret;
	}
	
	public static MenuData read(long syskey, Connection conn)
			throws SQLException {
		MenuData ret = new MenuData();
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(),
				"WHERE RecordStatus<>4 AND syskey=" + syskey, "", conn);
		if (dbrs.size() > 0)
			ret = getDBRecord(dbrs.get(0));
		return ret;
	}

	
	
	/*public static Result MenuRoleinsert( Connection conn,MenuRole mr)
			throws SQLException {
		Result res=new Result();
		
		System.out.println("Role1 sys="+mr.getN1()+"\t Menu sys="+mr.getN2());
		
		String sql = DBMgr.insertString(define23(), conn);
		PreparedStatement stmt = conn.prepareStatement(sql);
		DBRecord dbr = setDBRecord(mr);
		DBMgr.setValues(stmt, dbr);
		stmt.executeUpdate();
		System.out.println("Role2 sys="+mr.getN1()+"\t Menu sys="+mr.getN2());
		
		/*System.out.println("SHOW I am HERE");
		String sql23= DBMgr.insertString(define23(), conn);
		System.out.println(mr.getSyskey()+"\t"+mr.getN1()+"\t"+mr.getN2());
		System.out.println("TEst="+sql23);
		PreparedStatement stmt = conn.prepareStatement(sql23);
		DBRecord dbr = setDBRecord(mr);
		DBMgr.setValues(stmt, dbr)
		stmt.executeUpdate();
		res.setState(true);
		/*PreparedStatement stmt23 = conn.prepareStatement(sql23);
		DBRecord dbr23 = setDBRecord(mr);
		DBMgr.setValues(stmt23, dbr23);
		stmt23.executeUpdate();
		res.setState(true);			
		res.setMsgDesc("Save Successfully!");
		
		return res;
	}	
*/
	public static boolean isLinkExist(MenuData obj, Connection conn)
			throws SQLException {
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(),
				" WHERE RecordStatus<>4 AND syskey <> " + obj.getSyskey()
						+ " AND T1=\'" + obj.getT1() + "\'", "", conn);
		if (dbrs.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isDescriptionExit(MenuData obj, Connection conn)
			throws SQLException {
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(),
				" WHERE RecordStatus<>4 AND syskey <> " + obj.getSyskey()
						+ " AND T3=\'" + obj.getT3() + "\'", "", conn);
		if (dbrs.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	public static Result insert(MenuData obj, Connection conn)
			throws SQLException {
		Result res = new Result();
		if(!isDescriptionExit(obj,conn))
			{
			String sql = DBMgr.insertString(define(), conn);
			PreparedStatement stmt = conn.prepareStatement(sql);
			DBRecord dbr = setDBRecord(obj);
			DBMgr.setValues(stmt, dbr);
			stmt.executeUpdate();
			
			res.setMsgDesc("Save Successfully!");
			res.setState(true);	
			return res;			
			}
			else{
				res.setMsgDesc("Menu Item is already exit.");
				res.setState(false);
				return res;
			}
	}
	
	public static Result update(MenuData obj, Connection conn)
			throws SQLException {
		Result res = new Result();
		if(!isDescriptionExit(obj,conn))
			{
			String sql = DBMgr.updateString(
					" WHERE RecordStatus<>4 AND Syskey=" + obj.getSyskey(),
					define(), conn);
			PreparedStatement stmt = conn.prepareStatement(sql);
			DBRecord dbr = setDBRecord(obj);
			DBMgr.setValues(stmt, dbr);
			stmt.executeUpdate();
			res.setState(true);
			res.setMsgDesc("Update Successfully!");
			return res;
			}
			else
			{
				res.setMsgDesc("Description is already exist.");
				res.setState(false);
				return res;
			}
		
	}
	
	public static Result insertMenuRole(MenuRole obj, Connection conn)
			throws SQLException {
		Result res=new Result();
		
			String sql = DBMgr.insertString(define("UVM023_A"), conn);
			PreparedStatement stmt = conn.prepareStatement(sql);
			DBRecord dbr = setDBRecord(obj);
			DBMgr.setValues(stmt, dbr);
			stmt.executeUpdate();
			res.setState(true);
			res.setMsgDesc("Save Successfully!");
			return res;
			//System.out.println("Role sys="+obj.getN1()+"\t Menu sys="+obj.getN2());
		}
	

	

	public static Result delete(long syskey,String isParent, Connection conn)
			throws SQLException {
		Result res = new Result();
	
		
		if(!canDelete(syskey, conn))
		{
			 boolean ischild=true;
			if(isParent.equals("true") || isParent=="true")
			{
				
			ischild=canDeleteParent(syskey,conn);
				
			}
			
			if(ischild!=false)	
			{	
				
		
		String sql = "UPDATE UVM022_A SET RecordStatus=4 WHERE Syskey=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, syskey);
		int rs = stmt.executeUpdate();
		if (rs > 0) {
			String sql23 = "UPDATE UVM023_A SET RecordStatus=4 WHERE n2=?";
			PreparedStatement stmt23 = conn.prepareStatement(sql23);
			stmt23.setLong(1, syskey);
			int rss=stmt23.executeUpdate();
				if(rss>0){
			res.setState(true);
			res.setMsgDesc("Delete Successfully!");
				}
				else{
					res.setState(false);
					res.setMsgDesc("Delete Fail");
					return res;	
					
					}
				}
			else{
				res.setState(false);
				res.setMsgDesc("Delete Fail");
				return res;	
			}
		}
		else{
			
			res.setState(false);
			res.setMsgDesc("Cannot Delete");
			return res;	
		}
		}
		else{
			res.setState(false);
			res.setMsgDesc("Cannot Delete");
			return res;	
		}
return res;
	}

	public static MenuDataset getmainmenulist(Connection conn)
			throws SQLException {

		ArrayList<MenuData> ret = new ArrayList<MenuData>();
		String whereClause = " WHERE RecordStatus<>4 AND n2=0";

		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), whereClause,
				" ORDER BY syskey", conn);

		for (int i = 0; i < dbrs.size(); i++) {
			ret.add(getDBRecord(dbrs.get(i)));
		}

		MenuDataset dataSet = new MenuDataset();
		dataSet.setArlData(ret);

		return dataSet;
	}

	
	
	
	public static MenuViewDataset getAllMenuData(String searchText,Connection conn) throws SQLException {

		ArrayList<MenuViewData> ret = new ArrayList<MenuViewData>();
		String whereClause = "";
		if (searchText.equals("")) {
			whereClause = "ORDER BY syskey";
		} else {
			whereClause = "where t1 like '%" + searchText + "%' or syskey like '%" + searchText + "%' or t2 like '%"
					+ searchText + "%' or t5  like '%" + searchText + "%' or t6 like '%" + searchText
					+ "%' ORDER BY syskey";
		}

		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(defineView(), "", whereClause, conn);

		for (int i = 0; i < dbrs.size(); i++) {
			ret.add(getDBViewRecord(dbrs.get(i)));
		}

		MenuViewDataset dataSet = new MenuViewDataset();
		dataSet.setArlData(ret);

		return dataSet;
	}

	
	/*public static MenuViewDataset getParentMenuData(MrBean user, Connection conn)
			throws SQLException {

		ArrayList<MenuViewData> ret = new ArrayList<MenuViewData>();
		String where = "WHERE n2=0 AND  RecordStatus<>4";
		
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(defineView(), where,
				" ORDER BY syskey", conn);

		for (int i = 0; i < dbrs.size(); i++) {
			ret.add(getDBViewRecord(dbrs.get(i)));
		}

		MenuViewDataset dataSet = new MenuViewDataset();
		dataSet.setArlData(ret);

		return dataSet;
	}
	
	
	public static MenuViewDataset getChildMenuData(long key, MrBean user, Connection conn)
			throws SQLException {

		ArrayList<MenuViewData> ret = new ArrayList<MenuViewData>();
		String where = "WHERE  RecordStatus<>4 AND  n2=\'"+key+"\'";
		
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(defineView(), where,
				" ORDER BY syskey", conn);

		for (int i = 0; i < dbrs.size(); i++) {
			ret.add(getDBViewRecord(dbrs.get(i)));
		}

		MenuViewDataset dataSet = new MenuViewDataset();
		dataSet.setArlData(ret);

		return dataSet;
	}*/

	public static boolean canDeleteParent(long key, Connection conn)
			throws SQLException {
		
		ArrayList<Long> dbrs = new ArrayList<Long>();
		String sql = "Select n1 From UVM022_A Where  RecordStatus<>4 AND n2=?";
		
		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setLong(1, key);
		ResultSet result = stat.executeQuery();
		while(result.next())
		{
			dbrs.add(result.getLong("n1"));		
		}		
		if (dbrs.size() > 0) {
			return false;
		} else {
			return true;
		}	
}
	
	public static boolean canDelete(long key, Connection conn)
			throws SQLException {
		
		ArrayList<Long> dbrs = new ArrayList<Long>();
		String sql = "Select n1 From UVM023_A Where  RecordStatus<>4 AND n2=? AND n1<>1";
		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setLong(1, key);
		ResultSet result = stat.executeQuery();
		while(result.next())
		{
			dbrs.add(result.getLong("n1"));		
		}			
		if (dbrs.size() > 0) {
			return true;
		} else {
			return false;
		}	
}
	
	public String getUserName(String userid,Connection con)
	{
		String username = "";
		String sql = "select t2 from UVM012_A where t1=? ";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userid);
			ResultSet res = stmt.executeQuery();
			while(res.next())
			{
				username = res.getString("t2");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}

}