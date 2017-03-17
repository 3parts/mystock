package com.cxstock.pojo;

/**
 * Tbsalenoutdel entity. @author MyEclipse Persistence Tools
 */

public class Tbsalenoutdel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer salenoutId;
	private Integer commodityId;
	private String vcBatch;
	private String vcSn;
	private Integer warehouseId;
	private Double icount;
	private String vcDw;
	private Double dePriceMoney;
	private Double deSumMoney;
	private Double deDiscount;

	// Constructors

	/** default constructor */
	public Tbsalenoutdel() {
	}

	/** full constructor */
	public Tbsalenoutdel(Integer salenoutId, Integer commodityId,
			String vcBatch, String vcSn, Integer warehouseId, Double icount,
			String vcDw, Double dePriceMoney, Double deSumMoney,
			Double deDiscount) {
		this.salenoutId = salenoutId;
		this.commodityId = commodityId;
		this.vcBatch = vcBatch;
		this.vcSn = vcSn;
		this.warehouseId = warehouseId;
		this.icount = icount;
		this.vcDw = vcDw;
		this.dePriceMoney = dePriceMoney;
		this.deSumMoney = deSumMoney;
		this.deDiscount = deDiscount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalenoutId() {
		return this.salenoutId;
	}

	public void setSalenoutId(Integer salenoutId) {
		this.salenoutId = salenoutId;
	}

	public Integer getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
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

	public Double getIcount() {
		return this.icount;
	}

	public void setIcount(Double icount) {
		this.icount = icount;
	}

	public String getVcDw() {
		return this.vcDw;
	}

	public void setVcDw(String vcDw) {
		this.vcDw = vcDw;
	}

	public Double getDePriceMoney() {
		return this.dePriceMoney;
	}

	public void setDePriceMoney(Double dePriceMoney) {
		this.dePriceMoney = dePriceMoney;
	}

	public Double getDeSumMoney() {
		return this.deSumMoney;
	}

	public void setDeSumMoney(Double deSumMoney) {
		this.deSumMoney = deSumMoney;
	}

	public Double getDeDiscount() {
		return this.deDiscount;
	}

	public void setDeDiscount(Double deDiscount) {
		this.deDiscount = deDiscount;
	}

}