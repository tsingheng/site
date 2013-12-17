$(document).ready(function(){
	$('#message-list').datagrid({
		onRowContextMenu: messageContextMenu
	});
	$('.easyui-datebox').datebox({
		formatter: dateFormatter
	});
	
	refreshDealTime({value: 'false'});
	$('#dealed').combobox({
		onSelect: refreshDealTime
	});
});
function closeMsgWin(){
	$('#msgDlg').window('close');
}
function messageContextMenu(e, index, data){
		e.preventDefault();
		var menu = $('#message-menu');
		menu.menu('show', {
			left: e.pageX,
			top: e.pageY
		});
		$('#message-list').datagrid('selectRow', index);
	}

function dateFormatter(date){
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return (y + '-' + (m > 9 ? m : ('0' + m)) + '-' + (d > 9 ? d : ('0' + d)));
}

function onSearchMessage(){
	alert($('#sendTimeStart').datebox('getValue'));
}

function onClearMessage(){
	var inputs = $('#main-tabs').tabs('getSelected').find('.search');
	$.each(inputs, function(){
		$(this).val('');
	});
	$('#message-list').datagrid({
		queryParams: {}
	});
}

function refreshDealTime(rec){
	var dealed = rec.value;
	var dealTimeStartInput = $('#dealTimeStart');
	var dealTimeEndInput = $('#dealTimeEnd');
	if(dealed != 'true'){
		dealTimeStartInput.datebox({disabled: true});
		dealTimeStartInput.datebox('setValue', '');
		dealTimeEndInput.datebox({disabled: true});
		dealTimeEndInput.datebox('setValue', '');
	}else{
		dealTimeStartInput.datebox({disabled: false});
		dealTimeEndInput.datebox({disabled: false});
	}
}

function viewMessage(){
	var selected = $('#message-list').datagrid('getSelected');
	if(selected){
		url = admin + '/message!detail.action?id=' + selected.id;
		openForm('msg-detail-win', url, '产看留言记录');
	}else{
		showMsg('请先选择留言记录');
	}
}

function dealMessage(){
	var selected = $('#message-list').datagrid('getSelected');
	if(selected){
		if(selected.dealed){
			showMsg('该留言已处理过');
			return;
		}
		var url = admin + '/message!deal.action?id=' + selected.id;
		openForm('msg-deal-win', url, '留言处理');
	}else{
		showMsg('请先选择需要处理的记录');
	}
}

function msgRowStyler(index, row){
	if(!row.dealed){
		return 'background:#6293BB;color:#fff;font-weight:bold;';
	}else{
		return '';
	}
}