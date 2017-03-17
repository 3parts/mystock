package com.cxstock.pojo;

/**
 * Tbdecorate entity. @author MyEclipse Persistence Tools
 */

public class Tbdecorate implements java.io.Serializable {

	// Fields

	private Integer fid;
	private Integer finoutid;
	private Integer finouttype;
	private Integer fcoreid;
	private Integer fcoretype;

	// Constructors

	/** default constructor */
	public Tbdecorate() {
	}

	/** full constructor */
	public Tbdecorate(Integer finoutid, Integer finouttype, Integer fcoreid,
			Integer fcoretype) {
		this.finoutid = finoutid;
		this.finouttype = finouttype;
		this.fcoreid = fcoreid;
		this.fcoretype = fcoretype;
	}

	// Property accessors

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getFinoutid() {
		return this.finoutid;
	}

	public void setFinoutid(Integer finoutid) {
		this.finoutid = finoutid;
	}

	public Integer getFinouttype() {
		return this.finouttype;
	}

	public void setFinouttype(Integer finouttype) {
		this.finouttype = finouttype;
	}

	public Integer getFcoreid() {
		return this.fcoreid;
	}

	public void setFcoreid(Integer fcoreid) {
		this.fcoreid = fcoreid;
	}

	public Integer getFcoretype() {
		return this.fcoretype;
	}

	public void setFcoretype(Integer fcoretype) {
		this.fcoretype = fcoretype;
	}

}