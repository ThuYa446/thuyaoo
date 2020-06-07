package com.nirvasoft.fi.users.data;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UCJunctionArr {
	private String userID;
	private UCJunction[] data;
	private String messagecode;
	private String messagedesc;
	

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public UCJunction[] getData() {
		return data;
	}

	public void setData(UCJunction[] data) {
		this.data = data;
	}

	public String getMessagecode() {
		return messagecode;
	}

	public void setMessagecode(String messagecode) {
		this.messagecode = messagecode;
	}

	public String getMessagedesc() {
		return messagedesc;
	}

	public void setMessagedesc(String messagedesc) {
		this.messagedesc = messagedesc;
	}

}
