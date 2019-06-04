<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上机考试系统</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery.SuperSlide.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/Validform_v5.3.2_min.js"></script>
<link
	href="${pageContext.request.contextPath }/assets/css/loginpage.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/loginpage.js"></script>
</head>

<style>
  body{
  margin:0;
  }
  
  video{
  position:fixed;
  right:0;
  bottom:0;
  min-width:100%;
  min-height:100%;
  width:auto;
  height:auto;
  z-index:-9999;
  }

</style>
<body>
	<div id="tab">
	<s:if test="#application.examId != null && #application.examId > 0">
					<h1><s:property value="#application.examName"/>&nbsp; 考试正在进行中</h1>
				</s:if>
		<ul class="tab_menu">
			<li class="selected">学生登录</li>
			<li>教师登录</li>
			<li>管理员登录</li>
		</ul>
		<div class="tab_box">
			<!-- 学生登录开始 -->
			<div>
				<div class="stu_error_box"></div>

				<form
					action="student_login"
					method="post" class="stu_login_error">
					<div id="username">
						<label>学&nbsp;&nbsp;&nbsp;号：</label> <input type="text"
							id="stu_username_hide" name="number" />

					</div>
					<div id="password">
						<label>姓&nbsp;&nbsp;&nbsp;名：</label> <input type="text"
							id="stu_password_hide" name="name" />
					</div>
					<div id="code">
          				<label>验证码：</label>
          				<input type="text" id="stu_code_hide" name="code"  placeholder="请输入验证码" nullmsg="验证码不能为空！" datatype="*4-4" errormsg="验证码有4位数！" sucmsg="验证码验证通过！"/>
          				<img id="img" src="${pageContext.request.contextPath }/creatImgCode.action" onclick="changeValidateCode(this)" title="点击更换" alt="验证码"/> 
          				
          			</div>
					<s:if test="#application.examId != null && #application.examId > 0">
						<div id="login">
							<button type="submit">登录</button>
						</div>
					</s:if>
					<s:else>
						<p>当前没有正在进行的考试</p>
						<div id="login">
							<button type="submit" disabled="disabled">禁止登录</button>
						</div>
					</s:else>
				</form>
			</div>
			<!-- 学生登录结束-->
			<!-- 教师登录开始-->
			<div class="hide">
				<div class="tea_error_box"></div>
				<form
					action="teacher_login"
					method="post" class="tea_login_error">
					<div id="username">
						<label>工&nbsp;&nbsp;&nbsp;号：</label> <input type="text"
							id="tea_username_hide" name="number" />

					</div>
					<div id="password">
						<label>密&nbsp;&nbsp;&nbsp;码：</label> <input type="password"
							id="tea_password_hide" name="password" />
					</div>
					<div id="code">
          				<label>验证码：</label>
          				<input type="text" id="stu_code_hide" name="code" placeholder="请输入验证码" nullmsg="验证码不能为空！" datatype="*4-4" errormsg="验证码有4位数！" sucmsg="验证码验证通过！"/>
          				<img id="img" src="${pageContext.request.contextPath }/creatImgCode" onclick="changeValidateCode(this)" title="点击更换" alt="验证码占位图"/> 
          			</div>
					<input type="text" name="admin"  hidden=""/>
					<div id="login">
							<button type="submit">登录</button>
					</div>
					
				</form>
			</div>
			<!-- 教师登录结束-->
			<!-- 管理员登录开始-->
			<div class="hide">
				<div class="sec_error_box"></div>
				<form
					action="admin_login"
					method="post" class="sec_login_error">
					<div id="username">
						<label>工&nbsp;&nbsp;&nbsp;号：</label> <input type="text"
							id="sec_username_hide" name="number" />
					</div>
					<div id="password">
						<label>密&nbsp;&nbsp;&nbsp;码：</label> <input type="password"
							id="sec_password_hide" name="password" />
					</div>
					<div id="code">
          				<label>验证码：</label>
          				<input type="text" id="stu_code_hide" name="code"  placeholder="请输入验证码" nullmsg="验证码不能为空！" datatype="*4-4" errormsg="验证码有4位数！" sucmsg="验证码验证通过！"/>
          				<img id="img" src="${pageContext.request.contextPath }/creatImgCode" onclick="changeValidateCode(this)" title="点击更换" alt="验证码占位图"/> 
          			</div>
					<div id="login">
						<button type="submit">登录</button>
					</div>
				</form>
				<script type="text/javascript">
		        //页面加载刷新图片验证码
				$("#img").click();
				//生成图片验证码
				function changeValidateCode(obj) {
					//获取当前的时间作为参数，无具体意义   
					var timenow = new Date().getTime();
					//每次请求需要一个不同的参数，否则可能会返回同样的验证码   
					//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
					obj.src = "creatImgCode.action?d=" + timenow;
				}
				</script>
			</div>
			<!-- 管理员登录结束-->
		</div>
	</div>

	<div class="screenbg">
	  <!-- 
		<ul>
			<li><a href="javascript:;"><img
					src="${pageContext.request.contextPath }/assets/image/001.MOV"></a></li>
			<li><a href="javascript:;"><img
					src="${pageContext.request.contextPath }/assets/image/002.MOV"></a></li>
		</ul>
		-->
		
		<video autoplay muted loop  style="width:100%;">
		  <source src="${pageContext.request.contextPath }/assets/image/002.MOV">
		</video>
	</div>
</body>

</html>