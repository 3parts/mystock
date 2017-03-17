package com.cxstock.pojo;

/**
 * Tbposition entity. @author MyEclipse Persistence Tools
 */

public class Tbposition implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNo;
	private String vcName;
	private String vcRemark;
	private Integer companyId;

	// Constructors

	/** default constructor */
	public Tbposition() {
	}

	/** full constructor */
	public Tbposition(String vcNo, String vcName, String vcRemark,
			Integer companyId) {
		this.vcNo = vcNo;
		this.vcName = vcName;
		this.vcRemark = vcRemark;
		this.companyId = companyId;
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

}