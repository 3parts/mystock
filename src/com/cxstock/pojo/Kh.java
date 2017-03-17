package com.cxstock.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Kh entity. @author MyEclipse Persistence Tools
 */

public class Kh implements java.io.Serializable {

	// Fields

	private Integer khid;
	private String khname;
	private String pycode;
	private String lxren;
	private String lxtel;
	private String address;
	private String bz;
	private String country;
	private String province;
	private String city;
	private String distincts;
	private String mobile;
	private String fax;
	private String qq;
	private String email;
	private String shippingAddress;
	private String shippintClear;
	private String shippingType;
	private String shippingLog;
	private Integer credit;
	private String khnum;
	private Integer companyid;
	private Double deOwe;
	private Set tkds = new HashSet(0);
	private Set ckds = new HashSet(0);

	// Constructors

	/** default constructor */
	public Kh() {
	}

	/** minimal constructor */
	public Kh(String khname) {
		this.khname = khname;
	}

	/** full constructor */
	public Kh(String khname, String pycode, String lxren, String lxtel,
			String address, String bz, String country, String province,
			String city, String distincts, String mobile, String fax,
			String qq, String email, String shippingAddress,
			String shippintClear, String shippingType, String shippingLog,
			Integer credit, String khnum, Integer companyid, Double deOwe,
			Set tkds, Set ckds) {
		this.khname = khname;
		this.pycode = pycode;
		this.lxren = lxren;
		this.lxtel = lxtel;
		this.address = address;
		this.bz = bz;
		this.country = country;
		this.province = province;
		this.city = city;
		this.distincts = distincts;
		this.mobile = mobile;
		this.fax = fax;
		this.qq = qq;
		this.email = email;
		this.shippingAddress = shippingAddress;
		this.shippintClear = shippintClear;
		this.shippingType = shippingType;
		this.shippingLog = shippingLog;
		this.credit = credit;
		this.khnum = khnum;
		this.companyid = companyid;
		this.deOwe = deOwe;
		this.tkds = tkds;
		this.ckds = ckds;
	}

	// Property accessors

	public Integer getKhid() {
		return this.khid;
	}

	public void setKhid(Integer khid) {
		this.khid = khid;
	}

	public String getKhname() {
		return this.khname;
	}

	public void setKhname(String khname) {
		this.khname = khname;
	}

	public String getPycode() {
		return this.pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
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

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistincts() {
		return this.distincts;
	}

	public void setDistincts(String distincts) {
		this.distincts = distincts;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippintClear() {
		return this.shippintClear;
	}

	public void setShippintClear(String shippintClear) {
		this.shippintClear = shippintClear;
	}

	public String getShippingType() {
		return this.shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getShippingLog() {
		return this.shippingLog;
	}

	public void setShippingLog(String shippingLog) {
		this.shippingLog = shippingLog;
	}

	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getKhnum() {
		return this.khnum;
	}

	public void setKhnum(String khnum) {
		this.khnum = khnum;
	}

	public Integer getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Double getDeOwe() {
		return this.deOwe;
	}

	public void setDeOwe(Double deOwe) {
		this.deOwe = deOwe;
	}

	public Set getTkds() {
		return this.tkds;
	}

	public void setTkds(Set tkds) {
		this.tkds = tkds;
	}

	public Set getCkds() {
		return this.ckds;
	}

	public void setCkds(Set ckds) {
		this.ckds = ckds;
	}

}