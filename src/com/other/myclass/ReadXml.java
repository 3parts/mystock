package com.other.myclass;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.cxstock.action.BaseAction;

public class ReadXml extends BaseAction implements ApplicationContextAware {

	private ApplicationContext ac;

	private String url;
	private String name;
	private String pwd;
	private String driver;

	public void InitXml() {
		String str = (String) this.getSession().getAttribute("table");
		BasicDataSource bs = (BasicDataSource) ac.getBean(str);
		driver = bs.getDriverClassName();
		name = bs.getUsername();
		pwd = bs.getPassword();
		url = bs.getUrl();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd() {
		return pwd;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		ac = arg0;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriver() {
		return driver;
	}

}
