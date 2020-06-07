
package com.nirvasoft.fi.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.nirvasoft.fi.dao.DAOManager;
import com.nirvasoft.fi.dao.EmployeeTaxPayerDao;
import com.nirvasoft.fi.data.BranchData;
import com.nirvasoft.fi.framework.Bean001;
import com.nirvasoft.fi.framework.E001;
import com.nirvasoft.fi.framework.Lov3;
import com.nirvasoft.fi.framework.Menu;
import com.nirvasoft.fi.framework.Profile;
import com.nirvasoft.fi.framework.Ref;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.framework.ServerSession;
import com.nirvasoft.fi.mgr.EmployeeDataMgr;
import com.nirvasoft.fi.mgr.MenuDataMgr;
import com.nirvasoft.fi.mgr.RoleDataMgr;
import com.nirvasoft.fi.mgr.RoleMenuDataMgr;
import com.nirvasoft.fi.mgr.Service001Mgr;
import com.nirvasoft.fi.mgr.SessionMgr;
import com.nirvasoft.fi.mgr.UCJunctionMgr;
import com.nirvasoft.fi.mgr.UserDataMgr;
import com.nirvasoft.fi.mgr.UserRoleViewDataMgr;
import com.nirvasoft.fi.shared.BankData;
import com.nirvasoft.fi.shared.BankDataList;
import com.nirvasoft.fi.shared.BranchSetupDataSet;
import com.nirvasoft.fi.shared.MSigninResponseData;
import com.nirvasoft.fi.shared.OTPCodeData;
import com.nirvasoft.fi.shared.OTPCodeReqData;
import com.nirvasoft.fi.shared.PwdData;
import com.nirvasoft.fi.shared.ResponseData;
import com.nirvasoft.fi.shared.ServerGlobal;
import com.nirvasoft.fi.shared.TaxPaymentEmployee;
import com.nirvasoft.fi.shared.UserDetailReportList;
import com.nirvasoft.fi.shared.UserReportData;
import com.nirvasoft.fi.users.data.MenuData;
import com.nirvasoft.fi.users.data.MenuDataArr;
import com.nirvasoft.fi.users.data.MenuViewDataArr;
import com.nirvasoft.fi.users.data.RoleData;
import com.nirvasoft.fi.users.data.RoleDatas;
import com.nirvasoft.fi.users.data.RoleMenuData;
import com.nirvasoft.fi.users.data.UCJunctionArr;
import com.nirvasoft.fi.users.data.UserCIFJunctionArr;
import com.nirvasoft.fi.users.data.UserData;
import com.nirvasoft.fi.users.data.UserRoleViewData;
import com.nirvasoft.fi.users.data.UserViewDataArr;
import com.nirvasoft.fi.util.FileUtil;
import com.nirvasoft.fi.util.GeneralUtil;
import com.nirvasoft.fi.util.PrintFile;
import com.nirvasoft.fi.util.ServerUtil;
import com.nirvasoft.fi.util.hibernateUtils;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import mm.com.mit.tcis.data.ClientObjectImpl;
import mm.com.mit.tcis.data.ClientReturnObjectImpl;
import mm.com.mit.tcis.data.Constant;
import mm.com.mit.tcis.data.CreditorDownloadBodyImpl;
import mm.com.mit.tcis.data.DBResponeImpl;
import mm.com.mit.tcis.data.DebtorUploadBodyImpl;
import mm.com.mit.tcis.util.GeneralUtilities;

@Path("/service001")
public class Service001 {
	@Context
	HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	String UPLOAD_DIRECTORY = "";
	String FONT_DIRECTORY = "";
	String IMAGE_DIRECTORY = "";

	@javax.ws.rs.core.Context
	ServletContext context;
	public static String userid = "";
	public static String username = "";
	public static String userpsw = "";

	private void getPath() {
		ServerSession.serverPath = request.getServletContext().getRealPath("/") + "/";

	}

	@GET
	@Path("getMainList")
	@Produces(MediaType.APPLICATION_JSON)
	public MenuDataArr getmainlist() {
		getPath();
		MenuDataArr res = new MenuDataArr();
		res = MenuDataMgr.getmainmenulist();
		return res;
	}

