package com.nirvasoft.fi.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import com.nirvasoft.database.ConnMgr;
import com.nirvasoft.fi.util.FileUtil;
import com.nirvasoft.fi.util.ReadFile;
import com.nirvasoft.fi.util.ServerUtil;

import password.DESedeEncryption;

 public  class ConnAdmin {

	public ConnAdmin() {
		super();		
	}
	
	public static String servername = "";
	public static String port = "";
	public static String instance = "";
	public static String dbname = "";
	public static String dbUsr = "";
	public static String dbPwd = "";
	public static String connType = "";
	static String path = "";
	static String url = "";
	static DESedeEncryption myEncryptor;
	public static Connection getConn(String oId, String externalpath){
		
		Connection ret = null; 
		String driver = "", url = "", userID = "", password = "";
		try {
			myEncryptor = new DESedeEncryption();
			ArrayList<String> oracleConnList;
		    oracleConnList = ReadFile.readConnection(System.getenv("mBanking360")  +"/TPIS/data/"+ "ConnectionConfig.txt");
			if(oracleConnList.size() > 0){
				driver = oracleConnList.get(0).split("Driver:")[1];
				url = oracleConnList.get(1).split("URL:")[1];
				userID = oracleConnList.get(2).split("UserName:")[1];
				password = oracleConnList.get(3).split("Password:")[1];
			}
			Class.forName(driver);
			ret = DriverManager.getConnection(url, userID, myEncryptor.decrypt(password));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public static Connection getAnotherConn(String oId, String externalpath){
			
			Connection ret = null; 
			String driver = "", url = "", userID = "", password = "";
		try {
			myEncryptor = new DESedeEncryption();
			ArrayList<String> oracleConnList;
		    oracleConnList = ReadFile.readConnection(System.getenv("SMSAgg")  +"/Config/"+ "ConnectionConfig.txt");
			if(oracleConnList.size() > 0){
				driver = oracleConnList.get(0).split("Driver:")[1].trim();
				url = oracleConnList.get(1).split("URL:")[1].trim();
				userID = oracleConnList.get(2).split("UserName:")[1].trim();
				password = oracleConnList.get(3).split("Password:")[1].trim();
			}
			Class.forName(driver);
			ret = DriverManager.getConnection(url, userID, myEncryptor.decrypt(password));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
	public static Connection getOracleConn(String oId){
		
		Connection conn = null;
		if(!oId.equals("")){
			readOracleConnectionString(oId);				
			conn = (new ConnMgr(servername,Integer.parseInt(port),instance,dbname,dbUsr,dbPwd,Integer.parseInt(connType))).getConn();
		}
		return conn;
	}
	
	private static void readConnectionString(String pOID, String externalpath){	
		String l_ret = "";
		ArrayList<String> arl = new ArrayList<String>();
		if(externalpath.equals("")){
			path = ServerSession.serverPath+"data//ConncetionConfig.txt";
		}else{
			path = externalpath + "data//ConncetionConfig.txt";
		}
		
		try{
			arl = FileUtil.readFile(path);		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (int i = 0; i < arl.size(); i++) {
			if (!arl.get(i).equals("")){
				if (arl.get(i).startsWith(pOID)){
					l_ret = arl.get(i); 
					break;
				}
			}
		}
		String[] l_split = l_ret.split(",");
		servername = l_split[1];
		port = l_split[2];
		instance = l_split[3];
		dbname = l_split[4];
		dbUsr = l_split[5];
		dbPwd = ServerUtil.decryptPIN(l_split[6]);
		connType = l_split[7];
	}
	
	private static void readAnotherConnectionString(String fileName, String pOID){	
		String l_ret = "";
		ArrayList<String> arl = new ArrayList<String>();
		path = ServerSession.serverPath+"data//"+fileName;
		
		try{
			arl = FileUtil.readFile(path);		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (int i = 0; i < arl.size(); i++) {
			if (!arl.get(i).equals("")){
				if (arl.get(i).startsWith(pOID)){
					l_ret = arl.get(i); 
					break;
				}
			}
		}
		String[] l_split = l_ret.split(",");
		servername = l_split[1];
		port = l_split[2];
		instance = l_split[3];
		dbname = l_split[4];
		dbUsr = l_split[5];
		dbPwd = ServerUtil.decryptPIN(l_split[6]);
		connType = l_split[7];
	}
	
	private static void readOracleConnectionString(String pOID){	
		String l_ret = "";
		ArrayList<String> arl = new ArrayList<String>();
		path = ServerSession.serverPath+"data//OracleConncetionConfig.txt";
		
		try{
			arl = FileUtil.readFile(path);		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (int i = 0; i < arl.size(); i++) {
			if (!arl.get(i).equals("")){
				if (arl.get(i).startsWith(pOID)){
					l_ret = arl.get(i); 
					break;
				}
			}
		}
		String[] l_split = l_ret.split(",");
		servername = l_split[1];
		port = l_split[2];
		instance = l_split[3];
		dbname = l_split[4];
		dbUsr = l_split[5];
		dbPwd = ServerUtil.decryptPIN(l_split[6]);
		connType = l_split[7];
	}
	// suwai
	public static String readExternalUrl(String pOID){	
		url = "";
		String l_ret = "";
		ArrayList<String> arl = new ArrayList<String>();
	
		String path = ServerSession.serverPath+"data//ExternalConfig.txt";
		try{
			arl = FileUtil.readFile(path);		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (int i = 0; i < arl.size(); i++) {
			if (!arl.get(i).equals("")){
				if (arl.get(i).startsWith(pOID)){
					l_ret = arl.get(i); 
					break;
				}
			}
		}
		if(!l_ret.equalsIgnoreCase("")){
			String[] l_split = l_ret.split("_");
			return url =l_split[1];
		}
		
		return url;
	}
	
	
	public static String readConfigJson(String filepath , String pOID){	
		String l_ret = "";
		ArrayList<String> arl = new ArrayList<String>();
		
		String path = filepath  +"json/"+ "config.json";
		try{
			arl = FileUtil.readFile(path);		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (int i = 0; i < arl.size(); i++) {
			if (!arl.get(i).equals("")){
				if (arl.get(i).startsWith(pOID)){
					l_ret = arl.get(i); 
					break;
				}
			}
		}
		String[] l_split = l_ret.split("_");
		return url =l_split[1];
	}
}
