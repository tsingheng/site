$(document).ready(function(){
	var list = $('#user-list');
	list.datagrid({
		onRowContextMenu: userContextMenu
	});
});

function userContextMenu(e, index, data){
	e.preventDefault();
	var menu = $('#user-menu');
	menu.menu('show', {
		left: e.pageX,
		top: e.pageY
	});
	$('#user-list').datagrid('selectRow', index);
}

/**
 * 打开添加编辑表单
 */
function addUser(){
	var url = admin + '/user!add.action';
	openForm('user-win', url, '添加用户');
}

/**
 * 打开修改编辑表单
 */
function editUser(){
	var url = admin + '/user!edit.action';
	var selected = $('#user-list').datagrid('getSelected');
	if(selected){
		url = url + '?id=' + selected.id;
		openForm('user-win', url, '编辑用户');
	}else{
		showMsg('请先选择需要编辑的用户记录');
	}
}

/**
 * 删除节点
 * @return {TypeName} 
 */
function delUser(){
	var url = admin + '/user!del.action';
	var selected = $('#user-list').datagrid('getSelected');
	if(selected){
		confirm('确定要删除用户[' + selected.realName + ']吗', function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {id: selected.id},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							reload('user');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要删除的用户');
	}
}

function onSearchUser(){
	var list = $('#user-list');
	list.datagrid({
		queryParams: {
			userName: $('#userName').val(),
			realName: $('#realName').val(),
			tel: $('#tel').val(),
			qq: $('#qq').val(),
			email: $('#email').val()
		}
	});
	reload('user');
}

function onClearUser(){
	$('#userName').val('');
	$('#realName').val('');
	$('#tel').val('');
	$('#qq').val('');
	$('#email').val('');
	$('#user-list').datagrid({
		queryParams: {}
	});
}