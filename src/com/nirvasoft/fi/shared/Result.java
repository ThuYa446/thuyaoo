package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {

	

	private boolean state = false;
	private String msgCode = "";
	private String msgDesc = "";
	private String keyString = "";
	
	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}


	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

	@Override
	public String toString() {
		return "Result [state=" + state + ", msgCode=" + msgCode + ", msgDesc=" + msgDesc + ", keyString=" + keyString
				+ "]";
	}


}
