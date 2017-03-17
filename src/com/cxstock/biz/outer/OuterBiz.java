package com.cxstock.biz.outer;

import java.sql.SQLException;
import java.util.Date;

import com.cxstock.biz.outer.dto.TBdUserinfo;
import com.cxstock.utils.pubutil.Page;

public interface OuterBiz {

	/** 保存信息 **/
	public String saveInfo(TBdUserinfo t);

	/** 修改密码 **/
	public String savePsd(String ps, String ps1);

	/** 审核不通过 **/
	public String saveNoCheck(Integer fid);

	/**
	 * 审核通过
	 * 
	 * @throws SQLException
	 **/
	public String saveCheck(Integer fid, String ftable, String path)
			throws Exception;

	/** 获得申请的记录 **/
	public void getInfo(Page page);

	/***
	 * 禁用
	 * 
	 * @throws Exception
	 **/
	public void saveDisable(Integer fid, String path) throws Exception;

	/***
	 * 启用
	 * 
	 * @throws Exception
	 **/
	public void saveEnabled(Integer fid, String path) throws Exception;

	/***
	 * 删除
	 * 
	 * @throws Exception
	 **/
	public void saveDelete(Integer fid, String path) throws Exception;

	/**
	 * 为企业延期
	 * */
	public void saveDate(Integer fid, Date dt);

}
