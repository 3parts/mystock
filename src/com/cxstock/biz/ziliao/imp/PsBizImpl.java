package com.cxstock.biz.ziliao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.ziliao.PsBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbperson;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class PsBizImpl implements PsBiz {

	private OrderDAO orderDao;

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 分页查询记录
	 * */
	@Override
	public void getInfo(Page page) {
		page.setField("a.*,b.vcName AS positionName");
		page.setTable("tbperson a LEFT JOIN tbposition b ON a.positionId=b.id");
		page.setTotal(orderDao.getCount("select count(1) from "
				+ page.getTable()));
		String sql = "select " + page.getField() + " from " + page.getTable();
		if (page.getWheres() != null && page.getWheres().length() > 0) {
			sql += " where " + page.getWheres();
		}
		List list = orderDao.getPerson("select * from (" + sql
				+ ") as t limit " + page.getStart() + "," + page.getLimit());
		page.setRoot(list);
	}

	/**
	 * 保存或更新记录
	 * */
	@Override
	public void saveOrUpdate(Tbperson t) {
		Tbperson t1 = new Tbperson();
		if (t.getId() != null) {
			t1 = (Tbperson) orderDao.loadById(Tbperson.class, t.getId());
		} else {
			t1.setCompanyId(t.getCompanyId());
			if (t.getDtQuit() == null) {
				/* 设定修改企业 */
				t1.setCompanyId(t.getCompanyId());
			}
		}
		t1.setDtEntry(t.getDtEntry());
		t1.setDtQuit(t.getDtQuit());
		t1.setIcomminSsion(t.getIcomminSsion());
		t1.setIgender(t.getIgender());
		t1.setIstate(t.getIstate());
		t1.setPositionId(t.getPositionId());
		t1.setVcAddress(t.getVcAddress());
		t1.setVcIdCard(t.getVcIdCard());
		t1.setVcName(t.getVcName());
		t1.setVcNation(t.getVcNation());
		t1.setVcNo(t.getVcNo());
		t1.setVcQuitReason(t.getVcQuitReason());
		t1.setVcRemark(t.getVcRemark());
		t1.setVcTel(t.getVcTel());
		orderDao.saveOrUpdate(t1);
	}

	/**
	 * 删除记录
	 * */
	@Override
	public void delete(Integer id) {
		orderDao.deleteById(Tbperson.class, id);
	}

	/**
	 * 员工下拉框
	 * */
	@Override
	public List findPsComb() {
		String sql = "SELECT id,vcName FROM tbperson WHERE iState=1";
		sql += " and " + PublicClass.getRightStr("companyId");
		List<HashMap> listMap = orderDao.getDataInfo(sql);
		List list = new ArrayList();
		ComboData comb;
		for (int i = 0; i < listMap.size(); i++) {
			comb = new ComboData();
			comb.setText(listMap.get(i).get("vcName") + "");
			comb.setValue(listMap.get(i).get("id") + "");
			list.add(comb);
		}
		return list;
	}
}
