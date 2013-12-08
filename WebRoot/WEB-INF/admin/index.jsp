<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
	  	<link rel="stylesheet" type="text/css" href="${ctx}/components/easyui/themes/default/easyui.css"/>
	  	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/admin.css"/>
	    <script type="text/javascript" src="${ctx}/components/easyui/jquery-1.8.0.min.js"></script>
	    <script type="text/javascript" src="${ctx}/components/easyui/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="${ctx}/components/easyui/cardview.easyui.bug.js"></script>
		<script type="text/javascript" src="${ctx}/components/jquery.validate.js"></script>
		<script type="text/javascript" src="${ctx}/components/jquery.extend.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/ajax.extend.js"></script>
	    <script type="text/javascript" src="${ctx}/resources/js/common-admin.js"></script>
	    <script type="text/javascript" src="${ctx}/components/kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" src="${ctx}/components/kindeditor/zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery.multifile.js"></script>
	    <script type="text/javascript">
	    	var ctx = "${ctx}";
	    	var admin = "${ctx}/admin";
	    	$(document).ready(function(){
				$("body").layout();
				// 加载左侧菜单树
				$('#tree-menu').tree({
					url: admin + '/admin!menu.action',
					onClick: function(node){
						if(node.leaf){
							var mainTabs = $('#main-tabs');
							if(!mainTabs.tabs('exists', node.text)){
								mainTabs.tabs('add', {
									title: node.text,
									closable: true,
									href: '${ctx}/admin' + node.url
									//content: '<iframe scrolling="auto" frameborder="0" src="${ctx}/admin' + node.url + '" style="width:100%;height:100%;"></iframe>'
								});
							}else{
								mainTabs.tabs('select',node.text);
							}
						}
					}
				});
				var timespan = $('#time');
				setInterval(function(){
					var now = new Date();
					var hour = now.getHours();
					var time = '';
					if(hour < 10) time += '0';
					time += hour + ':';
					if(now.getMinutes()<10)	time += '0';
					time += now.getMinutes() + ':';
					if(now.getSeconds() < 10) time += '0';
					time += now.getSeconds();
					timespan.html(time);
				}, 100);
			});
	    </script>
  	</head>
  	<body>
		<div data-options="region:'north'" style="height:50px;border: none;">
			<div class="logo">网站后台管理系统</div>
			<div class="right">
				<div class="clock"><span id="date">${date}</span><span id="time">05:02:09</span></div>
				<div class="tools">
					<a href="#">关闭</a>|<a href="#">注销</a>|<a href="#">修改密码</a>
				</div>
			</div>
		</div>
		<div data-options="region:'south'" style="height:24px;padding-top:3px;border: none;">
			<div align="right"><font color="#15428b">${ctx}Developer:宋相恒　Email:tsingheng@163.com　QQ:474345424　Tel:15260816875</font></div>
		</div>
		<div data-options="region:'west',split:true" title="网站后台管理菜单" style="width:200px;">
			<ul id="tree-menu"></ul>
		</div>
		<div data-options="region:'center'" style="border: none;">
			<div class="easyui-tabs" id="main-tabs" data-options="fit:true"></div>
		</div>
	</body>
</html>