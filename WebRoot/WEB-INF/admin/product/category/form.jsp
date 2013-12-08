<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<form id="cateForm" method="post"
	<c:if test="${empty model.id}">action="${ctx}/admin/pro-category!add.action"</c:if>
	<c:if test="${not empty model.id}">action="${ctx}/admin/pro-category!edit.action"</c:if>>
<input id="id" type="hidden" name="id" value="${model.id}"/>
<input type="hidden" name="model.id" value="${model.id}"/>
<table class="form" width="570">
	<tr>
		<td width="120">产品系列名称：</td>
		<td>
			<input name="model.categoryName" value="${model.categoryName}" class="input1"/>
		</td>
	</tr>
	<tr>
		<td>排序：</td>
		<td>
			<input type="text" name="model.sort" value="${model.sort}" style="width:40px;"
				class="easyui-numberspinner" min="1" max="${max}"/>
		</td>
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
<script type="text/javascript" src="${ctx}/resources/js/admin/pro-category/form.js"></script>
