package com.nirvasoft.fi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nirvasoft.fi.users.data.UserRoleViewData;

public class UserRoleViewDao {

	public static UserRoleViewData[] getUserRoleList(Connection conn) {
		ArrayList<UserRoleViewData> ret = new ArrayList<UserRoleViewData>();
		UserRoleViewData data = new UserRoleViewData();
		try {
			String sql = "SELECT syskey,t2,n1 FROM UVM009_A WHERE RecordStatus<>4";

			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				data = new UserRoleViewData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t2"));

				ret.add(data);

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		UserRoleViewData[] dataarray = new UserRoleViewData[ret.size()];

		if (dataarray.length == 1) {
			dataarray = new UserRoleViewData[ret.size() + 1];
			dataarray[0] = ret.get(0);
			dataarray[1] = new UserRoleViewData();
		}

		for (int i = 0; i < ret.size(); i++) {
			dataarray[i] = ret.get(i);
		}

		return dataarray;

	}
	
	public static UserRoleViewData[] getMerchantUserList(Connection conn) {
		ArrayList<UserRoleViewData> ret = new ArrayList<UserRoleViewData>();
		UserRoleViewData data = new UserRoleViewData();
		try {
			String sql = "select syskey,UN from V_U001 ";

			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				data = new UserRoleViewData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("UN"));

				ret.add(data);

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		UserRoleViewData[] dataarray = new UserRoleViewData[ret.size()];

		if (dataarray.length == 1) {
			dataarray = new UserRoleViewData[ret.size() + 1];
			dataarray[0] = ret.get(0);
			dataarray[1] = new UserRoleViewData();
		}

		for (int i = 0; i < ret.size(); i++) {
			dataarray[i] = ret.get(i);
		}

		return dataarray;

	}

	public static UserRoleViewData[] getUserRoleList(long skey, Connection conn) {
		ArrayList<UserRoleViewData> ret = new ArrayList<UserRoleViewData>();
		UserRoleViewData data = new UserRoleViewData();
		try {
			String sql = "SELECT syskey,t2 FROM UVM009_A WHERE RecordStatus<>4";

			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				data = new UserRoleViewData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t2"));

				long s[] = UserRoleViewDao.getRoleResult(skey, conn);
				
				for (int i = 0; i < s.length; i++) {
					if (s[i] == result.getLong("syskey")) {
						data.setFlag(true);
					}

				}

				ret.add(data);

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		UserRoleViewData[] dataarray = new UserRoleViewData[ret.size()];

		if (dataarray.length == 1) {
			dataarray = new UserRoleViewData[ret.size() + 1];
			dataarray[0] = ret.get(0);
			dataarray[1] = new UserRoleViewData();
		}

		for (int i = 0; i < ret.size(); i++) {
			dataarray[i] = ret.get(i);
		}

		return dataarray;

	}
	
	public static UserRoleViewData[] getMerchantUserList(String id, Connection conn) {
		ArrayList<UserRoleViewData> ret = new ArrayList<UserRoleViewData>();
		UserRoleViewData data = new UserRoleViewData();
		try {
			
			String sql = "select B.syskey,B.t1,ISNULL(A.flag,0) as flag from"
					+ " (select ISNULL(A.UserSysKey,-1) as usersyskey,u.t1,"
					+ " case when ISNULL(A.UserSysKey,-1) = -1 then 0 else 1 end as flag  "
					+ "from (select * from mujunction m where m.merchantid = ? ) A "
					+ "right join V_U001 u on u.syskey = A.usersyskey ) A "
					+ "right join V_U001 B on A.usersyskey = B.syskey order by B.syskey"; 

			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, id);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				data = new UserRoleViewData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t1"));
				data.setFlag(result.getBoolean("flag"));
			
				ret.add(data);

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		UserRoleViewData[] dataarray = new UserRoleViewData[ret.size()];

		if (dataarray.length == 1) {
			dataarray = new UserRoleViewData[ret.size() + 1];
			dataarray[0] = ret.get(0);
			dataarray[1] = new UserRoleViewData();
		}

		for (int i = 0; i < ret.size(); i++) {
			dataarray[i] = ret.get(i);
		}

		return dataarray;

	}

	public static long[] getRoleResult(long skey, Connection conn) throws SQLException {

		ArrayList<Long> ary = new ArrayList<Long>();
		try {
			String sql = "Select n2 from JUN002_A Where RecordStatus<>4 and n1=? ";

			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setLong(1, skey);
			ResultSet res = stat.executeQuery();

			while (res.next()) {
				ary.add(res.getLong("n2"));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		long[] result = new long[ary.size()];
		
		if(result.length==1)
		{
			result = new long[ary.size()+1];
			result[0] = ary.get(0);
			result[1] = 0;
		}
		
		for (int i = 0; i < ary.size(); i++) {
			result[i] = ary.get(i);
		}

		return result;
	}
	
	public static long[] getUsersResult(String id, Connection conn) throws SQLException {

		ArrayList<Long> ary = new ArrayList<Long>();
		try {
			String sql = "select syskey from V_U001 " ;

			PreparedStatement stat = conn.prepareStatement(sql);
		
			ResultSet res = stat.executeQuery();

			while (res.next()) {
				ary.add(res.getLong("syskey"));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		long[] result = new long[ary.size()];
		
		if(result.length==1)
		{
			result = new long[ary.size()+1];
			result[0] = ary.get(0);
			result[1] = 0;
		}
		
		for (int i = 0; i < ary.size(); i++) {
			result[i] = ary.get(i);
		}

		return result;
	}
	
	//zmth
	public long[] getCustomerRole(Connection conn) {
        ArrayList<Long> ret = new ArrayList<Long>();
        Long roleSyskey;
        try {
//            String sql = "SELECT syskey,t2 FROM UVM009_A WHERE RecordStatus<>4 and T2 = 'Customer'";
        	String sql = "SELECT syskey,t2 FROM UVM009_A WHERE T2 = 'Customer'";

            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet result = stat.executeQuery();

            while (result.next()) {
                roleSyskey = result.getLong("syskey");
                ret.add(roleSyskey);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }    

        long[]  rsyskey = new long[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            rsyskey[i] = ret.get(i);
        }
        return rsyskey;

    }
	
	
}