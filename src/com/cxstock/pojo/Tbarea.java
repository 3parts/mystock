package com.cxstock.pojo;

/**
 * Tbarea entity. @author MyEclipse Persistence Tools
 */

public class Tbarea implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcNumber;
	private String vcName;
	private String vcRemark;
	private Integer cityId;

	// Constructors

	/** default constructor */
	public Tbarea() {
	}

	/** full constructor */
	public Tbarea(String vcNumber, String vcName, String vcRemark,
			Integer cityId) {
		this.vcNumber = vcNumber;
		this.vcName = vcName;
		this.vcRemark = vcRemark;
		this.cityId = cityId;
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

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}