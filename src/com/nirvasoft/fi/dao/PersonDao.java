package com.nirvasoft.fi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nirvasoft.database.DBField;
import com.nirvasoft.database.DBMgr;
import com.nirvasoft.database.DBRecord;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.users.data.PersonData;
import com.nirvasoft.fi.users.data.UserData;
import com.nirvasoft.fi.users.data.UserDataset;
import com.nirvasoft.fi.users.data.UserRole;
import com.nirvasoft.fi.util.GeneralUtil;
import com.nirvasoft.fi.util.ServerUtil;

public class PersonDao {
	public static DBRecord define() {
		DBRecord ret = new DBRecord();
		ret.setTableName("UVM012_A");
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
		ret.getFields().add(new DBField("t7", (byte) 5));
		ret.getFields().add(new DBField("t8", (byte) 5));
		ret.getFields().add(new DBField("t9", (byte) 5));
		ret.getFields().add(new DBField("t10", (byte) 5));
		ret.getFields().add(new DBField("t11", (byte) 5));
		ret.getFields().add(new DBField("t12", (byte) 5));
		ret.getFields().add(new DBField("t13", (byte) 5));
		ret.getFields().add(new DBField("t14", (byte) 5));
		ret.getFields().add(new DBField("t15", (byte) 5));
		ret.getFields().add(new DBField("t16", (byte) 5));
		ret.getFields().add(new DBField("t17", (byte) 5));
		ret.getFields().add(new DBField("t18", (byte) 5));
		ret.getFields().add(new DBField("t19", (byte) 5));
		ret.getFields().add(new DBField("t20", (byte) 5));
		ret.getFields().add(new DBField("t21", (byte) 5));
		ret.getFields().add(new DBField("t22", (byte) 5));
		ret.getFields().add(new DBField("t23", (byte) 5));
		ret.getFields().add(new DBField("t24", (byte) 5));
		ret.getFields().add(new DBField("n1", (byte) 2));
		ret.getFields().add(new DBField("n2", (byte) 2));
		ret.getFields().add(new DBField("n3", (byte) 2));
		ret.getFields().add(new DBField("n4", (byte) 2));
		ret.getFields().add(new DBField("n5", (byte) 2));
		ret.getFields().add(new DBField("n6", (byte) 2));
		ret.getFields().add(new DBField("n7", (byte) 1));
		ret.getFields().add(new DBField("n8", (byte) 1));
		ret.getFields().add(new DBField("n9", (byte) 2));
		ret.getFields().add(new DBField("n10", (byte) 1));

		return ret;
	}

	public static PersonData getDBRecord(DBRecord adbr) {
		PersonData ret = new PersonData();
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
		ret.setT7(adbr.getString("t7"));
		ret.setT1(adbr.getString("t8"));
		ret.setT2(adbr.getString("t9"));
		ret.setT3(adbr.getString("t10"));
		ret.setT4(adbr.getString("t11"));
		ret.setT5(adbr.getString("t12"));
		ret.setT6(adbr.getString("t13"));
		ret.setT7(adbr.getString("t14"));
		ret.setT3(adbr.getString("t15"));
		ret.setT4(adbr.getString("t16"));
		ret.setT5(adbr.getString("t17"));
		ret.setT6(adbr.getString("t18"));
		ret.setT7(adbr.getString("t19"));
		ret.setT3(adbr.getString("t20"));
		ret.setT4(adbr.getString("t21"));
		ret.setT5(adbr.getString("t22"));
		ret.setT6(adbr.getString("t23"));
		ret.setT7(adbr.getString("t24"));
		ret.setN1(adbr.getLong("n1"));
		ret.setN2(adbr.getLong("n2"));
		ret.setN3(adbr.getLong("n3"));
		ret.setN4(adbr.getLong("n4"));
		ret.setN5(adbr.getLong("n5"));
		ret.setN6(adbr.getLong("n6"));
		ret.setN7(adbr.getInt("n7"));
		ret.setN8(adbr.getInt("n8"));
		ret.setN9(adbr.getLong("n9"));
		ret.setN10(adbr.getInt("n10"));

		return ret;
	}

