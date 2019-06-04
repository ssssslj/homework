<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/font-awesome-4.7.0/css/font-awesome.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/ui.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/admin.css"
	type="text/css" />

<title>JSP Page</title>
</head>

<body>
	<div class="wrapper">
		<div class="top_navi">
			<div class="top_navi_cont">
				<div class="top_nav_left ">
					<span class="nav_title">上机考试系统</span>
					<ul class="top_navi_cont_ul">
						<li><a href="admin_main">首页</a></li>
						<li><a href="admin_teacher">教师管理</a></li>
						<li><a href="admin_exam">考试清理</a></li>
						<li><a href="admin_config">系统配置</a></li>
					</ul>
				</div>
				<div class="top_nav_right">
					<ul class="top_navi_cont_ul">
						<li><span>欢迎，<s:property value="#session.adminName" />
						</span></li>
						<li><a href="#" data-toggle="modal"
							data-target=".bs-example-modal-sm">修改口令</a></li>
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
			<s:if test='#session.adminId == "-1"'>
			<div style="margin-top: -30px;margin-bottom: 20px"> <span style="color:red;font-size:20px" >系统还未设置管理员，请尽快设置 </span> </div>
		</s:if>
		</div>
		<div class="container">
		
	