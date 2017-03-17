package com.cxstock.action.purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.purchase.ReturnBiz;
import com.cxstock.biz.purchase.dto.ReturnDTO;
import com.cxstock.biz.purchase.dto.ReturnDelDto;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class ReturnAction extends BaseAction {

	private ReturnBiz returnBiz;

	private Integer id;
	private String vcNo;
	private Integer gysId;
	private Date dtBs;
	private Date dtReceived;
	private Double deShouldPayMoney;
	private Double deActualPayMoney;
	private Integer ipayState;
	private Integer userId;
	private String vcRemark;
	private Integer companyId;
	private Integer icbill;

	private String jsonInfo;
	private Integer iddel;

	private Integer spId;
	private String batch;
	private String sn;
	private Integer houseid;
	private Double count;

	private Date dts;
	private Date dte;

	/* 查询条件 */
	private String key;

	/**
	 * 获得购进的信息
	 * */
	public void getInfo() {
		try {
			Page page = new Page();
			page.setWheres(PublicClass.getRightStr("a.companyId"));
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and (a.vcNo like '%" + key
						+ "%' or a.vcRemark like '%" + key
						+ "%' or b.name like '%" + key + "%') ");
			}
			if (gysId != null && gysId != 0) {
				page.setWheres(page.getWheres() + " and a.gysId=" + gysId);
			}
			if (ipayState != null && ipayState != -1) {
				page.setWheres(page.getWheres() + " and a.iPayState="
						+ ipayState);
			}
			if (dts != null && dte != null) {
				page.setWheres(page.getWheres() + " and a.dtBs between '"
						+ dts.getTime() + "' and '"
						+ (dte.getTime() + 23 * 59 * 59 * 1000) + "'");
			}
			page.setField("a.*,b.name AS gysName,c.username");
			page
					.setTable("tbreturn a LEFT JOIN gys b ON a.gysId = b.gysid LEFT JOIN users c  ON a.userId = c.userid ");
			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			returnBiz.getStorage(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 获得明细
	 * */
	public void getInfoDel() {
		try {
			Page page = new Page();
			if (id != null)
				page.setWheres(" a.returnId=" + id);
			page
					.setField("a.*,b.vcName AS commodityName,b.vcNo AS commodityNo,b.vcGg AS commodityGg,e.lbname AS xlName,d.vcName AS warehouseName");
			page
					.setTable("tbreturndel a LEFT JOIN tbcommodity b ON a.commodityId = b.id LEFT JOIN tbwarehouse d ON a.warehouseId = d.id LEFT JOIN splb e ON b.TypeId=e.lbid");
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			returnBiz.getStorageDel(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 保存记录
	 * */
	@SuppressWarnings("unchecked")
	public void savePhInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			ReturnDTO s = new ReturnDTO();
			s.setCompanyId(udt.getCompanyid());
			s.setDeActualPayMoney(deActualPayMoney);
			s.setDeShouldPayMoney(deShouldPayMoney);
			s.setDtBs(dtBs.getTime() + "");
			// s.setDtReceived(dtReceived.getTime() + "");
			s.setDtReceived(dtBs.getTime() + ""); /* 业务日期就是收货日期 */
			s.setGysId(gysId);
			s.setId(id);
			// s.setIpayState(ipayState);
			s.setIpayState(0); /* 默认未结算 应收回写 */
			s.setUserId(udt.getUserid());
			s.setVcNo(vcNo);
			s.setVcRemark(vcRemark);
			s.setIcbill(0);
			s.setFidel(0); /* 未作废 */
			List list = new ArrayList(); /* 存放明细对象 */
			String str = jsonInfo;
			JSONArray jsonArray = JSONArray.fromObject(str);
			JSONObject jt;
			ReturnDelDto s1;
			for (int i = 0; i < jsonArray.size(); i++) {
				jt = jsonArray.getJSONObject(i);
				s1 = new ReturnDelDto();
				s1.setId(jt.getInt("id"));
				s1.setCommodityGg(jt.getString("commodityGg"));
				s1.setCommodityId(jt.getInt("commodityId"));
				s1.setCommodityName(jt.getString("commodityName"));
				s1.setDePurchaseMoney(jt.getDouble("dePurchaseMoney"));
				s1.setDeSumMoney(jt.getDouble("deSumMoney"));
				s1.setIcount(jt.getDouble("icount"));
				s1.setReturnId(jt.getInt("returnId"));
				s1.setVcBatch(jt.getString("vcBatch"));
				s1.setVcColor(jt.getString("vcColor"));
				s1.setVcDw(jt.getString("vcDw"));
				s1.setVcRemark(jt.getString("vcRemark"));
				s1.setVcSn(jt.getString("vcSn"));
				s1.setWarehouseId(jt.getInt("warehoseId"));
				s1.setXlName(jt.getString("xlName"));
				list.add(s1);
			}
			Integer newid = returnBiz.saveInfo(list, s, udt.getUsername(), udt
					.getUserid());
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
			returnBiz.deleteInfo(iddel, true);
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
			returnBiz.deleteZhuInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 生成应付单据
	 * */
	public void saveApInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			returnBiz.saveApInfo(id, udt.getUsername(), udt.getUserid());
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

	}

	/**
	 * 功能：检测填写的购退记录是否合法 作者：RC 创建日期：2015-05-27
	 * */
	public void getLetMate() {
		try {
			if (returnBiz.getLetMate(spId, batch, sn, houseid, count)) {
				this.outString("{success:true,msg:'ok'}");
			} else {
				this.outString("{success:true,msg:'no'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：打印
	 * @作者：RC
	 * @日期：2015-08-12
	 * */
	public void getPrintXmlData() {
		try {
			if (id == null)
				return;
			String sql = "SELECT a.vcNo AS 单号,b.name AS 供应商,b.lxren AS 联系人,b.lxtel AS 联系电话,FROM_UNIXTIME( a.dtBs/1000,'%Y-%m-%d') AS 日期,"
					+ " a.vcRemark AS 备注,b.address AS 地址,d.VC_ADDRESS AS 企业地址,d.VC_QQ AS 企业QQ,d.fwx AS 企业微信,d.VC_TEL AS 企业电话,d.VC_FAX AS 企业传真,"
					+ " d.printInfo1 AS 附加资料1,d.printInfo2 AS 附加资料2,d.printInfo3 AS 附加资料3,"
					+ " e.vcName AS 商品名称,f.vcName AS 仓库,e.vcColor AS 颜色,e.vcGg AS 规格,c.iCount AS 数量,c.vcDw AS 单位,c.depurchaseMoney AS 单价,"
					+ " c.deSumMoney AS 金额,c.vcBatch AS 购进批次 ,"
					+ " (SELECT SUM(t.dlQty) AS dlqty FROM tbstock t WHERE t.vcSn=c.vcSn AND t.vcBatch=c.vcBatch AND t.commodityId=c.commodityId) AS 剩余库存 "
					+ " FROM tbreturn a LEFT JOIN gys b ON a.gysId=b.gysid INNER JOIN tbreturndel c ON a.id=c.returnid "
					+ " LEFT JOIN tbcompany d ON a.companyId=d.ID LEFT JOIN tbcommodity e ON c.commodityId=e.id LEFT JOIN tbwarehouse f ON c.warehouseId=f.id ";
			sql += "  WHERE a.id=" + id;
			sql = "select t.*,CONCAT(t.剩余库存-t.数量,'+',t.数量,'=',t.剩余库存) AS 说明  from ("
					+ sql + ") t";
			String str = returnBiz.getXmlPrint(sql);
			this.outString(str);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
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

	public Integer getGysId() {
		return gysId;
	}

	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}

	public Date getDtBs() {
		return dtBs;
	}

	public void setDtBs(Date dtBs) {
		this.dtBs = dtBs;
	}

	public Date getDtReceived() {
		return dtReceived;
	}

	public void setDtReceived(Date dtReceived) {
		this.dtReceived = dtReceived;
	}

	public Double getDeShouldPayMoney() {
		return deShouldPayMoney;
	}

	public void setDeShouldPayMoney(Double deShouldPayMoney) {
		this.deShouldPayMoney = deShouldPayMoney;
	}

	public Double getDeActualPayMoney() {
		return deActualPayMoney;
	}

	public void setDeActualPayMoney(Double deActualPayMoney) {
		this.deActualPayMoney = deActualPayMoney;
	}

	public Integer getIpayState() {
		return ipayState;
	}

	public void setIpayState(Integer ipayState) {
		this.ipayState = ipayState;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getJsonInfo() {
		return jsonInfo;
	}

	public void setJsonInfo(String jsonInfo) {
		this.jsonInfo = jsonInfo;
	}

	public Integer getIddel() {
		return iddel;
	}

	public void setIddel(Integer iddel) {
		this.iddel = iddel;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setReturnBiz(ReturnBiz returnBiz) {
		this.returnBiz = returnBiz;
	}

	public ReturnBiz getReturnBiz() {
		return returnBiz;
	}

	public void setIcbill(Integer icbill) {
		this.icbill = icbill;
	}

	public Integer getIcbill() {
		return icbill;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getHouseid() {
		return houseid;
	}

	public void setHouseid(Integer houseid) {
		this.houseid = houseid;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
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
