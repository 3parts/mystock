package com.cxstock.action.purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.purchase.SalenbackBiz;
import com.cxstock.pojo.Tbsalenback;
import com.cxstock.pojo.Tbsalenbackdel;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class SalenbackAction extends BaseAction {

	private SalenbackBiz salenbackBiz;

	private Integer id;
	private String vcNo;
	private Date dtBs;
	private Integer khId;
	private Integer ipayType;
	private Double deMoney;
	private Double deOkMoney;
	private Double deOtherMoney;
	private Integer warehouseId;
	private String vcRemark;
	private Integer userId;
	private Integer companyId;

	private String jsonInfo;
	private String key;
	private Integer iddel;

	private Date dts;
	private Date dte;

	/**
	 * 获取销退的记录
	 * */
	public void getInfo() {
		try {
			Page page = new Page();
			page
					.setField("a.*,b.khname AS khName,c.vcName AS warehouseName,d.username AS userName");
			page
					.setTable("tbsalenback a LEFT JOIN kh b ON a.khId=b.khid LEFT JOIN tbwarehouse c ON a.warehouseId=c.id LEFT JOIN users d ON a.userId=d.userid");
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			page.setWheres(PublicClass.getRightStr("a.companyId"));
			if (khId != null) {
				page.setWheres(page.getWheres() + " and a.khId=" + khId);
			}
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and (a.vcNo like '%" + key
						+ "%' or b.khName like '%" + key
						+ "%' or a.vcRemark like '%" + key + "%')");
			}
			if (ipayType != null && ipayType != -1) {
				page
						.setWheres(page.getWheres() + " and a.ipayType="
								+ ipayType);
			}
			if (dts != null && dte != null) {
				page.setWheres(page.getWheres() + " and a.dtBs between '"
						+ dts.getTime() + "' and '"
						+ (dte.getTime() + 23 * 59 * 59 * 1000) + "'");
			}
			page.setWheres(page.getWheres() + " order by a.id desc");
			salenbackBiz.getInfo(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 获得明细信息
	 * */
	public void getInfoDel() {
		try {
			String sql = "select a.*,b.vcName AS commodityName,b.vcNo AS commodityNo,b.vcGg AS commodityGg,d.vcName AS warehouseName "
					+ " from tbsalenbackdel a LEFT JOIN tbcommodity b ON a.commodityId = b.id LEFT JOIN tbwarehouse d ON a.warehouseId = d.id";
			if (id != null) {
				sql += " where a.salenbackId=" + id;
			}
			sql += " ORDER BY A.id DESC";
			this.outListString(salenbackBiz.getInfoDel(sql));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 保存信息
	 * */
	public void saveSInfo() {

		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			Tbsalenback s = new Tbsalenback();
			s.setCompanyId(udt.getCompanyid());
			s.setDeMoney(deMoney);
			s.setDeOkMoney(deOkMoney);
			s.setDeOtherMoney(deOtherMoney);
			s.setDtBs(dtBs.getTime() + "");
			s.setId(id);
			// s.setIpayType(ipayType);
			s.setIpayType(0); /* 默认未结算 应付回写 */
			s.setKhId(khId);
			s.setUserId(udt.getUserid());
			s.setVcRemark(vcRemark);
			s.setWarehouseId(warehouseId);
			s.setIcbill(0);
			s.setFidel(0); /* 未作废 */
			List list = new ArrayList(); /* 存放明细对象 */
			String str = jsonInfo;
			JSONArray jsonArray = JSONArray.fromObject(str);
			JSONObject jt;
			Tbsalenbackdel s1;
			for (int i = 0; i < jsonArray.size(); i++) {
				jt = jsonArray.getJSONObject(i);
				s1 = new Tbsalenbackdel();
				if (jt.getInt("id") != 0) {
					s1.setId(jt.getInt("id"));
				}
				s1.setCommodityId(jt.getInt("commodityId"));
				s1.setDeDiscount(jt.getDouble("deDiscount"));
				s1.setDePriceMoney(jt.getDouble("dePriceMoney"));
				s1.setDeSumMoney(jt.getDouble("deSumMoney"));
				s1.setIcount(jt.getDouble("icount"));
				s1.setVcBatch(jt.getString("vcBatch"));
				s1.setVcDw(jt.getString("vcDw"));
				s1.setVcSn(jt.getString("vcSn"));
				s1.setWarehouseId(jt.getInt("warehouseId"));
				list.add(s1);
			}
			if (salenbackBiz.saveInfo(list, s, udt.getUsername(), udt
					.getUserid())) {
				if (id == null) {
					this.outString("{success:true,message:'保存成功!'}");
				} else {
					this.outString("{success:true,message:'修改成功!'}");
				}
			} else {
				this.outString("{success:false,message:'包含有正在盘点的商品，无法保存！'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError(e.getMessage());
		}

	}

	/**
	 * 删除明细记录
	 * */
	public void deleteDelInfo() {
		try {
			salenbackBiz.deleteInfo(iddel, true);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 删除主行信息
	 * */
	public void deleteInfo() {
		try {
			salenbackBiz.deleteZhuInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 生成应收单
	 * */
	public void saveArInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			salenbackBiz.saveArInfo(id, udt.getUsername(), udt.getUserid());
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：购退的打印事件
	 * @作者：RC
	 * @日期：2015-08-08
	 * */
	public void getPrintXmlData() {
		try {
			if (id == null)
				return;
			String sql = "SELECT a.deMoney AS 总金额,b.lxren AS 客户联系人,b.mobile as 联系手机,b.khname AS 客户名称,b.lxtel AS 电话,c.username AS 制单人,a.vcNo AS 单号,FROM_UNIXTIME( a.dtBs/1000,'%Y-%m-%d') AS 日期,a.vcRemark AS 备注,  "
					+ "IFNULL(b.province,'') AS province,IFNULL(b.city,'') AS city,IFNULL(b.distincts,'') AS distincts,IFNULL(b.shipping_address,'') AS shipping_address,IFNULL(b.address,'') AS address, "
					+ "f.VC_ADDRESS AS 企业地址,f.VC_QQ AS 企业QQ,f.fwx AS 企业微信,f.VC_TEL AS 企业电话,f.VC_FAX AS 企业传真,"
					+ "f.printInfo1 AS 附加资料1,f.printInfo2 AS 附加资料2,f.printInfo3 AS 附加资料3,"
					+ "h.vcName AS 商品名称,i.vcName AS 仓库,h.vcColor AS 颜色,h.vcGg AS 规格,g.iCount AS 数量,g.vcDw AS 单位,g.dePriceMoney AS 单价,"
					+ "g.deDiscount AS 优惠,g.deSumMoney AS 金额,g.vcBatch AS 购进批次 ,"
					+ "(SELECT SUM(t.dlQty) AS dlqty FROM tbstock t WHERE t.vcSn=g.vcSn AND t.vcBatch=g.vcBatch AND t.commodityId=g.commodityId) AS 剩余库存   "
					+ " FROM tbsalenback a LEFT JOIN kh b ON a.khId=b.khid LEFT JOIN users c ON a.userId=c.userid "
					+ " LEFT JOIN tbcompany f ON a.companyId=f.ID INNER JOIN tbsalenbackdel g ON a.id=g.salenbackId "
					+ " LEFT JOIN tbcommodity h ON g.commodityId=h.id LEFT JOIN tbwarehouse i ON g.warehouseId=i.id ";
			sql += " WHERE a.id=" + id;
			sql = "select t.*,CONCAT(t.剩余库存-t.数量,'+',t.数量,'=',t.剩余库存) AS 说明  from ("
					+ sql + ") t";
			String str = salenbackBiz.getXmlPrint(sql);
			this.outString(str);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public SalenbackBiz getSalenbackBiz() {
		return salenbackBiz;
	}

	public void setSalenbackBiz(SalenbackBiz salenbackBiz) {
		this.salenbackBiz = salenbackBiz;
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

	public Date getDtBs() {
		return dtBs;
	}

	public void setDtBs(Date dtBs) {
		this.dtBs = dtBs;
	}

	public Integer getKhId() {
		return khId;
	}

	public void setKhId(Integer khId) {
		this.khId = khId;
	}

	public Integer getIpayType() {
		return ipayType;
	}

	public void setIpayType(Integer ipayType) {
		this.ipayType = ipayType;
	}

	public Double getDeMoney() {
		return deMoney;
	}

	public void setDeMoney(Double deMoney) {
		this.deMoney = deMoney;
	}

	public Double getDeOkMoney() {
		return deOkMoney;
	}

	public void setDeOkMoney(Double deOkMoney) {
		this.deOkMoney = deOkMoney;
	}

	public Double getDeOtherMoney() {
		return deOtherMoney;
	}

	public void setDeOtherMoney(Double deOtherMoney) {
		this.deOtherMoney = deOtherMoney;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setJsonInfo(String jsonInfo) {
		this.jsonInfo = jsonInfo;
	}

	public String getJsonInfo() {
		return jsonInfo;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setIddel(Integer iddel) {
		this.iddel = iddel;
	}

	public Integer getIddel() {
		return iddel;
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
