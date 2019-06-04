<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>


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
			<th scope="col" style="width:80px"></th>
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
					<s:if test="#exam.started"><a href="teacher_stopExam?eid=<s:property  value="#exam.id"/>" title="停止考试"><i class="fa fa-stop-circle-o"  style="color: #333333; aria-hidden="true"></i></a>&nbsp;</s:if>
					<s:if test="#exam.finished && !#exam.cleaned"><a href="teacher_downloadPaper?eid=<s:property  value="#exam.id"/>" title="打包下载试卷"><i class="fa fa-download"  style="color: #333333; aria-hidden="true"></i></a>&nbsp;</s:if>
					<s:if test="#exam.finished && !#exam.cleaned"><a href="teacher_downloadAnswers?eid=<s:property  value="#exam.id"/>" title="导出提交记录"><i class="icon-book"></i></a>&nbsp;</s:if>
					<s:if test="#application.config.candelete">
					<s:if test="#exam.archived && !#exam.cleaned"><a href="teacher_cleanExam?eid=<s:property  value="#exam.id"/>" title="清理"><i class="icon-remove-sign"></i></a>&nbsp;</s:if>
					<s:if test="#exam.cleaned"><a href="teacher_deleteExam?eid=<s:property  value="#exam.id"/>" title="删除"><i class="fa fa-trash" style="color: #333333;"></i></a>&nbsp;</s:if>
					</s:if>
					
					
				</td>
			</tr>
			</s:iterator>
		
	</table>
</div>

<%@ include file="footer.jsp"%>