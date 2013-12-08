<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<div class="easyui-layout" fit="true">
<div region="north" border="none">
<ul class="condition">
	<li><span clss="label">账户名：</span><input type="text" id="userName" class="search"/></li>
	<li><span clss="label">用户姓名：</span><input type="text" id="realName" class="search"/></li>
	<li><span clss="label">手机：</span><input type="text" id="tel" class="search"/></li>
	<li><span clss="label">QQ：</span><input type="text" id="qq" class="search"/></li>
	<li><span clss="label">邮箱：</span><input type="text" id="email" class="search"/></li>
	<li>
		<a href="#" onclick="onSearchUser();" class="easyui-linkbutton">搜索</a>
		<a href="#" onclick="onClearUser();" class="easyui-linkbutton">清空</a>
	</li>
</ul>
<div class="toolbar">
	<a href="#" onclick="addUser();" class="easyui-linkbutton">添加用户</a>
	<a href="#" onclick="editUser();" class="easyui-linkbutton">编辑用户</a>
	<a href="#" onclick="delUser();" class="easyui-linkbutton">删除用户</a>
</div>
</div>
<div region="center" border="none">
<div class="easyui-menu" id="user-menu">
	<div onclick="addUser();">添加用户</div>
	<div onclick="editUser();">编辑用户</div>
	<div onclick="delUser();">删除用户</div>
</div>
<table class="easyui-datagrid" url="${ctx}/admin/user.action" id="user-list" singleSelect="true"
	rownumbers="true" idField="id" fitColumns="true" pagination="true" fit="true" border="false">
	<thead>
		<tr>
			<th field="userName" width="10%">账户名</th>
			<th field="realName" width="10%">用户姓名</th>
			<th field="tel" width="10%">手机号</th>
			<th field="qq" width="10%">QQ</th>
			<th field="email" width="10%">邮箱</th>
			<th field="insertTime" width="10%">添加时间</th>
			<th field="userRole" width="10%">用户角色</th>
			<th field="memo" width="30%">备注</th>
		</tr>
	</thead>
</table>
<div id="user-win" class="easyui-window" cache="false" style="width:600px;height:480px;" closed="true">
</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/admin/user/main.js"></script>