package com.nirvasoft.fi.users.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UCJunction {
	private long autoKey;
	private String userID;
	private String customerid;
	private String t1;
	private String t2;
	private int n1;
	private int n2;
	private int srno;
	public long getAutoKey() {
		return autoKey;
	}

	public void setAutoKey(long autoKey) {
		this.autoKey = autoKey;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public int getSrno() {
		return srno;
	}

	public void setSrno(int srno) {
		this.srno = srno;
	}

	@Override
	public String toString() {
		return "UCJunction [autoKey=" + autoKey + ", userID=" + userID + ", customerid=" + customerid + ", t1=" + t1
				+ ", t2=" + t2 + ", n1=" + n1 + ", n2=" + n2 + ", srno=" + srno + "]";
	}
	

}
