package com.cxstock.pojo;

/**
 * Tballocationdel entity. @author MyEclipse Persistence Tools
 */

public class Tballocationdel implements java.io.Serializable {

	// Fields

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

	// Constructors

	/** default constructor */
	public Tballocationdel() {
	}

	/** full constructor */
	public Tballocationdel(Integer allocationId, Integer commodityId,
			String vcBatch, String vcSn, Double dlCount, String vcDw,
			Double dlMoney, Integer outId, Integer inId, String vcRemark,
			Integer comstockId, Integer gostockId) {
		this.allocationId = allocationId;
		this.commodityId = commodityId;
		this.vcBatch = vcBatch;
		this.vcSn = vcSn;
		this.dlCount = dlCount;
		this.vcDw = vcDw;
		this.dlMoney = dlMoney;
		this.outId = outId;
		this.inId = inId;
		this.vcRemark = vcRemark;
		this.comstockId = comstockId;
		this.gostockId = gostockId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAllocationId() {
		return this.allocationId;
	}

	public void setAllocationId(Integer allocationId) {
		this.allocationId = allocationId;
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

	public Double getDlCount() {
		return this.dlCount;
	}

	public void setDlCount(Double dlCount) {
		this.dlCount = dlCount;
	}

	public String getVcDw() {
		return this.vcDw;
	}

	public void setVcDw(String vcDw) {
		this.vcDw = vcDw;
	}

	public Double getDlMoney() {
		return this.dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Integer getOutId() {
		return this.outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public Integer getInId() {
		return this.inId;
	}

	public void setInId(Integer inId) {
		this.inId = inId;
	}

	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getComstockId() {
		return this.comstockId;
	}

	public void setComstockId(Integer comstockId) {
		this.comstockId = comstockId;
	}

	public Integer getGostockId() {
		return this.gostockId;
	}

	public void setGostockId(Integer gostockId) {
		this.gostockId = gostockId;
	}

}