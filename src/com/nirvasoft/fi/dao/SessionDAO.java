package com.nirvasoft.fi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.shared.MSigninResponseData;
import com.nirvasoft.fi.shared.OTPCodeData;
import com.nirvasoft.fi.shared.OTPCodeReqData;
import com.nirvasoft.fi.shared.ResponseData;
import com.nirvasoft.fi.util.GeneralUtil;

public class SessionDAO {

	private String mTableName = "tblSession";
	public Result insertSession(String userId,Connection conn, int sessionType) throws SQLException{
		Result res = new Result();
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		long key = 0L;
		key = generateKey(userId,sessionType,conn);
		String query = "UPDATE "+ mTableName +" SET UserID = ?, SessionID = ?,LogInDateTime = ?,LastActivityDateTime = ?,Status = ?,SessionType = ?  WHERE AutoKey = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		int i = 1;
		pstmt.setString(i++,userId);
		pstmt.setString(i++,String.valueOf(key));
		pstmt.setString(i++,date);
		pstmt.setString(i++,date);
		pstmt.setInt(i++,0);
		pstmt.setInt(i++,sessionType);
		pstmt.setLong(i++,key);
		int rst = pstmt.executeUpdate();
		if (rst > 0) {
			res.setState(true);
			res.setMsgCode("0000");
			res.setMsgDesc("Insert Successfully");
			res.setSessionID(String.valueOf(key));
			
			
		}else{
			res.setState(false);
			res.setMsgCode("0014");
			res.setMsgDesc("Insert Failed");
		}
		pstmt.close();
		return res;
	}
	 private long generateKey(String aRef,int sessionType,Connection mConn) throws SQLException{
	        long l_RefKey = 0;
	        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	        String sql = "Insert Into " + mTableName + "(UserID,LogInDateTime,Status) values(?,?,?)";
	        PreparedStatement preparedstatement    = mConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        int i = 1;
	        preparedstatement.setString(i++,aRef);
	        preparedstatement.setString(i++,date);
	        preparedstatement.setInt(i++,0);
	        preparedstatement.executeUpdate();
	        ResultSet rs = preparedstatement.getGeneratedKeys();
	        if (rs != null && rs.next()) {
	            l_RefKey = rs.getLong(1);
	        }
	        
	        rs.close();
	        preparedstatement.close();
	        
	        return l_RefKey;
	    }
	 
		
		public int forceLogout(String userId , Connection conn,int sessionType) throws SQLException, ParseException{
			int res = 0;
			String updatequery = "UPDATE "+mTableName+" SET Status = ? WHERE UserID = ? AND Status <> 8 AND SessionType= ?";

			PreparedStatement ps = conn.prepareStatement(updatequery);
			int i = 1;
			ps.setInt(i++,5);
			ps.setString(i++,userId);
			ps.setInt(i++,sessionType);
			res = ps.executeUpdate();
			return res;
		}
		
	public ResponseData updateAndcheckActivityTime(String sessionId , String userId , Connection conn) throws SQLException{
		ResponseData res = new ResponseData();
		String lastactivitydt = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date());
		String Status = "";
		
