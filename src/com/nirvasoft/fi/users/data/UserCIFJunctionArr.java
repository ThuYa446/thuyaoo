package com.nirvasoft.fi.users.data;

import javax.xml.bind.annotation.XmlRootElement;

import com.nirvasoft.fi.data.UserCIFJunction;

@XmlRootElement
public class UserCIFJunctionArr {
	private String userID;
	private UserCIFJunction[] data;
	private String messagecode;
	private String messagedesc;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public UserCIFJunction[] getData() {
		return data;
	}

	public void setData(UserCIFJunction[] data) {
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
