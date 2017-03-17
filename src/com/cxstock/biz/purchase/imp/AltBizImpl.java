package com.cxstock.biz.purchase.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.purchase.AltBiz;
import com.cxstock.biz.purchase.dto.AllocationDTO;
import com.cxstock.biz.purchase.dto.AllocationDelDTO;
import com.cxstock.biz.purchase.dto.StockDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tballocation;
import com.cxstock.pojo.Tballocationdel;
import com.cxstock.pojo.Tbstock;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class AltBizImpl implements AltBiz {

	private OrderDAO orderDao;

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	/**
	 * 获得库存记录
	 * */
	@Override
	public List getStockInfoDel(String sql) {
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		StockDTO s;
		List list = new ArrayList();
		for (int index = 0; index < listMap.size(); index++) {
			s = new StockDTO();
			s.setCommodityGg((String) listMap.get(index).get("commodityGg"));
			s.setCommodityId((Integer) listMap.get(index).get("commodityId"));
			s
					.setCommodityName((String) listMap.get(index).get(
							"commodityName"));
			s.setCommodityNo((String) listMap.get(index).get("commodityNo"));
			s.setCompanyId((Integer) listMap.get(index).get("companyId"));
			s.setDlQty((Double) listMap.get(index).get("dlQty"));
			s.setId((Integer) listMap.get(index).get("id"));
			s.setVcBatch((String) listMap.get(index).get("vcBatch"));
			s.setVcDw((String) listMap.get(index).get("vcDw"));
			s.setVcSn((String) listMap.get(index).get("vcSn"));
			s.setWarehouseId((Integer) listMap.get(index).get("warehouseId"));
			s
					.setWarehouseName((String) listMap.get(index).get(
							"warehouseName"));
			list.add(s);
		}
		return list;
	}

	/**
	 *得到调拨明细
	 * */
	@Override
	public List getInfoDel(String sql) {
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		AllocationDelDTO s;
		List list = new ArrayList();
		for (int index = 0; index < listMap.size(); index++) {
			s = new AllocationDelDTO();
			s.setAllocationId((Integer) listMap.get(index).get("allocationId"));
			s.setCommodityGg((String) listMap.get(index).get("commodityGg"));
			s.setCommodityId((Integer) listMap.get(index).get("commodityId"));
			s
					.setCommodityName((String) listMap.get(index).get(
							"commodityName"));
			s.setCommodityNo((String) listMap.get(index).get("commodityNo"));
			s.setDlCount((Double) listMap.get(index).get("dlCount"));
			s.setDlMoney((Double) listMap.get(index).get("dlMoney"));
			s.setId((Integer) listMap.get(index).get("id"));
			s.setInId((Integer) listMap.get(index).get("inId"));
			s.setInName((String) listMap.get(index).get("inName"));
			s.setOutId((Integer) listMap.get(index).get("outId"));
			s.setOutName((String) listMap.get(index).get("outName"));
			s.setVcBatch((String) listMap.get(index).get("vcBatch"));
			s.setVcDw((String) listMap.get(index).get("vcDw"));
			s.setVcSn((String) listMap.get(index).get("vcSn"));
			s.setVcRemark((String) listMap.get(index).get("vcRemark"));
			s.setComstockId((Integer) listMap.get(index).get("comstockId"));
			s.setGostockId((Integer) listMap.get(index).get("gostockId"));
			list.add(s);
		}
		return list;
	}

	/**
	 *保存记录
	 * 
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean saveInfo(List list, Tballocation t) throws Exception {
		Tballocation t1 = new Tballocation();
		if (t.getId() != null) {
			t1 = (Tballocation) orderDao
					.loadById(Tballocation.class, t.getId());
		} else {
			t1.setCompanyId(t.getCompanyId());
			// t1.setVcNo(this.getCode("tballocation", "DB"));
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String code = df.format(new Date());
			String vcNo = PublicClass.getCodeNo(orderDao, "tballocation", "DB",
					"vcNo", code);
			t1.setVcNo(vcNo);
		}
		t1.setDlCount(t.getDlCount());
		t1.setDlMoney(t.getDlMoney());
		t1.setDtBs(t.getDtBs());
		t1.setInId(t.getInId());
		t1.setIstate(t.getIstate());
		t1.setOutId(t.getOutId());
		t1.setUserId(t.getUserId());
		t1.setVcRemark(t.getVcRemark());
		orderDao.saveOrUpdate(t1);
		List list1 = new ArrayList();
		Tballocationdel t2;
		Tbstock tsOut;
		Tbstock tsIn;
		for (int i = 0; i < list.size(); i++) {
			t2 = (Tballocationdel) list.get(i);
			/* 查看库存是否在盘点 */
			if (orderDao.getExist(PublicClass.getInventoryStockSql(t2
					.getCommodityId(), t2.getVcBatch(), t2.getVcSn(), t2
					.getOutId()))) {
				throw new Exception("包含有正在盘点的商品，无法保存！");
				// return false;
			}

			if (t2.getId() == 0) /* 新增 */{
				t2.setId(null);
				t2.setAllocationId(t1.getId());
			}
			if (t2.getId() == null || t2.getId() == 0) {
				tsOut = (Tbstock) orderDao.loadById(Tbstock.class, t2
						.getComstockId());
				if (tsOut.getDlQty() < t2.getDlCount()) {
					throw new Exception("仓库库存不足，请检查后再进行操作");
				}
				tsOut.setDlQty(tsOut.getDlQty() - t2.getDlCount());
				orderDao.saveOrUpdate(tsOut);
				t2.setComstockId(tsOut.getId());
				/* 新增库存 */
				tsIn = new Tbstock();
				tsIn.setCommodityId(tsOut.getCommodityId());
				tsIn.setCompanyId(tsOut.getCompanyId());
				tsIn.setDlQty(t2.getDlCount());
				tsIn.setVcBatch(tsOut.getVcBatch());
				tsIn.setVcDw(tsOut.getVcDw());
				tsIn.setVcSn(tsOut.getVcSn());
				tsIn.setWarehouseId(t2.getInId());
				orderDao.saveOrUpdate(tsIn);
				t2.setGostockId(tsIn.getId());
			}
			orderDao.saveOrUpdate(t2);
		}
		return true;
	}

	/**
	 * 获得单据编号
	 * */
	private String getCode(String tab, String key) {
		// SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		// String code = df.format(new Date());
		// String sql = PublicClass.getCodeNo(tab, "vcNo", code);
		// Object obj = orderDao.getSinge(sql);
		// if (obj != null) {
		// String str = (obj + "").replace(key, "");
		// Long l = Long.parseLong(str + "") + 1;
		// return l + "";
		// }
		// return code + "0001";
		return "";
	}

	/**
	 * 分页得到记录
	 * */
	@Override
	public void getInfo(Page page) {
		page.setTotal(orderDao.getCount(PublicClass.getPageCountSql(page)));
		List<HashMap> list = orderDao.getDataInfo(PublicClass
				.getPageLimitSql(page));
		List list1 = new ArrayList();
		AllocationDTO t;
		for (int index = 0; index < list.size(); index++) {
			t = new AllocationDTO();
			t.setCompanyId((Integer) list.get(index).get("companyId"));
			t.setDlCount((Double) list.get(index).get("dlCount"));
			t.setDlMoney((Double) list.get(index).get("dlMoney"));
			t.setDtBs((String) list.get(index).get("dtBs"));
			t.setId((Integer) list.get(index).get("id"));
			t.setInId((Integer) list.get(index).get("inId"));
			t.setInName((String) list.get(index).get("inName"));
			t.setIstate((Integer) list.get(index).get("iState"));
			t.setOutId((Integer) list.get(index).get("outId"));
			t.setOutName((String) list.get(index).get("outName"));
			t.setUserId((Integer) list.get(index).get("userId"));
			t.setUserName((String) list.get(index).get("userName"));
			t.setVcNo((String) list.get(index).get("vcNo"));
			t.setVcRemark((String) list.get(index).get("vcRemark"));
			list1.add(t);
		}
		page.setRoot(list1);
	}

	/**
	 * 删除主行
	 * */
	@Override
	public void deleteInfo(Integer id) {
		List list = orderDao
				.findByHql("from Tballocationdel t where t.allocationId =" + id);
		if (list == null)
			return;
		for (int i = 0; i < list.size(); i++) {
			this.deleteInfoDel(((Tballocationdel) list.get(i)).getId());
		}
		orderDao.deleteById(Tballocation.class, id);
	}

	/**
	 * 删除明细行
	 * */
	@Override
	public void deleteInfoDel(Integer id) {
		Tballocationdel t = (Tballocationdel) orderDao.loadById(
				Tballocationdel.class, id);
		if (t == null)
			return;
		Tbstock t1 = (Tbstock) orderDao.loadById(Tbstock.class, t
				.getComstockId());
		if (t1 != null) { /* 累加数量 */
			t1.setDlQty(t1.getDlQty() + t.getDlCount());
		}
		/** 删除 后更新 库存数量 **/
		orderDao.delete(t);
		if (t1 != null) /* 只有当来源库存 不为空的时候才删除记录 */{
			orderDao.deleteById(Tbstock.class, t.getGostockId());
			orderDao.saveOrUpdate(t1);
		}

	}
}
