<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<style type="text/css">
.panel{font-size:14px;}
.panel td{height:24px;}
</style>
<div class="easyui-layout" fit="true">
<div region="north" border="false">
<ul class="condition">
	<li><span class="label">客户姓名：</span><input type="text" id="customerName" class="search"/></li>
	<li><span class="label">主题：</span><input type="text" id="subject" class="search"/></li>
	<li><span class="label">联系电话：</span><input type="text" id="tel" class="search"/></li>
	<li><span class="label">公司名称：</span><input type="text" id="company" class="search"/></li>
	<li>
		<span class="label">是否处理：</span>
		<select id="dealed" class="easyui-combobox">
			<option value="">全部</option>
			<option value="false">未处理</option>
			<option value="true">已处理</option>
		</select>
	</li>
	<li><span class="label">处理人：</span><input type="text" id="dealer" class="search"/></li>
	<li>
	<span class="label">发送时间：</span>
	<input type="text" id="sendTimeStart" class="easyui-datebox" style="width:90px;"/>&nbsp;至
	<input type="text" id="sendTimeEnd" class="easyui-datebox" style="width:90px;"/>
	</li>
	<li>
	<span class="label">处理时间：</span>
	<input type="text" id="dealTimeStart" class="easyui-datebox" style="width:90px;"/>&nbsp;至
	<input type="text" id="dealTimeEnd" class="easyui-datebox" style="width:90px;"/>
	</li>
	<li class="button">
	<a href="#" onclick="onSearchMessage();" class="easyui-linkbutton">搜索</a>
	<a href="#" onclick="onClearMessage();" class="easyui-linkbutton">清空</a>
	</li>
</ul>
<div class="toolbar">
	<a href="#" onclick="viewMessage();" class="easyui-linkbutton">查看详细</a>
	<a href="#" onclick="dealMessage();" class="easyui-linkbutton">处理</a>
</div>
</div>
<div region="center" border="false">
<div class="easyui-menu" id="message-menu">
	<div onclick="viewMessage();">查看详细</div>
	<div onclick="dealMessage();">处理</div>
</div>
<table class="easyui-datagrid" url="${ctx}/admin/message.action" id="message-list" singleSelect="true" border="false"
	rownumbers="true" idField="id" fitColumns="true" pagination="true" rowStyler="msgRowStyler" fit="true">
	<thead>
		<tr>
			<th field="customerName" width="8%">客户姓名</th>
			<th field="subject" width="10%">主题</th>
			<th field="tel" width="10%">联系电话</th>
			<th field="company" width="10%">客户公司</th>
			<th field="email" width="10%">电子邮箱</th>
			<th field="sendTime" width="6%" formatter="cutDateFormatter">发送时间</th>
			<th field="dealed" width="7" align="center" formatter="yesOrNoFormatter">已处理</th>
			<th field="dealTime" width="6%" formatter="cutDateFormatter">处理时间</th>
			<th field="dealer" width="8%">处理人</th>
			<th field="msgContent" width="25%">信息内容</th>
		</tr>
	</thead>
</table>
<div id="msg-deal-win" class="easyui-window" cache="false" style="width:610px;height:250px;" closed="true"></div>
<div id="msgDlg" class="easyui-window" style="width:600px;height:auto;" data-options="resizable:true,modal:true"
		minimizable="false" maximizable="false" closed="true" title="查看留言">
	<table width="100%" height="100%">
		<tr>
			<td width="15%" align="right">主题：</td><td width="35%"><span id="dlgSubject"></span></td>
			<td width="15%" align="right">邮箱：</td><td width="35%"><span id="dlgTel"></span></td>
		</tr>
		<tr>
			<td align="right">客户姓名：</td><td><span id="dlgCustomerName"></span></td>
			<td align="right">联系电话：</td><td><span id="dlgTel"></span></td>
		</tr>
		<tr>
			<td align="right">公司名称：</td><td><span id="dlgCompany"></span></td>
			<td align="right">传真：</td><td><span id="dlgFax"></span></td>
		</tr>
		<tr>
			<td align="right">国家/地区：</td><td><span id="dlgCountry"></span></td>
			<td align="right">地址：</td><td><span id="dlgAddress"></span></td>
		</tr>
		<tr>
			<td align="right">发送时间：</td><td><span id="dlgSendTime"></span></td>
			<td align="right">处理时间：</td><td><span id="dlgDealTime"></span></td>
		</tr>
		<tr>
			<td align="right">附件下载：</td><td colspan="3" id="dlgFiles"></td>
		</tr>
		<tr>
			<td align="right">信息内容：</td><td colspan="3"><span id="dlgMsgContent"></span></td>
		</tr>
		<tr>
			<td align="right">处理结果：</td><td colspan="3"><span id="dlgResult"></span></td>
		</tr>
	</table>
	<div style="text-align: right; padding:10px;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="closeMsgWin();">确定</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="closeMsgWin();">关闭</a>
	</div>
</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/admin/message/main.js"></script>