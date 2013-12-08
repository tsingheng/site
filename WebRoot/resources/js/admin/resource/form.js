$(document).ready(function() {
	$('#resourceForm').validate({
		rules: {
			"model.resourceName": {
    			required: true,
    			maxlength: 20
    		}, "model.resourceCode": {
    			required: true,
    			maxlength: 20
    		}, "model.iconCls": {
    			maxlength: 20
    		}, "model.memo": {
    			maxlength: 100
    		}
    	}
	});
	$('#resourceForm').find('.btnSubmit').click(function(){
		var form = $('#resourceForm');
		if(form.valid()){
			showProgress('正在处理,请稍候...');
			$('#resourceForm').ajaxSubmit({
				dataType: 'json',
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							$('#resource-win').window('close');
							$('#resource-list').treegrid('reload', response.resource.parent);
						}
					});
				}
			});
		}
	});
	$('#resourceForm').find('.btnCancel').click(function(){
		$('#resource-win').window('close');
	});
	// if menu selected, show choice of leaf, or hide it
	$('#resourceForm').find('#resourceType').combobox({
		onSelect: function(){
			var type = $('#resourceType').combobox("getValue");
			if(type == menuType){
				$('#leafTr').show();
			}else{
				$('#leafTr').hide();
			}
		}
	});
});