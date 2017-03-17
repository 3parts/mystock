package com.cxstock.biz.purchase.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.purchase.IvtBiz;
import com.cxstock.biz.purchase.dto.InventoryDTO;
import com.cxstock.biz.purchase.dto.InventoryDelDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbinventory;
import com.cxstock.pojo.Tbinventorydel;
import com.cxstock.pojo.Tbstock;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class IvtBizImpl implements IvtBiz {

	private OrderDAO orderDao;

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	/**
	 * 获得明细信息
	 * */
	@Override
	public List getInfoDel(String sql) {
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		InventoryDelDTO t;
		List list = new ArrayList();
		for (int index = 0; index < listMap.size(); index++) {
			t = new InventoryDelDTO();
			t.setCommodityGg((String) listMap.get(index).get("commodityGg"));
			t.setCommodityId((Integer) listMap.get(index).get("commodityId"));
			t
					.setCommodityName((String) listMap.get(index).get(
							"commodityName"));
			t.setCommodityNo((String) listMap.get(index).get("commodityNo"));
			t.setDlActual((Double) listMap.get(index).get("dlActual"));
			t.setDlBefore((Double) listMap.get(index).get("dlBefore"));
			t.setId((Integer) listMap.get(index).get("id"));
			t.setInventoryId((Integer) listMap.get(index).get("inventoryId"));
			t.setStockId((Integer) listMap.get(index).get("stockId"));
			t.setVcBatch((String) listMap.get(index).get("vcBatch"));
			t.setVcSn((String) listMap.get(index).get("vcSn"));
			t.setWarehouseId((Integer) listMap.get(index).get("warehouseId"));
			list.add(t);
		}
		return list;
	}

	/**
	 * 保存记录
	 * */
	@Override
	public void saveInfo(List list, InventoryDTO t) {
		Tbinventory t1 = new Tbinventory();
		if (t.getId() != null) {
			t1 = (Tbinventory) orderDao.loadById(Tbinventory.class, t.getId());
		} else {
			t1.setCompanyId(t.getCompanyId());
			// t1.setVcNo(this.getCode("tbinventory"));
			t1.setIsOk(0);
			t1.setIstate(0);
			t1.setCheckUserId(0);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String code = df.format(new Date());
			String vcNo = PublicClass.getCodeNo(orderDao, "tbinventory", "PD",
					"vcNo", code);
			t1.setVcNo(vcNo);

		}
		t1.setDtBs(t.getDtBs());
		t1.setUserId(t.getUserId());
		t1.setVcRemark(t.getVcRemark());
		t1.setWarehouseId(t.getWarehouseId());
		orderDao.saveOrUpdate(t1);
		/* 保存明细 */
		List list1 = new ArrayList();
		Tbinventorydel t2;
		// Tbstock t3;
		for (int index = 0; index < list.size(); index++) {
			t2 = (Tbinventorydel) list.get(index);
			t2.setInventoryId(t1.getId());
			if (t2.getId() == 0) {
				t2.setId(null);
			}
			list1.add(t2);
			/* 修改库存记录 */
			// if (t2.getId() == null) {
			// t3 = (Tbstock) orderDao
			// .loadById(Tbstock.class, t2.getStockId());
			// t3.setDlQty(t2.getDlActual());
			// list1.add(t3);
			// }
		}
		orderDao.saveOrUpdateAll(list1);
	}

	/**
	 * 分页获得记录
	 * */
	@Override
	public void getInfo(Page page) {
		page.setTotal(orderDao.getCount(PublicClass.getPageCountSql(page)));
		List<HashMap> list = orderDao.getDataInfo(PublicClass.getPageSql(page));
		List list1 = new ArrayList();
		InventoryDTO t;
		for (int index = 0; index < list.size(); index++) {
			t = new InventoryDTO();
			t.setCheckUserId((Integer) list.get(index).get("checkUserId"));
			t.setCheckUserName((String) list.get(index).get("checkUserName"));
			t.setCompanyId((Integer) list.get(index).get("companyId"));
			t.setDtBs((String) list.get(index).get("dtBs"));
			t.setDtCheck((String) list.get(index).get("dtCheck"));
			t.setId((Integer) list.get(index).get("id"));
			t.setIsOk((Integer) list.get(index).get("isOk"));
			t.setIstate((Integer) list.get(index).get("istate"));
			t.setUserId((Integer) list.get(index).get("userId"));
			t.setUserName((String) list.get(index).get("userName"));
			t.setVcNo((String) list.get(index).get("vcNo"));
			t.setVcRemark((String) list.get(index).get("vcRemark"));
			t.setWarehouseId((Integer) list.get(index).get("warehouseId"));
			t.setWarehouseName((String) list.get(index).get("warehouseName"));
			list1.add(t);
		}
		page.setRoot(list1);
	}

	/**
	 * 删除主行
	 * */
	@Override
	public Boolean deleteInfo(int id) {
		Tbinventory t = (Tbinventory) orderDao.loadById(Tbinventory.class, id);
		if (t == null)
			return false;
		if (t.getIsOk() == 1 || t.getIstate() == 1)
			return false;
		List list = orderDao
				.findByHql("from Tbinventorydel t where t.inventoryId="
						+ t.getId());
		if (list == null)
			return false;
		for (int i = 0; i < list.size(); i++) {
			this.deleteInfoDel(((Tbinventorydel) list.get(i)).getId());
		}
		orderDao.delete(t);
		return true;
	}

	/**
	 * 删除明细
	 * */
	@Override
	public void deleteInfoDel(int iddel) {
		Tbinventorydel t = (Tbinventorydel) orderDao.loadById(
				Tbinventorydel.class, iddel);
		orderDao.delete(t);
	}

	/**
	 * 审核记录
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public void saveCheckInfo(int id, Integer loginId) {
		Tbinventory t = (Tbinventory) orderDao.loadById(Tbinventory.class, id);
		if (t.getIstate() == 1)
			return;
		t.setIstate(1);/* 已审核 */
		t.setCheckUserId(loginId);
		t.setDtCheck((new Date()).getTime() + "");
		List list1 = new ArrayList();
		List list = orderDao
				.findByHql("from Tbinventorydel t where t.inventoryId=" + id);
		Tbstock t2;
		for (int i = 0; i < list.size(); i++) {
			t2 = (Tbstock) orderDao.loadById(Tbstock.class,
					((Tbinventorydel) list.get(i)).getStockId());
			if (t2 != null) { /* 修改真实库存 */
				t2.setDlQty(((Tbinventorydel) list.get(i)).getDlActual());
				list1.add(t2);
			}
		}
		orderDao.saveOrUpdateAll(list1);
		orderDao.saveOrUpdate(t);
	}

	/**
	 * 盘点完成
	 * */
	@Override
	public void saveOkInfo(int id) {
		Tbinventory t = (Tbinventory) orderDao.loadById(Tbinventory.class, id);
		if (t.getIsOk() == 1)
			return;
		t.setIsOk(1);/* 盘点完成 */
		orderDao.saveOrUpdate(t);
	}

}
