function openForm(winid, url, title){
	var win = $('#' + winid);
	var opts = {
		modal: true,
		title: title,
		cache: false
	};
	win.window(opts);
	win.window('open');
	win.window('refresh', url);
}

function showMsg(msg, callBack){
	$.messager.alert('系统消息',msg,'',callBack);
}

function confirm(msg, callBack){
	$.messager.confirm('系统提示',msg, function(r){
		if(r){
			callBack();
		}
	});
}

function showProgress(msg){
	$.messager.progress({
		title: '系统提示',
		msg: msg
	});
}

function getCurrentTab(){
	return $('#main-tabs').tabs('getSelected');
}

function closeProgress(){
	$.messager.progress('close');
}

function reload(list){
	$('#' + list + '-list').datagrid('reload');
}

function closeWin(win){
	$('#' + win + '-win').window('close');
}

function cutDateFormatter(value, row, index){
	var html = '';
	if(value){
		html = '<span title="' + value + '">' + value.substring(0, 10) + '</span>';
	}
	return html;
}
function yesOrNoFormatter(value, row, index){
	var html = '<div style="text-align:center;">' +
			   '<img style="padding:0px; margin:0px; width:16px; height:16px;" ' +
					'src="' + ctx + '/components/easyui/themes/icons/';
	if(value){
		html += 'ok.png';
	}else{
		html += 'no.png';
	}
	html += '"/></div>';
	return html;
}