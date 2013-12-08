$(document).ready(function() {
	$('#roleForm').validate({
		rules: {
			"model.roleName": {
    			required: true,
    			maxlength: 20
    		}, "model.roleCode": {
    			required: true,
    			maxlength: 20
    		}, "model.memo": {
    			maxlength: 100
    		}
    	}
	});
	$('#roleForm').find('.btnSubmit').click(function(){
		var form = $('#roleForm');
		if(form.valid()){
			showProgress('正在处理,请稍候...');
			$('#roleForm').ajaxSubmit({
				dataType: 'json',
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							$('#role-win').window('close');
							$('#role-list').datagrid('reload');
						}
					});
				}
			});
		}
	});
	$('#roleForm').find('.btnCancel').click(function(){
		$('#role-win').window('close');
	});
});