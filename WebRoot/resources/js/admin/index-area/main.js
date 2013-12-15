$(document).ready(function(){
	var list = $('#area-list');
	list.datagrid({
		onRowContextMenu: areaContextMenu,
		onSelect: onAreaSelect,
		onLoadSuccess: function(data){
			$('#area-entity-grid-list').datagrid('loadData', []);
		}
	});
	$('#area-entity-grid-list').datagrid({
		onRowContextMenu: entityContextMenu
	});
	function entityContextMenu(e, index, data){
		e.preventDefault();
		$('#area-entity-menu').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
		$('#area-entity-grid-list').datagrid('selectRow', index);
	}
});

function onAreaSelect(index, data){
	if($.data($('#area-list')[0], 'selected') == index) return;
	$.data($('#area-list')[0], 'selected', index)
	$('#area-entity-grid-list').datagrid({url: admin + '/area-entity.action?areaId=' + data.id});
}

function areaContextMenu(e, index, data){
	e.preventDefault();
	var menu = $('#area-menu');
	var list = $('#area-list');
	menu.menu('show', {
		left: e.pageX,
		top: e.pageY
	});
	list.datagrid('selectRow', index);
}

/**
 * 打开添加编辑表单
 */
function addArea(){
	var url = admin + '/index-area!add.action';
	openForm('area-win', url, '添加首页区域');
}

/**
 * 打开修改编辑表单
 */
function editArea(){
	var url = admin + '/index-area!edit.action';
	var selected = $('#area-list').datagrid('getSelected');
	if(selected){
		url = url + '?id=' + selected.id;
		openForm('area-win', url, '编辑首页区域');
	}else{
		showMsg('请先选择需要编辑的记录');
	}
}

/**
 * 删除节点
 * @return {TypeName} 
 */
function delArea(){
	var url = admin + '/index-area!del.action?v='+new Date().getTime();
	var selected = $('#area-list').datagrid('getSelected');
	if(selected){
		confirm('确定删除首页区域[' + selected.areaName + ']吗', function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {id: selected.id},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							reload('area');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要删除的记录');
	}
}

function addAreaEntity(){
	var list = $('#area-list');
	var selected = list.datagrid('getSelected');
	if(!selected){
		showMsg('请先选择首页区域');
	}else{
		var url = admin + '/area-entity!form.action?areaId=' + selected.id + '&viewType=' + selected.viewType;
		openForm('area-entity-win', url, '变更首页区域内容');
	}
}

function delAreaEntity(){
	var url = admin + '/area-entity!del.action?v='+new Date().getTime();
	var selected = $('#area-entity-grid-list').datagrid('getSelected');
	if(selected){
		confirm('确定删除记录[' + selected.title + ']吗', function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {id: selected.id, areaId: $('#area-list').datagrid('getSelected').id},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							reload('area-entity');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要删除的记录');
	}
}
function sortEntity(sortType){
	var list = $('#area-entity-grid-list');
	var url = admin + '/area-entity!sort.action?v='+new Date().getTime();
	var selected = list.datagrid('getSelected');
	sort(sortType, selected, list.datagrid('getPager').pagination('options').total, url, function(){
		reload('area-entity-grid');
	});
}