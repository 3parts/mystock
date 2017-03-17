<%@ page contentType="text/html; charset=UTF-8" %>
<%
	//session.removeAttribute("userInfo");
%>
<html>
<head>
	<title>奇计云商管理系统</title>
	<script type="text/javascript">
		/**
		 * 功能：获取url的参数
		 * */
		function GetRequest(){
			var url = location.search;
			var theRequest = new Object();
			if (url.indexOf("?") != -1) { 
				var str = url.substr(1); 
				strs = str.split("&"); 
				for(var i = 0; i < strs.length; i ++) { 
					theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
				}
			}
			return theRequest; 
		}
		
		
		function check(){
			var logincode = document.getElementById("logincode").value;
			var password = document.getElementById("password").value;
			var table = document.getElementById("table").value;
			var error = document.getElementById("error");
			error.innerHTML="";
			if(logincode==""){
				error.innerHTML="用户名不能为空！";
				return false;
			}
			if(password==""){
				error.innerHTML="密码不能为空！";
				return false;
			}
			/**写入cookies**/
			var vcheck = document.getElementById("okuser");
			var exp = new Date();
			if(vcheck.checked){
				exp.setTime(exp.getTime() + 30*24*60*60*1000*1);
				document.cookie = "usercode="+ logincode + ";expires=" + exp.toGMTString();
				document.cookie = "table="+table+";expires="+ exp.toGMTString();
			}else{
				exp.setTime(exp.getTime() -1);
				document.cookie = "usercode="+ logincode + ";expires=" + exp.toGMTString();
				document.cookie = "table="+table+";expires="+ exp.toGMTString();
			}
			return true;
		}
		
		function getCookie(name){ 
		    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		    //alert(unescape(arr[2]));
		    if(arr=document.cookie.match(reg))
		        return unescape(arr[2]);
		    else 
		        return null;
		}
		window.onload=function(){
			document.getElementById("logincode").value= getCookie("usercode");
			document.getElementById("table").value=getCookie("table");
		}
		
	</script>
	<link rel="stylesheet" href="loginstyle/css/user_login.css" type="text/css" />
</head>

<body>
<table width="100%" height="100%" cellpadding="0" cellspacing="0">
  <tr>
    <td><table align="center" height="300px" class="ta2" >
        <tr>
          <td align="center"><img src="loginstyle/images/logo.png" /></td>
        </tr>
        <tr>
          <td height="5"></td>
        </tr>
        <tr>
          <td class="white"><form action="user_login.do" method="post"><table align="center">
              <tr>
                <td colspan="2" class="text2" style="font-family:'微软雅黑';">企业ID：</td>
                <td colspan="2"><input id="table" name="table" type="text" onkeyup="value=value.replace(/[^a-z]/g,'')" class="text1"/></td>
              </tr>
              <tr>
                <td height="10"></td>
              </tr>
              <tr>
                <td colspan="2" class="text2" style="font-family:'微软雅黑';">用户名：</td>
                <td colspan="2"><input id="logincode" name="logincode" type="text" class="text1"/></td>
              </tr>
              <tr>
                <td height="10"></td>
              </tr>
              <tr>
                <td colspan="2" class="text2" style="font-family:'微软雅黑';">密&nbsp;&nbsp;&nbsp;码：</td>
                <td colspan="2"><input id="password" name="password" type="password" class="text1"/></td>
              </tr>
              <tr>
                <td height="10"></td>
              </tr>
              <tr>
                <td><input name="" type="checkbox" id="okuser" value="" class="ch1" checked="checked" /></td>
                <td class="text3" style="font-family:'微软雅黑';">记住用户</td>
                <td><input name="" type="reset" value="重置" style="font-family:'微软雅黑';"  class="text4"/></td>
                <td><input name="submit" type="submit" value="登录" onclick="return check()" style="font-family:'微软雅黑';"  class="text4"/></td>
              </tr>
            </table>
            <!-- 错误提示 -->
            <table width="100%" cellpadding="0" cellspacing="0" style="margin-top:8px;">
          	<tr>
          		<td align="center" style="height:25"><font id="error" color="red">${error}&nbsp;</font></td>
          	</tr>
        	</table>
            </form></td>
        </tr>
      </table>
      </td>
  </tr>
</table>
</body>
</html>