package com.cxstock.biz.ziliao.dto;

import java.util.Date;

public class QyDTO {
	public QyDTO(Integer id, String vcNumber, String vcName, Date dtBegeindate,
			Date dtEnddate, String vState, String vcCity, String vcAddress,
			String vcContact, String vcTel, String vcMobile, String vcFax,
			String vcQq, String vcEmail, String vcWeixin, String vcAccountnum,
			String vcAccountname, String vcBank, String vcLogo,
			String vcRemark, String vcWeb, String printInfo1,
			String printInfo2, String printInfo3, String fwx) {
		super();
		this.id = id;
		this.vcNumber = vcNumber;
		this.vcName = vcName;
		this.dtBegeindate = dtBegeindate;
		this.dtEnddate = dtEnddate;
		VState = vState;
		this.vcCity = vcCity;
		this.vcAddress = vcAddress;
		this.vcContact = vcContact;
		this.vcTel = vcTel;
		this.vcMobile = vcMobile;
		this.vcFax = vcFax;
		this.vcQq = vcQq;
		this.vcEmail = vcEmail;
		this.vcWeixin = vcWeixin;
		this.vcAccountnum = vcAccountnum;
		this.vcAccountname = vcAccountname;
		this.vcBank = vcBank;
		this.vcLogo = vcLogo;
		this.vcRemark = vcRemark;
		this.vcWeb = vcWeb;
		this.printInfo1 = printInfo1;
		this.printInfo2 = printInfo2;
		this.printInfo3 = printInfo3;
		this.fwx = fwx;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcNumber() {
		return vcNumber;
	}

	public void setVcNumber(String vcNumber) {
		this.vcNumber = vcNumber;
	}

	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public Date getDtBegeindate() {
		return dtBegeindate;
	}

	public void setDtBegeindate(Date dtBegeindate) {
		this.dtBegeindate = dtBegeindate;
	}

	public Date getDtEnddate() {
		return dtEnddate;
	}

	public void setDtEnddate(Date dtEnddate) {
		this.dtEnddate = dtEnddate;
	}

	public String getVState() {
		return VState;
	}

	public void setVState(String vState) {
		VState = vState;
	}

	public String getVcCity() {
		return vcCity;
	}

	public void setVcCity(String vcCity) {
		this.vcCity = vcCity;
	}

	public String getVcAddress() {
		return vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	public String getVcContact() {
		return vcContact;
	}

	public void setVcContact(String vcContact) {
		this.vcContact = vcContact;
	}

	public String getVcTel() {
		return vcTel;
	}

	public void setVcTel(String vcTel) {
		this.vcTel = vcTel;
	}

	public String getVcMobile() {
		return vcMobile;
	}

	public void setVcMobile(String vcMobile) {
		this.vcMobile = vcMobile;
	}

	public String getVcFax() {
		return vcFax;
	}

	public void setVcFax(String vcFax) {
		this.vcFax = vcFax;
	}

	public String getVcQq() {
		return vcQq;
	}

	public void setVcQq(String vcQq) {
		this.vcQq = vcQq;
	}

	public String getVcEmail() {
		return vcEmail;
	}

	public void setVcEmail(String vcEmail) {
		this.vcEmail = vcEmail;
	}

	public String getVcWeixin() {
		return vcWeixin;
	}

	public void setVcWeixin(String vcWeixin) {
		this.vcWeixin = vcWeixin;
	}

	public String getVcAccountnum() {
		return vcAccountnum;
	}

	public void setVcAccountnum(String vcAccountnum) {
		this.vcAccountnum = vcAccountnum;
	}

	public String getVcAccountname() {
		return vcAccountname;
	}

	public void setVcAccountname(String vcAccountname) {
		this.vcAccountname = vcAccountname;
	}

	public String getVcBank() {
		return vcBank;
	}

	public void setVcBank(String vcBank) {
		this.vcBank = vcBank;
	}

	public String getVcLogo() {
		return vcLogo;
	}

	public void setVcLogo(String vcLogo) {
		this.vcLogo = vcLogo;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public void setVcWeb(String vcWeb) {
		this.vcWeb = vcWeb;
	}

	public String getVcWeb() {
		return vcWeb;
	}

	public void setPrintInfo1(String printInfo1) {
		this.printInfo1 = printInfo1;
	}

	public String getPrintInfo1() {
		return printInfo1;
	}

	public void setPrintInfo2(String printInfo2) {
		this.printInfo2 = printInfo2;
	}

	public String getPrintInfo2() {
		return printInfo2;
	}

	public void setPrintInfo3(String printInfo3) {
		this.printInfo3 = printInfo3;
	}

	public String getPrintInfo3() {
		return printInfo3;
	}

	public void setFwx(String fwx) {
		this.fwx = fwx;
	}

	public String getFwx() {
		return fwx;
	}

	private Integer id;
	private String vcNumber;
	private String vcName;
	private Date dtBegeindate;
	private Date dtEnddate;
	private String VState;
	private String vcCity;
	private String vcAddress;
	private String vcContact;
	private String vcTel;
	private String vcMobile;
	private String vcFax;
	private String vcQq;
	private String vcEmail;
	private String vcWeixin;
	private String vcAccountnum;
	private String vcAccountname;
	private String vcBank;
	private String vcLogo;
	private String vcRemark;
	private String vcWeb;
	private String printInfo1;
	private String printInfo2;
	private String printInfo3;
	private String fwx;
}
