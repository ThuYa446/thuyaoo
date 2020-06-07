package com.nirvasoft.fi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.shared.PwdData;
import com.nirvasoft.fi.util.ServerUtil;

public class PwdDao {


	public Result updatePwdData(PwdData data,Connection l_Conn) throws SQLException {
		String userid = data.getUserid();
		Result ret = new Result();
		
		if(checkCurrentPassword(data,userid, l_Conn)){
			
			if(data.getPassword().equals(data.getNewpassword()))
			{
				ret.setMsgDesc("New Password should not be the same as current password");
			}
			else if (data.getNewpassword().length() < 6 || data.getNewpassword().length() >20)
			{
				ret.setMsgDesc("Password length must be between 6 and 20 characters");
			}
			else
			{
				String query = "UPDATE UVM005_A SET  [t2]=?  WHERE RecordStatus <> 4 and [t1]=?";
				PreparedStatement pstmt = l_Conn.prepareStatement(query);
				pstmt.setString(1, ServerUtil.encryptPIN(data.getNewpassword()));
				pstmt.setString(2, userid); 
				
				if (pstmt.executeUpdate() > 0) {
					query = "UPDATE tblSession SET  Status = 9  WHERE UserID=?";
					pstmt = l_Conn.prepareStatement(query);
					pstmt.setString(1, userid); 
					if (pstmt.executeUpdate() > 0) {
						ret.setMsgDesc("Updated Successfully.Please Signin Again...");
						ret.setKeyst(Long.toString(data.getSyskey()));
						ret.setMsgCode("0000");
						ret.setState(true);
					} else {
						ret.setMsgDesc("Updated fail");
						ret.setMsgCode("0014");
						ret.setState(false);
					}
					
				} else {
					ret.setMsgDesc("Updated fail");
					ret.setMsgCode("0014");
					ret.setState(false);
				}
			}
			
		}
		else{
			ret.setMsgDesc("Current Password Doesn't Exist!");
			ret.setMsgCode("0014");
			ret.setState(false);
		}
		return ret;
	}
	
 

	public boolean checkCurrentPassword(PwdData data, String userid, Connection l_Conn) throws SQLException {
	
		String l_Query = "SELECT * FROM UVM005_A WHERE RecordStatus <> 4 and ([t1]=? COLLATE SQL_Latin1_General_CP1_CS_AS)";
		String psw = "";
		
		PreparedStatement pstmt = l_Conn.prepareStatement(l_Query);
		pstmt.setString(1, userid);
		
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			
			 psw = rs.getString("t2");
		}
	    System.out.println("pwd"+ServerUtil.encryptPIN(data.getPassword()));
		if(ServerUtil.encryptPIN(data.getPassword()).equals(psw))
			return true;
		else
			return false;
	}
	
public Result forcechangePassword(PwdData data, Connection l_Conn) throws SQLException {
		String userid = data.getUserid();
		Result ret = new Result();
		if(checkvalidDate(userid,l_Conn))
		{
			if(checkCurrentPassword(data, userid, l_Conn)){
				
				if(data.getPassword().equals(data.getNewpassword()))
				{
					ret.setKeyst("4");
					ret.setMsgDesc("New Password should not be the same as current password");
				}
				else if (data.getNewpassword().length() < 6 || data.getNewpassword().length() >20)
				{
					ret.setKeyst("5");
					ret.setMsgDesc("Password length must be between 6 and 20 characters");
				}
				else
				{

					String query = "UPDATE UVM005_A SET  [t2]=?  WHERE RecordStatus <> 4 and ([t1]=? COLLATE SQL_Latin1_General_CP1_CS_AS )";
					PreparedStatement pstmt = l_Conn.prepareStatement(query);
					pstmt.setString(1, ServerUtil.encryptPIN(data.getNewpassword()));
					pstmt.setString(2, userid); 
					
					if (pstmt.executeUpdate() > 0) {
						if(ChangePwd(data.getNewpassword(),userid,l_Conn)> 0)
						{
							ret.setMsgDesc("Updated successfully");
							ret.setKeyst(Long.toString(data.getSyskey()));
							ret.setState(true);
							ret.setKeyst("0");
						}
						
					} else {
						ret.setKeyst("1");
						ret.setMsgDesc("Updated fail");
					}
				}
				
			}
			else{
				ret.setKeyst("2");
				ret.setMsgDesc("Current Password Doesn't Exist!");
			}
		}
		else
		{
			LockUserAcc(userid,l_Conn);
			ret.setMsgDesc("Your account has been locked because of 7 days over");
			ret.setKeyst("3");
		}

		return ret;
	}