	@POST
	@Path("signin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile signin(Profile p) {
		getPath();

		Profile resProfile = new Profile();
		Service001Mgr service_mgr = new Service001Mgr();
		if (p != null && p.getUserID() != null && p.getPassword() != null) {

			Result result1 = new Result();
			String sessionId = "";
			MSigninResponseData result = new MSigninResponseData();
			SessionMgr session_mgr = new SessionMgr();

			resProfile = service_mgr.login(p);
			int sessionType = 2;

			if (resProfile.getCode().equalsIgnoreCase("0014"))// Invalid
			{
				p.setCode("0014");
				p.setDesc("Invalid Email/Phone No. or Password");
				p.setRole(0);
			} else {

				result = session_mgr.checkLogin(resProfile.getUserID(), sessionType);

				if (result.getCode().equalsIgnoreCase("0000")) {
					result1 = session_mgr.insertSession(resProfile.getUserID(), sessionType);
					if (!result1.isState()) {
						p.setCode("0014");
						p.setUserID(resProfile.getUserID());
						p.setDesc("User already logged in");
						p.setRole(0);
						return p;
					}
					sessionId = result1.getSessionID();
					p.setSessionID(sessionId);
					p.setUserID(resProfile.getUserID());
					p.setTaxOffice(resProfile.getTaxOffice());

					String[] a2 = Service001Mgr.getMainMenu(p);
					if (a2.length > 0)
						p.setMenus(getProfileMainMenu(a2, p));
					p.setRightMenus(getProfileRightMenu());
					p.setUserName(resProfile.getUserName());

					p.setCode("0000");
					p.setDesc("Login Success");
				} else {
					p.setCode("0014");
					p.setUserID(resProfile.getUserID());
					p.setDesc("User already logged in");
					p.setRole(0);
				}
			}

		} else

		{
			p.setRole(0);
			p.setCode("0014");
			p.setDesc("Invalid Email/Phone No. or Password");
		}
		return p;
	}

	@GET
	@Path("method001")
	@Produces(MediaType.APPLICATION_JSON)
	public Bean001 test1() {
		Bean001 res = new Bean001();
		return res;
	}

	@GET
	@Path("gete001")
	@Produces(MediaType.APPLICATION_JSON)
	public E001 getE001() {
		E001 res = new E001();
		return res;
	}

	@POST
	@Path("poste001")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public E001 postE001(E001 p) {
		p.setT1("T1POST");
		p.getUdf()[0].setValue("UDF1POST");
		return p;
	}

	@GET
	@Path("getmenulist")
	@Produces(MediaType.APPLICATION_JSON)
	public MenuViewDataArr getMenulist(@QueryParam("searchVal") String searchText, @QueryParam("pagesize") int pageSize,
			@QueryParam("currentpage") int currentPage, @QueryParam("sessionID") String sessionId) {
		getPath();
		MenuViewDataArr res = new MenuViewDataArr();
		ResponseData sessionRes = new ResponseData();
		SessionMgr session_mgr = new SessionMgr();

		sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			res = MenuDataMgr.getAllMenuData(searchText, pageSize, currentPage);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}

