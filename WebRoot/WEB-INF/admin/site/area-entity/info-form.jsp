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
				<li><span class="label">文章标题：</span><input type="text" id="entity-infoTitle" class="search"/></li>
				<li>
					<a href="#" onclick="onSearchEntityInfo();" class="easyui-linkbutton">搜索</a>
					<a href="#" onclick="onClearEntityInfo();" class="easyui-linkbutton">清空</a>
				</li>
			</ul>
		</div>
		<div region="center" style="border:none;">
			<div class="easyui-menu" id="entity-info-menu">
				<div onclick="addEntityInfo();">在首页显示</div>
			</div>
			<table class="easyui-datagrid" singleSelect="true" rownumbers="true" idField="id" fitColumns="true"
				pagination="true" id="entity-info-list" fit="true" border="false" rowStyler="entityStyler">
				<thead>
					<tr>
						<th field="title" width="26%">文章标题</th>
						<th field="published" width="8%" formatter="yesOrNoFormatter" align="center">发布</th>
						<th field="viewTimes" width="11%">浏览次数</th>
						<th field="creater" width="13%">添加者</th>
						<th field="insertTime" width="12%">添加时间</th>
						<th field="memo" width="30%">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/admin/area-entity/info-form.js"></script>