	public static DBRecord setDBRecord(PersonData data) {
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
		ret.setValue("t7", data.getT7());
		ret.setValue("t8", data.getT8());
		ret.setValue("t9", data.getT9());
		ret.setValue("t10", data.getT10());
		ret.setValue("t11", data.getT11());
		ret.setValue("t12", data.getT12());
		ret.setValue("t13", data.getT13());
		ret.setValue("t14", data.getT14());
		ret.setValue("t15", data.getT15());
		ret.setValue("t16", data.getT16());
		ret.setValue("t17", data.getT17());
		ret.setValue("t18", data.getT18());
		ret.setValue("t19", data.getT19());
		ret.setValue("t20", data.getT20());
		ret.setValue("t21", data.getT21());
		ret.setValue("t22", data.getT22());
		ret.setValue("t23", data.getT23());
		ret.setValue("t24", data.getT24());
		ret.setValue("n1", data.getN1());
		ret.setValue("n2", data.getN2());
		ret.setValue("n3", data.getN3());
		ret.setValue("n4", data.getN4());
		ret.setValue("n5", data.getN5());
		ret.setValue("n6", data.getN6());
		ret.setValue("n7", data.getN7());
		ret.setValue("n8", data.getN8());
		ret.setValue("n9", data.getN9());
		ret.setValue("n10", data.getN10());
		return ret;
	}

	public static PersonData read(long syskey, Connection conn) throws SQLException {
		PersonData ret = new PersonData();
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), "where RecordStatus<>4 AND syskey=" + syskey, "", conn);
		if (dbrs.size() > 0)
			ret = getDBRecord(dbrs.get(0));
		return ret;
	}

	// MMPPM
		public static boolean isCodeExist(PersonData obj, Connection conn) throws SQLException {
			ArrayList<PersonData> dataList = new ArrayList<PersonData>();
			//String sql = "Select * from UVM012_A Where recordStatus <> 4 AND (T1='" + obj.getT1() + "')";
			String sql = "Select * from UVM012_A Where recordStatus <> 4 and T1 = ? and syskey <> ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1,obj.getT1());
			psmt.setLong(2, obj.getSyskey());
			ResultSet res = psmt.executeQuery();
			while (res.next()) {
				PersonData data = new PersonData();
				data.setT1(res.getString("T1"));
				dataList.add(data);
			}
			if (dataList.size() > 0) {
				return true;
			} else {
				return false;
			}

		}
		
		
	public static Result insert(PersonData obj, Connection conn) throws SQLException {
		Result res = new Result();
		if (!isCodeExist(obj, conn)) {
					 String sql = DBMgr.insertString(define(), conn);
						PreparedStatement stmt = conn.prepareStatement(sql);
						DBRecord dbr = setDBRecord(obj);
						DBMgr.setValues(stmt, dbr);
						int rs = stmt.executeUpdate();
						if (rs > 0)
							res.setState(true);
						else {
							res.setState(false);
							res.setMsgDesc("Save Failed");
						}

						res.getStringResult().add(obj.getT1());
				 
		} else {
			res.setState(false);
			res.setMsgDesc("User ID already exist");
		}

		return res;
	}

	public static Result update(PersonData obj, Connection conn) throws SQLException {
		Result res = new Result();
		if (isCodeExist(obj, conn)) {
			String sql = DBMgr.updateString(" WHERE RecordStatus<>4 AND Syskey=" + obj.getSyskey(), define(), conn);
			PreparedStatement stmt = conn.prepareStatement(sql);
			DBRecord dbr = setDBRecord(obj);
			DBMgr.setValues(stmt, dbr);
			int rs = stmt.executeUpdate();
			if (rs > 0)
				res.setState(true);
			else {
				res.setState(false);
				res.setMsgDesc("Update Failed");
			}
			res.getStringResult().add(obj.getT1());
		}
		return res;
	}
	public static Result updatePersonData(UserData obj, Connection conn) throws SQLException {
		Result ret = new Result();
		UserDao Dao = new UserDao();
		ret = Dao.canUpdate("update", obj.getT1(), conn);
		ret.setMsgCode("0000");
		if(ret.getMsgCode().equals("0000")){
			//String sql = "UPDATE UVM012_A SET t1='"+ obj.getT1() +"' ,t2='"+ obj.getName() +"'  ,t3='"+ obj.getState() +"'  ,t4='"+ obj.getCity() +"'  ,t5='"+ obj.getUserId() +"' , modifieddate = '" + GeneralUtil.datetoString() + "'  Where RecordStatus<>4 AND Syskey=" + obj.getPerson().getSyskey();
			String sql = "UPDATE UVM012_A SET t1=? ,t2=?  ,t3=?  ,t4=?  ,t5=? , modifieddate =? Where RecordStatus<>4 AND Syskey=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			int j = 1;
			stmt.setString(j++,obj.getT1());
			stmt.setString(j++,obj.getName());
			stmt.setString(j++,obj.getState());
			stmt.setString(j++,obj.getCity());
			stmt.setString(j++,obj.getUserId());
			stmt.setString(j++,GeneralUtil.datetoString());
			//stmt.setString(j++,obj.getCustomerid());
			stmt.setLong(j++, obj.getPerson().getSyskey());
			
			int rs = stmt.executeUpdate();
			if (rs > 0) {
				ret.setState(true);
				ret.setMsgCode("0000");
				ret.setMsgDesc("Updated Successfully");
			}else{
				ret.setState(false);
				ret.setMsgCode("0014");
				ret.setMsgDesc("Updated Failed");
			}
		}else{
			ret.setState(false);
		}
		
		
		
		return ret;
		}
