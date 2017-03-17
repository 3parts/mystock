package com.cxstock.action.userright;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.userright.UserrightBiz;
import com.cxstock.utils.system.Constants;

public class UrAction extends BaseAction {

	private UserrightBiz userrightBiz;

	private Integer userid;
	private String units;

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getUnits() {
		return units;
	}

	/**
	 * 保存记录
	 * */
	public void saveUserRight() {
		try {
			userrightBiz.saveUserRight(units, userid);
			this.outString("{success:true,msg:'保存成功！'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 得到当前用户所能操作的 企业ID
	 * */
	public void setUserUnitRight() {
		UserDTO udt = (UserDTO) this.getSession().getAttribute(
				Constants.USERINFO);
		String strUserUnitRights = userrightBiz.getUserUnitRights(udt
				.getUserid());
		/* 设置数据隔离字符串 */
		this.getSession().setAttribute("userunitrights", strUserUnitRights);
	}

	public void setUserrightBiz(UserrightBiz userrightBiz) {
		this.userrightBiz = userrightBiz;
	}

	public UserrightBiz getUserrightBiz() {
		return userrightBiz;
	}

}
