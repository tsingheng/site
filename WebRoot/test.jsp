<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    <script type="text/javascript" src="components/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	$('#songxh').click(function(){
    		var state = $.data(this, 'songxh');
    		alert(this);
    		if(!state){
    			$.data(this, 'songxh', {name: songxh});
    		}
    	});
    });
    </script>
  </head>
  
  <body>
  	<input type="button" id="songxh" value="songxh"/>
  </body>
</html>
