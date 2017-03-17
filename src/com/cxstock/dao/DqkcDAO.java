package com.cxstock.dao;

import java.util.List;

public interface DqkcDAO extends BaseDAO {

	/**
	 * 当前库存查询
	 */
	@SuppressWarnings("unchecked")
	public List getDqkcByParams(Integer kfid, Integer lbid, String search);

	/**
	 * 当前的菜单记录
	 * */
	@SuppressWarnings("unchecked")
	public List getMenu();

}
