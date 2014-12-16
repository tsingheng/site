$(document).ready(function(){
	editor = KindEditor.create('#pro-description', {
		uploadJson: admin + '/admin!upfile.action'
	});
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
	$('#oname_value a.remove').click(function(){
		var me = $(this);
		var id = me.attr('index');
		$.ajax({
			url: admin+'/product!delprop.action?v='+new Date().getTime(),
			method: 'POST',
			dataType: 'json',
			data: {id: id},
			success: function(result){
				if(result.success){
					me.parent().remove();
				}else{
					showMsg(result.msg);
				}
			}
		});
	});
	$('#nname_value .value a').click(function(){
		addProp();
	});
	function addProp(){
		var me = $('#nname_value .value:last a')
			.unbind()
			.html('移除')
			.click(function(){
				me.parent().parent().remove();
			});
		i = i+1;
		var li = $('<li></li>');
		var namediv = $('<div class="name"></div>'), valuediv = $('<div class="value"></div>');
		var nameinput = $('<input class="input1" type="text" name="name_'+i+'"/>'), valueinput = $('<input class="input1" type="text" name="name_'+i+'_value"/>');
		var a = $('<a href="#nogo">添加</a>');
		a.click(function(){
			addProp();
		});
		namediv.append(nameinput);
		valuediv.append(valueinput).append(a);
		li.append(namediv).append(valuediv).appendTo($('#nname_value')).append('<br clear="all"/>');
	}
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