package com.cxstock.biz.purchase;

import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.purchase.dto.StorageDTO;
import com.cxstock.pojo.Tbcommodity;
import com.cxstock.utils.pubutil.Page;

public interface PhBiz {

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
	public Integer saveInfo(List list, StorageDTO s, String loginName,
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
	 * 获得购进和购退的全部记录
	 * */
	public void getStorageInfo(Page page);

	/**
	 * 生成应付单
	 * */
	public void saveApInfo(Integer id, String loginName, Integer loginId);

	/**
	 * 根据表获得list
	 * */
	public List getKey(String tab, String where);

	/**************
	 * 查看同商品、批次、辅助标识是否重复 *
	 *****************/
	public String getExtsAddSpxx(Integer spid, String vcbach, String vcsn);

	public void getStorageDel1(Page page);

	/* 根据Id 查询商品信息 */
	public List<HashMap> getspxx(Integer spid);

	/** 保存商品 */
	public void saveSpxx(Tbcommodity t);

}
