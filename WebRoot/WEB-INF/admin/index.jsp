<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
  		<script type="text/javascript">
		var ctx = "${ctx}";
		var admin = "${ctx}/admin";
		</script>
	  	<link rel="stylesheet" type="text/css" href="${ctx}/components/easyui/themes/default/easyui.css"/>
	  	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/admin.css"/>
	    <script type="text/javascript" src="${ctx}/components/easyui/jquery-1.8.0.min.js"></script>
	    <script type="text/javascript" src="${ctx}/components/easyui/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="${ctx}/components/easyui/cardview.easyui.bug.js"></script>
		<script type="text/javascript" src="${ctx}/components/jquery.validate.js"></script>
		<script type="text/javascript" src="${ctx}/components/jquery.extend.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/ajax.extend.js"></script>
	    <script type="text/javascript" src="${ctx}/resources/js/common-admin.js"></script>
	    <script type="text/javascript" src="${ctx}/components/kindeditor-4.1.10/kindeditor-all.js"></script>
		<script type="text/javascript" src="${ctx}/components/kindeditor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery.multifile.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/admin/index.js"></script>
  	</head>
  	<body>
		<div data-options="region:'north'" style="height:50px;border: none;">
			<div class="logo">网站后台管理系统</div>
			<div class="right">
				<div class="clock"><span id="date">${date}</span><span id="time">05:02:09</span></div>
				<div class="tools">
					<a href="#" id="close_btn">关闭</a>|<a href="#" id="logout">注销</a>|<a href="#" id="reset_pwd">修改密码</a>
				</div>
			</div>
		</div>
		<div data-options="region:'south'" style="height:24px;padding-top:3px;border: none;">
			<div align="right"><font color="#15428b">${ctx}Developer:宋相恒　Email:tsingheng@163.com　QQ:474345424　Tel:15260816875</font></div>
		</div>
		<div data-options="region:'west',split:true" title="网站后台管理菜单" style="width:200px;">
			<ul id="tree-menu"></ul>
		</div>
		<div data-options="region:'center'" style="border: none;">
			<div class="easyui-tabs" id="main-tabs" data-options="fit:true"></div>
		</div>
		<div id="pwdDlg" class="easyui-window" style="width:300px;" closed="true" title="密码修改" modal="true">
		<form action="" id="pwdForm">
			<table class="form">
				<tr>
					<td width="80">原密码：</td><td><input type="text" id="opwd" class="input1"/></td>
				</tr>
				<tr>
					<td>新密码：</td><td><input type="password" id="npwd" class="input1"/></td>
				</tr>
				<tr>
					<td>密码确认：</td><td><input type="password" id="cpwd" class="input1"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="#" class="easyui-linkbutton btnSubmit">确定</a>
						<a href="#" class="easyui-linkbutton btnCancel">取消</a>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</body>
</html>