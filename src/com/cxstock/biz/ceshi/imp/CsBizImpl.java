package com.cxstock.biz.ceshi.imp;

import java.util.List;

import com.cxstock.biz.ceshi.CsBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.utils.system.Tools;

@SuppressWarnings("unchecked")
public class CsBizImpl implements CsBiz {

	private BaseDAO baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}

	public String getDataInfo(String tab, String strKey) {
		String strSql = "select t.menuname from " + tab
				+ " as t where t.menuid=" + strKey + "";
		List list = baseDao.findByHql(strSql);
		Object obj = list.get(0);
		if (obj == null)
			return null;
		return obj + "";
	}

	/*
	 * 生成单据编号
	 */
	public String getDjCode(String tab, String ymd) {
		String code = ymd.replaceAll("-", "");
		String hql = "select max(t.djid) from " + tab
				+ " as t where t.riqi between '" + ymd + " 00:00:00' and '"
				+ ymd + " 23:59:59'";
		List list = baseDao.findByHql(hql);
		Object obj = list.get(0);
		if (obj != null)
			return code + Tools.formatCode(obj.toString());
		return code + "0001";
	}

	/**
	 * 获取数据源
	 * */
	public List getDataInfo() {
		String strsql = "from Menu";
		List list = baseDao.findByHql(strsql);
		return list;
	}

}