$(document).ready(function(){
	resourceTree = $.fn.zTree.init($('#resourceTree'), setting, nodes);
	$('#authForm').find('.btnSubmit').click(function(){
		var checkedNodes = resourceTree.getCheckedNodes(true);
		var ids = '';
		if(checkedNodes.length < 1){
			confirm('没有选择任何资源,确定提交吗', function(){
				submitAuth(ids);
			});
		}else{
			for(var i = 0; i < checkedNodes.length; i++){
				ids = ids + checkedNodes[i].id + ",";
			}
			ids = ids.substring(0, ids.length-1);
			submitAuth(ids);
		}
	});
	$('#authForm').find('.btnCancel').click(function(){
		$('#auth-win').window('close');
	});
});

function submitAuth(ids){
	showProgress();
	$('#resources').val(ids);
	$('#authForm').ajaxSubmit({
		dataType: 'json',
		success: function(response){
			closeProgress();
			showMsg(response.msg, function(){
				if(response.success){
					closeWin('auth');
				}
			});
		}
	});
}