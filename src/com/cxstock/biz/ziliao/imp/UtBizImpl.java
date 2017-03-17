package com.cxstock.biz.ziliao.imp;

import java.util.List;

import com.cxstock.biz.ziliao.UtBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbaccount;
import com.cxstock.pojo.Tbaccounttype;
import com.cxstock.pojo.Tblogistics;
import com.cxstock.pojo.Tblogisticscompany;
import com.cxstock.pojo.Tbposition;
import com.cxstock.pojo.Tbsettlement;
import com.cxstock.pojo.Tbunit;
import com.other.myclass.PublicClass;

public class UtBizImpl implements UtBiz {

	private OrderDAO baseDao;

	public void setBaseDao(OrderDAO baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 获得的list信息
	 * */
	@Override
	public List getInfo(String str) {
		return baseDao.findByHql("from " + str + " where "
				+ PublicClass.getRightStr("companyId"));
	}

	@Override
	public List getInfo(String str, Integer itype) {
		return baseDao.findByHql("from " + str + " as t where "
				+ PublicClass.getRightStr("t.companyId") + " and t.itype ="
				+ itype);
	}

	@Override
	public List getInfo1(String str, Integer vctype) {
		return baseDao.findByHql("from " + str + " as t where "
				+ PublicClass.getRightStr("t.companyId") + " and t.vctype ="
				+ vctype);
	}

	/**
	 * 保存记录
	 * */
	@Override
	public void saveOrUpdate(String strType, Object obj) {
		if ("jldw".equals(strType)) /* 计量单位 */{
			Tbunit u = (Tbunit) obj;
			Tbunit u1 = new Tbunit();
			if (u.getId() != null) {
				u1 = (Tbunit) baseDao.loadById(Tbunit.class, u.getId());
			} else {
				u1.setCompanyId(u.getCompanyId());
			}
			u1.setVcName(u.getVcName());
			u1.setVcNo(u.getVcNo());
			u1.setVcRemark(u.getVcRemark());
			baseDao.saveOrUpdate(u1);
		} else if ("jsfs".equals(strType))/* 结算方式 */{
			Tbsettlement u = (Tbsettlement) obj;
			Tbsettlement u1 = new Tbsettlement();
			if (u.getId() != null) {
				u1 = (Tbsettlement) baseDao.loadById(Tbsettlement.class, u
						.getId());
			} else {
				u1.setCompanyId(u.getCompanyId());
			}
			u1.setVcName(u.getVcName());
			u1.setVcNo(u.getVcNo());
			u1.setVcRemark(u.getVcRemark());
			baseDao.saveOrUpdate(u1);

		} else if ("psfs".equals(strType)) /* 配送方式 */{
			Tblogistics u = (Tblogistics) obj;
			Tblogistics u1 = new Tblogistics();
			if (u.getId() != null) {
				u1 = (Tblogistics) baseDao.loadById(Tblogistics.class, u
						.getId());
			} else {
				u1.setCompanyId(u.getCompanyId());
			}
			u1.setVcName(u.getVcName());
			u1.setVcNo(u.getVcNo());
			u1.setVcRemark(u.getVcRemark());
			baseDao.saveOrUpdate(u1);
		} else if ("wlgs".equals(strType)) { /* 物流公司 */
			Tblogisticscompany u = (Tblogisticscompany) obj;
			Tblogisticscompany u1 = new Tblogisticscompany();
			if (u.getId() != null) {
				u1 = (Tblogisticscompany) baseDao.loadById(
						Tblogisticscompany.class, u.getId());
			} else {
				u1.setCompanyId(u.getCompanyId());
			}
			u1.setVcName(u.getVcName());
			u1.setVcNo(u.getVcNo());
			u1.setVcRemark(u.getVcRemark());
			u1.setVctype(u.getVctype());
			baseDao.saveOrUpdate(u1);
		} else if ("zmlx".equals(strType)) {/* 账目类型 */
			Tbaccounttype u = (Tbaccounttype) obj;
			Tbaccounttype u1 = new Tbaccounttype();
			if (u.getId() != null) {
				u1 = (Tbaccounttype) baseDao.loadById(Tbaccounttype.class, u
						.getId());
			} else {
				u1.setCompanyId(u.getCompanyId());
			}
			u1.setVcName(u.getVcName());
			u1.setVcNo(u.getVcNo());
			u1.setVcRemark(u.getVcRemark());
			u1.setItype(u.getItype());
			baseDao.saveOrUpdate(u1);
		} else if ("zh".equals(strType)) { /* 账户 */
			Tbaccount u = (Tbaccount) obj;
			Tbaccount u1 = new Tbaccount();
			if (u.getId() != null) {
				u1 = (Tbaccount) baseDao.loadById(Tbaccount.class, u.getId());
			} else {
				u1.setCompanyId(u.getCompanyId());
			}
			u1.setVcName(u.getVcName());
			u1.setVcNo(u.getVcNo());
			u1.setVcRemark(u.getVcRemark());
			u1.setItype(u.getItype());
			baseDao.saveOrUpdate(u1);
		} else if ("zw".equals(strType)) { /* 职位 */
			Tbposition u = (Tbposition) obj;
			Tbposition u1 = new Tbposition();
			if (u.getId() != null) {
				u1 = (Tbposition) baseDao.loadById(Tbposition.class, u.getId());
			} else {
				u1.setCompanyId(u.getCompanyId());
			}
			u1.setVcName(u.getVcName());
			u1.setVcNo(u.getVcNo());
			u1.setVcRemark(u.getVcRemark());
			baseDao.saveOrUpdate(u1);
		}

	}

	/**
	 * 删除记录
	 * */
	@Override
	public void deleteInfo(String strType, Integer id) {
		if ("jldw".equals(strType)) /* 计量单位 */{
			baseDao.deleteById(Tbunit.class, id);
		} else if ("jsfs".equals(strType))/* 结算方式 */{
			baseDao.deleteById(Tbsettlement.class, id);

		} else if ("psfs".equals(strType)) /* 配送方式 */{
			baseDao.deleteById(Tblogistics.class, id);
		} else if ("wlgs".equals(strType)) { /* 物流公司 */
			baseDao.deleteById(Tblogisticscompany.class, id);
		} else if ("zmlx".equals(strType)) { /* 账目类型 */
			baseDao.deleteById(Tbaccounttype.class, id);
		} else if ("zh".equals(strType)) { /* 账户 */
			/** 查看账户是否余额 没有余额才能删除 **/
			String sql = "select sum(a.dlMoney) from Tbbalance a where a.accountId="
					+ id;
			Object obj = baseDao.getSinge(sql);
			/*
			 * if (obj == null || Integer.parseInt((String) obj) == 0) {
			 * baseDao.deleteById(Tbaccount.class, id); }
			 */
			if (obj != null && Double.parseDouble(obj + "") != 0.0) {
				return;
			}
			baseDao.deleteById(Tbaccount.class, id);
		} else if ("zw".equals(strType)) { /* 职位 */
			baseDao.deleteById(Tbposition.class, id);
		}

	}
}
