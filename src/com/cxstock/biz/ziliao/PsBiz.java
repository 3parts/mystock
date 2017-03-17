package com.cxstock.biz.ziliao;

import java.util.List;

import com.cxstock.pojo.Tbperson;
import com.cxstock.utils.pubutil.Page;

public interface PsBiz {

	/**
	 * 分页查询记录
	 * */
	public void getInfo(Page page);

	/**
	 * 保存或更新记录
	 * */
	public void saveOrUpdate(Tbperson t);

	/**
	 * 删除记录
	 * */
	public void delete(Integer id);

	/**
	 * 员工下拉框
	 * */
	public List findPsComb();

}
