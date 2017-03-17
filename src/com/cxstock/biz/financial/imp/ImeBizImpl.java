package com.cxstock.biz.financial.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cxstock.biz.financial.ImeBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbar;
import com.cxstock.pojo.Tbbalance;
import com.cxstock.pojo.Tbdecorate;
import com.cxstock.pojo.Tbdelivery;
import com.cxstock.pojo.Tbincome;
import com.cxstock.pojo.Tbsalenback;
import com.cxstock.pojo.Tbsalenout;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

/*收入*/
public class ImeBizImpl implements ImeBiz {

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
	@Override
	public void saveInfo(Tbincome t) {
		Tbincome t1 = new Tbincome();
		if (t.getId() != null) {
			t1 = (Tbincome) orderDao.loadById(Tbincome.class, t.getId());
		} else {
			t1.setCompanyId(t.getCompanyId());
			t1.setDtWrite(t.getDtWrite());
			t1.setAccountId(t.getAccountId());
			t1.setDlMoney(t.getDlMoney());
			t1.setSourceId(t.getSourceId() == null ? 0 : t.getSourceId());
			t1.setFidel(t.getFidel());
		}
		t1.setAccountTypeId(t.getAccountTypeId());
		t1.setDtBs(t.getDtBs());
		t1.setVcAuditor(t.getVcAuditor());
		if (t.getVcNo() == null || t.getVcNo().length() == 0) {
			// t.setVcNo(this.getCode("tbincome"));
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String code = df.format(new Date());
			String vcNo = PublicClass.getCodeNo(orderDao, "tbincome", "SR",
					"vcNo", code);
			t.setVcNo(vcNo);
		}
		t1.setVcNo(t.getVcNo());
		t1.setVcRemark(t.getVcRemark());
		orderDao.saveOrUpdate(t1);
		if (t.getId() == null) { /* 新增资金余额 */
			Tbbalance t2 = new Tbbalance();
			t2.setAccountId(t.getAccountId());
			t2.setDlMoney(t.getDlMoney());
			t2.setCompanyId(t.getCompanyId());
			t2.setDtBs(t.getDtWrite());
			t2.setSourceId(t1.getId());
			t2.setSourceType(1); /* 收入 */
			orderDao.saveOrUpdate(t2);
		}
		if (t1.getSourceId() != null && t1.getSourceId() != 0) { /* 更新应收 的结算状态 */
			Tbar t2 = (Tbar) orderDao.loadById(Tbar.class, t1.getSourceId());
			t2.setIstate(1);
			t2.setDtJsDate((new Date()).getTime() + "");
			orderDao.saveOrUpdate(t2);

			/* 更新原始单据的付款状态 */
			if (t2.getSourceType() == 0) {/* 购进 */
				Tbsalenout ts1 = (Tbsalenout) orderDao.loadById(
						Tbsalenout.class, t2.getSourceId());
				if (ts1.getIpay() != 1) {
					ts1.setIpay(1);
					orderDao.saveOrUpdate(ts1);
				}
			} else if (t2.getSourceType() == 1) {/* 购退 */
				Tbsalenback ts1 = (Tbsalenback) orderDao.loadById(
						Tbsalenback.class, t2.getSourceId());
				if (ts1.getIpayType() != 1) {
					ts1.setIpayType(1);
					orderDao.saveOrUpdate(ts1);
				}
			}

		}
	}

	/**
	 * 删除记录
	 * */
	@Override
	public void deleteInfo(Integer id) {
		Tbincome to = (Tbincome) orderDao.loadById(Tbincome.class, id);
		if (to.getSourceId() != null && to.getSourceId() != 0) { /* 修改应收记录 */
			Tbar t = (Tbar) orderDao.loadById(Tbar.class, to.getSourceId());
			if (t != null) {
				t.setIstate(0);
				t.setDtJsDate("");
				orderDao.saveOrUpdate(t);

				/* 更新原始单据的付款状态 */
				if (t.getSourceType() == 0) {/* 购进 */
					Tbsalenout ts1 = (Tbsalenout) orderDao.loadById(
							Tbsalenout.class, t.getSourceId());
					if (ts1.getIpay() != 0) {
						ts1.setIpay(0);
						orderDao.saveOrUpdate(ts1);
					}
				} else if (t.getSourceType() == 1) {/* 购退 */
					Tbsalenback ts1 = (Tbsalenback) orderDao.loadById(
							Tbsalenback.class, t.getSourceId());
					if (ts1.getIpayType() != 0) {
						ts1.setIpayType(0);
						orderDao.saveOrUpdate(ts1);
					}
				}

			}
		} else {
			/** 查找记录表里面是否有相应记录 **/
			List listde = orderDao
					.findByHql("from Tbdecorate where fcoretype=0 and finoutid="
							+ id + " and finouttype=0");
			/** 修改拼包的结算状态 **/
			if (listde != null) {
				Tbdecorate tbde = (Tbdecorate) listde.get(0);
				if (tbde != null) {
					Tbdelivery tbspk = (Tbdelivery) orderDao.loadById(
							Tbdelivery.class, tbde.getFcoreid());
					tbspk.setIpayState(0);
					tbspk.setDtPay(null);
					tbspk.setVcPayMan("");
					orderDao.saveOrUpdate(tbspk);
				}
				orderDao.delete(tbde);
			}
		}
		/* 删除资金记录 */
		List list = orderDao.findByHql("from Tbbalance t where t.sourceId="
				+ to.getId() + " and t.sourceType=1");
		if (list.size() > 0) {
			orderDao.delete(list.get(0));
		}
		to.setFidel(1); /* 保存作废记录 */
		orderDao.saveOrUpdate(to);
		// orderDao.delete(to);

	}

}
