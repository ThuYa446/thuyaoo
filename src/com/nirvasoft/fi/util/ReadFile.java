package com.nirvasoft.fi.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.nirvasoft.fi.shared.ServerGlobal;

public class ReadFile {
	public static ArrayList<String> readConnection(String aFilePath) throws Exception
	{
 		ArrayList<String> l_result = new ArrayList<String>();		
		try {
			BufferedReader l_BufferedReader = new BufferedReader(new FileReader(ServerGlobal.getAppPath() +aFilePath));
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

}