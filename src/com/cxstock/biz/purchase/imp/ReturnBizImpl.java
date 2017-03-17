package com.cxstock.biz.purchase.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.cxstock.biz.purchase.ReturnBiz;
import com.cxstock.biz.purchase.dto.ReturnDTO;
import com.cxstock.biz.purchase.dto.ReturnDelDto;
import com.cxstock.biz.purchase.dto.StorageDelDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbap;
import com.cxstock.pojo.Tbreturn;
import com.cxstock.pojo.Tbreturndel;
import com.cxstock.pojo.Tbstock;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class ReturnBizImpl implements ReturnBiz {

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
		ReturnDTO s;
		for (int index = 0; index < listMap.size(); index++) {
			s = new ReturnDTO();
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
			s.setStorageId((Integer) listMap.get(index).get("returnId"));
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
			list.add(s);
		}
		page.setRoot(list);
	}

	/**
	 * 保存记录
	 * 
	 * @throws Exception
	 * */
	@Override
	public Integer saveInfo(List list, ReturnDTO s, String loginName,
			Integer loginId) throws Exception {
		Tbreturn s1 = new Tbreturn();
		if (s.getId() != null) {
			s1 = (Tbreturn) orderDao.loadById(Tbreturn.class, s.getId());
			if (s1.getIcbill() == 1) {
				throw new Exception("单据已生成应收单,无法进行保存操作");
			}
		} else {
			s1.setCompanyId(s.getCompanyId());
			s1.setVcNo(getCode("Tbreturn", "GT"));
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
		orderDao.saveOrUpdate(s1);
		/* 保存明细项 同时保存库存信息 */
		ReturnDelDto sd;
		Tbreturndel sd1;
		Tbstock ts;
		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			sd = (ReturnDelDto) list.get(i);
			if (orderDao.getExist(PublicClass.getInventoryStockSql(sd
					.getCommodityId(), sd.getVcBatch(), sd.getVcSn(), sd
					.getWarehouseId()))) {
				throw new Exception("包含有正在盘点的商品，无法保存！");
			}
			sd1 = new Tbreturndel();
			if (sd.getId() != null && sd.getId() != 0) {
				sd1 = (Tbreturndel) orderDao.loadById(Tbreturndel.class, sd
						.getId());
			}
			sd1.setReturnId(s1.getId()); /* 赋值主表ID */
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
			if (sd1.getId() == null) /* 新增才扣减库存 */
			{
				/* 库存减少 ************ */
				List list11 = orderDao
						.findByHql("from Tbstock where commodityId="
								+ sd1.getCommodityId() + " and vcBatch='"
								+ sd1.getVcBatch() + "' and vcSn='"
								+ sd1.getVcSn() + "' and warehouseId="
								+ sd1.getWarehouseId() + "");
				if (list11 == null) {
					throw new Exception("查询不到库存记录,请重试");
				}
				Boolean b = false;
				for (int j = 0; j < list11.size(); j++) {
					ts = (Tbstock) list11.get(j);
					if (ts.getDlQty() < sd1.getIcount()) {
						throw new Exception("购退数量大于库存数量");
						// continue;
					}
					// if (ts.getDlQty() < sd1.getIcount()) /* 控制数量不能大于库存数量 */
					// throw new Exception("购退数量大于库存数量");
					ts.setDlQty(ts.getDlQty() - sd1.getIcount());
					list1.add(ts);
					b = true;
					break;
				}
				if (!b) {
					throw new Exception("库存不足，无法进行购退操作");
				}
				// if (list11.size() == 1) { /* 控制唯一 */

				// }
			}
		}
		orderDao.saveOrUpdateAll(list1);

		/**************
		 * 生成应付
		 *************/
		this.saveApInfo(s1.getId(), loginName, loginId);
		return s1.getId();
	}

	/**
	 * 删除明细Id 并删除库存记录
	 * */
	@Override
	public void deleteInfo(Integer delId, Boolean b) {
		Tbreturndel t1 = (Tbreturndel) orderDao.loadById(Tbreturndel.class,
				delId);
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
	 * 获得单据编号
	 * */
	private String getCode(String tab, String key) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String code = df.format(new Date());
		return PublicClass.getCodeNo(orderDao, tab, key, "vcNo", code);
	}

	/**
	 * 删除主行记录 同时删除明细并删除库存
	 * */
	@Override
	public void deleteZhuInfo(Integer id) {
		Tbreturn ts = (Tbreturn) orderDao.loadById(Tbreturn.class, id);
		if (ts.getIcbill() == 1)
			return;
		String sql = "SELECT t.id FROM tbreturndel t where t.returnId="
				+ ts.getId();
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		for (int i = 0; i < listMap.size(); i++) {
			deleteInfo(Integer.parseInt(listMap.get(i).get("id") + ""), false);
		}
		ts.setFidel(1); /* 保存作废记录 */
		orderDao.saveOrUpdate(ts);
		// orderDao.delete(ts);
	}

	/**
	 * 添加应付单
	 * */
	@Override
	public void saveApInfo(Integer id, String loginName, Integer loginId) {
		List list = new ArrayList();
		Tbreturn ts = (Tbreturn) orderDao.loadById(Tbreturn.class, id);
		if (ts.getIcbill() == 1)
			return;
		ts.setIcbill(1);
		list.add(ts);
		/* 生成应付 */
		Tbap tp = new Tbap();
		tp.setCompanyId(ts.getCompanyId());
		tp.setDlMoney(-ts.getDeActualPayMoney());
		tp.setDtBs(ts.getDtBs());
		tp.setDtWrite((new Date()).getTime() + "");
		tp.setGysId(ts.getGysId());
		tp.setIstate(0);
		tp.setLogisticsId(0);
		tp.setSettlementId(0);
		tp.setSourceId(ts.getId());
		tp.setSourceType(1);
		tp.setUserId(loginId);
		tp.setVcAuditor(loginName);
		tp.setVcNo(this.getCode("tbap", "YF"));
		tp.setFidel(0);
		list.add(tp);
		orderDao.saveOrUpdateAll(list);
	}

	/**
	 * 功能：检测购退填写的记录是否合法 作者：RC 创建日期：2015-05-27
	 * */
	@Override
	public Boolean getLetMate(Integer spId, String batch, String sn,
			Integer houseid, Double count) {
		String sql = "select 1 from Tbstock where commodityId=" + spId
				+ " and vcBatch='" + batch + "' and vcSn='" + sn
				+ "' and warehouseId=" + houseid + " and dlQty>=" + count;
		return orderDao.getExist(sql);

	}

	@Override
	public String getXmlPrint(String sql) {
		List<HashMap> list = orderDao.getDataInfo(sql);
		if (list == null || list.size() <= 0)
			return "";
		HashMap map;
		Iterator iter;
		String str = "<NewDataSet>";
		Object objNext;
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			str += "<Table>";
			/* 获得key */
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
