package com.cxstock.pojo;

/**
 * Province entity. @author MyEclipse Persistence Tools
 */

public class Province implements java.io.Serializable {

	// Fields

	private Integer id;
	private String proname;
	private String type;
	private String state;

	// Constructors

	/** default constructor */
	public Province() {
	}

	/** minimal constructor */
	public Province(String proname) {
		this.proname = proname;
	}

	/** full constructor */
	public Province(String proname, String type, String state) {
		this.proname = proname;
		this.type = type;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}