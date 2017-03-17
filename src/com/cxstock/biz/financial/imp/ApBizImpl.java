package com.cxstock.biz.financial.imp;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cxstock.biz.financial.ApBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbap;
import com.cxstock.pojo.Tbreturn;
import com.cxstock.pojo.Tbstorage;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

/*应付*/
public class ApBizImpl implements ApBiz {

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
	public void saveInfo(Tbap t) {
		Tbap t1 = new Tbap();
		if (t.getId() != null) {
			t1 = (Tbap) orderDao.loadById(Tbap.class, t.getId());
		} else {
			t1.setCompanyId(t.getCompanyId());
			// t1.setVcNo(this.getCode("tbap"));
			t1.setUserId(t.getUserId());
			t1.setDtWrite(t.getDtWrite());
			t1.setDtJsDate("");
			t1.setIstate(0);
			t1.setFidel(t.getFidel());

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String code = df.format(new Date());
			String vcNo = PublicClass.getCodeNo(orderDao, "tbap", "YF", "vcNo",
					code);
			t1.setVcNo(vcNo);
		}
		t1.setDlMoney(t.getDlMoney());
		t1.setDtBs(t.getDtBs());
		t1.setGysId(t.getGysId());
		t1.setLogisticsId(t.getLogisticsId());
		t1.setSettlementId(t.getSettlementId());
		t1.setSourceId(t.getSourceId());
		t1.setSourceType(t.getSourceType());
		t1.setVcAuditor(t.getVcAuditor());
		t1.setVcRemark(t.getVcRemark());
		orderDao.saveOrUpdate(t1);
		/** 修改状态 */
		if (t1.getSourceType() == 0) /* 购进 */
		{
			Tbstorage ts = (Tbstorage) orderDao.loadById(Tbstorage.class, t1
					.getSourceId());
			ts.setIcbill(1);
			// ts.setIpayState(1);
			orderDao.saveOrUpdate(ts);
		} else { /* 购退 */
			Tbreturn tr = (Tbreturn) orderDao.loadById(Tbreturn.class, t1
					.getSourceId());
			tr.setIcbill(1);
			// tr.setIpayState(1);
			orderDao.saveOrUpdate(tr);
		}
	}

	/**
	 * 删除记录
	 * */
	@Override
	public void deleteInfo(Integer id) {
		Tbap t = (Tbap) orderDao.loadById(Tbap.class, id);
		/* 修改购进或购退的记录 */
		if (t.getSourceType() == 0) /* 购进 */{
			Tbstorage ts = (Tbstorage) orderDao.loadById(Tbstorage.class, t
					.getSourceId());
			ts.setIcbill(0);
			ts.setIpayState(0);
			// orderDao.delete(t);
			orderDao.saveOrUpdate(ts);
		} else {
			Tbreturn tr = (Tbreturn) orderDao.loadById(Tbreturn.class, t
					.getSourceId());
			tr.setIcbill(0);
			tr.setIpayState(0);
			// orderDao.delete(t);
			orderDao.saveOrUpdate(tr);
		}
		t.setFidel(1);
		orderDao.saveOrUpdate(t); /* 保存作废记录 */
		// orderDao.delete(t);

	}

}
