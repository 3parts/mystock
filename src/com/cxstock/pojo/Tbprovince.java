package com.cxstock.pojo;

/**
 * Tbprovince entity. @author MyEclipse Persistence Tools
 */

public class Tbprovince implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNumber;
	private String vcName;
	private String vcRemark;

	// Constructors

	/** default constructor */
	public Tbprovince() {
	}

	/** full constructor */
	public Tbprovince(String vcNumber, String vcName, String vcRemark) {
		this.vcNumber = vcNumber;
		this.vcName = vcName;
		this.vcRemark = vcRemark;
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

	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

}