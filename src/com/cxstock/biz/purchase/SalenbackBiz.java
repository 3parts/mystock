package com.cxstock.biz.purchase;

import java.util.List;

import com.cxstock.pojo.Tbsalenback;
import com.cxstock.utils.pubutil.Page;

public interface SalenbackBiz {

	/**
	 * 获得主项
	 * */
	public void getInfo(Page page);

	/**
	 * 获得明细项
	 * */
	public List getInfoDel(String sql);

	/**
	 * 保存记录
	 * 
	 * @throws Exception
	 * */
	public Boolean saveInfo(List list, Tbsalenback t, String loginName,
			Integer loginId) throws Exception;

	/**
	 * 单个删除记录
	 * */
	public void deleteInfo(Integer delId, Boolean b);

	/**
	 * 删除主行
	 * */
	public void deleteZhuInfo(Integer id);

	/**
	 * 生成应收单
	 * */
	public void saveArInfo(Integer id, String loginName, Integer loginId);

	public String getXmlPrint(String sql);

}
