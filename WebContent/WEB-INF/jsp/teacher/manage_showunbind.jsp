<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 exam-info">
			<p class="exam-info">
				<a class="btn btn-primary" href="teacher_manage_summary"><i class="icon-share-alt"></i> 返回</a>
			</p>
			<h4>未登录学生数量：<s:property value="studentUnlogin.size()" /></h4><br>
			<table class="ui-table" style="color: #333333;">
				
					<tr>
						<th>学号</th>
						<th>姓名</th>
					</tr>
					<s:iterator value="studentUnlogin" id="stu">
					<tr>
						<td><s:property  value="#stu.number"/></td>
						<td><s:property  value="#stu.name"/></td>
					</tr>
				</s:iterator>
			</table>				
		</div>
	</div>
</div>

<%@ include file="footer.jsp" %>
