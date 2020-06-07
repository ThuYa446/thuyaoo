package com.nirvasoft.fi.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@XmlRootElement
@Entity
@Table(name = "Employee")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "syskey")
	private long syskey;
	
	@Field
	@Column(name = "nrc")
	private String nrc;
	
	@Field
	@Column(name= "passport")
	private String passPort;
	
	@Field
	@Column(name= "employeeId",unique = true)
	private String employeeId;
	
	@Field
	@Analyzer(definition = "customAnalyze")
	@Column(name = "name")
	private String name;
	
	@Field
	@DateBridge(resolution = Resolution.DAY)
	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	private Date dob;
	
	@Field
	@Column(name = "current_address1")
	private String currentAdd1;
	
	@Field
	@Column(name = "current_address2")
	private String currentAdd2;
	
	@Field
	@Column(name = "current_address3")
	private String currentAdd3;
	
	@ContainedIn
	@JsonBackReference
	@OneToMany(mappedBy ="empl")
	private List<TaxPaymentEmployee> txpEmpList;

	public Employee(){
		super();
		txpEmpList = new ArrayList<TaxPaymentEmployee>();
		this.clearProperties();
	}
	
	private void clearProperties(){
		this.syskey = 0;
		this.nrc = "";
		this.passPort = "";
		this.employeeId = "";
		this.name = "";
		this.dob = new Date();
		this.currentAdd1 = "";
		this.currentAdd2 = "";
		this.currentAdd3 = "";
	}
	public long getSyskey() {
		return syskey;
	}
	
	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}
	
	public String getNrc() {
		return nrc;
	}
	
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	
	public String getPassPort() {
		return passPort;
	}

	public void setPassPort(String passPort) {
		this.passPort = passPort;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getCurrentAdd1() {
		return currentAdd1;
	}
	
	public void setCurrentAdd1(String currentAdd1) {
		this.currentAdd1 = currentAdd1;
	}
	
	public String getCurrentAdd2() {
		return currentAdd2;
	}
	
	public void setCurrentAdd2(String currentAdd2) {
		this.currentAdd2 = currentAdd2;
	}
	
	public String getCurrentAdd3() {
		return currentAdd3;
	}
	
	public void setCurrentAdd3(String currentAdd3) {
		this.currentAdd3 = currentAdd3;
	}

	public List<TaxPaymentEmployee> getTxpEmpList() {
		return txpEmpList;
	}

	public void setTxpEmpList(List<TaxPaymentEmployee> txpEmpList) {
		this.txpEmpList = txpEmpList;
	}
}