/*	public static Result updatePersonData(UserData obj, Connection conn) throws SQLException {
	Result ret = new Result();
	
	//String sql = "UPDATE UVM012_A SET t1='"+ obj.getT1() +"' ,t2='"+ obj.getName() +"'  ,t3='"+ obj.getState() +"'  ,t4='"+ obj.getCity() +"'  ,t5='"+ obj.getUserId() +"' , modifieddate = '" + GeneralUtil.datetoString() + "'  Where RecordStatus<>4 AND Syskey=" + obj.getPerson().getSyskey();
	String sql = "UPDATE UVM012_A SET t1=? ,t2=?  ,t3=?  ,t4=?  ,t5=? , modifieddate =?  Where RecordStatus<>4 AND Syskey=?";
	PreparedStatement stmt = conn.prepareStatement(sql);
	int j = 1;
	stmt.setString(j++,obj.getT1());
	stmt.setString(j++,obj.getName());
	stmt.setString(j++,obj.getState());
	stmt.setString(j++,obj.getCity());
	stmt.setString(j++,obj.getUserId());
	stmt.setString(j++,GeneralUtil.datetoString());
	stmt.setLong(j++, obj.getPerson().getSyskey());
	
	int rs = stmt.executeUpdate();
	if (rs > 0) {
		ret.setState(true);
		ret.setMsgCode("0000");
		ret.setMsgDesc("Updated Successfully");
	}else{
		ret.setState(false);
		ret.setMsgCode("0014");
		ret.setMsgDesc("Updated Failed");
	}
	
	return ret;
	}*/

	public static Result deletePerson(long syskey,String modifieduserId, Connection conn) throws SQLException {
		Result res = new Result();
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String sql = "UPDATE UVM012_A SET RecordStatus=4,modifieddate=?,t5=? WHERE Syskey IN(SELECT N4 FROM UVM005_A WHERE Syskey=?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		int i = 1;
		stmt.setString(i++,date);
		stmt.setString(i++,modifieduserId);
		stmt.setLong(i++, syskey);
		int rs = stmt.executeUpdate();
		if (rs > 0) {
			res.setState(true);
		}
		return res;
	}

	public static String getUserName(long usys, Connection con) throws SQLException {
		String sql = " SELECT T2  FROM UVM012_A WHERE RecordStatus<>4 AND syskey IN(SELECT N4 FROM UVM005_A WHERE syskey=?)";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setLong(1, usys);
		ResultSet result = stat.executeQuery();
		String u = "";
		while (result.next()) {
			u = result.getString("t2");
		}
		return u;

	}
	

	public PersonData getCreateUserData(long usys, Connection con) throws SQLException {
		PersonData p_data = new PersonData();
		String sql = " SELECT T2,T3,T4,T7  FROM UVM012_A WHERE RecordStatus<>4 AND syskey IN(SELECT N4 FROM UVM005_A WHERE syskey=?)";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setLong(1, usys);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			p_data.setT3(result.getString("t3"));
			p_data.setT4(result.getString("t4"));
			p_data.setT7(result.getString("t7"));
		}
		return p_data;

	}
}