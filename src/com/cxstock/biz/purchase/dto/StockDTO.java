package com.cxstock.biz.purchase.dto;

public class StockDTO {

	private Integer id;
	private Integer commodityId;
	private Integer warehouseId;
	private String vcBatch;
	private String vcSn;
	private Double dlQty;
	private String vcDw;
	private Integer companyId;

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

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
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

	public Double getDlQty() {
		return dlQty;
	}

	public void setDlQty(Double dlQty) {
		this.dlQty = dlQty;
	}

	public String getVcDw() {
		return vcDw;
	}

	public void setVcDw(String vcDw) {
		this.vcDw = vcDw;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
