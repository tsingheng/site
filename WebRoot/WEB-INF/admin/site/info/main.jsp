<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<div class="easyui-layout" fit="true">
		<div region="north" style="border:none;">
			<ul class="condition">
				<li><span class="label">文章标题：</span><input type="text" id="${type}-infoTitle" class="search"/></li>
				<li>
					<a href="#" onclick="onSearchInfo${type}();" class="easyui-linkbutton">搜索</a>
					<a href="#" onclick="onClearInfo${type}();" class="easyui-linkbutton">清空</a>
				</li>
			</ul>
			<div class="toolbar">
				<a href="#" onclick="addInfo${type}();" class="easyui-linkbutton">添加文章</a>
				<a href="#" onclick="editInfo${type}();" class="easyui-linkbutton">编辑文章</a>
				<a href="#" onclick="delInfo${type}();" class="easyui-linkbutton">删除文章</a>
			</div>
		</div>
		<div region="center" style="border:none;">
			<div class="easyui-menu" id="${type}-info-menu">
				<div onclick="viewInfo${type}();">预览</div>
				<div onclick="addInfo${type}();">添加文章</div>
				<div onclick="editInfo${type}();">编辑文章</div>
				<div id="publish-info-${type}" onclick="publishInfo${type}();">发布文章</div>
				<div id="cancel-info-${type}" onclick="cancelInfo${type}();">取消发布</div>
				<div onclick="delInfo${type}();">删除文章</div>
				<div id="${type}-info-sort">
					<span>移动顺序</span>
					<div>
						<div onclick="sortInfo${type}('first');">置顶</div>
						<div onclick="sortInfo${type}('up');">上移</div>
						<div onclick="sortInfo${type}('down');">下移</div>
						<div onclick="sortInfo${type}('last');">置尾</div>
					</div>
				</div>
			</div>
			<table class="easyui-datagrid" singleSelect="true" rownumbers="true" idField="id" fitColumns="true"
				pagination="true" id="${type}-info-list" fit="true" border="false" url="${ctx}/admin/info.action?type=${type}">
				<thead>
					<tr>
						<th field="title" width="16%">文章标题</th>
						<!-- <th field="minorTitle" width="8%">副标题</th> -->
						<th field="sort" width="3%">排序</th>
						<th field="creater" width="10%">添加者</th>
						<th field="insertTime" width="12%">添加时间</th>
						<th field="viewTimes" width="7%">浏览次数</th>
						<th field="tags" width="14%">标签</th>
						<th field="published" width="8%" formatter="yesOrNoFormatter" align="center">发布</th>
						<th field="memo" width="30%">备注</th>
					</tr>
				</thead>
			</table>
			<div id="${type}-info-win" class="easyui-window" cache="false" style="width:960px;height:680px;" closed="true"></div>
		</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var list = $('#${type}-info-list');
	list.datagrid({
		onRowContextMenu: ${type}InfoContextMenu
	});
});

function ${type}InfoContextMenu(e, index, data){
	e.preventDefault();
	var menu = $('#${type}-info-menu');
	var list = $('#${type}-info-list');
	var queryParams = list.datagrid('options').queryParams;
	var sortEl = document.getElementById('${type}-info-sort');
	if(JSON.stringify(queryParams) != '{}'){
		menu.menu('disableItem', sortEl);
	}else{
		menu.menu('enableItem', sortEl);
	}
	var publish = document.getElementById('publish-info-${type}');
	var cancel = document.getElementById('cancel-info-${type}');
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
	$('#${type}-info-list').datagrid('selectRow', index);
}

/**
 * 打开添加编辑表单
 */
function addInfo${type}(){
	var url = admin + '/info!add.action?type=${type}';
	openForm('${type}-info-win', url, '添加用户');
}

function viewInfo${type}(){
	
}

/**
 * 打开修改编辑表单
 */
function editInfo${type}(){
	var url = admin + '/info!edit.action';
	var selected = $('#${type}-info-list').datagrid('getSelected');
	if(selected){
		url = url + '?type=${type}&id=' + selected.id;
		openForm('${type}-info-win', url, '编辑文章');
	}else{
		showMsg('请先选择需要编辑的用户记录');
	}
}

/**
 * 删除节点
 * @return {TypeName} 
 */
function delInfo${type}(){
	var url = admin + '/info!del.action';
	var selected = $('#${type}-info-list').datagrid('getSelected');
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
							reload('${type}-info');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要删除的用户');
	}
}

function onSearchInfo${type}(){
	var list = $('#${type}-info-list');
	list.datagrid({
		queryParams: {
			title: $('#${type}-infoTitle').val()
		}
	});
	reload('${type}-info');
}

function onClearInfo${type}(){
	$('#${type}-infoTitle').val('');
	$('#${type}-info-list').datagrid({
		queryParams: {}
	});
}

function sortInfo${type}(sortType){
	var list = $('#${type}-info-list');
	var url = admin + '/info!sort.action';
	var selected = list.datagrid('getSelected');
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
				data: {sortType: sortType, id: selected.id, type: '${type}'},
				success: function(response){
					showMsg(response.msg, function(){
						if(response.success){
							reload('${type}-info');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要操作的记录');
	}
}

function publishInfo${type}(){
	var list = $('#${type}-info-list');
	var selected = list.datagrid('getSelected');
	if(selected){
		if(selected.published){
			showMsg('该产品已经发布');
			return;
		}
		showProgress();
		var url = admin + '/info!publish.action';
		$.ajax({
			url: url,
			method: 'post',
				dataType: 'json',
				data: {id: selected.id, type: '${type}'},
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

function cancelInfo${type}(){
	var list = $('#${type}-info-list');
	var selected = list.datagrid('getSelected');
	if(selected){
		if(!selected.published){
			showMsg('该产品还未发布');
			return;
		}
		showProgress();
		var url = admin + '/info!cancel.action';
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
							list.datagrid('refreshRow', list.datagrid('getRowIndex', selected));
						}
					});
				}
		});
	}else{
		showMsg('请先选择产品记录');
	}
}
</script>