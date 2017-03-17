<%@ page contentType="text/html; charset=utf-8"%>

<html>
<head>
	<title>打印--数据承载页面</title>
	
	<link rel="stylesheet" type="text/css" href="../../ext/resources/css/ext-all.css">
    <link rel="stylesheet" type="text/css" href="../../css/ext-icon.css">
    <script type="text/javascript" src="../ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="../ext/ext-all.js"></script>
    <script type="text/javascript" src="../ext/ext-lang-zh_CN.js"></script>
    <script src="CreateControl.js" type="text/javascript"></script>
    <script src="GRInstall.js" type="text/javascript"></script>
	
    <style type="text/css">
        html,body {
            margin:0;
            height:100%;
        }
    </style>
    
    <script type="text/javascript">
    	Install_InsertReport();
    </script>
    
</head>
<body style="margin:0">
	
	<script type="text/javascript">
	
		Ext.onReady(function(){
			var v=Install_Detect();
			if(!v) return;
			
			//setTimeout(function(){
				//var vxml1 = "<%= request.getParameter("xml")==null?"":new String(request.getParameter("xml").getBytes("ISO-8859-1"), "utf-8") %>"; /*xml数据*/
				//alert(vxml1);
			//},1000);
			//return;
			var report = "<%=request.getParameter("report")%>";
			var data="<%=request.getParameter("data")%>";
			var vsave="<%=request.getParameter("save")%>"; /*是否直接打印*/
			var vinqu="<%=request.getParameter("inqu")%>"; /*是否到后台查询 数据--直接传入xml数据*/
			var vxml = "<%= request.getParameter("xml")==null?"":new String(request.getParameter("xml").getBytes("ISO-8859-1"), "utf-8") %>"; /*xml数据*/
			//alert(vxml);
			var table = "<%= session.getAttribute("table") %>";
			if(vinqu=='1'){
				CreateReport("Report");
	   			Report.Register("打印数据承载页面", "8PJH495VA61FLI5TG0L4KB2337F1G7AKLD6LNNA9F9T28IKRU6N33P8Z6XX4BUYB5E9NZ6INMD5T8EN47IX63VV7F9BJHB5ZJQQ6MX3J3V12C4XDHU97SXX6X3VA57KCB6");
	   			Report.LoadFromURL("../grf/"+table+"/"+report);
	   			Report.LoadDataFromXML(vxml);
	   			if(vsave=='1'){
	   			    Report.Print(true); /*直接打印报表*/
	   			}else{
	   			    Report.PrintPreview(false); /*弹出 打印预览*/
	   			}
	   			/*如果不是 IE浏览器 就关闭本来的界面*/
	   			if(navigator.userAgent.indexOf("Trident")=='-1'){
	   			    window.close(); /*关闭本页面*/
	   			}
				return;
			}
			
			Ext.Ajax.request({
   			url : data,
   			//params:{ xml : vxml},/*打印无需参数*/
   			success : function(response) {
   				
   				//创建
   			    CreateReport("Report");
   			    //注册
   			    Report.Register("打印数据承载页面", "8PJH495VA61FLI5TG0L4KB2337F1G7AKLD6LNNA9F9T28IKRU6N33P8Z6XX4BUYB5E9NZ6INMD5T8EN47IX63VV7F9BJHB5ZJQQ6MX3J3V12C4XDHU97SXX6X3VA57KCB6");
   			    //报表模板的地址
   			    Report.LoadFromURL("../grf/"+table+"/"+report);
   			    //报表的数据源地址
   			    Report.LoadDataFromXML(response.responseText);
   			    if(vsave=='1'){
   			    	Report.Print(true); /*直接打印报表*/
   			    }else{
   			    	Report.PrintPreview(false); /*弹出 打印预览*/
   			    }
   			    /*如果不是 IE浏览器 就关闭本来的界面*/
   			    if(navigator.userAgent.indexOf("Trident")=='-1'){
   			    	window.close(); /*关闭本页面*/
   			    }
   			}});
		});
	
	</script>
	
</body>
</html>