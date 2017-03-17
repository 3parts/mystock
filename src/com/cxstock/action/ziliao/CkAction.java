package com.cxstock.action.ziliao;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.ziliao.CkBiz;
import com.cxstock.pojo.Tbwarehouse;
import com.cxstock.utils.system.Constants;

public class CkAction extends BaseAction {

	private CkBiz ckBiz;

	private Integer id;
	private String vcNo;
	private String vcName;
	private String vcAddress;
	private String vcRemark;

	/**
	 * 获得仓库信息
	 * */
	public void getCkInfo() {
		try {
			this.outListString(ckBiz.finCqInfo());
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 保存或更新
	 * */
	public void saveorupdate() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			Tbwarehouse tb = new Tbwarehouse();
			tb.setCompanyId(udt.getCompanyid());
			tb.setId(id);
			tb.setVcAddress(vcAddress);
			tb.setVcName(vcName);
			tb.setVcNo(vcNo);
			tb.setVcRemark(vcRemark);
			ckBiz.saveOrUpdate(tb);
			if (id == null || id == 0) {
				this.outString("{success:true,message:'保存成功!'}");
			} else {
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 删除记录
	 * */
	public void delete() {
		try {
			ckBiz.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcNo() {
		return vcNo;
	}

	public void setVcNo(String vcNo) {
		this.vcNo = vcNo;
	}

	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public String getVcAddress() {
		return vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public void setCkBiz(CkBiz ckBiz) {
		this.ckBiz = ckBiz;
	}

	public CkBiz getCkBiz() {
		return ckBiz;
	}
}
