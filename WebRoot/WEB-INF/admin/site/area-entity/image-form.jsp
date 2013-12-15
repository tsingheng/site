<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<script type="text/javascript">var nodes = [${nodes}];</script>
<div class="easyui-layout" fit="true">
	<div region="west" style="width:200px; border:none;">
		<ul id="entity-cate-tree-list" class="easyui-tree">
			<c:forEach items="${tree}" var="category">
			<li id="${category.id}">${category.text}</li>
			</c:forEach>
		</ul>
	</div>
	<div region="center" style="border-top:none; border-right:none; border-bottom:none;">
		<div class="easyui-layout" fit="true">
		<div region="north" style="border:none;">
			<ul class="condition">
				<li><span class="label">产品名称：</span><input type="text" id="entity-productName" class="search"/></li>
				<li>
					<a href="#" onclick="onSearchEntityPro();" class="easyui-linkbutton">搜索</a>
					<a href="#" onclick="onClearEntityPro();" class="easyui-linkbutton">清空</a>
				</li>
			</ul>
		</div>
		<div region="center" style="border:none;">
			<div class="easyui-menu" id="entity-pro-menu">
				<div onclick="addEntityPro();">在首页显示</div>
			</div>
			<table class="easyui-datagrid" singleSelect="true" rownumbers="true" idField="id" fitColumns="true"
				pagination="true" id="entity-pro-list" fit="true" border="false" rowStyler="entityStyler">
				<thead>
					<tr>
						<th field="productName" width="20%">产品名称</th>
						<th field="published" width="10%" formatter="yesOrNoFormatter" align="center">是否发布</th>
						<th field="creater" width="10%">添加者</th>
						<th field="insertTime" width="20%">添加时间</th>
						<th field="viewTimes" width="10%">浏览次数</th>
						<th field="memo" width="30%">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/admin/area-entity/image-form.js"></script>