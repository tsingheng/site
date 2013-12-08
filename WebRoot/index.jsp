<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="${ctx}/components/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	var iframe = $('<iframe width="400" height="400" src="admin/admin.action" onload="(function(){alert($(this))})();"></iframe>');
    	iframe.appendTo('body');
    });
    </script>
  </head>
  
  <body>
    
  </body>
</html>
