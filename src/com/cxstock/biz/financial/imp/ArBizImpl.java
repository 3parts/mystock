package com.cxstock.biz.financial.imp;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cxstock.biz.financial.ArBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbar;
import com.cxstock.pojo.Tbincome;
import com.cxstock.pojo.Tbsalenback;
import com.cxstock.pojo.Tbsalenout;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

/*应收*/
public class ArBizImpl implements ArBiz {

	private OrderDAO orderDao;

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	/**
	 * 分页获取记录
	 * */
	@Override
	public void getInfo(Page page) {
		page.setTotal(orderDao.getCount(PublicClass.getPageCountSql(page)));
		page
				.setListMap(orderDao.getDataInfo(PublicClass
						.getPageLimitSql(page)));

	}

	/**
	 * 保存记录
	 * */
	public void saveInfo(Tbar t) {
		Tbar t1 = new Tbar();
		if (t.getId() != null) {
			t1 = (Tbar) orderDao.loadById(Tbar.class, t.getId());
		} else {
			t1.setCompanyId(t.getCompanyId());
			// t1.setVcNo(this.getCode("tbar"));
			t1.setUserId(t.getUserId());
			t1.setDtWrite(t.getDtWrite());
			t1.setDtJsDate("");
			t1.setIstate(0);
			t1.setFidel(t.getFidel());

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String code = df.format(new Date());
			String vcNo = PublicClass.getCodeNo(orderDao, "tbar", "YS", "vcNo",
					code);
			t1.setVcNo(vcNo);
		}
		t1.setDlMoney(t.getDlMoney());
		t1.setDtBs(t.getDtBs());
		t1.setKhId(t.getKhId());
		t1.setLogisticsId(t.getLogisticsId());
		t1.setSettlementId(t.getSettlementId());
		t1.setSourceId(t.getSourceId());
		t1.setSourceType(t.getSourceType());
		t1.setVcAuditor(t.getVcAuditor());
		t1.setVcRemark(t.getVcRemark());
		orderDao.saveOrUpdate(t1);
		/** 修改状态 */
		if (t1.getSourceType() == 0) /* 销售 */
		{
			Tbsalenout ts = (Tbsalenout) orderDao.loadById(Tbsalenout.class, t1
					.getSourceId());
			ts.setIcbill(1);
			ts.setIlogistics(t1.getLogisticsId()); /* 配送方式 */
			ts.setIsettlement(t1.getSettlementId()); /* 结算方式 */
			// ts.setIpay(1);
			orderDao.saveOrUpdate(ts);
		} else { /* 销退 */
			Tbsalenback tr = (Tbsalenback) orderDao.loadById(Tbsalenback.class,
					t1.getSourceId());
			tr.setIcbill(1);
			// tr.setIpayType(1);
			orderDao.saveOrUpdate(tr);
		}

	}

	/**
	 * 删除记录
	 * */
	@Override
	public void deleteInfo(Integer id) {
		Tbar t = (Tbar) orderDao.loadById(Tbar.class, id);
		orderDao.deleteById(Tbar.class, id);
		/* 修改销售或销退的记录 */
		if (t.getSourceType() == 0) /* 销售 */{
			Tbsalenout ts = (Tbsalenout) orderDao.loadById(Tbsalenout.class, t
					.getSourceId());
			ts.setIcbill(0);
			ts.setIpay(0);
			// orderDao.delete(t);
			orderDao.saveOrUpdate(ts);
		} else {
			Tbsalenback tr = (Tbsalenback) orderDao.loadById(Tbsalenback.class,
					t.getSourceId());
			tr.setIcbill(0);
			tr.setIpayType(0);
			// orderDao.delete(t);
			orderDao.saveOrUpdate(tr);
		}
		t.setFidel(1);
		orderDao.saveOrUpdate(t); /* 保存作废记录 */
		// orderDao.delete(t);
	}

	/**
	 * 生成收入
	 * */
	@Override
	public void saveImeInfo(Integer id) {
		Tbar tr = (Tbar) orderDao.loadById(Tbar.class, id);
		Tbincome t = new Tbincome();

	}

}
