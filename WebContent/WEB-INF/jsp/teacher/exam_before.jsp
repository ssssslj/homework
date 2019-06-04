<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="header.jsp"%>
	<form class="exam-form form-inline" action="teacher_addExam" enctype="multipart/form-data" method="post">
		<h4>添加考试</h4>
		<input type="text" name="ename" placeholder="考试名称*" size="20">
		<div class="controls input-append date" id="datetimepicker" data-link-field="etime">
			<input class="span2" size="16" type="text" placeholder="考试时间*" readonly>
			<span class="add-on"><i class="icon-remove"></i></span>
			<span class="add-on"><i class="icon-th"></i></span>
		</div>
		<input type="hidden" id="etime" name="etime">
		<!-- <input type="checkbox" name="eautostart" value="true">自动开始 -->
		<input type="checkbox"
				id="checkbox1" name="eautostart" value="true"> <label for="checkbox1"
				class="ui-checkbox"></label><label for="checkbox1">&nbsp;自动开始</label>
		<input type="submit" class="btn btn-primary" value="添加">
	</form>
	<div class="exam">
	<table class="ui-table" style="color: #333333;">
		<tr>
			<th scope="col">考试名称</th>
			<th scope="col" style="width: 150px">考试时间</th>
			<th scope="col">创建人</th>
			<th scope="col">上传试卷</th>
			<th scope="col">自动开始</th>
			<th scope="col">进行中</th>
			<th scope="col">已结束</th>
			<th scope="col">已归档</th>
			<th scope="col">已清理</th>
			<th scope="col"></th>
		</tr>
		<s:iterator value="examList" id="exam">
				<tr>
				<td><s:property  value="#exam.name"/></td>
				<td><s:property  value="#exam.startTime.toLocaleString()"/></td>
				<td><s:property  value="#exam.teacher.name"/></td>
				<td><s:if test="#exam.paper!=null"><i style="color: #333333;" class="fa fa-check"  aria-hidden="true"></i></s:if> </td>
				<td><s:if test="#exam.autoStart"><i style="color: #333333;" class="fa fa-check"  aria-hidden="true"></i></s:if> </td>
				<td><s:if test="#exam.started && !#exam.finished"><i style="color: #333333;" class="fa fa-check"  aria-hidden="true"></i></s:if> </td>
				<td><s:if test="#exam.finished"><i style="color: #333333;" class="fa fa-check"  aria-hidden="true"></i></s:if> </td>
				<td><s:if test="#exam.archived"><i style="color: #333333;" class="fa fa-check"  aria-hidden="true"></i></s:if> </td>
				<td><s:if test="#exam.cleaned"><i style="color: #333333;" class="fa fa-check"  aria-hidden="true"></i></s:if> </td>
				<td>
					<s:if test="!#exam.started && !#exam.finished"><a href="teacher_editExam?eid=<s:property  value="#exam.id"/>" title="编辑"><i style="color: #333333;" class="fa fa-pencil-square-o"></i></a>&nbsp;
					<a href="teacher_deleteExam?eid=<s:property  value="#exam.id"/>" title="删除"><i class="fa fa-trash" style="color: #333333;"></i></a></s:if>
				</td>
			</tr>
			</s:iterator>
		
	</table>
</div>

<%@ include file="footer.jsp"%>
<script type="text/javascript">
	$("#datetimepicker").datetimepicker({
		format:"yyyy-mm-dd hh:ii",
		autoclose:true,
		todayBtn:true,
		minuteStep:10,
		minView:0,
		pickerPosition:'bottom-left',
		language:'zh-CN'
	});
</script>