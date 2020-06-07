package com.nirvasoft.fi.util;

/* 
TUN THURA THET 2011 04 21
*/
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.text.*;
import java.io.*;

import com.nirvasoft.fi.dao.DAOManager;
import com.nirvasoft.fi.framework.Ref;
import com.nirvasoft.fi.framework.Ref1;
import com.nirvasoft.fi.framework.ServerSession;

/**
 * 
 * TUN THURA THET @ NTU 2007-2009
 */
public class FileUtil {
	static public String LogFile;

	public static ArrayList<String> readTable(String pTable) {
		ArrayList<String> arlRecords = null;
		try {
			arlRecords = new ArrayList<String>();
			File f = new File(pTable);
			File[] fileList = f.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					String fname = fileList[i].getAbsolutePath();
					// System.out.println(fname);
					if (fname.endsWith("txt"))
						arlRecords.add(readALine(fname) + "__|" + fname);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return arlRecords;
	}

	public static void downloadFile(String path, String fileName, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        File file = new File(path + "/" + fileName);
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream fin = new FileInputStream(file);
            fin.read(bytes);
            ServletOutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static void writeTable(String pTable, String id, String data) {
		try {
			writeText(pTable + "/" + id + ".txt", data, false);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static boolean deleteFile(String f) {
		boolean isdeleted = false;
		File file = new File(f);
		try {
			isdeleted = file.delete();

		} catch (Exception e) {
			System.out.println("File Delete: " + e.toString());
		}
		return isdeleted;
	}

	public static ArrayList<String> getFileList(String strPath) {
		ArrayList<String> arlFiles = null;
		try {
			arlFiles = new ArrayList<String>();
			File f = new File(strPath);
			File[] fileList = f.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					arlFiles.add(fileList[i].getAbsolutePath());
				} else {
					ArrayList<String> arlSubFiles = getFileList(fileList[i].getAbsolutePath());
					arlFiles.addAll(arlSubFiles);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return arlFiles;
	}

	public static String[] getFileArray(String strPath) {
		ArrayList<String> arl = getFileList(strPath);
		String[] a = new String[arl.size()];
		a = arl.toArray(a);
		return a;
	}

	public static String readText(String strPath) {
		StringBuffer sbTemp = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new FileReader(strPath));
			String str;
			while ((str = in.readLine()) != null) {
				sbTemp.append(str + "\r");
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sbTemp.toString();
	}

	public static String readALine(String strPath) {
		StringBuffer sbTemp = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new FileReader(strPath));
			String str;
			while ((str = in.readLine()) != null) {
				sbTemp.append(str);
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sbTemp.toString();
	}

	public static void logTime(String strMsg) {
		logTime(strMsg, LogFile);
	}

	public static void logTime(String strMsg, String File) {
		logln(getTime() + " : " + strMsg + "\r\n", File);
	}

	public static String getTime() {
		Date d = new Date(System.currentTimeMillis()); // liftOffApollo11.getTime();
		DateFormat df1 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		String s1 = df1.format(d);
		return s1;
	}

	public static String datetoString() {
		String l_date = "";
		java.util.Date l_Date = new java.util.Date();
		SimpleDateFormat l_sdf = new SimpleDateFormat("yyyy-MM-dd");
		l_date = l_sdf.format(l_Date);

		return l_date;
	}

	public static void log(String strContent) {
		log(strContent, LogFile);
	}

	public static void log(String strContent, String File) {
		// System.out.print(strContent);
		writeText(File, strContent, true);
	}

	public static void logln(String strContent) {
		logln(strContent, LogFile);
	}

	public static void logln(String strContent, String File) {
		log(strContent + "\r\n", File);
	}

	public static void logEmpty() {
		writeText(LogFile, "", false);
	}

	public static void writeText(String strPath, String strContent, boolean bAppend) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(strPath, bAppend));
			out.write(strContent);
			out.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	public static void writeALine(String strPath, String strContent, boolean bAppend) {
		writeText(strPath, strContent + (char) 13 + (char) 10, bAppend);
	}

	public static PrintWriter openWriter(String strPath, boolean bAppend) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(strPath, bAppend));
		} catch (IOException e) {
		}
		return out;
	}

	public static void writeln(String strContent, PrintWriter out) {
		try {
			out.println(strContent);
		} catch (Exception e) {
		}
	}

	public static void writeList(String strPath, ArrayList<String> arlContent, boolean bAppend) {
		StringBuffer sbTemp = new StringBuffer();
		for (int i = 0; i < arlContent.size(); i++) {
			sbTemp.append(arlContent.get(i) + (char) 13 + (char) 10);
		}
		writeText(strPath, sbTemp.toString(), bAppend);
	}

	public static ArrayList<String> readList(String strPath) {
		return readList(strPath, false);
	}

	public static ArrayList<String> readList(String strPath, boolean stem) {
		ArrayList<String> arlTemp = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(strPath));
			String str;
			while ((str = in.readLine()) != null) {
				String strOK = str;
				if (stem) {
					strOK = str;
				}
				if (!str.equals(""))
					arlTemp.add(strOK);
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Error @ readList" + e.getMessage());
		}
		return arlTemp;
	}

