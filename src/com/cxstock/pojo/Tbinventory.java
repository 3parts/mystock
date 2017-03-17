package com.cxstock.pojo;

/**
 * Tbinventory entity. @author MyEclipse Persistence Tools
 */

public class Tbinventory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNo;
	private String dtBs;
	private Integer warehouseId;
	private String vcRemark;
	private Integer userId;
	private Integer istate;
	private Integer checkUserId;
	private String dtCheck;
	private Integer companyId;
	private Integer isOk;

	// Constructors

	/** default constructor */
	public Tbinventory() {
	}

	/** full constructor */
	public Tbinventory(String vcNo, String dtBs, Integer warehouseId,
			String vcRemark, Integer userId, Integer istate,
			Integer checkUserId, String dtCheck, Integer companyId, Integer isOk) {
		this.vcNo = vcNo;
		this.dtBs = dtBs;
		this.warehouseId = warehouseId;
		this.vcRemark = vcRemark;
		this.userId = userId;
		this.istate = istate;
		this.checkUserId = checkUserId;
		this.dtCheck = dtCheck;
		this.companyId = companyId;
		this.isOk = isOk;
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

	public Integer getIstate() {
		return this.istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public Integer getCheckUserId() {
		return this.checkUserId;
	}

	public void setCheckUserId(Integer checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getDtCheck() {
		return this.dtCheck;
	}

	public void setDtCheck(String dtCheck) {
		this.dtCheck = dtCheck;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getIsOk() {
		return this.isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}

}