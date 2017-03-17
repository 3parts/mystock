package com.cxstock.biz.ziliao.imp;

import java.util.List;

import com.cxstock.biz.ziliao.ComBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Spxx;
import com.cxstock.pojo.Tbcommodity;
import com.cxstock.utils.pubutil.Page;

public class ComBizImpl implements ComBiz {

	private OrderDAO orderDao;

	public void setOrderDao(OrderDAO baseDao) {
		this.orderDao = baseDao;
	}

	@Override
	public boolean deleteSpxx(String spid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void findPageSpxx(Page page) {
		String sql = " SELECT a.*,b.lbname AS TypeName,c.VC_NAME AS companyName FROM tbcommodity AS a LEFT JOIN (splb AS b,tbcompany AS c) ON a.TypeId=b.lbid AND a.companyId=c.ID ";
		if (page.getWheres() != null) {
			sql += page.getWheres();
		}
		/* 分页 */
		sql = "select * from (" + sql + ") as tt limit " + page.getStart()
				* page.getLimit() + "," + page.getLimit();

		List list = orderDao.finCommodityPage(sql);
		int total = orderDao
				.getCount("select count(1) FROM tbcommodity AS a LEFT JOIN splb AS b ON a.TypeId=b.lbid LEFT JOIN tbcompany AS c ON a.companyId=c.ID");
		page.setRoot(list);
		page.setTotal(total);
	}

	@Override
	public String getSpxxCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Spxx dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(Tbcommodity tb) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSpxx(Spxx dto) {
		// TODO Auto-generated method stub

	}

}
