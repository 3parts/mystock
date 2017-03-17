package com.cxstock.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tbcompany entity. @author MyEclipse Persistence Tools
 */

public class Tbcompany implements java.io.Serializable {

	// Fields

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
	private Set userses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tbcompany() {
	}

	/** full constructor */
	public Tbcompany(String vcNumber, String vcName, Date dtBegeindate,
			Date dtEnddate, String VState, String vcCity, String vcAddress,
			String vcContact, String vcTel, String vcMobile, String vcFax,
			String vcQq, String vcEmail, String vcWeixin, String vcAccountnum,
			String vcAccountname, String vcBank, String vcLogo,
			String vcRemark, String vcWeb, String printInfo1,
			String printInfo2, String printInfo3, String fwx, String vcTable,
			Set userses) {
		this.vcNumber = vcNumber;
		this.vcName = vcName;
		this.dtBegeindate = dtBegeindate;
		this.dtEnddate = dtEnddate;
		this.VState = VState;
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
		this.userses = userses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcNumber() {
		return this.vcNumber;
	}

	public void setVcNumber(String vcNumber) {
		this.vcNumber = vcNumber;
	}

	public String getVcName() {
		return this.vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public Date getDtBegeindate() {
		return this.dtBegeindate;
	}

	public void setDtBegeindate(Date dtBegeindate) {
		this.dtBegeindate = dtBegeindate;
	}

	public Date getDtEnddate() {
		return this.dtEnddate;
	}

	public void setDtEnddate(Date dtEnddate) {
		this.dtEnddate = dtEnddate;
	}

	public String getVState() {
		return this.VState;
	}

	public void setVState(String VState) {
		this.VState = VState;
	}

	public String getVcCity() {
		return this.vcCity;
	}

	public void setVcCity(String vcCity) {
		this.vcCity = vcCity;
	}

	public String getVcAddress() {
		return this.vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	public String getVcContact() {
		return this.vcContact;
	}

	public void setVcContact(String vcContact) {
		this.vcContact = vcContact;
	}

	public String getVcTel() {
		return this.vcTel;
	}

	public void setVcTel(String vcTel) {
		this.vcTel = vcTel;
	}

	public String getVcMobile() {
		return this.vcMobile;
	}

	public void setVcMobile(String vcMobile) {
		this.vcMobile = vcMobile;
	}

	public String getVcFax() {
		return this.vcFax;
	}

	public void setVcFax(String vcFax) {
		this.vcFax = vcFax;
	}

	public String getVcQq() {
		return this.vcQq;
	}

	public void setVcQq(String vcQq) {
		this.vcQq = vcQq;
	}

	public String getVcEmail() {
		return this.vcEmail;
	}

	public void setVcEmail(String vcEmail) {
		this.vcEmail = vcEmail;
	}

	public String getVcWeixin() {
		return this.vcWeixin;
	}

	public void setVcWeixin(String vcWeixin) {
		this.vcWeixin = vcWeixin;
	}

	public String getVcAccountnum() {
		return this.vcAccountnum;
	}

	public void setVcAccountnum(String vcAccountnum) {
		this.vcAccountnum = vcAccountnum;
	}

	public String getVcAccountname() {
		return this.vcAccountname;
	}

	public void setVcAccountname(String vcAccountname) {
		this.vcAccountname = vcAccountname;
	}

	public String getVcBank() {
		return this.vcBank;
	}

	public void setVcBank(String vcBank) {
		this.vcBank = vcBank;
	}

	public String getVcLogo() {
		return this.vcLogo;
	}

	public void setVcLogo(String vcLogo) {
		this.vcLogo = vcLogo;
	}

	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public String getVcWeb() {
		return this.vcWeb;
	}

	public void setVcWeb(String vcWeb) {
		this.vcWeb = vcWeb;
	}

	public String getPrintInfo1() {
		return this.printInfo1;
	}

	public void setPrintInfo1(String printInfo1) {
		this.printInfo1 = printInfo1;
	}

	public String getPrintInfo2() {
		return this.printInfo2;
	}

	public void setPrintInfo2(String printInfo2) {
		this.printInfo2 = printInfo2;
	}

	public String getPrintInfo3() {
		return this.printInfo3;
	}

	public void setPrintInfo3(String printInfo3) {
		this.printInfo3 = printInfo3;
	}

	public String getFwx() {
		return this.fwx;
	}

	public void setFwx(String fwx) {
		this.fwx = fwx;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

}