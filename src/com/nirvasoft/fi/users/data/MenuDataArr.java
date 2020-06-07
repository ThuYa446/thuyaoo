package com.nirvasoft.fi.users.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MenuDataArr {
    private MenuData[] data;
    
    public MenuDataArr(){
    	super();
    	data = new MenuData[1];
    	/*
    	MenuData d = new MenuData();
    	//d.sethub("hayyyyyyyyyyyyyyyyy success");    	
    	data[0] = d;
    	d = new MenuData();
    	//d.sethub("hayyyyyyyyyyyyyyyyy success2");    	
    	data[1] = d;*/
    	
    }

	public MenuData[] getdata() {
		return data;
	}

	public void setdata(MenuData[] data) {
		this.data = data;
	}
    
    
}
