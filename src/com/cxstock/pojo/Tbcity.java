package com.cxstock.pojo;

/**
 * Tbcity entity. @author MyEclipse Persistence Tools
 */

public class Tbcity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNumber;
	private String vcName;
	private String vcRemark;
	private Integer proviceid;

	// Constructors

	/** default constructor */
	public Tbcity() {
	}

	/** full constructor */
	public Tbcity(String vcNumber, String vcName, String vcRemark,
			Integer proviceid) {
		this.vcNumber = vcNumber;
		this.vcName = vcName;
		this.vcRemark = vcRemark;
		this.proviceid = proviceid;
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

	public Integer getProviceid() {
		return this.proviceid;
	}

	public void setProviceid(Integer proviceid) {
		this.proviceid = proviceid;
	}

}