$(document).ready(function(){
	$('#cate-tree-list').tree({
		onClick: onCateTreeClick
	});
	$('#pro-list').datagrid({
		onSelect: onProductSelect,
		onLoadSuccess: function(data){
			$('#pro-image-list').datagrid('loadData', []);
			$('#image-default').html('');
			//$('#pro-list').datagrid('unselectAll');
		},
		onRowContextMenu: proContextMenu
	});
	$('#pro-image-list').datagrid({
		onSelect: onProImageSelect,
		onRowContextMenu: proImageContextMenu
	});
})

function onProductSelect(index, data){
	var images = $('#pro-image-list');
	images.datagrid('loadData', data.images);
	images.datagrid('unselectAll');
	refreshProImage(data.image);
}

function onProImageSelect(index, data){
	refreshProImage(data.path);
}

function refreshProImage(path){
	var img = $('<img src="' + ctx + path + '" style="width:200px; height:200px;"/>');
	var old = $('#image-default').find('img');
	if(old && old.attr('src') != (ctx + path)){
		$('#image-default').html('');
		$('#image-default').append(img);
	}
}

function proContextMenu(e, index, data){
	e.preventDefault();
	var menu = $('#pro-menu');
	var list = $('#pro-list');
	var queryParams = list.datagrid('options').queryParams;
	var sortEl = document.getElementById('pro-sort');
	if(JSON.stringify(queryParams) != '{}'){
		menu.menu('disableItem', sortEl);
	}else{
		menu.menu('enableItem', sortEl);
	}
	var publish = document.getElementById('publish-pro');
	var cancel = document.getElementById('cancel-pro');
	if(publish){
		if(data.published){
			menu.menu('disableItem', publish);
		}else{
			menu.menu('enableItem', publish);
		}
	}
	if(cancel){
		if(data.published){
			menu.menu('enableItem', cancel);
		}else{
			menu.menu('disableItem', cancel);
		}
	}
	menu.menu('show', {
		left: e.pageX,
		top: e.pageY
	});
	$('#pro-list').datagrid('selectRow', index);
}

function proImageContextMenu(e, index, data){
	e.preventDefault();
	var menu = $('#pro-image-menu');
	menu.menu('show', {
		left: e.pageX,
		top: e.pageY
	});
	$('#pro-image-list').datagrid('selectRow', index);
}

function onCateTreeClick(node){
	var list = $('#pro-list');
	list.datagrid('unselectAll');
	var url = list.datagrid('options').url;
	if(url != admin + '/product.action?category='+node.id){
		list.datagrid({
			url: admin + '/product.action?category='+node.id
		});
	}
}

function addPro(){
	var category = $('#cate-tree-list').tree('getSelected');
	if(category){
		url = admin + '/product!add.action?category=' + category.id;
		openForm('pro-win', url, '添加产品');
	}else{
		showMsg('请先在左侧选择产品分类');
	}
}

function editPro(){
	var list = $('#pro-list');
	var selected = list.datagrid('getSelected');
	if(selected){
		url = admin + '/product!edit.action?id=' + selected.id;
		openForm('pro-win', url, '编辑产品');
	}else{
		showMsg('请先选择需要编辑的产品');
	}
}

