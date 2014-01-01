$(document).ready(function(){
	var list = $('#cate-list');
	list.treegrid({
		onContextMenu: cateContextMenu
	});
});

function cateContextMenu(e, row){
	e.preventDefault();
	var menu = $('#cate-menu');
	var list = $('#cate-list');
	var queryParams = list.treegrid('options').queryParams;
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
	$('#cate-list').treegrid('selectRow', row.id);
}

/**
 * 打开添加编辑表单
 */
function addCate(){
	var url = admin + '/pro-category!add.action';
	var selected = $('#cate-list').treegrid('getSelected');
	var parent = '0'; //如果没选中的话默认是顶级菜单
	if(selected){
		parent = selected.id;
	}
	url = url + '?parent=' + parent;
	openForm('cate-win', url, '添加产品系列');
}

/**
 * 打开修改编辑表单
 */
function editCate(){
	var url = admin + '/pro-category!edit.action';
	var selected = $('#cate-list').treegrid('getSelected');
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
	var selected = $('#cate-list').treegrid('getSelected');
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
							reloadNode(selected.parent);
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要删除的记录');
	}
}
function reloadNode(id){
	$('#cate-list').treegrid('reload', id);
}
/**
 * 更改排序操作
 * @param {Object} sortType up, down, first, last
 * @return {TypeName} 
 */
function sortCate(sortType){
	var list = $('#cate-list');
	var url = admin + '/pro-category!sort.action?v='+new Date().getTime();
	var selected = list.treegrid('getSelected');
	sort(sortType, selected, list.treegrid('getPager').pagination('options').total, url, function(){
		reload('cate');
	});
}

function onSearchCate(){
	var list = $('#cate-list');
	list.treegrid({
		queryParams: {
			userName: $('#categoryName').val()
		}
	});
	list.treegrid('reload');
}

function onClearCate(){
	$('#categoryName').val('');
	$('#cate-list').treegrid({
		queryParams: {}
	});
}