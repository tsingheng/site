$(document).ready(function(){
	var list = $('#rotate-image-list');
	var cardView = $.extend({}, $.fn.cardview.defaults.view, {
		renderRow: function(jq, rowIndex, rowData){
			var td = [];
			var columns = $.data(list[0], 'datagrid').options.columns[0];
			var header = $.data(list[0], 'datagrid').dc.view2.find('.datagrid-header .datagrid-header-inner table');
			td.push('<div style="text-align:center;margin:3px;"><img src="' + ctx + rowData['path'] + '" style="width:1000px; height:240px;"/></div>');
			td.push('<div style="margin-top: 0px;">');
				td.push('<table class="datagrid-btable" width="100%" cellspacing="0" cellspadding="0">');
					td.push('<tr style="height:25px;">');
					for(var i = 0; i < columns.length; i++){
						var width = header.find('td[field="' + columns[i].field + '"]').width();
						td.push('<td style="width:' + width + 'px;border-bottom:none;border-top:#ccc 1px dotted;');
						if((i+1) == columns.length)
							td.push('border-right:none;');
						var data = rowData[columns[i].field];
						if(columns[i].formatter)
							data = columns[i].formatter(data, rowData, rowIndex);
						if(!data)
							data = '';
						td.push('"><div class="datagrid-cell">' + data + '</div></td>');
					}
					td.push('</tr>');
				td.push('</table>');
			td.push('</div>');
			return td.join('');
		}
	});
	var list = $('#rotate-image-list');
	list.cardview({
		onRowContextMenu: rotateImageContextMenu,
		view: cardView
	});
});

function rotateImageContextMenu(e, index, data){
	e.preventDefault();
	var menu = $('#rotate-image-menu');
	var list = $('#rotate-image-list');
	var queryParams = list.cardview('options').queryParams;
	var sortEl = document.getElementById('rotate-image-sort');
	if(JSON.stringify(queryParams) != '{}'){
		menu.menu('disableItem', sortEl);
	}else{
		menu.menu('enableItem', sortEl);
	}
	menu.menu('show', {
		left: e.pageX,
		top: e.pageY
	});
	$('#rotate-image-list').cardview('selectRow', index);
}

/**
 * 打开添加编辑表单
 */
function addRotateImage(){
	var url = admin + '/rotate-image!add.action';
	openForm('rotate-image-win', url, '图片上传');
}

/**
 * 打开添加编辑表单
 */
function editRotateImage(){
	var url = admin + '/rotate-image!edit.action';
	var list = $('#rotate-image-list');
	var selected = $('#rotate-image-list').cardview('getSelected');
	if(!selected){
		showMsg('请选择需要编辑的记录');
		return;
	}
	url = url + '?id=' + selected.id;
	openForm('rotate-image-win', url, '图片上传');
}

/**
 * 删除节点
 * @return {TypeName} 
 */
function delRotateImage(){
	var url = admin + '/rotate-image!del.action';
	var list = $('#rotate-image-list');
	var selected = $('#rotate-image-list').cardview('getSelected');
	if(selected){
		confirm('确定要删除所选记录吗', function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {id: selected.id},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							list.cardview('reload');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要删除的用户');
	}
}

function sortRotateImage(sortType){
	var list = $('#rotate-image-list');
	var url = admin + '/rotate-image!sort.action';
	var selected = list.cardview('getSelected');
	if(selected){
		var msg = '';
		if((sortType == 'up' || sortType == 'first') && selected.sort <= 1){
			// 判断是不是第一个
			showMsg('该记录已经是第一条');
			return false;
		}
		var pager = list.datagrid('getPager');
		if((sortType == 'down' || sortType == 'last') && selected.sort >= pager.pagination('options').total){
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
				data: {sortType: sortType, id: selected.id, type: ''},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							list.cardview('reload');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要操作的记录');
	}
}

function publishRotateImage(){
	var list = $('#rotate-image-list');
	var selected = list.cardview('getSelected');
	if(selected){
		if(selected.published){
			showMsg('该产品已经发布');
			return;
		}
		showProgress();
		var url = admin + '/rotate-image!publish.action';
		$.ajax({
			url: url,
			method: 'post',
				dataType: 'json',
				data: {id: selected.id, type: ''},
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							selected.published = true;
							list.cardview('refreshRow', list.cardview('getRowIndex', selected));
						}
					});
				}
		});
	}else{
		showMsg('请先选择产品记录');
	}
}

function cancelRotateImage(){
	var list = $('#rotate-image-list');
	var selected = list.cardview('getSelected');
	if(selected){
		if(!selected.published){
			showMsg('该产品还未发布');
			return;
		}
		showProgress();
		var url = admin + '/rotate-image!cancel.action';
		$.ajax({
			url: url,
			method: 'post',
				dataType: 'json',
				data: {id: selected.id, type: '${types}'},
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							selected.published = false;
							list.cardview('refreshRow', list.cardview('getRowIndex', selected));
						}
					});
				}
		});
	}else{
		showMsg('请先选择产品记录');
	}
}