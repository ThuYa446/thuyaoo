package com.nirvasoft.fi.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="REQUEST")
@XmlAccessorType(XmlAccessType.FIELD)
public class Request {
	@XmlElement(name="ID", required=true) 
	private String id;
	@XmlElement(name="TransactionDateTime", required=true) 
	private String transactiondatetime;
	@XmlElement(name="DRAcc", required=true) 
	private String dracc;
	@XmlElement(name="CRAcc", required=true) 
	private String cracc; 
	@XmlElement(name="DRCcy", required=true) 
	private String drccy;
	@XmlElement(name="CRCcy", required=true) 
	private String crccy; 
	@XmlElement(name="TotalAmount") 
	private double totalamount; 
	@XmlElement(name="CommissionCharges") 
	private double commissioncharges; 
	@XmlElement(name="IBLCharges") 
	private double iblcharges; 
	@XmlElement(name="AgentID", required=true) 
	private String agentid; 
	@XmlElement(name="BankShortCode", required=true) 
	private String bankshortcode; 
	@XmlElement(name="MerchantID", required=true) 
	private String merchantid; 
	@XmlElement(name="MerchantName", required=true) 
	private String merchantname; 
	@XmlElement(name="Description", required=true) 
	private String description; 	
	@XmlElement(name="CustomerName", required=true) 
	private String customername;	
	@XmlElement(name="IsVIP") 
	private String isvip;
	
//	void clearProperty(){
//		id = "";
//		transactiondatetime = "";
//		dracc = "";
//		cracc = "";
//		drccy = "";
//		crccy = "";		
//		totalamount = 0.00;
//		commissioncharges  = 0.00;
//		iblcharges = 0.00;
//		agentid = "";
//		bankshortcode = "";
//		merchantid = "";
//		merchantname = "";
//		description = "";
//		customername = "";
//		isvip = "";
//	}
//	
//	public Request(){
//		clearProperty();
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransactiondatetime() {
		return transactiondatetime;
	}

	public void setTransactiondatetime(String transactiondatetime) {
		this.transactiondatetime = transactiondatetime;
	}

	public String getDracc() {
		return dracc;
	}

	public void setDracc(String dracc) {
		this.dracc = dracc;
	}

	public String getCracc() {
		return cracc;
	}

	public void setCracc(String cracc) {
		this.cracc = cracc;
	}

	public String getDrccy() {
		return drccy;
	}

	public void setDrccy(String drccy) {
		this.drccy = drccy;
	}

	public String getCrccy() {
		return crccy;
	}

	public void setCrccy(String crccy) {
		this.crccy = crccy;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public double getCommissioncharges() {
		return commissioncharges;
	}

	public void setCommissioncharges(double commissioncharges) {
		this.commissioncharges = commissioncharges;
	}

	public double getIblcharges() {
		return iblcharges;
	}

	public void setIblcharges(double iblcharges) {
		this.iblcharges = iblcharges;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getBankshortcode() {
		return bankshortcode;
	}

	public void setBankshortcode(String bankshortcode) {
		this.bankshortcode = bankshortcode;
	}

	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getMerchantname() {
		return merchantname;
	}

	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getIsvip() {
		return isvip;
	}

	public void setIsvip(String isvip) {
		this.isvip = isvip;
	}

	
	
	
}
