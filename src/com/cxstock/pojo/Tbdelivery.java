package com.cxstock.pojo;

/**
 * Tbdelivery entity. @author MyEclipse Persistence Tools
 */

public class Tbdelivery implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer salenId;
	private Integer khId;
	private String dtBs;
	private String vcSendMan;
	private String vcAddress;
	private Double dlMoeny;
	private Integer ipayState;
	private String dtPay;
	private String vcPayMan;
	private String vcRemark;
	private Integer userId;
	private Integer iprintTimes;
	private Integer companyId;

	// Constructors

	/** default constructor */
	public Tbdelivery() {
	}

	/** full constructor */
	public Tbdelivery(Integer salenId, Integer khId, String dtBs,
			String vcSendMan, String vcAddress, Double dlMoeny,
			Integer ipayState, String dtPay, String vcPayMan, String vcRemark,
			Integer userId, Integer iprintTimes, Integer companyId) {
		this.salenId = salenId;
		this.khId = khId;
		this.dtBs = dtBs;
		this.vcSendMan = vcSendMan;
		this.vcAddress = vcAddress;
		this.dlMoeny = dlMoeny;
		this.ipayState = ipayState;
		this.dtPay = dtPay;
		this.vcPayMan = vcPayMan;
		this.vcRemark = vcRemark;
		this.userId = userId;
		this.iprintTimes = iprintTimes;
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

	public String getVcSendMan() {
		return this.vcSendMan;
	}

	public void setVcSendMan(String vcSendMan) {
		this.vcSendMan = vcSendMan;
	}

	public String getVcAddress() {
		return this.vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	public Double getDlMoeny() {
		return this.dlMoeny;
	}

	public void setDlMoeny(Double dlMoeny) {
		this.dlMoeny = dlMoeny;
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

	public Integer getIprintTimes() {
		return this.iprintTimes;
	}

	public void setIprintTimes(Integer iprintTimes) {
		this.iprintTimes = iprintTimes;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}