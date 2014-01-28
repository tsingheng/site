<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<form id="sitePropertyForm" method="post" enctype="multipart/form-data" action="${ctx}/admin/site-property!edit.action">
<table class="form" width="800">
	<tr>
		<td width="100">${propertyMap.skype.propertyName}：</td>
		<td>
			<input type="text" class="input1" name="site_prop_${propertyMap.skype.propertyCode}" value="${propertyMap.skype.propertyValue}"/>
		</td>
	</tr>
	<tr>
		<td>${propertyMap.email.propertyName}：</td>
		<td>
			<input type="text" class="input1" name="site_prop_${propertyMap.email.propertyCode}" value="${propertyMap.email.propertyValue}"/>
		</td>
	</tr>
	<tr>
		<td>${propertyMap.msn.propertyName}：</td>
		<td>
			<input type="text" class="input1" name="site_prop_${propertyMap.msn.propertyCode}" value="${propertyMap.msn.propertyValue}"/>
		</td>
	</tr>
	<tr>
		<td>${propertyMap.site_logo.propertyName}：</td>
		<td>
			<input type="file" name="file" class="file"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img alt="网站LOGO" width="119" height="85" id="site-logo-pre" src="${ctx}${propertyMap.site_logo.propertyValue}"/>
		</td>
	</tr>
	<tr>
		<td>${propertyMap.site_title.propertyName}：</td>
		<td>
			<textarea rows="4" style="width:670px;" name="site_prop_${propertyMap.site_title.propertyCode}">${propertyMap.site_title.propertyValue}</textarea>
		</td>
	</tr>
	<tr>
		<td>网站关键字：</td>
		<td>
			<textarea rows="4" style="width:670px;" name="site_prop_${propertyMap.keywords.propertyCode}">${propertyMap.keywords.propertyValue}</textarea>
		</td>
	</tr>
	<tr>
		<td>网站描述：</td>
		<td>
			<textarea rows="4" style="width:670px;" name="site_prop_${propertyMap.description.propertyCode}">${propertyMap.description.propertyValue}</textarea>
		</td>
	</tr>
	<tr>
		<td>${propertyMap.site_head.propertyName}：</td>
		<td>
			<textarea rows="4" style="width:670px;" id="head_editor" name="site_prop_${propertyMap.site_head.propertyCode}">${propertyMap.site_head.propertyValue}</textarea>
		</td>
	</tr>
	<tr>
		<td>${propertyMap.site_foot.propertyName}：</td>
		<td>
			<textarea rows="4" style="width:670px;" id="foot_editor" name="site_prop_${propertyMap.site_foot.propertyCode}">${propertyMap.site_foot.propertyValue}</textarea>
		</td>
	</tr>
	<tr>
		<td>${propertyMap.site_contact.propertyName}：</td>
		<td>
			<textarea rows="4" style="width:670px;" id="contact_editor" name="site_prop_${propertyMap.site_contact.propertyCode}">${propertyMap.site_contact.propertyValue}</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<a href="#" class="easyui-linkbutton btnSubmit">确定</a>
			<a href="#" class="easyui-linkbutton btnBack">还原</a>
			<a href="#" class="easyui-linkbutton btnCancel">清空</a>
		</td>
	</tr>
</table>
</form>
<script type="text/javascript">
var headEditor, footEditor, contactEditor;
</script>
<script type="text/javascript" src="${ctx}/resources/js/admin/site-property/edit.js"></script>