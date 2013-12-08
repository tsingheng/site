$(document).ready(function(){
	var list = $('#role-list');
	list.datagrid({
		onRowContextMenu: roleContextMenu
	});
});

function roleContextMenu(e, index, data){
	e.preventDefault();
	var menu = $('#role-menu');
	menu.menu('show', {
		left: e.pageX,
		top: e.pageY
	});
	$('#role-list').datagrid('selectRow', index);
}

/**
 * 打开添加编辑表单
 */
function addRole(){
	var url = admin + '/role!add.action';
	openForm('role-win', url, '添加角色');
}

/**
 * 打开修改编辑表单
 */
function editRole(){
	var url = admin + '/role!edit.action';
	var selected = $('#role-list').datagrid('getSelected');
	if(selected){
		url = url + '?id=' + selected.id;
		openForm('role-win', url, '编辑角色');
	}else{
		showMsg('请先选择需要编辑的记录');
	}
}

function authRole(){
	var url = admin + '/role!auth.action';
	var selected = $('#role-list').datagrid('getSelected');
	if(selected){
		url = url + '?id=' + selected.id;
		openForm('auth-win', url, '角色授权');
	}else{
		showMsg('请选择需要授权的角色');
	}
}

/**
 * 删除节点
 * @return {TypeName} 
 */
function delRole(){
	var url = admin + '/role!del.action';
	var selected = $('#role-list').datagrid('getSelected');
	if(selected){
		confirm('确定要删除角色[' + selected.roleName + ']吗', function(){
			$.ajax({
				url: url,
				method: 'post',
				dataType: 'json',
				data: {id: selected.id},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							reload('role');
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
 * 新增或者修改保存成功,关闭表单,并刷新父节点
 * @param {Object} api
 * @param {Object} response
 * @param {Object} add
 */
function saveSuccess(api, response){
	if(response.success){
		api.close();
	}
	showMsg(response.msg, function(){
		if(response.success){
			reload();
		}
	});
}

function onSearchRole(){
	var list = $('#role-list');
	list.datagrid({
		queryParams: {
			roleCode: $('#roleCode').val(),
			roleName: $('#roleName').val()
		}
	});
	list.datagrid('reload');
}

function onClearRole(){
	$('#roleCode').val('');
	$('#roleName').val('');
	$('#role-list').datagrid({
		queryParams: {}
	});
}