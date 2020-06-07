package com.nirvasoft.fi.framework;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ref1 {

	private String value;
	private String caption;

	void clearProperty() {
		value = "";
		caption = "";
	}

	public Ref1() {
		clearProperty();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	
	}
