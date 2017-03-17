package com.cxstock.biz.purchase;

import java.util.List;

import com.cxstock.pojo.Tballocation;
import com.cxstock.utils.pubutil.Page;

public interface AltBiz {

	/**
	 * 分页得到记录
	 * */
	public void getInfo(Page page);

	/**
	 * 得到调拨明细
	 * */
	public List getInfoDel(String sql);

	/**
	 * 得到库存记录
	 * */
	public List getStockInfoDel(String sql);

	/**
	 * 保存记录
	 * 
	 * @throws Exception
	 * */
	public Boolean saveInfo(List list, Tballocation t) throws Exception;

	/**
	 * 删除明细记录
	 * */
	public void deleteInfoDel(Integer id);

	/**
	 * 删除主行记录
	 * */
	public void deleteInfo(Integer id);

}
