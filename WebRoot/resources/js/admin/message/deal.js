$(document).ready(function(){
	$('#dealMsgForm').validate({
		rules: {
			"model.memo": {
    			required: true,
    			maxlength: 500
    		}
    	}
	});
	$('#dealMsgForm').find('.btnSubmit').click(function(){
		var form = $('#dealMsgForm');
		if(form.valid()){
			showProgress();
			$('#dealMsgForm').ajaxSubmit({
				dataType: 'json',
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							$('#msg-deal-win').window('close');
							reload('message');
						}
					});
				}
			});
		}
	});
	$('#dealMsgForm').find('.btnCancel').click(function(){
		$('#msg-deal-win').window('close');
	});
});