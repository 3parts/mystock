package com.cxstock.biz.purchase;

import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.purchase.dto.SalenoutDTO;
import com.cxstock.utils.pubutil.Page;

public interface SalenoutBiz {

	/**
	 * 分页获得记录
	 * */
	public void getInfo(Page page);

	/**
	 * 保存记录
	 * 
	 * @throws Exception
	 * */
	public Integer saveInfo(List list, SalenoutDTO s, String loginName,
			Integer loginId) throws Exception;

	/**
	 * 获得明细网格
	 * */
	public List getInfoDel(String sql);

	/**
	 * 单个删除记录
	 * */
	public void deleteInfo(Integer delId, Boolean b);

	/**
	 * 删除主行
	 * 
	 * @throws Exception
	 * */
	public void deleteZhuInfo(Integer id) throws Exception;

	/**
	 * 获得选择销售单
	 * */
	public List<HashMap> getSalenDel(String sql);

	/**
	 * 获得销售相关的单据
	 * */
	public void getSalenNo(Page page);

	/**
	 * 生成应收单据
	 * */
	public void saveArInfo(Integer id, String loginName, Integer loginId);

	/* 得到打印的xml数据 */
	public String getXmlPrint(String sql);

	/* 保存拼包 */
	public void saveSpll(Integer id, String vcSpllName, String vcRemark,
			Double dlMoney, Integer companyId, Integer loginId);

	/* 保存送货 */
	public String saveSh(Integer id, String vcSpllName, Double dlMoney,
			String vcRemark, String vcAddress, Integer companyId,
			Integer loginId);

}