	public static BufferedReader openReader(String strPath) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(strPath));
		} catch (Exception e) {
		}
		return in;
	}

	public static String readln(BufferedReader in) {
		String str = "";
		try {
			str = in.readLine();
		} catch (Exception e) {
		}
		return str;
	}

	public static boolean deleteFiles(String d) {
		boolean isdeleted = false;
		File dir = new File(d);
		String[] list = dir.list();
		File file;
		if (list.length == 0)
			return isdeleted;
		for (int i = 0; i < list.length; i++) {
			file = new File(d + list[i]);
			isdeleted = file.delete();
		}
		return isdeleted;
	}

	public static boolean deleteFiles(String d, String start) {
		boolean isdeleted = false;
		File dir = new File(d);
		String[] list = dir.list();
		File file;
		if (list.length == 0)
			return isdeleted;
		for (int i = 0; i < list.length; i++) {
			if (list[i].startsWith(start)) {
				file = new File(d + list[i]);
				isdeleted = file.delete();
				System.out.println("deleted: " + d + list[i]);
			}
		}
		return isdeleted;
	}

	public static void makeDir(String d) {
		try {
			new File(d).mkdirs();
		} catch (Exception e) {
		}
	}

	public static String runFile(String exe) {
		// setPath();
		String sbs = new String();
		StringBuffer sb = new StringBuffer();
		try {
			StringBuffer buff = new StringBuffer();
			String modelCommand = exe;
			Runtime r1 = Runtime.getRuntime();
			Process p = r1.exec(modelCommand);
			BufferedInputStream bufIn = new BufferedInputStream(p.getInputStream());

			int ch;
			while ((ch = bufIn.read()) > -1) {
				buff.append((char) ch);
			}
			bufIn.close();
			sbs = buff.toString();
			System.out.println(sbs);
			sb.append(sbs);
			System.out.println(sbs);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}

	public static ArrayList<String> readFile(String aFilePath) throws Exception {
		ArrayList<String> l_result = new ArrayList<String>();

		try {
			BufferedReader l_BufferedReader = new BufferedReader(new FileReader(aFilePath));
			String l_str = "";
			while ((l_str = l_BufferedReader.readLine()) != null) {
				l_result.add(l_str);
			}
			l_BufferedReader.close();

		} catch (Exception e) {
			throw e;
		}

		return l_result;
	}

	public ArrayList<Ref> getAllProcessingCode() throws Exception {

		ArrayList<Ref> ref = new ArrayList<Ref>();
		String retCaption = "", retValue = "";
		String path = ServerSession.serverPath + "reference//";
		ArrayList<String> arl = FileUtil.readList(ServerSession.serverPath + "/reference/" + "ProcessingCodeLov.txt");

		for (int i = 0; i < arl.size(); i++) {
			Ref data = new Ref();
			if (!arl.get(i).equals("")) {
				retValue = arl.get(i).split(":")[0];
				retCaption = arl.get(i).split(":")[1];

				/*if (arl.get(i).split(":")[0].equalsIgnoreCase("000000")) {
					retValue = arl.get(i).split(":")[0];
					retCaption = arl.get(i).split(":")[1];

					System.out.println("result = " + retCaption);
				} else if (arl.get(i).split(":")[0].equalsIgnoreCase("080400")) {
					retValue = arl.get(i).split(":")[0];
					retCaption = arl.get(i).split(":")[1];
				}*/
				data.setcaption(retCaption);
				data.setvalue(retValue);
				ref.add(data);
			}
		}
		return ref;
	}
	
	 public static Ref[]  readListOfValue(String fileName)
	   	{
	   		
	    	Ref[] lovRef = null;
	   		try {
	   			System.out.println("File path : " + DAOManager.AbsolutePath +"/reference/"+ fileName+".txt");
	   			ArrayList<String> arl = FileUtil.readList(DAOManager.AbsolutePath +"/reference/"+ fileName+".txt");
	   			lovRef = new Ref[arl.size()];
	   			for (int i=0;i<arl.size();i++) {
	   				
	   					Ref ref=new Ref();
	   						if (!arl.get(i).equals("")){
	   							ref.setvalue(arl.get(i).split(":")[0]);
	   							ref.setcaption(arl.get(i).split(":")[1]);
	   						}	
	   					lovRef[i] = ref;   				
	   			}
	   			
	   		} catch (Exception e) {
	   			// TODO: handle exception
	   			e.printStackTrace();
	   		}
	   		return lovRef;
	   	}
	public static String getMessageDescription(String messageCode) {

		String retMsgDesc = "";
		try {
			ArrayList<String> arl = FileUtil.readList(DAOManager.AbsolutePath + "/reference/" + "MessageCode.txt");
			for (int i = 0; i < arl.size(); i++) {
				if (!arl.get(i).equals("")) {

					if (!arl.get(i).equals("")) {

						if (arl.get(i).split(":")[0].equalsIgnoreCase(messageCode)) {
							System.out.println("msgdesc" + arl.get(i).split(":")[1]);
							retMsgDesc = arl.get(i).split(":")[1];
						}
					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return retMsgDesc;
	}

}