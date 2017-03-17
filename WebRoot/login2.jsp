<%@ page contentType="text/html; charset=UTF-8" %>
<%
	session.removeAttribute("userInfo");
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
	<style type="text/css">
		* { margin:0 auto; padding:0; border:0;font-size:12px;}
		body { background:#0462A5; font:12px "宋体"; color:#004C7E;}
		input { border:1px solid #004C7E;}
		.main { background:url(img/login/bg.jpg) repeat-x;}AX
		.login { background:#DDF1FE; width:468px; height:262px; border:1px solid #000;}
		.top { background:url(img/login/login_bg.jpg) repeat-x; width:464px; height:113px; border:1px solid #2376B1; margin-top:1px;}
		.logo { background:url(img/login/logo.gif) no-repeat; width:150px; height:42px; float:left; margin:29px 0 0 14px;}
		.lable { background:url(img/login/lable_1.jpg) no-repeat; width:157px; height:32px; float:right; margin:81px 31px 0 0;}
		.submit { background:url(img/login/submit.gif) no-repeat; cursor:hand; width:71px; height:24px; border:0;} 
		.reset { background:url(img/login/reset.gif) no-repeat; cursor:hand; width:71px; height:24px; border:0;} 
	</style>
</head>

<body>
<div>
<table width="100%" height="100%" class="main" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
      <div class="login">
        <div class="top">
          <div class="logo"></div>
          <div class="lable"></div>
        </div>
        <table width="466" cellpadding="0" cellspacing="0">
          <tr>
            <td style="padding-top:30px;">
		    <form action="user_login.do" method="post">
              <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                  <td align="right" height="27" width="80">用户名：</td>
                  <td align="left" width="161">
                    <input tabIndex="1" type="text" id="logincode" name="logincode" style="width:150px;height:20px" />
                  </td>
                  <td align="center" height="29">
                    <input tabIndex="3" name="submit" type="submit" value="" onclick="return check()" class="submit" />
                  </td>
                </tr>
                <tr>
                  <td align="right" height="27">密码：</td>
                  <td align="left" width="161">
                    <input tabIndex="2" type="password" id="password" name="password" style="width:150px;height:20px" />
                  </td>
                  <td align="center" height="29">
                    <input name="reset" tabIndex="4" type="reset" value="" class="reset" />
                  </td>
                </tr>
                <tr>
                	<td height="27" align="right">数据库：</td>
                	<td align="left" width="161"><input type="text" id="table" name="table" style="width:150px;height:20px" /></td>
                	<td align="right" height="29"></td>
                </tr>
                <tr>
                	<td height="27"></td>
                	<td align="left" width="161"><input tabIndex="5" type="checkbox" id="okuser" checked="checked" />记住用户</td>
                	<td align="right" height="29"></td>
                </tr>
              </table>
	        </form>
            </td>
          </tr>
        </table>
        <table width="100%" cellpadding="0" cellspacing="0" style="margin-top:8px;">
          <tr>
          	<td align="center" style="height:25"><font id="error" color="red">${error}&nbsp;</font></td>
          </tr>
          <tr>
            <td align="center">MyStock 2013</td>
          </tr>
        </table>
      </div>
      <!--login -->
    </td>
  </tr>
</table>
</body>
</html>