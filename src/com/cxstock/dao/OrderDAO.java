package com.cxstock.dao;

import java.util.HashMap;
import java.util.List;

/**
 * 此接口用于 jdbc连接数据库
 * */
public interface OrderDAO extends BaseDAO {

	/**
	 * 查找用户的权限
	 * */
	public List finUserRights(Integer userid);

	/**
	 * 根据sql查找商品信息
	 * */
	public List finCommodityPage(String sql);

	/**
	 * 根据sql得到行数
	 * */
	public int getCount(String sql);

	/**
	 * 根据sql得到数据源
	 * */
	public List<HashMap> getDataInfo(String sql);

	/**
	 * 根据sql得到员工的资料
	 * */
	public List getPerson(String sql);

	/**
	 * 根据sql得到单个记录
	 * */
	public Object getSinge(String sql);

	/**
	 * 查看记录是否存在
	 * */
	public Boolean getExist(String sql);

	public Boolean getExist(String sql, String url, String user, String password);

	/**
	 * 带连接方式的 执行sql语句 insert、update、delete
	 * */
	public Integer ExecuteSql(String sql, String url, String user,
			String password);

	/**
	 * 默认用当前连接
	 * */
	public Integer ExecuteSql(String sql);

	/**
	 * 批量执行sql 实现事务
	 * */
	public Integer ExecuteSql(List<String> listsql, String url, String user,
			String password);

	/**
	 * 使用当前连接执行 批量sql
	 * */
	public Integer ExecuteSql(List<String> listsql);

}
