package com.cxstock.biz.purchase.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cxstock.biz.purchase.DlyBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Kh;
import com.cxstock.pojo.Tbar;
import com.cxstock.pojo.Tbbalance;
import com.cxstock.pojo.Tbdecorate;
import com.cxstock.pojo.Tbdelivery;
import com.cxstock.pojo.Tbincome;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class DlyBizImpl implements DlyBiz {
	private OrderDAO orderDao;

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	/**
	 * 保存记录
	 * */
	@Override
	public void saveInfo(Tbdelivery t) {
		Tbdelivery t1 = new Tbdelivery();
		if (t.getId() != null) {
			t1 = (Tbdelivery) orderDao.loadById(Tbdelivery.class, t.getId());
			if (t1.getIpayState() == 1)
				return;
		} else {
			t1.setCompanyId(t.getCompanyId());
			t1.setIprintTimes(0);
			t1.setIpayState(0);
		}
		t1.setDlMoeny(t.getDlMoeny());
		t1.setDtBs(t.getDtBs());
		t1.setKhId(t.getKhId());
		t1.setSalenId(t.getSalenId());
		t1.setUserId(t.getUserId());
		t1.setVcAddress(t.getVcAddress());
		t1.setVcPayMan(t.getVcPayMan());
		t1.setVcRemark(t.getVcRemark());
		t1.setVcSendMan(t.getVcSendMan());
		/** 修改 销售单的应收金额 **/
		if (t1.getId() == null) {
			List listar = orderDao.findByHql("from Tbar where sourceId="
					+ t.getSalenId() + " and sourceType=0 and fidel<>1");
			if (listar != null && listar.size() > 0) {
				Tbar tar = (Tbar) listar.get(0);
				if (tar != null) {
					tar.setDlMoney(tar.getDlMoney() - t.getDlMoeny());
					orderDao.saveOrUpdate(tar);
				}
			}
		}

		orderDao.saveOrUpdate(t1);
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
	 * 删除记录
	 * */
	@Override
	public void deleteInfo(Integer id) {
		Tbdelivery t = (Tbdelivery) orderDao.loadById(Tbdelivery.class, id);
		if (t.getIpayState() == 1)
			return;
		/** 给应收 加入送货的金额 **/
		List listar = orderDao.findByHql("from Tbar where sourceId="
				+ t.getSalenId() + " and sourceType=0 and IFNULL(fidel,0)<>1");
		if (listar != null && listar.size() > 0) {
			Tbar tar = (Tbar) listar.get(0);
			if (tar != null) {
				if (tar.getIstate() == 1)
					return;
				tar.setDlMoney(tar.getDlMoney() + t.getDlMoeny());
				orderDao.saveOrUpdate(tar);
			}
		}
		orderDao.delete(t);
		// orderDao.deleteById(Tbdelivery.class, id);

	}

	/**
	 * 获得库存记录
	 * */
	@Override
	public void getStockInfo(Page page) {
		// return orderDao.getDataInfo(sql);
		String sql = PublicClass.getPageCountSql(page);
		page.setTotal(orderDao.getCount(sql));
		sql = PublicClass.getPageLimitSql(page);
		page.setListMap(orderDao.getDataInfo(sql));
	}

	/* 结算与反结算 */
	@Override
	public void saveStateInfo(Integer id, String loginName, Integer companyid) {
		Tbdelivery t = (Tbdelivery) orderDao.loadById(Tbdelivery.class, id);
		if (t.getIpayState() != 1) { /* 结算 */
			t.setIpayState(1);
			t.setDtPay((new Date()).getTime() + "");
			t.setVcPayMan(loginName);
			/** 生成收入 **/
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String code = df.format(new Date());
			Tbincome tin = new Tbincome();
			Object objAccountid = orderDao
					.getSinge("select id from tbaccount where vcName='现金'"); /* 账户 */
			Object objAccountTypeid = orderDao
					.getSinge("select id from tbaccounttype where vcName='送货'"); /* 账目类型 */
			tin.setAccountId((Integer) objAccountid);
			tin.setAccountTypeId((Integer) objAccountTypeid);
			tin.setCompanyId(companyid);
			tin.setDlMoney(t.getDlMoeny());
			tin.setDtBs(t.getDtBs());/* 业务时间 */
			tin.setDtWrite(t.getDtPay());/* 录入时间 */
			tin.setFidel(0);
			tin.setSourceId(null);
			tin.setVcAuditor(loginName);
			tin.setVcNo(PublicClass.getCodeNo(orderDao, "tboutcome", "SR",
					"vcNo", code));
			tin.setVcRemark(((Kh) orderDao.loadById(Kh.class, t.getKhId()))
					.getKhname()
					+ "  " + t.getVcSendMan() + " " + t.getVcAddress());
			orderDao.saveOrUpdate(tin);
			/** 生成资金余额 **/
			Tbbalance tbalan = new Tbbalance();
			tbalan.setAccountId(tin.getAccountId());
			tbalan.setCompanyId(tin.getCompanyId());
			tbalan.setDlMoney(tin.getDlMoney());
			tbalan.setDtBs(tin.getDtWrite());
			tbalan.setSourceId(tin.getId());
			tbalan.setSourceType(1);/* 收入 */
			orderDao.saveOrUpdate(tbalan);

			/** 插入记录表 **/
			Tbdecorate tde = new Tbdecorate();
			tde.setFinoutid(tin.getId());
			tde.setFinouttype(0); /* 收入 */
			tde.setFcoreid(t.getId());
			tde.setFcoretype(0); /* 送货 */
			orderDao.saveOrUpdate(tde);

		} else { /* 反结算 */
			t.setIpayState(0);
			t.setDtPay(null);
			t.setVcPayMan("");

			/** 删除收入 **/
			List listde = orderDao
					.findByHql("from Tbdecorate where fcoretype=0 and fcoreid="
							+ t.getId() + " and finouttype=0");
			if (listde != null) {
				Tbdecorate tde = (Tbdecorate) listde.get(0);
				if (tde != null) {
					Tbincome tbin = (Tbincome) orderDao.loadById(
							Tbincome.class, tde.getFinoutid());
					/* 删除资金记录 */
					List list = orderDao
							.findByHql("from Tbbalance t where t.sourceId="
									+ tbin.getId() + " and t.sourceType=1");
					if (list.size() > 0) {
						orderDao.delete(list.get(0));
					}

					tbin.setFidel(1);
					orderDao.saveOrUpdate(tbin);
					orderDao.delete(tde);
				}
			}
		}
		orderDao.saveOrUpdate(t);

	}
}
