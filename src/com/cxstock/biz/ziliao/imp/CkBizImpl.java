package com.cxstock.biz.ziliao.imp;

import java.util.List;

import com.cxstock.biz.ziliao.CkBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Tbwarehouse;
import com.other.myclass.PublicClass;

public class CkBizImpl implements CkBiz {

	private BaseDAO baseDao;

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 查找仓库信息
	 * */
	@Override
	public List finCqInfo() {
		String hql = "from Tbwarehouse where "
				+ PublicClass.getRightStr("companyId");
		return baseDao.findByHql(hql);
	}

	@Override
	public void saveOrUpdate(Tbwarehouse tb) {
		Tbwarehouse tb1 = new Tbwarehouse();
		if (tb.getId() != null) {
			tb1 = (Tbwarehouse) baseDao.loadById(Tbwarehouse.class, tb.getId());
		} else {
			tb1.setCompanyId(tb.getCompanyId());
		}
		tb1.setVcAddress(tb.getVcAddress());
		tb1.setVcName(tb.getVcName());
		tb1.setVcNo(tb.getVcNo());
		tb1.setVcRemark(tb.getVcRemark());
		baseDao.saveOrUpdate(tb1);
	}

	@Override
	public void delete(Integer id) {
		String hql = "from Tbstock a where a.warehouseId=" + id;
		List list = baseDao.findByHql(hql);
		if (list != null && list.size() > 0) {
			return;
		}
		baseDao.deleteById(Tbwarehouse.class, id);
	}

}
