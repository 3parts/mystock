package com.cxstock.biz.ziliao;

import java.util.List;

import com.cxstock.biz.ziliao.dto.QyDTO;

public interface QyBiz {

	/**
	 * 分页获取数据
	 * */
	public List findPageInfo(String strKey, int istart, int ilimit);

	/**
	 * 保存数据
	 * */
	public void saveOrUpdateQy(QyDTO dto);

	/**
	 * 删除数据
	 * */
	public void deleteQyInfo(Integer id);

	/**
	 * 加载企业数据
	 * */
	public List loadQyInfo();

	/**
	 * 加载树形企业
	 * */
	public List findTreeInfo(String key);

	/**
	 * 加载树形企业
	 * */
	public List findTreeInfo1(String key);

	/**
	 * 加载数据表中所有行数
	 * */
	public int countAll();

	/* 当前用是否能打开本url */
	public String getOpenUrl(String url, Integer roleid);

}
