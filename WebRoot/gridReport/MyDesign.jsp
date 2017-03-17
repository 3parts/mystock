<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8"%>
<head>
	<title>模板设计承载页面</title>
	
    <style type="text/css">
        html,body {
            margin:0;
            height:100%;
        }
    </style>
	<script src="CreateControl.js" type="text/javascript"></script>
	<script src="GRUtility.js" type="text/javascript"></script>
	 <script src="GRInstall.js" type="text/javascript"></script>
	
	<script type="text/javascript">

	Install_InsertReport();
	
	var report = "<%=request.getParameter("report")%>";
	var id="<%=request.getParameter("id")%>";
//按AJAX异步方式请求报表模板数据，在响应事件中将模板数据加载进设计器中
function AjaxDesignerLoad(ReportDesigner, ReportLoadUrl) 
{
    var xmlhttp = CreateXMLHttpRequest();

    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            ReportDesigner.Report.LoadFromStr(xmlhttp.responseText);
            ReportDesigner.Reload();
        }
    }
    xmlhttp.open("POST", encodeURI(ReportLoadUrl), true);
    xmlhttp.send();
}

//按AJAX异步方式保存设计的报表模板数据
function AjaxDesignerSave(ReportDesigner, ReportSaveUrl) 
{
	ReportDesigner.Post();
	var strReport = ReportDesigner.Report.SaveToStr();
	
    var xmlhttp = CreateXMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            alert("报表设计已经成功保存!");
            window.close();
        }
    }
    xmlhttp.open("POST", encodeURI(ReportSaveUrl), true);
    xmlhttp.send(strReport);
}

function window_onload() 
{
	
	var v=Install_Detect();
		if(!v) return;
	
	this.setTimeout(function(){AjaxDesignerLoad(ReportDesigner, "user_getRead.do?report="+report)},200);
    //载入要设计的报表模板
}

function OnSaveReport()
{
    //保存设计的报表模板
    AjaxDesignerSave(ReportDesigner, "user_saveReport.do?report="+report+"&id="+id); 

	//忽略掉设计器本身的保存行为
	ReportDesigner.DefaultAction = false; 
}
	</script>
</head>
<body style="margin:0" onload="window_onload()">
    <script type="text/javascript"> 
        //报表模板载入URL、报表数据URL与报表模板保存URL这三个参数均不设置，而是用AJAX与WEB服务器传输数据，在浏览器中再调用报表插件的相关接口传输数据
        
        CreateDesignerEx("100%", "100%", "", "", "", "<param name='OnSaveReport' value='OnSaveReport'>");
    
    </script>
</body>
</html>
