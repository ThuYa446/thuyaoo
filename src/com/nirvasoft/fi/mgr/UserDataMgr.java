package com.nirvasoft.fi.mgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import com.nirvasoft.database.SysKeyMgr;
import com.nirvasoft.fi.dao.PersonDao;
import com.nirvasoft.fi.dao.UCJunctionDAO;
import com.nirvasoft.fi.dao.UserDao;
import com.nirvasoft.fi.dao.UserRoleViewDao;
import com.nirvasoft.fi.framework.ConnAdmin;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.framework.ServerSession;
import com.nirvasoft.fi.users.data.PersonData;
import com.nirvasoft.fi.users.data.UCJunctionArr;
import com.nirvasoft.fi.users.data.UserData;
import com.nirvasoft.fi.users.data.UserRoleViewData;
import com.nirvasoft.fi.users.data.UserViewData;
import com.nirvasoft.fi.users.data.UserViewDataArr;
import com.nirvasoft.fi.users.data.UserViewDataset;
import com.nirvasoft.fi.util.FileUtil;
import com.nirvasoft.fi.util.ServerUtil;

public class UserDataMgr {

	public static Result saveBranchUserData(UserData data) {

		Result res = new Result();
		Connection conn = null;
		String phoneNo = "";
		boolean flag = true;
		UserDao u_dao = new UserDao();
		try {
			conn = ConnAdmin.getConn("001", "");
			if (conn.equals("null") || conn.equals("")) {
				res.setState(false);
				res.setMsgDesc("Connection Fail");
			} else {
				data.getPerson().setSyskey(UserDao.getPersonSyskey(data.getSyskey(), conn));
				
				if (data.getPerson().getSyskey() == 0) {
					data.getPerson().setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));
					data = initData(data, conn);
					conn.setAutoCommit(false);
					res = PersonDao.insert(getPersonData(data), conn);
				} else {
					conn.setAutoCommit(false);
					data = initData(data, conn);
					res = PersonDao.updatePersonData(data, conn);
					// res = PersonDao.update(getPersonData(data), conn);
				}

				if (res.isState()) {
					res = normalizePhoneNoformat(data.getT4());
					if (res.isState() == false) {
						res.setMsgCode("0014");
						res.setMsgDesc("Phone No. is incorrect!");
						return res;
					} 
					data.setT4(res.getPhNo());
					if (data.getSyskey() == 0) {
						data.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));
						data.setN4(data.getPerson().getSyskey());
						System.out.println("Admin Merchant flat : "+ data.getN2());
						res = UserDao.insert(false, data, conn);
					} else {
						flag = u_dao.isPhDuplicate(data, conn);
						if(flag == false){
							flag = u_dao.isEmailDuplicate(data, conn);
							if(flag == false){
								System.out.println("Admin Merchant flat : "+ data.getN2());
								data.setN4(data.getPerson().getSyskey());
								res = UserDao.update(false, data, conn);
							}else{
								res.setState(false);
								res.setMsgCode("0014");
								res.setMsgDesc("Email already exist");
							}
							
						}else{
							res.setState(false);
							res.setMsgCode("0014");
							res.setMsgDesc("Phone No. already exist");
						}
						
					}

				}

				if (res.isState()) {

					res.setKeyResult(data.getSyskey());
					res.getLongResult().add(data.getSyskey());
					res.getLongResult().add(data.getPerson().getSyskey());
					res.setPhNo(data.getT4());
					conn.commit();
				}
			}
		} catch (SQLException e) {
			res.setState(false);
			res.setMsgDesc("Connection Fail");
			res.setMsgCode("0014");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;

	}

	public static PersonData getPersonData(UserData bdata) {

		PersonData adata = new PersonData();
		adata.setSyskey(bdata.getPerson().getSyskey());
		adata.setT1(bdata.getT1());
		adata.setT2(bdata.getName());
		adata.setT3(bdata.getState());
		adata.setT4(bdata.getCity());
		adata.setCreatedDate(bdata.getCreatedDate());
		adata.setModifiedDate(bdata.getModifiedDate());
		adata.setRecordStatus(bdata.getRecordStatus());
		adata.setSyncStatus(bdata.getSyncStatus());
		adata.setSyncBatch(bdata.getSyncBatch());
		adata.setUserId(bdata.getUserId());
		adata.setUserName(bdata.getUserName());
//		adata.setT7(bdata.getCustomerid());
		return adata;
	}

	public static UserData initData(UserData data, Connection con) {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		try {
			data.setUserName(getUserName(data.getUserId(), con));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (data.getSyskey() == 0) {
			data.setCreatedDate(date);
		}
		if (data.getCreatedDate().equals("")) {
			data.setCreatedDate(date);
		}

		data.setT2(ServerUtil.encryptPIN(data.getT2()));
		data.setModifiedDate(date);
		if (data.getRecordStatus() == 0) {
			data.setRecordStatus(1);
		} else {
			data.setRecordStatus(data.getRecordStatus());
		}
		data.setSyncBatch(0);
		data.setSyncStatus(1);

		return data;
	}

	// get Junction userlist
	public static UserViewDataArr getAllUCJunctionData(String searchText, int pageSize, int currentPage) {
		UserViewDataArr res = new UserViewDataArr();
		UserViewDataset dataSet = new UserViewDataset();
		Connection conn = ConnAdmin.getConn("001", "");
		try {
			dataSet = UserDao.getAllUCJunctionData(searchText, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}

		int startPage = (currentPage - 1) * pageSize;
		int endPage = pageSize + startPage;
		res.setTotalCount(dataSet.getArlData().size());
		dataSet.setArlData(new ArrayList<UserViewData>(dataSet.getArlData().subList(startPage,
				(endPage > dataSet.getArlData().size()) ? dataSet.getArlData().size() : endPage)));

		UserViewData[] dataarry = new UserViewData[dataSet.getArlData().size()];

		if (dataarry.length == 1) {
			dataarry = new UserViewData[dataSet.getArlData().size() + 1];
			dataarry[0] = dataSet.getArlData().get(0);
			dataarry[1] = new UserViewData();
		}

		for (int i = 0; i < dataSet.getArlData().size(); i++) {
			dataarry[i] = dataSet.getArlData().get(i);

		}

		res.setdata(dataarry);
		res.setSearchText(searchText);
		res.setCurrentPage(currentPage);
		res.setPageSize(pageSize);

		return res;
	}

	// get User setup list
	public UserViewDataArr getAllUserData(String searchText, int pageSize, int currentPage, String operation) {

		UserViewDataArr res = new UserViewDataArr();
		UserViewDataset dataSet = new UserViewDataset();
		UserDao u_dao = new UserDao();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			dataSet = u_dao.getAllUserData(searchText, conn, operation);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}

		int startPage = (currentPage - 1) * pageSize;
		int endPage = pageSize + startPage;
		res.setTotalCount(dataSet.getArlData().size());
		dataSet.setArlData(new ArrayList<UserViewData>(dataSet.getArlData().subList(startPage,
				(endPage > dataSet.getArlData().size()) ? dataSet.getArlData().size() : endPage)));
		UserViewData[] dataarry = new UserViewData[dataSet.getArlData().size()];
		if (dataarry.length == 1) {
			dataarry = new UserViewData[dataSet.getArlData().size() + 1];
			dataarry[0] = dataSet.getArlData().get(0);
			dataarry[1] = new UserViewData();
		}

		for (int i = 0; i < dataSet.getArlData().size(); i++) {
			dataarry[i] = dataSet.getArlData().get(i);

		}
		res.setdata(dataarry);
		res.setSearchText(searchText);

		res.setCurrentPage(currentPage);
		res.setPageSize(pageSize);

		return res;
	}

	public static UserData readBranchUserDataBySyskey(long pKey) {
		UserData res = new UserData();
		Connection conn = ConnAdmin.getConn("001", "");
		try {
			res = UserDao.readBranchUserDataBySyskey(pKey, conn);

			UserRoleViewData[] dataarray;
			dataarray = UserRoleViewDao.getUserRoleList(pKey, conn);
			long pvalue[] = UserRoleViewDao.getRoleResult(pKey, conn);
			String name = PersonDao.getUserName(pKey, conn);
			res.setName(name);
			res.setUserrolelist(dataarray);
			res.setRolesyskey(pvalue);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static Result deleteUserData(long syskey, String modifieduserId) {
		Result res = new Result();
		Connection conn = ConnAdmin.getConn("001", "");
		try {
			res = UserDao.delete(syskey, modifieduserId, conn);
		} catch (SQLException e) {
			res.setState(false);
			res.setMsgDesc("Cannot Delete");
		}

		return res;
	}

	public UserData readByUserID(String aUserID) {
		UserData res = new UserData();
		Connection conn = null;
		UserDao dao = new UserDao();
		try {
			conn = ConnAdmin.getConn("001", "");
			res = dao.readByUserID(aUserID, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}


	public ArrayList<Result> getAllAgent() {
		Connection conn = null;
		UserDao u_dao = new UserDao();
		ArrayList<Result> res = new ArrayList<Result>();

		try {
			conn = ConnAdmin.getConn("001", "");
			res = u_dao.getAllAgent(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<Result> getAllFeatures() {
		Connection conn = null;
		UserDao u_dao = new UserDao();
		ArrayList<Result> res = new ArrayList<Result>();

		try {
			conn = ConnAdmin.getConn("001", "");
			res = u_dao.getAllFeatures(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public Result activatedeactivateUserData(long syskey, String status, String userId) {
		Result res = new Result();
		Connection conn = null;
		String msg = "";
		UserDao u_dao = new UserDao();
		try {
			conn = ConnAdmin.getConn("001", "");
			res = u_dao.activateDeactivateUser(syskey, conn, status, userId);

			if (res.isState()) {
				if (status.equalsIgnoreCase("Activate")) {
					msg = "Activate Successfully.";
				} else if (status.equalsIgnoreCase("Deactivate")) {
					msg = "Deactivate Successfully.";
				}
			}
			else
			{				
				if (status.equalsIgnoreCase("Activate")) {
					msg = "Activate Failed.";
				} else if (status.equalsIgnoreCase("Deactivate")) {
					if(res.getMsgCode().equals("0014")){
						msg = res.getMsgDesc();
					}else{
						msg = "Deactivate Failed.";
					}					
				}
			}
			res.setMsgDesc(msg);
		} catch (SQLException e) {
			res.setState(false);
			res.setMsgDesc(e.getMessage());
		}

		return res;
	}

	public Result lockUnlockUserData(String userid, long syskey, String status) {
		Result res = new Result();
		Connection conn = null;
		String msg = "";
		UserDao u_dao = new UserDao();
		try {
			conn = ConnAdmin.getConn("001", "");
			res = u_dao.lockUnlockUser(userid, syskey, conn, status);
			if (res.isState()) {
				if (status.equalsIgnoreCase("Lock")) {
					msg = "Lock Successfully.";
				} else if (status.equalsIgnoreCase("Unlock")) {
					msg = "Unlock Successfully.";
				}
			}
			else
			{
				if (status.equalsIgnoreCase("Lock")) {
					if(res.getMsgCode().equals("0014")){
						msg = res.getMsgDesc();
					}else
					{
						msg = "Lock Failed.";
					}					
				} else if (status.equalsIgnoreCase("Unlock")) {
					msg = "Unlock Failed.";
				}
			}

			res.setMsgDesc(msg);
		} catch (SQLException e) {
			res.setState(false);
			res.setMsgDesc(e.getMessage());
		}

		return res;
	}

	public static UserViewDataArr getAllUserCIFDataList(String searchText, int pageSize, int currentPage) {
		UserViewDataArr res = new UserViewDataArr();
		UserViewDataset dataSet = new UserViewDataset();
		Connection conn = ConnAdmin.getConn("001", "");
		try {
			dataSet = UserDao.getAllUserCIFDataList(searchText, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		int startPage = (currentPage - 1) * pageSize;
		int endPage = pageSize + startPage;
		res.setTotalCount(dataSet.getArlData().size());
		dataSet.setArlData(new ArrayList<UserViewData>(dataSet.getArlData().subList(startPage,
				(endPage > dataSet.getArlData().size()) ? dataSet.getArlData().size() : endPage)));
		UserViewData[] dataarry = new UserViewData[dataSet.getArlData().size()];

		if (dataarry.length == 1) {
			dataarry = new UserViewData[dataSet.getArlData().size() + 1];
			dataarry[0] = dataSet.getArlData().get(0);
			dataarry[1] = new UserViewData();
		}

		for (int i = 0; i < dataSet.getArlData().size(); i++) {
			dataarry[i] = dataSet.getArlData().get(i);

		}

		res.setdata(dataarry);
		res.setSearchText(searchText);
		res.setCurrentPage(currentPage);
		res.setPageSize(pageSize);

		return res;
	}

	// zmth
	// Save User Profile
	/*public Result saveUserProfile(UserData data) {
		Result res = new Result();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			if (conn.equals("null") || conn.equals("")) {
				res.setState(false);
				res.setMsgDesc("Connection Fail");
			} else {

				res = normalizePhoneNoformat(data.getT4());
				if (res.isState() == false) {
					res.setMsgCode("0014");
					res.setMsgDesc("Phone No. is incorrect!");
				} else {

					data.setT4(res.getPhNo()); // Phone No

					data.getPerson().setSyskey(UserDao.getPersonSyskey(data.getSyskey(), conn));
					if (data.getPerson().getSyskey() == 0) {
						data.getPerson().setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));
						data.setT1(res.getPhNo());
						data = initData(data, conn);
						ServerUtil.closeConnection(conn);
						conn = ConnAdmin.getConn("001", "");
						res = PersonDao.insert(getPersonData(data), conn); // UVM012_A
					} else {
						data = initData(data, conn);
						ServerUtil.closeConnection(conn);
						conn = ConnAdmin.getConn("001", "");
						res = PersonDao.updatePersonData(data, conn);
						// res = PersonDao.update(getPersonData(data), conn);
					}

					if (res.isState()) {
						ServerUtil.closeConnection(conn);
						conn = ConnAdmin.getConn("001", "");
						if (data.getSyskey() == 0) {
							data.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));

							data.setN4(data.getPerson().getSyskey());
							data.setT2(ServerUtil.encryptPIN(UserDao.generatePassword()));
							data.setN2(1);

							res = UserDao.insert(true, data, conn); // UVM005_A
						} else {
							data.setN2(1);
							data.setN4(data.getPerson().getSyskey());
							res = UserDao.update(true, data, conn);
						}

					}

					if (res.isState()) {
						res.setKeyResult(data.getSyskey());
						res.getLongResult().add(data.getSyskey());
						res.getLongResult().add(data.getPerson().getSyskey());
					}
				}
			}

		} catch (SQLException e) {
			res.setState(false);
			res.setMsgDesc("Connection Fail");
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}*/
	
	public Result saveUserProfile(UserData data) {
		Result res = new Result();
		UserDao u_dao = new UserDao();
		UCJunctionDAO ucj_dao=new UCJunctionDAO();
		UCJunctionArr aData=new UCJunctionArr();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			if (conn.equals("null") || conn.equals("")) {
				res.setState(false);
				res.setMsgDesc("Connection Fail");
			} else {
				res = normalizePhoneNoformat(data.getT4());
				if (res.isState() == false) {
					res.setMsgCode("0014");
					res.setMsgDesc("Phone No. is incorrect!");
				} else {
					data.setT4(res.getPhNo()); // Phone No
					data.getPerson().setSyskey(UserDao.getPersonSyskey(data.getSyskey(), conn));
					if (data.getPerson().getSyskey() == 0) {
						/*res = u_dao.checkCustomerID(data.getData(),conn);
						if(res.isState() == false)
						{
							res.setMsgCode("0014");
							res.setMsgDesc("Customer ID already mapped");
							return res;
						}*/
						data.getPerson().setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));
						data.setT1(data.getT4());//Hla Min
						data = initData(data, conn);
						ServerUtil.closeConnection(conn);
						conn = ConnAdmin.getConn("001", "");
						aData.setUserID(data.getT4());
						aData.setData(data.getData());
						boolean response = ucj_dao.saveUCJunction(aData, conn);//UCJunction
						if(response){
							res = PersonDao.insert(getPersonData(data), conn); // UVM012_A
						}else{
							res.setMsgCode("0014");
							res.setMsgDesc("Save Failed");
							return res;
						}
						
					} else {
						res = u_dao.checkPhoneNo(data.getPerson().getSyskey(),data.getT4(),conn);
						if(res.isState() == false)
						{
							res.setMsgCode("0014");
							res.setMsgDesc("Registered Phone Number already exist");
							return res;
						}
						//data.setT1(data.getT4()); when phno and userid is equal
						data.setT1(data.getT1());//wcs
						
						/*res = u_dao.checkCustomerID(data.getPerson().getSyskey(),data.getData(),conn);
						if(res.isState() == false)
						{
							res.setMsgCode("0014");
							res.setMsgDesc("Customer ID already mapped");
							return res;
						}*/
						if(data.getN3() == 1){
							res = u_dao.checkParentAgent(data.getT1(), data.getParentID(), conn);
							if(res.isState() == false)
							{
								res.setMsgCode("0014");
								res.setMsgDesc("Does not allow sub agent.");
								return res;
							}
							
							res = u_dao.checkUpateSubAgent(data.getT1(), data.getParentID(), conn);
							if(res.isState() == false)
							{
								res.setMsgCode("0014");
								res.setMsgDesc("Does not allow sub agent.");
								return res;
							}
						}
						
						if(data.getN3() == 0){	
							res = u_dao.checkAgentParent(data.getT1(), conn);
							if(res.isState() == false)
							{
								res.setMsgCode("0014");
								res.setMsgDesc(res.getMsgDesc());
								return res;
							}
						}
						
						data = initData(data, conn);
						ServerUtil.closeConnection(conn);
						conn = ConnAdmin.getConn("001", "");
						aData.setUserID(data.getT1());//wcs
						aData.setData(data.getData());
						ucj_dao.saveUCJunction(aData, conn);//UCJunction
						res = PersonDao.updatePersonData(data, conn);
						// res = PersonDao.update(getPersonData(data), conn);
					}
					if (res.isState()) {
							ServerUtil.closeConnection(conn);
							conn = ConnAdmin.getConn("001", "");
							if (data.getSyskey() == 0) {
								data.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn("001", "")));
								data.setN4(data.getPerson().getSyskey());
								data.setT2(ServerUtil.encryptPIN(UserDao.generatePassword(conn)));
								data.setN2(1);

								res = UserDao.insert(true, data, conn); // UVM005_A
							} else {
								data.setN2(1);
								data.setN4(data.getPerson().getSyskey());
								res = UserDao.update(true, data, conn);
								if(res.isState()){
									UserDao dao = new UserDao();
									String loginID = "", phno = "";
									loginID = res.getLoginID();
									phno = res.getPhNo();
									res = dao.insertAgent(data, conn);
									if (res.isState()) {
										res.setMsgDesc("Updated Successfully");
									}
									res.setLoginID(loginID);
									res.setPhNo(phno);
									//System.out.println(phno+" New phone number");
									res.setParentID(data.getParentID());
									//System.out.println(loginID+" Login");
								}
							}

						}
						if (res.isState()) {
							res.setMsgDesc(res.getMsgDesc());
							res.setMsgCode("0000");
							res.setKeyResult(data.getSyskey());
							res.getLongResult().add(data.getSyskey());
							res.getLongResult().add(data.getPerson().getSyskey());
						}
					
				}
			}

		} catch (SQLException e) {
			res.setState(false);
			res.setMsgDesc("Connection Fail");
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}
	

	public static Result normalizePhoneNoformat(String phoneNo) {
		String chkPhno = "";
		Result res = new Result();

		if (phoneNo.substring(0, 2).equalsIgnoreCase("09")) {
			chkPhno = phoneNo.substring(2, phoneNo.length());
			res = checkPhoneNolength(chkPhno);

		} else if (phoneNo.substring(0, 2).equalsIgnoreCase("95")) {

			if (phoneNo.substring(2, 3).equalsIgnoreCase("9")) {
				chkPhno = phoneNo.substring(3, phoneNo.length());
			} else if (phoneNo.substring(2, 4).equalsIgnoreCase("09")) {
				chkPhno = phoneNo.substring(4, phoneNo.length());
			} else {
				chkPhno = phoneNo.substring(2, phoneNo.length());
			}

			res = checkPhoneNolength(chkPhno);

		} else if (phoneNo.substring(0, 1).equalsIgnoreCase("9")) {
			chkPhno = checklength(phoneNo);
			res = checkPhoneNolength(chkPhno);
		} else {
			chkPhno = phoneNo;
			res = checkPhoneNolength(chkPhno);
		}

		return res;
	}

	public static String checklength(String phNo) {
		String retphoneNo = "";
		if (phNo.length() == 9) {
			if (phNo.substring(1, 2).equalsIgnoreCase("4") || phNo.substring(1, 2).equalsIgnoreCase("3")) {
				retphoneNo = phNo.substring(1, phNo.length());
			} else {
				retphoneNo = phNo;
			}
		}
		if (phNo.length() == 10) {
			retphoneNo = phNo.substring(1, phNo.length());
		}
		if (phNo.length() == 8) {
			retphoneNo = phNo.substring(1, phNo.length());
		}
		return retphoneNo;
		
	}

	public static Result checkPhoneNolength(String phNo) {
		Result res = new Result();
		String countrycode = "959";
		String retphoneNo = "";

		if (phNo.length() >= 7 && phNo.length() <= 9) {
			retphoneNo = countrycode + phNo;
			res.setPhNo(retphoneNo);
			res.setState(true);
			res.setMsgCode("0000");
			res.setMsgDesc("Phone No. format is correct!");

		} else {
			res.setState(false);

		}

		return res;
	}
	// public Result saveUserProfile(UserData data) {
	// Result res = new Result();
	// Connection conn = null;
	// try {
	// conn = ConnAdmin.getConn("001", "");
	// if (conn.equals("null") || conn.equals("")) {
	// res.setState(false);
	// res.setMsgDesc("Connection Fail");
	// } else {
	// data.getPerson().setSyskey(UserDao.getPersonSyskey(data.getSyskey(),
	// conn));
	// if (data.getPerson().getSyskey() == 0) {
	// data.getPerson()
	// .setSyskey(
	// SysKeyMgr.getSysKey(1, "syskey",
	// ConnAdmin.getConn("001", "")));
	// data = initData(data,conn);
	// ServerUtil.closeConnection(conn);
	// conn = ConnAdmin.getConn("001", "");
	// res = PersonDao.insert(getPersonData(data), conn); // UVM012_A
	// } else {
	// data = initData(data,conn);
	// ServerUtil.closeConnection(conn);
	// conn = ConnAdmin.getConn("001", "");
	// res = PersonDao.updatePersonData(data, conn);
	//// res = PersonDao.update(getPersonData(data), conn);
	// }
	//
	// if (res.isState()) {
	// ServerUtil.closeConnection(conn);
	// conn = ConnAdmin.getConn("001", "");
	// if (data.getSyskey() == 0) {
	// data.setSyskey(SysKeyMgr.getSysKey(1, "syskey",
	// ConnAdmin.getConn("001", "")));
	//
	// data.setN4(data.getPerson().getSyskey());
	// data.setT2(ServerUtil.encryptPIN(UserDao.generatePassword()));
	// data.setN2(1);
	// res = UserDao.insert(true, data, conn); // UVM005_A
	// } else {
	// data.setN2(1);
	// data.setN4(data.getPerson().getSyskey());
	// res = UserDao.update(true, data, conn);
	// }
	//
	// }
	//
	// if (res.isState()) {
	//
	// res.setKeyResult(data.getSyskey());
	// res.getLongResult().add(data.getSyskey());
	// res.getLongResult().add(data.getPerson().getSyskey());
	//
	// }
	// }
	//
	// } catch (SQLException e) {
	// res.setState(false);
	// res.setMsgDesc("Connection Fail");
	// e.printStackTrace();
	// } finally {
	// ServerUtil.closeConnection(conn);
	// }
	// return res;
	// }


	public UserData getUserNameAndNrc(String userId) {
		UserData data = new UserData();
		UserDao u_dao = new UserDao();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			data = u_dao.getUserNameAndNrc(userId, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}
	
	public UserData getUserNameAndStatus(String userId) {
		UserData data = new UserData();
		UserDao u_dao = new UserDao();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			data = u_dao.getUserNameAndStatus(userId, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	public Result markPrinted(String userId) {
		Result res = new Result();
		Connection conn = null;
		String msg = "";
		UserDao u_dao = new UserDao();
		try {
			conn = ConnAdmin.getConn("001", "");
			res = u_dao.markPrinted(userId, conn);
			if (res.isState()) {
				msg = "Print";
			} else {
				msg = "Printed already once";
			}
			res.setMsgDesc(msg);
		} catch (SQLException e) {
			res.setMsgDesc(e.getMessage());
		}

		return res;
	}

	public Result forcedlogoutbyId(String userId,int type){
		// TODO Auto-generated method stub
		Result res = new Result();
		UserDao u_dao = new UserDao();
		Connection conn = ConnAdmin.getConn("001", "");
		try {
			res = u_dao.forcedlogoutbyId(userId,type, conn);
		}
		catch(Exception e)
		{
			res.setState(false);
			res.setMsgCode("0014");
			res.setMsgDesc(e.getMessage());
		}
		finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}
	
	
	public static String getUserName(String userid, Connection con) throws SQLException {
		String st = "";
		UserDao u_dao = new UserDao();
			st = u_dao.getUserName(userid, con);
			return st;
	}
	public String getSessionID() {
		String sessionID = "";
		String prefix = "S";
		Long key = 0L;
		try {
			key= SysKeyMgr.getSysKey(1, "", ConnAdmin.getConn("001", ""));
			sessionID = prefix + key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionID;
	}
	
	public Result getUserId(String phNo) {

		Result res = new Result();
		UserDao user_dao = new UserDao();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			if (conn.equals("null") || conn.equals("")) {
				res.setState(false);
				res.setMsgDesc("Connection Fail");
			}
			else
			{
				res = user_dao.getUserId(phNo, conn);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			res.setState(false);
			res.setMsgDesc(e.getMessage());
			
		}finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}
	
	public String getlasttimelogin(String userid){
		String lasttimelogin = "";
		UserDao u_dao = new UserDao();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			lasttimelogin = u_dao.getlasttimelogin(userid,conn);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			ServerUtil.closeConnection(conn);
		}
		return lasttimelogin;
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

	public String readOTPMsg()
	{
		String msg = "";
		String path = ServerSession.serverPath+"data//OtpConfig.txt";
		ArrayList<String> l_resultList = FileUtil.readList(path);
		
		if (l_resultList != null && l_resultList.size() > 0) {
			 System.out.println(l_resultList.get(0).split(":")[1]);
			 msg = l_resultList.get(0).split(":")[1];
		}
		return msg;
	}
	
	public String getPhoneNo(String userId){
		String phno = "";
		UserDao u_dao = new UserDao();
		Connection conn = null;
		try {
			conn = ConnAdmin.getConn("001", "");
			phno = u_dao.getPhoneNo(userId,conn);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			ServerUtil.closeConnection(conn);
		}
		return phno;
	}
	
	
	
}
