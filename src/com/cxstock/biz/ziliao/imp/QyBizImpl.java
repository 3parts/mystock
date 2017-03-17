package com.cxstock.biz.ziliao.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.ziliao.QyBiz;
import com.cxstock.biz.ziliao.dto.QyDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Tbcompany;
import com.cxstock.utils.pubutil.TreeNode;

public class QyBizImpl implements QyBiz {

	private OrderDAO orderDao;

	public void setOrderDao(OrderDAO baseDao) {
		this.orderDao = baseDao;
	}

	@Override
	public List findPageInfo(String strKey, int istart, int ilimit) {
		String strSql = "from Tbcompany as t ";
		if (strKey != null && !strKey.isEmpty()) {
			strSql += " where (t.vcName like '%" + strKey
					+ "%' or t.vcNumber like '%" + strKey + "%')";
		}

		return orderDao.findByHql(strSql, istart, ilimit);
	}

	@Override
	public void saveOrUpdateQy(QyDTO dto) {
		Tbcompany com = new Tbcompany();
		if (dto.getId() != null) {
			com.setId(dto.getId());
		}

		com.setDtBegeindate(dto.getDtBegeindate());
		com.setDtEnddate(dto.getDtEnddate());
		com.setVcAccountname(dto.getVcAccountname());
		com.setVcAccountnum(dto.getVcAccountnum());
		com.setVcAddress(dto.getVcAddress());
		com.setVcBank(dto.getVcBank());
		com.setVcCity(dto.getVcCity());
		com.setVcCity(dto.getVcCity());
		com.setVcContact(dto.getVcContact());
		com.setVcEmail(dto.getVcEmail());
		com.setVcFax(dto.getVcFax());
		com.setVcLogo(dto.getVcLogo());
		com.setVcMobile(dto.getVcMobile());
		com.setVcName(dto.getVcName());
		com.setVcNumber(dto.getVcNumber());
		com.setVcQq(dto.getVcQq());
		com.setVcRemark(dto.getVcRemark());
		com.setVcTel(dto.getVcTel());
		com.setVcWeixin(dto.getVcWeixin());
		com.setVState(dto.getVState());
		com.setVcWeb(dto.getVcWeb());
		com.setPrintInfo1(dto.getPrintInfo1());
		com.setPrintInfo2(dto.getPrintInfo2());
		com.setPrintInfo3(dto.getPrintInfo3());
		com.setFwx(dto.getFwx());

		orderDao.saveOrUpdate(com);

	}

	/**
	 * 删除数据
	 * */
	@Override
	public void deleteQyInfo(Integer qyid) {
		orderDao.deleteById(Tbcompany.class, qyid);
	}

	/**
	 * 加载企业数据
	 * */
	@SuppressWarnings("unchecked")
	public List loadQyInfo() {
		return orderDao.findByHql("from Tbcompany");
	}

	/**
	 * 加载树形企业
	 * */
	@Override
	public List findTreeInfo(String key) {
		String hql = "from Tbcompany";
		if (key != null && !key.isEmpty()) {
			hql += " where vcName like '%" + key + "%' ";
		}
		return this.getTree(0, orderDao.findByHql(hql));
	}

	/**
	 * 加载有多选框的树形
	 * */
	@SuppressWarnings("unchecked")
	public List findTreeInfo1(String userid) {
		if (userid != null && !userid.isEmpty()) {
			return orderDao.finUserRights(Integer.parseInt(userid));
		} else {
			return orderDao.finUserRights(0);
		}

	}

	/**
	 * 通过递归生成tree结构
	 */
	@SuppressWarnings("unchecked")
	private List getTree(Integer id, List childrenlist) {
		List resultlist = new ArrayList();

		for (Object obj : childrenlist) { /* 企业不构成树形结构 所以不需递归生成子节点 */
			Tbcompany tbCompany = (Tbcompany) obj;
			TreeNode treeNode = new TreeNode();
			treeNode.setId(tbCompany.getId() + "");
			treeNode.setText(tbCompany.getVcName());
			treeNode.setIconCls("menu-folder");
			treeNode.setLeaf(true);

			resultlist.add(treeNode);
		}
		return resultlist;
	}

	/**
	 * 返回记录的所有行
	 * */
	@Override
	public int countAll() {
		return orderDao.countAll("Tbcompany");
	}

	/* 当前用户是否能打开本url */
	@Override
	public String getOpenUrl(String url, Integer roleid) {
		String sql = "SELECT 1 FROM menu a INNER JOIN rolemenu b ON a.menuid=b.menuid "
				+ " WHERE a.menuurl='" + url + "' AND b.roleid=" + roleid;
		if (orderDao.getExist(sql))
			return "ok";
		return "no";
	}
}
