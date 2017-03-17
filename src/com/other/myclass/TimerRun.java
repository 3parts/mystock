package com.other.myclass;

import java.util.TimerTask;

import com.cxstock.dao.OrderDAO;

/**
 * @功能：执行定时器的方法
 * @作者：RC
 * @日期：2015-08-08
 * */
public class TimerRun extends TimerTask {

	private OrderDAO orderDao;

	public TimerRun(OrderDAO o) {
		orderDao = o;
	}

	@Override
	public void run() {
		System.out.print("\n00操作00\n");
		orderDao.findByHql("from Role");
	}

}
