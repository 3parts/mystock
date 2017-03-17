package com.cxstock.pojo;

/**
 * Tbtemplate entity. @author MyEclipse Persistence Tools
 */

public class Tbtemplate implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer itype;
	private String vcNo;
	private String vcName;
	private String vcTable;
	private String vccode;
	private String vcRemark;
	private Integer companyId;

	// Constructors

	/** default constructor */
	public Tbtemplate() {
	}

	/** full constructor */
	public Tbtemplate(Integer itype, String vcNo, String vcName,
			String vcTable, String vccode, String vcRemark, Integer companyId) {
		this.itype = itype;
		this.vcNo = vcNo;
		this.vcName = vcName;
		this.vcTable = vcTable;
		this.vccode = vccode;
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

	public Integer getItype() {
		return this.itype;
	}

	public void setItype(Integer itype) {
		this.itype = itype;
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

	public String getVcTable() {
		return this.vcTable;
	}

	public void setVcTable(String vcTable) {
		this.vcTable = vcTable;
	}

	public String getVccode() {
		return this.vccode;
	}

	public void setVccode(String vccode) {
		this.vccode = vccode;
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