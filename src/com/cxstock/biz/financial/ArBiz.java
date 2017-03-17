package com.cxstock.biz.financial;

import com.cxstock.pojo.Tbar;
import com.cxstock.utils.pubutil.Page;

public interface ArBiz {

	/**
	 * 分页获取记录
	 * */
	public void getInfo(Page page);

	/**
	 * 保存记录
	 * */
	public void saveInfo(Tbar t);

	/**
	 * 删除记录
	 * */
	public void deleteInfo(Integer id);

	/**
	 * 生成应收
	 * */
	public void saveImeInfo(Integer id);

}
