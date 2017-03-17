package com.cxstock.biz.ziliao;

import java.util.List;

public interface UtBiz {

	/**
	 * 获得list信息
	 * */
	public List getInfo(String strClass);

	/**
	 * 保存或更新记录
	 * */
	public void saveOrUpdate(String strType, Object obj);

	/**
	 * 删除记录
	 * */
	public void deleteInfo(String strType, Integer id);

	public List getInfo(String strClass, Integer itype);

	public List getInfo1(String strClass, Integer vctype);

}
