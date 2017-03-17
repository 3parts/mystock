package com.cxstock.action.purchase;

import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.purchase.DlyBiz;
import com.cxstock.pojo.Tbdelivery;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class DlyAction extends BaseAction {

	private DlyBiz dlyBiz;

	private Integer id;
	private Integer salenId;
	private Integer khId;
	private Date dtBs;
	private String vcSendMan;
	private String vcAddress;
	private Double dlMoeny;
	private Integer ipayState;
	private Date dtPay;
	private String vcPayMan;
	private String vcRemark;
	private Integer userId;
	private Integer iprintTimes;
	private Integer companyId;

	private String key;
	private Double minCount;
	private Double maxCount;
	private Integer warehouseId;
	private Integer lbid;

	private Date dts;
	private Date dte;

	/**
	 * 保存记录
	 * */
	public void saveInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			Tbdelivery t = new Tbdelivery();
			t.setCompanyId(udt.getCompanyid());
			t.setDlMoeny(dlMoeny);
			t.setDtBs(dtBs.getTime() + "");
			t.setDtPay(dtPay == null ? "" : dtPay.getTime() + "");
			t.setId(id);
			t.setIpayState(ipayState);
			t.setKhId(khId);
			t.setSalenId(salenId);
			t.setUserId(udt.getUserid());
			t.setVcAddress(vcAddress);
			t.setVcPayMan(vcPayMan);
			t.setVcRemark(vcRemark);
			t.setVcSendMan(vcSendMan);
			dlyBiz.saveInfo(t);
			if (id == null) {
				this.outString("{success:true,message:'保存成功!'}");
			} else {
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 结算与反结算
	 * */
	public void saveStateInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			dlyBiz.saveStateInfo(id, udt.getUsername(), udt.getCompanyid());
			this.outString("{success:false}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 分页获取记录
	 * */
	public void getInfo() {
		try {
			Page page = new Page();
			page
					.setField("a.*,b.vcNo AS salenNo,c.khname AS khName,d.username AS userName");
			page
					.setTable("tbdelivery a LEFT JOIN tbsalenout b ON a.salenId=b.id LEFT JOIN kh c ON a.khId=c.khid LEFT JOIN users d ON a.userId=d.userid");
			page.setWheres(PublicClass.getRightStr("a.companyId"));
			if (key != null && key.length() > 0) {
				if (PublicClass.isNumeric(key)) {
					page.setWheres(page.getWheres() + " and (c.khname like '%"
							+ key + "%' or a.vcSendMan like '%" + key
							+ "%' or a.vcAddress like '%" + key
							+ "%' or a.dlMoeny='" + key + "')");
				} else {
					page.setWheres(page.getWheres() + " and (c.khname like '%"
							+ key + "%' or a.vcSendMan like '%" + key
							+ "%' or a.vcAddress like '%" + key + "%')");
				}
			}
			if (dts != null && dte != null) {
				page.setWheres(page.getWheres() + " and a.dtBs between '"
						+ dts.getTime() + "' and '"
						+ (dte.getTime() + 23 * 59 * 59 * 1000) + "'");
			}
			if (ipayState != null && ipayState != -1) {
				page.setWheres(page.getWheres() + " and a.ipayState="
						+ ipayState);
			}
			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			page.setStart(getStart());
			page.setLimit(getLimit());
			dlyBiz.getInfo(page);
			this.outHashMapPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 删除记录
	 * */
	public void deleteInfo() {
		try {
			dlyBiz.deleteInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 获得库存记录
	 * */
	public void getStockInfo() {
		try {
			// String sql =
			// "SELECT a.*,b.vcNo AS commodityNo,b.vcname AS commodityName,c.lbname AS commodityType,b.vcGg AS commodityGg,b.vcColor AS commodityColor,b.dbAverageMoney,b.dbSuggMoney,d.vcName AS warehouseName,b.dbAverageMoney*a.dlQty AS dlSumMoney";
			// sql +=
			// " FROM (SELECT a.vcBatch,a.vcDw,a.commodityId,a.companyId,SUM(a.dlQty) AS dlQty,a.warehouseId FROM tbstock a GROUP BY a.commodityId,a.companyId,a.vcBatch,a.warehouseId) a LEFT JOIN tbcommodity b ON a.commodityId=b.id LEFT JOIN splb c ON b.TypeId=c.lbid LEFT JOIN tbwarehouse d ON a.warehouseId=d.id";
			// sql += " WHERE " + PublicClass.getRightStr("a.companyId");
			// sql += " and a.dlQty>0";
			// if (key != null && key.length() > 0) {
			// sql += " and (a.vcBatch like '%" + key + "%' or a.vcSn like '%"
			// + key + "%' or b.vcName like '%" + key
			// + "%' or b.vcNo like '%" + key + "%' or b.vcGg like '%"
			// + key + "%' or c.lbname like '%" + key + "%')";
			// }
			// /** 数量范围 **/
			// if (maxCount != null && minCount != null) {
			// sql += " and a.dlQty>=" + minCount + " and a.dlQty <="
			// + maxCount;
			// }
			// if (warehouseId != null) {
			// sql += " and a.warehouseId=" + warehouseId;
			// }
			// this.outHashMapString(dlyBiz.getStockInfo(sql));
			/** 分页查询记录 **/
			Page page = new Page();
			page.setStart(getStart());
			page.setLimit(getLimit());
			page
					.setField("a.*,b.vcFactoryNo,b.vcNo AS commodityNo,b.vcname AS commodityName,c.lbname AS commodityType,b.vcGg AS commodityGg,b.vcColor AS commodityColor,b.dbAverageMoney,b.dbSuggMoney,d.vcName AS warehouseName,b.dbAverageMoney*a.dlQty AS dlSumMoney,b.vcRemark");
			page
					.setTable("(SELECT a.vcBatch,a.vcSn,a.vcDw,a.commodityId,a.companyId,SUM(a.dlQty) AS dlQty,a.warehouseId FROM tbstock a GROUP BY a.commodityId,a.companyId,a.vcBatch,a.warehouseId,a.vcSn) a LEFT JOIN tbcommodity b ON a.commodityId=b.id LEFT JOIN splb c ON b.TypeId=c.lbid LEFT JOIN tbwarehouse d ON a.warehouseId=d.id");
			page.setWheres(PublicClass.getRightStr("a.companyId")
					+ " and a.dlQty>0");
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and (b.vcName like '%"
						+ key + "%' or b.vcNo like '%" + key
						+ "%' or b.vcFactoryNo like '%" + key
						+ "%' or b.vcRemark like '%" + key + "%')");
			}
			if (lbid != null) {
				page.setWheres(page.getWheres() + " and b.TypeId=" + lbid);
			}
			/** 数量范围 **/
			if (maxCount != null && minCount != null) {
				page.setWheres(page.getWheres() + " and a.dlQty>=" + minCount
						+ " and a.dlQty <=" + maxCount);
			}
			if (warehouseId != null) {
				page.setWheres(page.getWheres() + " and a.warehouseId="
						+ warehouseId);
			}
			dlyBiz.getStockInfo(page);
			this.outHashMapPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public DlyBiz getDlyBiz() {
		return dlyBiz;
	}

	public void setDlyBiz(DlyBiz dlyBiz) {
		this.dlyBiz = dlyBiz;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalenId() {
		return salenId;
	}

	public void setSalenId(Integer salenId) {
		this.salenId = salenId;
	}

	public Integer getKhId() {
		return khId;
	}

	public void setKhId(Integer khId) {
		this.khId = khId;
	}

	public Date getDtBs() {
		return dtBs;
	}

	public void setDtBs(Date dtBs) {
		this.dtBs = dtBs;
	}

	public String getVcSendMan() {
		return vcSendMan;
	}

	public void setVcSendMan(String vcSendMan) {
		this.vcSendMan = vcSendMan;
	}

	public String getVcAddress() {
		return vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	public Double getDlMoeny() {
		return dlMoeny;
	}

	public void setDlMoeny(Double dlMoeny) {
		this.dlMoeny = dlMoeny;
	}

	public Integer getIpayState() {
		return ipayState;
	}

	public void setIpayState(Integer ipayState) {
		this.ipayState = ipayState;
	}

	public Date getDtPay() {
		return dtPay;
	}

	public void setDtPay(Date dtPay) {
		this.dtPay = dtPay;
	}

	public String getVcPayMan() {
		return vcPayMan;
	}

	public void setVcPayMan(String vcPayMan) {
		this.vcPayMan = vcPayMan;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIprintTimes() {
		return iprintTimes;
	}

	public void setIprintTimes(Integer iprintTimes) {
		this.iprintTimes = iprintTimes;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public Double getMinCount() {
		return minCount;
	}

	public void setMinCount(Double minCount) {
		this.minCount = minCount;
	}

	public Double getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Double maxCount) {
		this.maxCount = maxCount;
	}

	public void setLbid(Integer lbid) {
		this.lbid = lbid;
	}

	public Integer getLbid() {
		return lbid;
	}

	public void setDts(Date dts) {
		this.dts = dts;
	}

	public Date getDts() {
		return dts;
	}

	public void setDte(Date dte) {
		this.dte = dte;
	}

	public Date getDte() {
		return dte;
	}

}
