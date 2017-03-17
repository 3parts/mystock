package com.cxstock.pojo;

/**
 * Tbbalance entity. @author MyEclipse Persistence Tools
 */

public class Tbbalance implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer accountId;
	private String dtBs;
	private Double dlMoney;
	private Double dlBalanceMoney;
	private Integer companyId;
	private Integer sourceId;
	private Integer sourceType;

	// Constructors

	/** default constructor */
	public Tbbalance() {
	}

	/** full constructor */
	public Tbbalance(Integer accountId, String dtBs, Double dlMoney,
			Double dlBalanceMoney, Integer companyId, Integer sourceId,
			Integer sourceType) {
		this.accountId = accountId;
		this.dtBs = dtBs;
		this.dlMoney = dlMoney;
		this.dlBalanceMoney = dlBalanceMoney;
		this.companyId = companyId;
		this.sourceId = sourceId;
		this.sourceType = sourceType;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getDtBs() {
		return this.dtBs;
	}

	public void setDtBs(String dtBs) {
		this.dtBs = dtBs;
	}

	public Double getDlMoney() {
		return this.dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Double getDlBalanceMoney() {
		return this.dlBalanceMoney;
	}

	public void setDlBalanceMoney(Double dlBalanceMoney) {
		this.dlBalanceMoney = dlBalanceMoney;
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

	public Integer getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

}