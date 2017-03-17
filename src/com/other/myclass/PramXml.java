package com.other.myclass;

public class PramXml {

	private String url;
	private String username;
	private String password;
	private String initialSize;
	private String maxIdle;
	private String minIdle;
	private String maxActive;
	private String removeAbandoned;
	private String removeAbandonedTimeout;
	private String maxWait;
	private String defaultAutoCommit;
	private String validationQuery;
	private String testOnBorrow;
	private String testOnReturn;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setInitialSize(String initialSize) {
		this.initialSize = initialSize;
	}

	public String getInitialSize() {
		return initialSize;
	}

	public void setMaxIdle(String maxIdle) {
		this.maxIdle = maxIdle;
	}

	public String getMaxIdle() {
		return maxIdle;
	}

	public void setMinIdle(String minIdle) {
		this.minIdle = minIdle;
	}

	public String getMinIdle() {
		return minIdle;
	}

	public void setMaxActive(String maxActive) {
		this.maxActive = maxActive;
	}

	public String getMaxActive() {
		return maxActive;
	}

	public void setRemoveAbandoned(String removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	public String getRemoveAbandoned() {
		return removeAbandoned;
	}

	public void setRemoveAbandonedTimeout(String removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	public String getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	public void setMaxWait(String maxWait) {
		this.maxWait = maxWait;
	}

	public String getMaxWait() {
		return maxWait;
	}

	public void setDefaultAutoCommit(String defaultAutoCommit) {
		this.defaultAutoCommit = defaultAutoCommit;
	}

	public String getDefaultAutoCommit() {
		return defaultAutoCommit;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setTestOnBorrow(String testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public String getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnReturn(String testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public String getTestOnReturn() {
		return testOnReturn;
	}

}
