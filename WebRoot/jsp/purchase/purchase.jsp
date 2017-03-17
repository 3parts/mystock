<%@ page language="java" pageEncoding="UTF-8"%>
<html>
  <head>
  	<title>客户管理</title>
    <link rel="stylesheet" type="text/css" href="../../ext/resources/css/ext-all.css">
    <link rel="stylesheet" type="text/css" href="../../css/ext-icon.css">
    <script type="text/javascript" src="../../ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="../../ext/ext-all.js"></script>
    <script type="text/javascript" src="../../ext/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../ext/userExt/Ext.ux.js"></script>
	<script type="text/javascript" src="../../ext/ux/FileUploadField.js"></script>
    <script type="text/javascript" src="purchase.js"></script>
    
    <script type="text/javascript">
        //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
        function forbidBackSpace(e) {
        	//alert(e.keyCode);
            var ev = e || window.event; //获取event对象 
            var obj = ev.target || ev.srcElement; //获取事件源 
            var t = obj.type || obj.getAttribute('type'); //获取事件源类型 
            //获取作为判断条件的事件类型 
            var vReadOnly = obj.readOnly;
            var vDisabled = obj.disabled;
            //处理undefined值情况 
            vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
            vDisabled = (vDisabled == undefined) ? true : vDisabled;
            //当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
            //并且readOnly属性为true或disabled属性为true的，则退格键失效 
            var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
            //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
            var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
            //判断 
            if (flag2 || flag1) return false;
        }
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
	</script>
    
  </head>
  <body>
  </body>
</html>