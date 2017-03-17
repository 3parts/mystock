package com.cxstock.biz.financial;

import java.util.HashMap;
import java.util.List;

import com.cxstock.pojo.Tboutcome;
import com.cxstock.utils.pubutil.Page;

public interface OmeBiz {

	/**
	 * 分页获取记录
	 * */
	public void getInfo(Page page);

	/**
	 * 保存记录
	 * */
	public void saveInfo(Tboutcome t);

	/**
	 * 删除记录
	 * */
	public void deleteInfo(Integer id);

	/**
	 * 获取资金余额
	 * */
	public List<HashMap> getBalanceInfo(Page page);

}
