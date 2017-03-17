package com.cxstock.biz.purchase.dto;

public class AllocationDelDTO {

	private Integer id;
	private Integer allocationId;
	private Integer commodityId;
	private String vcBatch;
	private String vcSn;
	private Double dlCount;
	private String vcDw;
	private Double dlMoney;
	private Integer outId;
	private Integer inId;
	private String vcRemark;
	private Integer comstockId;
	private Integer gostockId;

	private String commodityName;
	private String commodityNo;
	private String commodityGg;
	private String outName;
	private String inName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(Integer allocationId) {
		this.allocationId = allocationId;
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

	public Double getDlCount() {
		return dlCount;
	}

	public void setDlCount(Double dlCount) {
		this.dlCount = dlCount;
	}

	public String getVcDw() {
		return vcDw;
	}

	public void setVcDw(String vcDw) {
		this.vcDw = vcDw;
	}

	public Double getDlMoney() {
		return dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
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

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setComstockId(Integer comstockId) {
		this.comstockId = comstockId;
	}

	public Integer getComstockId() {
		return comstockId;
	}

	public void setGostockId(Integer gostockId) {
		this.gostockId = gostockId;
	}

	public Integer getGostockId() {
		return gostockId;
	}

}
