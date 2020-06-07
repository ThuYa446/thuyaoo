package com.nirvasoft.fi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nirvasoft.fi.users.data.RoleMenuData;
import com.nirvasoft.fi.users.data.RoleMenuDataset;


public class RoleMenuDao {

	public static RoleMenuData[] getRoleMenuList(Connection conn)
	{
		ArrayList<RoleMenuData> ret = new ArrayList<RoleMenuData>();
		RoleMenuData data = new RoleMenuData();
		try
		{
			String sql = "SELECT syskey,t2,n2 FROM View_Menu_A WHERE n2=0";				
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();
			
			while(result.next())
			{
				data = new RoleMenuData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t2"));
				data.setN2(result.getLong("n2"));
				data.setChildmenus(RoleMenuDao.getChildMenuData(result.getLong("syskey"),conn));
				ret.add(data);			
			}			
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		RoleMenuData[] dataarray = new RoleMenuData[ret.size()];
	
		
		if(ret.size()==1){
			dataarray = new RoleMenuData[ret.size()+1];
			dataarray[0] = ret.get(0);
			dataarray[1] = new RoleMenuData();
		}
	
		else
			
			for(int i=0; i < ret.size(); i++){	
				dataarray[i] = ret.get(i);
			}
		
		
		return dataarray;
		
	}
	
	public static RoleMenuData[] getChildMenuData(long skey, Connection conn)
			throws SQLException {

		ArrayList<RoleMenuData> ret = new ArrayList<RoleMenuData>();
		RoleMenuData data = new RoleMenuData();
		try
		{
			//String sql = "SELECT syskey,t2,n2 FROM View_Menu_A WHERE n2="+skey;
			String sql = "SELECT DISTINCT t2 , MIN(syskey) AS syskey  ,"
					+ " MIN(n2) AS n2 FROM View_Menu_A WHERE n2=? GROUP BY t2 ORDER BY syskey";	
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setLong(1, skey);
			ResultSet result = stat.executeQuery();
			
			while(result.next())
			{
				data = new RoleMenuData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t2"));
				data.setN2(result.getLong("n2"));
				ret.add(data);
			}			
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		RoleMenuData[] dataarray = new RoleMenuData[ret.size()];
		
		
		if(ret.size()==1){
			dataarray = new RoleMenuData[ret.size()+1];
			dataarray[0] = ret.get(0);
			dataarray[1] = new RoleMenuData();
		}
		
		else
		
			for(int i=0; i < ret.size(); i++){	
				dataarray[i] = ret.get(i);
		
			}
	
		
		return dataarray;
	}
	
	
	
	
	public static RoleMenuData[] getRoleMenuList(long skey,Connection conn)
	{
		ArrayList<RoleMenuData> ret = new ArrayList<RoleMenuData>();
		RoleMenuData data = new RoleMenuData();
		try
		{
			String sql = "SELECT syskey,t2,n2 FROM View_Menu_A WHERE n2=0";
				
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();
			
			while(result.next())
			{
				data = new RoleMenuData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t2"));
				data.setN2(result.getLong("n2"));
				long s[] = RoleMenuDao.getMenuResult(skey, conn);
				for(int i=0; i<s.length; i++)
				{
					if(s[i]==result.getLong("syskey"))
					{
						data.setResult(true);
					}
						
				}
				
				data.setChildmenus(RoleMenuDao.getChildMenuData(skey, result.getLong("syskey"),conn));
				ret.add(data);
				
			}			
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		RoleMenuData[] dataarray = new RoleMenuData[ret.size()];
		
		if(ret.size()==1){
				dataarray = new RoleMenuData[ret.size()+1];
				dataarray[0] = ret.get(0);
				dataarray[1] = new RoleMenuData();
		}
			
		else
			
		for(int i=0; i < ret.size(); i++){	
			dataarray[i] = ret.get(i);
		}
		
		return dataarray;
		
	}
	
	public static RoleMenuData[] getChildMenuData(long skey1, long skey2, Connection conn)
			throws SQLException {

		ArrayList<RoleMenuData> ret = new ArrayList<RoleMenuData>();
		RoleMenuData data = new RoleMenuData();
		try
		{

			//String sql = "SELECT syskey,t2,n2 FROM View_Menu_A WHERE n2="+skey;
			String sql = "SELECT DISTINCT t2 , MIN(syskey) AS syskey  ,"
					+ " MIN(n2) AS n2 FROM View_Menu_A WHERE n2=? GROUP BY t2 ORDER BY syskey";	
				
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setLong(1, skey2);
			ResultSet result = stat.executeQuery();
			
			while(result.next())
			{
				data = new RoleMenuData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t2"));
				data.setN2(result.getLong("n2"));
				
				long s[]= RoleMenuDao.getChildResult(skey1, conn);
				for(int i=0; i<s.length; i++)
				{
					if(s[i]==(result.getLong("syskey")))
						data.setResult(true);
				}
				ret.add(data);
			}			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		RoleMenuData[] dataarray = new RoleMenuData[ret.size()];
	
		
		if(ret.size()==1){
			dataarray = new RoleMenuData[ret.size()+1];
			dataarray[0] = ret.get(0);
			dataarray[1] = new RoleMenuData();
		}
		
		else
			
		for(int i=0; i < ret.size(); i++){	
			dataarray[i] = ret.get(i);
			
		}
		
		return dataarray;
	}
	
	
	public static long[] getMenuResult(long skey, Connection conn)
			throws SQLException {

		ArrayList<Long> ary = new ArrayList<Long>();
		try
		{
			String sql = "Select syskey from View_Menu_A Where syskey "
					+ "IN (Select n2 from UVM023_A Where n1=?) AND n2=0";
		
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setLong(1, skey);
			ResultSet res = stat.executeQuery();
			
			while(res.next())
			{
				ary.add(res.getLong("syskey"));
			}	
			
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		long []result = new long[ary.size()];
	
		for(int i=0; i< ary.size(); i++)
		{
			result[i] = ary.get(i);
		}
		
		return result;
	}
	
	
	public static long[] getChildResult(long skey, Connection conn)
			throws SQLException {

		ArrayList<Long> ary = new ArrayList<Long>();
		try
		{
			String sql = "Select syskey from View_Menu_A Where syskey "
					+ "IN (Select n2 from UVM023_A Where n1=?) AND n2<>0";
		
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setLong(1, skey);
			ResultSet res = stat.executeQuery();
			
			while(res.next())
			{
				ary.add(res.getLong("syskey"));
			}		
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		long []result = new long[ary.size()];
	
		for(int i=0; i< ary.size(); i++)
		{
			result[i] = ary.get(i);
		}
		return result;
	}
	
	
	
	
}