package com.cxstock.biz.purchase;

import java.util.List;

import com.cxstock.biz.purchase.dto.ReturnDTO;
import com.cxstock.utils.pubutil.Page;

public interface ReturnBiz {

	/**
	 * 获得购货记录
	 * */
	public void getStorage(Page page);

	/**
	 * 获得购进的明细信息
	 * */
	public void getStorageDel(Page page);

	/**
	 * 保存记录
	 * 
	 * @throws Exception
	 * */
	public Integer saveInfo(List list, ReturnDTO s, String loginName,
			Integer loginId) throws Exception;

	/**
	 * 删除明细记录
	 * */
	public void deleteInfo(Integer delId, Boolean b);

	/**
	 * 删除主行记录
	 * */
	public void deleteZhuInfo(Integer id);

	/**
	 * 生成应付单
	 * */
	public void saveApInfo(Integer id, String loginName, Integer loginId);

	/**
	 * 功能：检测购退填写的记录是否合法 作者：RC 创建日期：2015-05-27
	 * */
	public Boolean getLetMate(Integer spId, String batch, String sn,
			Integer houseid, Double count);

	/* 得到打印的xml数据 */
	public String getXmlPrint(String sql);

}
