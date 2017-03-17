package com.cxstock.action.ziliao;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

@SuppressWarnings("serial")
public class GysAction extends BaseAction {

	private GysBiz gysBiz;

	private Integer gysid;
	private String name;
	private String lxren;
	private String lxtel;
	private String address;
	private String bz;
	private String fsn;
	private String number;
	private String fax;

	private String key;
	private Integer keyid;

	/**
	 * 分页查询供应商列表
	 */
	public String findPageGys() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			page.setWheres(PublicClass.getRightStr()); /* 获取数据隔离的字符串 */
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and (name like '%" + key
						+ "%' or lxren like '%" + key + "%' or number like '%"
						+ key + "%' or fax like '%" + key
						+ "%' or lxtel like '%" + key + "%')");
			}
			gysBiz.findPageGys(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 保存/修改供应商
	 */
	public String saveOrUpdateGys() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			GysDTO dto = new GysDTO(gysid, name, lxren, lxtel, address, bz, udt
					.getCompanyid());
			dto.setFsn(fsn);
			dto.setNumber(number);
			dto.setFax(fax);
			gysBiz.saveOrUpdateGys(dto);
			if (gysid == null) {
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
	 * 删除供应商
	 */
	public String deleteGys() {
		try {
			gysBiz.deleteGys(gysid);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 供应商下拉列表
	 */
	public String findGysComb() {
		try {
			this.outListString(gysBiz.findGysComb(key, keyid));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	public void setGysBiz(GysBiz gysBiz) {
		this.gysBiz = gysBiz;
	}

	public void setGysid(Integer gysid) {
		this.gysid = gysid;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKeyid(Integer keyid) {
		this.keyid = keyid;
	}

	public Integer getKeyid() {
		return keyid;
	}

	public void setFsn(String fsn) {
		this.fsn = fsn;
	}

	public String getFsn() {
		return fsn;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return fax;
	}

}
