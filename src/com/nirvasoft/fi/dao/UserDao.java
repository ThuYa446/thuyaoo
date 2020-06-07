package com.nirvasoft.fi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import com.nirvasoft.database.DBField;
import com.nirvasoft.database.DBMgr;
import com.nirvasoft.database.DBRecord;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.users.data.PersonData;
import com.nirvasoft.fi.users.data.UCJunction;
import com.nirvasoft.fi.users.data.UserData;
import com.nirvasoft.fi.users.data.UserRole;
import com.nirvasoft.fi.users.data.UserViewData;
import com.nirvasoft.fi.users.data.UserViewDataset;
import com.nirvasoft.fi.util.GeneralUtil;
import com.nirvasoft.fi.util.ServerUtil;

public class UserDao {
	public static DBRecord define() {
		DBRecord ret = new DBRecord();
		ret.setTableName("UVM005_A");
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
		ret.getFields().add(new DBField("n1", (byte) 2));
		ret.getFields().add(new DBField("n2", (byte) 1));
		ret.getFields().add(new DBField("n3", (byte) 2));
		ret.getFields().add(new DBField("n4", (byte) 2));
		ret.getFields().add(new DBField("n5", (byte) 1));
		ret.getFields().add(new DBField("n6", (byte) 1));
		ret.getFields().add(new DBField("n7", (byte) 1));
		ret.getFields().add(new DBField("n8", (byte) 2));

		return ret;
	}

	public static DBRecord define(String tabName) {
		DBRecord ret = new DBRecord();
		ret.setTableName(tabName);
		ret.setFields(new ArrayList<DBField>());
		ret.getFields().add(new DBField("RecordStatus", (byte) 1));
		ret.getFields().add(new DBField("SyncStatus", (byte) 1));
		ret.getFields().add(new DBField("SyncBatch", (byte) 2));
		ret.getFields().add(new DBField("usersyskey", (byte) 2));
		ret.getFields().add(new DBField("n1", (byte) 2));
		ret.getFields().add(new DBField("n2", (byte) 2));
		ret.getFields().add(new DBField("n3", (byte) 1));
		ret.getFields().add(new DBField("n4", (byte) 1));
		ret.getFields().add(new DBField("n5", (byte) 2));

		return ret;
	}

	///////////
	public static DBRecord defineView() {
		DBRecord ret = new DBRecord();
		ret.setTableName("V_U001_A");
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
		// ret.getFields().add(new DBField("usersyskey", (byte) 2));
		ret.getFields().add(new DBField("t1", (byte) 5));
		ret.getFields().add(new DBField("t2", (byte) 5));
		ret.getFields().add(new DBField("t3", (byte) 5));
		ret.getFields().add(new DBField("t7", (byte) 5));
		// ret.getFields().add(new DBField("t2", (byte) 5));
		ret.getFields().add(new DBField("UN", (byte) 5));
		ret.getFields().add(new DBField("lock", (byte) 1));
		ret.getFields().add(new DBField("role", (byte) 1));
		ret.getFields().add(new DBField("customerID", (byte) 5));
		return ret;
	}

	public static UserViewData getDBViewRecord(DBRecord adbr) {
		UserViewData ret = new UserViewData();
		ret.setSyskey(adbr.getLong("syskey"));
		ret.setAutokey(adbr.getLong("autokey"));
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
		ret.setT7(adbr.getString("t7"));
		ret.setUsername(adbr.getString("UN"));
		ret.setN7(adbr.getInt("lock"));
		ret.setN2(adbr.getInt("role"));
		ret.setCustomerid(adbr.getString("customerID"));
		return ret;
	}

	/////////////

	public static UserData getDBRecord(DBRecord adbr) {
		UserData ret = new UserData();
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
		ret.setT2(ServerUtil.decryptPIN(adbr.getString("t2")));
		ret.setT3(adbr.getString("t3"));
		ret.setT4(adbr.getString("t4"));
		ret.setT5(adbr.getString("t5"));
		ret.setT6(adbr.getString("t6"));
		ret.setT7(adbr.getString("t7"));
		ret.setN1(adbr.getLong("n1"));
		ret.setN2(adbr.getInt("n2"));
		ret.setN3(adbr.getLong("n3"));
		ret.setN4(adbr.getLong("n4"));
		ret.setN5(adbr.getInt("n5"));
		ret.setN6(adbr.getInt("n6"));
		ret.setN7(adbr.getInt("n7"));
		ret.setN8(adbr.getLong("n8"));

		return ret;
	}

	public static DBRecord setDBRecord(UserData data) {
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
		ret.setValue("t3", data.getT3());
		ret.setValue("t5", data.getT5());
		ret.setValue("t6", data.getT6());
		ret.setValue("t7", data.getT7());
		ret.setValue("n1", data.getN1());
		ret.setValue("n2", data.getN2());
		ret.setValue("n3", data.getN3());
		ret.setValue("n4", data.getN4());
		ret.setValue("n5", data.getN5());
		ret.setValue("n6", data.getN6());
		ret.setValue("n7", data.getN7());
		ret.setValue("n8", data.getN8());
		return ret;
	}

	public static DBRecord setDBRecord(UserRole data) {
		DBRecord ret = define("JUN002_A");
		ret.setValue("RecordStatus", data.getRecordStatus());
		ret.setValue("SyncStatus", data.getSyncStatus());
		ret.setValue("SyncBatch", data.getSyncBatch());
		ret.setValue("usersysKey", data.getUsersyskey());
		ret.setValue("n1", data.getN1());
		ret.setValue("n2", data.getN2());
		ret.setValue("n3", data.getN3());
		ret.setValue("n4", data.getN4());
		ret.setValue("n5", data.getN5());
		return ret;
	}

	public static UserRole getDBRecords(DBRecord adbr) {
		UserRole ret = new UserRole();
		ret.setRecordStatus(adbr.getInt("RecordStatus"));
		ret.setSyncStatus(adbr.getInt("SyncStatus"));
		ret.setSyncBatch(adbr.getLong("SyncBatch"));

		ret.setUsersyskey(adbr.getLong("usersysKey"));

		ret.setN1(adbr.getLong("n1"));
		ret.setN2(adbr.getLong("n2"));
		ret.setN3(adbr.getInt("n3"));
		ret.setN4(adbr.getInt("n4"));
		ret.setN5(adbr.getInt("n5"));

		return ret;
	}

