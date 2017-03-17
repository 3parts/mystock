package com.cxstock.biz.outer.dto;

import java.sql.Timestamp;
import java.util.Date;

public class TBdUserinfo implements java.io.Serializable {

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

	// Constructors

	/** default constructor */
	public TBdUserinfo() {
	}

	/** full constructor */
	public TBdUserinfo(String fname, Date fopendate, Date ftodate,
			String fcity, String faddress, String fman, String ftel,
			String fqq, String femail, String fwx, String fbankname,
			String fbanknum, String fbank, String flogo, String fweb,
			String fremark, Timestamp fwrite) {
		this.fname = fname;
		this.fopendate = fopendate;
		this.ftodate = ftodate;
		this.fcity = fcity;
		this.faddress = faddress;
		this.fman = fman;
		this.ftel = ftel;
		this.fqq = fqq;
		this.femail = femail;
		this.fwx = fwx;
		this.fbankname = fbankname;
		this.fbanknum = fbanknum;
		this.fbank = fbank;
		this.flogo = flogo;
		this.fweb = fweb;
		this.fremark = fremark;
		this.fwrite = fwrite;
	}

	// Property accessors

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Date getFopendate() {
		return this.fopendate;
	}

	public void setFopendate(Date fopendate) {
		this.fopendate = fopendate;
	}

	public Date getFtodate() {
		return this.ftodate;
	}

	public void setFtodate(Date ftodate) {
		this.ftodate = ftodate;
	}

	public String getFcity() {
		return this.fcity;
	}

	public void setFcity(String fcity) {
		this.fcity = fcity;
	}

	public String getFaddress() {
		return this.faddress;
	}

	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}

	public String getFman() {
		return this.fman;
	}

	public void setFman(String fman) {
		this.fman = fman;
	}

	public String getFtel() {
		return this.ftel;
	}

	public void setFtel(String ftel) {
		this.ftel = ftel;
	}

	public String getFqq() {
		return this.fqq;
	}

	public void setFqq(String fqq) {
		this.fqq = fqq;
	}

	public String getFemail() {
		return this.femail;
	}

	public void setFemail(String femail) {
		this.femail = femail;
	}

	public String getFwx() {
		return this.fwx;
	}

	public void setFwx(String fwx) {
		this.fwx = fwx;
	}

	public String getFbankname() {
		return this.fbankname;
	}

	public void setFbankname(String fbankname) {
		this.fbankname = fbankname;
	}

	public String getFbanknum() {
		return this.fbanknum;
	}

	public void setFbanknum(String fbanknum) {
		this.fbanknum = fbanknum;
	}

	public String getFbank() {
		return this.fbank;
	}

	public void setFbank(String fbank) {
		this.fbank = fbank;
	}

	public String getFlogo() {
		return this.flogo;
	}

	public void setFlogo(String flogo) {
		this.flogo = flogo;
	}

	public String getFweb() {
		return this.fweb;
	}

	public void setFweb(String fweb) {
		this.fweb = fweb;
	}

	public String getFremark() {
		return this.fremark;
	}

	public void setFremark(String fremark) {
		this.fremark = fremark;
	}

	public Timestamp getFwrite() {
		return this.fwrite;
	}

	public void setFwrite(Timestamp fwrite) {
		this.fwrite = fwrite;
	}

}