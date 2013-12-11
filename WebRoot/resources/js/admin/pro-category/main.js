$(document).ready(function(){
	var list = $('#cate-list');
	list.datagrid({
		onRowContextMenu: cateContextMenu
	});
});

function cateContextMenu(e, index, data){
	e.preventDefault();
	var menu = $('#cate-menu');
	var list = $('#cate-list');
	var queryParams = list.datagrid('options').queryParams;
	var sortEl = document.getElementById('cate-sort');
	if(JSON.stringify(queryParams) != '{}'){
		menu.menu('disableItem', sortEl);
	}else{
		menu.menu('enableItem', sortEl);
	}
	menu.menu('show', {
		left: e.pageX,
		top: e.pageY
	});
	list.datagrid('selectRow', index);
}

/**
 * 打开添加编辑表单
 */
function addCate(){
	var url = admin + '/pro-category!add.action';
	openForm('cate-win', url, '添加产品系列');
}

/**
 * 打开修改编辑表单
 */
function editCate(){
	var url = admin + '/pro-category!edit.action';
	var selected = $('#cate-list').datagrid('getSelected');
	if(selected){
		url = url + '?id=' + selected.id;
		openForm('cate-win', url, '编辑产品系列');
	}else{
		showMsg('请先选择需要编辑的记录');
	}
}

/**
 * 删除节点
 * @return {TypeName} 
 */
function delCate(){
	var url = admin + '/pro-category!del.action';
	var selected = $('#cate-list').datagrid('getSelected');
	if(selected){
		confirm('确定删除产品系列[' + selected.categoryName + ']吗', function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {id: selected.id},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							reload('cate');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要删除的记录');
	}
}

/**
 * 更改排序操作
 * @param {Object} sortType up, down, first, last
 * @return {TypeName} 
 */
function sortCate(sortType){
	var list = $('#cate-list');
	var url = admin + '/pro-category!sort.action?v='+new Date().getTime();
	var selected = list.datagrid('getSelected');
	sort(sortType, selected, list.datagrid('getPager').pagination('options').total, url, function(){
		reload('cate');
	});
}

function onSearchCate(){
	var list = $('#cate-list');
	list.datagrid({
		queryParams: {
			userName: $('#categoryName').val()
		}
	});
	list.datagrid('reload');
}

function onClearCate(){
	$('#categoryName').val('');
	$('#cate-list').datagrid({
		queryParams: {}
	});
}