package com.cxstock.pojo;

/**
 * Tbap entity. @author MyEclipse Persistence Tools
 */

public class Tbap implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNo;
	private String dtBs;
	private Integer gysId;
	private Integer sourceId;
	private Integer sourceType;
	private Integer logisticsId;
	private Integer settlementId;
	private Double dlMoney;
	private Integer istate;
	private String vcAuditor;
	private String dtJsDate;
	private String vcRemark;
	private String dtWrite;
	private Integer userId;
	private Integer companyId;
	private Integer fidel;

	// Constructors

	/** default constructor */
	public Tbap() {
	}

	/** full constructor */
	public Tbap(String vcNo, String dtBs, Integer gysId, Integer sourceId,
			Integer sourceType, Integer logisticsId, Integer settlementId,
			Double dlMoney, Integer istate, String vcAuditor, String dtJsDate,
			String vcRemark, String dtWrite, Integer userId, Integer companyId,
			Integer fidel) {
		this.vcNo = vcNo;
		this.dtBs = dtBs;
		this.gysId = gysId;
		this.sourceId = sourceId;
		this.sourceType = sourceType;
		this.logisticsId = logisticsId;
		this.settlementId = settlementId;
		this.dlMoney = dlMoney;
		this.istate = istate;
		this.vcAuditor = vcAuditor;
		this.dtJsDate = dtJsDate;
		this.vcRemark = vcRemark;
		this.dtWrite = dtWrite;
		this.userId = userId;
		this.companyId = companyId;
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

	public String getDtBs() {
		return this.dtBs;
	}

	public void setDtBs(String dtBs) {
		this.dtBs = dtBs;
	}

	public Integer getGysId() {
		return this.gysId;
	}

	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}

	public Integer getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getLogisticsId() {
		return this.logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public Integer getSettlementId() {
		return this.settlementId;
	}

	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}

	public Double getDlMoney() {
		return this.dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Integer getIstate() {
		return this.istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public String getVcAuditor() {
		return this.vcAuditor;
	}

	public void setVcAuditor(String vcAuditor) {
		this.vcAuditor = vcAuditor;
	}

	public String getDtJsDate() {
		return this.dtJsDate;
	}

	public void setDtJsDate(String dtJsDate) {
		this.dtJsDate = dtJsDate;
	}

	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public String getDtWrite() {
		return this.dtWrite;
	}

	public void setDtWrite(String dtWrite) {
		this.dtWrite = dtWrite;
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

	public Integer getFidel() {
		return this.fidel;
	}

	public void setFidel(Integer fidel) {
		this.fidel = fidel;
	}

}