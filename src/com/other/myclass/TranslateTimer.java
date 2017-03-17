package com.other.myclass;

import java.util.Timer;

import com.cxstock.dao.OrderDAO;

/**
 * @功能：计时器
 * @作者：RC
 * @日期：2015-08-08
 * */
public class TranslateTimer {

	private OrderDAO orderDao;

	public Timer timer;

	public TranslateTimer(OrderDAO order) {
		this.orderDao = order;
	}

	public void timerStart() {
		timer = new Timer();
		/* 执行方法 */
		timer.schedule(new TimerRun(orderDao), 5000, 10000);
	}
}
