<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<form id="rotateImageForm" method="post" enctype="multipart/form-data"
	<c:if test="${empty model.id}">action="${ctx}/admin/rotate-image!add.action"</c:if>
	<c:if test="${not empty model.id}">action="${ctx}/admin/rotate-image!edit.action"</c:if>>
<input type="hidden" name="id" value="${model.id}"/>
<table class="form" width="520">
	<tr>
		<td width="80">标题：</td>
		<td>
			<input type="text" name="model.title" class="input1" value="${model.title}"/>
		</td>
	</tr>
	<tr>
		<td>链接：</td>
		<td>
			<input type="text" name="model.link" class="input1" value="${model.link}"/>
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
		<td>上传文件：</td>
		<td>
			<input type="file" name="file" class="file"/>
			<c:if test="${not empty model.id}">
			<p>图片不变可不用重新上传</p>
			</c:if>
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
<script type="text/javascript">
$(document).ready(function() {
	$('#rotateImageForm').validate({
		rules: {
			"model.title": {
    			required: true,
    			maxlength: 100
    		}
			<c:if test="${empty model.id}">
			, "file": {
    			required: true
    		}
			</c:if>
    	}
	});
	$('#rotateImageForm').find('.btnSubmit').click(function(){
		var form = $('#rotateImageForm');
		if(form.valid()){
			showProgress('正在处理,请稍候...');
			$('#rotateImageForm').ajaxSubmit({
				dataType: 'json',
				timeout: 500,
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							$('#rotate-image-win').window('close');
							$('#rotate-image-list').cardview('reload');
						}
					});
				}
			});
		}
	});
	$('#rotateImageForm').find('.btnCancel').click(function(){
		closeWin('rotate-image');
	});
});
</script>