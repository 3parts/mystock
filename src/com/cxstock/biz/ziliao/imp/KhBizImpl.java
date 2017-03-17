package com.cxstock.biz.ziliao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.ziliao.KhBiz;
import com.cxstock.biz.ziliao.dto.KhDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Kh;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

public class KhBizImpl implements KhBiz {

	private OrderDAO baseDao;

	public void setBaseDao(OrderDAO baseDao) {
		this.baseDao = baseDao;
	}

	/*
	 * 分页查询客户列表
	 */
	@SuppressWarnings("unchecked")
	public void findPageKh(Page page) {

		List list = baseDao.listAll("Kh", page.getStart(), page.getLimit(),
				page.getWheres());
		List dtoList = KhDTO.createDtos(list);
		String sql = "select count(1) from kh where " + page.getWheres() + "";
		int total = baseDao.getCount(sql);
		page.setRoot(dtoList);
		page.setTotal(total);

	}

	/*
	 * 保存/修改客户
	 */
	public void saveOrUpdateKh(KhDTO dto) {
		Kh kh = new Kh();
		if (dto.getKhid() != null) {
			kh = (Kh) baseDao.loadById(Kh.class, dto.getKhid());
		} else {
			kh.setCompanyid(dto.getCompanyid());
		}

		kh.setBz(dto.getBz());
		kh.setAddress(dto.getAddress());
		kh.setCity(dto.getCity());
		kh.setCountry(dto.getCountry());
		kh.setCredit(dto.getCredit() == null ? 0 : dto.getCredit());
		kh.setDistincts(dto.getDistinct());
		kh.setEmail(dto.getEmail());
		kh.setFax(dto.getFax());
		kh.setKhname(dto.getKhname());
		kh.setLxren(dto.getLxren());
		kh.setLxtel(dto.getLxtel());
		kh.setMobile(dto.getMobile());
		kh.setProvince(dto.getProvince());
		kh.setPycode(dto.getPycode());
		kh.setQq(dto.getQq());
		kh.setShippingAddress(dto.getShipping_address());
		kh.setShippingType(dto.getShipping_type());
		kh.setDeOwe(dto.getDeOwe());
		kh.setShippintClear(dto.getShippintClear());
		kh.setShippingLog(dto.getShippingLog());
		if (dto.getKhnum() == null || dto.getKhnum().length() == 0
				|| "不填写将自动生成".equals(dto.getKhnum())) {
			kh.setKhnum(PublicClass.getPyCode(dto.getKhname()).toUpperCase());
		} else {
			kh.setKhnum(dto.getKhnum().toUpperCase());
		}
		baseDao.saveOrUpdate(kh);
	}

	/*
	 * 删除客户
	 */
	public void deleteKh(Integer khid) {
		baseDao.deleteById(Kh.class, khid);
	}

	/*
	 * 客户下拉列表
	 */
	@SuppressWarnings("unchecked")
	public List findKhComb(String key, Integer keyid) {
		List<ComboData> list = new ArrayList<ComboData>();
		String sql = "SELECT t.khid,t.khname FROM Kh t WHERE "
				+ PublicClass.getRightStr("t.companyid");
		if (key != null && (key + "").length() > 0) {
			sql += " and (t.khname like '%" + key + "%' or t.khnum like '%"
					+ key + "%')";
		}
		if (keyid != null && keyid != -1) {
			sql += " and t.khid=" + keyid;
		}
		List<HashMap> listMap = baseDao.getDataInfo(sql);
		ComboData comb;
		for (int i = 0; i < listMap.size(); i++) {
			comb = new ComboData();
			comb.setValue(listMap.get(i).get("khid") + "");
			comb.setText(listMap.get(i).get("khname") + "");
			list.add(comb);
		}
		return list;
	}

	/**
	 * 获得省份的数据源
	 * */
	@Override
	public List getProvinceData() {
		return baseDao.findByHql("from Tbprovince");
	}

	/**
	 * 获得城市的数据源
	 * */
	@Override
	public List getCityData(String strProId) {
		String strSql = "from Tbcity t";
		if (strProId != null && !strProId.isEmpty()) {
			strSql += " where t.proviceid=" + strProId;
		}
		return baseDao.findByHql(strSql);
	}

	@Override
	public List getAreaData(String strAreaId) {
		String strSql = "from Tbarea t";
		if (strAreaId != null && !strAreaId.isEmpty()) {
			strSql += " where t.cityId=" + strAreaId;
		}
		return baseDao.findByHql(strSql);
	}

	@Override
	public List<HashMap> getKh(Integer id) {
		return baseDao.getDataInfo("select * from kh t where t.khid=" + id);
	}
}
