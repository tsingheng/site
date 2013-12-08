$(document).ready(function(){
	$('#proImageMemoForm').validate({
		rules: {
			"model.memo": {
    			required: true,
    			maxlength: 100
    		}
    	}
	});
	$('#proImageMemoForm').find('.btnSubmit').click(function(){
		var form = $('#proImageMemoForm');
		if(form.valid()){
			showProgress('正在处理,请稍候...');
			form.ajaxSubmit({
				dataType: 'json',
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							closeWin('pro-image-memo');
							var list = $('#pro-image-list');
							var image = list.datagrid('getSelected');
							image.memo = response.memo;
							var data = list.datagrid('getData');
							var product = $('#pro-list').datagrid('getSelected');
							product.images = data;
							list.datagrid('loadData', product.images);
						}
					});
				}
			});
		}
	});
	$('#proImageMemoForm').find('.btnCancel').click(function(){
		closeWin('pro-image-memo');
	});
});

function onSubmit(){
	var form = $('#editForm');
	if(form.valid()){
		showProgress();
		$('#editForm').ajaxSubmit({
			dataType: 'json',
			success: function(response){
				closeProgress();
				$.dialog.opener.memoSuccess($.dialog.open.api, response);
			}
		});
	}
	return false;
}