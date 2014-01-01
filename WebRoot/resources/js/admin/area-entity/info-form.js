$(document).ready(function(){
	$('#entity-cate-tree-list').tree({
		onClick: onCateTreeClick
	});
	function onCateTreeClick(node){
		var list = $('#entity-info-list');
		list.datagrid('unselectAll');
		var url = list.datagrid('options').url;
		if(url != admin + '/info.action?type='+node.id){
			list.datagrid({
				url: admin + '/info.action?type='+node.id
			});
		}
	}
	$('#entity-info-list').datagrid({
		onRowContextMenu: infoContextMenu
	});
	function infoContextMenu(e, index, data){
		e.preventDefault();
		$('#entity-info-list').datagrid('selectRow', index);
		if($.inArray(data.id, nodes)>-1) return;
		var menu = $('#entity-info-menu');
		var list = $('#entity-info-list');
		menu.menu('show', {
			left: e.pageX,
			top: e.pageY
		});
	}
});
function entityStyler(index, row){
	if($.inArray(row.id, nodes)>-1){
		return 'background:#6293BB;color:#fff;font-weight:bold;';
	}else{
		return '';
	}
}
function onSearchEntityInfo(){
	var list = $('#entity-info-list');
	list.datagrid({
		queryParams: {
			title: $('#entity-infoTitle').val()
		}
	});
	list.datagrid('reload');
}
function onClearEntityInfo(){
	var list = $('#entity-info-list');
	list.datagrid({
		queryParams: {}
	});
	$('#entity-infoTitle').val('');
	list.datagrid('reload');
}
function addEntityInfo(){
	var url = admin + '/area-entity!add.action?v='+new Date().getTime();
	var list = $('#entity-info-list');
	var selected = list.datagrid('getSelected');
	if(selected){
		confirm('确定要将文章[' + selected.title + ']放在首页显示吗', function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {id: selected.id, areaId: areaId},
				success: function(response){
					showMsg(response.msg, function(){
						list.datagrid('reload');
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要在首页显示的文章记录');
	}
}