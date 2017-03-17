package com.cxstock.biz.userright.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.userright.UserrightBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Tbuserunitrights;

public class UserrightBizImpl implements UserrightBiz {

	private BaseDAO baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 保存用户的操作权限
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public void saveUserRight(String units, Integer userid) {

		Tbuserunitrights userRight;
		List list = new ArrayList();
		for (int i = 0; i < units.split(",").length; i++) {
			if (units.split(",")[i].isEmpty() || units.split(",")[i] == "0")
				continue;
			userRight = new Tbuserunitrights();
			userRight.setCompanyid(Integer.parseInt(units.split(",")[i]));
			userRight.setUserid(userid);
			list.add(userRight);
		}
		/* 先删除记录 再进行重新保存 */
		List list1 = baseDao
				.findByHql("from Tbuserunitrights as a where a.userid="
						+ userid);
		for (int j = 0; j < list1.size(); j++) {
			baseDao.delete(list1.get(j));
		}
		baseDao.saveOrUpdateAll(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getUserUnitRights(Integer userid) {
		List list = baseDao
				.findByHql("from Tbuserunitrights as t where t.userid="
						+ userid);
		String strUnits = "";
		for (int i = 0; i < list.size(); i++) {
			if (strUnits.length() == 0) {
				strUnits = ((Tbuserunitrights) list.get(i)).getCompanyid() + "";
			} else {
				strUnits += ","
						+ ((Tbuserunitrights) list.get(i)).getCompanyid();
			}
		}
		return strUnits;
	}
}
