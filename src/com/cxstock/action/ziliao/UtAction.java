package com.cxstock.action.ziliao;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.ziliao.UtBiz;
import com.cxstock.pojo.Tbaccount;
import com.cxstock.pojo.Tbaccounttype;
import com.cxstock.pojo.Tblogistics;
import com.cxstock.pojo.Tblogisticscompany;
import com.cxstock.pojo.Tbposition;
import com.cxstock.pojo.Tbsettlement;
import com.cxstock.pojo.Tbunit;
import com.cxstock.utils.system.Constants;

public class UtAction extends BaseAction {

	private UtBiz utBiz;

	private Integer id;

	public UtBiz getUtBiz() {
		return utBiz;
	}

	public void setUtBiz(UtBiz utBiz) {
		this.utBiz = utBiz;
	}

	private String vcNo;
	private String vcName;
	private String vcRemark;
	private Integer vctype; /* 物流公司用 */

	private Integer itype; /* 账户对象用 */

	private String type;

	/**
	 * 获得记录信息
	 * */
	public void getInfo() {
		try {
			if ("jldw".equals(type)) /* 计量单位 */{
				this.outListString(utBiz.getInfo("Tbunit"));
			} else if ("jsfs".equals(type))/* 结算方式 */{
				this.outListString(utBiz.getInfo("Tbsettlement"));
			} else if ("psfs".equals(type)) /* 配送方式 */{
				this.outListString(utBiz.getInfo("Tblogistics"));
			} else if ("wlgs".equals(type)) { /* 物流公司 */
				if (vctype == null || (vctype + "").length() <= 0) {
					this.outListString(utBiz.getInfo("Tblogisticscompany"));
				} else {
					this.outListString(utBiz.getInfo1("Tblogisticscompany",
							vctype));
				}
			} else if ("zmlx".equals(type)) { /* 账目类型 */
				if (itype == null || (itype + "").length() <= 0) {
					this.outListString(utBiz.getInfo("Tbaccounttype"));
				} else {
					this.outListString(utBiz.getInfo("Tbaccounttype", itype));
				}
			} else if ("zh".equals(type)) {/* 账户 */
				if (itype == null)
					this.outListString(utBiz.getInfo("Tbaccount"));
				else
					this.outListString(utBiz.getInfo("Tbaccount", itype));
			} else if ("zw".equals(type)) { /* 职位 */
				this.outListString(utBiz.getInfo("Tbposition"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 保存或修改
	 * */
	public void saveOrUpdate() {
		UserDTO udt = (UserDTO) this.getSession().getAttribute(
				Constants.USERINFO);
		if ("jldw".equals(type)) {
			Tbunit u = new Tbunit();
			u.setId(id);
			u.setVcName(vcName);
			u.setVcNo(vcNo);
			u.setVcRemark(vcRemark);
			u.setCompanyId(udt.getCompanyid());
			utBiz.saveOrUpdate(type, u);
		} else if ("jsfs".equals(type)) {
			Tbsettlement u = new Tbsettlement();
			u.setId(id);
			u.setVcName(vcName);
			u.setVcNo(vcNo);
			u.setVcRemark(vcRemark);
			u.setCompanyId(udt.getCompanyid());
			utBiz.saveOrUpdate(type, u);
		} else if ("psfs".equals(type)) {
			Tblogistics u = new Tblogistics();
			u.setId(id);
			u.setVcName(vcName);
			u.setVcNo(vcNo);
			u.setVcRemark(vcRemark);
			u.setCompanyId(udt.getCompanyid());
			utBiz.saveOrUpdate(type, u);
		} else if ("wlgs".equals(type)) {
			Tblogisticscompany u = new Tblogisticscompany();
			u.setId(id);
			u.setVcName(vcName);
			u.setVcNo(vcNo);
			u.setVcRemark(vcRemark);
			u.setCompanyId(udt.getCompanyid());
			u.setVctype(vctype);
			utBiz.saveOrUpdate(type, u);
		} else if ("zmlx".equals(type)) {
			Tbaccounttype u = new Tbaccounttype();
			u.setId(id);
			u.setVcName(vcName);
			u.setVcNo(vcNo);
			u.setVcRemark(vcRemark);
			u.setItype(itype);
			u.setCompanyId(udt.getCompanyid());
			utBiz.saveOrUpdate(type, u);
		} else if ("zh".equals(type)) {
			Tbaccount u = new Tbaccount();
			u.setId(id);
			u.setVcName(vcName);
			u.setVcNo(vcNo);
			u.setVcRemark(vcRemark);
			// u.setItype(itype); /*暂时清除 账户类型 每个账户都有支出和收入*/
			u.setCompanyId(udt.getCompanyid());
			utBiz.saveOrUpdate(type, u);
		} else if ("zw".equals(type)) {
			Tbposition u = new Tbposition();
			u.setId(id);
			u.setVcName(vcName);
			u.setVcNo(vcNo);
			u.setVcRemark(vcRemark);
			u.setCompanyId(udt.getCompanyid());
			utBiz.saveOrUpdate(type, u);
		}

		if (id == null) {
			this.outString("{success:true,message:'保存成功!'}");
		} else {
			this.outString("{success:true,message:'修改成功!'}");
		}
	}

	/**
	 * 删除记录
	 * */
	public void delete() {
		try {
			utBiz.deleteInfo(type, id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public void aa() {
		String str = "["
				+ "{'id':150100,'leaf':false,expanded:false,'text':'呼和浩特市国土资源局','children':["
				+ "{'id':150101,'leaf':true,'children':null,'text':'市辖区国土资源局'},"
				+ "{'id':150102,'leaf':true,'children':null,'text':'新城区国土资源局'},"
				+ "{'id':150103,'leaf':true,'children':null,'text':'回民区国土资源局'},"
				+ "{'id':150104,'leaf':true,'children':null,'text':'玉泉区国土资源局'},"
				+ "{'id':150105,'leaf':true,'children':null,'text':'赛罕区国土资源局'},"
				+ "{'id':150121,'leaf':true,'children':null,'text':'土默特左旗国土资源局'},"
				+ "{'id':150122,'leaf':true,'children':null,'text':'托克托县国土资源局'},"
				+ "{'id':150123,'leaf':true,'children':null,'text':'和林格尔县国土资源局'},"
				+ "{'id':150124,'leaf':true,'children':null,'text':'清水河县国土资源局'},"
				+ "{'id':150125,'leaf':true,'children':null,'text':'武川县国土资源局'}"
				+ "]},"
				+ "{'id':150200,'leaf':false,expanded:false,'text':'包头市国土资源局','children':["
				+ "{'id':150201,'leaf':true,'children':null,'text':'市辖区国土资源局'},"
				+ "{'id':150202,'leaf':true,'children':null,'text':'东河区国土资源局'},"
				+ "{'id':150203,'leaf':true,'children':null,'text':'昆都仑区国土资源局'},"
				+ "{'id':150204,'leaf':true,'children':null,'text':'青山区国土资源局'},"
				+ "{'id':150205,'leaf':true,'children':null,'text':'石拐区国土资源局'},"
				+ "{'id':150206,'leaf':true,'children':null,'text':'白云矿区国土资源局'},"
				+ "{'id':150207,'leaf':true,'children':null,'text':'九原区国土资源局'},"
				+ "{'id':150221,'leaf':true,'children':null,'text':'土默特右旗国土资源局'},"
				+ "{'id':150222,'leaf':true,'children':null,'text':'固阳县国土资源局'},"
				+ "{'id':150223,'leaf':true,'children':null,'text':'达尔罕茂明安联合旗国土资源局'}"
				+ "]},"
				+ "{'id':150300,'leaf':false,expanded:false,'text':'乌海市国土资源局','children':["
				+ "{'id':150301,'leaf':true,'children':null,'text':'市辖区国土资源局'},"
				+ "{'id':150302,'leaf':true,'children':null,'text':'海勃湾区国土资源局'},"
				+ "{'id':150303,'leaf':true,'children':null,'text':'海南区国土资源局'},"
				+ "{'id':150304,'leaf':true,'children':null,'text':'乌达区国土资源局'}"
				+ "]},"
				+ "{'id':150400,'leaf':false,expanded:false,'text':'赤峰市国土资源局','children':["
				+ "{'id':150401,'leaf':true,'children':null,'text':'市辖区国土资源局'},"
				+ "{'id':150402,'leaf':true,'children':null,'text':'红山区国土资源局'},"
				+ "{'id':150403,'leaf':true,'children':null,'text':'元宝山区国土资源局'},"
				+ "{'id':150404,'leaf':true,'children':null,'text':'松山区国土资源局'},"
				+ "{'id':150421,'leaf':true,'children':null,'text':'阿鲁科尔沁旗国土资源局'},"
				+ "{'id':150422,'leaf':true,'children':null,'text':'巴林左旗国土资源局'},"
				+ "{'id':150423,'leaf':true,'children':null,'text':'巴林右旗国土资源局'},"
				+ "{'id':150424,'leaf':true,'children':null,'text':'林西县国土资源局'},"
				+ "{'id':150425,'leaf':true,'children':null,'text':'克什克腾旗国土资源局'},"
				+ "{'id':150426,'leaf':true,'children':null,'text':'翁牛特旗国土资源局'},"
				+ "{'id':150428,'leaf':true,'children':null,'text':'喀喇沁旗国土资源局'},"
				+ "{'id':150429,'leaf':true,'children':null,'text':'宁城县国土资源局'},"
				+ "{'id':150430,'leaf':true,'children':null,'text':'敖汉旗国土资源局'}"
				+ "]},"
				+ "{'id':150500,'leaf':false,expanded:false,'text':'通辽市国土资源局','children':["
				+ "{'id':150501,'leaf':true,'children':null,'text':'市辖区国土资源局'},"
				+ "{'id':150502,'leaf':true,'children':null,'text':'科尔沁区国土资源局'},"
				+ "{'id':150521,'leaf':true,'children':null,'text':'科尔沁左翼中旗国土资源局'},"
				+ "{'id':150522,'leaf':true,'children':null,'text':'科尔沁左翼后旗国土资源局'},"
				+ "{'id':150523,'leaf':true,'children':null,'text':'开鲁县国土资源局'},"
				+ "{'id':150524,'leaf':true,'children':null,'text':'库伦旗国土资源局'},"
				+ "{'id':150525,'leaf':true,'children':null,'text':'奈曼旗国土资源局'},"
				+ "{'id':150526,'leaf':true,'children':null,'text':'扎鲁特旗国土资源局'},"
				+ "{'id':150581,'leaf':true,'children':null,'text':'霍林郭勒市国土资源局'}"
				+ "]},"
				+ "{'id':150600,'leaf':false,expanded:false,'text':'鄂尔多斯市国土资源局','children':["
				+ "{'id':150602,'leaf':true,'children':null,'text':'东胜区国土资源局'},"
				+ "{'id':150621,'leaf':true,'children':null,'text':'达拉特旗国土资源局'},"
				+ "{'id':150622,'leaf':true,'children':null,'text':'准格尔旗国土资源局'},"
				+ "{'id':150623,'leaf':true,'children':null,'text':'鄂托克前旗国土资源局'},"
				+ "{'id':150624,'leaf':true,'children':null,'text':'鄂托克旗国土资源局'},"
				+ "{'id':150625,'leaf':true,'children':null,'text':'杭锦旗国土资源局'},"
				+ "{'id':150626,'leaf':true,'children':null,'text':'乌审旗国土资源局'},"
				+ "{'id':150627,'leaf':true,'children':null,'text':'伊金霍洛旗国土资源局'}"
				+ "]},"
				+ "{'id':150700,'leaf':false,expanded:false,'text':'呼伦贝尔市国土资源局','children':["
				+ "{'id':150701,'leaf':true,'children':null,'text':'市辖区国土资源局'},"
				+ "{'id':150702,'leaf':true,'children':null,'text':'海拉尔区国土资源局'},"
				+ "{'id':150721,'leaf':true,'children':null,'text':'阿荣旗国土资源局'},"
				+ "{'id':150722,'leaf':true,'children':null,'text':'莫力达瓦达斡尔族自治旗国土资源局'},"
				+ "{'id':150723,'leaf':true,'children':null,'text':'鄂伦春自治旗国土资源局'},"
				+ "{'id':150724,'leaf':true,'children':null,'text':'鄂温克族自治旗国土资源局'},"
				+ "{'id':150725,'leaf':true,'children':null,'text':'陈巴尔虎旗国土资源局'},"
				+ "{'id':150726,'leaf':true,'children':null,'text':'新巴尔虎左旗国土资源局'},"
				+ "{'id':150727,'leaf':true,'children':null,'text':'新巴尔虎右旗国土资源局'},"
				+ "{'id':150781,'leaf':true,'children':null,'text':'满洲里市国土资源局'},"
				+ "{'id':150782,'leaf':true,'children':null,'text':'牙克石市国土资源局'},"
				+ "{'id':150783,'leaf':true,'children':null,'text':'扎兰屯市国土资源局'},"
				+ "{'id':150784,'leaf':true,'children':null,'text':'额尔古纳市国土资源局'},"
				+ "{'id':150785,'leaf':true,'children':null,'text':'根河市国土资源局'}"
				+ "]},"
				+ "{'id':150800,'leaf':false,expanded:false,'text':'巴彦淖尔市国土资源局','children':["
				+ "{'id':150801,'leaf':true,'children':null,'text':'市辖区国土资源局'},"
				+ "{'id':150802,'leaf':true,'children':null,'text':'临河区国土资源局'},"
				+ "{'id':150821,'leaf':true,'children':null,'text':'五原县国土资源局'},"
				+ "{'id':150822,'leaf':true,'children':null,'text':'磴口县国土资源局'},"
				+ "{'id':150823,'leaf':true,'children':null,'text':'乌拉特前旗国土资源局'},"
				+ "{'id':150824,'leaf':true,'children':null,'text':'乌拉特中旗国土资源局'},"
				+ "{'id':150825,'leaf':true,'children':null,'text':'乌拉特后旗国土资源局'},"
				+ "{'id':150826,'leaf':true,'children':null,'text':'杭锦后旗国土资源局'}"
				+ "]},"
				+ "{'id':150900,'leaf':false,expanded:false,'text':'乌兰察布市国土资源局','children':["
				+ "{'id':150901,'leaf':true,'children':null,'text':'市辖区国土资源局'},"
				+ "{'id':150902,'leaf':true,'children':null,'text':'集宁区国土资源局'},"
				+ "{'id':150921,'leaf':true,'children':null,'text':'卓资县国土资源局'},"
				+ "{'id':150922,'leaf':true,'children':null,'text':'化德县国土资源局'},"
				+ "{'id':150923,'leaf':true,'children':null,'text':'商都县国土资源局'},"
				+ "{'id':150924,'leaf':true,'children':null,'text':'兴和县国土资源局'},"
				+ "{'id':150925,'leaf':true,'children':null,'text':'凉城县国土资源局'},"
				+ "{'id':150926,'leaf':true,'children':null,'text':'察哈尔右翼前旗国土资源局'},"
				+ "{'id':150927,'leaf':true,'children':null,'text':'察哈尔右翼中旗国土资源局'},"
				+ "{'id':150928,'leaf':true,'children':null,'text':'察哈尔右翼后旗国土资源局'},"
				+ "{'id':150929,'leaf':true,'children':null,'text':'四子王旗国土资源局'},"
				+ "{'id':150981,'leaf':true,'children':null,'text':'丰镇市国土资源局'}"
				+ "]},"
				+ "{'id':152200,'leaf':false,expanded:false,'text':'兴安盟国土资源局','children':["
				+ "{'id':152201,'leaf':true,'children':null,'text':'乌兰浩特市国土资源局'},"
				+ "{'id':152202,'leaf':true,'children':null,'text':'阿尔山市国土资源局'},"
				+ "{'id':152221,'leaf':true,'children':null,'text':'科尔沁右翼前旗国土资源局'},"
				+ "{'id':152222,'leaf':true,'children':null,'text':'科尔沁右翼中旗国土资源局'},"
				+ "{'id':152223,'leaf':true,'children':null,'text':'扎赉特旗国土资源局'},"
				+ "{'id':152224,'leaf':true,'children':null,'text':'突泉县国土资源局'}"
				+ "]},"
				+ "{'id':152500,'leaf':false,expanded:false,'text':'锡林郭勒盟国土资源局','children':["
				+ "{'id':152501,'leaf':true,'children':null,'text':'二连浩特市国土资源局'},"
				+ "{'id':152502,'leaf':true,'children':null,'text':'锡林浩特市国土资源局'},"
				+ "{'id':152522,'leaf':true,'children':null,'text':'阿巴嘎旗国土资源局'},"
				+ "{'id':152523,'leaf':true,'children':null,'text':'苏尼特左旗国土资源局'},"
				+ "{'id':152524,'leaf':true,'children':null,'text':'苏尼特右旗国土资源局'},"
				+ "{'id':152525,'leaf':true,'children':null,'text':'东乌珠穆沁旗国土资源局'},"
				+ "{'id':152526,'leaf':true,'children':null,'text':'西乌珠穆沁旗国土资源局'},"
				+ "{'id':152527,'leaf':true,'children':null,'text':'太仆寺旗国土资源局'},"
				+ "{'id':152528,'leaf':true,'children':null,'text':'镶黄旗国土资源局'},"
				+ "{'id':152529,'leaf':true,'children':null,'text':'正镶白旗国土资源局'},"
				+ "{'id':152530,'leaf':true,'children':null,'text':'正蓝旗国土资源局'},"
				+ "{'id':152531,'leaf':true,'children':null,'text':'多伦县国土资源局'}"
				+ "]},"
				+ "{'id':152900,'leaf':false,expanded:false,'text':'阿拉善盟国土资源局','children':["
				+ "{'id':152921,'leaf':true,'children':null,'text':'阿拉善左旗国土资源局'},"
				+ "{'id':152922,'leaf':true,'children':null,'text':'阿拉善右旗国土资源局'},"
				+ "{'id':152923,'leaf':true,'children':null,'text':'额济纳旗国土资源局'}"
				+ "]}" + "]";
		this.outString(str);
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

	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setItype(Integer itype) {
		this.itype = itype;
	}

	public Integer getItype() {
		return itype;
	}

	public void setVctype(Integer vctype) {
		this.vctype = vctype;
	}

	public Integer getVctype() {
		return vctype;
	}

}