function delPro(){
	var url = admin + '/product!del.action';
	var selected = $('#pro-list').datagrid('getSelected');
	if(selected){
		confirm('确定要删除产品[' + selected.productName + ']吗', function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {id: selected.id},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							reload('pro');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要删除的产品记录');
	}
}

function onSearchPro(){
	var list = $('#pro-list');
	list.datagrid({
		queryParams: {
			userName: $('#productName').val()
		}
	});
	list.datagrid('reload');
}

function onClearPro(){
	$('#productName').val('');
	$('#pro-list').datagrid({
		queryParams: {}
	});
}

/**
 * 更改排序操作
 * @param {Object} sortType up, down, first, last
 * @return {TypeName} 
 */
function sortPro(sortType){
	var list = $('#pro-list');
	var url = admin + '/product!sort.action?v='+new Date().getTime();
	var selected = list.datagrid('getSelected');
	sort(sortType, selected, list.datagrid('getPager').pagination('options').total, url, function(){
		reload('pro');
	});
}

function sortProImage(sortType){
	var list = $('#pro-image-list');
	var url = admin + '/pro-image!sort.action?v='+new Date().getTime();
	var selected = list.datagrid('getSelected');
	sort(sortType, selected, list.datagrid('getData').rows.length, url, function(){
		//var list = $('#pro-image-list');
		list.datagrid('loadData', response.list);
		var product = $('#pro-list').datagrid('getSelected');
		product.images = list.datagrid('getData');
		product.image = response.list[0].path;
	});
}

function delProImage(){
	var url = admin + '/pro-image!del.action';
	var selected = $('#pro-image-list').datagrid('getSelected');
	if(selected){
		confirm('确定要删除该记录吗', function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {id: selected.id},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							reloadProImage();
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要删除的记录');
	}
}

function test(){
	var images = $('#images');
	var data = images.datagrid('getData');
	alert(JSON.stringify(data));
}

/**
 * 重新加载产品图片
 * 看看删掉的图片是不是默认显示的第一张,如果是要先把第一张换掉
 * 被删掉的去掉,同时要更新产品中的图片数据
 * @param {Object} id
 */
function reloadProImage(){
	var images = $('#pro-image-list');
	var list = $('#pro-list');
	var deleted = images.datagrid('getSelected');
	var product = list.datagrid('getSelected');
	if(product.image == deleted.path){
		// 如果删的是第一个,把这一行删掉以后看还有没有数据,没有就用默认的,有就用第一条
		images.datagrid('deleteRow', images.datagrid('getRowIndex', deleted));
		var data = images.datagrid('getData');
		product.images = data;
		if(data.total == 0){
			product.image = '/resources/image/default.gif';
		}else{
			product.image = data.rows[0].path;
		}
	}else{
		// 如果删的不是第一个,直接先把行去掉,然后更新产品中的数据
		images.datagrid('deleteRow', images.datagrid('getRowIndex', deleted));
		var data = images.datagrid('getData');
		product.images = data;
	}
	refreshProImage(product.image);
}

function addProImage(){
	var list = $('#pro-list');
	var product = list.datagrid('getSelected');
	if(product){
		var url = admin + '/pro-image!add.action?product=' + product.id;
		openForm('pro-image-win', url, '添加产品图片');
	}else{
		showMsg('请先选择产品记录');
	}
}

function editProImage(){
	var images = $('#pro-image-list');
	var image = images.datagrid('getSelected');
	if(image){
		url = admin + '/pro-image!edit.action?id=' + image.id;
		openForm('pro-image-memo-win', url, '设置产品图片备注');
	}else{
		showMsg('请先选择需要操作的记录');
	}
}

function publishPro(){
	var list = $('#pro-list');
	var selected = list.datagrid('getSelected');
	if(selected){
		if(selected.published){
			showMsg('该产品已经发布');
			return;
		}
		showProgress();
		var url = admin + '/product!publish.action';
		$.ajax({
			url: url,
			method: 'post',
				dataType: 'json',
				data: {id: selected.id},
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							selected.published = true;
							list.datagrid('refreshRow', list.datagrid('getRowIndex', selected));
						}
					});
				}
		});
	}else{
		showMsg('请先选择产品记录');
	}
}

function cancelPro(){
	var list = $('#pro-list');
	var selected = list.datagrid('getSelected');
	if(selected){
		if(!selected.published){
			showMsg('该产品还未发布');
			return;
		}
		showProgress();
		var url = admin + '/product!cancel.action';
		$.ajax({
			url: url,
			method: 'post',
				dataType: 'json',
				data: {id: selected.id},
				success: function(response){
					closeProgress();
					showMsg(response.msg, function(){
						if(response.success){
							selected.published = false;
							list.datagrid('refreshRow', list.datagrid('getRowIndex', selected));
						}
					});
				}
		});
	}else{
		showMsg('请先选择产品记录');
	}
}

function publishedFormatter(value, row, index){
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