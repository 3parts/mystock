package com.cxstock.action.purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.purchase.SalenoutBiz;
import com.cxstock.biz.purchase.dto.SalenoutDTO;
import com.cxstock.pojo.Tbsalenoutdel;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class SalenoutAction extends BaseAction {

	private SalenoutBiz salenoutBiz;

	private Integer id;
	private String vcNo;
	private Date dtBs;
	private Integer ikh;
	private String vcTel;
	private Integer isaleType;
	private Integer isettlement;
	private Integer ipay;
	private Integer userId;
	private String vcAddress;
	private Integer ilogistics;
	private Integer iwl;
	private String vcYunNo;
	private Double deOutMoney;
	private Double deDiscount;
	private Double deOkOutMoney;
	private Double deOweMoney;
	private Double deOtherMoeny;
	private String vcRemark;
	private Integer isaleprint;
	private Integer companyId;
	private String vcPhone;

	private String ilogisticsname;
	private String isettlementname;
	private String iwlname;

	private String jsonInfo;
	private Integer iddel;
	private String key;
	private String bupdatekh;
	private String strType;

	private String vcSpllName;
	private Double dlMoney;

	private Date dts;
	private Date dte;

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

	public Integer getIkh() {
		return ikh;
	}

	public void setIkh(Integer ikh) {
		this.ikh = ikh;
	}

	public String getVcTel() {
		return vcTel;
	}

	public void setVcTel(String vcTel) {
		this.vcTel = vcTel;
	}

	public Integer getIsaleType() {
		return isaleType;
	}

	public void setIsaleType(Integer isaleType) {
		this.isaleType = isaleType;
	}

	public Integer getIsettlement() {
		return isettlement;
	}

	public void setIsettlement(Integer isettlement) {
		this.isettlement = isettlement;
	}

	public Integer getIpay() {
		return ipay;
	}

	public void setIpay(Integer ipay) {
		this.ipay = ipay;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVcAddress() {
		return vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	public Integer getIlogistics() {
		return ilogistics;
	}

	public void setIlogistics(Integer ilogistics) {
		this.ilogistics = ilogistics;
	}

	public String getVcYunNo() {
		return vcYunNo;
	}

	public void setVcYunNo(String vcYunNo) {
		this.vcYunNo = vcYunNo;
	}

	public Double getDeOutMoney() {
		return deOutMoney;
	}

	public void setDeOutMoney(Double deOutMoney) {
		this.deOutMoney = deOutMoney;
	}

	public Double getDeDiscount() {
		return deDiscount;
	}

	public void setDeDiscount(Double deDiscount) {
		this.deDiscount = deDiscount;
	}

	public Double getDeOkOutMoney() {
		return deOkOutMoney;
	}

	public void setDeOkOutMoney(Double deOkOutMoney) {
		this.deOkOutMoney = deOkOutMoney;
	}

	public Double getDeOweMoney() {
		return deOweMoney;
	}

	public void setDeOweMoney(Double deOweMoney) {
		this.deOweMoney = deOweMoney;
	}

	public Double getDeOtherMoeny() {
		return deOtherMoeny;
	}

	public void setDeOtherMoeny(Double deOtherMoeny) {
		this.deOtherMoeny = deOtherMoeny;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Integer getIsaleprint() {
		return isaleprint;
	}

	public void setIsaleprint(Integer isaleprint) {
		this.isaleprint = isaleprint;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获得记录
	 * */
	public void getInfo() {
		try {
			Page page = new Page();
			page
					.setField("a.*,f.vcName as wlName,b.khname AS khName,c.vcName AS settlementName,d.username AS userName,e.vcName AS logisticsName");
			page.setTable("tbsalenout a LEFT JOIN kh b ON a.iKh=b.khid"
					+ " LEFT JOIN tbsettlement c ON a.iSettlement=c.id"
					+ " LEFT JOIN users d ON a.userId=d.userid "
					+ " LEFT JOIN tblogistics e on a.iLogistics=e.id"
					+ " LEFT JOIN tblogisticscompany f on a.iwl=f.id");
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			page.setWheres(PublicClass.getRightStr("a.companyId"));
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and (a.vcNo like '%" + key
						+ "%' or a.vcRemark like '%" + key
						+ "%' or a.vcYunNo like '%" + key
						+ "%' or a.deOutMoney = '" + key + "')");
			}
			if (ikh != null && ikh != 0) {
				page.setWheres(page.getWheres() + " and a.iKh=" + ikh);
			}
			if (isettlement != null && isettlement != 0) {
				page.setWheres(page.getWheres() + " and a.iSettlement="
						+ isettlement);
			}
			if (ipay != null && ipay != -1) {
				page.setWheres(page.getWheres() + " and a.iPay=" + ipay);
			}
			if (isaleType != null && isaleType != -1) {
				page.setWheres(page.getWheres() + " and a.iSaleType="
						+ isaleType);
			}
			if (dts != null && dte != null) {
				page.setWheres(page.getWheres() + " and a.dtBs between '"
						+ dts.getTime() + "' and '"
						+ (dte.getTime() + 23 * 59 * 59 * 1000) + "'");
			}
			if (ilogistics != null) {
				page.setWheres(page.getWheres() + " and a.iLogistics="
						+ ilogistics);
			}
			if (iwl != null) {
				page.setWheres(page.getWheres() + " and a.iwl=" + iwl);
			}

			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			salenoutBiz.getInfo(page);
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
					+ " from tbsalenoutdel a LEFT JOIN tbcommodity b ON a.commodityId = b.id LEFT JOIN tbwarehouse d ON a.warehouseId = d.id";
			if (id != null) {
				sql += " where a.salenoutId=" + id;
			}
			sql += " ORDER BY A.id DESC";
			this.outListString(salenoutBiz.getInfoDel(sql));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 获得明细信息
	 * */
	public void getInfoDel1() {
		try {
			String sql = "select a.*,b.vcName AS commodityName,b.vcNo AS commodityNo,b.vcGg AS commodityGg,d.vcName AS warehouseName "
					+ " from tbsalenoutdel a LEFT JOIN tbcommodity b ON a.commodityId = b.id LEFT JOIN tbwarehouse d ON a.warehouseId = d.id "
					+ " INNER JOIN tbsalenout e ON a.salenoutId=e.id";
			sql += " where " + PublicClass.getRightStr("e.companyId")
					+ " and e.fidel=0"; /* 未作废 */
			if (key != null && key.length() > 0) {
				sql += " and (b.vcName like '%" + key + "%' or b.vcNo like '%"
						+ key + "%' or b.vcFactoryNo like '%" + key
						+ "%' or b.vcRemark like '%" + key + "%') ";
			}
			if (ikh != null) {
				sql += " and e.iKh="
						+ ikh
						+ " and NOT EXISTS(SELECT 1 FROM tbsalenbackdel t INNER JOIN tbsalenback t1 ON t.salenbackId=t1.id WHERE t.commodityId=a.commodityId AND t1.fidel=0 and t1.khId=e.iKh AND t.vcBatch=a.vcBatch AND t.vcSn=a.vcSn)";
			}
			sql += " ORDER BY A.id DESC LIMIT 0,50";
			this.outListString(salenoutBiz.getInfoDel(sql));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 保存信息
	 * */
	@SuppressWarnings("unchecked")
	public void saveSInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			SalenoutDTO s = new SalenoutDTO();
			s.setCompanyId(udt.getCompanyid());
			s.setDeDiscount(deDiscount);
			s.setDeOkOutMoney(deOkOutMoney);
			s.setDeOtherMoeny(deOtherMoeny);
			s.setDeOutMoney(deOutMoney);
			s.setDeOweMoney(deOweMoney);
			s.setDtBs(dtBs.getTime() + "");
			s.setId(id);
			s.setIkh(ikh);
			s.setIlogistics(ilogistics);
			s.setIwl(iwl);
			// s.setIpay(ipay);
			s.setIpay(0); /* 默认未结算 应收那边回写 */
			s.setIsaleprint(isaleprint);
			s.setIsaleType(isaleType);
			s.setIsettlement(isettlement);
			s.setVcYunNo(vcYunNo);
			s.setVcTel(vcTel);
			s.setVcRemark(vcRemark);
			s.setVcNo(vcNo);
			s.setVcAddress(vcAddress);
			s.setIcbill(0);
			s.setFidel(0); /* 未作废 */
			s.setUserId(udt.getUserid());
			s.setBupdatekh(bupdatekh); /* 是否更新客户 */
			s.setVcPhone(vcPhone);
			s.setIlogisticsname(ilogisticsname); /* 配送方式 */
			s.setIsettlementname(isettlementname); /* 结算方式 */
			s.setIwlname(iwlname); /* 物流公司 */

			List list = new ArrayList(); /* 存放明细对象 */
			String str = jsonInfo;
			JSONArray jsonArray = JSONArray.fromObject(str);
			JSONObject jt;
			Tbsalenoutdel s1;
			for (int i = 0; i < jsonArray.size(); i++) {
				jt = jsonArray.getJSONObject(i);
				s1 = new Tbsalenoutdel();
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
			Integer newid = salenoutBiz.saveInfo(list, s, udt.getUsername(),
					udt.getUserid());
			if (id == null) {
				this.outString("{success:true,message:'保存成功!',newid:" + newid
						+ "}");
			} else {
				this.outString("{success:true,message:'修改成功!',newid:" + newid
						+ "}");
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
			salenoutBiz.deleteInfo(iddel, true);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError(e.getMessage());
		}
	}

	/**
	 * 删除主行信息
	 * */
	public void deleteInfo() {
		try {
			salenoutBiz.deleteZhuInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError(e.getMessage());
		}
	}

	/**
	 * 获得销售明细行记录
	 * */
	public void getSalenDelInfo() {
		try {
			String sql = "SELECT a.id,a.vcNo,a.dtBs,a.deOutMoney,a.vcAddress,a.vcRemark,a.iKh,b.khname AS khName FROM tbsalenout a LEFT JOIN kh b ON a.iKh=b.khid";
			sql += " where " + PublicClass.getRightStr("a.companyId");
			if (key != null && key.length() > 0) {
				sql += " and (a.vcNo like '%" + key
						+ "%' or a.vcRemark like '%" + key
						+ "%' or b.khname like '%" + key + "%')";
			}
			if (strType.equals("pb")) /* 拼包 */{
				// sql +=
				// " and (NOT EXISTS (SELECT 1 FROM tbspellpack t WHERE t.salenId=a.id))";
			} else if (strType.equals("sh")) /* 送货 */{
				sql += " and (NOT EXISTS (SELECT 1 FROM tbdelivery t WHERE t.salenId=a.id))";
			}
			sql += " ORDER BY a.id DESC LIMIT 0,50";
			this.outHashMapString(salenoutBiz.getSalenDel(sql));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 获得销售相关的记录
	 * */
	public void getSalentInfo() {
		try {
			String strTab = "(SELECT a.id,'销售' AS vcType,a.vcNo,a.dtBs,a.deOutMoney AS dlMoney,a.iKh AS khId,a.iSettlement,a.iLogistics,a.companyId FROM tbsalenout a where a.icbill=0 and a.fidel=0"
					+ " UNION ALL"
					+ " SELECT b.id,'销退' AS vcType,b.vcNo,b.dtBs,b.deMoney AS dlMoney,b.khId,0 AS iSettlement,0 AS iLogistics,b.companyId FROM tbsalenback b where b.icbill=0 and b.fidel=0)";
			Page page = new Page();
			page.setField("t.*");
			page.setTable(strTab + " t");
			page.setLimit(getLimit());
			page.setStart(getStart());
			page.setWheres(PublicClass.getRightStr("t.companyId"));
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and t.vcNo like '%" + key
						+ "%'");
			}
			page
					.setWheres(page.getWheres()
							+ " ORDER BY t.id DESC LIMIT 0,100");
			salenoutBiz.getSalenNo(page);
			this.outHashMapPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

	}

	/**
	 * 生成应收单据
	 * */
	public void saveArInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			salenoutBiz.saveArInfo(id, udt.getUsername(), udt.getUserid());
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：得到打印的xml数据
	 * @作者：RC
	 * @日期：2015-07-04
	 * @throws Exception
	 * */
	public void getPrintXmlData() throws Exception {
		try {
			if (id == null)
				return;
			String sql = "SELECT a.deOutMoney AS 总金额,b.lxren AS 客户联系人,a.vcPhone as 联系手机,b.khname AS 客户名称,b.lxtel AS 电话,c.username AS 制单人,CONCAT(j.vcName,' ',IFNULL(d.vcName,'')) AS 配送方式,a.vcNo AS 单号,FROM_UNIXTIME( a.dtBs/1000,'%Y-%m-%d') AS 日期,CONCAT(a.vcYunNo,' ',a.vcRemark) AS 备注,  "
					+ "e.vcName AS 结算方式,IFNULL(b.province,'') AS province,IFNULL(b.city,'') AS city,IFNULL(b.distincts,'') AS distincts,IFNULL(b.shipping_address,'') AS shipping_address,IFNULL(b.address,'') AS address, "
					+ "f.VC_ADDRESS AS 企业地址,f.VC_QQ AS 企业QQ,f.fwx AS 企业微信,f.VC_TEL AS 企业电话,f.VC_FAX AS 企业传真,"
					+ "f.printInfo1 AS 附加资料1,f.printInfo2 AS 附加资料2,f.printInfo3 AS 附加资料3,"
					+ "h.vcName AS 商品名称,i.vcName AS 仓库,h.vcColor AS 颜色,h.vcGg AS 规格,g.iCount AS 数量,g.vcDw AS 单位,g.dePriceMoney AS 单价,"
					+ "g.deDiscount AS 优惠,g.deSumMoney AS 金额,g.vcBatch AS 购进批次 ,"
					+ "(SELECT SUM(t.dlQty) AS dlqty FROM tbstock t WHERE t.vcSn=g.vcSn AND t.vcBatch=g.vcBatch AND t.commodityId=g.commodityId) AS 剩余库存   "
					+ " FROM tbsalenout a LEFT JOIN kh b ON a.iKh=b.khid LEFT JOIN users c ON a.userId=c.userid "
					+ " LEFT JOIN tblogisticscompany d ON a.iwl=d.id LEFT JOIN tbsettlement e ON a.iSettlement = e.id "
					+ " LEFT JOIN tbcompany f ON a.companyId=f.ID INNER JOIN tbsalenoutdel g ON a.id=g.salenoutId "
					+ " LEFT JOIN tbcommodity h ON g.commodityId=h.id LEFT JOIN tbwarehouse i ON g.warehouseId=i.id "
					+ " LEFT JOIN tblogistics j ON a.iLogistics = j.id";
			sql += " WHERE a.id=" + id;
			sql = "select t.*,CONCAT(t.剩余库存+t.数量,'-',t.数量,'=',t.剩余库存) AS 说明  from ("
					+ sql + ") t";
			String str = salenoutBiz.getXmlPrint(sql);
			this.outString(str);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

	}

	/**
	 * 保存拼包
	 * */
	public void saveSpll() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			salenoutBiz.saveSpll(id, vcSpllName, vcRemark, dlMoney, udt
					.getCompanyid(), udt.getUserid());
			this.outString("{success:true,message:'添加拼包成功,可在拼包登记里面查看详情'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 保存送货
	 * */
	public void saveSh() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			String str = salenoutBiz.saveSh(id, vcSpllName, dlMoney, vcRemark,
					vcAddress, udt.getCompanyid(), udt.getUserid());
			this.outString("{success:true,message:'" + str + "'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public void setSalenoutBiz(SalenoutBiz salenoutBiz) {
		this.salenoutBiz = salenoutBiz;
	}

	public SalenoutBiz getSalenoutBiz() {
		return salenoutBiz;
	}

	public void setJsonInfo(String jsonInfo) {
		this.jsonInfo = jsonInfo;
	}

	public String getJsonInfo() {
		return jsonInfo;
	}

	public void setIddel(Integer iddel) {
		this.iddel = iddel;
	}

	public Integer getIddel() {
		return iddel;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setIwl(Integer iwl) {
		this.iwl = iwl;
	}

	public Integer getIwl() {
		return iwl;
	}

	public void setBupdatekh(String bupdatekh) {
		this.bupdatekh = bupdatekh;
	}

	public String getBupdatekh() {
		return bupdatekh;
	}

	public void setVcPhone(String vcPhone) {
		this.vcPhone = vcPhone;
	}

	public String getVcPhone() {
		return vcPhone;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}

	public String getStrType() {
		return strType;
	}

	public void setVcSpllName(String vcSpllName) {
		this.vcSpllName = vcSpllName;
	}

	public String getVcSpllName() {
		return vcSpllName;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Double getDlMoney() {
		return dlMoney;
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

	public void setIlogisticsname(String ilogisticsname) {
		this.ilogisticsname = ilogisticsname;
	}

	public String getIlogisticsname() {
		return ilogisticsname;
	}

	public void setIsettlementname(String isettlementname) {
		this.isettlementname = isettlementname;
	}

	public String getIsettlementname() {
		return isettlementname;
	}

	public void setIwlname(String iwlname) {
		this.iwlname = iwlname;
	}

	public String getIwlname() {
		return iwlname;
	}

}
