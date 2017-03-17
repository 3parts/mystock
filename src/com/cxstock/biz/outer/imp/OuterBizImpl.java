package com.cxstock.biz.outer.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import com.cxstock.biz.outer.OuterBiz;
import com.cxstock.biz.outer.dto.TBdUserinfo;
import com.cxstock.dao.OrderDAO;
import com.cxstock.utils.pubutil.Page;
import com.other.myclass.DataName;
import com.other.myclass.MultiDataSource;
import com.other.myclass.PramXml;
import com.other.myclass.PublicClass;

/**
 * @功能：企业申请和企业申请列表管理
 * @作者：RC
 * @创建日期：2015-08-04
 * */
public class OuterBizImpl implements OuterBiz, ApplicationContextAware,
		ApplicationListener {

	private OrderDAO orderDao;

	private ConfigurableApplicationContext app;

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	/**
	 * @功能：保存信息
	 * @作者：RC
	 * @日期：2015-07-25
	 * */
	@Override
	public String saveInfo(TBdUserinfo t) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "INSERT INTO t_bd_userinfo(fname,fopendate,ftodate,fcity,faddress,fman,ftel,fqq,femail,fwx,fbankname,fbanknum,fbank,fweb,fremark,fwrite)";
		sql += " VALUES ('" + t.getFname() + "','"
				+ format.format(t.getFopendate()) + "','"
				+ format.format(t.getFtodate()) + "','" + t.getFcity() + "','"
				+ t.getFaddress() + "','" + t.getFman() + "','" + t.getFtel()
				+ "','" + t.getFqq() + "','" + t.getFemail() + "','"
				+ t.getFwx() + "','" + t.getFbankname() + "','"
				+ t.getFbanknum() + "','" + t.getFbank() + "','" + t.getFweb()
				+ "','" + t.getFremark() + "','" + format.format(new Date())
				+ "')";
		PramXml px = (PramXml) this.app.getBean("keyid");
		DataName dn = (DataName) this.app.getBean("dataname");
		Integer in = orderDao
				.ExecuteSql(
						sql,
						px.getUrl()
								+ dn.getDataname()
								+ "?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull",
						px.getUsername(), px.getPassword());
		if (in > 0) {
			return "提交申请成功，请等待管理员审核...";
		}
		return "提交申请失败，请联系我们...";
	}

	/**
	 * @功能：获得申请的记录
	 * @作者：RC
	 * @日期：2015-07-25
	 * */
	@Override
	public void getInfo(Page page) {
		page.setTotal(orderDao.getCount(PublicClass.getPageCountSql(page)));
		page
				.setListMap(orderDao.getDataInfo(PublicClass
						.getPageLimitSql(page)));
	}

	/**
	 * @功能：修改密码
	 * @作者：RC
	 * @日期：2015-07-25
	 * */
	@Override
	public String savePsd(String ps, String ps1) {
		if (orderDao
				.getExist("select 1 from t_bd_user where username='admin' and password='"
						+ ps + "'")) {
			String sql = "UPDATE t_bd_user SET password='" + ps1
					+ "' WHERE username='admin'";
			Integer in = orderDao.ExecuteSql(sql);
			if (in > 0) {
				return "修改成功";
			} else {
				return "修改失败";
			}
		} else {
			return "密码不正确";
		}
	}

	/**
	 * @功能：审核不通过
	 * @作者：RC
	 * @日期：2015-07-25
	 * */
	@Override
	public String saveNoCheck(Integer fid) {
		String sql = "update t_bd_userinfo set fstatus=-1 where fid=" + fid;
		Integer in = orderDao.ExecuteSql(sql);
		if (in > 0) {
			return "操作成功";
		} else {
			return "操作失败";
		}
	}

	/**
	 * @throws SQLException
	 * @功能：审核通过
	 * @作者：RC
	 * @日期：2015-07-25
	 * */
	@SuppressWarnings("deprecation")
	@Override
	public String saveCheck(Integer fid, String ftable, String path)
			throws Exception {
		/** 查询数据库是否重复 **/
		if (orderDao.getExist("select 1 from t_bd_userinfo where ftable='"
				+ ftable + "'")) {
			return "数据库已存在";
		}

		/** 执行 创建数据库的存储过程 **/
		orderDao.callProcedure("CALL createdata('" + ftable + "')");
		/** 添加在bean容器里面 **/
		PramXml px = (PramXml) this.app.getBean("keyid");

		BeanDefinitionBuilder bdb;
		bdb = BeanDefinitionBuilder
				.rootBeanDefinition("org.apache.commons.dbcp.BasicDataSource");
		bdb.getBeanDefinition().setAttribute("id", ftable);
		bdb.addPropertyValue("driverClassName", "com.mysql.jdbc.Driver");
		bdb
				.addPropertyValue(
						"url",
						px.getUrl()
								+ ftable
								+ "?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull");
		bdb.addPropertyValue("username", px.getUsername());
		bdb.addPropertyValue("password", px.getPassword());
		bdb.addPropertyValue("initialSize", px.getInitialSize());
		bdb.addPropertyValue("maxIdle", px.getMaxIdle());
		bdb.addPropertyValue("minIdle", px.getMinIdle());
		bdb.addPropertyValue("maxActive", px.getMaxActive());
		bdb.addPropertyValue("removeAbandoned", px.getRemoveAbandoned());
		bdb.addPropertyValue("removeAbandonedTimeout", px
				.getRemoveAbandonedTimeout());
		bdb.addPropertyValue("maxWait", px.getMaxWait());
		bdb.addPropertyValue("defaultAutoCommit", px.getDefaultAutoCommit());
		bdb.addPropertyValue("validationQuery", px.getValidationQuery());
		bdb.addPropertyValue("testOnBorrow", px.getTestOnBorrow());
		bdb.addPropertyValue("testOnReturn", px.getTestOnReturn());
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) app
				.getAutowireCapableBeanFactory();

		// 注册bean
		acf.registerBeanDefinition(ftable, bdb.getBeanDefinition());

		/** 加入 到静态变量 **/
		MultiDataSource.targetDataSources.put(ftable, app.getBean(ftable));

		/** 读写xml **/
		SAXReader sr = new SAXReader();// 获取读取xml的对象。
		String filename = path + "/classes/spring-datas.xml";
		Document doc = sr.read(filename);
		Element el_root = doc.getRootElement();
		Element el_bean = el_root.addElement("bean");
		el_bean.setAttributeValue("id", ftable);
		el_bean.setAttributeValue("class",
				"org.apache.commons.dbcp.BasicDataSource");
		Element el_pro = el_bean.addElement("property");
		el_pro.setAttributeValue("name", "url");
		el_pro
				.setAttributeValue(
						"value",
						px.getUrl()
								+ ftable
								+ "?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull");
		Element el_pro2 = el_bean.addElement("property");
		el_pro2.setAttributeValue("name", "username");
		el_pro2.setAttributeValue("value", px.getUsername());
		Element el_pro3 = el_bean.addElement("property");
		el_pro3.setAttributeValue("name", "password");
		el_pro3.setAttributeValue("value", px.getPassword());
		Element el_pro4 = el_bean.addElement("property");
		el_pro4.setAttributeValue("name", "initialSize");
		el_pro4.setAttributeValue("value", px.getInitialSize());
		Element el_pro5 = el_bean.addElement("property");
		el_pro5.setAttributeValue("name", "maxIdle");
		el_pro5.setAttributeValue("value", px.getMaxIdle());
		Element el_pro6 = el_bean.addElement("property");
		el_pro6.setAttributeValue("name", "minIdle");
		el_pro6.setAttributeValue("value", px.getMinIdle());
		Element el_pro7 = el_bean.addElement("property");
		el_pro7.setAttributeValue("name", "maxActive");
		el_pro7.setAttributeValue("value", px.getMaxActive());
		Element el_pro8 = el_bean.addElement("property");
		el_pro8.setAttributeValue("name", "removeAbandoned");
		el_pro8.setAttributeValue("value", px.getRemoveAbandoned());
		Element el_pro9 = el_bean.addElement("property");
		el_pro9.setAttributeValue("name", "removeAbandonedTimeout");
		el_pro9.setAttributeValue("value", px.getRemoveAbandonedTimeout());
		Element el_pro10 = el_bean.addElement("property");
		el_pro10.setAttributeValue("name", "maxWait");
		el_pro10.setAttributeValue("value", px.getMaxWait());
		Element el_pro11 = el_bean.addElement("property");
		el_pro11.setAttributeValue("name", "defaultAutoCommit");
		el_pro11.setAttributeValue("value", px.getDefaultAutoCommit());
		Element el_pro12 = el_bean.addElement("property");
		el_pro12.setAttributeValue("name", "validationQuery");
		el_pro12.setAttributeValue("value", px.getValidationQuery());
		Element el_pro13 = el_bean.addElement("property");
		el_pro13.setAttributeValue("name", "testOnBorrow");
		el_pro13.setAttributeValue("value", px.getTestOnBorrow());
		Element el_pro14 = el_bean.addElement("property");
		el_pro14.setAttributeValue("name", "testOnReturn");
		el_pro14.setAttributeValue("value", px.getTestOnReturn());

		/* 写入文件 */
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(
				new File(filename)), format);
		writer.write(doc);
		writer.close();

		/** 修改企业记录 **/
		String sql = "UPDATE t_bd_userinfo SET ftable='" + ftable
				+ "',fstatus=1 WHERE fid=" + fid;
		Integer in = orderDao.ExecuteSql(sql);
		if (in > 0) {
			List<String> listsql = new ArrayList();
			/** 插入基础信息 **/
			File file = new File(path + "/data_dation.sql");
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), "UTF-8");// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				orderDao
						.ExecuteSql(
								lineTxt,
								px.getUrl()
										+ ftable
										+ "?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull",
								px.getUsername(), px.getPassword());
			}
			read.close();

			/** 插入企业信息 **/
			List<HashMap> listMap = orderDao
					.getDataInfo("select * from t_bd_userinfo where fid=" + fid);
			HashMap hm = listMap.get(0);
			/** 插入 企业 **/
			listsql
					.add("INSERT INTO tbcompany (ID,VC_NUMBER,VC_NAME,DT_BEGEINDATE,DT_ENDDATE,V_STATE,"
							+ "VC_CITY,VC_ADDRESS,VC_CONTACT,VC_TEL,VC_MOBILE,VC_QQ,VC_EMAIL,"
							+ "VC_WEIXIN,VC_ACCOUNTNUM,VC_ACCOUNTNAME,VC_BANK,VC_REMARK,VC_WEB) "
							+ " VALUES("
							+ hm.get("fid")
							+ ",'"
							+ hm.get("fname")
							+ "','"
							+ hm.get("fname")
							+ "','"
							+ hm.get("fopendate")
							+ "','"
							+ hm.get("ftodate")
							+ "','1','"
							+ hm.get("fcity")
							+ "','"
							+ hm.get("faddress")
							+ "','"
							+ hm.get("fman")
							+ "','"
							+ hm.get("ftel")
							+ "','"
							+ hm.get("ftel")
							+ "','"
							+ hm.get("fqq")
							+ "','"
							+ hm.get("femail")
							+ "','"
							+ hm.get("fwx")
							+ "','"
							+ hm.get("fbanknum")
							+ "','"
							+ hm.get("fbankname")
							+ "','"
							+ hm.get("fbank")
							+ "','"
							+ hm.get("fremark")
							+ "','"
							+ hm.get("fweb") + "')");

			/** 插入管理员 **/
			listsql
					.add("INSERT INTO users (userid,logincode,password,username,roleid,state,bz,companyid) "
							+ " VALUES (1,'admin','admin','管理员',1,1,'超级管理员',"
							+ fid + ")");

			/** 插入打印模板 **/
			listsql
					.add("INSERT  INTO `tbtemplate`(`id`,`itype`,`vcNo`,`vcName`,`vcTable`,`vccode`,`vcRemark`,`companyId`) VALUES (1,1,'1001','信封打印','tbsalenout','1.grf','信封打印',"
							+ fid
							+ "),(2,1,'1002','销售单打印','tbsalenout1','2.grf','销售单打印',"
							+ fid
							+ "),(3,1,'1003','进出商品打印','jcsp','3.grf','进出商品打印',"
							+ fid
							+ "),(4,1,'1004','销退单打印','tbsalenback','4.grf','销售单打印',"
							+ fid
							+ "),(5,1,'1005','购退单打印','tbreturn','5.grf','购退单打印',"
							+ fid + ")");

			/** 新建一个企业设计模板的文件夹 并复制文件 **/
			File f = new File(path.substring(0, path.length() - 7) + "/grf/"
					+ ftable);
			if (!f.exists() && !f.isDirectory()) {
				f.mkdir();
			}
			f = new File(path.substring(0, path.length() - 7) + "/grf/grf_bak");
			String[] strs = f.list();
			File temp = null;
			File temp1 = null;
			for (int i = 0; i < strs.length; i++) {
				temp = new File(path.substring(0, path.length() - 7)
						+ "/grf/grf_bak/" + strs[i]);
				temp1 = new File(path.substring(0, path.length() - 7) + "/grf/"
						+ ftable + "/" + strs[i]);
				FileUtils.copyFile(temp, temp1);
			}

			/** 插入用户权限 **/
			listsql
					.add("INSERT INTO tbuserunitrights (id,userid,companyid) VALUE(1,1,"
							+ fid + ")");

			/*** ===========插入基础数据========= ***/

			/** 插入固定账户信息和账目类型 **/
			listsql
					.add("INSERT INTO tbaccount (id,vcNo,vcName,vcRemark,companyId) VALUE "
							+ "  (1,'1001','现金','<span style=\\'color:red\\'>系统必须,勿改,勿删。</span>',"
							+ fid
							+ "),(2,'1002','农业银行','农业银行',"
							+ fid
							+ "),(3,'1003','招商银行','招商银行',"
							+ fid
							+ "),(4,'1004','工商银行','工商银行',"
							+ fid
							+ "),(5,'1005','邮政储蓄','邮政储蓄',"
							+ fid
							+ "),(6,'1006','信用社','信用社'," + fid + ")");
			listsql
					.add("INSERT INTO tbaccounttype (id,vcNo,vcName,vcRemark,itype,companyId) VALUE "
							+ "  (1,'1001','拼包','<span style=\\'color:red\\'>系统必须,勿改,勿删。</span>',0,"
							+ fid
							+ "),(2,'1002','送货','<span style=\\'color:red\\'>系统必须,勿改,勿删。</span>',1,"
							+ fid
							+ "),(3,'1003','销售收入','销售收入',1,"
							+ fid
							+ "),(4,'1004','进货支出','进货支出',0,"
							+ fid
							+ "),(5,'1005','员工工资','员工工资',0,"
							+ fid
							+ "),(6,'1006','存款','存款',1,"
							+ fid
							+ "),(7,'1007','取款','取款',0," + fid + ")");

			/* 计量单位 */
			listsql
					.add("insert  into `tbunit`(`id`,`vcNo`,`vcName`,`vcRemark`,`companyId`) values "
							+ " (1,'1001','米','米',"
							+ fid
							+ "),"
							+ " (2,'1002','匹','匹',"
							+ fid
							+ "),"
							+ " (3,'1003','箱','箱',"
							+ fid
							+ "),"
							+ " (4,'1004','件','件',"
							+ fid
							+ "),"
							+ " (5,'1005','次','次',"
							+ fid
							+ "),"
							+ " (6,'1006','并','并'," + fid + ")");
			/* 结算方式 */
			listsql
					.add("insert  into `tbsettlement`(`id`,`vcNo`,`vcName`,`vcRemark`,`companyId`) values "
							+ " (1,'1001','现金','<span style=\\'color:red\\'>系统必须,勿改,勿删。</span>',"
							+ fid
							+ "), "
							+ " (2,'1002','代收','代收',"
							+ fid
							+ "),"
							+ " (3,'1003','汇款','汇款',"
							+ fid
							+ "),"
							+ " (4,'1004','挂账','挂账'," + fid + ")");
			/* 配送方式 */
			listsql
					.add("insert  into `tblogistics`(`id`,`vcNo`,`vcName`,`vcRemark`,`companyId`) values "
							+ " (1,'1001','快递','快递',"
							+ fid
							+ "),"
							+ "(2,'1002','物流','物流',"
							+ fid
							+ "),"
							+ "(3,'1003','自提','自提',"
							+ fid
							+ "),"
							+ "(4,'1004','专车','专车',"
							+ fid
							+ "),"
							+ "(5,'1005','送货','送货'," + fid + ")");
			/* 物流公司 */
			listsql
					.add("insert  into `tblogisticscompany`(`id`,`vcNo`,`vcName`,`vcRemark`,`companyId`,`vctype`) values "
							+ " (1,'1001','联昊通','联昊通快递',"
							+ fid
							+ ",0),"
							+ "(2,'1002','信丰','信丰快递',"
							+ fid
							+ ",0),"
							+ "(3,'1003','韵达','韵达快递',"
							+ fid
							+ ",0),"
							+ "(5,'1004','顺丰','顺丰快递',"
							+ fid
							+ ",0),"
							+ "(6,'2001','方亮','总部86899238',"
							+ fid
							+ ",1),"
							+ "(7,'2002','颜司机','西樵86897936',"
							+ fid
							+ ",1),"
							+ "(8,'2003','昌盛','86819125',"
							+ fid
							+ ",1),"
							+ "(9,'2004','钟全','合信86813845',"
							+ fid
							+ ",1),"
							+ "(10,'2005','富华','86812737',"
							+ fid
							+ ",1),"
							+ "(11,'2006','银旺','86811769',"
							+ fid
							+ ",1),"
							+ "(12,'2007','仲通','',"
							+ fid
							+ ",1),(13,'2008','兆行','',"
							+ fid
							+ ",1),"
							+ "(14,'2009','金龙','',"
							+ fid
							+ ",1),(15,'2010','龙兴','',"
							+ fid
							+ ",1),"
							+ "(16,'2011','周司机','86848695',"
							+ fid
							+ ",1),"
							+ "(17,'2012','祥和','',"
							+ fid
							+ ",1),"
							+ "(18,'2013','双亨','',"
							+ fid
							+ ",1),"
							+ "(19,'2014','联城','',"
							+ fid
							+ ",1),"
							+ "(20,'2015','樵湛','',"
							+ fid
							+ ",1),"
							+ "(21,'2016','玉樵',''," + fid + ",1)");
			/* 职位管理 */
			listsql
					.add("insert  into `tbposition`(`id`,`vcNo`,`vcName`,`vcRemark`,`companyId`) values "
							+ " (1,'1001','后勤','后勤',"
							+ fid
							+ "),"
							+ "(3,'1002','销售','销售',"
							+ fid
							+ "),"
							+ "(4,'1003','会计','会计',"
							+ fid
							+ "),"
							+ "(5,'1004','主管','',"
							+ fid
							+ "),"
							+ "(6,'1005','店长','',"
							+ fid
							+ "),"
							+ "(7,'1006','总经理','',"
							+ fid
							+ "),"
							+ "(8,'1007','业务经理','外派'," + fid + ")");
			/* 仓库管理 */
			listsql
					.add("insert  into `tbwarehouse`(`id`,`vcNo`,`vcName`,`vcAddress`,`vcRemark`,`companyId`) values (5,'1','16号','方亮16号','',"
							+ fid + ")");
			orderDao
					.ExecuteSql(
							listsql,
							px.getUrl()
									+ ftable
									+ "?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull",
							px.getUsername(), px.getPassword());

			return "企业审核成功,帐套分配已生效,[数据库：" + ftable
					+ ",用户名：admin,密码：admin],请牢记...";
		} else {
			return "意外错误";
		}
	}

	/**
	 * @throws Exception
	 * @功能：禁用企业
	 * @作者：RC
	 * @创建日期：2015-08-04
	 * */
	@Override
	public void saveDisable(Integer fid, String path) throws Exception {
		Object objTable = orderDao
				.getSinge("select ftable from t_bd_userinfo where fid=" + fid);
		if (objTable == null)
			return;
		Boolean b = false;
		/** 删除xml数据 **/
		SAXReader sr = new SAXReader();// 获取读取xml的对象。
		String filename = path + "/classes/spring-datas.xml";
		Document doc = sr.read(filename);
		Element el_root = doc.getRootElement();
		Iterator gameElms = el_root.elementIterator("bean");
		while (gameElms.hasNext()) {
			Element gameElm = (Element) gameElms.next();
			String strName = gameElm.attributeValue("id");
			if (objTable.equals(strName)) {
				if (el_root.remove(gameElm)) {
					System.out.println("删除成功");
					/** 重新保存文件 **/
					OutputFormat format = OutputFormat.createPrettyPrint();
					format.setEncoding("UTF-8");
					XMLWriter writer = new XMLWriter(new FileOutputStream(
							new File(filename)), format);
					writer.write(doc);
					writer.close();
					b = true;
					break;
				}
			}
		}
		/** 更新数据表 **/
		if (b) {
			/** 移除 缓存数据库信息 **/
			if (MultiDataSource.targetDataSources.containsKey(objTable)) {
				MultiDataSource.targetDataSources.remove(objTable);
			}
			/** 移除 注册数据库信息 **/
			DefaultListableBeanFactory acf = (DefaultListableBeanFactory) app
					.getAutowireCapableBeanFactory();
			if (acf.containsBeanDefinition(objTable + "")) {
				acf.removeBeanDefinition(objTable + "");
			}

			String sql = "UPDATE t_bd_userinfo SET fstatus=-2 WHERE fid=" + fid;
			orderDao.ExecuteSql(sql);
			/** 执行删除数据库的存储过程 **/
			// orderDao.callProcedure("CALL dropdata('" + objTable + "')");
		}
	}

	/**
	 * @throws Exception
	 * @功能：启用企业
	 * @作者：RC
	 * @创建日期：2015-08-04
	 * */
	@SuppressWarnings("deprecation")
	@Override
	public void saveEnabled(Integer fid, String path) throws Exception {
		Object objTable = orderDao
				.getSinge("select ftable from t_bd_userinfo where fid=" + fid);
		if (objTable == null)
			return;
		/** 添加在bean容器里面 **/
		PramXml px = (PramXml) this.app.getBean("keyid");

		BeanDefinitionBuilder bdb;
		bdb = BeanDefinitionBuilder
				.rootBeanDefinition("org.apache.commons.dbcp.BasicDataSource");
		bdb.getBeanDefinition().setAttribute("id", objTable);
		bdb.addPropertyValue("driverClassName", "com.mysql.jdbc.Driver");
		bdb
				.addPropertyValue(
						"url",
						px.getUrl()
								+ objTable
								+ "?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull");
		bdb.addPropertyValue("username", px.getUsername());
		bdb.addPropertyValue("password", px.getPassword());
		bdb.addPropertyValue("initialSize", px.getInitialSize());
		bdb.addPropertyValue("maxIdle", px.getMaxIdle());
		bdb.addPropertyValue("minIdle", px.getMinIdle());
		bdb.addPropertyValue("maxActive", px.getMaxActive());
		bdb.addPropertyValue("removeAbandoned", px.getRemoveAbandoned());
		bdb.addPropertyValue("removeAbandonedTimeout", px
				.getRemoveAbandonedTimeout());
		bdb.addPropertyValue("maxWait", px.getMaxWait());
		bdb.addPropertyValue("defaultAutoCommit", px.getDefaultAutoCommit());
		bdb.addPropertyValue("validationQuery", px.getValidationQuery());
		bdb.addPropertyValue("testOnBorrow", px.getTestOnBorrow());
		bdb.addPropertyValue("testOnReturn", px.getTestOnReturn());
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) app
				.getAutowireCapableBeanFactory();

		// 注册bean
		acf.registerBeanDefinition(objTable + "", bdb.getBeanDefinition());

		/** 加入 到静态变量 **/
		MultiDataSource.targetDataSources.put(objTable + "", app
				.getBean(objTable + ""));
		/** 读写xml **/
		SAXReader sr = new SAXReader();// 获取读取xml的对象。
		String filename = path + "/classes/spring-datas.xml";
		Document doc = sr.read(filename);
		Element el_root = doc.getRootElement();
		Element el_bean = el_root.addElement("bean");
		el_bean.setAttributeValue("id", objTable + "");
		el_bean.setAttributeValue("class",
				"org.apache.commons.dbcp.BasicDataSource");
		Element el_pro = el_bean.addElement("property");
		el_pro.setAttributeValue("name", "url");
		el_pro
				.setAttributeValue(
						"value",
						px.getUrl()
								+ objTable
								+ "?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull");
		Element el_pro2 = el_bean.addElement("property");
		el_pro2.setAttributeValue("name", "username");
		el_pro2.setAttributeValue("value", px.getUsername());
		Element el_pro3 = el_bean.addElement("property");
		el_pro3.setAttributeValue("name", "password");
		el_pro3.setAttributeValue("value", px.getPassword());
		Element el_pro4 = el_bean.addElement("property");
		el_pro4.setAttributeValue("name", "initialSize");
		el_pro4.setAttributeValue("value", px.getInitialSize());
		Element el_pro5 = el_bean.addElement("property");
		el_pro5.setAttributeValue("name", "maxIdle");
		el_pro5.setAttributeValue("value", px.getMaxIdle());
		Element el_pro6 = el_bean.addElement("property");
		el_pro6.setAttributeValue("name", "minIdle");
		el_pro6.setAttributeValue("value", px.getMinIdle());
		Element el_pro7 = el_bean.addElement("property");
		el_pro7.setAttributeValue("name", "maxActive");
		el_pro7.setAttributeValue("value", px.getMaxActive());
		Element el_pro8 = el_bean.addElement("property");
		el_pro8.setAttributeValue("name", "removeAbandoned");
		el_pro8.setAttributeValue("value", px.getRemoveAbandoned());
		Element el_pro9 = el_bean.addElement("property");
		el_pro9.setAttributeValue("name", "removeAbandonedTimeout");
		el_pro9.setAttributeValue("value", px.getRemoveAbandonedTimeout());
		Element el_pro10 = el_bean.addElement("property");
		el_pro10.setAttributeValue("name", "maxWait");
		el_pro10.setAttributeValue("value", px.getMaxWait());
		Element el_pro11 = el_bean.addElement("property");
		el_pro11.setAttributeValue("name", "defaultAutoCommit");
		el_pro11.setAttributeValue("value", px.getDefaultAutoCommit());
		Element el_pro12 = el_bean.addElement("property");
		el_pro12.setAttributeValue("name", "validationQuery");
		el_pro12.setAttributeValue("value", px.getValidationQuery());
		Element el_pro13 = el_bean.addElement("property");
		el_pro13.setAttributeValue("name", "testOnBorrow");
		el_pro13.setAttributeValue("value", px.getTestOnBorrow());
		Element el_pro14 = el_bean.addElement("property");
		el_pro14.setAttributeValue("name", "testOnReturn");
		el_pro14.setAttributeValue("value", px.getTestOnReturn());

		/* 写入文件 */
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(
				new File(filename)), format);
		writer.write(doc);
		writer.close();

		/** 改变 企业状态 **/
		String sql = "UPDATE t_bd_userinfo SET fstatus=1 WHERE fid=" + fid;
		orderDao.ExecuteSql(sql);

	}

	/**
	 * @throws Exception
	 * @功能：删除
	 * @作者：RC
	 * @创建日期：2015-08-04
	 * */
	@Override
	public void saveDelete(Integer fid, String path) throws Exception {
		Object objTable = orderDao
				.getSinge("select ftable from t_bd_userinfo where fid=" + fid);
		if (objTable == null || objTable.toString().length() == 0) {
			orderDao.ExecuteSql("delete from t_bd_userinfo where fid=" + fid);
			return;
		}
		/** 执行删除数据库的存储过程 **/
		orderDao.callProcedure("CALL dropdata('" + objTable + "')");
	}

	/**
	 * @功能：为企业延期
	 * @作者：RC
	 * @日期：2015-08-08
	 * */
	@Override
	public void saveDate(Integer fid, Date dt) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Object objTable = orderDao
				.getSinge("select ftable from t_bd_userinfo where fid=" + fid);
		if (objTable == null)
			return;
		PramXml px = (PramXml) this.app.getBean("keyid");

		/* 修改当前记录 */
		String sql = "UPDATE t_bd_userinfo SET ftodate='" + format.format(dt)
				+ "' WHERE fid=" + fid;
		orderDao.ExecuteSql(sql);
		/* 修改 其他数据库记录 */
		sql = "UPDATE tbcompany SET DT_ENDDATE='" + format.format(dt) + "'";
		orderDao
				.ExecuteSql(
						sql,
						px.getUrl()
								+ objTable
								+ "?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull",
						px.getUsername(), px.getPassword());

	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.app = (ConfigurableApplicationContext) arg0;

	}

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// TODO Auto-generated method stub

	}
}
