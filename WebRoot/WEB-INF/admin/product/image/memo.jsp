<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<form id="proImageMemoForm" method="post" action="${ctx}/admin/pro-image!edit.action">
<input id="id" type="hidden" name="id" value="${model.id}"/>
<input type="hidden" name="model.id" value="${model.id}"/>
<table class="form" width="460">
	<tr>
		<td width="90">图片备注：</td>
		<td>
			<textarea name="model.memo" rows="7" cols="45">${model.memo}</textarea>
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
<script type="text/javascript" src="${ctx}/resources/js/admin/pro-image/memo.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.extend.js"></script>
