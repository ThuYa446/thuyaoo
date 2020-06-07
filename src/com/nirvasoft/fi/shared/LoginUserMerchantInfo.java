package com.nirvasoft.fi.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginUserMerchantInfo {
private String loginUser ;
private String merchantId ; 
private String merchantName ;

public void clearProperty(){
	loginUser ="";
	merchantId = "";
	merchantName = "";
}

public LoginUserMerchantInfo(){
	clearProperty();
}

public String getLoginUser() {
	return loginUser;
}

public void setLoginUser(String loginUser) {
	this.loginUser = loginUser;
}

public String getMerchantId() {
	return merchantId;
}

public void setMerchantId(String merchantId) {
	this.merchantId = merchantId;
}

public String getMerchantName() {
	return merchantName;
}

public void setMerchantName(String merchantName) {
	this.merchantName = merchantName;
}


}
