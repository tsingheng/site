<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<script type="text/javascript">
	var ${type}editor;
</script>
<form id="${type}-infoForm" method="post"
	<c:if test="${empty model.id}">action="${ctx}/admin/info!add.action?type=${type}"</c:if>
	<c:if test="${not empty model.id}">action="${ctx}/admin/info!edit.action?type=${type}"</c:if>>
<input type="hidden" name="id" value="${model.id}"/>
<input type="hidden" name="model.id" value="${model.id}"/>
<input type="hidden" name="model.typeCode" value="${type}"/>
<table class="form" width="840">
	<tr>
		<td width="80">文章标题：</td>
		<td>
			<input name="model.title" value="${model.title}" class="input1"/>
		</td>
	</tr>
	<tr>
		<td>文章副标题：</td>
		<td>
			<input name="model.minorTitle" value="${model.minorTitle}" class="input1"/>
		</td>
	</tr>
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
		<td>文章内容：</td>
		<td>
			<textarea id="${type}-info-content" name="model.content" style="width:712px;height:250px;">${model.content}</textarea>
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
$(document).ready(function() {
	${type}editor = KindEditor.create('#${type}-info-content');
	$('#${type}-infoForm').validate({
		rules: {
			"model.title": {
    			required: true,
    			maxlength: 100
    		}, "model.minorTitle": {
    			maxlength: 100
    		}, "model.memo": {
    			maxlength: 500
    		}
    	}
	});
	$('#${type}-infoForm').find('.btnSubmit').click(function(){
		${type}editor.sync();
		var form = $('#${type}-infoForm');
		if(form.valid()){
			showProgress('正在处理,请稍候...');
			$('#${type}-infoForm').ajaxSubmit({
				dataType: 'json',
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							$('#${type}-info-win').window('close');
							$('#${type}-info-list').datagrid('reload');
						}
					});
				}
			});
		}
	});
	$('#${type}-infoForm').find('.btnCancel').click(function(){
		closeWin('${type}-info');
	});
});
</script>