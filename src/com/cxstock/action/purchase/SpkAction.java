package com.cxstock.action.purchase;

import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.purchase.SpkBiz;
import com.cxstock.pojo.Tbspellpack;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class SpkAction extends BaseAction {

	private SpkBiz spkBiz;

	private Integer id;
	private Integer salenId;
	private Integer khId;
	private Date dtBs;
	private String vcSpllName;
	private Double dlMoney;
	private Integer ipayState;
	private String dtPay;
	private String vcPayMan;
	private String vcRemark;
	private Integer userId;
	private Integer companyId;

	private String key;
	private Date dtSDate;
	private Date dtEDate;

	private Integer iwl;
	private Integer ilogistics;

	private Date dts;
	private Date dte;

	private String ids;
	private Integer lis;

	/**
	 * 分页获取记录
	 * */
	public void getInfo() {
		try {
			Page page = new Page();
			page
					.setField("a.*,b.khname AS khName,c.vcNo AS salenNo,d.username AS userName");
			page
					.setTable("tbspellpack a LEFT JOIN kh b ON a.khId=b.khid LEFT JOIN tbsalenout c ON a.salenId=c.id LEFT JOIN users d ON a.userId=d.userid");
			page.setWheres(PublicClass.getRightStr("a.companyId"));
			if (key != null && key.length() > 0) {
				if (PublicClass.isNumeric(key)) {
					page.setWheres(page.getWheres()
							+ " and (a.vcSpllName like '%" + key
							+ "%' or b.khname like '%" + key
							+ "%' or a.dlMoney ='" + key + "')");
				} else {
					page.setWheres(page.getWheres()
							+ " and (a.vcSpllName like '%" + key
							+ "%' or b.khname like '%" + key + "%')");
				}

			}
			if (dts != null && dte != null) {
				page.setWheres(page.getWheres() + " and a.dtBs between '"
						+ dts.getTime() + "' and '"
						+ (dte.getTime() + 23 * 59 * 59 * 1000) + "'");
			}
			if (id != null && id != 0) {
				/** 销售查询记录 **/
				page.setWheres(page.getWheres() + " and c.id=" + id);
			}
			if (ipayState != null && ipayState != -1) {
				page.setWheres(page.getWheres() + " and a.ipayState="
						+ ipayState);
			}
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			spkBiz.getInfo(page);
			this.outHashMapPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

	}

	public void saveInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			Tbspellpack t = new Tbspellpack();
			t.setCompanyId(udt.getCompanyid());
			t.setDlMoney(dlMoney);
			t.setDtBs(dtBs.getTime() + "");
			t.setDtPay(dtPay);
			t.setId(id);
			t.setIpayState(ipayState);
			t.setKhId(khId);
			t.setSalenId(salenId);
			t.setUserId(udt.getUserid());
			t.setVcRemark(vcRemark);
			t.setVcSpllName(vcSpllName);
			spkBiz.saveInfo(t);
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
	 * 删除记录
	 * */
	public void deleteInfo() {
		try {
			spkBiz.deleteInfo(id);
			this.outString("{success:false}");
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
			spkBiz.saveStateInfo(id, udt.getUsername(), udt.getCompanyid());
			this.outString("{success:false}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 获得信封打印的记录
	 * */
	public void getprintInfo() {
		try {
			Page page = new Page();
			page
					.setField("a.id,(SELECT FROM_UNIXTIME(a.dtBs/1000,'%Y-%m-%d')) AS dtBs,a.vcNo,b.khname AS khName,a.deOutMoney,IFNULL(c.icount,0) AS icount ,IFNULL(c.dlMoney,0) AS dlSpellMoney,d.username AS userName,a.deOutMoney+IFNULL(c.dlMoney,0) AS dlSumMoney,e.vcName AS psName,f.vcName AS wlName");
			page
					.setTable("tbsalenout a LEFT JOIN kh b ON a.iKh=b.khid"
							+ " LEFT JOIN (SELECT SUM(1) AS icount,SUM(t.dlMoney) AS dlMoney,t.salenId FROM tbspellpack t GROUP BY t.salenId) c ON a.id=c.salenId"
							+ " LEFT JOIN users d ON a.userId=d.userid"
							+ " LEFT JOIN tblogistics e ON a.iLogistics=e.id "
							+ " LEFT JOIN tblogisticscompany f ON a.iwl=f.id");
			page.setWheres(PublicClass.getRightStr("a.companyId")
					+ " and a.fidel=0");
			if (dtSDate != null && dtEDate != null) {
				page.setWheres(page.getWheres() + " and a.dtBs between '"
						+ dtSDate.getTime() + "' and '" + dtEDate.getTime()
						+ "'");
			}
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and (b.khname like '%"
						+ key + "%' or d.username like '%" + key
						+ "%' or a.vcNo like '%" + key + "%')");
			}
			if (iwl != null) {
				page.setWheres(page.getWheres() + " and a.iwl=" + iwl);
			}
			if (ilogistics != null) {
				page.setWheres(page.getWheres() + " and a.iLogistics="
						+ ilogistics);
			}
			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			page.setLimit(getLimit());
			page.setStart(getStart());
			spkBiz.getprintInfo(page);
			this.outHashMapPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 获得 打印的数据
	 * */
	public void getXmlData() {
		try {
			String sql = "";
			String strids = ids.split("-")[0];
			String stris = ids.split("-")[1];
			if (!"1".equals(stris)) {
				sql = "SELECT a.id,"
						+ " (SELECT "
						+ " FROM_UNIXTIME(a.dtBs / 1000, '%Y-%m-%d')) AS dtBs,"
						+ " b.khname AS khName,"
						+ " b.lxtel,b.lxren,a.vcPhone,CONCAT(IFNULL(f.vcName,''),' ',IFNULL(e.vcName,'')) AS 配送方式,"
						+ " a.deOutMoney + IFNULL(c.dlMoney, 0) AS dlSumMoney,"
						+ " CONCAT(b.province,' ',b.city,' ',b.distincts,' ',b.shipping_address) AS vcAddress,e.vcName AS wlName"
						+ " FROM" + " tbsalenout a " + " LEFT JOIN kh b "
						+ " ON a.iKh = b.khid" + " LEFT JOIN " + " (SELECT "
						+ " SUM(1) AS icount," + " SUM(t.dlMoney) AS dlMoney,"
						+ " t.salenId " + " FROM" + " tbspellpack t"
						+ " GROUP BY t.salenId) c" + " ON a.id = c.salenId "
						+ " LEFT JOIN users d " + " ON a.userId = d.userid"
						+ " LEFT JOIN tblogisticscompany e ON a.iwl=e.id "
						+ " LEFT JOIN tblogistics f ON a.iLogistics=f.id";
				sql += " where a.id in (" + strids + ")";
			} else {
				sql = "SELECT a.id,"
						+ " (SELECT "
						+ " FROM_UNIXTIME(a.dtBs / 1000, '%Y-%m-%d')) AS dtBs,"
						+ " b.khname AS khName,"
						+ " b.lxtel,b.lxren,a.vcPhone,CONCAT(IFNULL(f.vcName,''),' ',IFNULL(e.vcName,'')) AS 配送方式,"
						+ " 0 AS dlSumMoney,"
						+ " CONCAT(b.province,' ',b.city,' ',b.distincts,' ',b.shipping_address) AS vcAddress,e.vcName AS wlName"
						+ " FROM" + " tbsalenout a " + " LEFT JOIN kh b "
						+ " ON a.iKh = b.khid" + " LEFT JOIN " + " (SELECT "
						+ " SUM(1) AS icount," + " SUM(t.dlMoney) AS dlMoney,"
						+ " t.salenId " + " FROM" + " tbspellpack t"
						+ " GROUP BY t.salenId) c" + " ON a.id = c.salenId "
						+ " LEFT JOIN users d " + " ON a.userId = d.userid"
						+ " LEFT JOIN tblogisticscompany e ON a.iwl=e.id "
						+ " LEFT JOIN tblogistics f ON a.iLogistics=f.id";
				sql += " where a.id in (" + strids + ")";
			}
			// GenXmlData.XML_GenDetailData(getResponse(), sql);
			String str = spkBiz.getPrintData(sql);
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

	public String getVcSpllName() {
		return vcSpllName;
	}

	public void setVcSpllName(String vcSpllName) {
		this.vcSpllName = vcSpllName;
	}

	public Double getDlMoney() {
		return dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Integer getIpayState() {
		return ipayState;
	}

	public void setIpayState(Integer ipayState) {
		this.ipayState = ipayState;
	}

	public String getDtPay() {
		return dtPay;
	}

	public void setDtPay(String dtPay) {
		this.dtPay = dtPay;
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

	public void setSpkBiz(SpkBiz spkBiz) {
		this.spkBiz = spkBiz;
	}

	public SpkBiz getSpkBiz() {
		return spkBiz;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public Date getDtSDate() {
		return dtSDate;
	}

	public void setDtSDate(Date dtSDate) {
		this.dtSDate = dtSDate;
	}

	public Date getDtEDate() {
		return dtEDate;
	}

	public void setDtEDate(Date dtEDate) {
		this.dtEDate = dtEDate;
	}

	public void setVcPayMan(String vcPayMan) {
		this.vcPayMan = vcPayMan;
	}

	public String getVcPayMan() {
		return vcPayMan;
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

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setIwl(Integer iwl) {
		this.iwl = iwl;
	}

	public Integer getIwl() {
		return iwl;
	}

	public void setIlogistics(Integer ilogistics) {
		this.ilogistics = ilogistics;
	}

	public Integer getIlogistics() {
		return ilogistics;
	}

	public void setLis(Integer lis) {
		this.lis = lis;
	}

	public Integer getLis() {
		return lis;
	}

}
