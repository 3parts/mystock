package com.cxstock.pojo;

/**
 * Tballocation entity. @author MyEclipse Persistence Tools
 */

public class Tballocation implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNo;
	private Integer outId;
	private Integer inId;
	private String dtBs;
	private Double dlCount;
	private Double dlMoney;
	private Integer istate;
	private Integer userId;
	private String vcRemark;
	private Integer companyId;

	// Constructors

	/** default constructor */
	public Tballocation() {
	}

	/** full constructor */
	public Tballocation(String vcNo, Integer outId, Integer inId, String dtBs,
			Double dlCount, Double dlMoney, Integer istate, Integer userId,
			String vcRemark, Integer companyId) {
		this.vcNo = vcNo;
		this.outId = outId;
		this.inId = inId;
		this.dtBs = dtBs;
		this.dlCount = dlCount;
		this.dlMoney = dlMoney;
		this.istate = istate;
		this.userId = userId;
		this.vcRemark = vcRemark;
		this.companyId = companyId;
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

	public Integer getOutId() {
		return this.outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public Integer getInId() {
		return this.inId;
	}

	public void setInId(Integer inId) {
		this.inId = inId;
	}

	public String getDtBs() {
		return this.dtBs;
	}

	public void setDtBs(String dtBs) {
		this.dtBs = dtBs;
	}

	public Double getDlCount() {
		return this.dlCount;
	}

	public void setDlCount(Double dlCount) {
		this.dlCount = dlCount;
	}

	public Double getDlMoney() {
		return this.dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Integer getIstate() {
		return this.istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}