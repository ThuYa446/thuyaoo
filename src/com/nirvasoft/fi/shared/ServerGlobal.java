package com.nirvasoft.fi.shared;


public class ServerGlobal {
	
	private static String mAppPath = "";
	private static String mSeparator = "\\|\\|\\_\\_";
	private static String mFCSeparator = "\\_\\_\\|";
	private static boolean mSetWriteLog = false;
	private static String fortuneYgnMerchant;
	private static String fortuneMdyMerchant;
	private static String mFCRTServiceURL="";
	private static String mEPIXServiceURL="";
	
	private static String mFCACServiceURL="";
	private static String mFCCUServiceURL="";

	//CONFIG
	private static String envType = "";
	private static String comGL = "";
	private static String advertise = "";	
	private static String SMSAggregator = "";
	
	public static String getmFCACServiceURL() {
		return mFCACServiceURL;
	}

	public static void setmFCACServiceURL(String mFCACServiceURL) {
		ServerGlobal.mFCACServiceURL = mFCACServiceURL;
	}

	public static String getmFCCUServiceURL() {
		return mFCCUServiceURL;
	}

	public static void setmFCCUServiceURL(String mFCCUServiceURL) {
		ServerGlobal.mFCCUServiceURL = mFCCUServiceURL;
	}

	public static String getmEPIXServiceURL() {
		return mEPIXServiceURL;
	}

	public static void setmEPIXServiceURL(String mEPIXServiceURL) {
		ServerGlobal.mEPIXServiceURL = mEPIXServiceURL;
	}

	public static String getSeparator(){
		return mSeparator;
	}
	
	public static String getFCSeparator(){
		return mFCSeparator;
	}

	public static String getAppPath() {
		return mAppPath;
	}

	public static void setAppPath(String mAppPath) {
		ServerGlobal.mAppPath = mAppPath;
	}
	
	public static boolean isWriteLog() {
		return mSetWriteLog;
	}

	public static void setWriteLog(boolean mWLog) {
		ServerGlobal.mSetWriteLog = mWLog;
	}

	public static String getmAppPath() {
		return mAppPath;
	}

	public static void setmAppPath(String mAppPath) {
		ServerGlobal.mAppPath = mAppPath;
	}

	public static String getmSeparator() {
		return mSeparator;
	}

	public static void setmSeparator(String mSeparator) {
		ServerGlobal.mSeparator = mSeparator;
	}

	

	public static boolean ismSetWriteLog() {
		return mSetWriteLog;
	}

	public static void setmSetWriteLog(boolean mSetWriteLog) {
		ServerGlobal.mSetWriteLog = mSetWriteLog;
	}

	public static String getFortuneYgnMerchant() {
		return fortuneYgnMerchant;
	}

	public static void setFortuneYgnMerchant(String fortuneYgnMerchant) {
		ServerGlobal.fortuneYgnMerchant = fortuneYgnMerchant;
	}

	public static String getFortuneMdyMerchant() {
		return fortuneMdyMerchant;
	}

	public static void setFortuneMdyMerchant(String fortuneMdyMerchant) {
		ServerGlobal.fortuneMdyMerchant = fortuneMdyMerchant;
	}
	
	public static String getFCRTServiceURL() {
		return mFCRTServiceURL;
	}

	public static void setFCRTServiceURL(String mFCURL) {
		ServerGlobal.mFCRTServiceURL = mFCURL;
	}

	public static String getEnvType() {
		return envType;
	}

	public static void setEnvType(String envType) {
		ServerGlobal.envType = envType;
	}

	public static String getComGL() {
		return comGL;
	}

	public static void setComGL(String comGL) {
		ServerGlobal.comGL = comGL;
	}

	public static String getAdvertise() {
		return advertise;
	}

	public static void setAdvertise(String advertise) {
		ServerGlobal.advertise = advertise;
	}

	public static String getSMSAggregator() {
		return SMSAggregator;
	}

	public static void setSMSAggregator(String sMSAggregator) {
		SMSAggregator = sMSAggregator;
	}

}
