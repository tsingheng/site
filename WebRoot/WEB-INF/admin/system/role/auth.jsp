<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/components/ztree/zTreeStyle/zTreeStyle.css"/>
<form id="authForm" method="post" action="${ctx}/admin/role!auth.action">
<input id="id" type="hidden" name="id" value="${model.id}"/>
<input id="resources" type="hidden" name="resources" value="${resources}"/>
<table class="form" width="330">
	<tr>
		<td width="80">角色名称：</td>
		<td>${model.roleName}</td>
	</tr>
	<tr>
		<td width="80" valign="top">授权资源：</td>
		<td>
			<ul id="resourceTree" class="ztree"></ul>
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
<script type="text/javascript">
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	var nodes = ${nodes};

	var resourceTree;
</script>
<script type="text/javascript" src="${ctx}/components/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/components/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery.extend.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/admin/role/auth.js"></script>