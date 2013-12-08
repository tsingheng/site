<%@ page language="java" import="com.songxh.common.CommonConstraint" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<c:set var="menu" value="<%=CommonConstraint.RESOURCE_TYPE.MENU.getType()%>"></c:set>
<c:set var="button" value="<%=CommonConstraint.RESOURCE_TYPE.BUTTON.getType()%>"></c:set>
<form id="resourceForm" method="post"
	<c:if test="${empty model.id}">action="${ctx}/admin/resource!add.action"</c:if>
	<c:if test="${not empty model.id}">action="${ctx}/admin/resource!edit.action"</c:if>>
<input type="hidden" name="id" value="${model.id}"/>
<input type="hidden" name="model.id" value="${model.id}"/>
<table class="form">
	<c:if test="${empty model.id}">
	<tr>
		<td>所属资源：</td>
		<td>
			<input id="resourceTree" class="easyui-combotree" name="model.parent" url="${ctx}/admin/resource!menuTree.action" value="${model.parent}"/>
		</td>
	</tr>
	</c:if>
	<tr>
		<td width="80">资源名称：</td>
		<td><input type="text" name="model.resourceName" value="${model.resourceName}" class="input1"/></td>
	</tr>
	<tr>
		<td>资源编码：</td>
		<td><input type="text" name="model.resourceCode" value="${model.resourceCode}" class="input1"/></td>
	</tr>
	<tr>
		<td>资源类型：</td>
		<td>
			<select id="resourceType" class="easyui-combobox" name="model.resourceType">
				<option value="${menu}"<c:if test="${menu eq model.resourceType}"> selected</c:if>>菜单资源</option>
				<option value="${button}"<c:if test="${button eq model.resourceType}"> selected</c:if>>按钮资源</option>
			</select>
		</td>
	</tr>
	<tr id="leafTr"
		<c:if test="${button eq model.resourceType}">style="display:none;"</c:if>>
		<td>叶节点：</td>
		<td>
			<select class="easyui-combobox" name="model.leaf">
				<option value="false"<c:if test="${not model.leaf}"> selected</c:if>>否</option>
				<option value="true"<c:if test="${model.leaf}"> selected</c:if>>是</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>图标编码：</td>
		<td><input type="text" name="model.iconCls" value="${model.iconCls}" class="input1"/></td>
	</tr>
	<tr>
		<td>资源路径：</td>
		<td><input type="text" name="model.resourceUri" value="${model.resourceUri}" class="input1"/></td>
	</tr>
	<tr>
		<td>排序：</td>
		<td>
			<input type="text" name="model.sort" value="${model.sort}" style="width:40px;"
			class="easyui-numberspinner"<c:if test="${not empty model.id}"> min="1" max="${max}"</c:if>/>
		</td>
	</tr>
	<tr>
		<td>资源说明：</td>
		<td>
			<textarea name="model.memo" rows="7" cols="50">${model.memo}</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<a href="#" class="easyui-linkbutton btnSubmit">确定</a>
			<a href="#" class="easyui-linkbutton btnCancel">取消</a>
		</td>
	</tr>
</table>
<script type="text/javascript" src="${ctx}/resources/js/admin/resource/form.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.extend.js"></script>
</form>