package com.cxstock.pojo;

/**
 * Tbinventorydel entity. @author MyEclipse Persistence Tools
 */

public class Tbinventorydel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer inventoryId;
	private Integer commodityId;
	private String vcBatch;
	private String vcSn;
	private Integer warehouseId;
	private Double dlBefore;
	private Double dlActual;
	private Integer stockId;

	// Constructors

	/** default constructor */
	public Tbinventorydel() {
	}

	/** full constructor */
	public Tbinventorydel(Integer inventoryId, Integer commodityId,
			String vcBatch, String vcSn, Integer warehouseId, Double dlBefore,
			Double dlActual, Integer stockId) {
		this.inventoryId = inventoryId;
		this.commodityId = commodityId;
		this.vcBatch = vcBatch;
		this.vcSn = vcSn;
		this.warehouseId = warehouseId;
		this.dlBefore = dlBefore;
		this.dlActual = dlActual;
		this.stockId = stockId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
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

	public Double getDlBefore() {
		return this.dlBefore;
	}

	public void setDlBefore(Double dlBefore) {
		this.dlBefore = dlBefore;
	}

	public Double getDlActual() {
		return this.dlActual;
	}

	public void setDlActual(Double dlActual) {
		this.dlActual = dlActual;
	}

	public Integer getStockId() {
		return this.stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

}