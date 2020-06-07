package com.nirvasoft.fi.framework;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Udf { 
	private String type;  
	private String label;  
	private String value;
	public Udf(){
		type="text"; 
	}
	public String getLabel() { return label; } 
	public void setLabel(String p) {this.label = p;}
	public String getValue() { return value; } 
	public void setValue(String p) {this.value = p;}
	public String getType() { return type; } 
	public void setType(String p) {this.type = p;} 
}
