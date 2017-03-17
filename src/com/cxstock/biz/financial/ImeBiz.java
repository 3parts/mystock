package com.cxstock.biz.financial;

import com.cxstock.pojo.Tbincome;
import com.cxstock.utils.pubutil.Page;

public interface ImeBiz {

	/**
	 * 分页获取记录
	 * */
	public void getInfo(Page page);

	/**
	 * 保存记录
	 * */
	public void saveInfo(Tbincome t);

	/**
	 * 删除记录
	 * */
	public void deleteInfo(Integer id);

}
