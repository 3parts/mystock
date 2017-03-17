package com.cxstock.action.ziliao;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.ziliao.ComBiz;
import com.cxstock.pojo.Tbcommodity;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class ComAction extends BaseAction {

	private ComBiz comBiz;

	private Integer id;
	private String vcNo;
	private String vcName;
	private Integer typeId;
	private String vcDw;
	private String vcFactoryNo;
	private String vcFactoryName;
	private String vcColor;
	private String vcGg;
	private Double dbSuggMoney;
	private Double dbLowMoney;
	private Double dbAverageMoney;
	private Double dbLastMoney;
	private String vcRemark;
	private Integer companyId;

	private Integer lbid;
	private String lbname;

	private String search;
	private String addupdate;

	/**
	 * 分页查询商品信息列表
	 */
	public String findPageSpxx() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			if (lbid != null && lbid != 0) {
				page.setWheres(" where a.TypeId=" + lbid);
			} else if (search != null && !"".equals(search)) {
				StringBuffer buf = new StringBuffer(" where a.vcNo like '%");
				buf.append(search);
				buf.append("%' or a.vcName like '%");
				buf.append(search);
				buf.append("%'");
				page.setWheres(buf.toString());
			}
			if (page.getWheres() != null && page.getWheres().length() > 0) {
				page.setWheres(page.getWheres()
						+ PublicClass.getRightStr(" and a.companyId"));

			} else {
				page.setWheres(" where "
						+ PublicClass.getRightStr(" a.companyId"));
			}
			comBiz.findPageSpxx(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 保存/修改商品信息
	 */
	public String saveOrUpdateSpxx() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			/* 验证编号是否重复 */
			Tbcommodity spxx = new Tbcommodity(vcNo, vcName, typeId, vcDw,
					vcFactoryNo, vcFactoryName, vcColor, vcGg, dbSuggMoney,
					dbLowMoney, dbAverageMoney, dbLastMoney, vcRemark, udt
							.getCompanyid());

			spxx.setId(id);
			comBiz.saveOrUpdate(spxx);
			if (id == null || id == 0) {
				this.outString("{success:true,message:'保存成功!'}");
			} else {
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcNo() {
		return vcNo;
	}

	public void setVcNo(String vcNo) {
		this.vcNo = vcNo;
	}

	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getVcDw() {
		return vcDw;
	}

	public void setVcDw(String vcDw) {
		this.vcDw = vcDw;
	}

	public String getVcFactoryNo() {
		return vcFactoryNo;
	}

	public void setVcFactoryNo(String vcFactoryNo) {
		this.vcFactoryNo = vcFactoryNo;
	}

	public String getVcFactoryName() {
		return vcFactoryName;
	}

	public void setVcFactoryName(String vcFactoryName) {
		this.vcFactoryName = vcFactoryName;
	}

	public String getVcColor() {
		return vcColor;
	}

	public void setVcColor(String vcColor) {
		this.vcColor = vcColor;
	}

	public String getVcGg() {
		return vcGg;
	}

	public void setVcGg(String vcGg) {
		this.vcGg = vcGg;
	}

	public Double getDbSuggMoney() {
		return dbSuggMoney;
	}

	public void setDbSuggMoney(Double dbSuggMoney) {
		this.dbSuggMoney = dbSuggMoney;
	}

	public Double getDbLowMoney() {
		return dbLowMoney;
	}

	public void setDbLowMoney(Double dbLowMoney) {
		this.dbLowMoney = dbLowMoney;
	}

	public Double getDbAverageMoney() {
		return dbAverageMoney;
	}

	public void setDbAverageMoney(Double dbAverageMoney) {
		this.dbAverageMoney = dbAverageMoney;
	}

	public Double getDbLastMoney() {
		return dbLastMoney;
	}

	public void setDbLastMoney(Double dbLastMoney) {
		this.dbLastMoney = dbLastMoney;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public ComBiz getSpxxBiz() {
		return comBiz;
	}

	public Integer getLbid() {
		return lbid;
	}

	public String getLbname() {
		return lbname;
	}

	public String getSearch() {
		return search;
	}

	public String getAddupdate() {
		return addupdate;
	}

}
