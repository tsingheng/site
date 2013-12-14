$(document).ready(function(){
	$('#entity-cate-tree-list').tree({
		onClick: onCateTreeClick
	});
	function onCateTreeClick(node){
		var list = $('#entity-pro-list');
		list.datagrid('unselectAll');
		var url = list.datagrid('options').url;
		if(url != admin + '/product.action?category='+node.id){
			list.datagrid({
				url: admin + '/product.action?category='+node.id
			});
		}
	}
	$('#entity-pro-list').datagrid({
		onRowContextMenu: proContextMenu
	});
	function proContextMenu(e, index, data){
		e.preventDefault();
		var menu = $('#entity-pro-menu');
		var list = $('#entity-pro-list');
		menu.menu('show', {
			left: e.pageX,
			top: e.pageY
		});
		$('#entity-pro-list').datagrid('selectRow', index);
	}
});
function onSearchEntityPro(){
	var list = $('#entity-pro-list');
	list.datagrid({
		queryParams: {
			productName: $('#entity-productName').val()
		}
	});
	list.datagrid('reload');
}
function onClearEntityPro(){
	var list = $('#entity-pro-list');
	list.datagrid({});
	list.datagrid('reload');
}
function addEntityPro(){
	var url = admin + '/area-entity!add.action?v='+new Date().getTime();
	var list = $('#entity-pro-list');
	var selected = list.datagrid('getSelected');
	if(selected){
		confirm('确定要将产品[' + selected.productName + ']放在首页显示吗', function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {id: selected.id, ctype: '3'},
				success: function(response){
					showMsg(response.msg, function(){
						
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要在首页显示的产品记录');
	}
}