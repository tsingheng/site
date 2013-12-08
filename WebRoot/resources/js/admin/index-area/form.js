$(document).ready(function() {
	$('#areaForm').validate({
		rules: {
			"model.areaName": {
    			required: true,
    			maxlength: 20
    		}, "model.areaCode": {
    			required: true,
    			maxlength: 20
    		}, "model.memo": {
    			maxlength: 200
    		}
    	}
	});
	$('#areaForm').find('.btnSubmit').click(function(){
		var form = $('#areaForm');
		if(form.valid()){
			showProgress('正在处理,请稍候...');
			$('#areaForm').ajaxSubmit({
				dataType: 'json',
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							closeWin('area');
							reload('area');
						}
					});
				}
			});
		}
	});
	$('#areaForm').find('.btnCancel').click(function(){
		closeWin('area');
	});
});