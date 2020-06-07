package com.nirvasoft.fi.shared;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DetailsData {
	private long sysKey;
	private long hkey;	
	private int srno;
	private String code;
	private String description;
	private double price;
	private double amount;
	private int quantity;
	
	public DetailsData(){
		clearProperty();
	}
	private void clearProperty() {
	
	}


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getSysKey() {
		return sysKey;
	}
	public void setSysKey(long sysKey) {
		this.sysKey = sysKey;
	}
	public long getHkey() {
		return hkey;
	}
	public void setHkey(long hkey) {
		this.hkey = hkey;
	}

		

}
