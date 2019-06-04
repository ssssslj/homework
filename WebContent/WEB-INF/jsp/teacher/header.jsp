<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0" >
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/font-awesome-4.7.0/css/font-awesome.css"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/bootstrap.css" media="screen">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/bootstrap-datetimepicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/bootstrap-responsive.min.css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/font-awesome-4.7.0/css/font-awesome.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/teacher.css">
<title>上机考试系统</title>
</head>
<body>
	<div class="wrapper">
		<div class="top_navi">
			<div class="top_navi_cont">
				<div class="top_nav_left ">
					<span class="nav_title" style="color:white;">上机考试系统</span>
					<ul class="top_navi_cont_ul">
						<li><a href="teacher_main"> 首页</a></li>
						<li><a href="teacher_exam_before"> 考前操作</a></li>
						<s:if test="#application.examId != null && #application.exam_creater == #session.teacherId">
							<li onclick = "f('exam_now')" >
							<a>考中管理</a>
							</s:if>
							<s:else>
							<li >
							<a style="color: black;" >考中管理</a>
							</s:else>
						
						
							<ul id="exam_now">
								<li><a href="teacher_manage_summary">考试概况</a></li>
								<li><a href="teacher_manage_student">学生信息</a></li>
								<li><a href="teacher_manage_unlock">解除绑定</a></li>
								<li><a href="teacher_manage_notify">通知管理</a></li>
							</ul>
						</li>
						<li><a href="teacher_exam_after"> 考后操作</a></li>
					</ul>
				</div>
				<div class="top_nav_right">
					<ul class="top_navi_cont_ul">
						<li><span style="color:white">欢迎，<s:property value="#session.teacherName" />
						</span></li>
						<li><a href="#" data-toggle="modal"
							data-target=".bs-example-modal-sm"><i class="fa fa-pencil"></i>修改口令</a></li>
						<li><a href="admin_exit"><i class="fa fa-sign-out"></i>退出</a></li>
					</ul>
				</div>

				<div class="modal fade bs-example-modal-sm span3" tabindex="-1" style="width: 350px"
					role="dialog" aria-labelledby="mySmallModalLabel">
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button"  class="close" data-dismiss="modal"
									aria-label="Close">
									<span style="color: black" aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">修改口令</h4>
							</div>
							<div class="modal-body">
								<form class="exam-form" action="admin_resetPassword" method="post">
									<p>
										<input type="password" name="oldPassword" placeholder="原口令"
											style="width: 95%;" /> <br /> <input type="password"
											name="newPassword" placeholder="新口令" style="width: 95%" /> <br />
										<input type="password" name="newPassword2" placeholder="重输新口令"
											style="width: 95%" /> <br /> <input type="submit"
											class="btn btn-primary" value="修改" style="width: 100%" />
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="top_title">
			<div class="top_title_cont">
				<span id="top_title"> 系统管理</span>
			</div>
		</div>
		<div class="container">
