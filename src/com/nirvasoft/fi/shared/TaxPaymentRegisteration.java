package com.nirvasoft.fi.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@XmlRootElement
@Entity
@Table(name = "TaxPaymentRegisteration")
public class TaxPaymentRegisteration implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "syskey")
	private long syskey;
	
	@Field
	@Column(name = "RecordStatus")
	private int recordStatus;
	
	@Field
	@Column(name = "taxPayerName")
	private String taxPayerName;
	
	@Field
	@Column(name = "BusinessName")
	private String businessName;
	
	@Field
	@Column(name = "taxPayerPhNo")
	private String taxPayerPhNo;
	
	@Field
	@Analyzer(definition = "customAnalyze")
	@Column(name = "taxPayerID",unique = true)
	private String taxPayerID;
	
	@Field
	@Column(name = "contactEmail")
	private String contactEmail;
	
	@Field
	@Column(name = "taxPayerAddr1")
	private String taxPayerAddr1;
	
	@Field
	@Column(name = "taxPayerAddr2")
	private String taxPayerAddr2;
	
	@Field
	@Column(name = "taxPayerAddr3")
	private String taxPayerAddr3;
	
	@Field
	@Column(name = "taxPayerRegion")
	private String taxPayerRegion;
	
	@Field
	@Column(name = "taxPayerTownship")
	private String taxPayerTownship;
	
	@Field
	@Column(name = "taxPayerDistrict")
	private String taxPayerDistrict;
	
	@Field
	@Column(name = "bankName")
	private String bankName;
	
	@Field
	@Column(name = "bankBranchName")
	private String bankBranchName;
	
	@Field
	@Column(name = "bankBranchCode")
	private String bankBranchCode;
	
	@Field
	@Column(name = "TaxCode")
	private String taxCode;
	
	@Field
	@Column(name = "MD_Number")
	private String mdNumber;
	
	@Field
	@Column(name = "TaxbranchName")
	private String taxBranchName;
	
	@Field
	@Column(name = "TaxbranchCode")
	private String taxBranchCode;
	
	@Field
	@Column(name = "taxOffice_Address")
	private String taxOfficeAddress;
	
	@Field
	@Column(name = "taxoffice_PhNo")
	private String taxoffice_PhNo;
	
	@Field
	@Column(name = "sign")
	private String sign;
	
	@Field
	@Column(name = "taxOfficerName")
	private String taxOfficerName;
	
	@Field
	@Column(name = "designation")
	private String designation;
	
	@Field
	@Column(name = "taxOffice")
	private String taxOffice;
	
	@Field
	@Column(name = "userID")
	private String userID;
	
	@Field
	@Column(name = "CreatedDate")
	private String createdDate;
	
	@Field
	@Column(name = "ModifiedDate")
	private String modifiedDate;
	
	@Field
	@Column(name = "T1")
	private String t1;
	
	@Field
	@Column(name = "T2")
	private String t2;
	
	@Field
	@Column(name = "T3")
	private String t3;
	
	@Field
	@Column(name = "T4")
	private String t4;
	
	@Field
	@Column(name = "T5")
	private String t5;
	
	@Field
	@Column(name = "N1")
	private int n1;
	
	@Field
	@Column(name = "N2")
	private int n2;
	
	@Field
	@Column(name = "N3")
	private int n3;
	
	@Field
	@Column(name = "N4")
	private int n4;
	
	@Field
	@Column(name = "N5")
	private long n5;
	
	@ContainedIn
	@JsonBackReference
	@OneToMany(mappedBy ="txrg")
	private List<TaxPaymentEmployee> txpEmpList;
	
	public TaxPaymentRegisteration() {
		super();
		txpEmpList = new ArrayList<TaxPaymentEmployee>();
		this.clearProperty();
	}

	public void clearProperty(){
		this.syskey = 0;
		this.recordStatus = 0;
		this.taxPayerName = "";
		this.businessName = "";
		this.taxPayerPhNo = "";
		this.taxPayerID = "";
		this.contactEmail = "";
		this.taxPayerAddr1 = "";
		this.taxPayerAddr2 = "";
		this.taxPayerAddr3 = "";
		this.taxPayerRegion = "";
		this.taxPayerTownship = "";
		this.taxPayerDistrict = "";
		this.bankName = "";
		this.bankBranchName = "";
		this.bankBranchCode = "";
		this.taxCode = "";
		this.mdNumber = "";
		this.taxBranchName = "";
		this.taxBranchCode = "";
		this.taxOfficeAddress = "";
		this.taxoffice_PhNo = "";
		this.sign = "";
		this.taxOfficerName = "";
		this.designation = "";
		this.taxOffice = "";
		this.userID = "";
		this.createdDate = "";
		this.modifiedDate = "";
		this.t1 = "";
		this.t2 = "";
		this.t3 = "";
		this.t4 = "";
		this.t5 = "";
		this.n1 = 0;
		this.n2 = 0;
		this.n3 = 0;
		this.n4 = 0;
		this.n5 = 0;
	}
	
	public long getSyskey() {
		return syskey;
	}
	
	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}
	
	public int getRecordStatus() {
		return recordStatus;
	}
	
	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}
	
	public String getTaxPayerName() {
		return taxPayerName;
	}
	
	public void setTaxPayerName(String taxPayerName) {
		this.taxPayerName = taxPayerName;
	}
	
	public String getBusinessName() {
		return businessName;
	}
	
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	public String getTaxPayerPhNo() {
		return taxPayerPhNo;
	}
	
	public void setTaxPayerPhNo(String taxPayerPhNo) {
		this.taxPayerPhNo = taxPayerPhNo;
	}
	
	public String getTaxPayerID() {
		return taxPayerID;
	}
	
	public void setTaxPayerID(String taxPayerID) {
		this.taxPayerID = taxPayerID;
	}
	
	public String getContactEmail() {
		return contactEmail;
	}
	
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	public String getTaxPayerAddr1() {
		return taxPayerAddr1;
	}
	
	public void setTaxPayerAddr1(String taxPayerAddr1) {
		this.taxPayerAddr1 = taxPayerAddr1;
	}
	
	public String getTaxPayerAddr2() {
		return taxPayerAddr2;
	}
	
	public void setTaxPayerAddr2(String taxPayerAddr2) {
		this.taxPayerAddr2 = taxPayerAddr2;
	}
	
	public String getTaxPayerAddr3() {
		return taxPayerAddr3;
	}
	
	public void setTaxPayerAddr3(String taxPayerAddr3) {
		this.taxPayerAddr3 = taxPayerAddr3;
	}
	
	public String getTaxPayerRegion() {
		return taxPayerRegion;
	}
	
	public void setTaxPayerRegion(String taxPayerRegion) {
		this.taxPayerRegion = taxPayerRegion;
	}
	
	public String getTaxPayerTownship() {
		return taxPayerTownship;
	}
	
	public void setTaxPayerTownship(String taxPayerTownship) {
		this.taxPayerTownship = taxPayerTownship;
	}
	
	public String getTaxPayerDistrict() {
		return taxPayerDistrict;
	}
	
	public void setTaxPayerDistrict(String taxPayerDistrict) {
		this.taxPayerDistrict = taxPayerDistrict;
	}
	
	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBankBranchName() {
		return bankBranchName;
	}
	
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	
	public String getBankBranchCode() {
		return bankBranchCode;
	}
	
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}
	
	public String getTaxCode() {
		return taxCode;
	}
	
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	
	public String getMdNumber() {
		return mdNumber;
	}
	
	public void setMdNumber(String mdNumber) {
		this.mdNumber = mdNumber;
	}
	
	public String getTaxBranchName() {
		return taxBranchName;
	}
	
	public void setTaxBranchName(String taxBranchName) {
		this.taxBranchName = taxBranchName;
	}
	
	public String getTaxBranchCode() {
		return taxBranchCode;
	}
	
	public void setTaxBranchCode(String taxBranchCode) {
		this.taxBranchCode = taxBranchCode;
	}
	
	public String getTaxOfficeAddress() {
		return taxOfficeAddress;
	}
	
	public void setTaxOfficeAddress(String taxOfficeAddress) {
		this.taxOfficeAddress = taxOfficeAddress;
	}
	
	public String getTaxoffice_PhNo() {
		return taxoffice_PhNo;
	}
	
	public void setTaxoffice_PhNo(String taxoffice_PhNo) {
		this.taxoffice_PhNo = taxoffice_PhNo;
	}
	
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String getTaxOfficerName() {
		return taxOfficerName;
	}
	
	public void setTaxOfficerName(String taxOfficerName) {
		this.taxOfficerName = taxOfficerName;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getTaxOffice() {
		return taxOffice;
	}
	
	public void setTaxOffice(String taxOffice) {
		this.taxOffice = taxOffice;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getModifiedDate() {
		return modifiedDate;
	}
	
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public String getT1() {
		return t1;
	}
	
	public void setT1(String t1) {
		this.t1 = t1;
	}
	
	public String getT2() {
		return t2;
	}
	
	public void setT2(String t2) {
		this.t2 = t2;
	}
	
	public String getT3() {
		return t3;
	}
	
	public void setT3(String t3) {
		this.t3 = t3;
	}
	
	public String getT4() {
		return t4;
	}
	
	public void setT4(String t4) {
		this.t4 = t4;
	}
	
	public String getT5() {
		return t5;
	}
	
	public void setT5(String t5) {
		this.t5 = t5;
	}
	
	public int getN1() {
		return n1;
	}
	
	public void setN1(int n1) {
		this.n1 = n1;
	}
	
	public int getN2() {
		return n2;
	}
	
	public void setN2(int n2) {
		this.n2 = n2;
	}
	
	public int getN3() {
		return n3;
	}
	
	public void setN3(int n3) {
		this.n3 = n3;
	}
	
	public int getN4() {
		return n4;
	}
	
	public void setN4(int n4) {
		this.n4 = n4;
	}
	
	public long getN5() {
		return n5;
	}
	
	public void setN5(long n5) {
		this.n5 = n5;
	}

	public List<TaxPaymentEmployee> getTxpEmpList() {
		return txpEmpList;
	}

	public void setTxpEmpList(List<TaxPaymentEmployee> txpEmpList) {
		this.txpEmpList = txpEmpList;
	}
}
