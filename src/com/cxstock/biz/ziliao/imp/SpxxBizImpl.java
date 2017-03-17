package com.cxstock.biz.ziliao.imp;

import java.util.List;

import com.cxstock.biz.ziliao.SpxxBiz;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Spxx;
import com.cxstock.pojo.Tbcommodity;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class SpxxBizImpl implements SpxxBiz {

	private OrderDAO orderDao;

	public void setOrderDao(OrderDAO baseDao) {
		this.orderDao = baseDao;
	}

	/*
	 * 商品编号
	 */
	@SuppressWarnings("unchecked")
	public String getSpxxCode() {
		String hql = "select max(spid) from Spxx";
		List list = orderDao.findByHql(hql);
		Object obj = list.get(0);
		if (obj != null) {
			Integer code = Integer.valueOf(obj.toString()) + 1;
			String codes = code.toString();
			int length = codes.length();
			for (int i = 4; i > length; i--) {
				codes = "0" + codes;
			}
			return codes;
		} else {
			return "0001";
		}

	}

	/*
	 * 分页查询商品列表
	 */
	@SuppressWarnings("unchecked")
	public void findPageSpxx(Page page) {
		String sql = " SELECT a.*,b.lbname AS TypeName,c.VC_NAME AS companyName,d.dlqty AS dlqty "
				+ " FROM tbcommodity AS a LEFT JOIN (splb AS b,tbcompany AS c) ON a.TypeId=b.lbid AND a.companyId=c.ID "
				+ " LEFT JOIN (SELECT t.commodityId,SUM(IFNULL(t.dlQty,0)) AS dlqty FROM tbstock t GROUP BY t.commodityId) d ON a.id=d.commodityId ";
		if (page.getWheres() != null) {
			sql += page.getWheres();
		}
		int total = orderDao.getCount("select count(1) from (" + sql + ") t");
		/* 分页 */
		sql = "select * from (" + sql + ") as tt limit " + page.getStart()
				+ "," + page.getLimit();

		List list = orderDao.finCommodityPage(sql);
		page.setRoot(list);
		page.setTotal(total);
	}

	/*
	 * 保存商品
	 */
	public void save(Spxx dto) {
		orderDao.save(dto);
	}

	/*
	 * 修改商品
	 */
	public void updateSpxx(Spxx dto) {
		Spxx spxx = (Spxx) orderDao.loadById(Spxx.class, dto.getSpid());
		spxx.setSpid(dto.getSpid());
		spxx.setSpname(dto.getSpname());
		spxx.setXinghao(dto.getXinghao());
		spxx.setDw(dto.getDw());
		if (dto.getJhprice() != null)
			spxx.setJhprice(dto.getJhprice());
		if (dto.getChprice() != null)
			spxx.setChprice(dto.getChprice());
		if (dto.getMinnum() != null)
			spxx.setMinnum(dto.getMinnum());
		spxx.setCsname(dto.getCsname());
		spxx.setBz(dto.getBz());
		// spxx.setLbid(dto.getSplb().getLbid());
		spxx.setLbname(dto.getLbname());
		orderDao.save(spxx);
	}

	/*
	 * 删除商品
	 */
	public boolean deleteSpxx(Integer spid) {
		Tbcommodity spxx = (Tbcommodity) orderDao.loadById(Tbcommodity.class,
				spid);
		/** 控制某个商品不能删除 **/
		if (orderDao.getExist("select 1 from tbstock t where t.commodityId="
				+ spid + " and dlQty>0")) {
			return false;
		}
		// if ("2".equals(spxx.getState())) {
		// return false;
		// }
		orderDao.delete(spxx);
		// List list = orderDao
		// .findByHql("from Tbstock where commodityId=" + spid);
		// for (int i = 0; i < list.size(); i++) {
		// Tbstock tb = (Tbstock) list.get(i);
		// orderDao.delete(tb);
		// }

		return true;
	}

	@Override
	public void saveOrUpdate(Tbcommodity tb) {
		Tbcommodity tb1 = new Tbcommodity();
		if (tb.getId() != null) {
			tb1 = (Tbcommodity) orderDao
					.loadById(Tbcommodity.class, tb.getId());
			tb1.setId(tb.getId());
		} else {
			tb1.setCompanyId(tb.getCompanyId());
		}
		tb1.setCompanyId(tb.getCompanyId());
		// tb1.setDbAverageMoney(tb.getDbAverageMoney());
		// tb1.setDbLastMoney(tb.getDbLastMoney());
		tb1.setDbLowMoney(tb.getDbLowMoney());
		tb1.setDbSuggMoney(tb.getDbSuggMoney());
		tb1.setTypeId(tb.getTypeId());
		tb1.setVcColor(tb.getVcColor());
		tb1.setVcDw(tb.getVcDw());
		tb1.setVcFactoryName(tb.getVcFactoryName());
		tb1.setVcFactoryNo(tb.getVcFactoryNo());
		tb1.setVcGg(tb.getVcGg());
		tb1.setVcName(tb.getVcName());
		tb1.setVcRemark(tb.getVcRemark());
		if (tb.getVcNo() == null || tb.getVcNo().length() <= 0
				|| "不填写将自动生成".equals(tb.getVcNo())) {
			tb1.setVcNo(PublicClass.getPyCode(tb.getVcName()).toUpperCase());
		} else {
			tb1.setVcNo(tb.getVcNo());
		}
		orderDao.saveOrUpdate(tb1);
	}

	@Override
	public boolean getCount(String sql) {
		List list = orderDao.findByHql(sql);
		return list.size() > 0;

	}

}
