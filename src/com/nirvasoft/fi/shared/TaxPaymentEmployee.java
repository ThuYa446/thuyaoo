package com.nirvasoft.fi.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.TokenFilterDef;

@XmlRootElement
@Indexed
@AnalyzerDef(name = "customAnalyze",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
	    @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
	      @Parameter(name = "language", value = "English")
	    })
	  })
@Entity
@Table(name = "TaxPaymentEmployee")
public class TaxPaymentEmployee implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "syskey")
	private long syskey;
	
	@IndexedEmbedded
	@JsonManagedReference
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "txrg_syskey",referencedColumnName = "syskey")
	private TaxPaymentRegisteration txrg;
	
	@IndexedEmbedded
	@JsonManagedReference
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "txpr_syskey",referencedColumnName = "syskey")
	private TaxPeriod  txpr;
	
	@IndexedEmbedded
	@JsonManagedReference
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "empl_syskey",referencedColumnName = "syskey")
	private Employee empl;
	
	@Field
	@Analyzer(definition = "customAnalyze")
	@Column(name = "employerName")
	private String employerName;
	
	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "endDatePeriod")
	private Date endDatePeriod;
	
	@Field
	@Column(name = "salary")
	private double salary;
	
	@Field
	@Column(name = "tax_amt")
	private double taxAmt;
	
	@Field
	@Column(name = "claim_amt")
	private double claimAmt;
	
	@Field
	@Column(name = "rank")
	private String rank;
	
	@Column(name = "tax_claim_status")
	private String taxClaimStatus;
	
	@Field
	@Column(name = "remark")
	private String remark;

	public TaxPaymentEmployee() {
		super();
		txrg = new TaxPaymentRegisteration();
		txpr = new TaxPeriod();
		empl = new Employee();
		this.clearProperty();
	}

	private void clearProperty(){
		this.syskey = 0;
		this.employerName = "";
		this.endDatePeriod = new Date();
		this.salary = 0;
		this.taxAmt = 0;
		this.claimAmt = 0;
		this.rank = "";
		this.taxClaimStatus = "";
		this.remark = "";
	}
	
	public long getSyskey() {
		return syskey;
	}

	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}

	public TaxPaymentRegisteration getTxrg() {
		return txrg;
	}

	public void setTxrg(TaxPaymentRegisteration txrg) {
		this.txrg = txrg;
	}

	public TaxPeriod getTxpr() {
		return txpr;
	}

	public void setTxpr(TaxPeriod txpr) {
		this.txpr = txpr;
	}

	public Employee getEmpl() {
		return empl;
	}

	public void setEmpl(Employee empl) {
		this.empl = empl;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public Date getEndDatePeriod() {
		return endDatePeriod;
	}

	public void setEndDatePeriod(Date endDatePeriod) {
		this.endDatePeriod = endDatePeriod;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(double taxAmt) {
		this.taxAmt = taxAmt;
	}

	public double getClaimAmt() {
		return claimAmt;
	}

	public void setClaimAmt(double claimAmt) {
		this.claimAmt = claimAmt;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getTaxClaimStatus() {
		return taxClaimStatus;
	}

	public void setTaxClaimStatus(String taxClaimStatus) {
		this.taxClaimStatus = taxClaimStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
