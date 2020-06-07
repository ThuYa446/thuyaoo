package com.nirvasoft.fi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nirvasoft.database.DBField;
import com.nirvasoft.database.DBRecord;
import com.nirvasoft.fi.framework.Menu;
import com.nirvasoft.fi.framework.MrBean;
import com.nirvasoft.fi.framework.Profile;
import com.nirvasoft.fi.shared.MSigninRequestData;
import com.nirvasoft.fi.shared.MSigninResponseData;
import com.nirvasoft.fi.util.ServerUtil;

public class Service001Dao {
	public Menu[] getProfileSubMenuItem(Profile p, Connection con, String parent) {
		ArrayList<String> arrdesp = new ArrayList<String>();
		ArrayList<String> arrmenu = new ArrayList<String>();
		String[] arydesp = null;
		String[] arymenu = null;

		Menu[] items = null;

		try {

			String sql = "SELECT t2,t3 FROM UVM022_A " + " WHERE syskey IN (SELECT n2 FROM UVM023_A WHERE "
					+ " n1 IN (SELECT n2 FROM JUN002_A WHERE n1 IN " + "(SELECT syskey FROM UVM005_A WHERE (t1=?))))"
					+ " AND RecordStatus<> 4 AND n2 IN (SELECT DISTINCT syskey from UVM022_A WHERE t2=?)";
			sql += " order by n6 ";

			PreparedStatement stat = con.prepareStatement(sql);
			int k=1;
			stat.setString(k++, p.getUserID());
			stat.setString(k++, parent);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				arrdesp.add(result.getString("t2"));
				arrmenu.add(result.getString("t3"));

			}

			arydesp = new String[arrdesp.size()];
			for (int i = 0; i < arrdesp.size(); i++) {
				arydesp[i] = arrdesp.get(i);
			}

			arymenu = new String[arrmenu.size()];
			for (int i = 0; i < arrmenu.size(); i++) {
				arymenu[i] = arrmenu.get(i);
			}

			items = new Menu[arymenu.length];
			Menu subobj = new Menu();
			if (arymenu.length == 1)
				items = new Menu[arymenu.length + 1];

			for (int j = 0; j < arymenu.length; j++) {
				subobj = new Menu();
				subobj.setMenuItem(arymenu[j]);
				subobj.setCaption(arydesp[j]);
				items[j] = subobj;

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return items;

	}
	

	public static DBRecord define() {
		DBRecord ret = new DBRecord();
		ret.setTableName("UVM005_A");
		ret.setFields(new ArrayList<DBField>());
		ret.getFields().add(new DBField("syskey", (byte) 2));
		ret.getFields().add(new DBField("t1", (byte) 5));
		ret.getFields().add(new DBField("t2", (byte) 5));
		return ret;
	}

	public MSigninResponseData mobilelogin(MSigninRequestData mprofile, Connection con) {

		MSigninResponseData response = new MSigninResponseData();
		String userid = mprofile.getUserID();
		String pwd = mprofile.getPassword();
		response.setUserid(mprofile.getUserID());

		String sql = "";
		String sqlretryCount = "";

		int retryCount = 0;
		int lockstatus = 0;
		pwd = ServerUtil.encryptPIN(pwd);
		String str = "";
		int recordStatus = 0;
		int lock = 0;
		PreparedStatement stmt2 = null;
		try {
			sql = "Select t1,t2,RecordStatus,n7,n2 From UVM005_A Where n2 = ? and recordStatus <> 4 and ((t1=? COLLATE SQL_Latin1_General_CP1_CS_AS and t2=?))";
			stmt2 = con.prepareStatement(sql);
			int k = 1;
			stmt2.setInt(k++, 1);
			stmt2.setString(k++, userid);
			stmt2.setString(k++, pwd);

			ResultSet result = stmt2.executeQuery();
			while (result.next()) {
				recordStatus = result.getInt("RecordStatus");
				lock = result.getInt("n7");
				userid = result.getString("t1");
				str = getUserData(userid, con);
			}
			if (str.equals("")) {
				sqlretryCount = "Select n1,n7,recordStatus from UVM005_A where recordStatus <> 4 and n2 = 1 and (t1='"
						+ userid + "' COLLATE SQL_Latin1_General_CP1_CS_AS)";
				PreparedStatement stmt1 = con.prepareStatement(sqlretryCount);
				ResultSet res = stmt1.executeQuery();
				while (res.next()) {
					retryCount = res.getInt("n1");
					recordStatus = res.getInt("recordStatus");
					lockstatus = res.getInt("n7");
				}
				if (recordStatus == 0 && retryCount == 0 && lockstatus == 0) {
					response.setCode("0014");
					response.setDesc("Invalid User ID or Password");
				}
				if (recordStatus == 1) {
					response.setCode("0014");
					response.setDesc("Your account is not activated.Please contact bank administrator");
				}
				if (recordStatus == 21) {
					response.setCode("0014");
					response.setDesc("Your acount is deactivated.Please contact bank administrator");
				}
				if (recordStatus == 2 && retryCount == 3 && lockstatus == 11) {
					response.setCode("0014");
					response.setDesc("Your acount has been locked.Please contact bank administrator");
				}
				if (recordStatus == 2 && retryCount < 3 && lockstatus != 11) {
					int count = ++retryCount;
					sql = "UPDATE UVM005_A SET n1=? WHERE recordStatus <> 4 and n2 = 1 and (t1=? COLLATE SQL_Latin1_General_CP1_CS_AS )";
					PreparedStatement stmt = con.prepareStatement(sql);
					int i = 1;
					stmt.setInt(i++, count);
					stmt.setString(i++, userid);
					int rs = stmt.executeUpdate();
					if (rs > 0) {
						sql = "UPDATE UVM012_A SET n1=? WHERE recordStatus <> 4 and syskey IN (Select n4 from UVM005_A where recordStatus <> 4 and n2 = 1 and  "
								+ "t1=? COLLATE SQL_Latin1_General_CP1_CS_AS )";
						stmt = con.prepareStatement(sql);
						int j = 1;
						stmt.setInt(j++, count);
						stmt.setString(j++, userid);
						rs = stmt.executeUpdate();
						if (rs > 0) {
							System.out.println("update retries count successfully");
						}
						if (count == 3) {
							LockUserAcc(userid, con);
							response.setCode("0014");
							response.setDesc("Your acount has been locked.Please contact bank administrator");
							return response;
						}
					}
					response.setCode("0014");
					response.setDesc("Invalid User ID or Password");
				}

			} else {
				if (recordStatus == 2 && lock == 11 && recordStatus != 4) {
					str = "";
					response.setCode("0014");
					response.setDesc("Your acount has been locked.Please contact bank administrator");
				} else if (recordStatus == 21 && recordStatus != 4) {
					str = "";
					response.setCode("0014");
					response.setDesc("Your acount is deactivated.Please contact bank administrator");
				} else {
					response.setCode("0000");
					response.setDesc("Login Successfully");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("0014");
			response.setDesc("Contact to Bank Administrator");
		}
		return response;

	}

	public Profile login(Profile p, Connection con) throws SQLException {

		String userid = p.getUserID();
		String psw = p.getPassword();

		String sql = "";
		String u = "";
		psw = ServerUtil.encryptPIN(psw);
		Profile resProfile = new Profile();
		PreparedStatement stmt2 = null;

		sql = "Select t1,t2,RecordStatus,n7,n2,t4,t5 From UVM005_A Where n2 = ? and recordStatus <> 4 and ((t3=? COLLATE SQL_Latin1_General_CP1_CS_AS and t2=?) OR (t4=? COLLATE SQL_Latin1_General_CP1_CS_AS and t2=?))";
		stmt2 = con.prepareStatement(sql);
		int k = 1;
		stmt2.setInt(k++, 2);
		stmt2.setString(k++, userid);
		stmt2.setString(k++, psw);
		stmt2.setString(k++, userid);
		stmt2.setString(k++, psw);

		ResultSet result = stmt2.executeQuery();
		while (result.next()) {
			userid = result.getString("t1");
			u = getUserData(userid, con);
			resProfile.setPhoneno(result.getString("t4"));
			resProfile.setUserName(u);
			resProfile.setUserID(userid);
			resProfile.setTaxOffice(result.getString("t5"));

		}
		if(u.equalsIgnoreCase("")){
			resProfile.setCode("0014");
		}else{
			resProfile.setCode("0000");
		}
		
		
		return resProfile;

	}

	public static void LockUserAcc(String userName, Connection con) {
		try {
			String sql = "UPDATE UVM005_A SET n1=?,n7=? WHERE recordStatus <> 4 and t1=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			int i = 1;
			stmt.setInt(i++, 3);
			stmt.setInt(i++, 11);
			stmt.setString(i++, userName);
			int rs = stmt.executeUpdate();
			if (rs > 0) {
				sql = "UPDATE UVM012_A SET n1=?,n7=? WHERE recordStatus <> 4 and syskey IN (Select n4 from UVM005_A where recordStatus <> 4 and t1=?)";
				stmt = con.prepareStatement(sql);
				int j = 1;
				stmt.setInt(j++, 3);
				stmt.setInt(j++, 11);
				stmt.setString(j++, userName);
				rs = stmt.executeUpdate();
				if (rs > 0) {
					System.out.println("update (Lock) successfully");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getUserData(String userid, Connection con) throws SQLException {
		String sql = " SELECT *  FROM View_User_A WHERE (userid=?  COLLATE SQL_Latin1_General_CP1_CS_AS) ";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, userid);
		ResultSet result = stat.executeQuery();
		String u = "";
		if (result.next()) {
			u = result.getString("username");
			return u;
		}
		return u;

	}
//uvm022 menu..//uvm05 user(eg. MTK) //uvm023 //uvm009 role..//
	//userbetweenrole(JUN002)
	public static ArrayList<String> getMainMenu(Profile p, Connection con) {
		ArrayList<String> ret = new ArrayList<String>();
		try {
			String sql = "SELECT t2 FROM UVM022_A " + "WHERE syskey IN (SELECT n2 FROM UVM023_A WHERE n3 = 2 And " // 
					+ "n1 IN (SELECT n2 FROM JUN002_A WHERE n1 IN (SELECT syskey FROM UVM005_A WHERE (t1=?))))" 
					+ " AND n2=0 AND RecordStatus<> 4 ";
			sql += " order by n6";

			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, p.getUserID());
			ResultSet result = stat.executeQuery();
			while (result.next()) {
				ret.add(result.getString("t2"));

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return ret;

	}

	public static ArrayList<String> getSubMenuItem(Profile p, Connection con, String parent) {
		ArrayList<String> ret = new ArrayList<String>();

		try {

			String sql = "SELECT t2 FROM UVM022_A " + " WHERE syskey IN (SELECT n2 FROM UVM023_A WHERE "
					+ " n1 IN (SELECT n2 FROM JUN002_A WHERE n1 IN " + "(SELECT syskey FROM UVM005_A WHERE (t1=?))))"
					+ " AND RecordStatus<> 4 AND n2 IN (SELECT DISTINCT syskey from UVM022_A WHERE t2=?)";
			sql += " order by n6 ";

			PreparedStatement stat = con.prepareStatement(sql);
			int i=1;
			stat.setString(i++, p.getUserID() );
			stat.setString(i++, parent );
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				ret.add(result.getString("t2"));

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return ret;

	}

	public static long[] getParentKey(MrBean user, Connection con, String userid) {
		ArrayList<Long> key = new ArrayList<Long>();

		try {

			String sql = "SELECT DISTINCT syskey FROM UVM022_A " + "WHERE syskey IN (SELECT n2 FROM UVM023_A WHERE "
					+ "n1 IN (SELECT n2 FROM JUN002_A WHERE n1 IN (SELECT syskey FROM UVM005_A WHERE t1=?)))" 
					+ " AND n2=0 AND RecordStatus<> 4 ORDER BY syskey ";

			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1,  user.getUser().getUserId());
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				key.add(result.getLong("syskey"));

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		long parentkey[] = new long[key.size()];

		for (int i = 0; i < key.size(); i++) {
			parentkey[i] = key.get(i);
		}

		return parentkey;

	}

	/* wkk */
	public static String getMerchant(Connection conn) throws SQLException {
		String ret = "";
		boolean isArray = true;

		String sql = "select A.UserId as MerchantID, A.UserName as MerchantName,A.ProcessingCode,ISNULL(ref.AccountNumber,'') as AccountNumber "
				+ " from (select c.UserId,c.UserName,p.ProcessingCode from CMSMerchant c, "
				+ " PayTemplateHeader p where c.UserId = p.MerchantID "
				+ " and c.recordStatus <> 4 ) A left join CMSMerchantAccRef ref "
				+ " on A.UserId = ref.t1 order by merchantname ";
		PreparedStatement stat = conn.prepareStatement(sql);
		ResultSet res = stat.executeQuery();

		ret = "[";
		while (res.next()) {
			ret += "{\"MerchantID\":\"" + res.getString("MerchantID") + "\",\"MerchantName\":\""
					+ res.getString("MerchantName") + "\",\"AccountNumber\":\"" + res.getString("AccountNumber")
					+ "\",\"ProcessingCode\":\"" + res.getString("ProcessingCode") + "\"}";
			ret += ",";
		}
		if (ret.length() > 1) {
			ret = ret.substring(0, ret.length() - 1);
		}

		ret += "]";

		return ret;
	}

	public Boolean checkForcePwdChange(MSigninRequestData p, Connection con) throws SQLException {
		Boolean res = false;
		int flag = 0;
		String sql = "SELECT t14,n1 FROM UVM012_A WHERE WHERE recordStatus <> 4 and syskey IN (Select n4 from UVM005_A where recordStatus <> 4 and n2 = 1 "
				+ "and t1= ? COLLATE SQL_Latin1_General_CP1_CS_AS )";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, p.getUserID());
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			flag = result.getInt("n1");
		}
		if (flag == 1) {
			res = true;
		}
		return res;
	}
	
	
}
