<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="main">
	<div class="instruction">
		<strong>教师管理</strong><br />
		<br />
		<ul class="ins">
			<li>可以对教师用户进行增删改查操作，并可以指定特定教师作为系统管理员</li>
			<li>系统可以有多个管理员</li>
			<li>如果没有任何一个教师具有管理员身份，则默认管理员登陆信息有效</li>
		</ul>
	</div>
	<div class="instruction">
		<strong>考试清理</strong><br />
		<br />
		<ul class="ins">
			<li>清除考试的相关数据，包括数据库中的信息、文件系统中的提交文件</li>
			<li>只有在主考老师已经打包下载学生提交文件后才可以进行</li>
			<li>清理后的考试可以删除</li>
		</ul>
	</div>
	<div class="instruction">
		<strong>系统配置</strong><br />
		<br />
		<ul class="ins">
			<li>设置一些全局性的系统选项，包括后台任务的时间周期、分页查询时的每页记录数、手动开始考试的时间阈值、学生上传文件
				字节输的有效范围等</li>
			<li>可以指定是否允许主考老师清理和删除考试</li>

		</ul>
	</div>
</div>


<%@ include file="footer.jsp"%>