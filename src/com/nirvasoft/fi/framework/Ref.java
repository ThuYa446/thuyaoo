package com.nirvasoft.fi.framework;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ref {

	
	private String value;
	private String caption;
	private String processingCode;
	
	void clearProperty(){
		value = "";
		caption = "";
		processingCode = "";
	}
	
	public Ref(){
		clearProperty();
	}

	
	
	public String getProcessingCode() {
		return processingCode;
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public String getvalue() {
		return value;
	}

	public void setvalue(String value) {
		this.value = value;
	}

	public String getcaption() {
		return caption;
	}

	public void setcaption(String caption) {
		this.caption = caption;
	}
	
	
}
