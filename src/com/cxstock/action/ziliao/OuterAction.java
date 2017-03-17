package com.cxstock.action.ziliao;

import java.util.Date;

import com.cxstock.action.BaseAction;

@SuppressWarnings("serial")
public class OuterAction extends BaseAction {

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

	/**
	 * @description 保存记录
	 * @author RC
	 * @date 2015-06-18
	 * */
	public void saveInfo() {
		// JdbcDao dao = new JdbcDao();
		// dao.Execute("select 1 from t_bd_userinfo");

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

}
