<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'head.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<% 
	//session验证
	Object object= session.getAttribute("user");
	if(object==null){
	    
		response.sendRedirect("logina.html");
	}
%>
  </head>
  
  <body>
    <div id="header">
    	<div id="rightheader">
    	<p>
    	<%=new Date()%>
    	<br>
    	</p>
    	</div>
    	<div id="topheader">
    	</div>
    	<div id="navigation"></div>
    </div>
  </body>
</html>
