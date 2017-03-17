package com.cxstock.biz.purchase.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.cxstock.biz.purchase.SpkBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Kh;
import com.cxstock.pojo.Tbar;
import com.cxstock.pojo.Tbbalance;
import com.cxstock.pojo.Tbdecorate;
import com.cxstock.pojo.Tboutcome;
import com.cxstock.pojo.Tbspellpack;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class SpkBizImpl implements SpkBiz {

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
	public Boolean saveInfo(Tbspellpack t) {
		Tbspellpack t1 = new Tbspellpack();
		if (t.getId() != null) {
			t1 = (Tbspellpack) orderDao.loadById(Tbspellpack.class, t.getId());
			if (t1.getIpayState() == 1) {
				return false;
			}
		} else {
			t1.setCompanyId(t.getCompanyId());
			t1.setIpayState(0);
		}
		t1.setDlMoney(t.getDlMoney());
		t1.setDtBs(t.getDtBs());
		t1.setKhId(t.getKhId());
		t1.setSalenId(t.getSalenId());
		t1.setUserId(t.getUserId());
		t1.setVcRemark(t.getVcRemark());
		t1.setVcSpllName(t.getVcSpllName());
		if (t.getId() == null) { /* 新增 给销售单的应收加入拼包金额 */
			List listar = orderDao.findByHql("from Tbar where sourceId="
					+ t.getSalenId() + " and sourceType=0 and fidel<>1");
			if (listar != null && listar.size() > 0) {
				Tbar tar = (Tbar) listar.get(0);
				if (tar != null) {
					tar.setDlMoney(tar.getDlMoney() + t.getDlMoney());
					orderDao.saveOrUpdate(tar);
				}
			}

		}
		orderDao.saveOrUpdate(t1);
		return true;
	}

	/**
	 * 分页得到记录
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
		Tbspellpack t = (Tbspellpack) orderDao.loadById(Tbspellpack.class, id);
		if (t.getIpayState() == 1)
			return;
		/* 给应收 减去拼包金额 */
		List listar = orderDao.findByHql("from Tbar where sourceId="
				+ t.getSalenId() + " and sourceType=0 and IFNULL(fidel,0)<>1");
		if (listar != null && listar.size() > 0) {
			Tbar tar = (Tbar) listar.get(0);
			if (tar.getIstate() == 1)
				return;
			if (tar != null) {
				tar.setDlMoney(tar.getDlMoney() - t.getDlMoney());
				orderDao.saveOrUpdate(tar);
			}
		}
		orderDao.delete(t);
		// orderDao.deleteById(Tbspellpack.class, id);
	}

	/**
	 * 结算和反结算
	 * */
	@Override
	public void saveStateInfo(Integer id, String loginName, Integer companyId) {
		Tbspellpack t = (Tbspellpack) orderDao.loadById(Tbspellpack.class, id);
		if (t.getIpayState() != 1) { /* 结算 */
			t.setIpayState(1);
			t.setDtPay((new Date()).getTime() + "");
			t.setVcPayMan(loginName);
			/** 生成支出 **/
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String code = df.format(new Date());
			Tboutcome tout = new Tboutcome();
			Object objAccountid = orderDao
					.getSinge("select id from tbaccount where vcName='现金'"); /* 账户 */
			Object objAccountTypeid = orderDao
					.getSinge("select id from tbaccounttype where vcName='拼包'"); /* 账目类型 */
			tout.setAccountId((Integer) objAccountid);
			tout.setAccountTypeId((Integer) objAccountTypeid);
			tout.setCompanyId(companyId);
			tout.setDlMoney(t.getDlMoney());
			tout.setDtBs(t.getDtBs());/* 业务时间 */
			tout.setDtWrite(t.getDtPay());/* 录入时间 */
			tout.setFidel(0);
			tout.setSourceId(null);
			tout.setVcAuditor(loginName);
			tout.setVcNo(PublicClass.getCodeNo(orderDao, "tboutcome", "ZC",
					"vcNo", code));
			tout.setVcRemark(((Kh) orderDao.loadById(Kh.class, t.getKhId()))
					.getKhname()
					+ " 拼包人 " + t.getVcSpllName());
			orderDao.saveOrUpdate(tout);
			/** 生成资金余额 **/
			Tbbalance tbalan = new Tbbalance();
			tbalan.setAccountId(tout.getAccountId());
			tbalan.setCompanyId(tout.getCompanyId());
			tbalan.setDlMoney(-tout.getDlMoney());
			tbalan.setDtBs(tout.getDtWrite());
			tbalan.setSourceId(tout.getId());
			tbalan.setSourceType(0);/* 支出 */
			orderDao.saveOrUpdate(tbalan);

			/** 插入记录表 **/
			Tbdecorate tde = new Tbdecorate();
			tde.setFinoutid(tout.getId());
			tde.setFinouttype(1); /* 支出 */
			tde.setFcoreid(t.getId());
			tde.setFcoretype(1); /* 拼包 */
			orderDao.saveOrUpdate(tde);
		} else { /* 反结算 */
			t.setIpayState(0);
			t.setDtPay(null);
			t.setVcPayMan("");
			/** 删除支出 **/
			List listde = orderDao
					.findByHql("from Tbdecorate where fcoretype=1 and fcoreid="
							+ t.getId() + " and finouttype=1");
			if (listde != null) {
				Tbdecorate tde = (Tbdecorate) listde.get(0);
				if (tde != null) {
					Tboutcome tbout = (Tboutcome) orderDao.loadById(
							Tboutcome.class, tde.getFinoutid());
					/* 删除资金记录 */
					List list = orderDao
							.findByHql("from Tbbalance t where t.sourceId="
									+ tbout.getId() + " and t.sourceType=0");
					if (list.size() > 0) {
						orderDao.delete(list.get(0));
					}

					tbout.setFidel(1);
					orderDao.saveOrUpdate(tbout);
					orderDao.delete(tde);
				}
			}

		}
		orderDao.saveOrUpdate(t);
	}

	/**
	 * 获得信封打印的记录
	 * */
	@Override
	public void getprintInfo(Page page) {
		page.setTotal(orderDao.getCount(PublicClass.getPageCountSql(page)));
		page
				.setListMap(orderDao.getDataInfo(PublicClass
						.getPageLimitSql(page)));

	}

	/**
	 * 获得信封打印的记录
	 * */
	@Override
	public String getPrintData(String sql) {
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		/** 获得xml数据 **/
		HashMap map;
		Iterator iter;
		Object objNext;
		String str = "<NewDataSet>";
		for (int i = 0; i < listMap.size(); i++) {
			map = listMap.get(i);
			str += "<Table>";
			iter = map.keySet().iterator();
			while (iter.hasNext()) {
				objNext = iter.next();
				str += "<" + objNext + ">";
				str += map.get(objNext) == null ? "" : map.get(objNext);
				str += "</" + objNext + ">";
			}
			str += "</Table>";
		}
		str += "</NewDataSet>";
		return str;
	}
}
