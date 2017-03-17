package com.cxstock.biz.purchase;

import java.util.List;

import com.cxstock.utils.pubutil.Page;

/**
 * @功能：云通货的其他操作
 * @作者：RC
 * @日期：2015-07-17
 * */
public interface OtherBiz {

	/* 进出商品查询 */
	public void getJcNoInfo(Page page);

	/* 今日报表查询 */
	public void getToDayInfo(Page page);

	/**
	 * 获得库存数量和库存总值
	 * */
	public List getStockMoney();

}
