package com.cxstock.biz.financial.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.financial.OmeBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbap;
import com.cxstock.pojo.Tbbalance;
import com.cxstock.pojo.Tbdecorate;
import com.cxstock.pojo.Tboutcome;
import com.cxstock.pojo.Tbreturn;
import com.cxstock.pojo.Tbspellpack;
import com.cxstock.pojo.Tbstorage;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class OmeBizImpl implements OmeBiz {

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
	public void saveInfo(Tboutcome t) {
		Tboutcome t1 = new Tboutcome();
		if (t.getId() != null) {
			t1 = (Tboutcome) orderDao.loadById(Tboutcome.class, t.getId());
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
			// t.setVcNo(this.getCode("tboutcome"));
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String code = df.format(new Date());
			String vcNo = PublicClass.getCodeNo(orderDao, "tboutcome", "ZC",
					"vcNo", code);
			t.setVcNo(vcNo);
		}
		t1.setVcNo(t.getVcNo());
		t1.setVcRemark(t.getVcRemark());
		orderDao.saveOrUpdate(t1);
		if (t.getId() == null) { /* 新增资金余额 */
			Tbbalance t2 = new Tbbalance();
			t2.setAccountId(t.getAccountId());
			t2.setDlMoney(-t.getDlMoney());
			t2.setCompanyId(t.getCompanyId());
			t2.setDtBs(t.getDtWrite());
			t2.setSourceId(t1.getId());
			t2.setSourceType(0); /* 支出 */
			orderDao.saveOrUpdate(t2);
		}

		if (t1.getSourceId() != null && t1.getSourceId() != 0) { /* 更新应付 的结算状态 */
			Tbap t2 = (Tbap) orderDao.loadById(Tbap.class, t1.getSourceId());
			t2.setIstate(1);
			t2.setDtJsDate((new Date()).getTime() + "");
			orderDao.saveOrUpdate(t2);

			/* 更新原始单据的付款状态 */
			if (t2.getSourceType() == 0) {/* 购进 */
				Tbstorage ts1 = (Tbstorage) orderDao.loadById(Tbstorage.class,
						t2.getSourceId());
				if (ts1.getIpayState() != 1) {
					ts1.setIpayState(1);
					orderDao.saveOrUpdate(ts1);
				}
			} else if (t2.getSourceType() == 1) {/* 购退 */
				Tbreturn ts1 = (Tbreturn) orderDao.loadById(Tbreturn.class, t2
						.getSourceId());
				if (ts1.getIpayState() != 1) {
					ts1.setIpayState(1);
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
		Tboutcome to = (Tboutcome) orderDao.loadById(Tboutcome.class, id);
		if (to.getSourceId() != null && to.getSourceId() != 0) {
			Tbap t = (Tbap) orderDao.loadById(Tbap.class, to.getSourceId());
			if (t != null) {
				t.setIstate(0);
				t.setDtJsDate("");
				orderDao.saveOrUpdate(t);

				/* 更新原始单据的付款状态 */
				if (t.getSourceType() == 0) {/* 购进 */
					Tbstorage ts1 = (Tbstorage) orderDao.loadById(
							Tbstorage.class, t.getSourceId());
					if (ts1.getIpayState() != 0) {
						ts1.setIpayState(0);
						orderDao.saveOrUpdate(ts1);
					}
				} else if (t.getSourceType() == 1) {/* 购退 */
					Tbreturn ts1 = (Tbreturn) orderDao.loadById(Tbreturn.class,
							t.getSourceId());
					if (ts1.getIpayState() != 0) {
						ts1.setIpayState(0);
						orderDao.saveOrUpdate(ts1);
					}
				}
			}
		} else {
			/** 查找记录表里面是否有相应记录 **/
			List listde = orderDao
					.findByHql("from Tbdecorate where fcoretype=1 and finoutid="
							+ id + " and finouttype=1");
			/** 修改拼包的结算状态 **/
			if (listde != null) {
				Tbdecorate tbde = (Tbdecorate) listde.get(0);
				if (tbde != null) {
					Tbspellpack tbspk = (Tbspellpack) orderDao.loadById(
							Tbspellpack.class, tbde.getFcoreid());
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
				+ to.getId() + " and t.sourceType=0");
		if (list.size() > 0) {
			orderDao.delete(list.get(0));
		}
		to.setFidel(1); /* 保存作废的记录 */
		orderDao.saveOrUpdate(to);
		// orderDao.delete(to);
	}

	/**
	 * 获取资金余额
	 * 
	 * @return
	 * */
	@Override
	public List<HashMap> getBalanceInfo(Page page) {
		return orderDao.getDataInfo(PublicClass.getPageSql(page));

	}

}
