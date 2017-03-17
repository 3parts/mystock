package com.cxstock.biz.purchase.imp;

import java.util.List;

import com.cxstock.biz.purchase.OtherBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

/**
 * @功能：云通货的其他操作
 * @作者：RC
 * @日期：2015-07-17
 * */
public class OtherBizImpl implements OtherBiz {

	private OrderDAO orderDao;

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	/**
	 * @功能：进出商品查询
	 * @作者：RC
	 * @日期：2015-07-17
	 * */
	@Override
	public void getJcNoInfo(Page page) {
		page.setTotal(orderDao.getCount(PublicClass.getPageCountSql(page)));
		page
				.setListMap(orderDao.getDataInfo(PublicClass
						.getPageLimitSql(page)));
	}

	/**
	 * @功能：今日报表查询
	 * @作者：RC
	 * @日期：2015-07-17
	 * */
	@Override
	public void getToDayInfo(Page page) {
		page.setTotal(orderDao.getCount(PublicClass.getPageCountSql(page)));
		page
				.setListMap(orderDao.getDataInfo(PublicClass
						.getPageLimitSql(page)));
	}

	/**
	 * @功能：获得库存数量和总值
	 * @作者：RC
	 * @日期：2015-09-07
	 * */
	@Override
	public List getStockMoney() {
		return orderDao
				.getDataInfo("SELECT SUM(a.dlQty) AS n, SUM(IFNULL(a.dlQty,0)*IFNULL(b.dbAverageMoney,0)) AS m FROM tbstock a INNER JOIN tbcommodity b ON a.commodityId = b.id");
	}

}
