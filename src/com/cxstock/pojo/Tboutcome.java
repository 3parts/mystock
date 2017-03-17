package com.cxstock.pojo;

/**
 * Tboutcome entity. @author MyEclipse Persistence Tools
 */

public class Tboutcome implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNo;
	private String dtBs;
	private Integer accountTypeId;
	private Double dlMoney;
	private Integer accountId;
	private String vcAuditor;
	private String vcRemark;
	private String dtWrite;
	private Integer companyId;
	private Integer sourceId;
	private Integer fidel;

	// Constructors

	/** default constructor */
	public Tboutcome() {
	}

	/** full constructor */
	public Tboutcome(String vcNo, String dtBs, Integer accountTypeId,
			Double dlMoney, Integer accountId, String vcAuditor,
			String vcRemark, String dtWrite, Integer companyId,
			Integer sourceId, Integer fidel) {
		this.vcNo = vcNo;
		this.dtBs = dtBs;
		this.accountTypeId = accountTypeId;
		this.dlMoney = dlMoney;
		this.accountId = accountId;
		this.vcAuditor = vcAuditor;
		this.vcRemark = vcRemark;
		this.dtWrite = dtWrite;
		this.companyId = companyId;
		this.sourceId = sourceId;
		this.fidel = fidel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcNo() {
		return this.vcNo;
	}

	public void setVcNo(String vcNo) {
		this.vcNo = vcNo;
	}

	public String getDtBs() {
		return this.dtBs;
	}

	public void setDtBs(String dtBs) {
		this.dtBs = dtBs;
	}

	public Integer getAccountTypeId() {
		return this.accountTypeId;
	}

	public void setAccountTypeId(Integer accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public Double getDlMoney() {
		return this.dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getVcAuditor() {
		return this.vcAuditor;
	}

	public void setVcAuditor(String vcAuditor) {
		this.vcAuditor = vcAuditor;
	}

	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public String getDtWrite() {
		return this.dtWrite;
	}

	public void setDtWrite(String dtWrite) {
		this.dtWrite = dtWrite;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getFidel() {
		return this.fidel;
	}

	public void setFidel(Integer fidel) {
		this.fidel = fidel;
	}

}