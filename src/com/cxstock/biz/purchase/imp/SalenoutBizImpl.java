package com.cxstock.biz.purchase.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.cxstock.biz.purchase.SalenoutBiz;
import com.cxstock.biz.purchase.dto.SalenoutDTO;
import com.cxstock.biz.purchase.dto.SalenoutDelDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Kh;
import com.cxstock.pojo.Tbar;
import com.cxstock.pojo.Tbdelivery;
import com.cxstock.pojo.Tbsalenout;
import com.cxstock.pojo.Tbsalenoutdel;
import com.cxstock.pojo.Tbspellpack;
import com.cxstock.pojo.Tbstock;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class SalenoutBizImpl implements SalenoutBiz {

	private OrderDAO orderDao;

	/**
	 * 分页获得记录
	 * */
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
		SalenoutDTO s;
		List list = new ArrayList();
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		for (int index = 0; index < listMap.size(); index++) {
			s = new SalenoutDTO();
			s.setCompanyId((Integer) listMap.get(index).get("companyId"));
			s.setDeDiscount((Double) listMap.get(index).get("deDiscount"));
			s.setDeOkOutMoney((Double) listMap.get(index).get("deOkOutMoney"));
			s.setDeOtherMoeny((Double) listMap.get(index).get("deOtherMoeny"));
			s.setDeOutMoney((Double) listMap.get(index).get("deOkOutMoney"));
			s.setDeOweMoney((Double) listMap.get(index).get("deOweMoney"));
			s.setDtBs((String) listMap.get(index).get("dtBs"));
			s.setId((Integer) listMap.get(index).get("id"));
			s.setIkh((Integer) listMap.get(index).get("iKh"));
			s.setIlogistics((Integer) listMap.get(index).get("iLogistics"));
			s.setIpay((Integer) listMap.get(index).get("iPay"));
			s.setIsaleprint((Integer) listMap.get(index).get("iSaleprint"));
			s.setIsaleType((Integer) listMap.get(index).get("iSaleType"));
			s.setIsettlement((Integer) listMap.get(index).get("iSettlement"));
			s.setKhName((String) listMap.get(index).get("khName"));
			s.setSettlementName((String) listMap.get(index).get(
					"settlementName"));
			s.setIwl((Integer) listMap.get(index).get("iwl"));
			s
					.setLogisticsName((String) listMap.get(index).get(
							"logisticsName"));
			s.setIcbill((Integer) listMap.get(index).get("icbill"));
			s.setUserId((Integer) listMap.get(index).get("userId"));
			s.setUserName((String) listMap.get(index).get("userName"));
			s.setVcAddress((String) listMap.get(index).get("vcAddress"));
			s.setVcNo((String) listMap.get(index).get("vcNo"));
			s.setVcRemark((String) listMap.get(index).get("vcRemark"));
			s.setVcTel((String) listMap.get(index).get("vcTel"));
			s.setVcYunNo((String) listMap.get(index).get("vcYunNo"));
			s.setFidel((Integer) listMap.get(index).get("fidel"));
			s.setVcPhone((String) listMap.get(index).get("vcPhone"));
			s.setWlName((String) listMap.get(index).get("wlName"));
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
	 * 保存记录
	 * 
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public Integer saveInfo(List list, SalenoutDTO s, String loginName,
			Integer loginId) throws Exception {
		Tbsalenout s1 = new Tbsalenout();
		if (s.getId() != null) {
			s1 = (Tbsalenout) orderDao.loadById(Tbsalenout.class, s.getId());
			if (s1.getIcbill() == 1) {
				throw new Exception("单据已生成应收单,无法进行保存操作");
			}
		} else {
			s1.setCompanyId(s.getCompanyId());
			s1.setVcNo(this.getCode("tbsalenout", "XS"));
			s1.setIcbill(s.getIcbill());
			s1.setFidel(s.getFidel());
		}
		s1.setDeDiscount(s.getDeDiscount());
		s1.setDeOkOutMoney(s.getDeOkOutMoney());
		s1.setDeOtherMoeny(s.getDeOtherMoeny());
		s1.setDeOutMoney(s.getDeOutMoney());
		s1.setDeOweMoney(s.getDeOweMoney());
		s1.setDtBs(s.getDtBs());
		s1.setIkh(s.getIkh());
		s1.setIlogistics(s.getIlogistics());
		s1.setIwl(s.getIwl());
		s1.setIpay(s.getIpay());
		s1.setIsaleprint(s.getIsaleprint());
		s1.setIsaleType(s.getIsaleType());
		s1.setIsettlement(s.getIsettlement());
		s1.setUserId(s.getUserId());
		s1.setVcAddress(s.getVcAddress());
		s1.setVcRemark(s.getVcRemark());
		s1.setVcTel(s.getVcTel());
		s1.setVcYunNo(s.getVcYunNo());
		s1.setVcPhone(s.getVcPhone());
		orderDao.saveOrUpdate(s1);
		/* 保存明细项 */
		Tbsalenoutdel t1;
		Tbstock ts;
		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			t1 = (Tbsalenoutdel) list.get(i);
			t1.setSalenoutId(s1.getId());
			if (t1.getId() == null) /* 销售新增才扣减库存 */
			{
				if (orderDao.getExist(PublicClass.getInventoryStockSql(t1
						.getCommodityId(), t1.getVcBatch(), t1.getVcSn(), t1
						.getWarehouseId()))) {
					throw new Exception("包含有正在盘点的商品，无法保存！");
				}
				/* 库存减少 ************ */
				List list11 = orderDao
						.findByHql("from Tbstock where commodityId="
								+ t1.getCommodityId() + " and vcBatch='"
								+ t1.getVcBatch() + "' and vcSn='"
								+ t1.getVcSn() + "' and warehouseId="
								+ t1.getWarehouseId());
				// if (list11.size() == 1) { /* 控制唯一 */
				Boolean b = false;
				for (int j = 0; j < list11.size(); j++) {
					ts = (Tbstock) list11.get(j);
					if (ts.getDlQty() < t1.getIcount())
						continue;
					// if (ts.getDlQty() < t1.getIcount()) /* 控制数量不能大于库存数量 */
					// throw new Exception("销售数量大于库存数量，无法保存！");
					ts.setDlQty(ts.getDlQty() - t1.getIcount());
					list1.add(ts);
					b = true;
					break;
				}
				if (!b) {
					throw new Exception("销售数量大于库存数量，无法保存！");
				}
				// }
			}
			list1.add(t1);
		}

		orderDao.saveOrUpdateAll(list1);

		/************
		 * 生成应收 *
		 **********/
		this.saveArInfo(s1.getId(), loginName, loginId);

		/* 是否更新客户资料 */
		if ("1".equals(s.getBupdatekh())) {
			Kh kh = (Kh) orderDao.loadById(Kh.class, s.getIkh());
			if (kh != null) {
				kh.setLxtel(s.getVcTel());
				String strAddress = s.getVcAddress();
				if (kh.getProvince() != null && kh.getProvince().length() > 0) {
					strAddress = strAddress.replaceAll(kh.getProvince(), "");
				}
				if (kh.getCity() != null && kh.getCity().length() > 0) {
					strAddress = strAddress.replaceAll(kh.getCity(), "");
				}
				if (kh.getDistincts() != null && kh.getDistincts().length() > 0) {
					strAddress = strAddress.replaceAll(kh.getDistincts(), "");
				}

				kh.setAddress(strAddress);
				kh.setMobile(s.getVcPhone());
				kh.setDeOwe(s.getDeOweMoney());
				kh.setShippingType(s.getIlogisticsname());
				kh.setShippintClear(s.getIsettlementname());
				kh.setShippingLog(s.getIwlname());
				orderDao.saveOrUpdate(kh);
			}
		}
		return s1.getId();
	}

	/**
	 * 获得明细网格数据
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List getInfoDel(String sql) {
		List<HashMap> list = orderDao.getDataInfo(sql);
		List list1 = new ArrayList();
		SalenoutDelDTO s;

		for (int index = 0; index < list.size(); index++) {
			s = new SalenoutDelDTO();
			s.setCommodityGg((String) list.get(index).get("commodityGg"));
			s.setCommodityId((Integer) list.get(index).get("commodityId"));
			s.setCommodityName((String) list.get(index).get("commodityName"));
			s.setCommodityNo((String) list.get(index).get("commodityNo"));
			s.setDeDiscount((Double) list.get(index).get("deDiscount"));
			s.setDePriceMoney((Double) list.get(index).get("dePriceMoney"));
			s.setDeSumMoney((Double) list.get(index).get("deSumMoney"));
			s.setIcount((Double) list.get(index).get("iCount"));
			s.setId((Integer) list.get(index).get("id"));
			s.setSalenoutId((Integer) list.get(index).get("salenoutId"));
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
	 * 获得单据编号
	 * */
	public String getCode(String tab, String key) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String code = df.format(new Date());
		return PublicClass.getCodeNo(orderDao, tab, key, "vcNo", code);
	}

	/**
	 * 删除销售明细Id 并增加库存记录
	 * */
	@Override
	public void deleteInfo(Integer delId, Boolean b) {
		Tbsalenoutdel t1 = (Tbsalenoutdel) orderDao.loadById(
				Tbsalenoutdel.class, delId);
		/**** 做库存增加 ***/
		List list11 = orderDao.findByHql("from Tbstock where commodityId="
				+ t1.getCommodityId() + " and vcBatch='" + t1.getVcBatch()
				+ "' and vcSn='" + t1.getVcSn() + "' and warehouseId="
				+ t1.getWarehouseId());
		Tbstock t11;
		for (int i = 0; i < list11.size(); i++) {
			t11 = (Tbstock) list11.get(i);
			t11.setDlQty(t1.getIcount() + t11.getDlQty());
			orderDao.saveOrUpdate(t11);
		}
		if (b) { /* 是否删除行记录 */
			orderDao.delete(t1);
		}
	}

	/**
	 * 删除主行记录 同时删除明细并删除库存
	 * 
	 * @throws Exception
	 * */
	@Override
	public void deleteZhuInfo(Integer id) throws Exception {
		Tbsalenout ts = (Tbsalenout) orderDao.loadById(Tbsalenout.class, id);
		if (ts == null || ts.getIcbill() == 1) {
			return;
		}
		/* 查看是否有拼包货送货的记录 */
		String sql = "SELECT 1 FROM tbdelivery WHERE salenId=" + id
				+ " UNION ALL SELECT 1 FROM tbspellpack WHERE salenId=" + id;
		if (orderDao.getExist(sql)) {
			throw new Exception("销售单记录有拼包货送货记录,请先删除。");
		}

		sql = "SELECT t.id FROM tbsalenoutdel t where t.salenoutId="
				+ ts.getId();
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		for (int i = 0; i < listMap.size(); i++) {
			deleteInfo(Integer.parseInt(listMap.get(i).get("id") + ""), false);
		}
		ts.setFidel(1); /* 保存作废记录 */
		orderDao.saveOrUpdate(ts);
		// orderDao.delete(ts);
	}

	@Override
	public List<HashMap> getSalenDel(String sql) {
		return orderDao.getDataInfo(sql);
	}

	/**
	 * 获得销售相关的单据
	 * */
	@Override
	public void getSalenNo(Page page) {
		page.setTotal(orderDao.getCount(PublicClass.getPageCountSql(page)));
		page
				.setListMap(orderDao.getDataInfo(PublicClass
						.getPageLimitSql(page)));

	}

	/**
	 * 生成应收单据
	 * */
	@Override
	public void saveArInfo(Integer id, String loginName, Integer loginId) {
		List list = new ArrayList();
		Tbsalenout t = (Tbsalenout) orderDao.loadById(Tbsalenout.class, id);
		if (t.getIcbill() == 1)
			return;
		/** 合计拼包金额 **/
		Object obj = orderDao
				.getSinge("SELECT IFNULL(SUM(dlMoney),0) FROM tbspellpack WHERE salenId="
						+ id);
		/** 送货金额 **/
		Object obj1 = orderDao
				.getSinge("SELECT IFNULL(SUM(dlMoeny),0) FROM tbdelivery WHERE salenId="
						+ id);
		t.setIcbill(1);
		list.add(t);
		Tbar tr = new Tbar();
		tr.setCompanyId(t.getCompanyId());
		/** 合计拼包 送货金额 **/
		tr.setDlMoney(t.getDeOutMoney() + (Double) obj - (Double) obj1);
		tr.setDtBs(t.getDtBs());
		tr.setDtWrite((new Date()).getTime() + "");
		tr.setKhId(t.getIkh());
		tr.setIstate(0);
		tr.setLogisticsId(t.getIlogistics());
		tr.setSettlementId(t.getIsettlement());
		tr.setSourceId(t.getId());
		tr.setSourceType(0); /* 销售 */
		tr.setUserId(loginId);
		tr.setVcAuditor(loginName);
		tr.setVcNo(this.getCode("tbar", "YS"));
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

	/**
	 * 添加拼包
	 * */
	@Override
	public void saveSpll(Integer id, String vcSpllName, String vcRemark,
			Double dlMoney, Integer companyId, Integer loginId) {
		Tbsalenout t = (Tbsalenout) orderDao.loadById(Tbsalenout.class, id);
		if (t == null || t.getFidel() == 1 || t.getIpay() == 1)
			return;
		Tbspellpack t1 = new Tbspellpack();
		t1.setCompanyId(companyId);
		t1.setDlMoney(dlMoney);
		t1.setDtBs(new Date().getTime() + "");
		t1.setKhId(t.getIkh());
		t1.setSalenId(t.getId());
		t1.setUserId(loginId);
		t1.setVcSpllName(vcSpllName);
		t1.setIpayState(0);
		t1.setVcRemark(vcRemark);
		/* 新增拼包 给销售单的应收加入拼包金额 */
		List listar = orderDao.findByHql("from Tbar where sourceId="
				+ t.getId() + " and sourceType=0");
		if (listar != null) {
			Tbar tar = (Tbar) listar.get(0);
			if (tar != null) {
				tar.setDlMoney(tar.getDlMoney() + dlMoney);
				orderDao.saveOrUpdate(tar);
			}

		}
		orderDao.saveOrUpdate(t1);
	}

	/**
	 * 保存送货
	 * */
	@Override
	public String saveSh(Integer id, String vcSpllName, Double dlMoney,
			String vcRemark, String vcAddress, Integer companyId,
			Integer loginId) {
		Tbsalenout t = (Tbsalenout) orderDao.loadById(Tbsalenout.class, id);
		if (t == null || t.getFidel() == 1 || t.getIpay() == 1)
			return "销售单据不存在";
		if (orderDao.getExist("select 1 from tbdelivery where salenId=" + id)) {
			return "该销售单已进行了送货,可在送货查询里查看";
		}
		Tbdelivery t1 = new Tbdelivery();
		t1.setCompanyId(companyId);
		// t1.setDlMoeny(t.getDeOutMoney());
		t1.setDlMoeny(dlMoney);
		t1.setDtBs(new Date().getTime() + "");
		t1.setIpayState(0);
		t1.setKhId(t.getIkh());
		t1.setSalenId(t.getId());
		t1.setUserId(loginId);
		t1.setVcAddress(vcAddress);
		t1.setVcRemark(vcRemark);
		t1.setVcSendMan(vcSpllName);
		List listar = orderDao.findByHql("from Tbar where sourceId="
				+ t.getId() + " and sourceType=0");
		if (listar != null) {
			Tbar tar = (Tbar) listar.get(0);
			if (tar != null) {
				tar.setDlMoney(tar.getDlMoney() - dlMoney);
				orderDao.saveOrUpdate(tar);
			}

		}
		orderDao.saveOrUpdate(t1);
		return "添加送货成功,可在送货查询里面查看";
	}

}
