package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OTPCodeData {
private String rKey;
private String code;
private String desc;
private String otpcode;
private String msg;

public OTPCodeData()
{
	clearProperties();
}
private void clearProperties()
{
	this.rKey = "";
	this.code = "";
	this.desc = "";
	this.otpcode = "";
	this.msg = "";
}
public String getCode() {
	return code;
}
public void otpMsg(String code) {
	this.code = code;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public String getrKey() {
	return rKey;
}
public void setrKey(String rKey) {
	this.rKey = rKey;
}
public String getOtpcode() {
	return otpcode;
}
public void setOtpcode(String otpcode) {
	this.otpcode = otpcode;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public void setCode(String code) {
	this.code = code;
}
@Override
public String toString() {
	return "{rKey=" + rKey + ", code=" + code + ", desc=" + desc + ", otpcode=" + otpcode + ", msg=" + msg
			+ "}";
}

}
