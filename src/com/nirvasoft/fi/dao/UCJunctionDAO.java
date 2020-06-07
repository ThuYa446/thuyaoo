package com.nirvasoft.fi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nirvasoft.fi.data.UserCIFJunction;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.MrBean;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.users.data.UCJunction;
import com.nirvasoft.fi.users.data.UCJunctionArr;
import com.nirvasoft.fi.users.data.UserCIFJunctionArr;
import com.nirvasoft.fi.users.data.UserViewData;
import com.nirvasoft.fi.users.data.UserViewDataset;

public class UCJunctionDAO {

	public static String mTableName = "UCJunction";
	
	public static ArrayList<String> getCustomerID(String aUserID, Connection conn) throws SQLException {
		ArrayList<String> ret = new ArrayList<String>();
		String sql = "select CustomerID from " + mTableName + " where UserID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, aUserID);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			String aCustomerID = rs.getString("CustomerID");
			ret.add(aCustomerID);
		}
		ps.close();
		rs.close();
		return ret;
	} 
	
	
	UCJunction readData(ResultSet rs, UCJunction data) throws SQLException{
		data.setAutoKey(rs.getLong("AutoKey"));
		data.setUserID(rs.getString("UserID"));
		data.setCustomerid(rs.getString("CustomerID"));
		data.setT1(rs.getString("t1"));
		data.setT2(rs.getString("t2"));
		data.setN1(rs.getInt("n1"));
		data.setN2(rs.getInt("n2"));
		data.setSrno(rs.getRow());
		return data;
	}

	public boolean saveUCJunction(UCJunctionArr aData, Connection l_Conn) throws SQLException {
		boolean ret = false;
		String l_Query1 = "delete from "+mTableName+" where UserId=?  ";		
		PreparedStatement pstmt = l_Conn.prepareStatement(l_Query1);
		pstmt.setString(1, aData.getUserID());
		System.out.println(aData.getUserID());
		pstmt.executeUpdate();	
		String l_Query2 = "INSERT INTO [dbo].["+mTableName+"] ([UserID] ,[CustomerID],[t1],[t2],[n1] ,[n2])	 VALUES(?,?,?,?,?,?)";
		PreparedStatement ps2 = l_Conn.prepareStatement(l_Query2);
		UCJunction[] ucArr = aData.getData();		
		for (UCJunction data : ucArr) {
			updateData(ps2, data, aData.getUserID());
			ps2.addBatch();
		}
		int status[] = ps2.executeBatch();
		for (int i : status) {
			if(i > 0){
				ret = true;
				break;
			}
		}
		return ret;
		}

	private void updateData(PreparedStatement ps, UCJunction data, String aUserID) throws SQLException {
		int i = 1;
		ps.setString(i++, aUserID);
		ps.setString(i++, data.getCustomerid());
		ps.setString(i++, data.getT1());
		ps.setString(i++, data.getT2());
		ps.setInt(i++, data.getN1());
		ps.setInt(i++, data.getN2());	
	}

	public boolean delete(String aUserID, Connection l_Conn) throws SQLException {
		boolean ret = false;
		String l_Query = "delete from "+mTableName+" where UserId= ?  ";
		PreparedStatement pstmt = l_Conn.prepareStatement(l_Query);
		pstmt.setString(1, aUserID);
		if(pstmt.executeUpdate() > 0){
			ret = true;
		}
		return ret;
	}



	public boolean deleteUserCIFJunction(String aUserID, Connection l_Conn) throws SQLException {
		boolean ret = false;
		String l_Query = "delete from MobileCAJunction where mobileUserID= ? ";
		PreparedStatement pstmt = l_Conn.prepareStatement(l_Query);
		pstmt.setString(1, aUserID);
		if(pstmt.executeUpdate() > 0){
			ret = true;
		}
		return ret;
	}




}


