package com.cxstock.pojo;

/**
 * Tbstock entity. @author MyEclipse Persistence Tools
 */

public class Tbstock implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer commodityId;
	private Integer warehouseId;
	private String vcBatch;
	private String vcSn;
	private Double dlQty;
	private String vcDw;
	private Integer companyId;

	// Constructors

	/** default constructor */
	public Tbstock() {
	}

	/** full constructor */
	public Tbstock(Integer commodityId, Integer warehouseId, String vcBatch,
			String vcSn, Double dlQty, String vcDw, Integer companyId) {
		this.commodityId = commodityId;
		this.warehouseId = warehouseId;
		this.vcBatch = vcBatch;
		this.vcSn = vcSn;
		this.dlQty = dlQty;
		this.vcDw = vcDw;
		this.companyId = companyId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
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

	public Double getDlQty() {
		return this.dlQty;
	}

	public void setDlQty(Double dlQty) {
		this.dlQty = dlQty;
	}

	public String getVcDw() {
		return this.vcDw;
	}

	public void setVcDw(String vcDw) {
		this.vcDw = vcDw;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}