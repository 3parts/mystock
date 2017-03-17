package com.cxstock.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Gys entity. @author MyEclipse Persistence Tools
 */

public class Gys implements java.io.Serializable {

	// Fields

	private Integer gysid;
	private String name;
	private String lxren;
	private String lxtel;
	private String address;
	private String bz;
	private Integer companyid;
	private String number;
	private String fax;
	private String fsn;
	private Set jhds = new HashSet(0);
	private Set thds = new HashSet(0);

	// Constructors

	/** default constructor */
	public Gys() {
	}

	/** minimal constructor */
	public Gys(String name) {
		this.name = name;
	}

	/** full constructor */
	public Gys(String name, String lxren, String lxtel, String address,
			String bz, Integer companyid, String number, String fax,
			String fsn, Set jhds, Set thds) {
		this.name = name;
		this.lxren = lxren;
		this.lxtel = lxtel;
		this.address = address;
		this.bz = bz;
		this.companyid = companyid;
		this.number = number;
		this.fax = fax;
		this.fsn = fsn;
		this.jhds = jhds;
		this.thds = thds;
	}

	// Property accessors

	public Integer getGysid() {
		return this.gysid;
	}

	public void setGysid(Integer gysid) {
		this.gysid = gysid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLxren() {
		return this.lxren;
	}

	public void setLxren(String lxren) {
		this.lxren = lxren;
	}

	public String getLxtel() {
		return this.lxtel;
	}

	public void setLxtel(String lxtel) {
		this.lxtel = lxtel;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Integer getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFsn() {
		return this.fsn;
	}

	public void setFsn(String fsn) {
		this.fsn = fsn;
	}

	public Set getJhds() {
		return this.jhds;
	}

	public void setJhds(Set jhds) {
		this.jhds = jhds;
	}

	public Set getThds() {
		return this.thds;
	}

	public void setThds(Set thds) {
		this.thds = thds;
	}

}