		return res;
	}

	@POST
	@Path("getMenuData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MenuData readMenuDataBySyskey(String skey, @QueryParam("sessionID") String sessionId) {

		getPath();
		MenuData res = new MenuData();
		SessionMgr session_mgr = new SessionMgr();
		ResponseData sessionRes = new ResponseData();

		skey = skey.substring(1, skey.length() - 1);
		long syskey = Long.parseLong(skey);

		sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			res = MenuDataMgr.readDataBySyskey(syskey);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}

		return res;
	}

	@POST
	@Path("saveMenu")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Result saveMenu(MenuData data) {
		getPath();
		Result res = new Result();
		ResponseData sessionRes = new ResponseData();
		SessionMgr session_mgr = new SessionMgr();

		sessionRes = session_mgr.updateActivityTime(data.getSessionId());
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			res = MenuDataMgr.saveMenuData(data);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}

		return res;
	}

	@POST
	@Path("deleteMenu")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Result deleteMenuData(MenuData meu) {

		getPath();
		Result res = new Result();
		ResponseData sessionRes = new ResponseData();
		SessionMgr session_mgr = new SessionMgr();

		String skey = meu.get_key();
		String isParent = meu.getIsParent();
		long syskey = Long.parseLong(skey);

		sessionRes = session_mgr.updateActivityTime(meu.getSessionId());
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			res = MenuDataMgr.deleteMenuData(syskey, isParent);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}

		return res;
	}

	@GET
	@Path("getRoleMenuList")
	@Produces(MediaType.APPLICATION_JSON)
	public RoleData getRoleMenuList(@QueryParam("sessionID") String sessionId) {
		getPath();
		RoleMenuData[] dataarray;
		RoleData res = new RoleData();
		ResponseData sessionRes = new ResponseData();
		SessionMgr session_mgr = new SessionMgr();

		sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			dataarray = RoleMenuDataMgr.getRoleMenuList();
			res.setMenu(dataarray);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}
		return res;
	}

	@POST
	@Path("getSearchRole")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RoleDatas getSerachRole() {
		String search = request.getParameter("searchVal");
		getPath();
		RoleDatas res = RoleDataMgr.getAllRoleData();
		return res;
	}

	public Menu[] getProfileMainMenu(String[] promenu, Profile p) {
		Menu[] menus = new Menu[promenu.length];
		Menu obj = new Menu();
		Service001Mgr mgr = new Service001Mgr();

		if (promenu.length == 1)
			menus = new Menu[promenu.length + 1];

		for (int i = 0; i < promenu.length; i++) {
			obj = new Menu();
			obj.setMenuItem("");
			obj.setCaption(promenu[i]);
			getPath();
			/*
			 * String[] arr = Service001Mgr.getSubMenuItem(p, promenu[i]);
			 * Menu[] menuitems = new Menu[arr.length]; menuitems =
			 * getProfileSubMenuItem(arr); obj.setMenuItems(menuitems); menus[i]
			 * = obj;
			 */
			Menu[] menuitems = null;
			menuitems = mgr.getProfileSubMenuItem(p, promenu[i]);
			obj.setMenuItems(menuitems);
			menus[i] = obj;
		}

		return menus;

	}

	public Menu[] getProfileSubMenuItem(String[] promenu) {
		Menu[] items = new Menu[promenu.length];
		Menu subobj = new Menu();

		if (promenu.length == 1)
			items = new Menu[promenu.length + 1];

		for (int j = 0; j < promenu.length; j++) {
			subobj = new Menu();
			subobj.setMenuItem(promenu[j]);
			subobj.setCaption(promenu[j]);
			items[j] = subobj;

		}
		return items;

	}

	public Menu[] getProfileRightMenu() {

		Menu[] right = new Menu[2];
		Menu obj = new Menu();
		obj.setMenuItem("Login");
		obj.setCaption("Sign Out");
		right[0] = obj;
		return right;
	}

	@POST
	@Path("deleteUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Result deleteUserData(String skey, @QueryParam("id") String modifieduserId,
			@QueryParam("sessionID") String sessionId) {

		getPath();
		Result res = new Result();
		SessionMgr session_mgr = new SessionMgr();
		ResponseData sessionRes = new ResponseData();

		skey = skey.substring(1, skey.length() - 1);
		long syskey = 0;

		sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			syskey = Long.parseLong(skey);
			res = UserDataMgr.deleteUserData(syskey, modifieduserId);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}
		return res;

	}

	@GET
	@Path("getUserListing")
	@Produces(MediaType.APPLICATION_JSON)
	public UserViewDataArr getUserlisting(@QueryParam("searchVal") String searchText,
			@QueryParam("pagesize") int pageSize, @QueryParam("currentpage") int currentPage,
			@QueryParam("operation") String operation, @QueryParam("sessionID") String sessionId) {
		getPath();
		UserViewDataArr res = new UserViewDataArr();
		UserDataMgr u_mgr = new UserDataMgr();
		SessionMgr session_mgr = new SessionMgr();
		ResponseData sessionRes = new ResponseData();

		sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			res = u_mgr.getAllUserData(searchText, pageSize, currentPage, operation);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}
		return res;
	}

	@POST
	@Path("getBranchUserData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserData readBranchUserDataBySyskey(String skey, @QueryParam("sessionID") String sessionID) {

		UserData res = new UserData();
		skey = skey.substring(1, skey.length() - 1);
		long syskey = Long.parseLong(skey);
		getPath();
		SessionMgr s_mgr = new SessionMgr();
		ResponseData response = new ResponseData();
		response = s_mgr.updateActivityTime(sessionID);
		if (response.getCode().equals("0000")) {
			res = UserDataMgr.readBranchUserDataBySyskey(syskey);
		} else {
			res.setMsgCode("0016");
			res.setMsgDesc(response.getDesc());
		}

		return res;
	}

	@GET
	@Path("getUserRolelist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserData getRolelist() {

		UserRoleViewData[] dataarray;
		UserData res = new UserData();
		getPath();
		dataarray = UserRoleViewDataMgr.getUserRoleList();
		res.setUserrolelist(dataarray);
		return res;

	}

	@GET
	@Path("getRoleList")
	@Produces(MediaType.APPLICATION_JSON)
	public RoleDatas getRoleList(@QueryParam("searchVal") String searchText, @QueryParam("pagesize") int pageSize,
			@QueryParam("currentpage") int currentPage, @QueryParam("sessionID") String sessionId) {
		getPath();
		RoleDatas res = new RoleDatas();
		RoleDataMgr r_mgr = new RoleDataMgr();
		ResponseData sessionRes = new ResponseData();
		SessionMgr session_mgr = new SessionMgr();

		sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			res = r_mgr.getAllRoleListData(searchText, pageSize, currentPage);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}

		return res;
	}

	@POST
	@Path("saveRole")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Result saveRole(RoleData data) {

		getPath();
		Result res = new Result();
		SessionMgr session_mgr = new SessionMgr();
		ResponseData sessionRes = new ResponseData();

		sessionRes = session_mgr.updateActivityTime(data.getSessionId());
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			if (data.getParentsyskey() != null && data.getParentsyskey().length > 0) {

				res = RoleDataMgr.saveRoleData(data);

			} else {
				res.setMsgDesc("Please select menu");
			}
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}
		return res;
	}

	@POST
	@Path("deleteRole")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Result deleteRoleData(String skey, @QueryParam("sessionID") String sessionId) {

		getPath();
		Result res = new Result();
		ResponseData sessionRes = new ResponseData();
		SessionMgr session_mgr = new SessionMgr();

		skey = skey.substring(1, skey.length() - 1);
		long syskey = 0;
		syskey = Long.parseLong(skey);

		sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			res = RoleDataMgr.deleteRoleData(syskey);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}

		return res;
	}

	@GET
	@Path("readBySKRole")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RoleData readDataBySyskeyr() {
		RoleData ret;

		long pKey = Long.parseLong(request.getParameter("syskey"));
		getPath();
		ret = RoleDataMgr.readDataBySyskey(pKey);
		return ret;
	}

	@POST
	@Path("getRoleData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RoleData readRoleDataBySyskey(String skey, @QueryParam("sessionID") String sessionId) {

		getPath();
		RoleData res = new RoleData();
		SessionMgr session_mgr = new SessionMgr();
		ResponseData sessionRes = new ResponseData();

		skey = skey.substring(1, skey.length() - 1);
		long syskey = Long.parseLong(skey);

		sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			res = RoleDataMgr.readDataBySyskey(syskey);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}

		return res;
	}

	@POST
	@Path("saveUBJunction")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UCJunctionArr saveUBJunction(UCJunctionArr aData) {
		DAOManager.AbsolutePath = context.getRealPath("");
		UCJunctionMgr dbMgr = new UCJunctionMgr();
		getPath();
		aData = dbMgr.saveUCJunction(aData);
		return aData;
	}

	@POST
	@Path("deleteUCJunction")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Result deleteUCJunction(String aUserID) {
		Result res = new Result();
		UCJunctionMgr ucMgr = new UCJunctionMgr();
		aUserID = aUserID.substring(1, aUserID.length() - 1);
		try {
			getPath();
			res = ucMgr.deleteUCJunction(aUserID);
		} catch (Exception e) {
			res.setMsgDesc("Cannot Delete");
		}
		return res;

	}

	@GET
	@Path("getUserList")
	@Produces(MediaType.APPLICATION_JSON)
	public UserViewDataArr getUserlist(@QueryParam("searchVal") String searchText, @QueryParam("pagesize") int pageSize,
			@QueryParam("currentpage") int currentPage) {

		UserViewDataArr res = new UserViewDataArr();
		getPath();
		res = UserDataMgr.getAllUCJunctionData(searchText, pageSize, currentPage);
		return res;
	}

	@POST
	@Path("saveUserCFIJunction")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public UserCIFJunctionArr saveUserCIFJunction(UserCIFJunctionArr data) {
		UserCIFJunctionArr res = new UserCIFJunctionArr();
		DAOManager.AbsolutePath = context.getRealPath("");
		UCJunctionMgr dbMgr = new UCJunctionMgr();
		getPath();
		// res = dbMgr.saveUserCFIJunction(data);
		return res;
	}

	@POST
	@Path("deleteUserCIFJunction")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Result deleteUserCIFJunction(String aUserID) {
		Result res = new Result();
		UCJunctionMgr ucMgr = new UCJunctionMgr();
		aUserID = aUserID.substring(1, aUserID.length() - 1);
		try {
			getPath();
			res = ucMgr.deleteUserCIFJunction(aUserID);
		} catch (Exception e) {
			res.setMsgDesc("Cannot Delete");
		}
		return res;

	}

	@GET
	@Path("getUserCIFList")
	@Produces(MediaType.APPLICATION_JSON)
	public UserViewDataArr getUserCIFList(@QueryParam("searchVal") String searchText,
			@QueryParam("pagesize") int pageSize, @QueryParam("currentpage") int currentPage) {
		UserViewDataArr res = new UserViewDataArr();
		getPath();
		res = UserDataMgr.getAllUserCIFDataList(searchText, pageSize, currentPage);
		return res;
	}

	@GET
	@Path("getUCJunctionByUserID")
	@Produces(MediaType.APPLICATION_JSON)
	public UserCIFJunctionArr getUCJunctionByUserID() {
		UserCIFJunctionArr res = new UserCIFJunctionArr();
		DAOManager.AbsolutePath = context.getRealPath("");
		getPath();
		// res = UserMgr.getUCJunctionByUserID(userID);
		return res;
	}

	public String otp() {
		long rdm = 100000 + (int) (Math.random() * 900000);

		return "" + rdm;
	}

	@POST
	@Path("saveBranchUser")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Result saveBranchUser(UserData data) {

		getPath();
		Result res = new Result();
		SessionMgr session_mgr = new SessionMgr();
		ResponseData sessionRes = new ResponseData();

		sessionRes = session_mgr.updateActivityTime(data.getSessionId());
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			if (data.getRolesyskey() != null && data.getRolesyskey().length != 0) {
				res = UserDataMgr.saveBranchUserData(data);
			} else {
				res.setState(false);
				res.setMsgCode("0001");
				res.setMsgDesc("Please select Role");
			}

		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}
		return res;
	}

	// get create user data
	@POST
	@Path("getUserProfileData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserData readUserProfileDataBySyskey(String skey, @QueryParam("sessionID") String sessionId) {

		getPath();
		UserData res = new UserData();
		SessionMgr session_mgr = new SessionMgr();
		ResponseData sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			skey = skey.substring(1, skey.length() - 1);
			long syskey = Long.parseLong(skey);

			// res = UserDataMgr.readUserProfileDataBySyskey(syskey);
		} else

		{
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}

		return res;
	}

	// save User Profile
	@POST
	@Path("saveUserProfile")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Result saveUserProfile(UserData data) {
		getPath();
		UserDataMgr Mgr = new UserDataMgr();
		Result res = new Result();
		ResponseData sessionRes = new ResponseData();
		SessionMgr session_mgr = new SessionMgr();

		sessionRes = session_mgr.updateActivityTime(data.getSessionId());
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {

			res = Mgr.saveUserProfile(data);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}

		return res;

	}

	@GET
	@Path("printPassword")
	@Produces(MediaType.TEXT_PLAIN)
	public String printPassword() {
		DAOManager.AbsolutePath = context.getRealPath("");
		String password = request.getParameter("password");
		getPath();
		PrintFile pf = new PrintFile();

		try {
			String path = context.getRealPath("");

			pf.printPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String rt = "Print Password";
		return rt;
	}

	@GET
	@Path("signout")
	@Produces(MediaType.APPLICATION_JSON)
	public Result signout(@QueryParam("sessionid") String sessionId) {
		getPath();
		Result res = new Result();
		SessionMgr session_mgr = new SessionMgr();
		res = session_mgr.logout(sessionId);

		return res;
	}

	// for forced log out --start

	@GET
	@Path("getUserNameAndStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public UserData getUserNameAndStatus(@QueryParam("id") String Id, @QueryParam("sessionID") String sessionId) {

		getPath();
		UserData data = new UserData();
		UserDataMgr u_mgr = new UserDataMgr();
		SessionMgr session_mgr = new SessionMgr();
		ResponseData sessionRes = new ResponseData();

		sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			data = u_mgr.getUserNameAndStatus(Id);
		} else {
			data.setMsgCode(sessionRes.getCode());
			data.setMsgDesc(sessionRes.getDesc());
		}

		return data;
	}

	@GET
	@Path("forcedlogoutbyId")
	@Produces(MediaType.APPLICATION_JSON)
	public Result forcedlogoutbyId(@QueryParam("userId") String userId, @QueryParam("sessionID") String sessionId,
			@QueryParam("type") String type) {
		getPath();
		Result res = new Result();
		UserDataMgr u_mgr = new UserDataMgr();
		SessionMgr session_mgr = new SessionMgr();
		ResponseData sessionRes = new ResponseData();
		int sessiontype = Integer.parseInt(type);

		sessionRes = session_mgr.updateActivityTime(sessionId);
		if (sessionRes.getCode().equalsIgnoreCase("0000")) {
			res = u_mgr.forcedlogoutbyId(userId, sessiontype);
		} else {
			res.setMsgCode(sessionRes.getCode());
			res.setMsgDesc(sessionRes.getDesc());
		}

		return res;
	}

	// for forced log out --end

	@GET
	@Path("checkSession")
	@Produces(MediaType.APPLICATION_JSON)
	public Result checkSession(@QueryParam("sessionID") String sessionId) {
		Result res = new Result();
		ResponseData sessionRes = new ResponseData();
		SessionMgr mgr = new SessionMgr();
		getPath();
		sessionRes = mgr.updateActivityTime(sessionId);
		res.setMsgCode(sessionRes.getCode());
		res.setMsgDesc(sessionRes.getDesc());
		return res;
	}

	// Water Bill Excel Import //
	// ---- Excel Import --- //
	@POST
	@Path("excelImport")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Result excelImport(@QueryParam("f") String filePath, @QueryParam("fn") String inputFileName) {

		DAOManager.AbsolutePath = context.getRealPath("");

		Result res = new Result();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		filePath = context.getRealPath("") + "/uploadexcel";
		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdirs();

		filePath += "/" + inputFileName;

		File l_file = new File(filePath);
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {

				if (l_file.exists()) {
					try {
						l_file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (l_file.createNewFile()) {
					item.write(l_file);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@GET
	@Path("downloadType")
	@Produces(MediaType.TEXT_PLAIN)
	public String downloadType() {
		DAOManager.AbsolutePath = context.getRealPath("");

		try {
			String path = context.getRealPath("") + "/download";
			String fileName = "WaterBillTemplate.xls";
			FileUtil.downloadFile(path, fileName, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		String rt = "Template Water Bill";
		return rt;
	}

	@POST
	@Path("uploadPhoto")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public void uploadPhoto(@QueryParam("f") String filePath, @QueryParam("fn") String inputFileName) {
		// Result res = new Result(inputFileName, null);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		DAOManager.AbsolutePath = context.getRealPath("");
		filePath = request.getServletContext().getRealPath("/") + "/" + filePath; // save

		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdirs();

		filePath += "/" + inputFileName;

		File l_file = new File(filePath);
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {

				if (l_file.exists()) {
					try {
						l_file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (l_file.createNewFile()) {
					item.write(l_file);
				}
			}
			// UploadMgr.savePhoto(l_file,merchantid,fieldCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return res;
	}

	private boolean isExit(String fileName, String absolutePath) {
		String[] fileList = new File(absolutePath).list();
		if (fileList == null) {
			return false;
		}
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].equals(fileName))
				return true;
		}
		return false;
	}

	private boolean deleteFile(String absolutePath) {
		try {
			Files.deleteIfExists(Paths.get(absolutePath));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// ,@FormDataParam("obj") MBPaidObjImpl cmp
	@POST
	@Path("/uploadForImportExcel")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadForImportExcel(@FormDataParam("file") InputStream fis,
			@FormDataParam("file") FormDataContentDisposition fdcd) throws Exception {
		String orgname = "uploads";
		File theDir = new File(request.getServletContext().getRealPath("/" + orgname));
		if (!theDir.exists()) {
			try {
				boolean success = theDir.mkdir();
				if (success) {
					File childDir = new File(request.getServletContext().getRealPath("/" + orgname));
					if (!childDir.exists()) {
						try {
							childDir.mkdir();
						} catch (SecurityException se) {
							// handle it
							se.printStackTrace();
						}
					}
				}
			} catch (SecurityException se) {
				// handle it
				se.printStackTrace();
			}
		} else {
			File childDir = new File(request.getServletContext().getRealPath("/" + orgname));
			if (!childDir.exists()) {
				try {
					childDir.mkdir();
				} catch (SecurityException se) {
					// handle it
					se.printStackTrace();
				}
			}
		}

		OutputStream outpuStream = null;
		String fileName = fdcd.getFileName();
		String response = "";
		String basename = FilenameUtils.getBaseName(fileName);
		basename = basename.replaceAll("\\s+", "");
		String extension = FilenameUtils.getExtension(fileName);
		String fullname = basename + "." + extension;
		String absolutePath = request.getServletContext().getRealPath("/" + orgname + "/");
		String filePath = request.getServletContext().getRealPath("/" + orgname + "/" + fullname);
		try {

			if (!isExit(fullname, absolutePath)) {
				int read = 0;
				byte[] bytes = new byte[1024];
				outpuStream = new FileOutputStream(new File(filePath));
				while ((read = fis.read(bytes)) != -1) {
					outpuStream.write(bytes, 0, read);
				}
				outpuStream.flush();
				outpuStream.close();
				if (EmployeeDataMgr.saveEmployeeData(filePath)) {
					response = "Save Successfully!";
				} else {
					if (this.deleteFile(filePath))
						response = "Save Failed!";
				}
			} else {
				response = "File Already Exist!";
			}
		} catch (IOException iox) {
			iox.printStackTrace();
		} finally {
			if (outpuStream != null) {
				try {
					outpuStream.close();
				} catch (Exception ex) {
				}
			}
		}
		return response;
	}
	
	@GET
	@Path("searchText")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaxPaymentEmployee> getSearchList(@QueryParam("text") String text){
		List<TaxPaymentEmployee> txpEmpList = null;
		EntityManager entityManager = hibernateUtils.getEntityManager();
		txpEmpList = EmployeeTaxPayerDao.getTXPEmpByTextSearch(entityManager, text);
		return txpEmpList;
	}
	// for mobile service

	@POST
	@Path("mbsignin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile mbsignin(Profile p) {
		getPath();
		GeneralUtil.readDebugLogStatus();
		// Request log
		if (ServerGlobal.isWriteLog()) {
			ArrayList<String> l_err = new ArrayList<String>();
			l_err.add("");
			l_err.add("=============================================================================");
			l_err.add("Time :" + GeneralUtil.getTime());
			l_err.add("Signin Request Message.... : " + p.getUserID() + ":" + ServerUtil.encryptPIN(p.getPassword())
					+ ":" + p.getUserType());
			l_err.add("-----------------------------------------------------------------------------");
			GeneralUtil.writeLog(l_err, "\\Mobile\\log\\");
		}
		Profile resProfile = new Profile();
		Service001Mgr service_mgr = new Service001Mgr();
		int sessionType = 1;

		if (p != null && p.getUserID() != null && p.getPassword() != null) {

			Result result1 = new Result();
			Result resFirstTime = new Result();
			String sessionId = "";
			MSigninResponseData result = new MSigninResponseData();
			SessionMgr session_mgr = new SessionMgr();
			resProfile = service_mgr.login(p);
			if (resProfile.getCode().equalsIgnoreCase("0014"))// Invalid
			{
				p.setCode("0014");
				p.setDesc("Invalid Email/Phone No. or Password");
				p.setRole(0);
			} else {

				result = session_mgr.checkLogin(resProfile.getUserID(), sessionType);

				if (result.getCode().equalsIgnoreCase("0000")) {
					resFirstTime = session_mgr.checkFirstTimeLogin(resProfile.getUserID());
					if (resFirstTime.getMsgCode().equalsIgnoreCase("0001")) {
						p.setOTPcode(false);
					} else {

						p.setOTPcode(true);
					}
					result1 = session_mgr.insertSession(resProfile.getUserID(), sessionType);
					if (!result1.isState()) {
						p.setUserID(resProfile.getUserID());
						p.setCode("0014");
						p.setDesc("User already logged in");
						p.setRole(0);
						// Response log
						if (ServerGlobal.isWriteLog()) {
							p.setPassword(ServerUtil.encryptPIN(p.getPassword()));
							ArrayList<String> l_err = new ArrayList<String>();
							l_err.add("Time :" + GeneralUtil.getTime());
							l_err.add("signin Response Message.... :" + p.toString());
							l_err.add("=============================================================================");

							GeneralUtil.writeLog(l_err, "\\Mobile\\log\\");
						}

						return p;
					}
					sessionId = result1.getSessionID();
					p.setSessionID(sessionId);
					p.setUserID(resProfile.getUserID());
					p.setPhoneno(resProfile.getPhoneno());
					p.setUserName(resProfile.getUserName());
					p.setCode("0000");
					p.setDesc("Login Success");
				} else {
					p.setUserID(resProfile.getUserID());
					p.setCode("0014");
					p.setDesc("User already logged in");
					p.setRole(0);
				}
			}

		} else

		{
			p.setRole(0);
			p.setCode("0014");
			p.setDesc("Invalid User ID or Password");
		}
		// Response log
		if (ServerGlobal.isWriteLog()) {
			p.setPassword(ServerUtil.encryptPIN(p.getPassword()));
			ArrayList<String> l_err = new ArrayList<String>();
			l_err.add("Time :" + GeneralUtil.getTime());
			l_err.add("signin Response Message.... :" + p.toString());
			l_err.add("=============================================================================");

			GeneralUtil.writeLog(l_err, "\\Mobile\\log\\");
		}

		return p;
	}

	public ResponseData validateID(String sessionId, String userId) {
		ResponseData response = new ResponseData();
		if (sessionId.equalsIgnoreCase("null") || sessionId.equalsIgnoreCase("")) {
			response.setCode("0014");
			response.setDesc("Session ID is mandatory");
		} else if (userId.equalsIgnoreCase("null") || userId.equalsIgnoreCase("")) {
			response.setCode("0014");
			response.setDesc("User ID is mandatory");
		} else {
			response.setCode("0000");
			response.setDesc("Success");
		}
		return response;
	}

	// --- end of mobile service-------

}
