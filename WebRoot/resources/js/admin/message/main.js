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
	var list = $('#message-list');
	var selected = list.datagrid('getSelected');
	if(selected){
		$('#dlgSubject').html(selected.subject);
		$('#dlgEmail').html(selected.email);
		$('#dlgCustomerName').html(selected.customerName);
		$('#dlgTel').html(selected.tel);
		$('#dlgCompany').html(selected.company);
		$('#dlgFax').html(selected.fax);
		$('#dlgCountry').html(selected.country);
		$('#dlgAddress').html(selected.address);
		$('#dlgSendTime').html(selected.sendTime);
		$('#dlgMsgContent').html(selected.msgContent);
		if(selected.dealed){
			$('#dlgDealTime').html(selected.dealTime + ' 由 ' + selected.dealer + ' 处理');
			$('#dlgResult').html(selected.memo);
		}else{
			$('#dlgDealTime').html('尚未处理');
			$('#dlgResult').html('尚未处理');
		}
		var files = selected.files;
		if(files.length > 0){
			var file = $('<a href="' + ctx + file.path + '">' + file.originalName + '</a>&nbsp;&nbsp;&nbsp;&nbsp;');
			$('#dlgFiles').append(file);
		}else{
			$('#dlgFiles').html('未上传附件');
		}
		var msgDlg = $('#msgDlg');
		msgDlg.window('open');
	}else{
		showMsg('请先选择需要查看的记录');
	}
}

function dealMessage(){
	var selected = $('#message-list').datagrid('getSelected');
	if(selected){
		if(selected.dealed){
			showMsg('该留言以处理过');
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