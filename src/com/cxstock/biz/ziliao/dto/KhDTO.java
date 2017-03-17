package com.cxstock.biz.ziliao.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.cxstock.pojo.Kh;

public class KhDTO {

	public String getPycode() {
		return pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}

	public String getCountry() {
		return country;
	}

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
	private String distinct;
	private String mobile;
	private String fax;
	private String qq;
	private String email;
	private String shipping_address;
	private String shipping_type;
	private Integer credit;
	private String khnum;
	private Integer companyid;
	private Double deOwe;
	private String shippintClear;
	private String shippingLog;

	public KhDTO() {
		super();
	}

	public KhDTO(Integer khid, String khname, String pycode, String lxren,
			String lxtel, String address, String bz, String country,
			String province, String city, String distinct, String mobile,
			String fax, String qq, String email, String shippingAddress,
			String shippingType, Integer credit, String khnum, Integer companyid) {
		super();
		this.khid = khid;
		this.khname = khname;
		this.pycode = pycode;
		this.lxren = lxren;
		this.lxtel = lxtel;
		this.address = address;
		this.bz = bz;
		this.country = country;
		this.province = province;
		this.city = city;
		this.distinct = distinct;
		this.mobile = mobile;
		this.fax = fax;
		this.qq = qq;
		this.email = email;
		shipping_address = shippingAddress;
		shipping_type = shippingType;
		this.credit = credit;
		this.khnum = khnum;
		this.companyid = companyid;
	}

	public KhDTO(Integer khid2, String khname2, String lxren2, String lxtel2,
			String address2, String bz2) {
		this.khid = khid2;
		this.khname = khname2;
		this.lxren = lxren2;
		this.lxtel = lxtel2;
		this.address = address2;
		this.bz = bz2;

	}

	public static KhDTO createDto(Kh pojo) {
		KhDTO dto = null;
		if (pojo != null) {

			dto = new KhDTO(pojo.getKhid(), pojo.getKhname(), pojo.getPycode(),
					pojo.getLxren(), pojo.getLxtel(), pojo.getAddress(), pojo
							.getBz(), pojo.getCountry(), pojo.getProvince(),
					pojo.getCity(), pojo.getDistincts(), pojo.getMobile(), pojo
							.getFax(), pojo.getQq(), pojo.getEmail(), pojo
							.getShippingAddress(), pojo.getShippingType(), pojo
							.getCredit(), pojo.getKhnum(), pojo.getCompanyid());
			dto.setDeOwe(pojo.getDeOwe());
			dto.setShippintClear(pojo.getShippintClear());
			dto.setShippingLog(pojo.getShippingLog());
		}
		return dto;
	}

	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<KhDTO> list = new ArrayList<KhDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				list.add(createDto((Kh) iterator.next()));
			}
		}
		return list;
	}

	public Integer getKhid() {
		return khid;
	}

	public void setKhid(Integer khid) {
		this.khid = khid;
	}

	public String getKhname() {
		return khname;
	}

	public void setKhname(String khname) {
		this.khname = khname;
	}

	public String getLxren() {
		return lxren;
	}

	public void setLxren(String lxren) {
		this.lxren = lxren;
	}

	public String getLxtel() {
		return lxtel;
	}

	public void setLxtel(String lxtel) {
		this.lxtel = lxtel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistinct() {
		return distinct;
	}

	public void setDistinct(String distinct) {
		this.distinct = distinct;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(String shippingAddress) {
		shipping_address = shippingAddress;
	}

	public String getShipping_type() {
		return shipping_type;
	}

	public void setShipping_type(String shippingType) {
		shipping_type = shippingType;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public void setKhnum(String khnum) {
		this.khnum = khnum;
	}

	public String getKhnum() {
		return khnum;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setDeOwe(Double deOwe) {
		this.deOwe = deOwe;
	}

	public Double getDeOwe() {
		return deOwe;
	}

	public void setShippintClear(String shippintClear) {
		this.shippintClear = shippintClear;
	}

	public String getShippintClear() {
		return shippintClear;
	}

	public void setShippingLog(String shippingLog) {
		this.shippingLog = shippingLog;
	}

	public String getShippingLog() {
		return shippingLog;
	}

}
