package com.nirvasoft.fi.framework;

import java.io.Serializable;

import com.nirvasoft.fi.users.data.UserData;

public class MrBean implements Serializable{  
	
	private static final long serialVersionUID = -5796624435566145102L;	
	private UserData user;
	
	private String message = "";
	private String logoText = "";
	private boolean status = false;
	
	
	public MrBean(){
		this.user= new UserData();
		
		this.message = "";
		this.status = false;
	}
	
	public UserData getUser(){ return this.user;}
	public void setUser(UserData p){user=p;}
	
	
	
	public String getMessage() {return message;}
	public void setMessage(String command) {this.message = command;}
	
	public String getLogoText() {return logoText;}
	public void setLogoText(String logoText) {this.logoText = logoText;}	
	
	public boolean getStatus(){return status;}
	public void setStatus(boolean p){status=p;}	
}
