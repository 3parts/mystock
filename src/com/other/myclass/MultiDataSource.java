package com.other.myclass;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MultiDataSource implements DataSource, ApplicationContextAware {
	private DataSource dateSource = null;
	private ApplicationContext applicationContext = null;

	public static Map<String, Object> targetDataSources = new LinkedHashMap<String, Object>();

	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return getDateSource().getConnection();
	}

	public Connection getConnection(String arg0, String arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public DataSource getDateSource(String dataSourceName) {
		try {
			if (dataSourceName == null || dataSourceName.equals("")) {
				return this.dateSource;
			}
			if (!targetDataSources.containsKey(dataSourceName)) {
				targetDataSources.put(dataSourceName, this.applicationContext
						.getBean(dataSourceName));
			}
			Object obj = targetDataSources.get(dataSourceName);
			if (obj == null) {
				return null;
			} else {
				return (DataSource) obj;
			}
		} catch (NoSuchBeanDefinitionException ex) {
			System.out.print("\n[请重新启动 tomcat]\n");
			return null;
		}

		// System.out.println("线程进来2");
		// try {
		// if (dataSourceName == null || dataSourceName.equals("")) {
		// // System.out.println("线程进来3");
		// return this.dateSource;
		// }
		// // System.out.println("线程进来4");
		//
		// return (DataSource)
		// this.applicationContext.getBean(dataSourceName);//
		// 根据dataSourceName加载配置文件中的数据源对象
		// } catch (NoSuchBeanDefinitionException ex) {
		// System.out.print("\n[请重新启动 tomcat]\n");
		// return null;
		// // System.out.println("线程进来5");
		// // return this.dateSource;
		// }
	}

	public void setDateSource(DataSource dateSource) {
		// System.out.println("dataSource方法");
		this.dateSource = dateSource;
	}

	/**
	 * 项目启动时，默认使用defaultDataSource 用户选择时，根据选择数据源
	 * ThreadLocal在单例下生成多个线程变量副本，解决多用户并发访问
	 */
	public DataSource getDateSource() {
		// System.out.println("线程进来1");
		String sp = SpObserver.getSp();
		return this.getDateSource(sp);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}