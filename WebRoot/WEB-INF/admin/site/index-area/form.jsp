<%@ page language="java" pageEncoding="UTF-8" import="com.songxh.common.IndexAreaTypes"%>
<%@ include file="/components/common.jsp" %>
<c:set var="contentTypeEnums" value="<%=IndexAreaTypes.ContentTypeEnums.values()%>"></c:set>
<c:set var="viewTypeEnums" value="<%=IndexAreaTypes.ViewTypeEnums.values()%>"></c:set>
<c:set var="orientationEnums" value="<%=IndexAreaTypes.OrientationEnums.values()%>"></c:set>
<form id="areaForm" method="post"
	<c:if test="${empty model.id}">action="${ctx}/admin/index-area!add.action"</c:if>
	<c:if test="${not empty model.id}">action="${ctx}/admin/index-area!edit.action"</c:if>>
<input id="id" type="hidden" name="id" value="${model.id}"/>
<input type="hidden" name="model.id" value="${model.id}"/>
<table class="form" width="560">
	<tr>
		<td width="80">区域名称：</td>
		<td>
			<input name="model.areaName" value="${model.areaName}" class="input1"/>
		</td>
	</tr>
	<tr>
		<td>区域编码：</td>
		<td>
			<input name="model.areaCode" value="${model.areaCode}" class="input1"/>
		</td>
	</tr>
	<tr>
		<td>内容类型：</td>
		<td>
			<select name="model.contentType" class="easyui-combobox">
				<c:forEach items="${contentTypeEnums}" var="contentType">
				<option value="${contentType.value}"<c:if test="${model.contentType eq contentType.value}"> selected</c:if>>${contentType.text}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>显示类型：</td>
		<td>
			<select name="model.viewType" class="easyui-combobox">
				<c:forEach items="${viewTypeEnums}" var="viewType">
				<option value="${viewType.value}"<c:if test="${model.viewType eq viewType.value}"> selected</c:if>>${viewType.text}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>排列方向：</td>
		<td>
			<select name="model.orientation" class="easyui-combobox">
				<c:forEach items="${orientationEnums}" var="orientation">
				<option value="${orientation.value}"<c:if test="${model.orientation eq orientation.value}"> selected</c:if>>${orientation.text}</option>
				</c:forEach>
			</select>
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
<script type="text/javascript" src="${ctx}/resources/js/admin/index-area/form.js"></script>
