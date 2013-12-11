$(document).ready(function(){
	var list = $('#resource-list');
	list.treegrid({
		onContextMenu: resourceContextMenu
	});
});

function resourceContextMenu(e, row){
	e.preventDefault();
	var menu = $('#resource-menu');

	menu.menu('show', {
		left: e.pageX,
		top: e.pageY
	});
	$('#resource-list').treegrid('selectRow', row.id);
}

function expandResource(){
	var list = $('#resource-list');
	var selected = list.treegrid('getSelected');
	list.treegrid('expand', selected.id);
}

function collapseResource(){
	var list = $('#resource-list');
	var selected = list.treegrid('getSelected');
	list.treegrid('collapse', selected.id);
}

/**
 * 重新加载子节点数据
 * @param {Object} id
 */
function reloadNode(id){
	$('#resource-list').treegrid('reload', id);
}

/**
 * 打开添加编辑表单
 */
function addResource(){
	var url = admin + '/resource!add.action';
	var selected = $('#resource-list').treegrid('getSelected');
	var parent = '0'; //如果没选中的话默认是顶级菜单
	if(selected){
		// 如果选中的是菜单资源,用选中的记录做默认值
		// 否则是按钮资源,用选中的parent做默认值
		if(selected.resourceType == menuType){
			parent = selected.id;
		}else{
			parent = selected.parent;
		}
	}
	url = url + '?parent=' + parent;
	openForm('resource-win', url, '添加资源');
}

/**
 * 打开修改编辑表单
 */
function editResource(){
	var selected = $('#resource-list').treegrid('getSelected');
	if(selected){
		var url = admin + '/resource!edit.action?id=' + selected.id;
		openForm('resource-win', url, '编辑资源');
	}else{
		showMsg("请先选择需要编辑的记录");
	}
}

/**
 * 删除节点
 * @return {TypeName} 
 */
function delResource(){
	var url = admin + '/resource!del.action';
	var selected = $('#resource-list').treegrid('getSelected');
	if(selected){
		var children = $('#resource-list').treegrid('getChildren', selected.id);
		if(children.length > 0){
			showMsg('该资源下还有其他资源,请删除下属资源后再删除该资源');
			return false;
		}
		confirm('确定要删除资源[' + selected.resourceName + ']吗', function(){
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
			reloadNode(response.resource.parent);
		}
	});
}

/**
 * 更改排序操作
 * @param {Object} sortType up, down, first, last
 * @return {TypeName} 
 */
function sortResource(sortType){
	var list = $('#resource-list');
	var url = admin + '/resource!sort.action?v='+new Date().getTime();
	var selected = list.treegrid('getSelected');
	sort(sortType, selected, list.treegrid('getChildren', selected.parent).length, url, function(){
		reloadNode(selected.parent);
	});
}

function typeFormatter(value, row, index){
	if(value == menuType){
		return '系统菜单';
	}else{
		return '功能按钮';
	}
}