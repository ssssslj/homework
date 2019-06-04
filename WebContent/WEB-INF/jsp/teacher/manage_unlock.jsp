<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<form class="exam-form form-inline" action="teacher_manage_serach_student" method="post">
	<h4>按学生查找已登录信息</h4>
	<input type="text" name="snumber" placeholder="学号" size="20" />
	<input type="text" name="sname" placeholder="姓名" size="20" />
	<input type="text" name="sclass" placeholder="班级" size="20" />
	<button type="submit" class="btn btn-primary"><i class="icon-search"></i> 查找</button>
</form>

<form class="exam-form form-inline" action="teacher_manage_serach_ip" method="post">
	<h4>按ip地址查找已登录信息</h4>
	<input type="text" name="ip" placeholder="ip地址" size="20" />
	<button type="submit" class="btn btn-primary"><i class="icon-search"></i> 查找</button>
</form>

<div class="container-fluid">
	<div class="row-fluid">
		<h4>查找结果</h4>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th class="span2">学号</th>
					<th class="span2">姓名</th>
					<th class="span2">班级</th>
					<th class="span3">ip地址</th>
					<th class="span2">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				
				<s:iterator value="studentList" id="student">
					<tr>
						<td><s:property value="#student.number" /></td>
						<td><s:property value="#student.name" /></td>
						<td><s:property value="#student.sclass" /></td>
						<td><s:property value="#student.binding.ip" /></td>
						<td>
							<s:if test="#student.binding != null">
							<a class="btn" href="teacher_manage_binding_delete?ip=<s:property value="#student.binding.ip" />"><i class="icon-remove"></i> 解锁</a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>


<%@ include file="footer.jsp"%>