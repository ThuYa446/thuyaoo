package com.nirvasoft.fi.users.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDataArr {
    private UserData[] data;
    
    public UserDataArr(){
    	super();
    	data = new UserData[1];
    	/*
    	UserData d = new UserData();
    	//d.sethub("hayyyyyyyyyyyyyyyyy success");    	
    	data[0] = d;
    	d = new UserData();
    	//d.sethub("hayyyyyyyyyyyyyyyyy success2");    	
    	data[1] = d;*/
    	
    }

	public UserData[] getdata() {
		return data;
	}

	public void setdata(UserData[] data) {
		this.data = data;
	}
    
    
}
