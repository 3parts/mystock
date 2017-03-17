package com.cxstock.biz.userright;

public interface UserrightBiz {

	/**
	 * 根据关键字查询数据
	 * */
	public void saveUserRight(String units, Integer userid);

	/**
	 * 获得用户所能操作企业的ID字符串
	 * */
	public String getUserUnitRights(Integer userid);

}
