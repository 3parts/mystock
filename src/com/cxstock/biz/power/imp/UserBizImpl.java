package com.cxstock.biz.power.imp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.power.dto.UserMenuDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.pojo.Role;
import com.cxstock.pojo.Tbcompany;
import com.cxstock.pojo.Tbtemplate;
import com.cxstock.pojo.Tbuserunitrights;
import com.cxstock.pojo.Users;
import com.cxstock.utils.pubutil.Page;

public class UserBizImpl implements UserBiz, ApplicationContextAware {

	private OrderDAO baseDao;
	private ConfigurableApplicationContext app;

	public void setBaseDao(OrderDAO baseDao) {
		this.baseDao = baseDao;
	}

	/*
	 * 登录验证
	 * 
	 * @see com.cxstock.biz.power.UserBiz#login(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public UserDTO login(String code, String pass) {
		String hql = "from Users as t where t.logincode='" + code
				+ "' and t.password='" + pass + "'";
		Users user = (Users) baseDao.loadObject(hql);
		if (user != null) {
			UserDTO dto = UserDTO.createDto(user);
			/* 赋值 角色id */
			if (user.getRole() != null) {
				dto.setRoleid(user.getRole().getRoleid());
			}
			/* 赋值企业 */
			if (dto.getTbcompany() != null) {
				Tbcompany t = dto.getTbcompany();
				dto.setCompanyid(t.getId());
				dto.setCompanyname(t.getVcName());
			}
			hql = "from Vusermenu as t where t.userid=" + user.getUserid()
					+ " ORDER BY t.menuid";
			List list = baseDao.findByHql(hql);
			JSONArray jsong = JSONArray.fromObject(new UserMenuDTO().getTree(0,
					list));

			dto.setUsermenu(jsong.toString());
			return dto;
		}
		return null;
	}

	/*
	 * 分页查询用户列表
	 * 
	 * @see
	 * com.cxstock.biz.power.UserBiz#findPageUser(com.cxstock.utils.system.Page)
	 */
	@SuppressWarnings("unchecked")
	public void findPageUser(Page page) {
		String hql = "from Users as t left join fetch t.role order by t.userid";
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		List dtoList = UserDTO.createDtos(list);
		int total = baseDao.countAll("Users");
		page.setRoot(dtoList);
		page.setTotal(total);
	}

	/*
	 * 保存/修改用户
	 * 
	 * @see
	 * com.cxstock.biz.power.UserBiz#saveOrUpdateUser(com.cxstock.biz.power.
	 * dto.UserDTO)
	 */
	public boolean saveOrUpdateUser(UserDTO dto) {
		Users user = new Users();
		if (dto.getUserid() != null) {
			user = (Users) baseDao.loadById(Users.class, dto.getUserid());
		} else {
			Users u = (Users) baseDao.loadObject("from Users where logincode='"
					+ dto.getLogincode() + "'");
			if (u != null) {
				return false;
			}
			user.setLogincode(dto.getLogincode());
			user.setState(0);
		}
		user.setPassword(dto.getPassword());
		user.setUsername(dto.getUsername());
		user.setRole(new Role(dto.getRoleid()));
		user.setBz(dto.getBz());
		Tbcompany tbCompany = new Tbcompany();
		tbCompany.setId(dto.getCompanyid());
		user.setTbcompany(tbCompany);
		baseDao.saveOrUpdate(user);
		/* 自动设置用户的权限 */
		if (dto.getUserid() == null) {
			Tbuserunitrights rights = new Tbuserunitrights();
			rights.setUserid(user.getUserid());
			rights.setCompanyid(user.getTbcompany().getId());
			baseDao.saveOrUpdate(rights);
		}
		return true;
	}

	/*
	 * 删除用户
	 * 
	 * @see com.cxstock.biz.power.UserBiz#deleteUser(java.lang.String)
	 */
	public void deleteUser(Integer userid) {
		baseDao.deleteById(Users.class, userid);
		/* 删除用户权限记录 */
		List list = baseDao
				.findByHql("from Tbuserunitrights as t where t.userid="
						+ userid);
		for (int i = 0; i < list.size(); i++) {
			baseDao.delete((Tbuserunitrights) list.get(i));
		}
	}

	@Override
	public List findUser(Integer companyid, String key) {
		String hql = "from Users as t where 1=1";
		if (companyid != null && companyid != 0) {
			hql += " and t.tbcompany in (" + companyid + ")";
		}
		if (key != null && !key.isEmpty()) {
			hql += " and (t.logincode like '%" + key
					+ "%' or t.username like '%" + key + "%')";
		}
		return baseDao.findByHql(hql);
	}

	/**
	 * 保存报表
	 * */
	@SuppressWarnings("deprecation")
	@Override
	public void saveReport(HttpServletRequest request) {
		/** 修改文件名称 ***/
		String FileName = request.getRealPath("grf") + "\\"
				+ request.getSession().getAttribute("table") + "\\"
				+ request.getParameter("report");
		File f = new File(FileName);
		if (f.exists()) {
			String strId = request.getParameter("id");
			if (strId == null || strId.length() == 0 || "null".equals(strId))
				return;
			Tbtemplate t = (Tbtemplate) baseDao.loadById(Tbtemplate.class,
					Integer.parseInt(strId));

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
			String str = df.format(new Date()) + '-' + t.getCompanyId();
			String fileNewName = request.getRealPath("grf") + "\\"
					+ request.getSession().getAttribute("table") + "\\"
					+ t.getVcTable() + str + ".grf";
			if (f.renameTo(new File(fileNewName))) {
				/** 修改相应记录的数据 **/
				t.setVccode(t.getVcTable() + str + ".grf");
				baseDao.saveOrUpdate(t);
			}
		}

	}

	/** 登录企业列表 **/
	@Override
	public boolean loginQyList(String code, String pass) {

		if (baseDao.getExist("select 1 from t_bd_user where username='" + code
				+ "' and password='" + pass + "'")) {
			return true;
		}
		return false;
	}

	/*** 验证登录是否有效 **/
	@Override
	public boolean loginCheck(String ftable) {
		BasicDataSource bds = (BasicDataSource) this.app.getBean("qjkj");
		String sql = "SELECT 1 FROM t_bd_userinfo WHERE ftable='" + ftable
				+ "' AND fstatus=1";
		return baseDao.getExist(sql, bds.getUrl(), bds.getUsername(), bds
				.getPassword());
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.app = (ConfigurableApplicationContext) arg0;

	}
}
