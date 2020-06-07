package com.nirvasoft.fi.mgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nirvasoft.fi.dao.Service001Dao;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.Menu;
import com.nirvasoft.fi.framework.Profile;
import com.nirvasoft.fi.framework.ServerSession;
import com.nirvasoft.fi.shared.MSigninRequestData;
import com.nirvasoft.fi.shared.MSigninResponseData;
import com.nirvasoft.fi.shared.ServerGlobal;
import com.nirvasoft.fi.util.FileUtil;
import com.nirvasoft.fi.util.ServerUtil;


public class Service001Mgr {
	public Menu[] getProfileSubMenuItem (Profile p, String parent)
	{
		Menu[] ary = null;
		Service001Dao dao = new Service001Dao();
		Connection con = ConnAdmin.getConn("001", "");
		ary = dao.getProfileSubMenuItem(p, con, parent);		
		return ary;		
		
	}

	public Profile login (Profile p) {
		Profile resProfile = new Profile();
		Service001Dao service_dao = new Service001Dao();
		Connection con = ConnAdmin.getConn("001", "");
		try {
			resProfile=service_dao.login(p, con);
			
		} catch (SQLException e) {
			resProfile.setCode("0000");
			resProfile.setDesc(e.getMessage());
		} finally {
			ServerUtil.closeConnection(con);
		}
		return resProfile;
	}
	
	public MSigninResponseData mobilelogin (MSigninRequestData mprofile){
		MSigninResponseData res = new MSigninResponseData();
		
		Connection con = null;
		Service001Dao service_dao = new Service001Dao();
		try {
			con = ConnAdmin.getConn("001", "");
			res=service_dao.mobilelogin(mprofile, con);
			return res;
		}catch(Exception e)
		{
			e.printStackTrace();
			res.setCode("0014");
			res.setDesc("Contact to Bank Administrator");
			
		}
		finally {
			
			ServerUtil.closeConnection(con);
			
		}
		return res;
	}
	
	public static String[] getMainMenu (Profile p) 
	{
		Connection con = ConnAdmin.getConn("001", "");
		ArrayList<String> arr = Service001Dao.getMainMenu(p,con);
		String[ ] ary = new String[arr.size()];
		for(int i=0;i<arr.size();i++)
		{
			ary[i]=arr.get(i);
		}
		
		return ary;		
		
	}
	
	public static String[] getSubMenuItem (Profile p, String parent)
	{
		Connection con = ConnAdmin.getConn("001", "");
		ArrayList<String> arr = Service001Dao.getSubMenuItem(p,con,parent);
		String[ ] ary = new String[arr.size()];
		for(int i=0;i<arr.size();i++)
		{
			ary[i]=arr.get(i);
		}
		
		return ary;		
		
	}
	

	/*//suwai
	public static String getMerchant(MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID(),"");
		String res = "";
		try {
			res = Service001Dao.getMerchant(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}*/
	
	/*wkk*/
	public static String getMerchant() {
		Connection conn = null;
		String res = null;
		
		try {
			conn = ConnAdmin.getConn("001","");
			res = Service001Dao.getMerchant(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return res;
	}

	public  Boolean checkForcePwdChange(MSigninRequestData p) {
		Boolean st= false;
		Service001Dao s_dao = new Service001Dao();
		Connection con = null;
		try {
			con = ConnAdmin.getConn("001", "");
			st= s_dao.checkForcePwdChange(p, con);
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(con);
		}
		return st;
	}
	
	public static void readDebugLogStatus() {
		try {
			//ServerSession.serverPath = request.getServletContext().getRealPath("/") + "/";
			String path = ServerSession.serverPath+"data//OtpConfig.txt";
			ArrayList<String> l_resultList = FileUtil.readList(path);
			
			if (l_resultList != null && l_resultList.size() > 0) {
				 System.out.println(l_resultList.get(0).split(":")[1]);
				if (l_resultList.get(0).split(":")[1].trim().equals("Yes")) {
					ServerGlobal.setWriteLog(true);
				} else {
					ServerGlobal.setWriteLog(false);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	}
	
