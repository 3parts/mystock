package com.cxstock.biz.purchase.dto;

public class AllocationDTO {

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

	private String outName;
	private String inName;
	private String userName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcNo() {
		return vcNo;
	}

	public void setVcNo(String vcNo) {
		this.vcNo = vcNo;
	}

	public Integer getOutId() {
		return outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public Integer getInId() {
		return inId;
	}

	public void setInId(Integer inId) {
		this.inId = inId;
	}

	public String getDtBs() {
		return dtBs;
	}

	public void setDtBs(String dtBs) {
		this.dtBs = dtBs;
	}

	public Double getDlCount() {
		return dlCount;
	}

	public void setDlCount(Double dlCount) {
		this.dlCount = dlCount;
	}

	public Double getDlMoney() {
		return dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Integer getIstate() {
		return istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public String getInName() {
		return inName;
	}

	public void setInName(String inName) {
		this.inName = inName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
