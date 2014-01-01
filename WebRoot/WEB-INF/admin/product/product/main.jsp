<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<div class="easyui-layout" fit="true">
	<div region="west" style="width:200px; border:none;">
		<ul id="cate-tree-list" class="easyui-tree">
			<c:forEach items="${list}" var="category">
			<li id="${category.id}"
				<c:if test="${(not empty category.children) && (fn:length(category.children) gt 0)}">
				state="closed"
				</c:if>
				>
				<span>${category.categoryName}</span>
				<c:if test="${(not empty category.children) && (fn:length(category.children) gt 0)}">
				<ul>
					<c:forEach items="${category.children}" var="child">
					<li id="${child.id}">${child.categoryName}</li>
					</c:forEach>
				</ul>
				</c:if>
			</li>
			</c:forEach>
		</ul>
	</div>
	<div region="center" style="border-top:none; border-right:none; border-bottom:none;">
		<div class="easyui-layout" fit="true">
		<div region="north" style="border:none;">
			<ul class="condition">
				<li><span class="label">产品名称：</span><input type="text" id="productName" class="search"/></li>
				<li>
					<a href="#" onclick="onSearchPro();" class="easyui-linkbutton">搜索</a>
					<a href="#" onclick="onClearPro();" class="easyui-linkbutton">清空</a>
				</li>
			</ul>
			<div class="toolbar">
				<a href="#" onclick="addPro();" class="easyui-linkbutton">添加产品</a>
				<a href="#" onclick="editPro();" class="easyui-linkbutton">编辑产品</a>
				<a href="#" onclick="addProImage();" class="easyui-linkbutton">添加图片</a>
				<a href="#" onclick="delPro();" class="easyui-linkbutton">删除产品</a>
			</div>
		</div>
		<div region="center" style="border:none;">
			<div class="easyui-menu" id="pro-menu">
				<div onclick="addPro();">添加产品</div>
				<div onclick="editPro();">编辑产品</div>
				<div onclick="addProImage();">添加图片</div>
				<div id="publish-pro" onclick="publishPro();">发布产品</div>
				<div id="cancel-pro" onclick="cancelPro();">取消发布</div>
				<div onclick="delPro();">删除产品</div>
				<div id="pro-sort">
					<span>移动顺序</span>
					<div>
						<div onclick="sortPro('first');">置顶</div>
						<div onclick="sortPro('up');">上移</div>
						<div onclick="sortPro('down');">下移</div>
						<div onclick="sortPro('last');">置尾</div>
					</div>
				</div>
			</div>
			<table class="easyui-datagrid" singleSelect="true" rownumbers="true" idField="id" fitColumns="true"
				pagination="true" id="pro-list" fit="true" border="false">
				<thead>
					<tr>
						<th field="productName" width="13%">产品名称</th>
						<th field="sort" width="3%">排序</th>
						<th field="creater" width="10%">添加者</th>
						<th field="insertTime" width="12%">添加时间</th>
						<th field="viewTimes" width="7%">浏览次数</th>
						<th field="price" width="5%">价格</th>
						<th field="tags" width="12%">标签</th>
						<th field="published" width="8%" formatter="publishedFormatter" align="center">发布</th>
						<th field="memo" width="30%">备注</th>
					</tr>
				</thead>
			</table>
			<div id="pro-win" class="easyui-window" cache="false" style="width:960px;height:680px;" closed="true"></div>
		</div>
		<div region="south" style="height:201px; border-left:none;border-right:none;border-bottom:none;">
			<div class="easyui-layout" fit="true">
			<div region="west" style="width:200px;border:none;" id="image-default"></div>
			<div region="center" style="border-top:none;border-right:none;border-bottom:none;">
				<div class="easyui-layout" fit="true">
				<div region="north" style="border:none;">
					<div class="toolbar">
						<a href="#" onclick="editProImage();" class="easyui-linkbutton">设置备注</a>
						<a href="#" onclick="delProImage();" class="easyui-linkbutton">删除图片</a>
						<a href="#" onclick="test()" class="easyui-linkbutton">测试</a>
					</div>
				</div>
				<div region="center" style="border:none;">
					<div class="easyui-menu" id="pro-image-menu">
						<div onclick="editProImage();">设置备注</div>
						<div onclick="delProImage();">删除图片</div>
						<div>
							<span>移动顺序</span>
							<div>
								<div onclick="sortProImage('first');">置顶</div>
								<div onclick="sortProImage('up');">上移</div>
								<div onclick="sortProImage('down');">下移</div>
								<div onclick="sortProImage('last');">置尾</div>
							</div>
						</div>
					</div>
					<table class="easyui-datagrid" id="pro-image-list" fit="true" singleSelect="true" rownumbers="true" 
						idField="id" fitColumns="true" border="false">
						<thead>
							<tr>
								<th field="sort" width="5%">排序</th>
								<th field="originalName" width="25%">原文件名</th>
								<th field="creater" width="15%">添加者</th>
								<th field="insertTime" width="15%">添加时间</th>
								<th field="memo" width="40%">备注</th>
							</tr>
						</thead>
					</table>
					<div id="pro-image-win" class="easyui-window" cache="false" style="width:510px;height:250px;" closed="true"></div>
					<div id="pro-image-memo-win" class="easyui-window" cache="false" style="width:510px;height:250px;" closed="true"></div>
				</div>
				</div>
			</div>
			</div>
		</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/admin/product/main.js"></script>