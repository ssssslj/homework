<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%> 
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Login form</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>

<link href='https://fonts.googleapis.com/css?family=Raleway:300,200' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

  
      <link rel="stylesheet" href="<%=basePath %>/assets/css/style.css">

  
</head>

<body>
<form class="form" id="form" style="z-index: 100">
  <div class="forceColor"></div>
  <div class="topbar">
    <div class="spanColor"></div>
    <input type="username" class="input" name="number" id="username" placeholder="Username"/>
  </div>
  <div class="topbar">
    <div class="spanColor"></div>
    <input type="password" class="input" id="password" name="password" placeholder="Password"/>
  </div>
  <s:if test="#application.examId != null && #application.examId > 0">
	<button class="submit" id="submit" onclick="send()">Login</button>
  </s:if>
  <s:else>
	<p>当前没有正在进行的考试</p>
	  <button class="submit" disabled="disabled" id="submit" onclick="send()">Login</button>
	  
  </s:else>
  
  <s:if test="#application.examId != null && #application.examId > 0">
	<h1><s:property value="#application.examName"/>&nbsp; 考试正在进行中</h1>
  </s:if>
</form>
<script type="text/javascript">
	function send()
	{
	
		var form = document.getElementById("form");
		var student=/^[1][0-9]{9}$/;
		var teacher=/^[1][0-9]{6}$/;
		var name=document.getElementById("username").value;
		if(student.test(name)){
			form.action="student_login";
			
		}else if(teacher.test(name)){
			form.action="teacher_login";

		}else{
			form.action="admin_login";
			
		}
		form.submit();
 	}
</script>
<div class="bg">
	<video autoplay muted loop  style="width:100%;position: fixed;min-width:100%;min-height:100%">
		  <source src="${pageContext.request.contextPath }/assets/image/002.MOV">
	</video>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script  src="<%=basePath %>/assets/js/login.js"></script>
</body>

</html>
