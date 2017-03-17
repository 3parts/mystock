package com.cxstock.pojo;

/**
 * Tbaccounttype entity. @author MyEclipse Persistence Tools
 */

public class Tbaccounttype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNo;
	private String vcName;
	private String vcRemark;
	private Integer companyId;
	private Integer itype;

	// Constructors

	/** default constructor */
	public Tbaccounttype() {
	}

	/** full constructor */
	public Tbaccounttype(String vcNo, String vcName, String vcRemark,
			Integer companyId, Integer itype) {
		this.vcNo = vcNo;
		this.vcName = vcName;
		this.vcRemark = vcRemark;
		this.companyId = companyId;
		this.itype = itype;
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

	public String getVcName() {
		return this.vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
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

	public Integer getItype() {
		return this.itype;
	}

	public void setItype(Integer itype) {
		this.itype = itype;
	}

}