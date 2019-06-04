<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>	
<div style="color:red"><%=session.getAttribute("tipssss")%></div>
<a href='teacher_editExam?eid=<%=session.getAttribute("tipsssss") %>'>返回</a>
<%@ include file="footer.jsp"%>
</body>
</html>