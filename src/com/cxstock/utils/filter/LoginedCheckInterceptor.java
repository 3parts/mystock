package com.cxstock.utils.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.utils.system.Constants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/** session过期、登录有效性及操作的权限验证拦截器 */
@SuppressWarnings("serial")
public class LoginedCheckInterceptor extends AbstractInterceptor {

	/** 拦截请求并进行登录有效性验证 */
	public String intercept(ActionInvocation ai) throws Exception {
		HttpServletRequest httpRequest = ServletActionContext.getRequest();
		HttpServletResponse httpResponse = ServletActionContext.getResponse();

		// 取得请求的URL
		String url = httpRequest.getRequestURL().toString();
		/**
		 * @description 申请界面无需拦截
		 * @author RC
		 * @date 2015-06-18
		 * */
		if (url.indexOf("/outer/outer_saveInfo.do") > -1) {
			return ai.invoke();
		}
		/** 申请界面的城市请求 无需拦截 **/
		if (url.indexOf("/outer/kh_getCityData.do") > -1) {
			return ai.invoke();
		}

		if (url.indexOf("/outer/") > -1) {
			/** 企业审核 session不同 **/
			String str = (String) httpRequest.getSession().getAttribute(
					"userinfo1");
			if (str != null && str.length() > 0 && "outer_admin".equals(str)) {
				return ai.invoke();
			} else {
				return "tologin";
			}
		}

		// 验证Session是否过期
		if (!ServletActionContext.getRequest().isRequestedSessionIdValid()) {
			// session过期,转向session过期提示页,最终跳转至登录页面
			if ("XMLHttpRequest".equals(httpRequest
					.getHeader("x-requested-with"))) {// ajax
				httpResponse.addHeader("__timeout", "true");
				return null;
			} else
				return "tologin";
		} else {
			// 对登录与注销请求直接放行,不予拦截
			if (url.indexOf("/user_login.do") != -1) {
				return ai.invoke();
			} else {
				UserDTO userInfo = (UserDTO) httpRequest.getSession()
						.getAttribute(Constants.USERINFO);
				// 验证是否已经登录
				if (userInfo == null) {
					// session过期,转向session过期提示页,最终跳转至登录页面
					if ("XMLHttpRequest".equals(httpRequest
							.getHeader("x-requested-with"))) {// ajax
						httpResponse.addHeader("__timeout", "true");
						return null;
					} else
						return "tologin";
				} else {
					// 已登录直接放行
					return ai.invoke();
				}
			}
		}
	}
}