package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BankInfo {
private String bankname;
private String bankcode;
private String branchname;
private String branchcode;
private String accNo;
private boolean flag;

public BankInfo(){
	clearProperties();
}
private void clearProperties()
{
	this.bankname = "";
	this.branchname = "";
	this.branchcode = "";
	this.bankcode = "";
	this.accNo = "";
	this.flag = true;
}
public String getBankname() {
	return bankname;
}
public void setBankname(String bankname) {
	this.bankname = bankname;
}
public String getBranchname() {
	return branchname;
}
public void setBranchname(String branchname) {
	this.branchname = branchname;
}
public String getBranchcode() {
	return branchcode;
}
public void setBranchcode(String branchcode) {
	this.branchcode = branchcode;
}
public String getBankcode() {
	return bankcode;
}
public void setBankcode(String bankcode) {
	this.bankcode = bankcode;
}
public String getAccNo() {
	return accNo;
}
public void setAccNo(String accNo) {
	this.accNo = accNo;
}
public boolean isFlag() {
	return flag;
}
public void setFlag(boolean flag) {
	this.flag = flag;
}
@Override
public String toString() {
	return "BankInfo [bankname=" + bankname + ", bankcode=" + bankcode + ", branchname=" + branchname + ", branchcode="
			+ branchcode + ", accNo=" + accNo + ", flag=" + flag + "]";
}


}
