<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 exam-info">
				<h4><strong><s:property value="#application.exam"/></strong>进行情况</h4>
					<p>参加考试学生总数：<s:property value="student.size()"/></p>
					<p>已登录学生数量： <a class="btn-primary" href="teacher_manage_showbind"><i class="icon-search icon-white"></i><s:property value="studentLogined.size()" /></a>，
					     未登录学生数量： <a class="btn-primary" href="teacher_manage_showunbind"><i class="icon-search icon-white"></i><s:property value="studentUnlogin.size()" /></a>
					</p>
					<p>已登录学生中，提交文件学生数量：<a class="btn-primary" href="teacher_manage_showsubmit"><i class="icon-search icon-white"></i><s:property value="studentCommited.size()" /></a>，
					     未提交文件学生数量： <a class="btn-primary" href="teacher_manage_showunsubmit"><i class="icon-search icon-white"></i><s:property value="studentUnCommited.size()" /></a>
					</p>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp"%>