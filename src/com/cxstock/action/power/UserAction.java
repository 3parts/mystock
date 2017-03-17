package com.cxstock.action.power;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.pojo.Users;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.other.gridreport.ReadReport;

@SuppressWarnings("serial")
public class UserAction extends BaseAction {

	private UserBiz userBiz;

	private Integer userid;
	private String logincode;
	private String password;
	private String username;
	private Integer roleid;
	private Integer state;
	private String bz;
	private Integer companyid;
	private String keyword;
	private String table;

	private String isuser;

	public String getIsuser() {
		return isuser;
	}

	public void setIsuser(String isuser) {
		this.isuser = isuser;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/** 登录验证 */
	public String login() {
		try {
			/** 移除session **/
			this.getSession().removeAttribute("userInfo");
			this.getSession().removeAttribute("userinfo1");

			String code = logincode.trim().toLowerCase();
			code = new String(code.getBytes("ISO-8859-1"), "utf-8"); /* 防止中文乱码 */
			String pass = password.trim().toLowerCase();
			if (table == null || table.length() <= 0
					|| "undefined".equals(table)) {
				this.getRequest().setAttribute("error", "数据库不正确");
				return "input";
			}

			// /** 验证数据库是否有效 **/
			// if (!userBiz.loginCheck(table)) {
			// this.getRequest().setAttribute("error", "数据库名称不正确或企业已被 禁用");
			// return "input";
			// }

			/** 写入数据库名称 **/
			this.getSession().setAttribute("table", table);
			if ("qjkj".equals(table)) {
				/** 登录企业列表 **/
				if (userBiz.loginQyList(code, pass)) {
					this.getSession().setAttribute("userinfo1", "outer_admin"); /* 写入登录session */
					return "successqjkj";
				} else {
					this.getRequest().setAttribute("error", "用户名或密码不正确");
					return "input";
				}
			}

			/** ================开始登陆============= **/
			UserDTO userInfo = userBiz.login(code, pass);
			if (userInfo != null) {
				this.getSession().setAttribute(Constants.USERINFO, userInfo);
				/** 记录当前登录部门和登录人的ID **/
				// PublicClass.iLoginCompanyId = userInfo.getCompanyid();
				// PublicClass.strLoginCompanyName = userInfo.getCompanyname();
				// PublicClass.iLoginId = userInfo.getUserid();
				// PublicClass.strLoginName = userInfo.getUsername();
				// PublicClass.iLoginRole = userInfo.getRoleid();
				return "success";
			} else {
				this.getRequest().setAttribute("error", "用户名或密码错误");
				return "input";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("error", "连接失败");
			return "login";
		}
	}

	/** 用户权限菜单 */
	public String getMenuBuf() {
		UserDTO userInfo = this.getUserDTO();
		try {
			if (userInfo != null) {
				this.outString(userInfo.getUsermenu());
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 分页查询用户列表
	 */
	public String findPageUser() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			userBiz.findPageUser(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 保存/修改用户
	 */
	public String saveOrUpdateUser() {
		try {
			UserDTO dto = new UserDTO(userid, logincode, password, username,
					roleid, null, state, bz, companyid, null);
			boolean bool = userBiz.saveOrUpdateUser(dto);
			if (bool) {
				if (userid == null) {
					this.outString("{success:true,message:'保存成功!'}");
				} else {
					this.outString("{success:true,message:'修改成功!'}");
				}
			} else {
				this.outString("{success:false,errors:'用户账号已存在!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 删除用户
	 */
	public String deleteUser() {
		try {
			userBiz.deleteUser(userid);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 查询user
	 * */
	@SuppressWarnings("unchecked")
	public void findUser() {

		UserDTO udt = (UserDTO) this.getSession().getAttribute(
				Constants.USERINFO);

		try {
			if (companyid == null) {
				companyid = udt.getCompanyid();
			}
			List list = userBiz.findUser(companyid, keyword);
			List listDto = new ArrayList();
			/* 遍历解析list */
			Users users;
			UserDTO userDto;
			for (int i = 0; i < list.size(); i++) {
				users = (Users) list.get(i);
				if ("1".equals(isuser)) { /* 只查询自己 */
					if (!udt.getUserid().equals(users.getUserid())) {
						continue;
					}
				}
				userDto = new UserDTO();
				userDto.setUserid(users.getUserid());
				userDto.setLogincode(users.getLogincode());
				userDto.setUsername(users.getUsername());

				listDto.add(userDto);
			}
			this.outListString(listDto);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();

		}
	}

	/**
	 * 读取文件
	 * */
	public void getRead() {
		try {
			ReadReport.Read(getRequest(), getResponse());
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	/**
	 * 保存模板
	 * */
	public void saveReport() {
		try {
			ReadReport.Save(getRequest(), getResponse());
			userBiz.saveReport(getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public void setTable(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

}
