<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<div class="easyui-layout" fit="true">
		<div region="north" style="border:none;">
			<ul class="condition">
				<li><span class="label">图片标题：</span><input type="text" id="${type}-imageTitle" class="search"/></li>
				<li>
					<a href="#" onclick="onSearchImage${type}();" class="easyui-linkbutton">搜索</a>
					<a href="#" onclick="onClearImage${type}();" class="easyui-linkbutton">清空</a>
				</li>
			</ul>
			<div class="toolbar">
				<a href="#" onclick="addImage${type}();" class="easyui-linkbutton">图片上传</a>
				<a href="#" onclick="editImage${type}();" class="easyui-linkbutton">编辑图片</a>
				<a href="#" onclick="delImage${type}();" class="easyui-linkbutton">删除图片</a>
			</div>
		</div>
		<div region="center" style="border:none;">
			<div class="easyui-menu" id="${type}-image-menu">
				<div onclick="addImage${type}();">图片上传</div>
				<div onclick="editImage${type}();">编辑图片</div>
				<div id="publish-image-${type}" onclick="publishImage${type}();">发布图片</div>
				<div id="cancel-image-${type}" onclick="cancelImage${type}();">取消发布</div>
				<div onclick="delImage${type}();">删除图片</div>
				<div id="${type}-image-sort">
					<span>移动顺序</span>
					<div>
						<div onclick="sortImage${type}('first');">置顶</div>
						<div onclick="sortImage${type}('up');">上移</div>
						<div onclick="sortImage${type}('down');">下移</div>
						<div onclick="sortImage${type}('last');">置尾</div>
					</div>
				</div>
			</div>
			<table class="easyui-cardview" singleSelect="true" idField="id" colNums="2"
				pagination="true" id="${type}-image-list" fit="true" border="false" url="${ctx}/admin/image-display.action?type=${type}">
			</table>
			<div id="${type}-image-win" class="easyui-window" cache="false" style="width:510px;height:250px;" closed="true"></div>
		</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var list = $('#${type}-image-list');
	var cardView${type} = $.extend({}, $.fn.cardview.defaults.view, {
		renderRow: function(jq, rowIndex, rowData){
			var td = [];
			var published = '<img style="padding:0px; margin:0px; width:16px; height:16px;" ' +
							'src="' + ctx + '/components/easyui/themes/icons/';
			if(rowData['published']){
				published += 'ok.png';
			}else{
				published += 'no.png';
			}
			published += '"/>';
			td.push('<img src="' + rowData['path'] + '" style="width:294px; height:294px; float:left; margin: 3px;"/>');
			td.push('<div style="float:left; margin-left:20px; margin-top: 10px;">');
				td.push('<p><span class="op-title">排序: </span>' + rowData['sort'] + '</p>');
				td.push('<p><span class="op-title">是否发布: </span>' + published + '</p>');
				td.push('<p><span class="op-title">标题: </span>' + rowData['title'] + '</p>');
				td.push('<p><span class="op-title">创建者: </span>' + rowData['creater'] + '</p>');
				td.push('<p><span class="op-title">创建时间: </span>' + rowData['insertTime'] + '</p>');
				td.push('<p><span class="op-title">文件名称: </span>' + rowData['fileName'] + '</p>');
				//td.push('<p><span class="op-title">存储路径: </span>' + rowData['path'] + '</p>');
			td.push('</div>');
			return td.join('');
		}
	});
	var list = $('#${type}-image-list');
	list.cardview({
		onRowContextMenu: ${type}ImageContextMenu,
		view: cardView${type}
	});
});

function ${type}ImageContextMenu(e, index, data){
	e.preventDefault();
	var menu = $('#${type}-image-menu');
	var list = $('#${type}-image-list');
	var queryParams = list.cardview('options').queryParams;
	var sortEl = document.getElementById('${type}-image-sort');
	if(JSON.stringify(queryParams) != '{}'){
		menu.menu('disableItem', sortEl);
	}else{
		menu.menu('enableItem', sortEl);
	}
	menu.menu('show', {
		left: e.pageX,
		top: e.pageY
	});
	$('#${type}-image-list').cardview('selectRow', index);
}

/**
 * 打开添加编辑表单
 */
function addImage${type}(){
	var url = admin + '/image-display!add.action?type=${type}';
	openForm('${type}-image-win', url, '图片上传');
}

/**
 * 打开添加编辑表单
 */
function editImage${type}(){
	var url = admin + '/image-display!edit.action?type=${type}';
	var list = $('#${type}-image-list');
	var selected = $('#${type}-image-list').cardview('getSelected');
	if(!selected){
		showMsg('请选择需要编辑的记录');
		return;
	}
	url = url + '&id=' + selected.id;
	openForm('${type}-image-win', url, '图片上传');
}

/**
 * 删除节点
 * @return {TypeName} 
 */
function delImage${type}(){
	var url = admin + '/image-display!del.action';
	var list = $('#${type}-image-list');
	var selected = $('#${type}-image-list').cardview('getSelected');
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
							list.cardview('reload');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要删除的用户');
	}
}

function onSearchImage${type}(){
	var list = $('#${type}-image-list');
	list.cardview({
		queryParams: {
			title: $('#${type}-imageTitle').val()
		}
	});
	list.cardview('reload');
}

function onClearImage${type}(){
	$('#${type}-imageTitle').val('');
	$('#${type}-image-list').cardview({
		queryParams: {}
	});
}

function sortImage${type}(sortType){
	var list = $('#${type}-image-list');
	var url = admin + '/image-display!sort.action';
	var selected = list.cardview('getSelected');
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
							list.cardview('reload');
						}
					});
				}
			});
		});
	}else{
		showMsg('请先选择需要操作的记录');
	}
}

function publishImage${type}(){
	var list = $('#${type}-image-list');
	var selected = list.cardview('getSelected');
	if(selected){
		if(selected.published){
			showMsg('该产品已经发布');
			return;
		}
		showProgress();
		var url = admin + '/image-display!publish.action';
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
							list.cardview('refreshRow', list.cardview('getRowIndex', selected));
						}
					});
				}
		});
	}else{
		showMsg('请先选择产品记录');
	}
}

function cancelImage${type}(){
	var list = $('#${type}-image-list');
	var selected = list.cardview('getSelected');
	if(selected){
		if(!selected.published){
			showMsg('该产品还未发布');
			return;
		}
		showProgress();
		var url = admin + '/image-display!cancel.action';
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
							list.cardview('refreshRow', list.cardview('getRowIndex', selected));
						}
					});
				}
		});
	}else{
		showMsg('请先选择产品记录');
	}
}
</script>