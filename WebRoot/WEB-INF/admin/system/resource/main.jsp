<%@ page language="java" import="com.songxh.common.CommonConstraint" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<c:set var="menu" value="<%=CommonConstraint.RESOURCE_TYPE.MENU.getType()%>"></c:set>
<c:set var="button" value="<%=CommonConstraint.RESOURCE_TYPE.BUTTON.getType()%>"></c:set>
<script type="text/javascript">
var buttonType = ${button};
var menuType = ${menu};
</script>
<div class="easyui-layout" fit="true">
<div region="north" border="none">
<div class="toolbar">
	<a href="#" onclick="addResource();" class="easyui-linkbutton">添加资源</a>
	<a href="#" onclick="editResource();" class="easyui-linkbutton">编辑资源</a>
	<a href="#" onclick="delResource();" class="easyui-linkbutton">删除资源</a>
</div>
</div>
<div region="center" border="none">
<div class="easyui-menu" id="resource-menu">
	<div onclick="addResource();">添加资源</div>
	<div onclick="editResource();">编辑资源</div>
	<div onclick="delResource();">删除资源</div>
	<div>
		<span>移动顺序</span>
		<div>
			<div onclick="sortResource('first');">置顶</div>
			<div onclick="sortResource('up');">上移</div>
			<div onclick="sortResource('down');">下移</div>
			<div onclick="sortResource('last');">置尾</div>
		</div>
	</div>
	<div onclick="expandResource();">展开</div>
	<div onclick="collapseResource();">关闭</div>
</div>
<table class="easyui-treegrid" url="${ctx}/admin/resource.action" id="resource-list" border="false"
	rownumbers="true" idField="id" treeField="resourceName" fitColumns="true" fit="true">
	<thead>
		<tr>
			<th field="resourceName" width="15%">资源名称</th>
			<th field="sort" width="3%">排序</th>
			<th field="resourceCode" width="10%">资源编码</th>
			<th field="resourceType" formatter="typeFormatter" width="8%">资源类型</th>
			<th field="iconCls" width="10%">图标编码</th>
			<th field="resourceUri" width="15%">资源路径</th>
			<th field="memo" width="30%">资源说明</th>
		</tr>
	</thead>
</table>
<div id="resource-win" class="easyui-window" cache="false" style="width:535px;height:525px;" closed="true">
</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/admin/resource/main.js"></script>