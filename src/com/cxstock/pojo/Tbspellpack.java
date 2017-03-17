package com.cxstock.pojo;

/**
 * Tbspellpack entity. @author MyEclipse Persistence Tools
 */

public class Tbspellpack implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer salenId;
	private Integer khId;
	private String dtBs;
	private String vcSpllName;
	private Double dlMoney;
	private Integer ipayState;
	private String dtPay;
	private String vcPayMan;
	private String vcRemark;
	private Integer userId;
	private Integer companyId;

	// Constructors

	/** default constructor */
	public Tbspellpack() {
	}

	/** full constructor */
	public Tbspellpack(Integer salenId, Integer khId, String dtBs,
			String vcSpllName, Double dlMoney, Integer ipayState, String dtPay,
			String vcPayMan, String vcRemark, Integer userId, Integer companyId) {
		this.salenId = salenId;
		this.khId = khId;
		this.dtBs = dtBs;
		this.vcSpllName = vcSpllName;
		this.dlMoney = dlMoney;
		this.ipayState = ipayState;
		this.dtPay = dtPay;
		this.vcPayMan = vcPayMan;
		this.vcRemark = vcRemark;
		this.userId = userId;
		this.companyId = companyId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalenId() {
		return this.salenId;
	}

	public void setSalenId(Integer salenId) {
		this.salenId = salenId;
	}

	public Integer getKhId() {
		return this.khId;
	}

	public void setKhId(Integer khId) {
		this.khId = khId;
	}

	public String getDtBs() {
		return this.dtBs;
	}

	public void setDtBs(String dtBs) {
		this.dtBs = dtBs;
	}

	public String getVcSpllName() {
		return this.vcSpllName;
	}

	public void setVcSpllName(String vcSpllName) {
		this.vcSpllName = vcSpllName;
	}

	public Double getDlMoney() {
		return this.dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Integer getIpayState() {
		return this.ipayState;
	}

	public void setIpayState(Integer ipayState) {
		this.ipayState = ipayState;
	}

	public String getDtPay() {
		return this.dtPay;
	}

	public void setDtPay(String dtPay) {
		this.dtPay = dtPay;
	}

	public String getVcPayMan() {
		return this.vcPayMan;
	}

	public void setVcPayMan(String vcPayMan) {
		this.vcPayMan = vcPayMan;
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

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}