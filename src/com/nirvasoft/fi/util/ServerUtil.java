package com.nirvasoft.fi.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;

import password.DESedeEncryption;

public class ServerUtil {
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null)
				if (!conn.isClosed())
					conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String encryptPIN(String p){
		String ret = "";
		try {
			DESedeEncryption myEncryptor = new DESedeEncryption();
			ret = myEncryptor.encrypt(p);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return ret;
	}

	public static String decryptPIN(String p){
		String ret = "";
		try {
			DESedeEncryption myEncryptor = new DESedeEncryption();
			ret = myEncryptor.decrypt(p);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return ret;
	}
	
	public static String formatNumber(double pAmount) {
        String l_result="0.00";
        DecimalFormat l_df=new DecimalFormat("#,###.00");
        l_result=l_df.format(pAmount);
        return l_result;
    }
}
