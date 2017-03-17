package com.cxstock.biz.print.imp;

import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.print.PrintBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbtemplate;

public class PrintBizImpl implements PrintBiz {

	private OrderDAO orderDao;

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 获得打印模板信息
	 * */
	@Override
	public List<HashMap> getInfo(String sql) {
		return orderDao.getDataInfo(sql);
	}

	/**
	 * 根据唯一代码获得模板名称
	 * */
	@Override
	public String getSingInfo(String table) {
		return orderDao
				.getSinge("select t.vccode from tbtemplate t where t.vcTable ='"
						+ table + "'")
				+ "";
	}

	/**
	 * 保存模板名称
	 * */
	@Override
	public void saveInfo(Tbtemplate t) {
		Tbtemplate t1 = (Tbtemplate) orderDao.loadById(Tbtemplate.class, t
				.getId());
		if (t1 != null) {
			t1.setVccode(t.getVccode());
			orderDao.saveOrUpdate(t1);
		}

	}
}
