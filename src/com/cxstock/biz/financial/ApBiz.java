package com.cxstock.biz.financial;

import com.cxstock.pojo.Tbap;
import com.cxstock.utils.pubutil.Page;

public interface ApBiz {

	/**
	 * 分页获取记录
	 * */
	public void getInfo(Page page);

	/**
	 * 保存记录
	 * */
	public void saveInfo(Tbap t);

	/**
	 * 删除记录
	 * */
	public void deleteInfo(Integer id);

}
