package com.cxstock.action.ziliao;

import java.util.List;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.ziliao.KhBiz;
import com.cxstock.biz.ziliao.dto.KhDTO;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

@SuppressWarnings("serial")
public class KhAction extends BaseAction {

	private KhBiz khBiz;

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
	private Double deOwe;
	private String shippintClear;
	private String shippingLog;

	private String proid;
	private String cityid;
	private String key;
	private Integer keyid;

	/**
	 * 分页查询客户列表
	 */
	public String findPageKh() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			page.setWheres(PublicClass.getRightStr()); /* 获取数据隔离的字符串 */
			if (key != null && (key + "").length() > 0) {
				page.setWheres(page.getWheres() + " and (khname like '%" + key
						+ "%' or khnum like '%" + key + "%' or lxren like '%"
						+ key + "%' or lxtel like '%" + key
						+ "%' or mobile like '%" + key + "%' or fax like '%"
						+ key + "%')");
			}
			khBiz.findPageKh(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 保存/修改客户
	 */
	public String saveOrUpdateKh() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			KhDTO dto = new KhDTO(khid, khname, PublicClass.getPyCode(khname),
					lxren, lxtel, address, bz, country, province, city,
					distinct, mobile, fax, qq, email, shipping_address,
					shipping_type, credit, khnum, udt.getCompanyid());
			dto.setDeOwe(deOwe);
			dto.setShippintClear(shippintClear);
			dto.setShippingLog(shippingLog);
			khBiz.saveOrUpdateKh(dto);
			if (khid == null) {
				this.outString("{success:true,message:'保存成功!'}");
			} else {
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 删除客户
	 */
	public String deleteKh() {
		try {
			khBiz.deleteKh(khid);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 客户下拉列表
	 */
	public String findKhComb() {
		try {
			this.outListString(khBiz.findKhComb(key, keyid));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 获得省份data
	 * */
	@SuppressWarnings("unchecked")
	public String getProData() {
		try {
			List list = khBiz.getProvinceData();
			this.outListString(list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 获取城市数据源
	 * */
	@SuppressWarnings("unchecked")
	public String getCityData() {
		try {
			List list = khBiz.getCityData(proid);
			this.outListString(list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 获取区县数据
	 */
	@SuppressWarnings("unchecked")
	public String getAreaData() {
		try {
			List list = khBiz.getAreaData(cityid);
			this.outListString(list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	public void getKhInfo() {
		try {
			this.outHashMapString((khBiz.getKh(khid)));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public void setKhBiz(KhBiz khBiz) {
		this.khBiz = khBiz;
	}

	public void setKhid(Integer khid) {
		this.khid = khid;
	}

	public void setKhname(String khname) {
		this.khname = khname;
	}

	public void setLxren(String lxren) {
		this.lxren = lxren;
	}

	public void setLxtel(String lxtel) {
		this.lxtel = lxtel;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getPycode() {
		return pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}

	public String getCountry() {
		return country;
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

	public KhBiz getKhBiz() {
		return khBiz;
	}

	public Integer getKhid() {
		return khid;
	}

	public String getKhname() {
		return khname;
	}

	public String getLxren() {
		return lxren;
	}

	public String getLxtel() {
		return lxtel;
	}

	public String getAddress() {
		return address;
	}

	public String getBz() {
		return bz;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setKhnum(String khnum) {
		this.khnum = khnum;
	}

	public String getKhnum() {
		return khnum;
	}

	public void setKeyid(Integer keyid) {
		this.keyid = keyid;
	}

	public Integer getKeyid() {
		return keyid;
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
