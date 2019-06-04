<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上机考试系统</title>

<link
	href="${pageContext.request.contextPath }/assets/css/loginpage.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/loginpage.js"></script>
<style>
body {
	padding-top: 60px;
	background:
		url(${pageContext.request.contextPath }/assets/image/school.jpg)
		no-repeat;
	background-size: cover;
}
</style>
</head>
<body>
	<div class="container">
		<div class="loginTab">
			<ul>
				<li><input class="btn" id="stu" type="button" name="student"
					value="学生登录" onClick="showstudent()" /></li>
				<li><input class="btn" id="tea" type="button" name="teacher"
					value="教师登录" onClick="showteacher()" /></li>
				<li><input class="btn" id="adm" type="button" name="admin"
					value="管理员登录" onClick="showadmin()" /></li>
			</ul>
		</div>
		<div class="line"></div>
		<div id="loginTabContent" class="tab-content">

			<div class="exam-login" id="teacher">
				<h3>
					<img class="img"
						src="${pageContext.request.contextPath }/assets/image/exam-teacher.png" />
					教师登录
				</h3>

				<form class="exam-form" action="teacher_login" method="post">
					<p>
						<input class="username" type="text" name="name" placeholder="用户名" />
						<br /> <input class="password" type="password" name="pass"
							placeholder="口令" />
					</p>
					<p>
						<input class="login" type="submit" value="登录" />
					</p>
				</form>
			</div>


			<div class="exam-login" id="admin">
				<h3>
					<img class="img"
						src="${pageContext.request.contextPath }/assets/image/exam-admin.png" />
					管理员登录
				</h3>

				<form class="exam-form" action="jsp/admin/main.jsp" method="post">
					<p>
						<input class="username" type="text" name="name" placeholder="用户名" />
						<br /> <input class="password" type="password" name="pass"
							placeholder="口令" />
					</p>
					<p>
						<input class="login" type="submit" value="登录" />
					</p>
				</form>
			</div>


			<div class="exam-login" id="student">
				<h3>
					<img class="img"
						src="${pageContext.request.contextPath }/assets/image/exam-student.png" />
					学生登录
				</h3>

				<form class="exam-form"
					action="${pageContext.request.contextPath }/jsp/student/student_main.jsp"
					method="post">
					<p>
						<input class="username" type="text" name="sno" placeholder="学号" />
						<br /> <input class="password" type="text" name="sname"
							placeholder="姓名" />
					</p>
					<p>
						<input class="login" type="submit" value="登录" />
					</p>
				</form>
			</div>


		</div>
	</div>
</body>
</html>