/*public Result forcechangePassword(PwdData data, Connection l_Conn) throws SQLException {
		String userid = data.getUserid();
		Result ret = new Result();
		if(checkvalidDate(userid,l_Conn))
		{
			if(checkCurrentPassword(data, userid, l_Conn)){
				
				if(data.getPassword().equals(data.getNewpassword()))
				{
					
					ret.setMsgDesc("New Password should not be the same as current password");
				}
				else if (data.getNewpassword().length() < 6 || data.getNewpassword().length() >20)
				{
					
					ret.setMsgDesc("Password length must be between 6 and 20 characters");
				}
				else
				{

					String query = "UPDATE UVM005_A SET  [t2]=?  WHERE RecordStatus <> 4 and ([t1]=? COLLATE SQL_Latin1_General_CP1_CS_AS )";
					PreparedStatement pstmt = l_Conn.prepareStatement(query);
					pstmt.setString(1, ServerUtil.encryptPIN(data.getNewpassword()));
					pstmt.setString(2, userid); 
					
					if (pstmt.executeUpdate() > 0) {
						if(ChangePwd(data.getNewpassword(),userid,l_Conn)> 0)
						{
							ret.setMsgDesc("Updated successfully");
							ret.setKeyst(Long.toString(data.getSyskey()));
							ret.setState(true);
							ret.setMsgCode("0000");
						}
						
					} else {
						
						ret.setMsgDesc("Updated fail");
					}
				}
				
			}
			else{
				
				ret.setMsgDesc("Current Password Doesn't Exist!");
			}
		}
		else
		{
			LockUserAcc(userid,l_Conn);
			ret.setMsgDesc("Your account has been locked because of 7 days over");
			
		}

		return ret;
	}*/


public void LockUserAcc(String userName,Connection con)
{
	try
	{
		String sql = "UPDATE UVM005_A SET n1=?,n7=? WHERE recordStatus <> 4 and (t1=? COLLATE SQL_Latin1_General_CP1_CS_AS)"; 
		PreparedStatement stmt = con.prepareStatement(sql);
		int i = 1 ;
		stmt.setInt(i++,3);
		stmt.setInt(i++, 11);
		stmt.setString(i++,userName);
		int rs = stmt.executeUpdate();
		if (rs > 0) {
		sql = "UPDATE UVM012_A SET n1=?,n7=? WHERE recordStatus <> 4 and syskey = (Select n4 from UVM005_A where t1=?)"; 
		stmt = con.prepareStatement(sql);
		int j = 1 ;
		stmt.setInt(j++,3);
		stmt.setInt(j++,11);
		stmt.setString(j++, userName);
		rs = stmt.executeUpdate();
		if (rs > 0) {
			System.out.println("update (Lock) successfully");
		}
	}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
}
public boolean checkvalidDate(String userid, Connection l_Conn) throws SQLException
{
	boolean result = false;
	Date registerdate = new Date();
	Date sysdate = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");		
	String chksevenday = "",createDate = ""  ;
	String duedate = ConnAdmin.readExternalUrl("DueDate");
	int subduedate = Integer.parseInt(duedate);
	
	
	String sql = "Select createddate from ForceChangePwd where userId=? ";
	PreparedStatement pstmt = l_Conn.prepareStatement(sql);
	pstmt.setString(1, userid);
	ResultSet res =pstmt.executeQuery();
	while (res.next())
	{
		createDate = res.getString("createddate");
	}
	
	//createdDate ="20170321"; // need to change
	chksevenday = createDate.substring(0,4)+ "-" + createDate.substring(4,6) + "-" + createDate.substring(6,8) ;
	
	try {
		registerdate = df.parse(chksevenday);
	} catch (java.text.ParseException e) {
		e.printStackTrace();
	} 
	
	Calendar cal = Calendar.getInstance();
	cal.setTime(registerdate);
	cal.add(Calendar.DATE,  subduedate);
	Date ValueDate = cal.getTime();
	
	
	if (ValueDate.equals(sysdate) || ValueDate.after(sysdate)){
		 //need to do something
		result = true;

	}else{
		System.out.println("Value Date after   :: "+ ValueDate);
		result = false;
	}
	
	return result;
}

public int ChangePwd(String newpwd,String userId,Connection l_Conn) throws SQLException
{

	String sql = "Update ForceChangePwd set password=? where userId=?";
	PreparedStatement stmt1 = l_Conn.prepareStatement(sql);
	int j = 1;
	int rs = 0;
	stmt1.setString(j++, ServerUtil.encryptPIN(newpwd));
	stmt1.setString(j++,userId);
	rs = stmt1.executeUpdate();
	if(rs > 0)
	{
		return rs;
		
	}
	return rs;
}


}
