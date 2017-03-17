package com.cxstock.action.purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.purchase.AltBiz;
import com.cxstock.pojo.Tballocation;
import com.cxstock.pojo.Tballocationdel;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class AltAction extends BaseAction {

	private AltBiz altBiz;

	private Integer id;
	private String vcNo;
	private Integer outId;
	private Integer inId;
	private Date dtBs;
	private Double dlCount;
	private Double dlMoney;
	private Integer istate;
	private Integer userId;
	private String vcRemark;
	private Integer companyId;

	private String key;
	private String jsonInfo;
	private Integer idDel;
	private Integer warehouseId;
	private String type;

	private Date dts;
	private Date dte;

	/**
	 * 获得主行记录
	 * */
	public void getInfo() {
		try {
			Page page = new Page();
			page
					.setField("a.*,b.vcName AS outName,c.vcName AS inName,d.username AS userName");
			page
					.setTable("tballocation a LEFT JOIN tbwarehouse b ON a.outId=b.id LEFT JOIN tbwarehouse c ON a.inId=c.id LEFT JOIN users d ON a.userId=d.userid");
			page.setWheres(PublicClass.getRightStr("a.companyId"));
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and (a.vcNo like '%" + key
						+ "%' or b.vcName like '%" + key
						+ "%' or c.vcName like '%" + key
						+ "%' or d.username like '%" + key + "%' )");
			}
			if (dts != null && dte != null) {
				page.setWheres(page.getWheres() + " and a.dtBs between '"
						+ dts.getTime() + "' and '"
						+ (dte.getTime() + 23 * 59 * 59 * 1000) + "'");
			}
			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			altBiz.getInfo(page);
			this.outPageString(page);

		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

	}

	/**
	 * 根据sql得到明细记录
	 * */
	public void getInfoDel() {
		try {
			String sql = "SELECT a.*, b.vcName AS commodityName, b.vcNo AS commodityNo,b.vcGg AS commodityGg, c.vcName AS outName, d.vcName AS inName "
					+ " FROM tballocationdel a LEFT JOIN tbcommodity b ON a.commodityId=b.id "
					+ " LEFT JOIN tbwarehouse c ON a.outId=c.id LEFT JOIN tbwarehouse d ON a.inId=d.id "
					+ " INNER JOIN tballocation e ON a.allocationId=e.id";
			sql += " WHERE a.allocationId=" + (id == null ? 0 : id);
			this.outListString(altBiz.getInfoDel(sql));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 查询库存明细记录
	 * */
	public void getStockInfoDel() {
		try {
			String sql = "SELECT a.*,b.vcName AS commodityName,b.vcNo AS commodityNo,b.vcGg AS commodityGg,c.vcName AS warehouseName "
					+ " from tbstock a LEFT JOIN tbcommodity b ON a.commodityId=b.id "
					+ " LEFT JOIN tbwarehouse c ON a.warehouseId=c.id";
			sql += " where " + PublicClass.getRightStr("a.companyId");
			if (key != null && key.length() > 0) {
				sql += " and (b.vcName like '%" + key + "%' or b.vcNo like '%"
						+ key + "%' or b.vcFactoryNo like '%" + key
						+ "%' or b.vcRemark like '%" + key + "%')";
			}
			if (warehouseId != null) {
				sql += " and a.warehouseId=" + warehouseId;
			}
			// if (!"pd".equals(type)) { /* 如果是盘点的话 就不要库存大于0 */
			// sql += " and a.dlQty >0";
			// }
			sql += " and a.dlQty >0"; /* 不管是否盘点 都需大于0 */
			sql += " ORDER BY a.id DESC LIMIT 0,50";
			this.outListString(altBiz.getStockInfoDel(sql));
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
			Tballocation t = new Tballocation();
			t.setCompanyId(udt.getCompanyid());
			t.setDlCount(dlCount);
			t.setDlMoney(dlMoney);
			t.setDtBs(dtBs.getTime() + "");
			t.setId(id);
			t.setInId(inId);
			t.setIstate(istate);
			t.setOutId(outId);
			t.setUserId(udt.getUserid());
			t.setVcNo(vcNo);
			t.setVcRemark(vcRemark);

			List list = new ArrayList(); /* 存放明细对象 */
			String str = jsonInfo;
			JSONArray jsonArray = JSONArray.fromObject(str);
			JSONObject jt;
			Tballocationdel t1;
			for (int i = 0; i < jsonArray.size(); i++) {
				jt = jsonArray.getJSONObject(i);
				t1 = new Tballocationdel();
				t1.setAllocationId(jt.getInt("allocationId"));
				t1.setCommodityId(jt.getInt("commodityId"));
				t1.setDlCount(jt.getDouble("dlCount"));
				t1.setDlMoney(jt.getDouble("dlMoney"));
				t1.setId(jt.getInt("id"));
				t1.setOutId(jt.getInt("outId"));
				t1.setInId(jt.getInt("inId"));
				t1.setVcBatch(jt.getString("vcBatch"));
				t1.setVcDw(jt.getString("vcDw"));
				t1.setVcRemark(jt.getString("vcRemark"));
				t1.setVcSn(jt.getString("vcSn"));
				t1.setComstockId(jt.getInt("comstockId"));
				t1.setGostockId(jt.getInt("gostockId"));
				list.add(t1);
			}
			if (altBiz.saveInfo(list, t)) {
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
	 * 删除明细
	 * */
	public void deleteInfoDel() {
		try {
			altBiz.deleteInfoDel(idDel);
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
			altBiz.deleteInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public AltBiz getAltBiz() {
		return altBiz;
	}

	public void setAltBiz(AltBiz altBiz) {
		this.altBiz = altBiz;
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

	public Integer getOutId() {
		return outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public Integer getInId() {
		return inId;
	}

	public void setInId(Integer inId) {
		this.inId = inId;
	}

	public Date getDtBs() {
		return dtBs;
	}

	public void setDtBs(Date dtBs) {
		this.dtBs = dtBs;
	}

	public Double getDlCount() {
		return dlCount;
	}

	public void setDlCount(Double dlCount) {
		this.dlCount = dlCount;
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

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String getJsonInfo() {
		return jsonInfo;
	}

	public void setJsonInfo(String jsonInfo) {
		this.jsonInfo = jsonInfo;
	}

	public void setIdDel(Integer idDel) {
		this.idDel = idDel;
	}

	public Integer getIdDel() {
		return idDel;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
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
