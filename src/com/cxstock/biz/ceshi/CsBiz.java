package com.cxstock.biz.ceshi;

import java.util.List;

public interface CsBiz {
	/**
	 * 获得数据的信息
	 * 
	 * @param tab
	 *            要查询的对象
	 * @param strKey
	 *            关键信息
	 * @return 名称
	 */
	public String getDataInfo(String tab, String strKey);

	/**
	 * 获取单号
	 * 
	 * @param tab
	 *            要查询的对象
	 * @param ymd
	 *            日期
	 * @return 名称
	 */
	public String getDjCode(String tab, String ymd);

	/**
	 * 获取数据源
	 * 
	 * @return 数据源
	 * */
	@SuppressWarnings("unchecked")
	public List getDataInfo();

}
