package com.nirvasoft.fi.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.nirvasoft.fi.dao.DAOManager;
import com.nirvasoft.fi.shared.ServerGlobal;

public class GeneralUtil {
	private static String apppath = "";

	public static boolean writeLogWebService(ArrayList<String> pErrorList, String logpath) {
        boolean l_result = false;

        try {
            String path = System.getenv("mBanking360");
            String l_Date = datetoString();
            String l_Path = path + logpath;
            File dir = new File(l_Path);
            dir.mkdirs();
            FileUtil.writeList(l_Path + "\\" + l_Date + ".txt", pErrorList, true);
            l_result = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            l_result = false;
        }
        return l_result;

    }
	
	public static void readFCACServiceSetting() {
		 
        String mPath = System.getenv("mBanking360");
        ArrayList<String> l_resultList = null;
        try {
            l_resultList = FileUtil.readList(mPath + "/data/ServiceSetting.txt");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Get Service Path URL		
        for (String string : l_resultList) {
            if (string.startsWith("AC__")) {
            	String url = string.split(ServerGlobal.getFCSeparator())[1];
                ServerGlobal.setmFCACServiceURL(url);
            }
        }
    }
 
 public static void readFCCUServiceSetting() {
	 
        String mPath = System.getenv("mBanking360");
        ArrayList<String> l_resultList = null;
        try {
            l_resultList = FileUtil.readList(mPath + "/data/ServiceSetting.txt");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Get Service Path URL		
        for (String string : l_resultList) {
            if (string.startsWith("CU")) {
                String url = string.split(ServerGlobal.getFCSeparator())[1];
                ServerGlobal.setmFCCUServiceURL(url);
            }
        }
    }
 
	
	public static void readEPIXWSDebugLogStatus() {
		try {
			
			apppath = System.getenv("mBanking360");
			ArrayList<String> l_resultList = FileUtil.readList(apppath + "\\EPIXWS\\debugconfig\\Log.txt");
			
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
	// suwai
	public static void readDebugLogStatus() {
		try {
			
			apppath = System.getenv("mBanking360");
			ArrayList<String> l_resultList = FileUtil.readList(apppath + "\\TPIS\\Mobile\\debugconfig\\Log.txt");
			
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
	
	public static String readConfig(String param){    
        String l_ret = "";
        ArrayList<String> arl = new ArrayList<String>();
        String path = System.getenv("mBanking360")+"/reference/config.txt";    
        try{
            arl = FileUtil.readFile(path);        
        }catch(Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < arl.size(); i++) {
            if (!arl.get(i).equals("")){
                if (arl.get(i).startsWith(param)){
                    l_ret = arl.get(i); 
                    break;
                }
            }
        }
        String[] l_split = l_ret.split(":");
        return l_ret =l_split[1];
    }
	
	public static void readConfig() {
		try {
			apppath = System.getenv("mBanking360");
			ArrayList<String> l_resultList = FileUtil.readList(apppath + "\\reference\\config.txt");
			if (l_resultList != null && l_resultList.size() > 0) {
				int i = 0;
				 ServerGlobal.setEnvType(l_resultList.get(i++).split(":")[1]);
				 ServerGlobal.setComGL(l_resultList.get(i++).split(":")[1]);
				 ServerGlobal.setAdvertise(l_resultList.get(i++).split(":")[1]);
				 ServerGlobal.setSMSAggregator(l_resultList.get(i++).split(":")[1]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void readDataConfig() {
		
		ArrayList<String> l_resultList = null;
		try {
			l_resultList = FileUtil.readList(DAOManager.AbsolutePath +"/data/DataConfig.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String string : l_resultList) {
			if(string.startsWith("FortuneYgnMerchant")){
				ServerGlobal.setFortuneYgnMerchant(string.split(ServerGlobal.getSeparator())[1]);
			}
			if(string.startsWith("FortuneMdyMerchant")){
				ServerGlobal.setFortuneMdyMerchant(string.split(ServerGlobal.getSeparator())[1]);
			}
			
		}
		
	}

public static boolean writeLog(ArrayList<String> pErrorList) {
	boolean l_result = false;

	try {
		String l_Date = datetoString();
		String l_Path =DAOManager.AbsolutePath + "log\\Web";
		System.out.println(l_Path);
		File dir = new File(l_Path);
		dir.mkdirs();
		FileUtil.writeList(l_Path +"\\"+ l_Date +".txt", pErrorList, true);
		l_result = true;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		l_result = false;
	}
	return l_result;
	
}

public static String datetoString()
{
	String l_date = "";
	java.util.Date l_Date = new java.util.Date();
	SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMdd");	
	l_date = l_sdf.format(l_Date);
	
	return l_date;
}

public static String ChangeDateFormat(String date) throws ParseException
{
	String l_date = "";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//yyyy-MM-dd'T'HH:mm:ss
    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date data = sdf.parse(date);
    l_date = output.format(data);
	l_date = l_date.replaceAll("/", "");
	return l_date;
}

public static String changedateformat(String pdate) 
{
	String l_date="";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//yyyy-MM-dd'T'HH:mm:ss
    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
    Date data = null;
	try {
		data = sdf.parse(pdate);
		 String formattedTime = output.format(data);
		    l_date =  formattedTime.replaceAll("-", "");
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return l_date;
	}
	
	return l_date;
}

public static String mobiledateformat(String pdate)
{
	//yyyymmdd to dd-mm-yyyy
	String l_date="";
	l_date = pdate.substring(6, 8) + '-'+ pdate.substring(4, 6)+ '-'+ pdate.substring(0, 4);
	
	return l_date;
}


public static String getTime(){
	String l_Time = "";
	SimpleDateFormat l_DateFormat = new SimpleDateFormat("HH:mm:ss");
	Calendar l_Canlendar = Calendar.getInstance();
	l_Time = l_DateFormat.format(l_Canlendar.getTime());
	
	return l_Time;
}

public static String formatddMMYYYY2YYYYMMDD(String p){
	 String ret = "";
	SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat f2 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	Date d;
	try {
		d = f.parse(p);
		ret = f2.format(d);				
	} catch (ParseException e) {				
		e.printStackTrace();
	}
	return ret;	
}

public static String formatddMMYYYY2YYYYMMdd(String p) {
   String ret = "";
   String dd="";
   String mm="";
   try {
       int d = Integer.parseInt(p.substring(0, 2));
       
       int m = Integer.parseInt(p.substring(3, 5));
       
       int y = Integer.parseInt(p.substring(6, 10));
       
       if(d<10){
       	 dd="0"+d;
       }else{
    	   dd = p.substring(0, 2);
       }
       if(m<10){
       	 mm="0"+m;
       }else{
    	   mm = p.substring(3, 5);
       }
       ret = y+""+mm+""+dd;
   } catch (Exception ex) {
       ex.printStackTrace();
   }
   return ret;
}
public static String formatNumber(double pAmount) {
	String l_result="0.00";
	DecimalFormat l_df=new DecimalFormat("###0.00");
	l_result=l_df.format(pAmount);
	return l_result;
}

	public static void readFCServiceSetting() {
		
		String mPath = System.getenv("mBanking360");
		
		
		ArrayList<String> l_resultList = null;
		try {
			l_resultList = FileUtil.readList(mPath +"/data/ServiceSetting.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Get Service Path URL		
		for (String string : l_resultList) {
			if(string.startsWith("RT")){
				String url = string.split(ServerGlobal.getFCSeparator())[1];
				ServerGlobal.setFCRTServiceURL(url);
			}
		}
		
	}
	
	//suwai
	public static boolean writeLog(ArrayList<String> pErrorList, String logpath) {
		boolean l_result = false;

		try {
			String path = System.getenv("mBanking360");
			path += "\\TPIS";
			String l_Date = datetoString();
			String l_Path = path + logpath;
			System.out.println(l_Path);
			File dir = new File(l_Path);
			dir.mkdirs();
			FileUtil.writeList(l_Path +"\\"+ l_Date +".txt", pErrorList, true);
			l_result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			l_result = false;
		}
		return l_result;
		
	}

	public static String datetoddMMMyyyywithSlach(String aDate)
	{
		String l_date = "";
		java.util.Date l_Date = new java.util.Date();
		SimpleDateFormat l_sdf = new SimpleDateFormat("dd/MMM/yyyy");	
		SimpleDateFormat l_sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			l_Date = l_sdf.parse(aDate);
			l_date = l_sdf2.format(l_Date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l_date;
	}
	
	public static String datetoyyyyMMddtoddSlashMMSlashyyyy(String aDate)
	{
		String l_date = "";
		java.util.Date l_Date = new java.util.Date();
		SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMdd");	
		SimpleDateFormat l_sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		try {
			l_Date = l_sdf.parse(aDate);
			l_date = l_sdf2.format(l_Date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l_date;
	}
	
	public boolean checkTimeStamp(String timeStamp,String currentdtime,String sessionLimit) throws SQLException, ParseException{
		boolean rt = false;
		long sessionTime = Long.parseLong(sessionLimit);
		// Custom date format
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");  
		Long timelimit = (sessionTime * 60000); // 3 minutes
		Date d1 = null;
		Date d2 = null;
		
		    d1 = format.parse(timeStamp);
		    d2 = format.parse(currentdtime);
		  

		// Get msec from each, and subtract.
		long diff = d2.getTime() - d1.getTime();
		if(diff < timelimit)
		{
			rt = true; 
			
		}
		return rt;
	}
	
   public static void writeLog(String fileName, String msgType, String msgData)
   {
     try {
       String cpath = System.getenv("CMSService");
       String filepath = cpath + "/SMS/log/" + getDateYYYYMMDD() + "_" + fileName + ".txt";
       System.out.println("File Paht :: " + filepath);
       PrintWriter writer = new PrintWriter(
         new BufferedWriter(new FileWriter(filepath, true)));
       writer.println(getDateTime() + "\t:\t" + msgType + " : " + msgData);
       writer.close();
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
   
   public static String getDateTime()
   {
     Date d = new Date();
     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
     String dat = sdf.format(d);
     return dat;
   }
	
   public static String getDateYYYYMMDD(){
	   Date d=new Date();
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	   String dat=sdf.format(d);
	   return dat;
	}
   
   
   public String readOTPMessage() {
		String msg = "";
		try {
			apppath = System.getenv("mBanking360")+"\\TPIS";
			ArrayList<String> l_resultList = FileUtil.readList(apppath + "\\OTPMsg.txt");
			
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
