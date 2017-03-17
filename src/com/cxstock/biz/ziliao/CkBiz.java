package com.cxstock.biz.ziliao;

import java.util.List;

import com.cxstock.pojo.Tbwarehouse;

public interface CkBiz {

	/**
	 * 查找仓库信息
	 * */
	public List finCqInfo();

	/**
	 * 保存或更新仓库信息
	 * */
	public void saveOrUpdate(Tbwarehouse tb);

	/**
	 * 根据ID 删除记录
	 * */
	public void delete(Integer id);

}
