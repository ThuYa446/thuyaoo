package com.nirvasoft.fi.mgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nirvasoft.fi.dao.DAOManager;
import com.nirvasoft.fi.dao.UCJunctionDAO;
import com.nirvasoft.fi.dao.UserDao;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.users.data.UCJunctionArr;
import com.nirvasoft.fi.util.FileUtil;

public class UCJunctionMgr {
	
	public String getCusIDByLoginUser(String userid) {
		Connection conn = null;
		ArrayList<String> customerIDList = new ArrayList<String>();
		String customerid = "";
		UserDao dao = new UserDao();
		try {
			conn = ConnAdmin.getConn("001","");
			customerIDList = dao.getCustomerID(userid, conn);
			if(customerIDList.size() >= 1){
				if(customerIDList.size() == 1){
					customerid += "'" +customerIDList.get(0).toString()+  "'";
				} else {
					for(int i = 0; i < customerIDList.size()-1; i++){					
						customerid += "'" +customerIDList.get(i).toString()+  "',";					
					}
					customerid += "'" +customerIDList.get(customerIDList.size()-1).toString()+ "'";
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return customerid;		
	}
	
	public ArrayList<String> getAccountListByLoginID(String loginid) {
		Connection conn = null;
		ArrayList<String> AccountList = new ArrayList<String>();
		UserDao dao = new UserDao();
		try {
			conn = ConnAdmin.getConn("001", "");
			AccountList = dao.getActiveAcctListByLoginUserID(loginid, conn);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return AccountList;		
	}
	
	
	/*public UCJunctionArr getUCJunctionByUserID(String userID, MrBean user) {
		UCJunctionArr ret = new UCJunctionArr();
		ArrayList<UCJunction> ucList = new ArrayList<UCJunction>();
		UCJunction[] ucArr = null;
		
		Connection l_Conn = null;
		UCJunctionDAO l_DAO = new UCJunctionDAO();
		try {
			l_Conn = ConnAdmin.getConn(user.getUser().getOrganizationID(), "");
			ucList = l_DAO.getUCJunctionByUserID(userID, l_Conn);
			if(ucList.size() > 0){
				ucArr = new UCJunction[ucList.size()];
				for(int i=0;i<ucList.size();i++)
				{
					ucArr[i]=ucList.get(i);
				}				
			}
			ret.setUserID(userID);
			ret.setData(ucArr);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!l_Conn.isClosed())
					l_Conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}*/
	
	public UCJunctionArr saveUCJunction(UCJunctionArr aData) {
		//LOVSetupAllData ret = new LOVSetupAllData();
		DAOManager l_DAO = new DAOManager();
		Connection l_Conn = null;
		UCJunctionDAO l_UCDAO = new UCJunctionDAO();
		String id = "";
		long Hkey = 0;
		try {
			l_Conn = ConnAdmin.getConn("001", "");
			
				if (l_UCDAO.saveUCJunction(aData, l_Conn)) {					
					aData.setMessagecode("0000");
					aData.setMessagedesc(FileUtil.getMessageDescription(aData.getMessagecode()));
				} else {
					aData.setMessagecode("0050");
					aData.setMessagedesc(FileUtil.getMessageDescription(aData.getMessagecode()));
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			aData.setMessagedesc(e.getMessage());
		} finally {
			try {
				if (!l_Conn.isClosed())
					l_Conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				aData.setMessagedesc(e.getMessage());
			}
		}
		return aData;
	}
	
	public Result deleteUCJunction(String aUserID) {
		Result res = new Result();
		Connection l_Conn = null;
		UCJunctionDAO l_DAO = new UCJunctionDAO();
		try {
			l_Conn = ConnAdmin.getConn("001", "");
			if(l_DAO.delete(aUserID, l_Conn)){
				res.setState(true);
				res.setMsgCode("0002");
				res.setMsgDesc(FileUtil.getMessageDescription(res.getMsgCode()));
			} else {
				res.setState(false);
				res.setMsgCode("0052");
				res.setMsgDesc(FileUtil.getMessageDescription(res.getMsgCode()));
			}
		}  catch (Exception e) {			
			e.printStackTrace();
			res.setState(false);
			res.setMsgCode("0014");
			res.setMsgDesc(e.getMessage());
		} finally {
			try {
				if (!l_Conn.isClosed())
					l_Conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				res.setState(false);
				res.setMsgCode("0014");
				res.setMsgDesc(e.getMessage());
			}
		}

		return res;
	}
	
	

public Result deleteUserCIFJunction(String aUserID) {
	Result res = new Result();
	Connection l_Conn = null;
	UCJunctionDAO l_DAO = new UCJunctionDAO();
	try {
		l_Conn = ConnAdmin.getConn("001", "");
		if(l_DAO.deleteUserCIFJunction(aUserID, l_Conn)){
			res.setState(true);
			res.setMsgCode("0002");
			res.setMsgDesc(FileUtil.getMessageDescription(res.getMsgCode()));
		} else {
			res.setState(false);
			res.setMsgCode("0052");
			res.setMsgDesc(FileUtil.getMessageDescription(res.getMsgCode()));
		}
	}  catch (Exception e) {			
		e.printStackTrace();
		res.setState(false);
		res.setMsgCode("0014");
		res.setMsgDesc(e.getMessage());
	} finally {
		try {
			if (!l_Conn.isClosed())
				l_Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			res.setState(false);
			res.setMsgCode("0014");
			res.setMsgDesc(e.getMessage());
		}
	}

	return res;
}




}
