package com.cxstock.biz.purchase;

import java.util.List;

import com.cxstock.biz.purchase.dto.InventoryDTO;
import com.cxstock.utils.pubutil.Page;

public interface IvtBiz {

	/**
	 * 获得明细信息
	 * */
	public List getInfoDel(String sql);

	/**
	 * 保存记录
	 * */
	public void saveInfo(List list, InventoryDTO t);

	/**
	 * 分页获得记录
	 * */
	public void getInfo(Page page);

	/**
	 * 删除明细
	 * */
	public void deleteInfoDel(int iddel);

	/**
	 * 删除主行
	 * */
	public Boolean deleteInfo(int id);

	/**
	 * 审核
	 * */
	public void saveCheckInfo(int id, Integer loginId);

	/**
	 * 盘点完成
	 * */
	public void saveOkInfo(int id);

}
