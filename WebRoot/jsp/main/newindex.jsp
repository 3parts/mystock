<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.cxstock.biz.power.dto.UserDTO"%>
<%
  UserDTO userInfo=(UserDTO)session.getAttribute("userInfo");
%> 
<html>
  <head>
  	<title>奇计云商管理系统</title>
    <script type="text/javascript">
	     window.log_id="<%=userInfo.getUserid()%>";
	     window.log_name="<%=userInfo.getUsername()%>";
	     window.log_companyname="<%=userInfo.getCompanyname() %>";
	     window.cookies_xsps=0; /*销售配送方式*/
	     window.cookies_xsjs=0; /*销售结算方式*/
	     window.cookies_xsydh='运单号';/*销售运单号*/
	</script>
    <link rel="stylesheet" type="text/css" href="../../ext/resources/css/ext-all.css">
    <link rel="stylesheet" type="text/css" href="../../css/ext-icon.css">
    <script type="text/javascript" src="../../ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="../../ext/ext-all.js"></script>
    <script type="text/javascript" src="../../ext/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../ext/userExt/Ext.ux.js"></script>
    <script type="text/javascript" src="../../js/Clock.js"></script>
    <script type="text/javascript" src="newindex.js"></script>
    <script type="text/javascript" src="App.js"></script>
    

    
  </head>
  
  <body>
  </body>
</html>