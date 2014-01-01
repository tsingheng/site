<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<div class="easyui-layout" fit="true">
<div region="north" border="none">
<ul class="condition">
	<li><span class="label">产品系列：</span><input type="text" id="categoryName" class="search"/></li>
	<li>
		<a href="#" onclick="onSearchCate();" class="easyui-linkbutton">搜索</a>
		<a href="#" onclick="onClearCate();" class="easyui-linkbutton">清空</a>
	</li>
</ul>
<div class="toolbar">
	<a href="#" onclick="addCate();" class="easyui-linkbutton">添加产品系列</a>
	<a href="#" onclick="editCate();" class="easyui-linkbutton">编辑产品系列</a>
	<a href="#" onclick="delCate();" class="easyui-linkbutton">删除产品系列</a>
</div>
</div>
<div region="center" border="none">
<div class="easyui-menu" id="cate-menu">
	<div onclick="addCate();">添加产品系列</div>
	<div onclick="editCate();">编辑产品系列</div>
	<div onclick="delCate();">删除产品系列</div>
	<div id="cate-sort">
		<span>移动顺序</span>
		<div>
			<div onclick="sortCate('first');">置顶</div>
			<div onclick="sortCate('up');">上移</div>
			<div onclick="sortCate('down');">下移</div>
			<div onclick="sortCate('last');">置尾</div>
		</div>
	</div>
</div>
<table class="easyui-treegrid" url="${ctx}/admin/pro-category.action" id="cate-list" singleSelect="true"
	rownumbers="true" idField="id" fitColumns="true" treeField="categoryName" pagination="false" fit="true" border="false">
	<thead>
		<tr>
			<th field="categoryName" width="25%">产品系列名称</th>
			<th field="sort" width="3%">排序</th>
			<th field="creater" width="15%">添加者</th>
			<th field="insertTime" width="17%">添加时间</th>
			<th field="memo" width="40%">备注</th>
		</tr>
	</thead>
</table>
<div id="cate-win" class="easyui-window" cache="false" style="width:610px;height:330px;" closed="true">
</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/admin/pro-category/main.js"></script>