package com.nirvasoft.fi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nirvasoft.database.DBField;
import com.nirvasoft.database.DBMgr;
import com.nirvasoft.database.DBRecord;
import com.nirvasoft.fi.framework.MrBean;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.users.data.MenuRole;
import com.nirvasoft.fi.users.data.RoleData;
import com.nirvasoft.fi.users.data.RoleDataset;



public class RoleDao {
	public static DBRecord define() {
		DBRecord ret = new DBRecord();
		ret.setTableName("UVM009_A");
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
		return ret;
	}

	public static RoleData getDBRecord(DBRecord adbr) {
		RoleData ret = new RoleData();
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
		ret.setN3(adbr.getLong("n3"));
		return ret;
	}

	public static DBRecord setDBRecord(RoleData data) {
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
		ret.setValue("n3", data.getN3());
		return ret;
	}
	
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
		DBRecord ret = define("UVM022_A");
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
	

	public static RoleData read(long syskey, Connection conn)
			throws SQLException {
		RoleData ret = new RoleData();
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(),
				"WHERE RecordStatus<>4 AND Syskey=" + syskey, "", conn);
		if (dbrs.size() > 0)
			ret = getDBRecord(dbrs.get(0));
		return ret;
	}
	
	public static long[] getParentMenukey(long syskey, Connection conn)
			throws SQLException {
			 
		ArrayList<Long> key = new ArrayList<Long>();
		try
		{
			String sql = "SELECT syskey FROM UVM022_A WHERE syskey IN "
					+ "(SELECT n2 FROM UVM023_A  WHERE n1=?) AND n2=0";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setLong(1, syskey);
			ResultSet result = stat.executeQuery();
			while(result.next())
			{
				key.add((long)result.getInt("syskey"));
				
			}			
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		long ret[] = new long[key.size()];
		for(int i=0;i<key.size();i++)
		{
			ret[i] = key.get(i);
		}
		
		return ret;
	}
	
	
	public static long[] getChildMenukey(long syskey, Connection conn)
			throws SQLException {
			 
		ArrayList<Long> key = new ArrayList<Long>();
		try
		{
			String sql = "SELECT syskey FROM UVM022_A WHERE syskey IN "
					+ "(SELECT n2 FROM UVM023_A  WHERE n1=?) AND n2<> 0";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setLong(1, syskey);
			ResultSet result = stat.executeQuery();
			while(result.next())
			{
				key.add((long)result.getInt("syskey"));
				
			}			
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		long ret[] = new long[key.size()];
		for(int i=0;i<key.size();i++)
		{
			ret[i] = key.get(i);
		}
		
		return ret;
	}
	
	

	public static boolean isCodeExist(RoleData obj, Connection conn)
			throws SQLException {
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(),
				" WHERE RecordStatus<>4 AND Syskey <> " + obj.getSyskey()
						+ " AND (t1='" + obj.getT1() + "'"+ " OR t2='" + obj.getT2() + "')", "", conn);
		
		if (dbrs.size() > 0) {
			return true;
		} else {
			return false;
		}	
	}

	public static Result insert(RoleData obj, Connection conn)
			throws SQLException {
		Result res = new Result();
		
		if (!isCodeExist(obj, conn)) {
			String sql = DBMgr.insertString(define(), conn);
			PreparedStatement stmt = conn.prepareStatement(sql);
			DBRecord dbr = setDBRecord(obj);
			DBMgr.setValues(stmt, dbr);
			stmt.executeUpdate();
			res.setState(true);
		}
		return res;
	}

	public static Result update(RoleData obj, Connection conn)
			throws SQLException {
		Result res = new Result();
		if (!isCodeExist(obj, conn)) {
			String sql = DBMgr.updateString(
					" WHERE RecordStatus<>4 AND Syskey=" + obj.getSyskey(),
					define(), conn);
			PreparedStatement stmt = conn.prepareStatement(sql);
			DBRecord dbr = setDBRecord(obj);
			DBMgr.setValues(stmt, dbr);
			stmt.executeUpdate();
			res.setState(true);
		}
		return res;
	}

	public static Result delete(String syskey, Connection conn)
			throws SQLException {
		Result res = new Result();
		String sql = "UPDATE UVM009_A SET RecordStatus=4 WHERE Syskey=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, syskey);
		int rs = stmt.executeUpdate();
		if (rs > 0) {
			res.setState(true);
		}
		return res;
	}
	
	public static RoleDataset getAllRoleData(Connection conn)
			throws SQLException {

		ArrayList<RoleData> ret = new ArrayList<RoleData>();
		String whereClause = " WHERE RecordStatus<>4 ";

		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), whereClause,
				" ORDER BY syskey", conn);

		for (int i = 0; i < dbrs.size(); i++) {
			ret.add(getDBRecord(dbrs.get(i)));
		}

		RoleDataset dataSet = new RoleDataset();
		dataSet.setArlData(ret);

		return dataSet;
	}
	
	public static Result delete(long syskey, Connection conn)
			throws SQLException {
		
		Result res = new Result();
		if (!canDelete(syskey, conn))
		{
			String sql = "UPDATE UVM009_A SET RecordStatus=4 WHERE Syskey=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, syskey);
			int rs = stmt.executeUpdate();
			if (rs > 0) {
				res.setState(true);
			}
		}
		
		return res;
	}
	
	
	public static RoleDataset getRoleData(Connection conn)
			throws SQLException {

		ArrayList<RoleData> ret = new ArrayList<RoleData>();
		String whereClause = " WHERE RecordStatus<>4 ";

		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), whereClause,
				" ORDER BY syskey", conn);

		for (int i = 0; i < dbrs.size(); i++) {
			ret.add(getDBRecord(dbrs.get(i)));
		}

		RoleDataset dataSet = new RoleDataset();
		dataSet.setArlData(ret);

		return dataSet;
	}
	
	public static Result insertRoleMenu(MenuRole obj, Connection conn)
			throws SQLException {
		   Result res = new Result();
		   
		   if (!isRoleMenuExist(obj, conn)) {
			
			String sql = DBMgr.insertString(define("UVM023_A"), conn);
			PreparedStatement stmt = conn.prepareStatement(sql);
			DBRecord dbr = setDBRecord(obj);
			DBMgr.setValues(stmt, dbr);
			stmt.executeUpdate();
			res.setState(true);
		   }
		   
			return res;
		
	}
	
	public static Result updateRoleMenu(RoleData obj, Connection conn)
			throws SQLException {
		Result res = new Result();
		int key = (int)obj.getSyskey();
		String sql = "DELETE FROM UVM023_A WHERE n1=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, key);
		int rs = stmt.executeUpdate();
		if (rs > 0) {
			res.setState(true);
		}
		return res;
		
		
	}
	
	public static Result deleteRoleMenu(long syskey, Connection conn)
			throws SQLException {
		
		Result res = new Result();
		if (!canDelete(syskey, conn))
		{
			
			String sql = "UPDATE UVM023_A SET RecordStatus=4 WHERE n1=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, syskey);
			int rs = stmt.executeUpdate();
			if (rs > 0) {
				res.setState(true);
			}
			return res;
		}
		else
			return res;
		
	}
	
	public static boolean canDelete(long key, Connection conn)
			throws SQLException {
		
		ArrayList<Long> dbrs = new ArrayList<Long>();
		String sql = "Select n1 From JUN002_A Where n2=? And RecordStatus<>4";
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
	
	
	public static boolean isRoleMenuExist(MenuRole obj, Connection conn)
			throws SQLException {
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define("UVM023_A"),
				" WHERE RecordStatus<>4 AND Syskey <> " + obj.getSyskey()
						+ " AND (n1="+ obj.getN1() + " AND n2=" + obj.getN2() + ")", "", conn);
		
		if (dbrs.size() > 0) {
			return true;
		} else {
			return false;
		}	
	}
	
	public static boolean checkParentContain(long parent[], long child, Connection conn)
			throws SQLException {
		long l = 0;
		String sql = "Select n2 from View_Menu_A where syskey=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, child);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			l = rs.getLong("n2");
		}
		
		for(int i=0; i<parent.length; i++){
		
			if(l==parent[i]){
				return true;
			}
		}
		return false;
			
	}
	public static RoleDataset getAllRoleListData(String searchText,int pageSize ,int currentPage , Connection conn)
			throws SQLException {

		ArrayList<RoleData> ret = new ArrayList<RoleData>();
		String whereClause = "";
		if(searchText.equals(""))
		{
			whereClause = " WHERE RecordStatus<>4 ";

		}
		else
		{
			
			whereClause = " WHERE RecordStatus<>4  and (t1 like '%"+searchText+"%' or t2 like '%"+searchText+"%')";
		}
		
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), whereClause,
				" ORDER BY T1, T2 ", conn);

		for (int i = 0; i < dbrs.size(); i++) {
			ret.add(getDBRecord(dbrs.get(i)));
		}

		RoleDataset dataSet = new RoleDataset();
		dataSet.setArlData(ret);

		return dataSet;
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
