package com.cxstock.biz.ziliao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Gys;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

@SuppressWarnings("unchecked")
public class GysBizImpl implements GysBiz {

	private OrderDAO baseDao;

	public void setBaseDao(OrderDAO baseDao) {
		this.baseDao = baseDao;
	}

	/*
	 * 分页查询供应商列表
	 */
	public void findPageGys(Page page) {
		List list = baseDao.listAll("Gys", page.getStart(), page.getLimit(),
				page.getWheres());
		List dtoList = GysDTO.createDtos(list);
		int total = baseDao.getCount("select count(1) from gys where "
				+ page.getWheres());
		page.setRoot(dtoList);
		page.setTotal(total);
	}

	/*
	 * 保存/修改供应商
	 */
	public void saveOrUpdateGys(GysDTO dto) {
		Gys gys = new Gys();
		if (dto.getGysid() != null) {
			gys = (Gys) baseDao.loadById(Gys.class, dto.getGysid());
		}
		gys.setName(dto.getName());
		gys.setLxren(dto.getLxren());
		gys.setLxtel(dto.getLxtel());
		gys.setAddress(dto.getAddress());
		gys.setBz(dto.getBz());
		gys.setCompanyid(dto.getCompanyid());
		gys.setFsn(dto.getFsn());
		gys.setFax(dto.getFax());
		if (dto.getNumber() == null || dto.getNumber().length() <= 0
				|| "不填写将自动生成".equals(dto.getNumber())) {
			gys.setNumber(PublicClass.getPyCode(dto.getName()).toUpperCase());
		} else {
			gys.setNumber(dto.getNumber());
		}
		baseDao.saveOrUpdate(gys);
	}

	/*
	 * 删除供应商
	 */
	public void deleteGys(Integer gysid) {
		baseDao.deleteById(Gys.class, gysid);
	}

	/*
	 * 供应商下拉拉列表
	 */
	public List<ComboData> findGysComb(String key, Integer keyid) {
		List<ComboData> list = new ArrayList<ComboData>();
		String sql = "SELECT t.gysid,t.name FROM Gys t";
		sql += " WHERE " + PublicClass.getRightStr("t.companyid");
		if (key != null && (key + "").length() > 0) {
			sql += " and (t.name like '%" + key + "%' or t.number like '%"
					+ key + "%')";
		}
		if (keyid != null && keyid != -1) {
			sql += " and t.gysid=" + keyid;
		}
		List<HashMap> listMap = baseDao.getDataInfo(sql);
		ComboData comb;
		for (HashMap obj : listMap) {
			comb = new ComboData();
			comb.setValue(obj.get("gysid") + "");
			comb.setText(obj.get("name") + "");
			list.add(comb);
		}
		return list;
	}

}
