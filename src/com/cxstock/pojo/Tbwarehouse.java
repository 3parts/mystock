package com.cxstock.pojo;

/**
 * Tbwarehouse entity. @author MyEclipse Persistence Tools
 */

public class Tbwarehouse implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNo;
	private String vcName;
	private String vcAddress;
	private String vcRemark;
	private Integer companyId;

	// Constructors

	/** default constructor */
	public Tbwarehouse() {
	}

	/** full constructor */
	public Tbwarehouse(String vcNo, String vcName, String vcAddress,
			String vcRemark, Integer companyId) {
		this.vcNo = vcNo;
		this.vcName = vcName;
		this.vcAddress = vcAddress;
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

	public String getVcAddress() {
		return this.vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
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