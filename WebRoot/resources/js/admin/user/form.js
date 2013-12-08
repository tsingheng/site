$(document).ready(function() {
	$('#userForm').validate({
		rules: {
			"model.userName": {
    			required: true,
    			maxlength: 10
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
	$('#userForm').find('.btnSubmit').click(function(){
		var form = $('#userForm');
		if(form.valid()){
			showProgress('正在处理,请稍候...');
			$('#userForm').ajaxSubmit({
				dataType: 'json',
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							$('#user-win').window('close');
							$('#user-list').datagrid('reload');
						}
					});
				}
			});
		}
	});
	$('#userForm').find('.btnCancel').click(function(){
		closeWin('user');
	});
});