<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>后台登陆</title>
		<script type="text/javascript" src="${ctx}/components/easyui/jquery-1.8.0.min.js"></script>
		<link href="${ctx}/resources/css/login.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript">
			function closeWin(){
				var ua=navigator.userAgent
			    var ie=navigator.appName=="Microsoft Internet Explorer"?true:false
			    if(ie){
			        var IEversion=parseFloat(ua.substring(ua.indexOf("MSIE ")+5,ua.indexOf(";",ua.indexOf("MSIE "))))
			        if(IEversion< 5.5){
			            var str  = '<object id=noTipClose classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">'
			            str += '<param name="Command" value="Close"></object>';
			            document.body.insertAdjacentHTML("beforeEnd", str);
			            document.all.noTipClose.Click();
			        }else{
			            window.opener=null;
			            window.open('','_self');
			            window.close();
			        //window.opener.close();
			        }
			    }else{
			        window.close();
			    }
			}
			function openMaxWin(Url,name) {
			    var Height = screen.height -70;
			    var Width = screen.width -12;
			    return window.open(Url,name,"width=" + Width +",height=" + Height +",top=0,left=0,resizable=yes,scrollbars=yes,location=no,titlebar=yes,menubar=no,status=no");
			}
		</script>
	</head>
	<body>
		<div class="logo"></div>
        <div class="workstation"></div>
        <table width="100%" height="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td valign="middle" align="center"><table width="605" height="297" cellpadding="0" cellspacing="0" background="${ctx}/resources/image/login/border.png">
                        <tr>
                            <td width="605" valign="middle" align="center"><table cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="14"><img src="${ctx}/resources/image/login/login_l.gif"/></td>
                                        <td width="540" background="${ctx}/resources/image/login/login_c.gif" align="center">
                                    <form id="loginForm">
                                        <table width="320" height="100%" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td height="60" colspan="2" valign="top" align="center"><img src="${ctx}/resources/image/login/captain.gif"/></td>
                                            </tr>
                                            <tr>
                                                <td height="50" width="93"><span class="textLabel">登录账户：</span><br></td><td><input type="text" id="username" name="username" value="songxh" class="inputText1" /></td>
                                            </tr>
                                            <tr>
                                                <td height="50" width="93"><span class="textLabel">登录密码：</span></td><td><input type="password" id="password" name="password" value="|songxh@19900212|" class="inputText1" /></td>
                                            </tr>
                                            <tr>
                                                <td height="50" width="93"><span class="textLabel">验证码：</span></td>
                                                <td>
                                                	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                                                		<tr>
                                                			<td><input type="text" id="code" onkeydown="onenter(event.keyCode);" name="code" class="inputText2" /></td>
                                                			<td><img alt="点击刷新" onClick="this.src='${ctx}/image?' + new Date().getTime();" src="${ctx}/image" class="checkcode"/></td>
                                                		</tr>
                                                	</table>
                                                </td>
                                            </tr>
                                            <tr align="right">
                                                <td height="50" width="93"><input type="button" id="submitButton" value="登录" class="submitButton" /></td><td><input type="reset" class="resetButton" value="重置"/></td>
                                            </tr>
                                        </table>
                                    </form>
                            </td>
                            <td width="14"><img src="${ctx}/resources/image/login/login_r.gif"/></td>
                        </tr>
                    </table></td>
            </tr>
        </table></td>
			</tr>
		</table>
		<div id="loading" style="display: none">
			<div class="loading-indicator">
				<img src="${ctx}/resources/image/login/extanim32.gif" alt="" width="32" height="32"
					style="margin-right: 8px;" align="middle" />
					正在验证,请稍候......
			</div>
		</div>
		<div id="loading-mask" style="display: none">
        </div>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#username").focus();
				$("#submitButton").click(check);
			});
			function onenter(c){
				if(c == 13){
					check();
				}
			}
			function check(){
				var username = $("#username").val();
					var password = $("#password").val();
					var code = $("#code").val();
					if(username == ""){
						alert("请输入用户名！");
						$("#username").focus();
					}else if(password == ""){
						alert("请输入密码！");
						$("#password").focus();
					}else if(code == ""){
						alert("请输入验证码！");
						$("#code").focus();
					}else{
						$("#loading").css("display", "block");
						$("#loading-mask").css("display", "block");
						$.ajax({
							url : "${ctx}/admin/admin!login.action",
							data : {
								username : username,
								password : password,
								code : code
							},
							success : function(_res){
								_resText = eval("(" + _res + ")");
								if(_resText.success){
									if(window.name != "main_win"){
										var result = openMaxWin("${ctx}/admin", "main_win");
										if(result != null){
											closeWin();
										}else{
											alert("请设置Internet选项允许本站点弹窗！");
										}
									}else{
										document.location.href = "${ctx}/admin";
									}
								}else{
									alert(_resText.msg);
									$('img.checkcode').trigger('click');
									$("#loading").css("display", "none");
									$("#loading-mask").css("display", "none");
								}
							}
						});
					}
			}
		</script>
	</body>
</html>