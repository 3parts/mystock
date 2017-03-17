package com.cxstock.utils.pubutil;

import java.util.List;

@SuppressWarnings("unchecked")
public class Page {
	/** 总记录数 */
	private int total;
	/** 分页结果 */
	private List root;
	/** 开始页码 */
	private int start;
	/** 每页多少 */
	private int limit;
	/** 查询条件 */
	private String wheres;
	/** 表 **/
	private String table;
	/** 字段 **/
	private String field;
	/** 存放关键记录的对象 **/
	private Object listMap;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRoot() {
		return root;
	}

	public void setRoot(List root) {
		this.root = root;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getWheres() {
		return wheres;
	}

	public void setWheres(String wheres) {
		this.wheres = wheres;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setListMap(Object listMap) {
		this.listMap = listMap;
	}

	public Object getListMap() {
		return listMap;
	}

}