package com.cxstock.action.financial;

import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.financial.ArBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.pojo.Tbar;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class ArAction extends BaseAction {

	private ArBiz arBiz;

	private Integer id;
	private String vcNo;
	private Date dtBs;
	private Integer khId;
	private Integer sourceId;
	private Integer sourceType;
	private Integer logisticsId;
	private Integer settlementId;
	private Double dlMoney;
	private Integer istate;
	private String vcAuditor;
	private Date dtJsDate;
	private String vcRemark;
	private Date dtWrite;
	private Integer userId;
	private Integer companyId;

	private String key;
	private Date dtSDate;
	private Date dtEDate;
	private Integer iwl;

	public void getInfo() {
		try {
			Page page = new Page();
			page
					.setField("a.*,b.khname AS khName,"
							+ " (CASE WHEN a.sourceType=0 THEN c.vcNo ELSE d.vcNo END) AS sourceNo,"
							+ " e.vcName AS logisticsName,"
							+ " f.vcName AS settlementName");
			page
					.setTable("tbar a LEFT JOIN kh b ON a.khId=b.khid"
							+ " LEFT JOIN tbsalenout c ON a.sourceId=c.id AND a.sourceType=0"
							+ " LEFT JOIN tbsalenback d ON a.sourceId=d.id AND a.sourceType=1"
							+ " LEFT JOIN tblogistics e ON a.logisticsId=e.id"
							+ " LEFT JOIN tbsettlement f ON a.settlementId=f.id");
			page.setLimit(this.getLimit());
			page.setStart(this.getStart());
			page.setWheres(PublicClass.getRightStr("a.companyId"));
			// page.setWheres(page.getWheres() + " and c.fidel=0"); /* 未作废 */
			if (dtSDate != null && dtEDate != null) {
				page.setWheres(page.getWheres() + " and a.dtBs between '"
						+ dtSDate.getTime() + "' and '"
						+ (dtEDate.getTime() + 23 * 59 * 59 * 1000) + "'");
			}
			if (key != null && key.length() > 0) { /* 搜索金额 */
				// page.setWheres(page.getWheres() + " and (a.vcNo like '%" +
				// key
				// + "%' or c.vcNo like '%" + key + "%' or d.vcNo)");

				page.setWheres(page.getWheres() + " and a.dlMoney=" + key);
			}
			if (logisticsId != null) { /* 配送方式 */
				page.setWheres(page.getWheres() + " and a.logisticsId="
						+ logisticsId);
			}
			if (settlementId != null) { /* 结算方式 */
				page.setWheres(page.getWheres() + " and a.settlementId="
						+ settlementId);
			}
			if (istate != null && istate != -1) { /* 结算状态 */
				page.setWheres(page.getWheres() + " and a.istate=" + istate);
			}
			if (khId != null) { /* 客户 */
				page.setWheres(page.getWheres() + " and a.khId=" + khId);
			}
			if (iwl != null) /* 物流 */{
				page.setWheres(page.getWheres() + " and c.iwl=" + iwl);
			}
			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			arBiz.getInfo(page);
			this.outHashMapPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

	}

	/**
	 * 保存记录
	 * */
	public void saveInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			Tbar t = new Tbar();
			t.setCompanyId(udt.getCompanyid());
			t.setDlMoney(dlMoney);
			t.setDtJsDate(dtJsDate == null ? null : dtJsDate.getTime() + "");
			t.setDtWrite((new Date()).getTime() + "");
			t.setId(id);
			t.setIstate(istate);
			t.setKhId(khId);
			t.setLogisticsId(logisticsId);
			t.setSettlementId(settlementId);
			t.setSourceId(sourceId);
			t.setSourceType(sourceType);
			t.setUserId(udt.getUserid());
			t.setVcAuditor(vcAuditor);
			t.setDtBs(dtBs.getTime() + "");
			t.setVcNo(vcNo);
			t.setVcRemark(vcRemark);
			t.setFidel(0);
			arBiz.saveInfo(t);
			if (id == null || id == 0) {
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
			arBiz.deleteInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public ArBiz getArBiz() {
		return arBiz;
	}

	public void setArBiz(ArBiz arBiz) {
		this.arBiz = arBiz;
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

	public Integer getKhId() {
		return khId;
	}

	public void setKhId(Integer khId) {
		this.khId = khId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public Integer getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}

	public Double getDlMoney() {
		return dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Integer getIstate() {
		return istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public String getVcAuditor() {
		return vcAuditor;
	}

	public void setVcAuditor(String vcAuditor) {
		this.vcAuditor = vcAuditor;
	}

	public Date getDtJsDate() {
		return dtJsDate;
	}

	public void setDtJsDate(Date dtJsDate) {
		this.dtJsDate = dtJsDate;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public Date getDtWrite() {
		return dtWrite;
	}

	public void setDtWrite(Date dtWrite) {
		this.dtWrite = dtWrite;
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

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setDtBs(Date dtBs) {
		this.dtBs = dtBs;
	}

	public Date getDtBs() {
		return dtBs;
	}

	public void setDtSDate(Date dtSDate) {
		this.dtSDate = dtSDate;
	}

	public Date getDtSDate() {
		return dtSDate;
	}

	public void setDtEDate(Date dtEDate) {
		this.dtEDate = dtEDate;
	}

	public Date getDtEDate() {
		return dtEDate;
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

}
