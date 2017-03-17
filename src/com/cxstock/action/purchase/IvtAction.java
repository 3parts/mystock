package com.cxstock.action.purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.purchase.IvtBiz;
import com.cxstock.biz.purchase.dto.InventoryDTO;
import com.cxstock.pojo.Tbinventorydel;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class IvtAction extends BaseAction {

	private IvtBiz ivtBiz;

	private Integer id;
	private String vcNo;
	private Date dtBs;
	private Integer warehouseId;
	private String vcRemark;
	private Integer userId;
	private Integer istate;
	private Integer checkUserId;
	private String dtCheck;
	private Integer companyId;

	private String jsonInfo;
	private String key;
	private Integer iddel;

	private Date dts;
	private Date dte;

	/**
	 * 获得明细信息
	 * */
	public void getInfoDel() {
		try {
			String sql = "SELECT a.*,b.vcName AS commodityName,b.vcNo AS commodityNo,b.vcGg AS commodityGg "
					+ " FROM tbinventorydel a LEFT JOIN tbcommodity b ON a.commodityId=b.id";
			if (id != null) {
				sql += " where a.inventoryId=" + id;
			}
			this.outListString(ivtBiz.getInfoDel(sql));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 保存信息
	 * */
	public void saveInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			InventoryDTO t = new InventoryDTO();
			t.setCompanyId(udt.getCompanyid());
			t.setDtBs(dtBs.getTime() + "");
			t.setUserId(udt.getUserid());
			t.setVcRemark(vcRemark);
			t.setWarehouseId(warehouseId);
			t.setId(id);
			List list = new ArrayList(); /* 存放明细对象 */
			String str = jsonInfo;
			JSONArray jsonArray = JSONArray.fromObject(str);
			JSONObject jt;
			Tbinventorydel t1;
			for (int i = 0; i < jsonArray.size(); i++) {
				t1 = new Tbinventorydel();
				jt = jsonArray.getJSONObject(i);
				t1.setCommodityId(jt.getInt("commodityId"));
				t1.setDlActual(jt.getDouble("dlActual"));
				t1.setDlBefore(jt.getDouble("dlBefore"));
				t1.setId(jt.getInt("id"));
				t1.setInventoryId(jt.getInt("inventoryId"));
				t1.setStockId(jt.getInt("stockId"));
				t1.setVcBatch(jt.getString("vcBatch"));
				t1.setVcSn(jt.getString("vcSn"));
				t1.setWarehouseId(jt.getInt("warehouseId"));
				list.add(t1);
			}
			ivtBiz.saveInfo(list, t);
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
	 * 分页获得记录
	 * */
	public void getInfo() {
		try {
			Page page = new Page();
			page
					.setField("a.*,b.vcName AS warehouseName,c.username AS userName,d.username AS checkUserName");
			page
					.setTable("tbinventory a LEFT JOIN tbwarehouse b ON a.warehouseId=b.id LEFT JOIN users c ON a.userId=c.userid LEFT JOIN users d ON a.checkUserId=d.userid");
			page.setWheres(PublicClass.getRightStr("a.companyId"));
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and (a.vcNo like '%" + key
						+ "%' or a.vcRemark like '%" + key + "%')");
			}
			if (warehouseId != null) {
				page.setWheres(page.getWheres() + " and a.warehouseId="
						+ warehouseId);
			}
			if (dts != null && dte != null) {
				page.setWheres(page.getWheres() + " and a.dtBs between '"
						+ dts.getTime() + "' and '"
						+ (dte.getTime() + 23 * 59 * 59 * 1000) + "'");
			}
			page.setWheres(page.getWheres() + " order by a.id desc");
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			ivtBiz.getInfo(page);
			this.outPageString(page);

		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 删除明细
	 * */
	public void deleteInfoDel() {
		try {
			ivtBiz.deleteInfoDel(iddel);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 删除主行
	 * */
	public void deleteInfo() {
		try {
			if (ivtBiz.deleteInfo(id)) {
				this.outString("{success:true}");
			} else {
				this.outString("{success:false}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 审核记录 加或减库存
	 * */
	public void saveCheckInfo() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			ivtBiz.saveCheckInfo(id, udt.getUserid());
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 盘点完成
	 * */
	public void saveOkInfo() {
		try {
			ivtBiz.saveOkInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public IvtBiz getIvtBiz() {
		return ivtBiz;
	}

	public void setIvtBiz(IvtBiz ivtBiz) {
		this.ivtBiz = ivtBiz;
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

	public Integer getIstate() {
		return istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public Integer getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(Integer checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getDtCheck() {
		return dtCheck;
	}

	public void setDtCheck(String dtCheck) {
		this.dtCheck = dtCheck;
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
