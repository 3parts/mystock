package com.cxstock.biz.purchase.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.cxstock.biz.purchase.PhBiz;
import com.cxstock.biz.purchase.dto.StorageDTO;
import com.cxstock.biz.purchase.dto.StorageDelDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbap;
import com.cxstock.pojo.Tbcommodity;
import com.cxstock.pojo.Tbstock;
import com.cxstock.pojo.Tbstorage;
import com.cxstock.pojo.Tbstoragedel;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class PhBizImpl implements PhBiz {

	private OrderDAO orderDao;

	public void setBaseDao(OrderDAO orderDao) {
		this.setOrderDao(orderDao);
	}

	/**
	 * 获得购进的记录
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public void getStorage(Page page) {
		String sql = "select " + page.getField() + " from " + page.getTable()
				+ " ";
		if (page.getWheres() != null && page.getWheres().length() > 0) {
			sql += " where " + page.getWheres();
		}
		page.setTotal(orderDao.getCount("select count(1) from (" + sql
				+ ") as t"));
		sql = "select * from (" + sql + ") as t limit " + page.getStart() + ","
				+ page.getLimit() + "";
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		List list = new ArrayList();
		StorageDTO s;
		for (int index = 0; index < listMap.size(); index++) {
			s = new StorageDTO();
			s.setId((Integer) listMap.get(index).get("id"));
			s.setVcNo((String) listMap.get(index).get("vcNo"));
			s.setGysId((Integer) listMap.get(index).get("gysId"));
			s.setDtBs((String) listMap.get(index).get("dtBs"));
			s.setDtReceived((String) listMap.get(index).get("dtReceived"));
			s.setDeShouldPayMoney((Double) listMap.get(index).get(
					"deShouldPayMoney"));
			s.setDeActualPayMoney((Double) listMap.get(index).get(
					"deActualPayMoney"));
			s.setIcbill((Integer) listMap.get(index).get("icbill"));
			s.setIpayState((Integer) listMap.get(index).get("iPayState"));
			s.setUserId((Integer) listMap.get(index).get("userId"));
			s.setVcRemark((String) listMap.get(index).get("vcRemark"));
			s.setGysName((String) listMap.get(index).get("gysName"));
			s.setUsername((String) listMap.get(index).get("username"));
			s.setCompanyId((Integer) listMap.get(index).get("companyId"));
			s.setFidel((Integer) listMap.get(index).get("fidel"));
			list.add(s);
		}
		page.setRoot(list);

	}

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	/**
	 * 获得购进的明细信息
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public void getStorageDel(Page page) {
		String sql = "select " + page.getField() + " from " + page.getTable()
				+ " ";
		if (page.getWheres() != null && page.getWheres().length() > 0) {
			sql += " where " + page.getWheres();
		}
		page.setTotal(orderDao.getCount("select count(1) from (" + sql
				+ ") as t"));
		// sql = "select * from (" + sql + ") as t limit " + page.getStart() +
		// ","
		// + page.getLimit() + "";
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		List list = new ArrayList();
		StorageDelDTO s;
		for (int index = 0; index < listMap.size(); index++) {
			s = new StorageDelDTO();

			s.setCommodityGg((String) listMap.get(index).get("commodityGg"));
			s.setCommodityId((Integer) listMap.get(index).get("commodityId"));
			s.setCommodityNo((String) listMap.get(index).get("commodityNo"));
			s
					.setCommodityName((String) listMap.get(index).get(
							"commodityName"));
			s.setDePurchaseMoney((Double) listMap.get(index).get(
					"dePurchaseMoney"));
			s.setDeSumMoney((Double) listMap.get(index).get("deSumMoney"));
			s.setIcount((Double) listMap.get(index).get("iCount"));
			s.setId((Integer) listMap.get(index).get("id"));
			s.setStorageId((Integer) listMap.get(index).get("storageId"));
			s.setVcDw((String) listMap.get(index).get("vcDw"));
			s.setVcBatch((String) listMap.get(index).get("vcBatch"));
			s.setVcColor((String) listMap.get(index).get("vcColor"));
			s.setVcRemark((String) listMap.get(index).get("vcRemark"));
			s.setVcSn((String) listMap.get(index).get("vcSn"));
			s.setWarehouseId((Integer) listMap.get(index).get("warehouseId"));
			s
					.setWarehouseName((String) listMap.get(index).get(
							"warehouseName"));
			s.setXlName((String) listMap.get(index).get("xlName"));
			s.setDlQty((Double) listMap.get(index).get("dlQty"));
			list.add(s);
		}
		page.setRoot(list);
	}

	/**
	 * 保存记录
	 * 
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public Integer saveInfo(List list, StorageDTO s, String loginName,
			Integer loginId) throws Exception {
		Tbstorage s1 = new Tbstorage();
		if (s.getId() != null) {
			s1 = (Tbstorage) orderDao.loadById(Tbstorage.class, s.getId());
			if (s1.getIcbill() == 1)
				throw new Exception("单据已经生成应付单,无法执行保存操作");
		} else {
			s1.setCompanyId(s.getCompanyId());
			s1.setVcNo(getCode("Tbstorage", "GJ"));
			s1.setIcbill(s.getIcbill());
			s1.setFidel(s.getFidel());
		}
		s1.setDeActualPayMoney(s.getDeActualPayMoney());
		s1.setDeShouldPayMoney(s.getDeShouldPayMoney());
		s1.setDtBs(s.getDtBs());
		s1.setDtReceived(s.getDtReceived());
		s1.setGysId(s.getGysId());
		s1.setIpayState(s.getIpayState());
		s1.setUserId(s.getUserId());
		s1.setVcRemark(s.getVcRemark());
		// s1.setIcbill(1); /* 已经生成支出 */
		orderDao.saveOrUpdate(s1);
		/* 保存明细项 同时保存库存信息 */
		StorageDelDTO sd;
		Tbstoragedel sd1;
		Tbstock ts;
		Tbcommodity com;
		List list1 = new ArrayList();
		List listSn = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			sd = (StorageDelDTO) list.get(i);
			if (orderDao.getExist(PublicClass.getInventoryStockSql(sd
					.getCommodityId(), sd.getVcBatch(), sd.getVcSn(), sd
					.getWarehouseId()))) {
				throw new Exception("包含有正在盘点的商品，无法保存！");
			}

			if (sd.getVcSn() == null || sd.getVcSn().length() == 0) {

				/** 生成辅助标识 **/
				String strSn = this.getSn(sd.getCommodityId(), sd.getVcBatch(),
						"N", listSn);
				sd.setVcSn(strSn);
			}
			if (listSn.contains(sd.getCommodityId() + "" + sd.getVcBatch() + ""
					+ sd.getVcSn())) {
				throw new Exception("标识符[" + sd.getVcSn() + "]重复，请删除后重新添加。");
			}
			listSn.add(sd.getCommodityId() + "" + sd.getVcBatch() + ""
					+ sd.getVcSn());
			sd1 = new Tbstoragedel();
			if (sd.getId() != null && sd.getId() != 0) {
				sd1 = (Tbstoragedel) orderDao.loadById(Tbstoragedel.class, sd
						.getId());
			}
			sd1.setStorageId(s1.getId()); /* 赋值主表ID */
			sd1.setCommodityId(sd.getCommodityId());
			sd1.setDePurchaseMoney(sd.getDePurchaseMoney());
			sd1.setDeSumMoney(sd.getDeSumMoney());
			sd1.setIcount(sd.getIcount());
			sd1.setVcBatch(sd.getVcBatch());
			sd1.setVcColor(sd.getVcColor());
			sd1.setVcDw(sd.getVcDw());
			sd1.setVcRemark(sd.getVcRemark());
			sd1.setVcSn(sd.getVcSn());
			sd1.setWarehouseId(sd.getWarehouseId());
			list1.add(sd1);
			/* 库存增加 */
			if (sd1.getId() == null || sd1.getId() == 0) {
				ts = new Tbstock();
				ts.setCompanyId(s1.getCompanyId());
				ts.setCommodityId(sd1.getCommodityId());
				ts.setDlQty(sd1.getIcount());
				ts.setVcBatch(sd1.getVcBatch());
				ts.setVcDw(sd1.getVcDw());
				ts.setVcSn(sd1.getVcSn());
				ts.setWarehouseId(sd1.getWarehouseId());
				list1.add(ts);

				/** 修改商品价格 **/
				com = (Tbcommodity) orderDao.loadById(Tbcommodity.class, sd1
						.getCommodityId());
				/* 修改平均成本价 */
				if (com.getDbAverageMoney() == null) {
					com.setDbAverageMoney(sd1.getDePurchaseMoney());
				} else {
					if (com.getDbAverageMoney() <= 0) {
						com.setDbAverageMoney(sd1.getDePurchaseMoney());
					} else {
						com.setDbAverageMoney((sd1.getDePurchaseMoney() + com
								.getDbAverageMoney()) / 2);
					}
				}
				/* 修改最后一次进价 */
				com.setDbLastMoney(sd1.getDePurchaseMoney());
				list1.add(com);
			}
		}
		orderDao.saveOrUpdateAll(list1);
		/****************
		 * 生成应付
		 ****************/
		this.saveApInfo(s1.getId(), loginName, loginId);

		return s1.getId();
	}

	/**
	 * 删除明细Id 并删除库存记录
	 * */
	@Override
	public void deleteInfo(Integer delId, Boolean b) {
		Tbstoragedel t1 = (Tbstoragedel) orderDao.loadById(Tbstoragedel.class,
				delId);
		if (t1 == null)
			return;
		String sql = "SELECT id FROM tbstock WHERE commodityId="
				+ t1.getCommodityId() + " and vcBatch='" + t1.getVcBatch()
				+ "' and vcSn='" + t1.getVcSn() + "'" + " and dlQty="
				+ t1.getIcount();
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		for (int i = 0; i < listMap.size(); i++) {
			orderDao.deleteById(Tbstock.class, Integer.parseInt(listMap.get(i)
					.get("id")
					+ ""));
		}
		if (b) { /* 是否删除行记录 */
			orderDao.delete(t1);
		}

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
	 * 功能：获得辅助标识符 作者：RC 创建日期：2015-05-28
	 * */
	public String getSn(String te) {
		int max = 9999;
		int min = 1000;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return te + s;
	}

	/**
	 * 获得辅助标识
	 * */
	public String getSn(Integer spid, String vcbach, String code, List list) {
		String sql = "select t.vcSn from tbstock t where t.commodityId=" + spid
				+ " and t.vcBatch='" + vcbach + "' and t.vcSn like '" + code
				+ "%' order by t.vcSn desc";
		Object obj = orderDao.getSinge(sql);
		if (obj != null && (obj + "").length() > 0) {
			String str = (obj + "").replace(code, "");
			Integer l = 1;
			for (int i = 0; i < list.size(); i++) {
				String str1 = (String) list.get(i);
				if (str1.indexOf(spid + "" + vcbach + "" + code) > -1) {
					l++;
				}
			}
			return code + (Integer.parseInt(str) + l);
		} else {
			Integer l = 1;
			for (int i = 0; i < list.size(); i++) {
				String str = (String) list.get(i);
				if (str.indexOf(spid + "" + vcbach + "" + code) > -1) {
					l++;
				}
			}
			return code + l;
		}
	}

	/**
	 * 删除主行记录 同时删除明细并删除库存
	 * */
	@Override
	public void deleteZhuInfo(Integer id) {
		Tbstorage ts = (Tbstorage) orderDao.loadById(Tbstorage.class, id);
		if (ts == null || ts.getIcbill() == 1)
			return;
		String sql = "SELECT t.id FROM tbstoragedel t where t.storageId="
				+ ts.getId();
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		for (int i = 0; i < listMap.size(); i++) {
			deleteInfo(Integer.parseInt(listMap.get(i).get("id") + ""), false);
		}
		ts.setFidel(1);
		orderDao.saveOrUpdate(ts); /* 保存作废 */
		// orderDao.delete(ts);
	}

	/**
	 * 获得购进和购退的全部记录
	 * */
	@Override
	public void getStorageInfo(Page page) {
		page.setTotal(orderDao.getCount(PublicClass.getPageCountSql(page)));
		page
				.setListMap(orderDao.getDataInfo(PublicClass
						.getPageLimitSql(page)));

	}

	/**
	 * 添加应付单
	 * */
	@Override
	public void saveApInfo(Integer id, String loginName, Integer loginId) {
		List list = new ArrayList();
		Tbstorage ts = (Tbstorage) orderDao.loadById(Tbstorage.class, id);
		if (ts.getIcbill() == 1)
			return;
		ts.setIcbill(1);
		list.add(ts);
		/* 生成应付 */
		Tbap tp = new Tbap();
		tp.setCompanyId(ts.getCompanyId());
		tp.setDlMoney(ts.getDeActualPayMoney());
		tp.setDtBs(ts.getDtBs());
		tp.setDtWrite((new Date()).getTime() + "");
		tp.setGysId(ts.getGysId());
		tp.setIstate(0);
		tp.setLogisticsId(0);
		tp.setSettlementId(0);
		tp.setSourceId(ts.getId());
		tp.setSourceType(0);
		tp.setUserId(loginId);
		tp.setVcAuditor(loginName);
		tp.setVcNo(this.getCode("tbap", "YF"));
		tp.setVcRemark(ts.getVcRemark());
		tp.setFidel(0);
		list.add(tp);

		orderDao.saveOrUpdateAll(list);
	}

	/**
	 * 根据表获得list
	 * */
	@Override
	public List getKey(String tab, String where) {
		return orderDao.findByHql("from " + tab + " where " + where);
	}

	/*************
	 * 查看是否重复 *
	 ************/
	@Override
	public String getExtsAddSpxx(Integer spid, String vcbach, String vcsn) {
		String sql = "select 1 from tbstock t where t.commodityId=" + spid
				+ " and t.vcBatch='" + vcbach + "' and t.vcSn='" + vcsn + "'";
		if (orderDao.getExist(sql)) {
			return "no";
		}
		return "ok";
	}

	@Override
	public void getStorageDel1(Page page) {
		page.setTotal(orderDao.getCount(PublicClass.getPageCountSql(page)));
		page
				.setListMap(orderDao.getDataInfo(PublicClass
						.getPageLimitSql(page)));
	}

	/* 根据商品id 查询商品信息 */
	@Override
	public List<HashMap> getspxx(Integer spid) {
		return orderDao.getDataInfo("SELECT * FROM tbcommodity WHERE id="
				+ spid);
	}

	/* 保存商品 */
	@Override
	public void saveSpxx(Tbcommodity t) {
		Tbcommodity t1 = (Tbcommodity) orderDao.loadById(Tbcommodity.class, t
				.getId());
		t1.setVcNo(t.getVcNo());
		t1.setVcName(t.getVcName());
		t1.setVcFactoryNo(t.getVcFactoryNo());
		t1.setVcFactoryName(t.getVcFactoryName());
		t1.setVcColor(t.getVcColor());
		t1.setVcGg(t.getVcGg());
		t1.setVcRemark(t.getVcRemark());
		orderDao.saveOrUpdate(t1);
	}
}
