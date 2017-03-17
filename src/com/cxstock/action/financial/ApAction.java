package com.cxstock.action.financial;

import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.financial.ApBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.pojo.Tbap;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class ApAction extends BaseAction {

	private ApBiz apBiz;

	private Integer id;
	private String vcNo;
	private Date dtBs;
	private Integer gysId;
	private Integer sourceId;
	private Integer sourceType;
	private Integer logisticsId;
	private Integer settlementId;
	private Double dlMoney;
	private Integer istate;
	private String vcAuditor;
	private Date dtJsDate;
	private String vcRemark;
	private String dtWrite;
	private Integer userId;
	private Integer companyId;

	private String key;
	private Date dtSDate;
	private Date dtEDate;

	public void getInfo() {
		try {
			Page page = new Page();
			page
					.setField("a.*,b.name AS gysName,"
							+ " (CASE WHEN a.sourceType=0 THEN c.vcNo ELSE d.vcNo END) AS sourceNo,"
							+ " e.vcName AS logisticsName,"
							+ " f.vcName AS settlementName");
			page
					.setTable("tbap a LEFT JOIN gys b ON a.gysId=b.gysid"
							+ " LEFT JOIN tbstorage c ON a.sourceId=c.id AND a.sourceType=0"
							+ " LEFT JOIN tbreturn d ON a.sourceId=d.id AND a.sourceType=1"
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
			if (key != null && key.length() > 0) {
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
			if (gysId != null) { /* 供应商 */
				page.setWheres(page.getWheres() + " and a.gysId=" + gysId);
			}
			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			apBiz.getInfo(page);
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
			Tbap t = new Tbap();
			t.setCompanyId(udt.getCompanyid());
			t.setDlMoney(dlMoney);
			t.setDtJsDate(dtJsDate == null ? null : dtJsDate.getTime() + "");
			t.setDtWrite((new Date()).getTime() + "");
			t.setId(id);
			t.setIstate(istate);
			t.setGysId(gysId);
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
			apBiz.saveInfo(t);
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
			apBiz.deleteInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public ApBiz getApBiz() {
		return apBiz;
	}

	public void setApBiz(ApBiz apBiz) {
		this.apBiz = apBiz;
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

	public Integer getGysId() {
		return gysId;
	}

	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
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

	public String getDtWrite() {
		return dtWrite;
	}

	public void setDtWrite(String dtWrite) {
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

}
