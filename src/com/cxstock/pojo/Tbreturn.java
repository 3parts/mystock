package com.cxstock.pojo;

/**
 * Tbreturn entity. @author MyEclipse Persistence Tools
 */

public class Tbreturn implements java.io.Serializable {

	// Fields

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
	private Integer icbill;
	private Integer fidel;

	// Constructors

	/** default constructor */
	public Tbreturn() {
	}

	/** full constructor */
	public Tbreturn(String vcNo, Integer gysId, String dtBs, String dtReceived,
			Double deShouldPayMoney, Double deActualPayMoney,
			Integer ipayState, Integer userId, String vcRemark,
			Integer companyId, Integer icbill, Integer fidel) {
		this.vcNo = vcNo;
		this.gysId = gysId;
		this.dtBs = dtBs;
		this.dtReceived = dtReceived;
		this.deShouldPayMoney = deShouldPayMoney;
		this.deActualPayMoney = deActualPayMoney;
		this.ipayState = ipayState;
		this.userId = userId;
		this.vcRemark = vcRemark;
		this.companyId = companyId;
		this.icbill = icbill;
		this.fidel = fidel;
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

	public Integer getGysId() {
		return this.gysId;
	}

	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}

	public String getDtBs() {
		return this.dtBs;
	}

	public void setDtBs(String dtBs) {
		this.dtBs = dtBs;
	}

	public String getDtReceived() {
		return this.dtReceived;
	}

	public void setDtReceived(String dtReceived) {
		this.dtReceived = dtReceived;
	}

	public Double getDeShouldPayMoney() {
		return this.deShouldPayMoney;
	}

	public void setDeShouldPayMoney(Double deShouldPayMoney) {
		this.deShouldPayMoney = deShouldPayMoney;
	}

	public Double getDeActualPayMoney() {
		return this.deActualPayMoney;
	}

	public void setDeActualPayMoney(Double deActualPayMoney) {
		this.deActualPayMoney = deActualPayMoney;
	}

	public Integer getIpayState() {
		return this.ipayState;
	}

	public void setIpayState(Integer ipayState) {
		this.ipayState = ipayState;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getIcbill() {
		return this.icbill;
	}

	public void setIcbill(Integer icbill) {
		this.icbill = icbill;
	}

	public Integer getFidel() {
		return this.fidel;
	}

	public void setFidel(Integer fidel) {
		this.fidel = fidel;
	}

}