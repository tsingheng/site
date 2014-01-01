<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<table class="form" id="msg-detail-tbl" width="90%">
	<tr>
		<td width="80">主题：</td>
		<td>${msg.subject}</td>
		<td width="80">邮箱：</td>
		<td>${msg.email}</td>
	</tr>
	<tr>
		<td>客户姓名：</td>
		<td>${msg.custName}</td>
		<td>联系电话：</td>
		<td>${msg.tel}</td>
	</tr>
	<tr>
		<td>公司名称：</td>
		<td>${msg.company}</td>
		<td>传真：</td>
		<td>${msg.fax}</td>
	</tr>
	<tr>
		<td>国家/地区：</td>
		<td>${msg.country}</td>
		<td>地址：</td>
		<td>${msg.address}</td>
	</tr>
	<tr>
		<td>发送时间：</td>
		<td>${msg.sendTime}</td>
		<td>处理时间：</td>
		<td>
		<c:if test="${empty msg.dealTime}">尚未处理</c:if>${msg.dealTime}
		</td>
	</tr>
	<tr>
		<td>IP地址：</td>
		<td colspan="3">${msg.ip}</td>
	</tr>
	<tr>
		<td>附件下载：</td>
		<td colspan="3">
		<c:if test="${empty files}">未上传附件</c:if>
		<c:if test="${not empty files}">
		<c:forEach items="${files}" var="file">
		<a style="margin-right:13px;" href="${ctx}/admin/message!file.action?id=${file.id}">${file.attachment.originalName}</a>
		</c:forEach>
		</c:if>
		</td>
	</tr>
	<tr>
		<td>信息内容：</td>
		<td colspan="3">${msg.msgContent}</td>
	</tr>
	<tr>
		<td>处理结果：</td>
		<td colspan="3">${msg.memo}</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<a href="#" class="easyui-linkbutton btnCancel">关闭</a>
		</td>
	</tr>
</table>
<script type="text/javascript">
$(document).ready(function(){
	$('#msg-detail-tbl').find('a.btnCancel').click(function(){
		closeWin('msg-detail');
	});
});
</script>