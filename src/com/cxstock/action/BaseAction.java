package com.cxstock.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {

	private int limit; // 每页显示多少行
	private int start; // 开始行

	public void outJsonString(String str) {
		getResponse().setContentType("text/json;charset=UTF-8");
		outString(str);
	}

	public void outString(String str) {
		try {
			OutputStream out=getResponse().getOutputStream();
			/** 去除 json 转换不了的字符 **/
			str = str.replaceAll("\n", "<br />").replaceAll("\r", "<br />");
			out.write(str.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void outListString(List list) {
		try {
			JSONArray jsonArray = new JSONArray();
			if (list.size() > 0) {
				jsonArray = JSONArray.fromObject(list);
			}
			String jsonString = "{total:" + list.size() + ",root:"
					+ jsonArray.toString() + "}";
			outString(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void outObjectString(Object obj) {
		JSONObject josnobj = new JSONObject();
		if (obj != null) {
			josnobj = JSONObject.fromObject(obj);
		}
		String jsonString = "{success:true,data:" + josnobj.toString() + "}";
		outString(jsonString);
	}

	public void outObjString(Object obj) {
		JSONArray jsonArray = new JSONArray();
		if (obj != null) {
			jsonArray = JSONArray.fromObject(obj);
		}
		String jsonString = "{success:true,data:" + jsonArray.toString() + "}";
		outString(jsonString);
	}

	public void outPageString(Page page) {

		JSONArray jsonArray = new JSONArray();
		if (page.getRoot().size() > 0) {
			jsonArray = JSONArray.fromObject(page.getRoot());
		}
		String jsonString = "{total:" + page.getTotal() + ",root:"
				+ jsonArray.toString() + "}";
		outString(jsonString);
	}

	@SuppressWarnings("unchecked")
	public void outTreeJsonList(List list) {
		JSONArray jsonArray = new JSONArray();
		if (list.size() > 0) {
			jsonArray = JSONArray.fromObject(list);
		}
		outString(jsonArray.toString());
	}

	public void outXMLString(String xmlStr) {
		getResponse().setContentType("application/xml;charset=UTF-8");
		outString(xmlStr);
	}

	@SuppressWarnings("unchecked")
	public void outHashMapString(List<HashMap> listMap) {
		String str = "";
		HashMap hs;
		Iterator it;
		Map.Entry me;
		/* 拼接 json */
		str += "{total:" + listMap.size() + ",root:[";
		for (int i = 0; i < listMap.size(); i++) {
			hs = listMap.get(i);
			str += "{";
			it = hs.entrySet().iterator();
			while (it.hasNext()) {
				me = (Map.Entry) it.next();
				str += "'"
						+ me.getKey()
						+ "':'"
						+ (me.getValue() == null
								|| (me.getValue() + "").length() == 0
								|| (me.getValue() + "") == "null" ? "" : me
								.getValue()) + "',";
			}
			str = str.substring(0, str.length() - 1); /* 去除逗号 */
			str += "},";
		}
		if (listMap.size() > 0) {/* 去除逗号 */
			str = str.substring(0, str.length() - 1);
		}
		str += "]}";
		outString(str);
	}

	@SuppressWarnings("unchecked")
	public void outHashMapPageString(Page page) {
		List<HashMap> listMap = (List<HashMap>) page.getListMap();
		String str = "";
		HashMap hs;
		Iterator it;
		Map.Entry me;
		/* 拼接 json */
		str += "{total:" + page.getTotal() + ",root:[";
		for (int i = 0; i < listMap.size(); i++) {
			hs = listMap.get(i);
			str += "{";
			it = hs.entrySet().iterator();
			while (it.hasNext()) {
				me = (Map.Entry) it.next();
				str += "'"
						+ me.getKey()
						+ "':'"
						+ (me.getValue() == null
								|| (me.getValue() + "").length() == 0
								|| (me.getValue() + "") == "null" ? "" : me
								.getValue()) + "',";
			}
			str = str.substring(0, str.length() - 1); /* 去除逗号 */
			str += "},";
		}
		if (listMap.size() > 0) {/* 去除逗号 */
			str = str.substring(0, str.length() - 1);
		}
		str += "]}";
		outString(str);
	}

	public void outError() {
		outString("{success:false,errors:'操作失败！'}");
	}

	public void outError(String message) {
		outString("{success:false,errors:'" + message + "'}");
	}

	/**
	 * 获得request
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得response
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获得session
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获得servlet上下文
	 * 
	 * @return
	 */
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public String getRealyPath(String path) {
		return getServletContext().getRealPath(path);
	}

	public UserDTO getUserDTO() {
		return (UserDTO) getSession().getAttribute(Constants.USERINFO);
	}

	// 获得uploadfile路径的实际目录
	public String getUpdateFilePath() {
		return getRealyPath("/").concat(
				getServletContext().getInitParameter(Constants.FILE_DIRECTORY));
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
