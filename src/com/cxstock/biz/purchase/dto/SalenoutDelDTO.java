package com.cxstock.biz.purchase.dto;

public class SalenoutDelDTO {

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

	private String commodityName;
	private String commodityGg;
	private String commodityNo;
	private String warehouseName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalenoutId() {
		return salenoutId;
	}

	public void setSalenoutId(Integer salenoutId) {
		this.salenoutId = salenoutId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public String getVcBatch() {
		return vcBatch;
	}

	public void setVcBatch(String vcBatch) {
		this.vcBatch = vcBatch;
	}

	public String getVcSn() {
		return vcSn;
	}

	public void setVcSn(String vcSn) {
		this.vcSn = vcSn;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Double getIcount() {
		return icount;
	}

	public void setIcount(Double icount) {
		this.icount = icount;
	}

	public String getVcDw() {
		return vcDw;
	}

	public void setVcDw(String vcDw) {
		this.vcDw = vcDw;
	}

	public Double getDePriceMoney() {
		return dePriceMoney;
	}

	public void setDePriceMoney(Double dePriceMoney) {
		this.dePriceMoney = dePriceMoney;
	}

	public Double getDeSumMoney() {
		return deSumMoney;
	}

	public void setDeSumMoney(Double deSumMoney) {
		this.deSumMoney = deSumMoney;
	}

	public Double getDeDiscount() {
		return deDiscount;
	}

	public void setDeDiscount(Double deDiscount) {
		this.deDiscount = deDiscount;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityGg() {
		return commodityGg;
	}

	public void setCommodityGg(String commodityGg) {
		this.commodityGg = commodityGg;
	}

	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

}
