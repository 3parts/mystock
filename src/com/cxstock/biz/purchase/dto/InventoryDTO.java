package com.cxstock.biz.purchase.dto;

public class InventoryDTO {

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

	private String warehouseName;
	private String userName;
	private String checkUserName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcNo() {
		return vcNo;
	}

	public void setVcNo(String vcNo) {
		this.vcNo = vcNo;
	}

	public String getDtBs() {
		return dtBs;
	}

	public void setDtBs(String dtBs) {
		this.dtBs = dtBs;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIstate() {
		return istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public Integer getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(Integer checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getDtCheck() {
		return dtCheck;
	}

	public void setDtCheck(String dtCheck) {
		this.dtCheck = dtCheck;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getIsOk() {
		return isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

}
