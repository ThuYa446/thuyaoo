package com.nirvasoft.fi.framework;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Menu {
	private String menuitem;
	private String caption;
	private Menu[] menuitems;

	public String getMenuItem() { return menuitem; } 
	public void setMenuItem(String p) {this.menuitem = p;}
	public String getCaption() { return caption; } 
	public void setCaption(String p) {this.caption = p;}
	public Menu[] getMenuItems() { return menuitems; } 
	public void setMenuItems(Menu[] p) {this.menuitems = p;}
	
	@Override
	public String toString() {
		return "Menu [menuitem=" + menuitem + ", caption=" + caption + ", menuitems=" + Arrays.toString(menuitems)
				+ "]";
	}
	
	
}
