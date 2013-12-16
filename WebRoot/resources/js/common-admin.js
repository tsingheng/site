function openForm(winid, url, title){
	var win = $('#' + winid);
	var opts = {
		modal: true,
		title: title,
		cache: false
	};
	win.window(opts);
	win.window('open');
	if(url.indexOf('?')>-1){
		url = url+'&';
	}else{
		url = url + '?';
	}
	win.window('refresh', url+'v='+new Date().getTime());
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

function sort(sortType, selected, total, url, callback){
	if(selected){
		var msg = '';
		if((sortType == 'up' || sortType == 'first') && selected.sort >= total){
			// 判断是不是第一条
			showMsg('该记录已经是第一条');
			return false;
		}
		if((sortType == 'down' || sortType == 'last') && selected.sort <= 1){
			// 判断是不是已经是最后一个
			showMsg('该记录已经是最后一条');
			return false;
		}
		if(sortType == 'up'){
			msg = '确定将此记录上移';
		}else if(sortType == 'down'){
			msg = '确定将此记录下移';
		}else if(sortType == 'first'){
			msg = '确定将此记录移至第一条';
		}else if(sortType == 'last'){
			msg = '确定将此记录移至最后一条';
		}
		confirm(msg, function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {sortType: sortType, id: selected.id},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							callback();
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要操作的记录');
	}
}