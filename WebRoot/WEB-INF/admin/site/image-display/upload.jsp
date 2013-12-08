<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<form id="imageDisplayForm-${type}" method="post" enctype="multipart/form-data"
	<c:if test="${empty model.id}">action="${ctx}/admin/image-display!add.action?type=${type}"</c:if>
	<c:if test="${not empty model.id}">action="${ctx}/admin/image-display!edit.action?type=${type}"</c:if>>
<input type="hidden" name="model.typeCode" value="${type}"/>
<input type="hidden" name="id" value="${model.id}"/>
<table class="form" width="420">
	<tr>
		<td width="80">标题：</td>
		<td>
			<input type="text" name="model.title" class="input1" value="${model.title}"/>
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
		<td colspan="2" align="center">
			<a href="#" class="easyui-linkbutton btnSubmit">确定</a>
			<a href="#" class="easyui-linkbutton btnCancel">取消</a>
		</td>
	</tr>
</table>
</form>
<script type="text/javascript">
$(document).ready(function() {
	$('#imageDisplayForm-${type}').validate({
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
	$('#imageDisplayForm-${type}').find('.btnSubmit').click(function(){
		var form = $('#imageDisplayForm-${type}');
		if(form.valid()){
			showProgress('正在处理,请稍候...');
			$('#imageDisplayForm-${type}').ajaxSubmit({
				dataType: 'json',
				timeout: 500,
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							$('#${type}-image-win').window('close');
							$('#${type}-image-list').cardview('reload');
						}
					});
				}
			});
		}
	});
	$('#imageDisplayForm-${type}').find('.btnCancel').click(function(){
		closeWin('${type}-image');
	});
});
</script>