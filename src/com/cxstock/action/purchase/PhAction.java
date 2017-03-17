package com.cxstock.action.purchase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.purchase.PhBiz;
import com.cxstock.biz.purchase.dto.StorageDTO;
import com.cxstock.biz.purchase.dto.StorageDelDTO;
import com.cxstock.pojo.Tbcommodity;
import com.cxstock.pojo.Tbwarehouse;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.myclass.PublicClass;

public class PhAction extends BaseAction {

	private PhBiz phBiz;

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
	private Integer fidel;

	private String jsonInfo;
	private Integer iddel;

	private Integer spid;
	private String vcbach;
	private String vcsn;

	private Date dts;
	private Date dte;

	/* 查询条件 */
	private String key;

	private File upload;// 实际上传文件
	private String uploadContentType; // 文件的内容类型
	private String uploadFileName; // 上传文件名

	/* 商品 */
	private String vcName;
	private String vcFactoryNo;
	private String vcFactoryName;
	private String vcColor;
	private String vcGg;

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

			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			page.setField("a.*,b.name AS gysName,c.username");
			page
					.setTable("tbstorage a LEFT JOIN gys b ON a.gysId = b.gysid LEFT JOIN users c  ON a.userId = c.userid ");
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			phBiz.getStorage(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 获得销售明细
	 * */
	public void getInfoDel1() {
		try {
			// Page page = new Page();
			// page
			// .setWheres(" f.dlQty>0 and "
			// + PublicClass.getRightStr("g.companyid")
			// + " and g.fidel=0"); /* 未作废 */
			// if (key != null && key.length() > 0) {
			// page.setWheres(page.getWheres() + " and (a.vcBatch like '%"
			// + key + "%' or b.vcName like '%" + key
			// + "%' or b.vcNo like '%" + key + "%')");
			// }
			// page
			// .setField("a.*,b.vcName AS commodityName,b.vcNo AS commodityNo,b.vcGg AS commodityGg,e.lbname AS xlName,d.vcName AS warehouseName,f.dlQty");
			// page
			// .setTable("tbstoragedel a "
			// + "LEFT JOIN tbcommodity b ON a.commodityId = b.id "
			// + "LEFT JOIN tbwarehouse d ON a.warehouseId = d.id "
			// + "LEFT JOIN splb e ON b.TypeId=e.lbid "
			// +
			// "LEFT JOIN tbstock f ON a.commodityId=f.commodityId AND a.vcBatch=f.vcBatch AND a.vcSn=f.vcSn "
			// + "INNER JOIN tbstorage g ON a.storageId=g.id");
			// page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			// page.setStart(this.getStart());
			// page.setLimit(this.getLimit());
			// phBiz.getStorageDel(page);
			// this.outPageString(page);
			Page page = new Page();
			page.setStart(getStart());
			page.setLimit(getLimit());
			page
					.setField("a.*,b.dbSuggMoney,b.vcRemark as commodityRemark,b.vcFactoryNo,b.vcName AS commodityName,b.vcNo AS commodityNo,b.vcGg AS commodityGg,e.lbname AS xlName,d.vcName AS warehouseName,b.dbLastMoney AS dePurchaseMoney,0 AS deSumMoney");
			page
					.setTable("tbstock a LEFT JOIN tbcommodity b ON a.commodityId = b.id LEFT JOIN tbwarehouse d ON a.warehouseId = d.id LEFT JOIN splb e ON b.TypeId = e.lbid ");
			page.setWheres("a.dlQty>0 and "
					+ PublicClass.getRightStr("a.companyid") + "");
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and (b.vcName like '%"
						+ key + "%' or b.vcNo like '%" + key
						+ "%' or b.vcFactoryNo like '%" + key
						+ "%' or b.vcRemark like '%" + key + "%')");
			}
			phBiz.getStorageDel1(page);
			this.outHashMapPageString(page);
			// this.outPageString(page);
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
				page.setWheres(" a.storageId=" + id);
			page
					.setField("a.*,b.vcName AS commodityName,b.vcNo AS commodityNo,b.vcGg AS commodityGg,e.lbname AS xlName,d.vcName AS warehouseName");
			page
					.setTable("tbstoragedel a LEFT JOIN tbcommodity b ON a.commodityId = b.id LEFT JOIN tbwarehouse d ON a.warehouseId = d.id LEFT JOIN splb e ON b.TypeId=e.lbid");
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			page.setWheres(page.getWheres() + " ORDER BY a.id DESC");
			phBiz.getStorageDel(page);
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
			StorageDTO s = new StorageDTO();
			s.setCompanyId(udt.getCompanyid());
			s.setDeActualPayMoney(deActualPayMoney);
			s.setDeShouldPayMoney(deShouldPayMoney);
			// s.setDtBs(dtBs.getTime() + "");
			s.setDtBs(dtReceived.getTime() + ""); /* 收货日期就是业务日期 */
			s.setDtReceived(dtReceived.getTime() + "");
			s.setGysId(gysId);
			s.setId(id);
			// s.setIpayState(ipayState);
			s.setIpayState(0); /* 默认未结算 应付回写 */
			s.setUserId(udt.getUserid());
			s.setVcNo(vcNo);
			s.setVcRemark(vcRemark);
			s.setIcbill(0);
			s.setFidel(0); /* 未作废 */
			List list = new ArrayList(); /* 存放明细对象 */
			String str = jsonInfo;
			JSONArray jsonArray = JSONArray.fromObject(str);
			JSONObject jt;
			StorageDelDTO s1;
			for (int i = 0; i < jsonArray.size(); i++) {
				jt = jsonArray.getJSONObject(i);
				s1 = new StorageDelDTO();
				s1.setId(jt.getInt("id"));
				s1.setCommodityGg(jt.getString("commodityGg"));
				s1.setCommodityId(jt.getInt("commodityId"));
				s1.setCommodityName(jt.getString("commodityName"));
				s1.setDePurchaseMoney(jt.getDouble("dePurchaseMoney"));
				s1.setDeSumMoney(jt.getDouble("deSumMoney"));
				s1.setIcount(jt.getDouble("icount"));
				s1.setStorageId(jt.getInt("storageId"));
				s1.setVcBatch(jt.getString("vcBatch"));
				s1.setVcColor(jt.getString("vcColor"));
				s1.setVcDw(jt.getString("vcDw"));
				s1.setVcRemark(jt.getString("vcRemark"));
				s1.setVcSn(jt.getString("vcSn"));
				s1.setWarehouseId(jt.getInt("warehoseId"));
				s1.setXlName(jt.getString("xlName"));
				list.add(s1);
			}
			Integer newId = phBiz.saveInfo(list, s, udt.getUsername(), udt
					.getUserid());
			if (id == null) {
				this.outString("{success:true,message:'保存成功!',newid:" + newId
						+ "}");
			} else {
				this.outString("{success:true,message:'修改成功!',newid:" + newId
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
			phBiz.deleteInfo(iddel, true);
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
			phBiz.deleteZhuInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 获得购进和购退的记录
	 * */
	public void getStorageInfo() {
		try {
			Page page = new Page();
			page.setField("t.*");
			page
					.setTable("(SELECT '购进' AS vcType,a.id,a.vcNo,a.gysId,a.dtBs,a.deActualPayMoney AS dlMoney,a.companyId FROM tbstorage a where a.icbill=0 and a.fidel=0 "
							+ " UNION ALL"
							+ " SELECT '购退' AS vcType,b.id,b.vcNo,b.gysId,b.dtBs,b.deActualPayMoney AS dlMoney,b.companyId FROM tbreturn b where b.icbill=0 and b.fidel=0) t");
			page.setLimit(getLimit());
			page.setStart(getStart());
			page.setWheres(PublicClass.getRightStr("t.companyId"));
			if (key != null && key.length() > 0) {
				page.setWheres(page.getWheres() + " and t.vcNo like '%" + key
						+ "%'");
			}
			page.setWheres(page.getWheres() + " ORDER BY t.id DESC LIMIT 0,50");
			phBiz.getStorageInfo(page);
			this.outHashMapPageString(page);
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
			phBiz.saveApInfo(id, udt.getUsername(), udt.getUserid());
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}

	}

	/**
	 * 导入
	 * */
	public void saveImp() {
		try {
			InputStream input = new FileInputStream(upload);
			HSSFWorkbook wk = new HSSFWorkbook(input);
			HSSFSheet sheet = wk.getSheetAt(0);
			HSSFRow row;
			StorageDelDTO sroDel;
			String strNo;
			String strName;
			List list;
			List list1 = new ArrayList();
			Tbcommodity t;
			Tbwarehouse t1;
			String strErrMsg = "";
			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				if (row == null)
					continue;
				/* 查询商品Id */
				strNo = this.getValue(row.getCell((short) 0)); /* 编号 */
				strName = this.getValue(row.getCell((short) 1)); /* 名称 */
				if (strNo.length() == 0 || strName.length() == 0)
					continue;
				list = phBiz.getKey("Tbcommodity", "vcNo='" + strNo
						+ "' and vcName='" + strName + "'");
				if (list == null || list.size() == 0) {
					strErrMsg += "根据编号[" + strNo + "]和名称[" + strName
							+ "]在系统中查找不到商品信息,";
					continue;
				}
				t = (Tbcommodity) list.get(0); /* 商品记录 */
				/* 查找仓库 */
				list = phBiz.getKey("Tbwarehouse", "vcName='"
						+ this.getValue(row.getCell((short) 4)) + "'");
				if (list == null || list.size() == 0) {
					strErrMsg += "根据仓库["
							+ this.getValue(row.getCell((short) 4))
							+ "]在系统中查找不到仓库信息,";
					continue;
				}
				t1 = (Tbwarehouse) list.get(0); /* 仓库记录 */
				sroDel = new StorageDelDTO();
				sroDel.setCommodityGg(t.getVcGg()); /* 规格 */
				sroDel.setCommodityId(t.getId()); /* ID */
				sroDel.setCommodityName(t.getVcName()); /* 名称 */
				sroDel.setCommodityNo(t.getVcNo()); /* 编号 */
				sroDel.setDePurchaseMoney(Double.valueOf(this.getValue(row
						.getCell((short) 6)))); /* 采购价 */
				sroDel.setDeSumMoney(Double.valueOf(this.getValue(row
						.getCell((short) 7)))); /* 总金额 */
				sroDel.setDlQty(Double.valueOf(this.getValue(row
						.getCell((short) 5)))); /* 数量 */
				sroDel.setIcount(Double.valueOf(this.getValue(row
						.getCell((short) 5)))); /* 数量 */
				sroDel.setId(0); /* Id */
				sroDel.setStorageId(0); /* 主表ID */
				sroDel.setVcBatch(this.getValue(row.getCell((short) 2))); /* 批次 */
				sroDel.setVcColor(t.getVcColor()); /* 颜色 */
				sroDel.setVcDw(t.getVcDw()); /* 单位 */
				sroDel.setVcRemark(this.getValue(row.getCell((short) 8)));/* 备注 */
				sroDel.setVcSn(this.getValue(row.getCell((short) 3))); /* 辅助标识 */
				sroDel.setWarehouseId(t1.getId());
				sroDel.setWarehouseName(t1.getVcName());
				list1.add(sroDel);
			}
			/* 转换json */
			JSONArray jsonArray = new JSONArray();
			if (list1.size() > 0) {
				jsonArray = JSONArray.fromObject(list1);
			}
			strErrMsg = strErrMsg.length() == 0 ? "" : strErrMsg.substring(0,
					strErrMsg.length() - 1);
			this.outString("{success:true,msg:'\"err\":\"" + strErrMsg
					+ "\",\"data\":" + jsonArray + "'}");

		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 获取单元格的列植
	 * */
	@SuppressWarnings( { "deprecation", "static-access" })
	private String getValue(HSSFCell cell) {
		if (cell == null)
			return "";
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	/*************
	 * 购进商品是否重复 *
	 **************/
	public void getExtsAddSpxx() {
		try {
			String str = "ok";
			if (vcsn != null && vcsn.length() > 0) {
				str = phBiz.getExtsAddSpxx(spid, vcbach, vcsn);
			}
			this.outString("{success:true,message:'" + str + "'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：根据id查询商品
	 * @作者：RC
	 * @日期：2015-07-14
	 * */
	public void getSpxxInfo() {
		try {
			this.outHashMapString(phBiz.getspxx(spid));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：保存商品
	 * @作者：RC
	 * @日期：2015-07-14
	 * */
	public void saveSpxx() {
		try {
			Tbcommodity t = new Tbcommodity();
			t.setId(id);
			t.setVcNo(vcNo);
			t.setVcName(vcName);
			t.setVcFactoryName(vcFactoryName);
			t.setVcFactoryNo(vcFactoryNo);
			t.setVcColor(vcColor);
			t.setVcGg(vcGg);
			t.setVcRemark(vcRemark);
			phBiz.saveSpxx(t);
			this.outString("{success:true,message:'修改商品成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public PhBiz getPhBiz() {
		return phBiz;
	}

	public void setPhBiz(PhBiz phBiz) {
		this.phBiz = phBiz;
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

	public void setDtBs(Date dtBs) {
		this.dtBs = dtBs;
	}

	public Date getDtBs() {
		return dtBs;
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

	public void setIcbill(Integer icbill) {
		this.icbill = icbill;
	}

	public Integer getIcbill() {
		return icbill;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public File getUpload() {
		return upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setFidel(Integer fidel) {
		this.fidel = fidel;
	}

	public Integer getFidel() {
		return fidel;
	}

	public void setSpid(Integer spid) {
		this.spid = spid;
	}

	public Integer getSpid() {
		return spid;
	}

	public void setVcbach(String vcbach) {
		this.vcbach = vcbach;
	}

	public String getVcbach() {
		return vcbach;
	}

	public void setVcsn(String vcsn) {
		this.vcsn = vcsn;
	}

	public String getVcsn() {
		return vcsn;
	}

	public Date getDts() {
		return dts;
	}

	public void setDts(Date dts) {
		this.dts = dts;
	}

	public Date getDte() {
		return dte;
	}

	public void setDte(Date dte) {
		this.dte = dte;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public String getVcName() {
		return vcName;
	}

	public void setVcFactoryNo(String vcFactoryNo) {
		this.vcFactoryNo = vcFactoryNo;
	}

	public String getVcFactoryNo() {
		return vcFactoryNo;
	}

	public void setVcFactoryName(String vcFactoryName) {
		this.vcFactoryName = vcFactoryName;
	}

	public String getVcFactoryName() {
		return vcFactoryName;
	}

	public void setVcColor(String vcColor) {
		this.vcColor = vcColor;
	}

	public String getVcColor() {
		return vcColor;
	}

	public void setVcGg(String vcGg) {
		this.vcGg = vcGg;
	}

	public String getVcGg() {
		return vcGg;
	}

}
