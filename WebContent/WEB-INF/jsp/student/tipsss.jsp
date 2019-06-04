<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ include file="header2.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>	
<%if(session.getAttribute("tipss")!=null) 
{%>
<div style="color:red;margin-left:100px;"><%=session.getAttribute("tipss")%><%} %></div>
<%session.removeAttribute("tipss"); %>
<%if(session.getAttribute("tipsss")!=null) 
{%>
<div style="color:red;margin-left:100px;"><%=session.getAttribute("tipsss")%><%} %></div>
<%session.removeAttribute("tipsss"); %>
<a style="margin-left:100px;color:blue;margin-top:-100px;" href="student_main">返回</a>
</body>
</html>