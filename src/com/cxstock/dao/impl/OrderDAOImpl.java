package com.cxstock.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cxstock.biz.ziliao.dto.CommodityDTO;
import com.cxstock.biz.ziliao.dto.PsDTO;
import com.cxstock.dao.OrderDAO;
import com.cxstock.utils.pubutil.TreeNodeChecked;

public class OrderDAOImpl extends BaseDAOImpl implements OrderDAO {

	/**
	 * 创建
	 * */
	@SuppressWarnings( { "unchecked" })
	@Override
	public List finUserRights(Integer userid) {

		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer(
				"SELECT a.ID,a.VC_NAME,b.id AS chek FROM tbcompany a LEFT JOIN (");
		sql.append("SELECT c.* FROM tbuserunitrights c");
		if (userid != null) {
			sql.append(" WHERE c.userid=" + userid);
		}
		sql.append(") b ");
		sql.append("ON a.ID=b.companyid");
		try {
			conn = getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql.toString());
			String strCheck = null;
			TreeNodeChecked node;
			while (rs.next()) {
				node = new TreeNodeChecked();
				node.setId(rs.getString("ID"));
				node.setText(rs.getString("VC_NAME"));
				strCheck = rs.getString("chek");
				node.setChecked(strCheck != null && !strCheck.isEmpty()
						&& strCheck != "0");
				node.setLeaf(true);
				/* 设置图标 */
				node.setIconCls("menu-folder");
				list.add(node);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}

		}
		return list;
	}

	/**
	 * 查找商品信息
	 * */
	@Override
	public List finCommodityPage(String sql) {

		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			CommodityDTO com;
			while (rs.next()) {
				/* 逐行读取记录 */
				com = new CommodityDTO();
				com.setId(rs.getInt("id"));
				com.setCompanyId(rs.getInt("companyId"));
				com.setCompanyName(rs.getString("companyName"));
				com.setDbAverageMoney(rs.getDouble("dbAverageMoney"));
				com.setDbLastMoney(rs.getDouble("dbLastMoney"));
				com.setDbLowMoney(rs.getDouble("dbLowMoney"));
				com.setDbSuggMoney(rs.getDouble("dbSuggMoney"));
				com.setTypeId(rs.getInt("TypeId"));
				com.setTypeName(rs.getString("typeName"));
				com.setVcColor(rs.getString("vcColor"));
				com.setVcDw(rs.getString("vcDw"));
				com.setVcFactoryName(rs.getString("vcFactoryName"));
				com.setVcFactoryNo(rs.getString("vcFactoryNo"));
				com.setVcGg(rs.getString("vcGg"));
				com.setVcName(rs.getString("vcName"));
				com.setVcNo(rs.getString("vcNo"));
				com.setVcRemark(rs.getString("vcRemark"));
				com.setDlqty(rs.getString("dlqty"));
				list.add(com);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}

		}
		return list;
	}

	/**
	 * 根据sql得到行数
	 * */
	@Override
	public int getCount(String sql) {

		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		int i = 0;
		try {
			conn = getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			rs.next();
			i = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}

		}
		return i;
	}

	/**
	 * 根据sql获得单个值
	 * */
	@Override
	public Object getSinge(String sql) {

		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		Object obj = null;
		try {
			conn = getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if (rs.next()) {
				obj = rs.getObject(1);
			} else {
				obj = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}

		}
		return obj;
	}

	/**
	 * 根据sql得到list
	 * */
	@Override
	public List getPerson(String sql) {

		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			PsDTO p;
			while (rs.next()) {
				p = new PsDTO();
				p.setCompanyId(rs.getInt("companyId"));

				/* 确保日期格式 被josn解析 */
				p.setDtEntry(rs.getString("dtEntry"));
				p.setDtQuit(rs.getString("dtQuit"));
				p.setIcomminSsion(rs.getInt("iComminSsion"));
				p.setId(rs.getInt("id"));
				p.setIgender(rs.getInt("iGender"));
				p.setIstate(rs.getInt("iState"));
				p.setPositionId(rs.getInt("positionId"));
				p.setPositionName(rs.getString("positionName"));
				p.setVcAddress(rs.getString("vcAddress"));
				p.setVcIdCard(rs.getString("vcIdCard"));
				p.setVcName(rs.getString("vcName"));
				p.setVcNation(rs.getString("vcNation"));
				p.setVcNo(rs.getString("vcNo"));
				p.setVcQuitReason(rs.getString("vcQuitReason"));
				p.setVcRemark(rs.getString("vcRemark"));
				p.setVcTel(rs.getString("vcTel"));
				list.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}

		}
		return list;
	}

	/**
	 * 根据sql得到记录
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap> getDataInfo(String sql) {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		List<HashMap> list = new ArrayList();
		try {
			conn = getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			HashMap map;
			while (rs.next()) {
				/* 逐行读取记录 */
				map = new HashMap();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					map.put(rs.getMetaData().getColumnLabel(i), rs.getObject(rs
							.getMetaData().getColumnLabel(i)));
				}
				list.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}

		}
		return list;
	}

	/**
	 * 查看记录是否存在
	 * */
	@Override
	public Boolean getExist(String sql) {

		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		Boolean obj = false;
		try {
			conn = getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			obj = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}

		}
		return obj;
	}

	/**
	 * 查看记录是否存在
	 * */
	@Override
	public Boolean getExist(String sql, String url, String user, String password) {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		Boolean obj = false;
		try {
			conn = DriverManager.getConnection(url, user, password);
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			obj = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}

		}
		return obj;
	}

	/**
	 * 执行 insert update delete 语句
	 * */
	@Override
	public Integer ExecuteSql(String sql, String url, String user,
			String password) {
		Connection conn = null;
		Statement stm = null;
		Integer rs = 0;
		try {
			conn = DriverManager.getConnection(url, user, password);
			// conn = getConnection();
			stm = conn.createStatement();
			rs = stm.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		return rs;
	}

	@Override
	public Integer ExecuteSql(String sql) {
		Connection conn = null;
		Statement stm = null;
		Integer rs = 0;
		try {
			conn = getConnection();
			stm = conn.createStatement();
			rs = stm.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		return rs;
	}

	/**
	 * 批量执行sql
	 * */
	@Override
	public Integer ExecuteSql(List<String> listsql, String url, String user,
			String password) {
		Connection conn = null;
		Statement stm = null;
		Integer rs = 0;
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			stm = conn.createStatement();
			for (String sql : listsql) {
				stm.addBatch(sql);
				rs++;
			}
			stm.executeBatch();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		return rs;
	}

	@Override
	public Integer ExecuteSql(List<String> listsql) {
		Connection conn = null;
		Statement stm = null;
		Integer rs = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stm = conn.createStatement();
			for (String sql : listsql) {
				stm.addBatch(sql);
				rs++;
			}
			stm.executeBatch();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		return rs;
	}
}
