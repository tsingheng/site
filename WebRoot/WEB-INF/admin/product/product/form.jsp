<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<script type="text/javascript">
	var editor;
	var i = 0;
</script>
<form id="proForm" method="post" enctype="multipart/form-data"
	<c:if test="${empty model.id}">action="${ctx}/admin/product!add.action"</c:if>
	<c:if test="${not empty model.id}">action="${ctx}/admin/product!edit.action"</c:if>>
<input id="id" type="hidden" name="id" value="${model.id}"/>
<input type="hidden" name="model.id" value="${model.id}"/>
<table class="form" width="840">
	<tr>
		<td width="80">产品名称：</td>
		<td>
			<input name="model.productName" value="${model.productName}" class="input1"/>
		</td>
	</tr>
	<tr>
		<td>产品分类：</td>
		<td>
			${model.category.categoryName}
			<c:if test="${empty model.id}">
			<input type="hidden" name="model.category.id" value="${model.category.id}"/>
			</c:if>
		</td>
	</tr>
	<c:if test="${empty model.id}">
	<tr>
		<td valign="top" style="padding-top: 15px;">产品图片：</td>
		<td>
			<input class="multifile file" type="file" name="files"/>
		</td>
	</tr>
	</c:if>
	<tr>
		<td>发布：</td>
		<td>
			<select class="easyui-combobox" name="model.published">
				<option value="false"<c:if test="${not model.published}"> selected</c:if>>否</option>
				<option value="true"<c:if test="${model.published}"> selected</c:if>>是</option>
			</select>
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
		<td valign="middle">属性：</td>
		<td>
			<ul class="name_value" id="oname_value">
				<c:forEach var="prop" items="${model.properties}">
				<li><span class="name">${prop.propertyName}</span><span class="value">${prop.propertyValue}</span><a index="${prop.id}" href="#nogo" class="remove">移除</a></li>
				</c:forEach>
			</ul>
			<ul class="name_value" id="nname_value">
				<li><div class="name">属性名称</div><div class="value">属性值</div><br clear="all"/></li>
				<li><div class="name"><input class="input1" type="text" name="name_0"/></div><div class="value"><input class="input1" type="text" name="name_0_value"/><a href="#nogo">添加</a></div><br clear="all"/></li>
			</ul>
		</td>
	</tr>
	<tr>
		<td>产品标签：</td>
		<td>
			<textarea name="model.tags" rows="4" cols="50">${model.tags}</textarea>
		</td>
	</tr>
	<tr>
		<td>备注：</td>
		<td>
			<textarea name="model.memo" rows="4" cols="50">${model.memo}</textarea>
		</td>
	</tr>
	<tr>
		<td>详细描述：</td>
		<td>
			<textarea id="pro-description" name="model.description" style="width:712px;height:250px;">${model.description}</textarea>
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
<script type="text/javascript" src="${ctx}/resources/js/admin/product/form.js"></script>