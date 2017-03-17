package com.cxstock.biz.ziliao.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.cxstock.pojo.Gys;

public class GysDTO {

	private Integer gysid;
	private String name;
	private String lxren;
	private String lxtel;
	private String address;
	private String bz;
	private Integer companyid;
	private String fsn;
	private String number;
	private String fax;

	public GysDTO() {
		super();
	}

	public GysDTO(Integer gysid, String name, String lxren, String lxtel,
			String address, String bz, Integer companyid) {
		super();
		this.gysid = gysid;
		this.name = name;
		this.lxren = lxren;
		this.lxtel = lxtel;
		this.address = address;
		this.bz = bz;
		this.companyid = companyid;
	}

	public static GysDTO createDto(Gys pojo) {
		GysDTO dto = null;
		if (pojo != null) {
			dto = new GysDTO(pojo.getGysid(), pojo.getName(), pojo.getLxren(),
					pojo.getLxtel(), pojo.getAddress(), pojo.getBz(), pojo
							.getCompanyid());
			dto.setFsn(pojo.getFsn());
			dto.setNumber(pojo.getNumber());
			dto.setFax(pojo.getFax());
		}
		return dto;
	}

	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<GysDTO> list = new ArrayList<GysDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				list.add(createDto((Gys) iterator.next()));
			}
		}
		return list;
	}

	public Integer getGysid() {
		return gysid;
	}

	public void setGysid(Integer gysid) {
		this.gysid = gysid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLxren() {
		return lxren;
	}

	public void setLxren(String lxren) {
		this.lxren = lxren;
	}

	public String getLxtel() {
		return lxtel;
	}

	public void setLxtel(String lxtel) {
		this.lxtel = lxtel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setFsn(String fsn) {
		this.fsn = fsn;
	}

	public String getFsn() {
		return fsn;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return fax;
	}

}
