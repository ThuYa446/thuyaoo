package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseData {
private String code;
private String desc;
public ResponseData()
{
	clearProperties();
}
private void clearProperties()
{
	this.code = "";
	this.desc = "";
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
@Override
public String toString() {
	return "{code=" + code + ", desc=" + desc + "}";
}

}
