<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<form id="proImageForm" method="post" action="${ctx}/admin/pro-image!add.action" enctype="multipart/form-data">
<input type="hidden" name="model.product" value="${param.product}"/>
<table class="form" width="420">
	<tr>
		<td width="80" valign="top" style="padding-top:10px;">上传文件：</td>
		<td>
			<input type="file" name="files" class="multifile file"/>
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
<script type="text/javascript" src="${ctx}/resources/js/admin/pro-image/upload.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.extend.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.multifile.js"></script>