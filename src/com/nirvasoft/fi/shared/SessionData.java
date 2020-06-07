package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SessionData {
	private String userid;
	private String sessionID;
	public SessionData() {
		clearProperty();
	}

	void clearProperty() {
		userid = "";
		sessionID = "";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	@Override
	public String toString() {
		return "SessionData [userid=" + userid + ", sessionID=" + sessionID + "]";
	}


}
