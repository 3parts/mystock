package com.cxstock.biz.ziliao.dto;

import java.util.Date;

public class PsDTO {

	public PsDTO() {
	}

	public PsDTO(Integer id, String vcNo, String vcName, Integer igender,
			String vcNation, String vcIdCard, String vcAddress,
			Integer positionId, String positionName, Integer icomminSsion,
			String vcTel, Integer istate, String dtEntry, String dtQuit,
			String vcQuitReason, String vcRemark, Integer companyId) {
		super();
		this.id = id;
		this.vcNo = vcNo;
		this.vcName = vcName;
		this.igender = igender;
		this.vcNation = vcNation;
		this.vcIdCard = vcIdCard;
		this.vcAddress = vcAddress;
		this.positionId = positionId;
		this.positionName = positionName;
		this.icomminSsion = icomminSsion;
		this.vcTel = vcTel;
		this.istate = istate;
		this.dtEntry = dtEntry;
		this.dtQuit = dtQuit;
		this.vcQuitReason = vcQuitReason;
		this.vcRemark = vcRemark;
		this.companyId = companyId;
	}

	private Integer id;
	private String vcNo;
	private String vcName;
	private Integer igender;
	private String vcNation;
	private String vcIdCard;
	private String vcAddress;
	private Integer positionId;
	private String positionName;

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

	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public Integer getIgender() {
		return igender;
	}

	public void setIgender(Integer igender) {
		this.igender = igender;
	}

	public String getVcNation() {
		return vcNation;
	}

	public void setVcNation(String vcNation) {
		this.vcNation = vcNation;
	}

	public String getVcIdCard() {
		return vcIdCard;
	}

	public void setVcIdCard(String vcIdCard) {
		this.vcIdCard = vcIdCard;
	}

	public String getVcAddress() {
		return vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getIcomminSsion() {
		return icomminSsion;
	}

	public void setIcomminSsion(Integer icomminSsion) {
		this.icomminSsion = icomminSsion;
	}

	public String getVcTel() {
		return vcTel;
	}

	public void setVcTel(String vcTel) {
		this.vcTel = vcTel;
	}

	public Integer getIstate() {
		return istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public String getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(String dtEntry) {
		this.dtEntry = dtEntry;
	}

	public String getDtQuit() {
		return dtQuit;
	}

	public void setDtQuit(String dtQuit) {
		this.dtQuit = dtQuit;
	}

	public String getVcQuitReason() {
		return vcQuitReason;
	}

	public void setVcQuitReason(String vcQuitReason) {
		this.vcQuitReason = vcQuitReason;
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

	private Integer icomminSsion;
	private String vcTel;
	private Integer istate;
	private String dtEntry;
	private String dtQuit;
	private String vcQuitReason;
	private String vcRemark;
	private Integer companyId;

}
