<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<div class="easyui-layout" fit="true">
<div region="north" border="none">
<div class="toolbar">
	<a href="#" onclick="addArea();" class="easyui-linkbutton">添加区域</a>
	<a href="#" onclick="editArea();" class="easyui-linkbutton">编辑区域</a>
	<a href="#" onclick="delArea();" class="easyui-linkbutton">删除区域</a>
</div>
</div>
<div region="center" border="none">
<div class="easyui-menu" id="area-menu">
	<div onclick="addArea();">添加区域</div>
	<div onclick="editArea();">编辑区域</div>
	<div onclick="delArea();">删除区域</div>
</div>
<table class="easyui-datagrid" url="${ctx}/admin/index-area.action" id="area-list" singleSelect="true"
	rownumbers="true" idField="id" fitColumns="true" pagination="true" fit="true" border="false">
	<thead>
		<tr>
			<th field="areaName" width="20%">区域名称</th>
			<th field="areaCode" width="15%">区域编码</th>
			<th field="contentTypeText" width="15%">内容类型</th>
			<th field="viewTypeText" width="15%">显示类型</th>
			<th field="orientation" width="15%">排列方向</th>
			<th field="memo" width="20%">备注</th>
		</tr>
	</thead>
</table>
<div id="area-win" class="easyui-window" cache="false" style="width:610px;height:430px;" closed="true">
</div>
</div>
<div region="south" style="height:400px;" style="border-left:none;border-right:none;border-bottom:none;">
	<div class="easyui-layout" fit="true">
		<div region="north" border="none">
			<div class="toolbar">
				<a href="#" onclick="addAreaEntity();" class="easyui-linkbutton">添加记录</a>
				<a href="#" onclick="delAreaEntity();" class="easyui-linkbutton">删除记录</a>
			</div>
		</div>
		<div region="center" border="none">
			<div class="easyui-menu" id="area-entity-menu">
				<div onclick="addAreaEntity();">变更记录</div>
				<div onclick="delAreaEntity();">删除记录</div>
			</div>
			<table class="easyui-datagrid" url="${ctx}/admin/area-entity.action" id="area-entity-grid-list" singleSelect="true"
				rownumbers="true" idField="id" fitColumns="true" pagination="true" fit="true" border="false">
				<thead>
					<tr>
						<th field="sort" width="5%">显示顺序</th>
						<th field="type" width="20%">类别</th>
						<th field="title" width="30%">标题</th>
						<th field="creater" width="12%">添加者</th>
						<th field="insertTime" width="13%">添加时间</th>
						<th field="memo" width="20%">备注</th>
					</tr>
				</thead>
			</table>
			<table class="easyui-cardview" singleSelect="true" idField="id" colNums="2" pagination="true" id="area-entity-card-list" 
				fit="true" border="false" url="${ctx}/admin/area-entity.action">
			</table>
			<div id="area-entity-win" class="easyui-window" cache="false" style="width:910px;height:630px;" closed="true"></div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
function showList(type){
	if(type == '2'){
		$('#area-entity-card-list').cardview('getPanel').hide();
		$('#area-entity-grid-list').datagrid('getPanel').show();
	}else{
		$('#area-entity-grid-list').datagrid('getPanel').hide();
		$('#area-entity-card-list').cardview('getPanel').show();
	}
}
</script>
<script type="text/javascript" src="${ctx}/resources/js/admin/index-area/main.js"></script>