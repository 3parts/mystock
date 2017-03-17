package com.cxstock.action.financial;

import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.financial.ImeBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.pojo.Tbincome;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class ImeAction extends BaseAction {

	private ImeBiz imeBiz;

	private Integer id;
	private String vcNo;
	private Date dtBs;
	private Integer accountTypeId;
	private Double dlMoney;
	private Integer accountId;
	private String vcAuditor;
	private String vcRemark;
	private String dtWrite;
	private Integer companyId;
	private Integer sourceId;

	private Date dtSDate;
	private Date dtEDate;

	/**
	 * 分页获得记录
	 * */
	public void getInfo() {
		try {
			Page page = new Page();
			page
					.setField("a.*,b.vcName AS accountTypeName,c.vcName AS accountName");
			page
					.setTable("tbincome a LEFT JOIN tbaccounttype b ON a.accounttypeId=b.id LEFT JOIN tbaccount c ON a.accountId=c.id");
			page.setLimit(getLimit());
			page.setStart(getStart());
			page.setWheres(PublicClass.getRightStr("a.companyId"));
			if (dtSDate != null && dtEDate != null) {
				page.setWheres(page.getWheres() + " and a.dtBs between '"
						+ dtSDate.getTime() + "' and '"
						+ (dtEDate.getTime() * 23 * 59 * 59 * 1000) + "'");
			}
			if (accountTypeId != null) {
				page.setWheres(page.getWheres() + " and a.accountTypeId="
						+ accountTypeId);
			}
			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			imeBiz.getInfo(page);
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
			Tbincome t = new Tbincome();
			t.setAccountId(accountId);
			t.setAccountTypeId(accountTypeId);
			t.setCompanyId(udt.getCompanyid());
			t.setDlMoney(dlMoney);
			t.setDtBs(dtBs.getTime() + "");
			t.setDtWrite((new Date()).getTime() + "");
			t.setId(id);
			t.setVcAuditor(vcAuditor);
			t.setVcNo(vcNo);
			t.setVcRemark(vcRemark);
			t.setSourceId(sourceId);
			t.setFidel(0);
			imeBiz.saveInfo(t);
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
			imeBiz.deleteInfo(id);
			this.outString("{success:true'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public ImeBiz getImeBiz() {
		return imeBiz;
	}

	public void setImeBiz(ImeBiz imeBiz) {
		this.imeBiz = imeBiz;
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

	public Integer getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Integer accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public Double getDlMoney() {
		return dlMoney;
	}

	public void setDlMoney(Double dlMoney) {
		this.dlMoney = dlMoney;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getVcAuditor() {
		return vcAuditor;
	}

	public void setVcAuditor(String vcAuditor) {
		this.vcAuditor = vcAuditor;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

}
