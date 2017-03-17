package com.cxstock.action.outer;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.outer.OuterBiz;
import com.cxstock.biz.outer.dto.TBdUserinfo;
import com.cxstock.utils.pubutil.Page;

public class OuterAction extends BaseAction {

	private OuterBiz outerBiz;

	private Integer fid;
	private String fname;
	private Date fopendate;
	private Date ftodate;
	private String fcity;
	private String faddress;
	private String fman;
	private String ftel;
	private String fqq;
	private String femail;
	private String fwx;
	private String fbankname;
	private String fbanknum;
	private String fbank;
	private String flogo;
	private String fweb;
	private String fremark;
	private Timestamp fwrite;
	private String ftable;

	private String ps;
	private String ps1;

	/**
	 * @功能：保存信息
	 * @作者：RC
	 * @日期：2015-07-25
	 * */
	public void saveInfo() {
		try {
			TBdUserinfo t = new TBdUserinfo();
			t.setFaddress(faddress);
			t.setFbank(fbank);
			t.setFbankname(fbankname);
			t.setFbanknum(fbanknum);
			t.setFcity(fcity);
			t.setFemail(femail);
			t.setFid(fid);
			t.setFlogo(flogo);
			t.setFman(fman);
			t.setFname(fname);
			t.setFopendate(fopendate);
			t.setFwx(fwx);
			t.setFwrite(fwrite);
			t.setFweb(fweb);
			t.setFtodate(ftodate);
			t.setFtel(ftel);
			t.setFremark(fremark);
			t.setFqq(fqq);
			String str = outerBiz.saveInfo(t);
			this.outString("{success:true,message:'" + str + "'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：获得企业申请记录
	 * @作者：RC
	 * @日期：2015-07-25
	 * */
	public void getInfo() {
		try {
			Page page = new Page();
			page.setLimit(getLimit());
			page.setStart(getStart());
			page.setField("*");
			page.setTable("t_bd_userinfo order by fid desc");
			outerBiz.getInfo(page);
			this.outHashMapPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：修改密码
	 * @作者：RC
	 * @日期：2015-07-25
	 * */
	public void savePsd() {
		try {
			String str = outerBiz.savePsd(ps, ps1);
			this.outString("{success:true,message:'" + str + "'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：审核不通过
	 * @作者：RC
	 * @日期：2015-07-25
	 * */
	public void saveNoCheck() {
		try {
			String str = outerBiz.saveNoCheck(fid);
			this.outString("{success:true,message:'" + str + "'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：审核通过
	 * @作者：RC
	 * @日期：2015-07-25
	 * */
	public void saveCheck() {
		try {
			String strPath = ServletActionContext.getServletContext()
					.getRealPath("/WEB-INF");

			String str = outerBiz.saveCheck(fid, ftable, strPath);
			this.outString("{success:true,message:'" + str + "'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：禁用信息
	 * @作者：RC
	 * @创建日期：2015-08-04
	 * */
	public void saveDisable() {
		try {
			String strPath = ServletActionContext.getServletContext()
					.getRealPath("/WEB-INF");
			outerBiz.saveDisable(fid, strPath);
			this.outString("{success:true");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：禁用信息
	 * @作者：RC
	 * @创建日期：2015-08-04
	 * */
	public void saveEnabled() {
		try {
			String strPath = ServletActionContext.getServletContext()
					.getRealPath("/WEB-INF");
			outerBiz.saveEnabled(fid, strPath);
			this.outString("{success:true");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：删除信息
	 * @作者：RC
	 * @创建日期：2015-08-04
	 * */
	public void saveDelete() {
		try {
			String strPath = ServletActionContext.getServletContext()
					.getRealPath("/WEB-INF");
			outerBiz.saveDelete(fid, strPath);
			this.outString("{success:true");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * @功能：为企业延期
	 * @作者：RC
	 * @日期：2015-08-08
	 * */
	public void saveDate() {
		try {
			outerBiz.saveDate(fid, ftodate);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public OuterBiz getOuterBiz() {
		return outerBiz;
	}

	public void setOuterBiz(OuterBiz outerBiz) {
		this.outerBiz = outerBiz;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Date getFopendate() {
		return fopendate;
	}

	public void setFopendate(Date fopendate) {
		this.fopendate = fopendate;
	}

	public Date getFtodate() {
		return ftodate;
	}

	public void setFtodate(Date ftodate) {
		this.ftodate = ftodate;
	}

	public String getFcity() {
		return fcity;
	}

	public void setFcity(String fcity) {
		this.fcity = fcity;
	}

	public String getFaddress() {
		return faddress;
	}

	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}

	public String getFman() {
		return fman;
	}

	public void setFman(String fman) {
		this.fman = fman;
	}

	public String getFtel() {
		return ftel;
	}

	public void setFtel(String ftel) {
		this.ftel = ftel;
	}

	public String getFqq() {
		return fqq;
	}

	public void setFqq(String fqq) {
		this.fqq = fqq;
	}

	public String getFemail() {
		return femail;
	}

	public void setFemail(String femail) {
		this.femail = femail;
	}

	public String getFwx() {
		return fwx;
	}

	public void setFwx(String fwx) {
		this.fwx = fwx;
	}

	public String getFbankname() {
		return fbankname;
	}

	public void setFbankname(String fbankname) {
		this.fbankname = fbankname;
	}

	public String getFbanknum() {
		return fbanknum;
	}

	public void setFbanknum(String fbanknum) {
		this.fbanknum = fbanknum;
	}

	public String getFbank() {
		return fbank;
	}

	public void setFbank(String fbank) {
		this.fbank = fbank;
	}

	public String getFlogo() {
		return flogo;
	}

	public void setFlogo(String flogo) {
		this.flogo = flogo;
	}

	public String getFweb() {
		return fweb;
	}

	public void setFweb(String fweb) {
		this.fweb = fweb;
	}

	public String getFremark() {
		return fremark;
	}

	public void setFremark(String fremark) {
		this.fremark = fremark;
	}

	public Timestamp getFwrite() {
		return fwrite;
	}

	public void setFwrite(Timestamp fwrite) {
		this.fwrite = fwrite;
	}

	public void setFtable(String ftable) {
		this.ftable = ftable;
	}

	public String getFtable() {
		return ftable;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getPs() {
		return ps;
	}

	public void setPs1(String ps1) {
		this.ps1 = ps1;
	}

	public String getPs1() {
		return ps1;
	}

}
