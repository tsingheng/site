<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<div class="easyui-layout" fit="true">
		<div region="north" style="border:none;">
			<div class="toolbar">
				<a href="#" onclick="addRotateImage();" class="easyui-linkbutton">图片上传</a>
				<a href="#" onclick="editRotateImage();" class="easyui-linkbutton">编辑图片</a>
				<a href="#" onclick="delRotateImage();" class="easyui-linkbutton">删除图片</a>
			</div>
		</div>
		<div region="center" style="border:none;">
			<div class="easyui-menu" id="rotate-image-menu">
				<div onclick="addRotateImage();">图片上传</div>
				<div onclick="editRotateImage();">编辑图片</div>
				<div id="publish-image-rotate" onclick="publishRotateImage();">发布图片</div>
				<div id="cancel-image-rotate" onclick="cancelRotateImage();">取消发布</div>
				<div onclick="delRotateImage();">删除图片</div>
				<div id="rotate-image-sort">
					<span>移动顺序</span>
					<div>
						<div onclick="sortRotateImage('first');">置顶</div>
						<div onclick="sortRotateImage('up');">上移</div>
						<div onclick="sortRotateImage('down');">下移</div>
						<div onclick="sortRotateImage('last');">置尾</div>
					</div>
				</div>
			</div>
			<table class="easyui-cardview" singleSelect="true" idField="id" fitColumns="true" pagination="true" 
				id="rotate-image-list" fit="true" border="false" url="${ctx}/admin/rotate-image.action">
				<thead>
					<tr>
						<th field="sort" width="4%">排序</th>
						<th field="title" width="10%">标题</th>
						<th field="link" width="20%">链接</th>
						<th field="published" width="4%" formatter="yesOrNoFormatter">是否发布</th>
						<th field="creater" width="10%">创建者</th>
						<th field="insertTime" width="15%">上传时间</th>
						<th field="originalName" width="17%">文件名</th>
						<th field="memo" width="20%">备注</th>
					</tr>
				</thead>
			</table>
			<div id="rotate-image-win" class="easyui-window" cache="false" style="width:580px;height:420px;" closed="true"></div>
		</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/admin/rotate-image/main.js"></script>