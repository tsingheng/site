<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<form id="roleForm" method="post"
	<c:if test="${empty model.id}">action="${ctx}/admin/role!add.action"</c:if>
	<c:if test="${not empty model.id}">action="${ctx}/admin/role!edit.action"</c:if>>
<input id="id" type="hidden" name="id" value="${model.id}"/>
<input type="hidden" name="model.id" value="${model.id}"/>
<table class="form" width="530">
	<tr>
		<td width="80">角色名称：</td>
		<td><input type="text" name="model.roleName" value="${model.roleName}" class="input1"/></td>
	</tr>
	<tr>
		<td>角色编码：</td>
		<td><input type="text" name="model.roleCode" value="${model.roleCode}" class="input1"/></td>
	</tr>
	<tr>
		<td>角色说明：</td>
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
</form>
<script type="text/javascript" src="${ctx}/resources/js/admin/role/form.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.extend.js"></script>
