package com.cxstock.action.purchase;

import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.purchase.OtherBiz;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.PublicClass;

/**
 * @功能：云通货的其他操作
 * @作者：RC
 * @日期：2015-07-17
 * */
public class OtherAction extends BaseAction {
	private OtherBiz otherBiz;

	private String vctype;
	private Integer gysId;
	private Integer khId;
	private String key;

	private Date dts;
	private Date dte;

	private String xml;

	public void setOtherBiz(OtherBiz otherBiz) {
		this.otherBiz = otherBiz;
	}

	public OtherBiz getOtherBiz() {
		return otherBiz;
	}

	/**
	 * @功能：进出商品查询
	 * @作者：RC
	 * @日期：2015-07-17
	 * */
	public void getJcNoInfo() {
		try {
			String strTable = "SELECT b.dtBs,'购进' AS typeNo,b.userId,b.iPayState,b.gysId AS billId,b.vcNo,a.commodityId,a.iCount,a.vcDw,a.dePurchaseMoney,0 AS zkMoney,"
					+ " a.deSumMoney,0 AS psInfo,0 AS wlInfo,0 AS jsInfo,a.vcBatch,a.vcSn,a.warehouseId,b.companyId,b.vcRemark"
					+ " FROM tbstoragedel a INNER JOIN tbstorage b ON a.storageId=b.id AND b.fidel=0"
					+ " UNION ALL"
					+ " SELECT b.dtBs,'购退' AS typeNo,b.userId,b.iPayState,b.gysId AS billId,b.vcNo,a.commodityId,a.iCount,a.vcDw,a.dePurchaseMoney,0 AS zkMoney,"
					+ " a.deSumMoney,0 AS psInfo,0 AS wlInfo,0 AS jsInfo,a.vcBatch,a.vcSn,a.warehouseId,b.companyId,b.vcRemark"
					+ " FROM tbreturndel a INNER JOIN tbreturn b ON a.returnId=b.id  AND b.fidel=0"
					+ " UNION ALL"
					+ " SELECT b.dtBs,'销售' AS typeNo,b.userId,b.iPay as iPayState,b.iKh AS billId,b.vcNo,a.commodityId,a.iCount,a.vcDw,a.dePriceMoney AS dePurchaseMoney,a.deDiscount AS zkMoney,"
					+ " a.deSumMoney,b.iLogistics AS psInfo,b.iwl AS wlInfo,b.iSettlement AS jsInfo,a.vcBatch,a.vcSn,a.warehouseId,b.companyId,b.vcRemark"
					+ " FROM tbsalenoutdel a INNER JOIN tbsalenout b ON a.salenoutId=b.id  AND b.fidel=0"
					+ " UNION ALL"
					+ " SELECT b.dtBs,'销退' AS typeNo,b.userId,b.ipayType as iPayState,b.khId AS billId,b.vcNo,a.commodityId,a.iCount,a.vcDw,a.dePriceMoney AS dePurchaseMoney,a.deDiscount AS zkMoney,"
					+ " a.deSumMoney,0 AS psInfo,0 AS wlInfo,0 AS jsInfo,a.vcBatch,a.vcSn,a.warehouseId,b.companyId,b.vcRemark"
					+ " FROM tbsalenbackdel a INNER JOIN tbsalenback b ON a.salenbackId=b.id  AND b.fidel=0";
			String sql = "select t.*,h.username,c.vcNo as spNo,c.vcName AS spName,(CASE WHEN t.typeNo='销售' OR t.typeNo='销退' THEN a.khname WHEN t.typeNo='购进' OR t.typeNo='购退' THEN b.name END) AS billName,"
					+ " d.vcName AS warehouseName,"
					+ " e.vcName AS PsName,"
					+ " f.vcName AS wlName,g.vcName AS jsName from ("
					+ strTable
					+ ") t LEFT JOIN kh a ON t.billId=a.khid AND (t.typeNo='销售' OR t.typeNo='销退')"
					+ " LEFT JOIN gys b ON t.billId =b.gysid AND (t.typeNo='购进' OR t.typeNo='购退')"
					+ " LEFT JOIN tbcommodity c ON t.commodityId = c.id"
					+ " LEFT JOIN tbwarehouse d ON t.warehouseId = d.id"
					+ " LEFT JOIN tblogistics e ON t.psInfo = e.id"
					+ " LEFT JOIN tblogisticscompany f ON t.wlInfo=f.id"
					+ " LEFT JOIN tbsettlement g ON t.jsInfo=g.id "
					+ " LEFT JOIN users h on t.userId = h.userid";
			Page page = new Page();
			page.setStart(getStart());
			page.setLimit(getLimit());
			page.setField("t.*");
			page.setTable("(" + sql + ") t");
			page.setWheres(PublicClass.getRightStr("t.companyId"));
			if (dts != null && dte != null) {
				page.setWheres(page.getWheres() + " and t.dtBs between '"
						+ dts.getTime() + "' and '"
						+ (dte.getTime() + 23 * 59 * 59 * 1000) + "'");
			}
			if (vctype != null && (vctype.length() > 0) && !"全部".equals(vctype)) {
				page.setWheres(page.getWheres() + " and t.typeNo='" + vctype
						+ "'");
			}
			if (gysId != null) {
				page.setWheres(page.getWheres()
						+ " and (t.typeNo in ('购进','购退') and t.billId=" + gysId
						+ ")");
			}
			if (khId != null) {
				page.setWheres(page.getWheres()
						+ " and (t.typeNo in ('销售','销退') and t.billId=" + khId
						+ ")");
			}
			if (key != null && (key.length() > 0)) {
				page.setWheres(page.getWheres() + " and (t.spName like '%"
						+ key + "%' or t.spNo like '%" + key
						+ "%' or t.username like '%" + key + "%')");
			}
			page.setWheres(page.getWheres() + " order by t.dtBs desc");
			otherBiz.getJcNoInfo(page);
			this.outHashMapPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

	}

	/**
	 * @功能：今日报表
	 * @作者：RC
	 * @日期：2015-07-17
	 * */
	public void getToDayInfo() {
		try {
			Page page = new Page();
			page.setStart(getStart());
			page.setLimit(getLimit());
			page
					.setField("t.vctype,SUM(t.qty) AS qty,SUM(money) AS money,SUM(IFNULL(t.cbmoney,0)) AS cbmoney");
			page
					.setTable("( SELECT a.dtReceived AS dtBs,'购进' AS vctype,a.vcNo,b.qty,a.deShouldPayMoney AS money,a.companyId,b.cbmoney FROM tbstorage a INNER JOIN (SELECT SUM(b.iCount) AS qty,SUM(c.dbAverageMoney*b.iCount) AS cbmoney,b.storageId FROM tbstoragedel b LEFT JOIN tbcommodity c ON b.commodityId=c.id GROUP BY b.storageId) b ON a.id = b.b.storageId AND a.fidel=0 "
							+ " UNION ALL "
							+ " SELECT a.dtBs,'购退' AS vctype,a.vcNo,b.qty,a.deShouldPayMoney AS money,a.companyId,b.cbmoney FROM tbreturn a INNER JOIN (SELECT SUM(b.iCount) AS qty,SUM(c.dbAverageMoney*b.iCount) AS cbmoney,b.returnId FROM tbreturndel b LEFT JOIN tbcommodity c ON b.commodityId=c.id GROUP BY b.returnId) b ON a.id=b.returnId AND a.fidel=0 "
							+ " UNION ALL "
							+ " SELECT a.dtBs,'销售' AS vctype,a.vcNo,b.qty,a.deOutMoney AS money,a.companyId,b.cbmoney FROM tbsalenout a INNER JOIN (SELECT SUM(b.iCount) AS qty,SUM(c.dbAverageMoney*b.iCount) AS cbmoney,b.salenoutId FROM tbsalenoutdel b LEFT JOIN tbcommodity c ON b.commodityId=c.id GROUP BY b.salenoutId) b ON a.id=b.salenoutId AND a.fidel=0 "
							+ " UNION ALL "
							+ " SELECT a.dtBs,'销退' AS vctype,a.vcNo,b.qty,a.deMoney AS money,a.companyId,b.cbmoney FROM tbsalenback a INNER JOIN (SELECT SUM(b.iCount) AS qty,SUM(c.dbAverageMoney*b.iCount) AS cbmoney,b.salenbackId FROM tbsalenbackdel b LEFT JOIN tbcommodity c ON b.commodityId=c.id GROUP BY b.salenbackId) b ON a.id=b.salenbackId AND a.fidel=0 ) t");
			page.setWheres(PublicClass.getRightStr("t.companyId"));
			if (vctype != null && !"全部".equals(vctype)) {
				page.setWheres(page.getWheres() + " and t.vctype='" + vctype
						+ "'");
			}
			if (dts != null && dte != null) {
				page.setWheres(page.getWheres() + " and t.dtBs between '"
						+ dts.getTime() + "' and '"
						+ (dte.getTime() + 23 * 59 * 59 * 1000) + "'");
			}
			page.setWheres(page.getWheres() + " GROUP BY t.vctype");
			otherBiz.getToDayInfo(page);
			this.outHashMapPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：获得库存数量和总值
	 * @作者：RC
	 * @日期：2015-09-07
	 * */
	public void getStockMoney() {
		try {
			this.outHashMapString(otherBiz.getStockMoney());
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

	}

	public void setVctype(String vctype) {
		this.vctype = vctype;
	}

	public String getVctype() {
		return vctype;
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

	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}

	public Integer getGysId() {
		return gysId;
	}

	public void setKhId(Integer khId) {
		this.khId = khId;
	}

	public Integer getKhId() {
		return khId;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getXml() {
		return xml;
	}

}
