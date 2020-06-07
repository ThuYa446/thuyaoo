package com.nirvasoft.fi.mgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.nirvasoft.fi.dao.SessionDAO;
import com.nirvasoft.fi.dao.UserDao;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.shared.MSigninResponseData;
import com.nirvasoft.fi.shared.OTPCodeData;
import com.nirvasoft.fi.shared.OTPCodeReqData;
import com.nirvasoft.fi.shared.ResponseData;
import com.nirvasoft.fi.util.GeneralUtil;
import com.nirvasoft.fi.util.ServerUtil;

public class SessionMgr {
	public Result insertSession(String userId,int sessionType)  {

		Result res = new Result();
		Connection conn = null;
		SessionDAO s_DAO = new SessionDAO();
		conn = ConnAdmin.getConn("001", "");
		try {
			
			res = s_DAO.insertSession(userId, conn,sessionType);
		} catch (Exception e ) {
			res.setState(false);
			res.setMsgDesc(e.getMessage());
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;

	}


	public ResponseData validateID(String sessionId , String userId){
		ResponseData response = new ResponseData();
		if(sessionId.equalsIgnoreCase("null") || sessionId.equalsIgnoreCase("")){
			response.setCode("0014");
			response.setDesc("Session ID is mandatory");
		}else if(userId.equalsIgnoreCase("null") || userId.equalsIgnoreCase("")){
			response.setCode("0014");
			response.setDesc("User ID is mandatory");
		}else{
			response.setCode("0000");
			response.setDesc("Success");
		}
			return response;
	}
	
	public ResponseData updateActivityTime(String sessionId)  {

		ResponseData res = new ResponseData();
		Connection conn = null;
		SessionDAO s_DAO = new SessionDAO();
		try {
			conn = ConnAdmin.getConn("001", "");
			res = s_DAO.updateActivityTime(sessionId , conn);
		} catch (Exception e ) {
			res.setCode("0014");
			res.setDesc(e.getMessage());
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;

	}
	
	public ResponseData updateAndcheckActivityTime(String sessionId , String userId)  {

		ResponseData res = new ResponseData();
		Connection conn = null;
		SessionDAO s_DAO = new SessionDAO();
		conn = ConnAdmin.getConn("001", "");
		try {
			
			res = s_DAO.updateAndcheckActivityTime(sessionId , userId , conn);
		} catch (Exception e ) {
			res.setCode("0014");
			res.setDesc(e.getMessage());
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;

	}
	
	public Result logout(String sessionId)  {

		Result res = new Result();
		Connection conn = null;
		SessionDAO s_DAO = new SessionDAO();
		
		try {
			conn = ConnAdmin.getConn("001", "");
			res = s_DAO.logout(sessionId , conn);
		} catch (Exception e ) {
			res.setState(false);
			res.setMsgCode("0014");
			res.setMsgDesc(e.getMessage());
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;

	}
	
	//check user is FistTime login or not
	public Result checkFirstTimeLogin(String userId){
		Result res = new Result();
		Connection conn = null;
		SessionDAO s_DAO = new SessionDAO();
		try {
			conn = ConnAdmin.getConn("001", "");
			res = s_DAO.checkFirstTimeLogin(userId , conn);
		} catch (Exception e ) {
			res.setState(false);
			res.setMsgCode("0014");
			res.setMsgDesc(e.getMessage());
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}
	
	public Result checkOTPDuration(String sessionId)
	{
		Result res = new Result();
		SessionDAO session_dao = new SessionDAO();
		String getOTPTime = "";
		boolean timeflag = false;
		Connection conn = null;
		GeneralUtil util = new GeneralUtil();
		
		try{
			conn = ConnAdmin.getConn("001", "");
			getOTPTime = session_dao.getOTPGeneratedTime(conn,sessionId);
			if(getOTPTime.equalsIgnoreCase("") || getOTPTime.equalsIgnoreCase("null"))
			{
				res.setMsgCode("0000"); // first time getOTP request with new sessionID
			}
			else
			{
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				String dtime1 = getOTPTime.replace("-","");//getotp datetime
				String dtime2 = date.replace("-",""); // current datetime
				String otpsessionLimit = ConnAdmin.readExternalUrl("OTPSESSIONTIME");
				timeflag = util.checkTimeStamp(dtime1,dtime2,otpsessionLimit);
				if(timeflag == true)
				{
					res.setMsgCode("0001");//OTP Request within specified min
				}
				else
				{
					res.setMsgCode("0000"); 
				}
			}
		}
		 catch (Exception e) {
			 res.setMsgCode("0014");
			 res.setMsgDesc(e.getMessage());
	        }
		
		
		return res;
	}
	
	
	
	
	public String generateOTP()
	{
		String otpcode = "";
		String num_list = "0123456789";
		int length = 6;
		char ch;
		StringBuffer randStr =new StringBuffer();
		for (int i = 0; i < length; i++) {
			Random randomGenerator = new Random();
			int number = 0;
			number = randomGenerator.nextInt(num_list.length());
			ch = num_list.charAt(number);
			
			randStr.append(ch);
		}
		otpcode = String.valueOf(randStr);
		return otpcode;
	}
	
	public ResponseData validatecheckOTPReq(String sessionId , String userId,String rkey,String otpCode){
		ResponseData response = new ResponseData();
		if(sessionId.equalsIgnoreCase("null") || sessionId.equalsIgnoreCase("")){
			response.setCode("0014");
			response.setDesc("Session ID is mandatory");
		}else if(userId.equalsIgnoreCase("null") || userId.equalsIgnoreCase("")){
			response.setCode("0014");
			response.setDesc("User ID is mandatory");
		}
		else if(rkey.equalsIgnoreCase("null") || rkey.equalsIgnoreCase("")){
			response.setCode("0014");
			response.setDesc("Reference Key is mandatory");
		}
		else if(otpCode.equalsIgnoreCase("null") || otpCode.equalsIgnoreCase("")){
			response.setCode("0014");
			response.setDesc("OTP Code is mandatory");
		}
		else{
			response.setCode("0000");
			response.setDesc("Success");
		}
			return response;
	}
	
	public MSigninResponseData checkLogin(String userId,int sessionType) {
		MSigninResponseData res = new MSigninResponseData();
		SessionDAO s_dao = new SessionDAO();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			res = s_dao.checkLogin(userId , conn,sessionType);					
				
		}catch(Exception e){
			res.setCode("0014");
			res.setDesc(e.getMessage());
		}finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				res.setCode("0014");
				res.setDesc(e.getMessage());
			}
		}
		return res;		
	}
	
	
	public ResponseData checkOtpCode(String sessionid,String userid,String rkey,String otpcode) {
		ResponseData response = new ResponseData();
        SessionDAO session_dao = new SessionDAO();
		Connection conn = null;
		
        try {
        	conn = ConnAdmin.getConn("001", "");
        	response = session_dao.checkOTPCode(sessionid,userid,rkey,otpcode,conn);
        } catch (Exception e) {
            response.setCode("0014");
            response.setDesc("Failed");
        }
        return response;
    }
	

//check both userID and sessionID
	public ResponseData checkuserIDandsessionID(String userId,String sessionId){
		ResponseData res = new ResponseData();
		Connection conn = null;
		SessionDAO s_DAO = new SessionDAO();
		try {
			conn = ConnAdmin.getConn("001", "");
			res = s_DAO.checkuserIDandsessionID(userId,sessionId , conn);
		} catch (Exception e ) {
			res.setCode("0014");
			res.setDesc(e.getMessage());
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}
}
