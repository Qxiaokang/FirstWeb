<%@page import="com.xk.bean.User"%>
<%@page import="javax.jws.soap.SOAPBinding.Use"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="head.jsp" %>
	<table width="30%" border="1px">
		<tr>
			<td>name</td>
			<td>pwd</td>
		</tr>
		<% List<User> users=(List<User>)request.getAttribute("users"); 
		for(int i=0;i<users.size();i++){
				User user=users.get(i);
		%>
		<tr class="row<%=(i%2+1)%>">
		<td>
		<%=user.getUser_name() %>
		</td>
		<td>
		<%=user.getUser_pwd() %>
		</td>
		</tr>
		<%}%>
	</table>
</body>
</html>