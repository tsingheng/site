$(document).ready(function() {
	$('#cateForm').validate({
		rules: {
			"model.categoryName": {
    			required: true,
    			maxlength: 30
    		}, "model.memo": {
    			maxlength: 100
    		}
    	}
	});
	$('#cateForm').find('.btnSubmit').click(function(){
		var form = $('#cateForm');
		if(form.valid()){
			showProgress('正在处理,请稍候...');
			$('#cateForm').ajaxSubmit({
				dataType: 'json',
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							closeWin('cate');
							reload('cate');
						}
					});
				}
			});
		}
	});
	$('#cateForm').find('.btnCancel').click(function(){
		closeWin('cate');
	});
});