package com.nirvasoft.fi.framework;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Bean001 {
	private long syskey; 
	private String t1;  
	private String t2;  
	private String t3;  
	private String t4;  
	private String t5;  
	private String t6;  
	private double n1; 
	private double n2; 
	private double n3; 
	public Bean001(){
		syskey=0;
		t1="Empty*";
		n1=0;
	}
	public long getSysKey() { return syskey; } 
	public void setSysKey(long p) {this.syskey= p;}
	public String getT1() { return t1; } 
	public void setT1(String p) {this.t1 = p;}
	public String getT2() { return t2; } 
	public void setT2(String p) {this.t2 = p;}
	public String getT3() { return t3; } 
	public void setT3(String p) {this.t3 = p;}
	public String getT4() { return t4; } 
	public void setT4(String p) {this.t4 = p;}
	public String getT5() { return t5; } 
	public void setT5(String p) {this.t5 = p;}
	public String getT6() { return t6; } 
	public void setT6(String p) {this.t6 = p;}
	public double getN1() { return n1; } 
	public void setN1(double p) {this.n1 = p;}
	public double getN2() { return n2; } 
	public void setN2(double p) {this.n2 = p;}
	public double getN3() { return n3; } 
	public void setN3(double p) {this.n3 = p;}
}