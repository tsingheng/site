$(document).ready(function() {
	$("body").layout();
	// 加载左侧菜单树
	$('#tree-menu').tree({
		url : admin + '/admin!menu.action',
		onClick : function(node) {
			if (node.leaf) {
				var mainTabs = $('#main-tabs');
				if (!mainTabs.tabs('exists', node.text)) {
					mainTabs.tabs('add', {
						title : node.text,
						closable : true,
						href : ctx+'/admin' + node.url
					// content: '<iframe scrolling="auto" frameborder="0"
					// src="${ctx}/admin' + node.url + '"
					// style="width:100%;height:100%;"></iframe>'
					});
				} else {
					mainTabs.tabs('select', node.text);
				}
			}
		}
	});
	var timespan = $('#time');
	setInterval(function() {
		var now = new Date();
		var hour = now.getHours();
		var time = '';
		if (hour < 10)
			time += '0';
		time += hour + ':';
		if (now.getMinutes() < 10)
			time += '0';
		time += now.getMinutes() + ':';
		if (now.getSeconds() < 10)
			time += '0';
		time += now.getSeconds();
		timespan.html(time);
	}, 100);
	$('#close_btn').click(function(){
		$.ajax({
			url: ctx+'/admin/admin!logout.action?v='+new Date().getTime(),
			method: 'POST',
			dataType: 'json',
			success: function(result){
				if(result.success){
					window.close();
				}else{
					alert('退出异常');
				}
			}
		});
	});
	$('#logout').click(function(){
		$.ajax({
			url: admin+'/admin!logout.action?v='+new Date().getTime(),
			method: 'POST',
			dataType: 'json',
			success: function(result){
				if(result.success){
					document.location.reload();
				}else{
					alert('退出异常');
				}
			}
		});
	});
	$('#reset_pwd').click(function(){
		$('#pwdDlg').window('open');
	});
	$('#pwdDlg .btnSubmit').click(function(){
		var iopwd = $('#opwd'), inpwd = $('#npwd'), icpwd = $('#cpwd');
		if(!iopwd.val()){alert('请输入原密码');iopwd.focus();return false;}
		if(!inpwd.val()){alert('请输入新密码');inpwd.focus();return false;}
		if(!icpwd.val()){alert('请输入确认密码');icpwd.focus();return false;}
		if(icpwd.val() != inpwd.val()){alert('两次密码输入不一致，请重新输入');inpwd.val('');icpwd.val('');inpwd.focus();return false;}
		showProgress('正在提交请求');
		$.ajax({
			url: admin+'/admin!reset.action?v='+new Date().getTime(),
			method: 'POST',
			dataType: 'json',
			data: {
				opwd: iopwd.val(),
				npwd: inpwd.val(),
				cpwd: icpwd.val()
			},
			success: function(result){
				closeProgress();
				if(result.success){
					alert('密码修改成功，请使用新密码重新登录');
					document.location.reload();
				}else{
					alert(result.msg);
				}
			}
		});
	});
	$('#pwdDlg .btnCancel').click(function(){
		$('#pwdDlg').window('close');
		$('#opwd').val('');
		$('#npwd').val('');
		$('#cpwd').val('');
	});
});
$.extend($.fn.window.defaults, {
	collapsible: false,
	minimizable: false,
	maximizable: false
});