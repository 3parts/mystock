package com.cxstock.biz.ziliao;

import com.cxstock.pojo.Spxx;
import com.cxstock.pojo.Tbcommodity;
import com.cxstock.utils.pubutil.Page;

public interface SpxxBiz {

	/**
	 * 商品编号
	 */
	public String getSpxxCode();

	/**
	 * 分页查询商品列表
	 */
	public void findPageSpxx(Page page);

	/**
	 * 保存商品
	 */
	public void save(Spxx dto);

	/**
	 * 修改改商品
	 */
	public void updateSpxx(Spxx dto);

	/**
	 * 删除商品
	 */
	public boolean deleteSpxx(Integer spid);

	/**
	 * 保存或修改
	 * */
	public void saveOrUpdate(Tbcommodity tb);

	/**
	 * 满足条件的记录行数
	 * */
	public boolean getCount(String sql);

}
