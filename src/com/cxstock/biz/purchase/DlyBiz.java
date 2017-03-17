package com.cxstock.biz.purchase;

import com.cxstock.pojo.Tbdelivery;
import com.cxstock.utils.pubutil.Page;

public interface DlyBiz {

	/**
	 * 保存 记录
	 * */
	public void saveInfo(Tbdelivery t);

	/**
	 * 结算和反结算
	 * */
	public void saveStateInfo(Integer id, String loginName, Integer companyid);

	/**
	 * 分页获取记录
	 * */
	public void getInfo(Page page);

	/**
	 * 删除记录
	 * */
	public void deleteInfo(Integer id);

	/**
	 * 获得库存查询记录
	 * */
	public void getStockInfo(Page page);

}
