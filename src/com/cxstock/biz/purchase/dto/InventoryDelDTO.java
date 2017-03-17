package com.cxstock.biz.purchase.dto;

public class InventoryDelDTO {

	private Integer id;
	private Integer inventoryId;
	private Integer commodityId;
	private String vcBatch;
	private String vcSn;
	private Integer warehouseId;
	private Double dlBefore;
	private Double dlActual;
	private Integer stockId;

	private String commodityName;
	private String commodityNo;
	private String commodityGg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
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

	public Double getDlBefore() {
		return dlBefore;
	}

	public void setDlBefore(Double dlBefore) {
		this.dlBefore = dlBefore;
	}

	public Double getDlActual() {
		return dlActual;
	}

	public void setDlActual(Double dlActual) {
		this.dlActual = dlActual;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public String getCommodityGg() {
		return commodityGg;
	}

	public void setCommodityGg(String commodityGg) {
		this.commodityGg = commodityGg;
	}

}
