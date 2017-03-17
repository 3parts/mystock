package com.cxstock.action.ziliao;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.ziliao.QyBiz;
import com.cxstock.biz.ziliao.dto.QyDTO;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;

@SuppressWarnings("serial")
public class QyAction extends BaseAction {

	private QyBiz qyBiz;

	private Integer id;
	private String vcNumber;
	private String vcName;
	private Date dtBegeindate;
	private Date dtEnddate;
	private String VState;
	private String vcCity;
	private String vcAddress;
	private String vcContact;
	private String vcTel;
	private String vcMobile;
	private String vcFax;
	private String vcQq;
	private String vcEmail;
	private String vcWeixin;
	private String vcAccountnum;
	private String vcAccountname;
	private String vcBank;
	private String vcLogo;
	private String vcRemark;
	private String vcWeb;
	private String printInfo1;
	private String printInfo2;
	private String printInfo3;
	private String fwx;

	private String key;
	private String file;
	private String treekey;
	private String userid;

	private File upload;// 实际上传文件
	private String uploadContentType; // 文件的内容类型
	private String uploadFileName; // 上传文件名

	/* 当前用户是能操作界面 */
	private String openurl;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/* 分页获取数据 */
	public void findPageInfo() {
		try {
			List list = qyBiz.findPageInfo(key, this.getStart(), this
					.getLimit());
			Page page = new Page();
			page.setLimit(this.getLimit());
			page.setRoot(list);
			page.setStart(this.getStart());
			int total = qyBiz.countAll();
			page.setTotal(total);
			this.outPageString(page);

		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 保存数据
	 **/
	public void saveQydata() {
		try {
			QyDTO qydto = new QyDTO(id, vcNumber, vcName, dtBegeindate,
					dtEnddate, VState, vcCity, vcAddress, vcContact, vcTel,
					vcMobile, vcFax, vcQq, vcEmail, vcWeixin, vcAccountnum,
					vcAccountname, vcBank, vcLogo, vcRemark, vcWeb, printInfo1,
					printInfo2, printInfo3, fwx);
			qyBiz.saveOrUpdateQy(qydto);
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
	 * 删除数据
	 * */
	public void deleteQyInfo() {
		try {
			qyBiz.deleteQyInfo(id);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 上传文件
	 * */
	public void upLoadImg() {
		UserDTO udt = (UserDTO) this.getSession().getAttribute(
				Constants.USERINFO);
		HttpServletRequest request = ServletActionContext.getRequest();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort();

		String strPath = ServletActionContext.getServletContext().getRealPath(
				"/upload");
		/* 生成文件名 */
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String strFielName = df.format(new Date()) + "_" + udt.getUserid();

		/* 得到文件类型 */
		String str = uploadContentType.split("/")[1];
		File target = new File(strPath, strFielName + "." + str);
		try {
			FileUtils.copyFile(upload, target);
			/* 删除以前的文件 */
			if (vcLogo != null && vcLogo.length() > 0) {
				String[] strFileName = vcLogo.split("upload");
				File f = new File(strPath + strFileName[1]);
				if (f.exists())
					f.delete();
			}
			this.outString("{success:true,msg:'../../upload/" + strFielName
					+ "." + str + "'}");
		} catch (IOException e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 加载企业数据
	 * */
	public void loadInfo() {
		try {
			List list = qyBiz.loadQyInfo();
			this.outListString(list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 加载树形企业
	 * */
	public void LoadTreeInfo() {
		try {
			if (treekey != null && !treekey.isEmpty()) {
				treekey = new String(treekey.getBytes("ISO-8859-1"), "UTF-8"); /* 转码 */
			}
		} catch (UnsupportedEncodingException e) {
		}
		this.outTreeJsonList(qyBiz.findTreeInfo(treekey));
	}

	/**
	 * 加载树形企业
	 * */
	public void LoadTreeInfo1() {
		/* 加载树形企业 */
		this.outTreeJsonList(qyBiz.findTreeInfo1(userid));
	}

	/* 当前用户是否能打开 url */
	public void getOpenUrl() {
		try {
			UserDTO udt = (UserDTO) this.getSession().getAttribute(
					Constants.USERINFO);
			if (openurl.indexOf(".html") > -1) {
				this.outString("ok");
				return;
			}
			this.outString(qyBiz.getOpenUrl(openurl, udt.getRoleid()));
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public void setQyBiz(QyBiz qyBiz) {
		this.qyBiz = qyBiz;
	}

	public QyBiz getQyBiz() {
		return qyBiz;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcNumber() {
		return vcNumber;
	}

	public void setVcNumber(String vcNumber) {
		this.vcNumber = vcNumber;
	}

	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public Date getDtBegeindate() {
		return dtBegeindate;
	}

	public void setDtBegeindate(Date dtBegeindate) {
		this.dtBegeindate = dtBegeindate;
	}

	public Date getDtEnddate() {
		return dtEnddate;
	}

	public void setDtEnddate(Date dtEnddate) {
		this.dtEnddate = dtEnddate;
	}

	public String getVState() {
		return VState;
	}

	public void setVState(String vState) {
		VState = vState;
	}

	public String getVcCity() {
		return vcCity;
	}

	public void setVcCity(String vcCity) {
		this.vcCity = vcCity;
	}

	public String getVcAddress() {
		return vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	public String getVcContact() {
		return vcContact;
	}

	public void setVcContact(String vcContact) {
		this.vcContact = vcContact;
	}

	public String getVcTel() {
		return vcTel;
	}

	public void setVcTel(String vcTel) {
		this.vcTel = vcTel;
	}

	public String getVcMobile() {
		return vcMobile;
	}

	public void setVcMobile(String vcMobile) {
		this.vcMobile = vcMobile;
	}

	public String getVcFax() {
		return vcFax;
	}

	public void setVcFax(String vcFax) {
		this.vcFax = vcFax;
	}

	public String getVcQq() {
		return vcQq;
	}

	public void setVcQq(String vcQq) {
		this.vcQq = vcQq;
	}

	public String getVcEmail() {
		return vcEmail;
	}

	public void setVcEmail(String vcEmail) {
		this.vcEmail = vcEmail;
	}

	public String getVcWeixin() {
		return vcWeixin;
	}

	public void setVcWeixin(String vcWeixin) {
		this.vcWeixin = vcWeixin;
	}

	public String getVcAccountnum() {
		return vcAccountnum;
	}

	public void setVcAccountnum(String vcAccountnum) {
		this.vcAccountnum = vcAccountnum;
	}

	public String getVcAccountname() {
		return vcAccountname;
	}

	public void setVcAccountname(String vcAccountname) {
		this.vcAccountname = vcAccountname;
	}

	public String getVcBank() {
		return vcBank;
	}

	public void setVcBank(String vcBank) {
		this.vcBank = vcBank;
	}

	public String getVcLogo() {
		return vcLogo;
	}

	public void setVcLogo(String vcLogo) {
		this.vcLogo = vcLogo;
	}

	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFile() {
		return file;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setVcWeb(String vcWeb) {
		this.vcWeb = vcWeb;
	}

	public String getVcWeb() {
		return vcWeb;
	}

	public void setTreekey(String treekey) {
		this.treekey = treekey;
	}

	public String getTreekey() {
		return treekey;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setPrintInfo1(String printInfo1) {
		this.printInfo1 = printInfo1;
	}

	public String getPrintInfo1() {
		return printInfo1;
	}

	public void setPrintInfo2(String printInfo2) {
		this.printInfo2 = printInfo2;
	}

	public String getPrintInfo2() {
		return printInfo2;
	}

	public void setPrintInfo3(String printInfo3) {
		this.printInfo3 = printInfo3;
	}

	public String getPrintInfo3() {
		return printInfo3;
	}

	public void setFwx(String fwx) {
		this.fwx = fwx;
	}

	public String getFwx() {
		return fwx;
	}

	public void setOpenurl(String openurl) {
		this.openurl = openurl;
	}

	public String getOpenurl() {
		return openurl;
	}

}
