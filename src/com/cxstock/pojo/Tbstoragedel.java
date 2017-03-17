package com.cxstock.pojo;

/**
 * Tbstoragedel entity. @author MyEclipse Persistence Tools
 */

public class Tbstoragedel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer storageId;
	private Integer commodityId;
	private Double dePurchaseMoney;
	private Double icount;
	private Double deSumMoney;
	private String vcDw;
	private String vcColor;
	private String vcBatch;
	private String vcSn;
	private Integer warehouseId;
	private String vcRemark;

	// Constructors

	/** default constructor */
	public Tbstoragedel() {
	}

	/** full constructor */
	public Tbstoragedel(Integer storageId, Integer commodityId,
			Double dePurchaseMoney, Double icount, Double deSumMoney,
			String vcDw, String vcColor, String vcBatch, String vcSn,
			Integer warehouseId, String vcRemark) {
		this.storageId = storageId;
		this.commodityId = commodityId;
		this.dePurchaseMoney = dePurchaseMoney;
		this.icount = icount;
		this.deSumMoney = deSumMoney;
		this.vcDw = vcDw;
		this.vcColor = vcColor;
		this.vcBatch = vcBatch;
		this.vcSn = vcSn;
		this.warehouseId = warehouseId;
		this.vcRemark = vcRemark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStorageId() {
		return this.storageId;
	}

	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	public Integer getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Double getDePurchaseMoney() {
		return this.dePurchaseMoney;
	}

	public void setDePurchaseMoney(Double dePurchaseMoney) {
		this.dePurchaseMoney = dePurchaseMoney;
	}

	public Double getIcount() {
		return this.icount;
	}

	public void setIcount(Double icount) {
		this.icount = icount;
	}

	public Double getDeSumMoney() {
		return this.deSumMoney;
	}

	public void setDeSumMoney(Double deSumMoney) {
		this.deSumMoney = deSumMoney;
	}

	public String getVcDw() {
		return this.vcDw;
	}

	public void setVcDw(String vcDw) {
		this.vcDw = vcDw;
	}

	public String getVcColor() {
		return this.vcColor;
	}

	public void setVcColor(String vcColor) {
		this.vcColor = vcColor;
	}

	public String getVcBatch() {
		return this.vcBatch;
	}

	public void setVcBatch(String vcBatch) {
		this.vcBatch = vcBatch;
	}

	public String getVcSn() {
		return this.vcSn;
	}

	public void setVcSn(String vcSn) {
		this.vcSn = vcSn;
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

}