package com.cxstock.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer userid;
	private Tbcompany tbcompany;
	private Role role;
	private String logincode;
	private String password;
	private String username;
	private Integer state;
	private String bz;
	private Integer companyid;

	private Set thds = new HashSet(0);
	private Set tkds = new HashSet(0);
	private Set bsds = new HashSet(0);
	private Set jhds = new HashSet(0);
	private Set ckds = new HashSet(0);
	private Set byds = new HashSet(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String logincode, String password, String username,
			Integer state, Integer companyid) {
		this.logincode = logincode;
		this.password = password;
		this.username = username;
		this.state = state;
		this.companyid = companyid;
	}

	/** full constructor */
	public Users(Tbcompany tbcompany, Role role, String logincode,
			String password, String username, Integer state, String bz,
			Integer companyid, Set thds, Set tkds, Set bsds, Set jhds,
			Set ckds, Set byds) {
		this.tbcompany = tbcompany;
		this.role = role;
		this.logincode = logincode;
		this.password = password;
		this.username = username;
		this.state = state;
		this.bz = bz;
		this.companyid = companyid;
		this.thds = thds;
		this.tkds = tkds;
		this.bsds = bsds;
		this.jhds = jhds;
		this.ckds = ckds;
		this.byds = byds;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Tbcompany getTbcompany() {
		return this.tbcompany;
	}

	public void setTbcompany(Tbcompany tbcompany) {
		this.tbcompany = tbcompany;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLogincode() {
		return this.logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Set getThds() {
		return this.thds;
	}

	public void setThds(Set thds) {
		this.thds = thds;
	}

	public Set getTkds() {
		return this.tkds;
	}

	public void setTkds(Set tkds) {
		this.tkds = tkds;
	}

	public Set getBsds() {
		return this.bsds;
	}

	public void setBsds(Set bsds) {
		this.bsds = bsds;
	}

	public Set getJhds() {
		return this.jhds;
	}

	public void setJhds(Set jhds) {
		this.jhds = jhds;
	}

	public Set getCkds() {
		return this.ckds;
	}

	public void setCkds(Set ckds) {
		this.ckds = ckds;
	}

	public Set getByds() {
		return this.byds;
	}

	public void setByds(Set byds) {
		this.byds = byds;
	}

}