$(document).ready(function(){
	editor = KindEditor.create('#pro-description');
	$('#proForm').find('.multifile').multifile();
	$('#proForm').validate({
		rules: {
			"model.productName": {
    			required: true,
    			maxlength: 30
    		}, "model.realName": {
    			required: true,
    			maxlength: 10
    		}, "model.tel": {
    			digits: true,
    			maxlength: 20
    		}, "model.qq": {
    			digits: true,
    			maxlength: 20
    		}, "model.email": {
    			email: true,
    			maxlength: 50
    		}, "model.memo": {
    			maxlength: 100
    		}
    	}
	});
	$('#proForm').find('.btnSubmit').click(function(){
		editor.sync();
		var form = $('#proForm');
		if(form.valid()){
			showProgress('正在处理,请稍候...');
			form.ajaxSubmit({
				dataType: 'json',
				timeout: 500,
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							closeWin('pro');
							reload('pro');
						}
					});
				}
			});
		}
	});
	$('#proForm').find('.btnCancel').click(function(){
		closeWin('pro');
	});
});

function onSubmit(){
	editor.sync();
	var form = $('#editForm');
	if(form.valid()){
		showProgress();
		$('#editForm').ajaxSubmit({
			dataType: 'json',
			complete: function(response){
				var data = eval('(' + response.responseText + ')');
				closeProgress();
				$.dialog.opener.saveSuccess($.dialog.open.api, data);
			}
		});
	}
	return false;
}