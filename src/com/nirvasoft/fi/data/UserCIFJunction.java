package com.nirvasoft.fi.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserCIFJunction {
	
	private long syskey;
	private String mobileUserID;
	private String customerID;
	private String accountNo;
	private String currencyCode;
	private float transferLimit;
	private String productType;
	private String branchCode;
	private int status;
	private String t1;
	private String t2;
	private String t3;
	private String t4;
	private String t5;
	private String t6;
	private String t7;
	private String t8;
	private String t9;
	private int n1;
	private int n2;
	private int n3;
	private int n4;
	private int n5;
	private int n6;
	private int n7;
	private int n8;
	private int n9;
	private String joinedDate;
	
	public UserCIFJunction(){
		clearProperties();
	}
	
	
	public long getSyskey() {
		return syskey;
	}


	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}


	public String getMobileUserID() {
		return mobileUserID;
	}
	public void setMobileUserID(String mobileUserID) {
		this.mobileUserID = mobileUserID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public float getTransferLimit() {
		return transferLimit;
	}
	public void setTransferLimit(float transferLimit) {
		this.transferLimit = transferLimit;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	private void clearProperties(){
		this.syskey=0;
		this.mobileUserID="";
		this.customerID="";
		this.accountNo="";
		this.currencyCode="";
		this.transferLimit=0;
		this.productType="";
		this.branchCode="";
		this.status=0;
		this.t1="";
		this.t2="";
		this.t3="";
		this.t4="";
		this.t5="";
		this.t6="";
		this.t7="";
		this.t8="";
		this.t9="";
		this.n1=0;
		this.n2=0;
		this.n3=0;
		this.n4=0;
		this.n5=0;
		this.n6=0;
		this.n7=0;
		this.n8=0;
		this.n9=0;
		this.joinedDate="";
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

	public String getT3() {
		return t3;
	}

	public void setT3(String t3) {
		this.t3 = t3;
	}

	public String getT4() {
		return t4;
	}

	public void setT4(String t4) {
		this.t4 = t4;
	}

	public String getT5() {
		return t5;
	}

	public void setT5(String t5) {
		this.t5 = t5;
	}

	public String getT6() {
		return t6;
	}

	public void setT6(String t6) {
		this.t6 = t6;
	}

	public String getT7() {
		return t7;
	}

	public void setT7(String t7) {
		this.t7 = t7;
	}

	public String getT8() {
		return t8;
	}

	public void setT8(String t8) {
		this.t8 = t8;
	}

	public String getT9() {
		return t9;
	}

	public void setT9(String t9) {
		this.t9 = t9;
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

	public int getN3() {
		return n3;
	}

	public void setN3(int n3) {
		this.n3 = n3;
	}

	public int getN4() {
		return n4;
	}

	public void setN4(int n4) {
		this.n4 = n4;
	}

	public int getN5() {
		return n5;
	}

	public void setN5(int n5) {
		this.n5 = n5;
	}

	public int getN6() {
		return n6;
	}

	public void setN6(int n6) {
		this.n6 = n6;
	}

	public int getN7() {
		return n7;
	}

	public void setN7(int n7) {
		this.n7 = n7;
	}

	public int getN8() {
		return n8;
	}

	public void setN8(int n8) {
		this.n8 = n8;
	}

	public int getN9() {
		return n9;
	}

	public void setN9(int n9) {
		this.n9 = n9;
	}

}
