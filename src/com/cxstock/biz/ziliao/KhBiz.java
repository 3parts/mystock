package com.cxstock.biz.ziliao;

import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.ziliao.dto.KhDTO;
import com.cxstock.utils.pubutil.Page;

public interface KhBiz {

	/**
	 * 分页查询用户列表
	 */
	public void findPageKh(Page page);

	/**
	 * 保存/修改用户
	 */
	public void saveOrUpdateKh(KhDTO dto);

	/**
	 * 删除用户
	 */
	public void deleteKh(Integer khid);

	/**
	 * 客户下拉列表
	 */
	@SuppressWarnings("unchecked")
	public List findKhComb(String key, Integer keyid);

	/**
	 * 获得省份的数据源
	 * */
	public List getProvinceData();

	/**
	 * 获得城市的数据源
	 * */
	public List getCityData(String StrProId);

	/**
	 * 获取区县数据源
	 **/
	public List getAreaData(String strCityId);

	/**
	 * 根据Id获得客户信息
	 * */
	public List<HashMap> getKh(Integer id);

}
