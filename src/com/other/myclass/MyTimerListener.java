package com.other.myclass;

import com.cxstock.dao.OrderDAO;

/**
 * @功能：程序启动 实现监听
 * @作者：RC
 * @日期：2015-08-08
 * */
public class MyTimerListener {

	private OrderDAO orderDao;

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
		/* 开启计时器 */
		TranslateTimer mytimer = new TranslateTimer(orderDao);
		mytimer.timerStart();
	}

}