	public static UserData readBranchUserDataBySyskey(long syskey, Connection conn) throws SQLException {

		UserData ret = new UserData();
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), "WHERE RecordStatus<>4 AND syskey=" + syskey, "", conn);
		if (dbrs.size() > 0)
			ret = getDBRecord(dbrs.get(0));

		return ret;

	}

	// MMPPM
	public static boolean isCodeExist(UserData obj, Connection conn) throws SQLException {

		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(),
				" WHERE RecordStatus<>4 AND syskey = " + obj.getSyskey(), "", conn);
		if (dbrs.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	public static Result insert(boolean isUserProfile, UserData obj, Connection conn) throws SQLException {
		Result res = new Result();
		if (!isCodeExist(obj, conn)) {
			  if(!isPhDuplicate(obj,conn)){
				  if(!isEmailDuplicate(obj,conn)){

				try {
					String sql = DBMgr.insertString(define(), conn);
					PreparedStatement stmt = conn.prepareStatement(sql);
					DBRecord dbr = setDBRecord(obj);
					DBMgr.setValues(stmt, dbr);
					int rs = stmt.executeUpdate();

					if (rs > 0) {

						if (!isUserProfile) {
							UserRole jun = new UserRole();
							for (long l : obj.getRolesyskey()) {

								if (l != 0) {

									jun.setRecordStatus(obj.getRecordStatus());
									jun.setSyncBatch(obj.getSyncBatch());
									jun.setSyncStatus(obj.getSyncStatus());
									jun.setUsersyskey(obj.getSyskey());
									jun.setN1(obj.getSyskey());
									jun.setN2(l);
									res = insertUserRole(jun, conn);

								}
							}

							if (res.isState()) {

								res.setState(true);
								res.setMsgDesc("Saved Successfully");

							} else
								res.setMsgDesc("Cannot Save");
						} else {
							String query = "Update UVM012_A set N10 = ? Where recordStatus <> 4 and t1=?";
							PreparedStatement stmt1 = conn.prepareStatement(query);
							int i = 1;
							stmt1.setInt(i++, 1);
							stmt1.setString(i++, obj.getT1());
							int rst = stmt1.executeUpdate();
							if (rst > 0) {

								UserDao dao = new UserDao();
								res = dao.insertAgent(obj, conn);

								/*
								 * if (obj.getN3() == 1) { UserDao dao = new
								 * UserDao(); res = dao.insertAgent(obj, conn); }
								 */

								if (res.isState()) {
									res.setState(true);
									res.setMsgDesc("Saved Successfully");
									res.setLoginID(obj.getT1());
									res.setPhNo(obj.getT4());
								}

								/*
								 * res.setState(true); res.setMsgDesc(
								 * "Saved Successfully");
								 * res.setLoginID(obj.getT1());
								 * res.setPhNo(obj.getT4());
								 */
							} else {
								res.setState(false);
								res.setMsgDesc("Save Fail");
							}

						}

					} else
						res.setMsgDesc("Cannot Save");

				}

				catch (Exception e) {
					res.setMsgDesc("Cannot Save");
					e.printStackTrace();
				}

					
				  }else{
					  res.setMsgDesc("Email already exist");
				  }
			  }else{
				  res.setMsgDesc("Phone No. already exist"); 
			  }
		} else
			res.setMsgDesc("Data already exist");

		return res;
	}
	
	public static boolean isEmailDuplicate(UserData obj, Connection conn) throws SQLException {
		ArrayList<PersonData> dataList = new ArrayList<PersonData>();
		String sql = "Select * from UVM005_A Where recordStatus <> 4 and T3 = ? and syskey <> ?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1,obj.getT3());
		psmt.setLong(2, obj.getSyskey());
		ResultSet res = psmt.executeQuery();
		while (res.next()) {
			PersonData data = new PersonData();
			data.setT1(res.getString("T3"));
			dataList.add(data);
		}
		if (dataList.size() > 0) {
			return true;
		} else {
			return false;
		}

	}
	
	public static boolean isPhDuplicate(UserData obj, Connection conn) throws SQLException {
		ArrayList<PersonData> dataList = new ArrayList<PersonData>();
		String sql = "Select * from UVM005_A Where recordStatus <> 4 and T4 = ? and syskey <> ?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1,obj.getT4());
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


	public static Result insertUserRole(UserRole obj, Connection conn) throws SQLException {
		Result res = new Result();
		String sql = DBMgr.insertString(define("JUN002_A"), conn);
		PreparedStatement stmt = conn.prepareStatement(sql);
		DBRecord dbr = setDBRecord(obj);
		DBMgr.setValues(stmt, dbr);
		int rs = stmt.executeUpdate();

		if (rs > 0) {
			res.setState(true);
		}

		return res;
	}

	public Result insertAgent(UserData data, Connection conn) throws SQLException {
		Result res = new Result();

		String l_query = "delete from Agent where LogInId = ?";
		PreparedStatement st = conn.prepareStatement(l_query);
		st.setString(1, data.getT1());
		int count = st.executeUpdate();

		if (data.getN3() == 1) {
			String query = "INSERT INTO Agent (LogInID ,AgentAccNumber ,Name ,RecordStatus , ParentID ,n1 )"
					+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(query);

			int i = 1;
			psmt.setString(i++, data.getT1());

			psmt.setString(i++, data.getAccountNumber());
			psmt.setString(i++, data.getName());
			psmt.setInt(i++, 1);
			if (data.getParentID().equals("0") || data.getParentID().equals("") || data.getParentID().equals("null")) {
				psmt.setString(i++, data.getT1()); // data.getParentID()
				psmt.setInt(i++, 1);
			} else {
				psmt.setString(i++, data.getParentID());
				psmt.setInt(i++, 0);
			}
			if (psmt.executeUpdate() > 0) {
				res.setState(true);
			} else {
				res.setState(false);
			}
		} else {
			res.setState(true);
		}

		return res;
	}

	public static Result update(boolean isUserProfile, UserData obj, Connection conn) throws SQLException {
		Result res = new Result();
		String userPwd = "";

		if (ServerUtil.decryptPIN(obj.getT2()).equals("")) // for immediately
															// update data after
															// saving
		{
			String query = "Select t2 from UVM005_A where recordstatus <> 4 and syskey = ?";
			PreparedStatement stmt1 = conn.prepareStatement(query);
			stmt1.setLong(1, obj.getSyskey());
			ResultSet result = stmt1.executeQuery();
			boolean flag = false;
			while (result.next()) {
				userPwd = result.getString("t2");
				obj.setT2(userPwd);
				flag = true;
			}
			if (!flag) {
				res.setState(false);
				res.setMsgDesc("Cannot Update");
			}
		}

		String sql = "UPDATE UVM005_A SET n2=? , n3 = ?, t1=? ,t2=? ,t3=?,t4=? ,t5=?,"
				+ "t6=? ,t7=? , modifieddate = ?  Where RecordStatus<>4 AND Syskey=?";
		/*String sql = "UPDATE UVM005_A SET n2=" + obj.getN2() + " , n3 = " + obj.getN3() + ", t2='" + obj.getT2() + "' ,t3='" + obj.getT3() + "' ,t4='" + obj.getT4() + "' ,t5='" + obj.getT5()
		+ "',t6='" + obj.getT6() + "' ,t7='" + obj.getT7() + "' , modifieddate = '" + GeneralUtil.datetoString()
		+ "' Where RecordStatus<>4 AND Syskey=" + obj.getSyskey();*/
		PreparedStatement stmt = conn.prepareStatement(sql);
		int i = 1;
		stmt.setInt(i++, obj.getN2());
		stmt.setLong(i++, obj.getN3());
		stmt.setString(i++, obj.getT1());
		stmt.setString(i++, obj.getT2());
		stmt.setString(i++, obj.getT3());
		stmt.setString(i++, obj.getT4());
		stmt.setString(i++, obj.getT5());
		stmt.setString(i++, obj.getT6());
		stmt.setString(i++, obj.getT7());
		stmt.setString(i++, GeneralUtil.datetoString());
		stmt.setLong(i++, obj.getSyskey());
		int rs = stmt.executeUpdate();
		if (rs > 0) {
			if (!isUserProfile) {
				UserRole jun = new UserRole();
				for (long l : obj.getRolesyskey()) {
					System.out.println("Rolesyskye :" + l);
					jun.setN1(obj.getSyskey());
					jun.setN2(l);
					int rst;
					sql = "DELETE FROM JUN002_A WHERE n1=?";
					stmt = conn.prepareStatement(sql);
					stmt.setLong(1, jun.getN1());
					rst = stmt.executeUpdate();
				}

				for (long l : obj.getRolesyskey()) {
					if (l != 0) {
						jun.setRecordStatus(obj.getRecordStatus());
						jun.setSyncBatch(obj.getSyncBatch());
						jun.setSyncStatus(obj.getSyncStatus());
						jun.setUsersyskey(obj.getUsersyskey());
						jun.setN1(obj.getSyskey());
						jun.setN2(l);
						res = insertUserRole(jun, conn);
					}
				}
				if (res.isState()) {
					res.setState(true);
					res.setMsgDesc("Updated Successfully");

				} else
					res.setMsgDesc("Cannot Update");
			} else {

				res.setState(true);
				res.setMsgDesc("Updated Successfully");
				res.setLoginID(obj.getT1());
				//System.out.println(obj.getT1()+"This is loginid update function in userdao");
				res.setPhNo(obj.getT4());
			}
		}
		return res;
	}

	public static Result delete(long syskey, String modifieduserId, Connection conn) throws SQLException {
		Result res = new Result();
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		// String userId = "";

		if (!checkCanDelete(syskey, conn))// check user session exist or not, If
											// exist,system do not allow to
											// delete that user
		{
			res.setState(false);
			res.setMsgDesc("Cannot delete.Please forced Sign Out.");
		} else {
			res = PersonDao.deletePerson(syskey, modifieduserId, conn);

			if (res.isState()) {

				/*
				 * String s_sql = "SELECT T1 FROM UVM005_A a WHERE Syskey='"+
				 * syskey + "' and a.recordstatus<>4"; PreparedStatement sttmt =
				 * conn.prepareStatement(s_sql); ResultSet result =
				 * sttmt.executeQuery(); while (result.next()) {
				 * res.setUserId(result.getString("T1")); userId =
				 * result.getString("T1"); }
				 */

				String sql = "delete From JUN002_A Where n1= ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setLong(1, syskey);
				stmt.executeUpdate();

				/*sql = "Delete from Agent  WHERE loginId= (Select t1 From UVM005_A Where RecordStatus<>4 And syskey =?) ";

				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, syskey);
				int count = stmt.executeUpdate();*/

				sql = "UPDATE UVM005_A SET RecordStatus=4,modifieddate=? WHERE Syskey=?";
				stmt = conn.prepareStatement(sql);
				int j = 1;
				stmt.setString(j++, date);
				stmt.setLong(j++, syskey);
				int rs = stmt.executeUpdate();
				if (rs > 0) {

					res.setState(true);
					res.setMsgDesc("Deleted Successfully");
				} else {
					res.setState(false);
					res.setMsgDesc("Deleted Fail");
				}
			}
		}

		return res;
	}

	public static boolean checkCanDelete(long key, Connection conn) throws SQLException {

		ArrayList<String> dbrs = new ArrayList<String>();
		String userId = "";
		String sql = "SELECT t1 FROM UVM005_A WHERE syskey = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, key);
		ResultSet result = pstmt.executeQuery();
		if (result.next()) {
			userId = result.getString("t1");
		}
		String query = "SELECT SessionID FROM tblSession  WHERE AutoKey = (SELECT MAX(AutoKey) FROM TblSession WHERE (UserID = ? COLLATE SQL_Latin1_General_CP1_CS_AS) AND Status = 0) ";
		PreparedStatement stmt = conn.prepareStatement(query);
		int j = 1;
		stmt.setString(j++, userId);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			dbrs.add(res.getString("SessionID"));

		}
		if (dbrs.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean canDelete(long key, Connection conn) throws SQLException {

		ArrayList<Long> dbrs = new ArrayList<Long>();
		String sql = "Select n1 From JUN002_A Where n2=?";
		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setLong(1, key);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			dbrs.add(result.getLong("n1"));

		}
		if (dbrs.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	// get user setup list
	/*
	 * public UserViewDataset getAllUserData(String searchtext, Connection conn,
	 * String operation) throws SQLException { int recordStatus = 0, n7 = 0;
	 * String searchText = searchtext.replace("'", "''"); String whereClause =
	 * ""; if (operation.equalsIgnoreCase("alluserprofile")) { if
	 * (searchText.equals("")) { whereClause =
	 * "where RecordStatus<>4 and role = 1 "; } else { whereClause =
	 * "where RecordStatus<>4 and role = 1" + " and (" + " t1 like '%" +
	 * searchText + "%' or UN like '%" + searchText + "%' )"; }
	 * 
	 * } else if (operation.equalsIgnoreCase("allbankuser")) { if
	 * (searchText.equals("")) { whereClause =
	 * "where RecordStatus<>4 and role = 2 "; } else { whereClause =
	 * "where RecordStatus<>4 and role = 2" + " and (" + " t1 like '%" +
	 * searchText + "%' or UN like '%" + searchText + "%')"; } } else if
	 * (operation.equalsIgnoreCase("activate")) { if (searchText.equals("")) {
	 * whereClause =
	 * " where (RecordStatus = 21 or RecordStatus = 1) and role = 1 "; } else {
	 * whereClause =
	 * " where ((RecordStatus = 21 or RecordStatus = 1) and role = 1)" + " and "
	 * + "(t1 like '%" + searchText + "%' or UN like '%" + searchText + "%') ";
	 * }
	 * 
	 * } else { if (operation.equalsIgnoreCase("lock")) { recordStatus = 2; n7 =
	 * 0;
	 * 
	 * } else if (operation.equalsIgnoreCase("deactivate")) { recordStatus = 2;
	 * n7 = 0;
	 * 
	 * } else if (operation.equalsIgnoreCase("unlock")) { recordStatus = 2; n7 =
	 * 11;
	 * 
	 * }
	 * 
	 * if (searchText.equals("")) { whereClause = "where RecordStatus =" +
	 * recordStatus + " and lock =" + n7 + "and role = 1"; } else { whereClause
	 * = "where RecordStatus =" + recordStatus + " and lock =" + n7 +
	 * "and role = 1 and " + "(t1 like '%" + searchText + "%' or UN like '%" +
	 * searchText + "%') "; } }
	 * 
	 * ArrayList<UserViewData> ret = new ArrayList<UserViewData>();
	 * ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(defineView(), whereClause,
	 * "ORDER BY t1,UN", conn);
	 * 
	 * for (int i = 0; i < dbrs.size(); i++) {
	 * ret.add(getDBViewRecord(dbrs.get(i))); }
	 * 
	 * UserViewDataset dataSet = new UserViewDataset(); dataSet.setArlData(ret);
	 * return dataSet; }
	 */

	// get user setup list
	public UserViewDataset getAllUserData(String searchtext, Connection conn, String operation) throws SQLException {
		int recordStatus = 0, n7 = 0;
		String searchText = searchtext.replace("'", "''");
		String whereClause = "";
		if (operation.equalsIgnoreCase("alluserprofile")) {
			if (searchText.equals("")) {
				whereClause = "where RecordStatus<>4 and role = 1 ";
			} else {
				whereClause = "where RecordStatus<>4 and role = 1" + " and (" + " t1 like '%" + searchText
						+ "%' or UN like '%" + searchText + "%' or t7 like '%" + searchText + "%' )";
			}

		} else if (operation.equalsIgnoreCase("allbankuser")) {
			if (searchText.equals("")) {
				whereClause = "where RecordStatus<>4 and role <> 1 ";
			} else {
				whereClause = "where RecordStatus<>4 and role <> 1" + " and (" + " t1 like '%" + searchText
						+ "%' or UN like '%" + searchText + "%' or customerID like '%" + searchText + "%' )";
			}
		} else if (operation.equalsIgnoreCase("activate")) {
			if (searchText.equals("")) {
				whereClause = " where (RecordStatus = 21 or RecordStatus = 1) and role = 1 ";
			} else {
				whereClause = " where ((RecordStatus = 21 or RecordStatus = 1) and role = 1)" + " and " + "(t1 like '%"
						+ searchText + "%' or UN like '%" + searchText + "%' or customerID like '%" + searchText
						+ "%' ) ";
			}

		} else {
			if (operation.equalsIgnoreCase("lock")) {
				recordStatus = 2;
				n7 = 0;

			} else if (operation.equalsIgnoreCase("deactivate")) {
				recordStatus = 2;
				n7 = 0;

			} else if (operation.equalsIgnoreCase("unlock")) {
				recordStatus = 2;
				n7 = 11;

			}

			if (searchText.equals("")) {
				whereClause = "where RecordStatus =" + recordStatus + " and lock =" + n7 + "and role = 1";
			} else {
				whereClause = "where RecordStatus =" + recordStatus + " and lock =" + n7 + "and role = 1 and "
						+ "(t1 like '%" + searchText + "%' or UN like '%" + searchText + "%' or customerID like '%"
						+ searchText + "%' ) ";
			}
		}

		ArrayList<UserViewData> ret = new ArrayList<UserViewData>();
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(defineView(), whereClause, "ORDER BY t1,UN,customerID", conn);

		for (int i = 0; i < dbrs.size(); i++) {
			ret.add(getDBViewRecord(dbrs.get(i)));
		}

		UserViewDataset dataSet = new UserViewDataset();
		dataSet.setArlData(ret);
		return dataSet;
	}

	public static long getPersonSyskey(long usys, Connection con) throws SQLException {

		String sql = "SELECT * FROM UVM005_A WHERE syskey=?";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setLong(1, usys);
		ResultSet result = stat.executeQuery();
		long u = 0;
		while (result.next()) {
			u = result.getLong("n4");
		}

		return u;

	}

	public UserData readByUserID(String aUserID, Connection conn) throws SQLException {

		UserData ret = new UserData();
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), "WHERE RecordStatus<>4 AND t1='" + aUserID + "'", "",
				conn);
		if (dbrs.size() > 0)
			ret = getDBRecord(dbrs.get(0));

		return ret;

	}

	public ArrayList<Result> getAllAgent(Connection conn) throws SQLException {

		ArrayList<Result> datalist = new ArrayList<Result>();
		String agentName = "";

		String sql = "Select LogInID,Name from Agent Where RecordStatus <> 4 and n1 =1 ORDER BY ID";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			Result combo = new Result();
			agentName = res.getString("LogInID") + " , " + res.getString("Name");
			combo.setKeyString(agentName);
			combo.setKeyst(res.getString("LogInID"));
			datalist.add(combo);
		}

		return datalist;
	}

	public ArrayList<Result> getAllFeatures(Connection conn) throws SQLException {

		ArrayList<Result> datalist = new ArrayList<Result>();

		String sql = "select Code,Description from LOVDetails where hkey = (select syskey from LOVHeader where description = 'Feature') ORDER BY Syskey";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			Result combo = new Result();
			combo.setKeyst(res.getString("Code"));
			combo.setKeyString(res.getString("Description"));
			datalist.add(combo);
		}

		return datalist;
	}


	public Result checkLockDeactive(String status, long syskey, Connection conn) throws SQLException {

		Result result = new Result();
		int totalCount = 0;
		String sql = "Select count(*) Count from TblSession Where UserId =(Select t1 from  UVM005_A Where Syskey = ?) And Status = 0";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, syskey);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			totalCount = res.getInt("Count");
		}

		if (totalCount > 0) {
			result.setMsgCode("0014");
			result.setMsgDesc("Cannot " + status + ".Please forced Sign Out.");
		} else {
			result.setMsgCode("0000");
			result.setMsgDesc("Success");
		}

		return result;
	}

	public Result canUpdate(String status, String userId, Connection conn) throws SQLException {

		Result result = new Result();
		int totalCount = 0;
		String sql = "Select count(*) Count from TblSession Where UserId =? And Status = 0";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, userId);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			totalCount = res.getInt("Count");
		}

		if (totalCount > 0) {
			result.setMsgCode("0014");
			result.setMsgDesc("Cannot " + status + ".Please forced Sign Out");
		} else {
			result.setMsgCode("0000");
			result.setMsgDesc("Success");
		}

		return result;
	}

	public Result activateDeactivateUser(long syskey, Connection conn, String status, String userId)
			throws SQLException {
		Result res = new Result();
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String sql = "";
		int restatus = 0;
		if (status.equalsIgnoreCase("Activate")) {
			restatus = 2;
			res.setMsgCode("0000");
		} else if (status.equalsIgnoreCase("Deactivate")) {
			restatus = 21;
			res = checkLockDeactive(status, syskey, conn);
		}
		try {
			if (res.getMsgCode().equals("0000")) {

				sql = "UPDATE UVM012_A SET RecordStatus=?,t5=?,modifieddate=? WHERE Syskey IN(SELECT N4 FROM UVM005_A WHERE RecordStatus <> 4 and Syskey=?)";

				PreparedStatement stmt = conn.prepareStatement(sql);
				int i = 1;
				stmt.setInt(i++, restatus);
				stmt.setString(i++, userId);
				stmt.setString(i++, date);
				stmt.setLong(i++, syskey);
				int rs = stmt.executeUpdate();
				if (rs > 0) {
					sql = "UPDATE UVM005_A SET RecordStatus= ?,modifieddate=? WHERE RecordStatus <> 4 and Syskey=?";
					stmt = conn.prepareStatement(sql);
					int j = 1;
					stmt.setInt(j++, restatus);
					stmt.setString(j++, date);
					stmt.setLong(j++, syskey);
					rs = stmt.executeUpdate();
					if (rs > 0) {
						res.setState(true);
					} else {
						res.setState(false);
					}
				}

			} else {
				res.setState(false);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public Result lockUnlockUser(String userid, long syskey, Connection conn, String status) throws SQLException {
		Result res = new Result();
		String sql = "";
		int restatus = 0;
		int retryCount = 3;
		if (status.equalsIgnoreCase("Lock")) {
			restatus = 11;
			retryCount = 3;
			res = checkLockDeactive(status, syskey, conn);
		} else if (status.equalsIgnoreCase("Unlock")) {
			restatus = 0;
			retryCount = 0;
			res.setMsgCode("0000");
		}
		try {
			if (res.getMsgCode().equals("0000")) {
				sql = "UPDATE UVM005_A SET modifieddate = ?, n7=?, n1=? WHERE Syskey=?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				int j = 1;
				stmt.setString(j++, GeneralUtil.datetoString());
				stmt.setInt(j++, restatus);
				stmt.setInt(j++, retryCount);
				stmt.setLong(j++, syskey);
				int rs = stmt.executeUpdate();
				if (rs > 0) {
					String query = "UPDATE UVM012_A SET modifieddate = ? ,t5 = ? WHERE Syskey IN(SELECT N4 FROM UVM005_A WHERE Syskey=?)";
					PreparedStatement stmt1 = conn.prepareStatement(query);
					int i = 1;
					stmt1.setString(i++, GeneralUtil.datetoString());
					stmt1.setString(i++, userid);
					stmt1.setLong(i++, syskey);
					int rst = stmt1.executeUpdate();
					if (rst > 0) {
						res.setState(true);// lock/unlock successfully
					} else {
						res.setState(false);
					}
				}

			} else {
				res.setState(false);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public boolean checkFirstTimeLogin(long syskey, Connection conn) {
		boolean result = false;
		int flag = 0;
		String query = "SELECT N10 FROM UVM012_A WHERE Syskey IN(SELECT N4 FROM UVM005_A a WHERE a.Syskey=?)";
		try {
			PreparedStatement stmt1 = conn.prepareStatement(query);
			int k = 1;
			stmt1.setLong(k++, syskey);
			ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				flag = rs.getInt("N10");
				if (flag == 1) {
					result = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateCreatedDate(long syskey, Connection conn) {
		int rs = 0;
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String sql = "UPDATE UVM012_A SET createddate=? WHERE Syskey IN(SELECT N4 FROM UVM005_A a WHERE a.Syskey=?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			int j = 1;
			stmt.setString(j++, date);
			stmt.setLong(j++, syskey);
			rs = stmt.executeUpdate();
			if (rs > 0) {
				return rs;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	// ///////////////////////////////////////////////////////////////
	public static UserViewDataset getAllUCJunctionData(String searchtext, Connection conn) throws SQLException {
		String searchText = searchtext.replace("'", "''");
		ArrayList<UserViewData> ret = new ArrayList<UserViewData>();
		String whereClause = "";
		if (searchText.equals("") || searchText.equals(null)) {
			whereClause = " where a.RecordStatus<>4 and a.n2=1 and a.n7<>11 and b.RecordStatus<>21";
		} else {
			whereClause = " where a.RecordStatus<>4 and a.n2=1 and a.n7<>11 and b.RecordStatus<>21" + " and "
					+ "(a.t1 like ? or a.t7 like ? or b.t2 like ? )";
		}
		String l_Query = "select b.t2 'UserName',a.t1,a.t7 from UVM005_A a Join UVM012_A b ON a.n4=b.syskey"
				+ whereClause;

		l_Query += " order by a.t1,b.t2,a.t7";

		PreparedStatement pstmt = conn.prepareStatement(l_Query);
		if (searchText.equals("") || searchText.equals(null)) {
		} else {
			for (int i = 1; i < 4; i++) {
				pstmt.setString(i, "%" + searchText + "%");
			}
		}
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			UserViewData data = new UserViewData();
			{
				data.setT1(rs.getString("t1"));
				data.setUsername(rs.getString("UserName"));
				data.setT7(rs.getString("t7"));
				ret.add(data);
			}
		}
		UserViewDataset dataSet = new UserViewDataset();
		dataSet.setArlData(ret);

		return dataSet;

	}

	public static UserViewDataset getAllUserCIFDataList(String searchtext, Connection conn) throws SQLException {
		// new
		String searchText = searchtext.replace("'", "''");
		ArrayList<UserViewData> ret = new ArrayList<UserViewData>();
		String whereClause = "";
		if (searchText.equals("")) {
			whereClause = "where u.RecordStatus<>4 and m.mobileUserID = u.t1";
		} else {
			whereClause = "where (u.RecordStatus<>4 and m.mobileUserID = u.t1 and m.mobileUserID like ?) or "
					+ "(u.RecordStatus<>4 and m.mobileUserID = u.t1 and u.t2 like ?)";
		}
		String l_Query = "select distinct u.t1 as userid,u.t2 as username from MobileCAJunction m , UVM012_A u "
				+ whereClause;
		l_Query += " order by u.t1,u.t2";

		PreparedStatement pstmt = conn.prepareStatement(l_Query);
		if (searchText.equals("")) {
		} else {
			int i = 1;
			pstmt.setString(i++, "%" + searchText + "%");
			pstmt.setString(i++, "%" + searchText + "%");
		}
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			UserViewData data = new UserViewData();
			{
				data.setT1(rs.getString("userid"));
				data.setUserName(rs.getString("username"));
				ret.add(data);
			}
		}
		UserViewDataset dataSet = new UserViewDataset();
		dataSet.setArlData(ret);
		return dataSet;
	}

	// MMPPM
	public ArrayList<String> getCustomerID(String aUserID, Connection conn) throws SQLException {
		ArrayList<String> ret = new ArrayList<String>();
		String sql = "select CustomerID from MobileCAJunction where MobileUserID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, aUserID);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String aCustomerID = rs.getString("CustomerID");
			ret.add(aCustomerID);
		}
		ps.close();
		rs.close();
		return ret;
	}

	// MMPPM
	public ArrayList<String> getActiveAcctListByLoginUserID(String aLoginUserID, Connection conn) throws SQLException {
		ArrayList<String> ret = new ArrayList<String>();
		String sql = "select AccountNo from MobileCAJunction where MobileUserID = ? and n1 = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, aLoginUserID);
		ps.setInt(2, 1);// 1 is active
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String aCustomerID = rs.getString("AccountNo");
			ret.add(aCustomerID);
		}
		ps.close();
		rs.close();
		return ret;
	}

	// zmth

	/*
	 * public UserData readUserProfileDataBySyskey(long syskey, Connection conn)
	 * throws SQLException {
	 * 
	 * UserData ret = new UserData(); ArrayList<DBRecord> dbrs =
	 * DBMgr.getDBRecords(define(), "WHERE RecordStatus<>4 AND syskey=" +
	 * syskey, "", conn); if (dbrs.size() > 0) ret = getDBRecord(dbrs.get(0));
	 * 
	 * return ret;
	 * 
	 * }
	 */

	public UserData readUserProfileDataBySyskey(long syskey, Connection conn) throws SQLException {
		UserData ret = new UserData();
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), "WHERE RecordStatus<>4 AND syskey=" + syskey, "", conn);
		if (dbrs.size() > 0)
			ret = getDBRecord(dbrs.get(0));

		return ret;

	}

	public static String generatePassword(Connection conn) throws SQLException {
		String num_list = "0123456789";
		String lowerchar_list = "abcdefghijklmnopqrstuvwxyz";
		String upperchar_list = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String specialchar_list = "!#$%&*?@";
		String list = "!#$%&*?@abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		int minimunlength = 0;
		int splength = 0;
		int uppercharlength = 0;
		int lowercharlength = 0;
		int numberlength = 0;

		StringBuffer randStr = new StringBuffer();
		int number = 0;

		String sql = "SELECT MinimumLength,SpecialCharacter,UpperCaseCharacter,LowerCaseCharacter,Number FROM tblPasswordpolicy";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet res = ps.executeQuery();
		while (res.next()) {
			minimunlength = res.getInt("MinimumLength");
			splength = res.getInt("SpecialCharacter");// 2
			uppercharlength = res.getInt("UpperCaseCharacter");// 1
			lowercharlength = res.getInt("LowerCaseCharacter");// 1
			numberlength = res.getInt("Number");// 0
		}

		String pwd = "";
		if (splength == 0 && uppercharlength == 0 && lowercharlength == 0 && numberlength == 0) {
			pwd = autogeneratePassword(minimunlength);
		} else {
			char ch;
			int count = 0;
			Random randomGenerator = new Random();
			if (splength != 0) {
				number = randomGenerator.nextInt(specialchar_list.length());
				ch = specialchar_list.charAt(number);
				randStr.append(ch);
				splength = splength - 1;
				count++;
			}
			if (uppercharlength != 0) {
				number = randomGenerator.nextInt(upperchar_list.length());
				ch = upperchar_list.charAt(number);
				randStr.append(ch);
				uppercharlength = uppercharlength - 1;
				count++;
			}
			if (lowercharlength != 0) {
				number = randomGenerator.nextInt(lowerchar_list.length());
				ch = lowerchar_list.charAt(number);
				randStr.append(ch);
				lowercharlength = lowercharlength - 1;
				count++;
			}
			if (numberlength != 0) {
				number = randomGenerator.nextInt(num_list.length());
				ch = num_list.charAt(number);
				randStr.append(ch);
				numberlength = numberlength - 1;
				count++;
			}
			if (splength != 0) {
				for (int f = 0; f < splength; f++) {
					number = randomGenerator.nextInt(specialchar_list.length());
					ch = specialchar_list.charAt(number);
					randStr.append(ch);
					count++;
				}
			}

			if (uppercharlength != 0) {
				for (int d = 0; d < uppercharlength; d++) {
					number = randomGenerator.nextInt(upperchar_list.length());
					ch = upperchar_list.charAt(number);
					randStr.append(ch);
					count++;
				}

			}
			if (numberlength != 0) {
				for (int j = 0; j < numberlength; j++) {
					number = randomGenerator.nextInt(num_list.length());
					ch = num_list.charAt(number);
					randStr.append(ch);
					count++;
				}
			}

			if (lowercharlength != 0) {
				for (int k = 0; k < lowercharlength; k++) {
					number = randomGenerator.nextInt(lowerchar_list.length());
					ch = lowerchar_list.charAt(number);
					randStr.append(ch);
					count++;
				}
			}

			if (count < minimunlength) {
				for (int i = 0; i < (minimunlength - count); i++) {
					number = randomGenerator.nextInt(list.length());
					ch = list.charAt(number);
					randStr.append(ch);
				}
			}
			pwd = String.valueOf(randStr);
		}
		return pwd;
	}

	// generate password
	public static String autogeneratePassword(int length) {

		String pwd = "";
		if (length == 0) {
			length = 6;
		}
		int pwdlength = length;

		char ch;
		String num_list = "0123456789";
		String char_list = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String specialchar_list = "!#$%&*?@";
		// String specialchar_list = "!@#$%^&*()+`~";
		String list = "!#$%&*?@abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer randStr = new StringBuffer();

		for (int i = 0; i < pwdlength; i++) {
			Random randomGenerator = new Random();
			int number = 0;
			if (i == 0) {
				number = randomGenerator.nextInt(char_list.length());
				ch = char_list.charAt(number);
			} else if (i == 1) {
				number = randomGenerator.nextInt(specialchar_list.length());
				ch = specialchar_list.charAt(number);
			} else if (i == 2) {
				number = randomGenerator.nextInt(num_list.length());
				ch = num_list.charAt(number);
			} else {
				number = randomGenerator.nextInt(list.length());
				ch = list.charAt(number);
			}
			randStr.append(ch);
		}
		pwd = String.valueOf(randStr);
		return pwd;

	}

	public UserData getUserNameAndStatus(String Id, Connection conn) throws SQLException {

		UserData ret = new UserData();

		String query = "SELECT  a.T1 'Id',b.T2 'Name' FROM UVM005_A a JOIN UVM012_A b ON a.T1=b.t1 WHERE"
				+ " (a.t3=? COLLATE SQL_Latin1_General_CP1_CS_AS OR a.t4 = ? COLLATE SQL_Latin1_General_CP1_CS_AS) AND  a.recordStatus <> 4";
		PreparedStatement stmt1 = conn.prepareStatement(query);
		int index = 1;
		stmt1.setString(index++, Id);
		stmt1.setString(index++,Id);
		ResultSet result = stmt1.executeQuery();
		while (result.next()) {
			ret.setT1(result.getString("Id"));
			ret.setName(result.getString("Name"));

		}
		if(!ret.getT1().equalsIgnoreCase("")){
			ret = checkSessionStatus(ret, conn);
		}

		return ret;
	}

	public UserData checkSessionStatus(UserData data, Connection conn) throws SQLException {
		String Id = "";
		int mloginstatus = 1;
		int webloginstatus = 1;
		int sessionType = 0;
		String sql = "SELECT UserID,Status,sessionType FROM tblSession WHERE (AutoKey = (SELECT MAX(AutoKey) FROM TblSession WHERE UserID = ? AND SessionType = 2 AND Status <> 8)) or (AutoKey = (SELECT MAX(AutoKey) FROM TblSession WHERE UserID = ? AND SessionType = 1 AND Status <> 8))";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int j = 1;
		pstmt.setString(j++, data.getT1());
		pstmt.setString(j++, data.getT1());
		ResultSet res = pstmt.executeQuery();
		while (res.next()) {
			Id = res.getString("UserID");
			sessionType = res.getInt("sessionType");
			if(sessionType == 1){
				mloginstatus = res.getInt("Status");
			}
			else if(sessionType == 2){
				webloginstatus = res.getInt("Status");
			}

		}
		data.setMloginstatus(mloginstatus);
		data.setWebloginstatus(webloginstatus);
		return data;
	}

	public UserData getUserNameAndNrc(String userId, Connection conn) throws SQLException {

		UserData ret = new UserData();
		String pwd = "";
		String query = "Select  a.T1 'Id',a.T7 'Nrc',a.T2 'Password',b.T2 'Name' from UVM005_A a Join UVM012_A b ON a.T1=b.t1 where "
				+ "(a.t1=? COLLATE SQL_Latin1_General_CP1_CS_AS ) and a.recordStatus = 2 and a.n7 = 0 and a.n2 = 1";
		/*String query = "Select  a.T4 'Id',a.T7 'Nrc',a.T2 'Password',b.T2 'Name' from UVM005_A a Join UVM012_A b ON a.T1=b.t1 where (a.t4='"
				+ userId + "' COLLATE SQL_Latin1_General_CP1_CS_AS ) and a.recordStatus = 2 and a.n7 = 0 and a.n2 = 1";*/
		PreparedStatement stmt1 = conn.prepareStatement(query);
		stmt1.setString(1, userId);
		ResultSet result = stmt1.executeQuery();
		while (result.next()) {
			ret.setT1(result.getString("Id"));  
			ret.setT7(result.getString("Nrc"));
			ret.setName(result.getString("Name"));
			pwd = result.getString("Password");
			ret.setT2(ServerUtil.decryptPIN(pwd));
		}
		return ret;
	}

	public Result markPrinted(String userId, Connection conn) throws SQLException {
		Result res = new Result();
		String sql = "";
		int printCount = 0;
		try {
			String query = "Select n8 From UVM012_A WHERE Syskey IN(SELECT N4 FROM UVM005_A WHERE recordStatus <> 4 and t1=?)";
			PreparedStatement stmt1 = conn.prepareStatement(query);
			stmt1.setString(1, userId);
			ResultSet result = stmt1.executeQuery();
			while (result.next()) {
				printCount = result.getInt("n8");
			}
			if (printCount == 1) {
				res.setState(false);
			} else {
				sql = "UPDATE UVM012_A SET n8=? WHERE Syskey IN(SELECT N4 FROM UVM005_A WHERE recordStatus <> 4 and t1=?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				int i = 1;
				stmt.setInt(i++, 1);
				stmt.setString(i++, userId);
				int rs = stmt.executeUpdate();
				if (rs > 0) {
					res.setState(true);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public Result forcedlogoutbyId(String userId,int type, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		Result res = new Result();
		String query = "UPDATE tblSession SET Status = 6 WHERE UserID = ? AND status <> 8 AND SessionType = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		int i = 1;
		pstmt.setString(i++, userId);
		pstmt.setInt(i++, type);
		int rs = pstmt.executeUpdate();
		if (rs > 0) {
			res.setState(true);
			res.setMsgCode("0000");
			res.setMsgDesc("Forced Sign Out Successfully");
		} else {
			res.setState(false);
			res.setMsgCode("0014");
			res.setMsgDesc("Forced Sign Out Failed");
		}
		return res;
	}

	/*
	 * public Result resetPasswordById(String userId, String adminId, Connection
	 * conn) throws SQLException { // TODO Auto-generated method stub Result res
	 * = new Result(); String phoneNo = ""; String result = ""; Long syskey =
	 * 0L; // boolean flag=false; res = canUpdate("reset", userId, conn); if
	 * (res.getMsgCode().equals("0000")) { String pwd =
	 * ServerUtil.encryptPIN(generatePassword(conn)); String query2 =
	 * "update UVM005_A SET t2='" + pwd + "' " +
	 * " WHERE recordStatus <> 4 and t1='" + userId + "'"; PreparedStatement
	 * stmt2 = conn.prepareStatement(query2); int count = stmt2.executeUpdate();
	 * if (count > 0) { // after reset,update flag String query =
	 * "update UVM012_A SET n8=2 , n10 = 1 , t14 = '" +
	 * GeneralUtil.datetoString() + "'  WHERE recordStatus <> 4 and t1='" +
	 * userId + "'"; PreparedStatement stmt = conn.prepareStatement(query); int
	 * count2 = stmt.executeUpdate(); if (count2 > 0) { // get phone no String
	 * query3 =
	 * "SELECT t4,syskey from UVM005_A WHERE RecordStatus <> 4 and t1 ='" +
	 * userId + "'"; PreparedStatement stmt3 = conn.prepareStatement(query3);
	 * ResultSet rs = stmt3.executeQuery(); while (rs.next()) { phoneNo =
	 * rs.getString("t4"); syskey = rs.getLong("syskey"); } // before sending
	 * sms, update flag String query4 =
	 * "update UVM012_A SET n8=3 WHERE RecordStatus <> 4 and t1='" + userId +
	 * "'"; PreparedStatement stmt4 = conn.prepareStatement(query4); int count3
	 * = stmt4.executeUpdate(); if (count3 > 0) { // String smsMsg = "Dear
	 * Customer,Your new password : // "+ServerUtil.decryptPIN(pwd)+""; String
	 * Msg = ConnAdmin.readExternalUrl("SMSPwd"); String smsMsg =
	 * Msg.replace("<msg>", "" + ServerUtil.decryptPIN(pwd) + ""); OTPReqData
	 * smspwd = sendSMSPassword(phoneNo, smsMsg); // send // SMS // after
	 * sending sms,update flag String query5 =
	 * "update UVM012_A SET n8=? WHERE RecordStatus <> 4 and t1='" + userId +
	 * "'"; PreparedStatement stmt5 = conn.prepareStatement(query5); if
	 * (smspwd.getCode().equals("0000")) // send // successfully // case {
	 * stmt5.setInt(1, 4); } else if (smspwd.getCode().equals("0014")) // send
	 * // failed // or // invalid // phone no // case { stmt5.setInt(1, 5); }
	 * int count4 = stmt5.executeUpdate(); if (count4 > 0) { String query6 =
	 * "update UVM012_A SET t12=?,t13=? WHERE RecordStatus <> 4 and t1='" +
	 * userId + "'"; PreparedStatement stmt6 = conn.prepareStatement(query6);
	 * int j = 1; stmt6.setString(j++, smspwd.getCode()); stmt6.setString(j++,
	 * smspwd.getDesc()); int count5 = stmt6.executeUpdate(); if (count5 > 0) {
	 * 
	 * res = lockUnlockUser(adminId, syskey, conn, "unLock"); if (res.isState())
	 * { res.setMsgDesc("Password reset successfully."); } else {
	 * res.setMsgDesc("Password reset fail."); } } } } else {
	 * res.setState(false); res.setMsgDesc("Password reset fail."); }
	 * 
	 * } else { res.setState(false); res.setMsgDesc("Password reset fail."); } }
	 * else { res.setState(false); res.setMsgDesc("Password reset fail."); } }
	 * else { res.setState(false); }
	 * 
	 * return res; }
	 */



	public String getPhoneNo(String userId, Connection conn) throws SQLException {
		String phno = new String();

		String query = "select t4 from UVM005_A Where RecordStatus <> 4 and t1 = ? ";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, userId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			phno = rs.getString("T4");
		}
		rs.close();
		ps.close();
		return phno;
	}

	public boolean checkOtp(String userId, String phoneNo, Connection conn) throws SQLException {
		boolean result = false;

		String query = "select Top(1) T4 from UVM005_A Where RecordStatus <> 4 and t1 = ? and t4 = ? ";
		PreparedStatement ps = conn.prepareStatement(query);
		int i = 1;
		ps.setString(i++, userId);
		ps.setString(i++, phoneNo);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			result = true;
		}
		rs.close();
		ps.close();
		return result;
	}

	public String getUserName(String userid, Connection con) {
		String username = "";
		String sql = "select t2 from UVM012_A where RecordStatus<>4 and t1=? ";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userid);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				username = res.getString("t2");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}

	public Result getUserId(String phNo, Connection con) {
		String userId = "";
		Result result = new Result();

		String query = "select t1 from UVM005_A where RecordStatus<>4 and n2 = 1 and t4=? ";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, phNo);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				userId = res.getString("t1");
			}
			if (!userId.equals("")) {
				result.setPhNo(phNo);
				result.setUserId(userId);
				result.setMsgCode("0000");
				result.setMsgDesc("Registered User with requested phone number exists");
			} else {
				result.setPhNo(phNo);
				result.setUserId(userId);
				result.setMsgCode("0014");
				result.setMsgDesc("Registered User with requested phone number doesn't exist");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String getlasttimelogin(String userid, Connection con) {
		String lasttimelogin = "";
		String time = "";
		// String sql = "SELECT * FROM LoginHistory WHERE UserID = '"+userid+"'
		// AND Time = (SELECT MAX(Time) FROM LoginHistory WHERE UserID =
		// '"+userid+"' AND Activity = 'mobilesignin' AND Date = (SELECT
		// MAX(Date) FROM LoginHistory))";
		String sql = "SELECT * FROM LoginHistory WHERE Time = (SELECT MAX(Time) FROM LoginHistory WHERE  "
				+ "Activity = 'mobilesignin' AND UserID = ? AND Date = (SELECT MAX(Date) FROM LoginHistory "
				+ "WHERE UserID = ? ))";
		String date = "";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			int i = 1;
			stmt.setString(i++, userid);
			stmt.setString(i++, userid);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				time = res.getString("Time");
				date = res.getString("Date");
			}
			if (!date.equalsIgnoreCase("") && !time.equalsIgnoreCase("")) {
				lasttimelogin = date.substring(6, date.length()) + "-" + date.substring(4, 6) + "-"
						+ date.substring(0, 4) + " " + time + "";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lasttimelogin;
	}

	public Result checkCustomerID(UCJunction[] data, Connection conn) throws SQLException {
		Result res = new Result();
		String cid = "";
		// String query1 = "Select t7 from UVM012_A where recordstatus <> 4 and
		// t7= ?";

		ArrayList<String> CusidList = new ArrayList<>();

		for (UCJunction da : data) {
			CusidList.add(da.getCustomerid());

		}
		;
		String customerid = "";
		for (int i = 0; i < CusidList.size(); i++) {
			if (i == CusidList.size() - 1) {
				customerid += "'" + CusidList.get(i) + "'";
			} else {
				customerid += "'" + CusidList.get(i) + "'" + ",";
			}
		}
		;

		String l_query = "Select customerid from UCJunction where customerid in (?)";
		PreparedStatement p_st = conn.prepareStatement(l_query);
		p_st.setString(1, customerid);
		ResultSet rs = p_st.executeQuery();
		while (rs.next()) {
			cid = rs.getString("customerid");
		}

		if (cid.equalsIgnoreCase("")) {
			res.setState(true);
		} else {
			res.setState(false);
		}
		return res;
	}

	public Result checkPhoneNo(long syskey, String phNo, Connection conn) throws SQLException {
		Result res = new Result();
		String phno = "";
		String query1 = "Select t4 from UVM005_A where recordstatus <> 4 and n2 = 1 and t4= ? and  n4 <> ?";
		PreparedStatement stmt2 = conn.prepareStatement(query1);
		stmt2.setString(1, phNo);
		stmt2.setLong(2, syskey);
		ResultSet resph = stmt2.executeQuery();
		while (resph.next()) {
			phno = resph.getString("T4");
		}
		if (phno.equalsIgnoreCase("")) {
			res.setState(true);
		} else {
			res.setState(false);
		}
		return res;
	}

	public Result checkCustomerID(long syskey, UCJunction[] data, Connection conn) throws SQLException {
		Result res = new Result();
		String cid = "";
		ArrayList<String> CusidList = new ArrayList<>();

		for (UCJunction da : data) {
			CusidList.add(da.getCustomerid());

		}
		;
		String customerid = "";
		for (int i = 0; i < CusidList.size(); i++) {
			if (i == CusidList.size() - 1) {
				customerid += "'" + CusidList.get(i) + "'";
			} else {
				customerid += "'" + CusidList.get(i) + "'" + ",";
			}
		}
		;

		String l_Query = "Select customerid from UCJunction where customerid in (?)";

		PreparedStatement pstmt = conn.prepareStatement(l_Query);
		pstmt.setString(1, customerid);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			cid = rs.getString("customerid");
		}
		if (cid.equalsIgnoreCase("")) {
			res.setState(true);
		} else {
			res.setState(false);
		}
		return res;
	}
	/*
	 * public Result checkCustomerID(long syskey, UCJunction[] data, Connection
	 * conn) throws SQLException { Result res = new Result(); String cid = "";
	 * PreparedStatement stmt2 = conn.prepareStatement(query1);
	 * stmt2.setString(1, customerid); stmt2.setLong(2, syskey);
	 * ArrayList<String> CusidList=new ArrayList<>();
	 * 
	 * for(UCJunction da: data){ CusidList.add(da.getCustomerid());
	 * 
	 * }; String customerid= ""; for(int i=0;i<CusidList.size();i++){
	 * if(i==CusidList.size()-1){ customerid+="'"+CusidList.get(i)+"'"; }else{
	 * customerid +="'"+CusidList.get(i)+"'"+","; } }; // String query1 =
	 * "Select customerid from UCJunction where customerid in (?)"; String
	 * query1 =
	 * "Select customerid from UCJunction where customerid in ('013000020')";
	 * 
	 * PreparedStatement stmt2 = conn.prepareStatement(query1); //
	 * stmt2.setString(1, customerid);
	 * 
	 * ResultSet resph = stmt2.executeQuery(); while (resph.next()) { cid =
	 * resph.getString("customerid"); } if (cid.equalsIgnoreCase("")) {
	 * res.setState(true); } else { res.setState(false); } return res; }
	 */

	public Result checkParentAgent(String loginID, String parentID, Connection conn) throws SQLException {
		Result res = new Result();
		int count = 0;
		String query1 = "Select Count(*) Count  From Agent Where N1 = 1 And LogInID = ?  And ParentID = ? ";
		PreparedStatement stmt2 = conn.prepareStatement(query1);
		int i = 1;
		stmt2.setString(i++, loginID);
		stmt2.setString(i++, parentID);
		ResultSet ret = stmt2.executeQuery();
		while (ret.next()) {
			count = ret.getInt("Count");
		}
		if (count == 0) {

			res.setState(true);
		} else {
			res.setState(false);
		}
		return res;
	}

	public Result checkUpateSubAgent(String loginID, String parentID, Connection conn) throws SQLException {
		Result res = new Result();
		int count = 0;

		if (parentID.equalsIgnoreCase("") || parentID.equalsIgnoreCase("null")) {
			res.setState(true);
		} else {
			String query1 = "Select Count (*) Count from Agent Where ParentId = ? And n1 = 0";
			System.out.println(" Sub parent agent : " + query1);
			PreparedStatement stmt2 = conn.prepareStatement(query1);
			stmt2.setString(1, loginID);
			ResultSet ret = stmt2.executeQuery();
			while (ret.next()) {
				count = ret.getInt("Count");
			}
			if (count == 0) {
				res.setState(true);
			} else {
				res.setState(false);
			}
		}
		return res;
	}

	// public Result checkUpateSubAgent(String loginID, String parentID,
	// Connection conn) throws SQLException {
	// boolean response = false;
	// Result res = new Result();
	// int count = 0;
	//
	// String query1 = "Select Count (*) Count from Agent Where ParentId = '" +
	// loginID + "' And n1 = 0";
	// System.out.println(" Sub parent agent : " + query1);
	// PreparedStatement stmt2 = conn.prepareStatement(query1);
	// ResultSet ret = stmt2.executeQuery();
	// while (ret.next()) {
	// count = ret.getInt("Count");
	// }
	// if (count == 0) {
	//
	// res.setState(true);
	// } else {
	// res.setState(false);
	// }
	// return res;
	// }
	public UCJunction[] getCustIDByUID(String userid, Connection conn) {
		ArrayList<UCJunction> ret = new ArrayList<UCJunction>();
		UCJunction data = new UCJunction();
		try {
			String sql = "SELECT customerid FROM UCJunction WHERE UserId = ? ";

			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, userid);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				data = new UCJunction();
				data.setCustomerid(result.getString("customerid"));
				ret.add(data);

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		UCJunction[] dataarray = new UCJunction[ret.size()];

		for (int i = 0; i < ret.size(); i++) {
			dataarray[i] = ret.get(i);
		}
		return dataarray;
	}

	public Result checkAgentParent(String parentID, Connection conn) throws SQLException {
		Result res = new Result();
		int count = 0;
		String query1 = "Select Count(*) Count  From Agent Where LogInID <> ParentID  And N1 = 0 And ParentID =  ? ";

		PreparedStatement stmt2 = conn.prepareStatement(query1);
		stmt2.setString(1, parentID);
		ResultSet ret = stmt2.executeQuery();
		while (ret.next()) {
			count = ret.getInt("Count");
		}
		if (count == 0) {
			res.setState(true);
		} else {
			res.setState(false);
			res.setMsgDesc("Cannot update.Please remove sub agent frist");
		}
		return res;
	}

}