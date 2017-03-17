package com.cxstock.pojo;

/**
 * Tbsalenout entity. @author MyEclipse Persistence Tools
 */

public class Tbsalenout implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNo;
	private String dtBs;
	private Integer ikh;
	private String vcTel;
	private Integer isaleType;
	private Integer isettlement;
	private Integer ipay;
	private Integer userId;
	private String vcAddress;
	private Integer ilogistics;
	private Integer iwl;
	private String vcYunNo;
	private Double deOutMoney;
	private Double deDiscount;
	private Double deOkOutMoney;
	private Double deOweMoney;
	private Double deOtherMoeny;
	private String vcRemark;
	private Integer isaleprint;
	private Integer companyId;
	private Integer icbill;
	private Integer fidel;
	private String vcPhone;

	// Constructors

	/** default constructor */
	public Tbsalenout() {
	}

	/** full constructor */
	public Tbsalenout(String vcNo, String dtBs, Integer ikh, String vcTel,
			Integer isaleType, Integer isettlement, Integer ipay,
			Integer userId, String vcAddress, Integer ilogistics, Integer iwl,
			String vcYunNo, Double deOutMoney, Double deDiscount,
			Double deOkOutMoney, Double deOweMoney, Double deOtherMoeny,
			String vcRemark, Integer isaleprint, Integer companyId,
			Integer icbill, Integer fidel, String vcPhone) {
		this.vcNo = vcNo;
		this.dtBs = dtBs;
		this.ikh = ikh;
		this.vcTel = vcTel;
		this.isaleType = isaleType;
		this.isettlement = isettlement;
		this.ipay = ipay;
		this.userId = userId;
		this.vcAddress = vcAddress;
		this.ilogistics = ilogistics;
		this.iwl = iwl;
		this.vcYunNo = vcYunNo;
		this.deOutMoney = deOutMoney;
		this.deDiscount = deDiscount;
		this.deOkOutMoney = deOkOutMoney;
		this.deOweMoney = deOweMoney;
		this.deOtherMoeny = deOtherMoeny;
		this.vcRemark = vcRemark;
		this.isaleprint = isaleprint;
		this.companyId = companyId;
		this.icbill = icbill;
		this.fidel = fidel;
		this.vcPhone = vcPhone;
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

	public Integer getIkh() {
		return this.ikh;
	}

	public void setIkh(Integer ikh) {
		this.ikh = ikh;
	}

	public String getVcTel() {
		return this.vcTel;
	}

	public void setVcTel(String vcTel) {
		this.vcTel = vcTel;
	}

	public Integer getIsaleType() {
		return this.isaleType;
	}

	public void setIsaleType(Integer isaleType) {
		this.isaleType = isaleType;
	}

	public Integer getIsettlement() {
		return this.isettlement;
	}

	public void setIsettlement(Integer isettlement) {
		this.isettlement = isettlement;
	}

	public Integer getIpay() {
		return this.ipay;
	}

	public void setIpay(Integer ipay) {
		this.ipay = ipay;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVcAddress() {
		return this.vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	public Integer getIlogistics() {
		return this.ilogistics;
	}

	public void setIlogistics(Integer ilogistics) {
		this.ilogistics = ilogistics;
	}

	public Integer getIwl() {
		return this.iwl;
	}

	public void setIwl(Integer iwl) {
		this.iwl = iwl;
	}

	public String getVcYunNo() {
		return this.vcYunNo;
	}

	public void setVcYunNo(String vcYunNo) {
		this.vcYunNo = vcYunNo;
	}

	public Double getDeOutMoney() {
		return this.deOutMoney;
	}

	public void setDeOutMoney(Double deOutMoney) {
		this.deOutMoney = deOutMoney;
	}

	public Double getDeDiscount() {
		return this.deDiscount;
	}

	public void setDeDiscount(Double deDiscount) {
		this.deDiscount = deDiscount;
	}

	public Double getDeOkOutMoney() {
		return this.deOkOutMoney;
	}

	public void setDeOkOutMoney(Double deOkOutMoney) {
		this.deOkOutMoney = deOkOutMoney;
	}

	public Double getDeOweMoney() {
		return this.deOweMoney;
	}

	public void setDeOweMoney(Double deOweMoney) {
		this.deOweMoney = deOweMoney;
	}

	public Double getDeOtherMoeny() {
		return this.deOtherMoeny;
	}

	public void setDeOtherMoeny(Double deOtherMoeny) {
		this.deOtherMoeny = deOtherMoeny;
	}

	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getIsaleprint() {
		return this.isaleprint;
	}

	public void setIsaleprint(Integer isaleprint) {
		this.isaleprint = isaleprint;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getIcbill() {
		return this.icbill;
	}

	public void setIcbill(Integer icbill) {
		this.icbill = icbill;
	}

	public Integer getFidel() {
		return this.fidel;
	}

	public void setFidel(Integer fidel) {
		this.fidel = fidel;
	}

	public String getVcPhone() {
		return this.vcPhone;
	}

	public void setVcPhone(String vcPhone) {
		this.vcPhone = vcPhone;
	}

}