		String sql = "select Status from "+ mTableName +" Where SessionID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sessionId );
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			Status = rs.getString("Status");
			if(Status.equals("0")){
				String query1 = "UPDATE "+ mTableName +" SET LastActivityDateTime = ?  Where SessionID = ? ";
				PreparedStatement p_stmt1 = conn.prepareStatement(query1);
				p_stmt1.setString(1, lastactivitydt);
				p_stmt1.setString(2, sessionId);
				int row = p_stmt1.executeUpdate();
				if (row > 0) {
					res.setCode("0000");
					res.setDesc("Updated Successfully");
				}else{
					res.setCode("0014");
					res.setDesc("Updated Failed");
				}
				
				p_stmt1.close();
			}else{
				res.setCode("0016");
				res.setDesc("Your session has expired.Please signin again...");
			}
		}else{
			res.setCode("0016");
			res.setDesc("Your session has expired.Please signin again...");
		}
		
		ps.close();		
		
		return res;
	}
	
	public ResponseData updateActivityTime(String sessionId , Connection conn) throws SQLException{
		ResponseData res = new ResponseData();
		String lastactivitydt = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date());
		String Status = "";
		
		String sql = "select Status from "+ mTableName +" Where SessionID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sessionId );
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			Status = rs.getString("Status");
			if(Status.equals("0")){
				String query1 = "UPDATE "+ mTableName +" SET LastActivityDateTime = ?  Where SessionID = ? ";
				PreparedStatement p_stmt1 = conn.prepareStatement(query1);
				p_stmt1.setString(1, lastactivitydt);
				p_stmt1.setString(2, sessionId);
				int row = p_stmt1.executeUpdate();
				
				
				if (row > 0) {
					res.setCode("0000");
					res.setDesc("Updated Successfully");
					
					
				}else{
					res.setCode("0014");
					res.setDesc("Updated Failed");
				}
				
				p_stmt1.close();
			}else{
				res.setCode("0016");
				res.setDesc("Your session has expired.Please signin again...");
			}			
		}else{
			res.setCode("0016");
			res.setDesc("Your session has expired.Please signin again...");
		}
		
		ps.close();		
		
		return res;
	}
	
	public Result logout(String sessionId , Connection conn) throws SQLException{
		Result res = new Result();
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String logoutquery = "Update "+ mTableName +" set Status = ?,LastActivityDateTime = ?,LogOutDateTime = ?  WHERE sessionID = ?";
		PreparedStatement ps = conn.prepareStatement(logoutquery);
		int i = 1;
		ps.setInt(i++,9);
		ps.setString(i++,date);
		ps.setString(i++,date);
		ps.setString(i++,sessionId);
		int result = ps.executeUpdate();
		if(result > 0)
		{
			res.setState(true);
			res.setMsgCode("0000");
			res.setMsgDesc("Success");
		}
		else
		{
			res.setState(false);
			res.setMsgCode("0014");
			res.setMsgDesc("Failed");
		}
		
		return res;
	}
	
  //check firstTime login
  public Result checkFirstTimeLogin(String userId,Connection conn) throws SQLException{
	  Result res = new Result();
	  int count = 0;
	  String query = "";
	  int status = 2;
	  
	  
	  query = "SELECT Status FROM FirstTimeLogin WHERE UserID = ?";
	  PreparedStatement pstmt = conn.prepareStatement(query);
	  pstmt.setString(1,userId);
	  ResultSet rs = pstmt.executeQuery();
	  if(rs.next()){
		  status = rs.getInt("Status"); 
	  }
	  if(status == 2){
		  query = "INSERT INTO FirstTimeLogin(UserID) VALUES(?)";
		  pstmt = conn.prepareStatement(query);
		  pstmt.setString(1,userId);
		  int rst = pstmt.executeUpdate();
		  if(rst > 0){
			  res.setMsgCode("0000");
			  res.setMsgDesc("This user is first time login");
		  }else{
			  res.setMsgCode("0014");
			  res.setMsgDesc("Insert Failed");
		  }
	  }else if(status == 1){
		  
		  res.setMsgCode("0001");
		  res.setMsgDesc("This user is not first time login");
	  }else{
			  res.setMsgCode("0000");
			  res.setMsgDesc("This user is first time login");
		  }
	  pstmt.close();
	  return res;
  }
  
  public String getOTPGeneratedTime(Connection conn,String sessionId) throws SQLException
	{
		String getotptime = "";
		String sql = "SELECT GetOTPDateTime FROM OTPSession WHERE AutoKey = (SELECT MAX(AutoKey) FROM OTPSession WHERE SessionID = ? AND Status <> 1)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
	    pstmt.setString(i++,sessionId);
		ResultSet result = pstmt.executeQuery();
		while (result.next())
		{
			getotptime = result.getString("GetOTPDateTime");
		}
		return getotptime;
	}
  
  
  public MSigninResponseData checkLogin(String userId, Connection conn,int sessionType) throws SQLException, ParseException{
		MSigninResponseData response= new MSigninResponseData();
		String max_time = "";
		int status = -1;
		int res = 0;
		String lastActivity_dtime = "";
		GeneralUtil gUtil = new GeneralUtil();
		String sql = "SELECT * FROM "+mTableName+" WHERE AutoKey = (SELECT MAX(AutoKey) FROM TblSession WHERE (UserID = ? COLLATE SQL_Latin1_General_CP1_CS_AS) AND Status <> 8 AND SessionType= ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		int i = 1;
		ps.setString(i++,userId);
		ps.setInt(i++,sessionType);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			status = rs.getInt("Status");
			lastActivity_dtime = rs.getString("LastActivityDateTime"); 
		}
		if(status != 0)
		{
			response.setCode("0000");
		}
		if(status == 0)
		{
			if(!lastActivity_dtime.equalsIgnoreCase("") && !lastActivity_dtime.equalsIgnoreCase("null"))
			{
				max_time = lastActivity_dtime.replace("-","").substring(0,17);
			}
			
			String currentdtime = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date());
			String sessionLimit = ConnAdmin.readExternalUrl("SESSIONTIMEADMIN"); // SESSIONTIME
			if( gUtil.checkTimeStamp(max_time,currentdtime,sessionLimit) == true)//user already logged in --user cannot signin
			{
				response.setCode("0014");
			}
			else
			{
				res = forceLogout(userId,conn,sessionType); // force logout -- user can signin
				if(res > 0) //Updating status for force logout succeed
				{
					response.setCode("0000");
				}
				else // Updating status for force logout failed
				{
					response.setCode("0014");
				}
			}

		}
		
			ps.close();
			rs.close();
		return response;
	}
  
  public ResponseData updateGetOTPCount(String referenceKey,Connection conn) throws SQLException, ParseException{
		
		ResponseData response = new ResponseData(); 
		String sql = "";
		String query = "";
		int count = 0;
		
			sql = "SELECT N1 FROM OTPSession WHERE AutoKey = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int i = 1;
		    pstmt.setLong(i++,Long.valueOf(referenceKey));
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				count = result.getInt("N1");
			}
	
			query = "UPDATE OTPSession SET N1 = ? WHERE AutoKey = ?";
			PreparedStatement pstmt1 = conn.prepareStatement(query);
			int j = 1;
			pstmt1.setInt(j++,++count);
			pstmt1.setLong(j++,Long.valueOf(referenceKey));
			int rs = pstmt1.executeUpdate();
			if(rs > 0 )
			{
				response.setCode("0000");
				response.setDesc("Updated Successfully ");
			}
			else
			{
				response.setCode("0014");
				response.setDesc("Update Failed");
			}
		return response;
	}
  
	public OTPCodeData insertOTPSession(OTPCodeReqData request,String phoneNo,String otpCode,Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		OTPCodeData res = new OTPCodeData();
		long l_RefKey = 0;
		int count_getOTP = 0;
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String sql = "INSERT INTO OTPSession(UserID,SessionID,OTPCode,Type,GetOTPDateTime,N1) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedstatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        preparedstatement.setString(i++,request.getUserID());
        preparedstatement.setString(i++,request.getSessionID());
        preparedstatement.setString(i++,otpCode);
        preparedstatement.setString(i++,request.getType());
        preparedstatement.setString(i++,date);
        preparedstatement.setInt(i++,++count_getOTP);
        preparedstatement.executeUpdate();
        ResultSet rs = preparedstatement.getGeneratedKeys();
        if (rs != null && rs.next()) {
            l_RefKey = rs.getLong(1);
        }
        if(l_RefKey != 0)
        {
        	res.setCode("0000");
        	res.setDesc("Inserted Successfully");
        	res.setrKey(String.valueOf(l_RefKey));
        }
        else
        {
        	res.setCode("0014");
        	res.setDesc("Insert Failed");
        	res.setrKey("");
        }
        rs.close();
        preparedstatement.close();

		return res;
	}
	
	public ResponseData checkOTPCode(String sessionID,String userID,String rKey,String otpCode,Connection conn) throws SQLException, ParseException{
		String sql = "";
		String query = "";
		int count = 0;
		int status = 0;
		ResponseData updateRes = new ResponseData();
		boolean timeflag = false;
		GeneralUtil util = new GeneralUtil();
		ResponseData response = new ResponseData(); 
		long referenceKey = Long.parseLong(rKey);
		String userid = "", sessionid = "", otpcode = "", getotpdtime = "";
		
			sql = "SELECT UserID,SessionID,OTPCode,GetOTPDateTime,Count FROM OTPSession WHERE AutoKey = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int i = 1;
		    pstmt.setLong(i++, referenceKey);
			ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				userid = result.getString("UserID");
				sessionid = result.getString("SessionID");
				otpcode = result.getString("OTPCode");
				getotpdtime = result.getString("GetOTPDateTime");
				count = result.getInt("Count");
			}
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			if(userid.equalsIgnoreCase(userID) && sessionid.equalsIgnoreCase(sessionID) && otpcode.equalsIgnoreCase(otpCode))
			{ 
				String dtime1 = getotpdtime.replace("-","");//getotp datetime
				String dtime2 = date.replace("-",""); // current datetime
				String otpsessionLimit = ConnAdmin.readExternalUrl("OTPSESSIONTIME");
				timeflag = util.checkTimeStamp(dtime1,dtime2,otpsessionLimit);
				if(timeflag == true)
				{
					status = 1;
				}
				else
				{
					status = 3;
				}
			}
			else
			{
				status = 2;
			}
			query = "UPDATE OTPSession SET CheckOTPDateTime = ?, Count = ?, Status = ? WHERE AutoKey = ?";
			PreparedStatement pstmt1 = conn.prepareStatement(query);
			int j = 1;
			pstmt1.setString(j++,date);
			pstmt1.setInt(j++,++count);
			pstmt1.setInt(j++,status);
			pstmt1.setLong(j++,referenceKey);
			int rs = pstmt1.executeUpdate();
			if(rs > 0 )
			{
				if(timeflag == true)
				{
					updateRes = updateStatusForFirstTimeLogin(userID,conn);
					if(updateRes.getCode().equalsIgnoreCase("0000")){
						response.setCode("0000");
						response.setDesc("Success");
					}
				}
				if(timeflag == false)
				{
					response.setCode("0014");
					response.setDesc("Failed");
				}
			}else{
				response.setCode("0014");
				response.setDesc("Failed");
			}
			
			
		return response;
	}
	
	public ResponseData updateStatusForFirstTimeLogin(String userId,Connection conn) throws SQLException{
		ResponseData res = new ResponseData();
		String query = "";
		query = "UPDATE FirstTimeLogin set Status = ? WHERE UserID = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		int i = 1;
		pstmt.setInt(i++, 1);
		pstmt.setString(i++, userId);
		int rst = pstmt.executeUpdate();
		if(rst > 0){
			res.setCode("0000");
		}else{
			res.setCode("0014");
		}
		return res;
	}
	
	public ResponseData checkuserIDandsessionID(String userId,String sessionId,Connection conn) throws SQLException{
		ResponseData res = new ResponseData();
		String query = "";
		int status = -1;
		query = "SELECT Status FROM TblSession WHERE UserID = ? AND SessionID = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		int i = 1;
		pstmt.setString(i++, userId);
		pstmt.setString(i++, sessionId);
		ResultSet rst = pstmt.executeQuery();
		while(rst.next()){
			status = rst.getInt("Status");
		}
		if(status == -1){
			res.setCode("0014");
			res.setDesc("Invalid UserID and SessionID");
		}else if(status > 0){
			res.setCode("0014");
			res.setDesc("Session Expired");
		}else{
			res.setCode("0000");
			res.setDesc("Success");
		}
		return res;
	}
}
