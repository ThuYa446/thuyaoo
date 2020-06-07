package com.nirvasoft.fi.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {
	
	@XmlElement(name="ID", required=true) 
	private String id;
	
	@XmlElement(name="ResponseCode", required=true) 
	private String responsecode;
	
	@XmlElement(name="ResponseDesc") 
	private String responsedesc;
	
	@XmlElement(name="TransactionID") 
	private String transactionid;
	
	@XmlElement(name="TransactionDateTime") 
	private String transactiondatetime;

	void clearProperty(){
		id = "";
		responsecode = "";
		responsedesc = "";
		transactionid = "";
		transactiondatetime = "";
	}
	
	public Response(){
		clearProperty();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public String getResponsedesc() {
		return responsedesc;
	}

	public void setResponsedesc(String responsedesc) {
		this.responsedesc = responsedesc;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getTransactiondatetime() {
		return transactiondatetime;
	}

	public void setTransactiondatetime(String transactiondatetime) {
		this.transactiondatetime = transactiondatetime;
	}
	
	
	
}
