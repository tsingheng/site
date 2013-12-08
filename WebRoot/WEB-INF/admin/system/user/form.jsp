<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<form id="userForm" method="post"
	<c:if test="${empty model.id}">action="${ctx}/admin/user!add.action"</c:if>
	<c:if test="${not empty model.id}">action="${ctx}/admin/user!edit.action"</c:if>>
<input id="id" type="hidden" name="id" value="${model.id}"/>
<input type="hidden" name="model.id" value="${model.id}"/>
<table class="form" width="530">
	<tr>
		<td width="80">账户名：</td>
		<td><input type="text" name="model.userName" value="${model.userName}" class="input1"/></td>
	</tr>
	<tr>
		<td>用户姓名：</td>
		<td><input type="text" name="model.realName" value="${model.realName}" class="input1"/></td>
	</tr>
	<tr>
		<td>用户角色：</td>
		<td>
			<select name="model.userRole.id" class="easyui-combobox">
				<option value="">未授予角色</option>
				<c:forEach items="${roleList}" var="role">
				<option value="${role.id}"<c:if test="${model.userRole.id eq role.id}"> selected</c:if>>${role.roleName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>手机号码：</td>
		<td><input type="text" name="model.tel" value="${model.tel}" class="input1"/></td>
	</tr>
	<tr>
		<td>QQ：</td>
		<td><input type="text" name="model.qq" value="${model.qq}" class="input1"/></td>
	</tr>
	<tr>
		<td>邮箱：</td>
		<td><input type="text" name="model.email" value="${model.email}" class="input1"/></td>
	</tr>
	<tr>
		<td>备注：</td>
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
<script type="text/javascript" src="${ctx}/resources/js/admin/user/form.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.extend.js"></script>
