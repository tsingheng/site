$(document).ready(function(){
	$('#proImageForm').find('.multifile').multifile();
	$('#proImageForm').find('.btnSubmit').click(function(){
		var form = $('#proImageForm');
		var valid = false;
		var files = form.find('input[type="file"]');
		for(var i = 0; i < files.length; i++){
			if($(files[i]).val()){
				valid = true;
				break;
			}
		}
		if(valid){
			showProgress('正在处理,请稍候...');
			form.ajaxSubmit({
				dataType: 'json',
				timeout: 500,
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							closeWin('pro-image');
							var images = $('#pro-image-list');
							for(var i = 0; i < response.list.length; i++){
								images.datagrid('appendRow', response.list[i]);
							}
							var pro = $('#pro-list').datagrid('getSelected');
							pro.images = images.datagrid('getData');
							if(!images.datagrid('getSelected')){
								var path = response.list[0].path;
								pro.image = path;
								refreshProImage(path);
							}
						}
					});
				}
			});
		}else{
			showMsg('请选择上传文件');
		}
	});
	$('#proImageForm').find('.btnCancel').click(function(){
		closeWin('pro-image');
	});
});