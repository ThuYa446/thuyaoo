package com.nirvasoft.fi.users.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserRoleViewData{
	private long syskey;
	private String t2;
	private int n1;
	private boolean flag;
	private long []rolesyskey;
	

	public UserRoleViewData() {
		clearProperties();
	}

	public long getSyskey() {
		return syskey;
	}


	public long[] getRolesyskey() {
		return rolesyskey;
	}

	public void setRolesyskey(long[] rolesyskey) {
		this.rolesyskey = rolesyskey;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}
	

	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	private void clearProperties() {
		this.syskey = 0;		
		this.t2 = "";
		this.flag=false;
		this.n1 = 0;
		
		

	}
}
