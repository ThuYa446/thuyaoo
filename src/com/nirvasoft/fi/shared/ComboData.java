package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComboData {

	private String value;
	private String caption;
	private String datatype;
	private boolean flag;
	private int id;
	private long typeSrNo;
	private String refDataType;
	private String lovref;
	private String t2;
	private String lovType;

	public ComboData() {
		super();
		clearProperties();
	}

	public void clearProperties() {
		this.value = "";
		this.caption = "";
		this.flag = false;
		this.id = 0;
		datatype = "";
		typeSrNo = 0;
		refDataType = "";
		lovref = "";
		t2 = "";
		lovType = "";
	}

	public long getTypeSrNo() {
		return typeSrNo;
	}

	public void setTypeSrNo(long typeSrNo) {
		this.typeSrNo = typeSrNo;
	}

	public String getRefDataType() {
		return refDataType;
	}

	public void setRefDataType(String refDataType) {
		this.refDataType = refDataType;
	}

	public String getLovref() {
		return lovref;
	}

	public void setLovref(String lovref) {
		this.lovref = lovref;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String getLovType() {
		return lovType;
	}

	public void setLovType(String lovType) {
		this.lovType = lovType;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

}
