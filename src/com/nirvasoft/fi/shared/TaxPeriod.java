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
@Table(name = "TaxPeriod")
public class TaxPeriod implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "syskey")
	private long syskey;
	
	@Field
	@Analyzer(definition = "customAnalyze")
	@Column(name = "period_code",unique = true)
	private String periodCode;
	
	@Field
	@Column(name = "start_month")
	private String startMonth;
	
	@Field
	@Column(name = "end_month")
	private String endMonth;
	
	@Field
	@Column(name = "start_year")
	private String startYear;
	
	@Field
	@Column(name = "end_year")
	private String endYear;
	
	@Field
	@DateBridge(resolution = Resolution.DAY)
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
	
	@Field
	@Column(name = "type")
	private String type;
	
	@ContainedIn
	@JsonBackReference
	@OneToMany(mappedBy ="txpr")
	private List<TaxPaymentEmployee> txpEmpList;
	
	public TaxPeriod() {
		super();
		txpEmpList = new ArrayList<TaxPaymentEmployee>();
		this.clearProperties();
	}
	
	private void clearProperties(){
		this.syskey = 0;
		this.periodCode = "";
		this.startMonth = "";
		this.endMonth = "";
		this.startYear = "";
		this.endYear = "";
		this.date = new Date();
		this.type = "";
	}
	
	public long getSyskey() {
		return syskey;
	}
	
	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}
	
	public String getPeriodCode() {
		return periodCode;
	}
	
	public void setPeriodCode(String periodCode) {
		this.periodCode = periodCode;
	}
	
	public String getStartMonth() {
		return startMonth;
	}
	
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	
	public String getEndMonth() {
		return endMonth;
	}
	
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	
	public String getStartYear() {
		return startYear;
	}
	
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	
	public String getEndYear() {
		return endYear;
	}
	
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public List<TaxPaymentEmployee> getTxpEmpList() {
		return txpEmpList;
	}

	public void setTxpEmpList(List<TaxPaymentEmployee> txpEmpList) {
		this.txpEmpList = txpEmpList;
	}
}
