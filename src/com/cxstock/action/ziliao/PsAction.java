package com.cxstock.action.ziliao;

import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.ziliao.PsBiz;
import com.cxstock.pojo.Tbperson;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class PsAction extends BaseAction {

	private PsBiz psBiz;

	private Integer id;

	public void setPsBiz(PsBiz psBiz) {
		this.psBiz = psBiz;
	}

	private String vcNo;
	private String vcName;
	private Integer igender;
	private String vcNation;
	private String vcIdCard;
	private String vcAddress;
	private Integer positionId;
	private Integer icomminSsion;
	private String vcTel;
	private Integer istate;
	private Date dtEntry;
	private Date dtQuit;
	private String vcQuitReason;
	private String vcRemark;

	private String key;

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

	public Integer getIgender() {
		return igender;
	}

	public void setIgender(Integer igender) {
		this.igender = igender;
	}

	public String getVcNation() {
		return vcNation;
	}

	public void setVcNation(String vcNation) {
		this.vcNation = vcNation;
	}

	public String getVcIdCard() {
		return vcIdCard;
	}

	public void setVcIdCard(String vcIdCard) {
		this.vcIdCard = vcIdCard;
	}

	public String getVcAddress() {
		return vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getIcomminSsion() {
		return icomminSsion;
	}

	public void setIcomminSsion(Integer icomminSsion) {
		this.icomminSsion = icomminSsion;
	}

	public String getVcTel() {
		return vcTel;
	}

	public void setVcTel(String vcTel) {
		this.vcTel = vcTel;
	}

	public Integer getIstate() {
		return istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
	}

	public Date getDtQuit() {
		return dtQuit;
	}

	public void setDtQuit(Date dtQuit) {
		this.dtQuit = dtQuit;
	}

	public String getVcQuitReason() {
		return vcQuitReason;
	}

	public void setVcQuitReason(String vcQuitReason) {
		this.vcQuitReason = vcQuitReason;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public PsBiz getPsBiz() {
		return psBiz;
	}

	/**
	 * 获得分页数据
	 * */
	public void getPageInfo() {
		try {
			Page page = new Page();
			page.setWheres(PublicClass.getRightStr("a.companyId"));
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and (a.vcNo like '%" + key
						+ "%' or a.vcName like '%" + key + "%')");
			}
			page.setStart(getStart());
			page.setLimit(getLimit());
			psBiz.getInfo(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 保存或更新数据
	 * */
	public void saveOrUpdate() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			Tbperson t = new Tbperson();
			t.setCompanyId(udt.getCompanyid());
			t.setDtEntry(dtEntry.getTime() + "");
			t.setDtQuit(dtQuit == null ? null : dtQuit.getTime() + "");
			t.setIcomminSsion(icomminSsion);
			t.setId(id);
			t.setIgender(igender);
			t.setIstate(istate);
			t.setPositionId(positionId);
			t.setVcAddress(vcAddress);
			t.setVcIdCard(vcIdCard);
			t.setVcName(vcName);
			t.setVcNation(vcNation);
			t.setVcTel(vcTel);
			t.setVcRemark(vcRemark);
			t.setVcQuitReason(vcQuitReason);
			t.setVcNo(vcNo);
			psBiz.saveOrUpdate(t);
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
			psBiz.delete(id);
			this.outString("{success:true");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 得到下拉数据
	 * */
	public void findPsComb() {
		try {
			this.outListString(psBiz.findPsComb());
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
