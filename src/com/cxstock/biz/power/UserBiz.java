package com.cxstock.biz.power;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.utils.pubutil.Page;

public interface UserBiz {

	/**
	 * 登录验证
	 */
	public UserDTO login(String code, String pass);

	/**
	 * 分页查询用户列表
	 */
	public void findPageUser(Page page);

	/**
	 * 保存/修改用户
	 */
	public boolean saveOrUpdateUser(UserDTO dto);

	/**
	 * 删除用户
	 */
	public void deleteUser(Integer userid);

	/**
	 * 根据条件查询 用户
	 * */
	public List findUser(Integer companyid, String key);

	public void saveReport(HttpServletRequest a);

	public boolean loginQyList(String code, String pass);

	public boolean loginCheck(String ftabl);

}
