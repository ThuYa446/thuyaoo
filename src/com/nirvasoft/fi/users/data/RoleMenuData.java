package com.nirvasoft.fi.users.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoleMenuData {
	private long syskey;
	private String t2;
	private boolean result;
	private long n2;
	private RoleMenuData[] childmenus;
	
	public RoleMenuData()
	{
		clearProperties();
	}
	public RoleMenuData(long key, String t, boolean res)
	{
		this.syskey = key;
		this.t2 = t;
		this.result = res;
	}
	
	public long getSyskey() {
		return syskey;
	}
	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}
	
	public long getN2() {
		return n2;
	}

	public void setN2(long n2) {
		this.n2 = n2;
	}

	public String getT2() {
		return t2;
	}
	public void setT2(String t2) {
		this.t2 = t2;
	}
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public RoleMenuData[] getChildmenus() {
		return childmenus;
	}
	public void setChildmenus(RoleMenuData[] childmenus) {
		this.childmenus = childmenus;
	}
	

	protected void clearProperties() {
		this.syskey = 0;
		this.t2 = "";
		this.result = false;

	}
	
}
