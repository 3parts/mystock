package com.cxstock.action.ceshi;

import java.util.List;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.ceshi.CsBiz;
import com.cxstock.biz.ceshi.imp.CsBizImpl;
import com.other.gridreport.GenXmlData;
import com.other.gridreport.ReadReport;

@SuppressWarnings("serial")
public class CsAction extends BaseAction {

	private CsBiz csBiz = new CsBizImpl();

	private String tab;
	private String ymd;

	/**
	 * 生成单据编号
	 */
	public String getDjCode() {
		try {
			String code = csBiz.getDjCode(tab, ymd);
			this.outString(code);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 得到名称
	 * */
	public String getMenuName() {
		try {
			String strName = csBiz.getDataInfo("Menu", "0");
			this.outString(strName);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 得到网格的数据源
	 * */
	@SuppressWarnings("unchecked")
	public String getDataStore() {
		try {
			List list = csBiz.getDataInfo();
			this.outListString(list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 获取xml数据
	 * */
	public String getXmlDate() {

		GenXmlData.XML_GenDetailData(getResponse(),
				"select t.* from users t where t.userid=1");

		return null;

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
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getYmd() {
		return ymd;
	}

	public void setYmd(String ymd) {
		this.ymd = ymd;
	}

	public void setCsBiz(CsBiz csBiz) {
		this.csBiz = csBiz;
	}

}
