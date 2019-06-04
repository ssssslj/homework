<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>


<form class="exam-form form-inline" action="teacher_importStudent"
	enctype="multipart/form-data" method="post">
	<input type="hidden" name="exam.id" value="<s:property value="exam.id"/>">
	<input type="file" name="studentsFile" />
<input type="submit" class="btn btn-primary" value="导入" />
</form>

<form class="exam-form form-inline" action="teacher_before_addStudent" method="post">
	<h4>添加单个学生</h4>
	<input type="hidden" name="exam.id" value="<s:property value="exam.id"/>">
	<input type="text" name="snumber" placeholder="学号*" size="20" />
	<input type="text" name="sname" placeholder="姓名*" size="20" />
	<input type="text" name="sclass" placeholder="班级" size="20" />
	<button type="submit" class="btn btn-primary"><i class="icon-plus"></i> 添加</button>
</form>

<form class="exam-form form-inline" action="teacher_before_serach_student2" method="post">
	<h4>查找学生信息</h4>
	<input type="hidden" name="exam.id" value="<s:property value="exam.id"/>">
	<input type="text" name="snumber" placeholder="学号" size="20" />
	<input type="text" name="sname" placeholder="姓名" size="20" />
	<input type="text" name="sclass" placeholder="班级" size="20" />
	<button type="submit" class="btn btn-primary"><i class="icon-search"></i> 查找</button>
</form>

<div class="container-fluid">
	<div class="row-fluid">
		<h4>查找结果</h4>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th class="span3">学号</th>
					<th class="span2">姓名</th>
					<th class="span3">班级</th>
				</tr>
			</thead>
			<tbody>
				
				<s:iterator value="studentList" id="student">
					<tr>
						<td><s:property value="#student.number" /></td>
						<td><s:property value="#student.name" /></td>
						<td><s:property value="#student.sclass" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>

<%@ include file="footer.jsp"%>
</body>
</html>