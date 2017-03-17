package com.cxstock.biz.purchase.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.cxstock.biz.purchase.SalenbackBiz;
import com.cxstock.biz.purchase.dto.SalenbackDTO;
import com.cxstock.biz.purchase.dto.SalenbackDelDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbar;
import com.cxstock.pojo.Tbsalenback;
import com.cxstock.pojo.Tbsalenbackdel;
import com.cxstock.pojo.Tbstock;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class SalenbackBizImpl implements SalenbackBiz {

	private OrderDAO orderDao;

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 分页获取记录
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public void getInfo(Page page) {
		String sql = "select " + page.getField() + " from " + page.getTable()
				+ " ";
		if (page.getWheres() != null && page.getWheres().length() > 0) {
			sql += " where " + page.getWheres();
		}
		page.setTotal(orderDao
				.getCount("select count(1) from (" + sql + ") t "));
		sql = " select t.* from (" + sql + ") t limit " + page.getStart() + ","
				+ page.getLimit();
		SalenbackDTO s;
		List list = new ArrayList();
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		for (int index = 0; index < listMap.size(); index++) {
			s = new SalenbackDTO();
			s.setCompanyId((Integer) listMap.get(index).get("companyId"));
			s.setDeMoney((Double) listMap.get(index).get("deMoney"));
			s.setDeOkMoney((Double) listMap.get(index).get("deOkMoney"));
			s.setDeOtherMoney((Double) listMap.get(index).get("deOtherMoney"));
			s.setDtBs((String) listMap.get(index).get("dtBs"));
			s.setId((Integer) listMap.get(index).get("id"));
			s.setIpayType((Integer) listMap.get(index).get("ipayType"));
			s.setKhId((Integer) listMap.get(index).get("khId"));
			s.setKhName((String) listMap.get(index).get("khName"));
			s.setUserId((Integer) listMap.get(index).get("userId"));
			s.setUserName((String) listMap.get(index).get("userName"));
			s.setVcNo((String) listMap.get(index).get("vcNo"));
			s.setVcRemark((String) listMap.get(index).get("vcRemark"));
			s.setWarehouseId((Integer) listMap.get(index).get("warehouseId"));
			s
					.setWarehouseName((String) listMap.get(index).get(
							"warehouseName"));
			s.setIcbill((Integer) listMap.get(index).get("icbill"));
			s.setFidel((Integer) listMap.get(index).get("fidel"));
			list.add(s);
		}
		page.setRoot(list);

	}

	/**
	 * 获取明细网格的记录
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List getInfoDel(String sql) {
		List<HashMap> list = orderDao.getDataInfo(sql);
		List list1 = new ArrayList();
		SalenbackDelDTO s;

		for (int index = 0; index < list.size(); index++) {
			s = new SalenbackDelDTO();
			s.setCommodityGg((String) list.get(index).get("commodityGg"));
			s.setCommodityId((Integer) list.get(index).get("commodityId"));
			s.setCommodityName((String) list.get(index).get("commodityName"));
			s.setCommodityNo((String) list.get(index).get("commodityNo"));
			s.setDeDiscount((Double) list.get(index).get("deDiscount"));
			s.setDePriceMoney((Double) list.get(index).get("dePriceMoney"));
			s.setDeSumMoney((Double) list.get(index).get("deSumMoney"));
			s.setIcount((Double) list.get(index).get("iCount"));
			s.setId((Integer) list.get(index).get("id"));
			s.setSalenbackId((Integer) list.get(index).get("salenbackId"));
			s.setVcBatch((String) list.get(index).get("vcBatch"));
			s.setVcDw((String) list.get(index).get("vcDw"));
			s.setVcSn((String) list.get(index).get("vcSn"));
			s.setWarehouseId((Integer) list.get(index).get("warehouseId"));
			s.setWarehouseName((String) list.get(index).get("warehouseName"));
			list1.add(s);
		}

		return list1;

	}

	/**
	 * 保存信息
	 * 
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean saveInfo(List list, Tbsalenback s, String loginName,
			Integer loginId) throws Exception {
		Tbsalenback s1 = new Tbsalenback();
		if (s.getId() != null) {
			s1 = (Tbsalenback) orderDao.loadById(Tbsalenback.class, s.getId());
			if (s1.getIcbill() == 1) {
				throw new Exception("单据已生成应付单,无法进行保存操作");
			}
		} else {
			s1.setCompanyId(s.getCompanyId());
			s1.setVcNo(this.getCode("tbsalenback", "XT"));
			s1.setIcbill(s.getIcbill());
			s1.setFidel(s.getFidel());
		}
		s1.setDeMoney(s.getDeMoney());
		s1.setDeOkMoney(s.getDeOkMoney());
		s1.setDeOtherMoney(s.getDeOtherMoney());
		s1.setDtBs(s.getDtBs());
		s1.setIpayType(s.getIpayType());
		s1.setKhId(s.getKhId());
		s1.setUserId(s.getUserId());
		s1.setVcRemark(s.getVcRemark());
		s1.setWarehouseId(s.getWarehouseId());
		orderDao.saveOrUpdate(s1);

		/* 保存明细项 */
		Tbsalenbackdel t1;
		Tbstock ts;
		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			t1 = (Tbsalenbackdel) list.get(i);
			t1.setSalenbackId(s1.getId());
			if (t1.getId() == null) /* 销退新增才增加库存 */
			{
				if (orderDao.getExist(PublicClass.getInventoryStockSql(t1
						.getCommodityId(), t1.getVcBatch(), t1.getVcSn(), t1
						.getWarehouseId()))) {
					throw new Exception("包含有正在盘点的商品，无法保存！");
				}

				/* 库存增加 ************ */
				List list11 = orderDao
						.findByHql("from Tbstock where commodityId="
								+ t1.getCommodityId() + " and vcBatch='"
								+ t1.getVcBatch() + "' and vcSn='"
								+ t1.getVcSn() + "' and warehouseId="
								+ t1.getWarehouseId() + "");
				// if (list11.size() == 1) { /* 控制唯一 */
				ts = (Tbstock) list11.get(0);
				ts.setDlQty(ts.getDlQty() + t1.getIcount());
				list1.add(ts);
				// }
			}
			list1.add(t1);
		}
		orderDao.saveOrUpdateAll(list1);

		/***********
		 * 生成应收 *
		 ***********/
		this.saveArInfo(s1.getId(), loginName, loginId);

		return true;
	}

	/**
	 * 获得单据编号
	 * */
	private String getCode(String tab, String key) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String code = df.format(new Date());
		return PublicClass.getCodeNo(orderDao, tab, key, "vcNo", code);
	}

	/**
	 * 删除销售明细Id 并增加库存记录
	 * */
	@Override
	public void deleteInfo(Integer delId, Boolean b) {
		Tbsalenbackdel t1 = (Tbsalenbackdel) orderDao.loadById(
				Tbsalenbackdel.class, delId);
		/**** 做库存减少 ***/
		List list11 = orderDao.findByHql("from Tbstock where commodityId="
				+ t1.getCommodityId() + " and vcBatch='" + t1.getVcBatch()
				+ "' and vcSn='" + t1.getVcSn() + "' and warehouseId="
				+ t1.getWarehouseId());
		Tbstock t11;
		for (int i = 0; i < list11.size(); i++) {
			t11 = (Tbstock) list11.get(i);
			t11.setDlQty(t11.getDlQty() - t1.getIcount());
			orderDao.saveOrUpdate(t11);
		}
		if (b) { /* 是否删除行记录 */
			orderDao.delete(t1);
		}
	}

	/**
	 * 删除主行记录 同时删除明细并删除库存
	 * */
	@Override
	public void deleteZhuInfo(Integer id) {
		Tbsalenback ts = (Tbsalenback) orderDao.loadById(Tbsalenback.class, id);
		if (ts == null || ts.getIcbill() == 1) {
			return;
		}
		String sql = "SELECT t.id FROM tbsalenbackdel t where t.salenbackId="
				+ ts.getId();
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		for (int i = 0; i < listMap.size(); i++) {
			deleteInfo(Integer.parseInt(listMap.get(i).get("id") + ""), false);
		}
		ts.setFidel(1); /* 保存作废信息 */
		orderDao.saveOrUpdate(ts);
		// orderDao.delete(ts);
	}

	/**
	 * 生成应收单据
	 * */
	@Override
	public void saveArInfo(Integer id, String loginName, Integer loginId) {
		List list = new ArrayList();
		Tbsalenback t = (Tbsalenback) orderDao.loadById(Tbsalenback.class, id);
		if (t.getIcbill() == 1)
			return;
		t.setIcbill(1);
		list.add(t);
		Tbar tr = new Tbar();
		tr.setCompanyId(t.getCompanyId());
		tr.setDlMoney(-t.getDeMoney());
		tr.setDtBs(t.getDtBs());
		tr.setDtWrite((new Date()).getTime() + "");
		tr.setKhId(t.getKhId());
		tr.setIstate(0);
		tr.setLogisticsId(0);
		tr.setSettlementId(0);
		tr.setSourceId(t.getId());
		tr.setSourceType(1); /* 销退 */
		tr.setUserId(loginId);
		tr.setVcAuditor(loginName);
		tr.setVcNo(this.getCode("tbar", "YS"));
		tr.setVcRemark(t.getVcRemark());
		tr.setFidel(0);
		list.add(tr);
		orderDao.saveOrUpdateAll(list);
	}

	/* 得到打印的xml数据 */
	@Override
	public String getXmlPrint(String sql) {
		List<HashMap> list = orderDao.getDataInfo(sql);
		if (list == null || list.size() <= 0)
			return "";
		HashMap map;
		String khAddress = "";
		Iterator iter;
		/****** 第一种方式 *******/
		String str = "<NewDataSet>";
		Object objNext;
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			str += "<Table>";
			/* 赋值客户地址 */
			if (map.get("shipping_address") == null
					|| (map.get("shipping_address") + "").length() <= 0) {
				khAddress = map.get("province") + "" + map.get("city")
						+ map.get("distincts") + map.get("address");
			} else {
				khAddress = map.get("province") + "" + map.get("city")
						+ map.get("distincts") + map.get("shipping_address");
			}
			/* 获得key */
			iter = map.keySet().iterator();
			while (iter.hasNext()) {
				objNext = iter.next();
				str += "<" + objNext + ">";
				str += map.get(objNext) == null ? "" : map.get(objNext);
				str += "</" + objNext + ">";
				/* 加入客户地址 */
				str += "<客户地址>";
				str += khAddress;
				str += "</客户地址>";
			}
			str += "</Table>";
		}
		str += "</NewDataSet>";
		/***** 第二种方式 *****/
		// String str = "<xml>";
		// Object objNext;
		// for (int i = 0; i < list.size(); i++) {
		// map = list.get(i);
		// str += "<row>";
		// /* 赋值客户地址 */
		// if (map.get("shipping_address") == null
		// || (map.get("shipping_address") + "").length() <= 0) {
		// khAddress = map.get("province") + "" + map.get("city")
		// + map.get("distincts") + map.get("address");
		// } else {
		// khAddress = map.get("province") + "" + map.get("city")
		// + map.get("distincts") + map.get("shipping_address");
		// }
		// /* 获得key */
		// iter = map.keySet().iterator();
		// while (iter.hasNext()) {
		// objNext = iter.next();
		// str += "<" + objNext + ">";
		// str += map.get(objNext) == null ? "" : map.get(objNext);
		// str += "</" + objNext + ">";
		// /* 加入客户地址 */
		// str += "<客户地址>";
		// str += khAddress;
		// str += "</客户地址>";
		// }
		// str += "</row>";
		// }
		// str += "</xml>";
		return str;
	}
}
