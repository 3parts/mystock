package com.cxstock.pojo;

/**
 * Tbuserunitrights entity. @author MyEclipse Persistence Tools
 */

public class Tbuserunitrights implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer companyid;

	// Constructors

	/** default constructor */
	public Tbuserunitrights() {
	}

	/** full constructor */
	public Tbuserunitrights(Integer userid, Integer companyid) {
		this.userid = userid;
		this.companyid = companyid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

}