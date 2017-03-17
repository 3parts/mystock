package com.cxstock.pojo;

/**
 * Tbsalenback entity. @author MyEclipse Persistence Tools
 */

public class Tbsalenback implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNo;
	private String dtBs;
	private Integer khId;
	private Integer ipayType;
	private Double deMoney;
	private Double deOkMoney;
	private Double deOtherMoney;
	private Integer warehouseId;
	private String vcRemark;
	private Integer userId;
	private Integer companyId;
	private Integer icbill;
	private Integer fidel;

	// Constructors

	/** default constructor */
	public Tbsalenback() {
	}

	/** full constructor */
	public Tbsalenback(String vcNo, String dtBs, Integer khId,
			Integer ipayType, Double deMoney, Double deOkMoney,
			Double deOtherMoney, Integer warehouseId, String vcRemark,
			Integer userId, Integer companyId, Integer icbill, Integer fidel) {
		this.vcNo = vcNo;
		this.dtBs = dtBs;
		this.khId = khId;
		this.ipayType = ipayType;
		this.deMoney = deMoney;
		this.deOkMoney = deOkMoney;
		this.deOtherMoney = deOtherMoney;
		this.warehouseId = warehouseId;
		this.vcRemark = vcRemark;
		this.userId = userId;
		this.companyId = companyId;
		this.icbill = icbill;
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

	public Integer getKhId() {
		return this.khId;
	}

	public void setKhId(Integer khId) {
		this.khId = khId;
	}

	public Integer getIpayType() {
		return this.ipayType;
	}

	public void setIpayType(Integer ipayType) {
		this.ipayType = ipayType;
	}

	public Double getDeMoney() {
		return this.deMoney;
	}

	public void setDeMoney(Double deMoney) {
		this.deMoney = deMoney;
	}

	public Double getDeOkMoney() {
		return this.deOkMoney;
	}

	public void setDeOkMoney(Double deOkMoney) {
		this.deOkMoney = deOkMoney;
	}

	public Double getDeOtherMoney() {
		return this.deOtherMoney;
	}

	public void setDeOtherMoney(Double deOtherMoney) {
		this.deOtherMoney = deOtherMoney;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

}