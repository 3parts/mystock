package com.cxstock.biz.purchase;

import com.cxstock.pojo.Tbspellpack;
import com.cxstock.utils.pubutil.Page;

public interface SpkBiz {

	/**
	 * 保存记录
	 * */
	public Boolean saveInfo(Tbspellpack t);

	/**
	 * 分页获取记录
	 * */
	public void getInfo(Page page);

	/**
	 * 删除记录
	 * */
	public void deleteInfo(Integer id);

	/**
	 * 结算和反结算
	 * */
	public void saveStateInfo(Integer id, String loginName, Integer companyId);

	/**
	 * 获得 信封打印 的记录
	 * */
	public void getprintInfo(Page page);

	/**
	 * 获得 信封打印的数据
	 * */
	public String getPrintData(String sql);

}
