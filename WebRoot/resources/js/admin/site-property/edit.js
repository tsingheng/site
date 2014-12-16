$(document).ready(function() {
	headEditor = KindEditor.create('#head_editor', {
		uploadJson: admin + '/admin!upfile.action'
	});
	footEditor = KindEditor.create('#foot_editor', {
		uploadJson: admin + '/admin!upfile.action'
	});
	contactEditor = KindEditor.create('#contact_editor', {
		uploadJson: admin + '/admin!upfile.action'
	});
	//保存原值
	$('.input1,textarea', $('#sitePropertyForm ')).each(function(){
		$.data(this, 'old', $(this).val());
	});
	$.data($('#site-logo-pre')[0], 'old', $('#site-logo-pre').attr('src'));
	$('#sitePropertyForm').find('.btnSubmit').click(function(){
		headEditor.sync();
		footEditor.sync();
		contactEditor.sync();
		showProgress('正在处理,请稍候...');
		$('#sitePropertyForm').ajaxSubmit({
			dataType: 'json',
			timeout: 500,
			success: function(response){
				closeProgress();
				showMsg(response.msg, function(){
					if(response.success){
						if(response.site_logo != undefined){
							$('#sitePropertyForm .file').val('');
							$('#site-logo-pre').attr('src', response.siteLogo);
						}
					}
				});
			}
		});
	});
	$('#sitePropertyForm').find('.btnCancel').click(function(){
		$('#sitePropertyForm')[0].reset();
		headEditor.html('');
		footEditor.html('');
		contactEditor.html('');
	});
	$('#sitePropertyForm').find('.btnBack').click(function(){
		//恢复原值
		$('.input1,textarea', $('#sitePropertyForm ')).each(function(){
			$(this).val($.data(this, 'old'));
		});
		$('#sitePropertyForm .file').val('');
		$('#site-logo-pre').attr('src', $.data($('#site-logo-pre')[0], 'old'));
		headEditor.html($('#sitePropertyForm #head_editor').val());
		footEditor.html($('#sitePropertyForm #foot_editor').val());
		contactEditor.html($('#sitePropertyForm #contact_editor').val());
	});
});