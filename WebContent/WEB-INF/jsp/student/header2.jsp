<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>

<head>
<sx:head/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/admin.css"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/font-awesome-4.7.0/css/font-awesome.css"/>	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/student.css"
	type="text/css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/ui.css"/>
<title>JSP Page</title>
</head>

<body>
	<div class="wrapper">
		<div class="top_navi">
			<div class="top_navi_cont">
				<div class="top_nav_left ">
					<span class="nav_title">上机考试系统</span>
					<ul class="top_navi_cont_ul">
						<li><a
							href="student_main">首页</a></li>
						<li><a
							href="student_summit">查看提交</a></li>
					</ul>
				</div>
				<div class="top_nav_right">
					<ul class="top_navi_cont_ul">
						<li><span>欢迎，<s:property value="#session.studentName"/></span></li>
						
						<li><a href="student_exit"><i
								class="fa fa-sign-out"></i>退出</a></li>
					</ul>
				</div>
			</div>
		</div>

	<div class="container">