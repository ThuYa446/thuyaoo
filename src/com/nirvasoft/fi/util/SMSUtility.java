package com.nirvasoft.fi.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import password.DESedeEncryption;

public class SMSUtility {
	public static String OutputDigit = "";
	public static String StepSeconds = "";
	public static String EncryptType = "";
	public static String OTPMessage = "";

	public static String URL = "";
	public static String NameSpace = "";
	public static String UserName = "";
	public static String Password = "";

	static {
		BufferedReader br = null;
		try {
			String cpath = System.getenv("CMSService");
			br = new BufferedReader(new FileReader(cpath + "/SMS/config/OtpConfig.txt"));
			DESedeEncryption myEncryptor = new DESedeEncryption();
			String st = br.readLine();
			String[] stArr = st.split("OutputDigit :");

			OutputDigit = myEncryptor.decrypt(stArr[1].trim());

			st = br.readLine();
			stArr = st.split("StepSeconds :");

			StepSeconds = myEncryptor.decrypt(stArr[1].trim());

			st = br.readLine();
			stArr = st.split("EncryptType :");
			EncryptType = myEncryptor.decrypt(stArr[1].trim());

			st = br.readLine();
			stArr = st.split("Message :");
			OTPMessage = stArr[1].trim();
		} catch (Exception ex) {
			System.out.println("*** Error in config : " + ex.toString());
			try {
				br.close();
			} catch (IOException ex1) {
				System.out.println("*** Error in config : " + ex1.toString());
			}
		} finally {
			try {
				br.close();
			} catch (IOException ex) {
				System.out.println("*** Error in config : " + ex.toString());
			}
		}
		try {
			String cpath = System.getenv("CMSService");
			br = new BufferedReader(new FileReader(cpath + "/SMS/config/MITSMSConfig.txt"));
			DESedeEncryption myEncryptor1 = new DESedeEncryption();
			String st = br.readLine();
			String[] stArr = st.split("Url :");
			URL = stArr[1].trim();

			st = br.readLine();
			stArr = st.split("NameSpace :");
			NameSpace = stArr[1].trim();

			st = br.readLine();
			stArr = st.split("Username :");
			UserName = stArr[1].trim();

			st = br.readLine();
			stArr = st.split("Password :");
			Password = stArr[1].trim();
		} catch (Exception ex) {
			System.out.println("*** Error in config : " + ex.toString());
			try {
				br.close();
			} catch (IOException ex1) {
				System.out.println("*** Error in config : " + ex1.toString());
			}
		} finally {
			try {
				br.close();
			} catch (IOException ex) {
				System.out.println("*** Error in config : " + ex.toString());
			}
		}
	}

	public static String readOTPMessage() {
		String msg = "";
		try {
			String apppath = System.getenv("CMSService");
			ArrayList<String> l_resultList = FileUtil.readList(apppath + "/SMS/config/OTPMsg.txt");

			if (l_resultList != null && l_resultList.size() > 0) {
				msg = l_resultList.get(0).split(":")[1];
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return msg;
	}

}