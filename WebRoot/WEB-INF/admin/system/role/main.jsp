<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<div class="easyui-layout" fit="true">
<div region="north" border="none">
<ul class="condition">
	<li><span class="label">角色名称：</span><input type="text" id="roleName" class="search"/></li>
	<li><span class="label">角色编码：</span><input type="text" id="roleCode" class="search"/></li>
	<li>
		<a href="#" onclick="onSearchRole();" class="easyui-linkbutton">搜索</a>
		<a href="#" onclick="onClearRole();" class="easyui-linkbutton">清空</a>
	</li>
</ul>
<div class="toolbar">
	<a href="#" onclick="addRole();" class="easyui-linkbutton">添加角色</a>
	<a href="#" onclick="editRole();" class="easyui-linkbutton">编辑角色</a>
	<a href="#" onclick="authRole();" class="easyui-linkbutton">资源授权</a>
	<a href="#" onclick="delRole();" class="easyui-linkbutton">删除角色</a>
</div>
</div>
<div region="center" border="none">
<div class="easyui-menu" id="role-menu">
	<div onclick="addRole();">添加角色</div>
	<div onclick="editRole();">编辑角色</div>
	<div onclick="authRole();">资源授权</div>
	<div onclick="delRole();">删除角色</div>
</div>
<table class="easyui-datagrid" url="${ctx}/admin/role.action" id="role-list" singleSelect="true"
	rownumbers="true" idField="id" fitColumns="true" fit="true" border="false">
	<thead>
		<tr>
			<th field="roleName" width="20%">角色名称</th>
			<th field="roleCode" width="15%">角色编码</th>
			<th field="insertTime" width="15%">添加时间</th>
			<th field="memo" width="50%">角色说明</th>
		</tr>
	</thead>
</table>
<div id="auth-win" class="easyui-window" cache="false" style="width:380px;height:420px;" closed="true">
</div>
<div id="role-win" class="easyui-window" cache="false" style="width:570px;height:325px;" closed="true">
</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/admin/role/main.js"></script>