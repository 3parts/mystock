package com.cxstock.biz.purchase.dto;

public class StorageDTO {

	private Integer id;
	private String vcNo;
	private Integer gysId;
	private String dtBs;
	private String dtReceived;
	private Double deShouldPayMoney;
	private Double deActualPayMoney;
	private Integer ipayState;
	private Integer userId;
	private String vcRemark;
	private Integer companyId;
	private String gysName;
	private String username;
	private Integer icbill;
	private Integer fidel;

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

	public Integer getGysId() {
		return gysId;
	}

	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}

	public String getDtReceived() {
		return dtReceived;
	}

	public void setDtReceived(String dtReceived) {
		this.dtReceived = dtReceived;
	}

	public Double getDeShouldPayMoney() {
		return deShouldPayMoney;
	}

	public void setDeShouldPayMoney(Double deShouldPayMoney) {
		this.deShouldPayMoney = deShouldPayMoney;
	}

	public Double getDeActualPayMoney() {
		return deActualPayMoney;
	}

	public void setDeActualPayMoney(Double deActualPayMoney) {
		this.deActualPayMoney = deActualPayMoney;
	}

	public Integer getIpayState() {
		return ipayState;
	}

	public void setIpayState(Integer ipayState) {
		this.ipayState = ipayState;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getGysName() {
		return gysName;
	}

	public void setGysName(String gysName) {
		this.gysName = gysName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDtBs(String dtBs) {
		this.dtBs = dtBs;
	}

	public String getDtBs() {
		return dtBs;
	}

	public void setIcbill(Integer icbill) {
		this.icbill = icbill;
	}

	public Integer getIcbill() {
		return icbill;
	}

	public void setFidel(Integer fidel) {
		this.fidel = fidel;
	}

	public Integer getFidel() {
		return fidel;
	}

}
