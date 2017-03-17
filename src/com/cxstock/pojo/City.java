package com.cxstock.pojo;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class City implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cityname;
	private Integer proid;
	private String state;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** minimal constructor */
	public City(String cityname, Integer proid) {
		this.cityname = cityname;
		this.proid = proid;
	}

	/** full constructor */
	public City(String cityname, Integer proid, String state) {
		this.cityname = cityname;
		this.proid = proid;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public Integer getProid() {
		return this.proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}