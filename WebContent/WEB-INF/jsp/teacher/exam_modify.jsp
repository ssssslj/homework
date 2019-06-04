<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<form class="exam-form form-inline" action="teacher_updateExam" method="post">
	<h4>编辑考试信息</h4>
	<input type="hidden" name="eid" value="<s:property value="exam.id"/>">
	考试名称：
	<input type="text" name="ename" value="<s:property value="exam.name"/>" />
	考试时间：
	<div class="controls input-append date" id="datetimepicker" data-link-field="etime" >
		<input class="span2" type="text" value="<s:property value="exam.startTime.toLocaleString()"/>" readonly/> 
		<span class="add-on"><i class="icon-remove"></i></span>
		<span class="add-on"><i class="icon-th"></i></span>
	</div>    
	<input type="hidden" id="etime" name="etime" />
	<div class="control-group">
		<div class="controls">
			<!-- <label class="checkbox"> <input type="checkbox" name="eautostart"
				value="true" <s:if test="exam.autoStart">checked</s:if> /> 自动开始
			</label> -->
			<input type="checkbox"
				id="checkbox1" name="eautostart" <s:if test="exam.autoStart">checked</s:if> value="true"> <label for="checkbox1"
				class="ui-checkbox"></label><label for="checkbox1">&nbsp;自动开始</label>
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<input type="submit" name="action" class="btn btn-primary" value="修改" />
		</div>
	</div>
</form>

<form class="exam-form form-inline" action="teacher_uploadExamPaper" enctype="multipart/form-data" method="post">
	<h4>上传试卷</h4>
	<input type="hidden" name="eid" value="<s:property value="exam.id"/>">
	<s:if test='exam.paper!=null && !exam.paper.equals("")' >
		<p class="exam-alert">已经上传过试卷，再次上传将导致原有试卷不可访问！
			<a class="btn" target="_blank" href="teacher_downloadExamPaper2?eid=<s:property value="exam.id"/>"><i class="icon-download"></i>下载查看</a>
		</p>
		<input type="file" name="paper" />
		<input type="submit" class="btn btn-warning" value="更新" />
	</s:if>
	<s:else>
		<input type="file" name="paper" />
		<input type="submit" class="btn btn-primary" value="上传" />
	</s:else>
</form>

<form class="exam-form form-inline" action="teacher_examStudentPage" enctype="multipart/form-data" method="post">
	<h4>导入学生名单</h4>
	<p>目前设定参加此次考试的学生人数：<s:property value="exam.students.size()"/></p>
	<input type="hidden" name="exam.id" value="<s:property value="exam.id"/>">
	<input type="submit" class="btn btn-primary" value="继续导入" />
</form>
 
<form class="exam-form form-inline" action="teacher_startExam" method="post">
	<h4>开启考试</h4>
	<input type="hidden" name="eid" value="<s:property value="exam.id"/>">
	<s:if test="#application.examId!=null && #application.examId>0">
		<p class="text-danger">考试（<s:property value="#application.examName"/>）正在进行中，本系统不允许同时进行多场考试。</p>
	</s:if>
	<s:else>
		<s:if test="@java.lang.Math@abs(exam.etime.getTime()-(new java.util.Date()).getTime())>#application.config.timegap*60*1000">
			<p class="text-danger">时间相差过多，不能开启考试。</p>
			<button type="submit" class="btn btn-danger btn-large disabled" onclick="return false"><i class="icon-off"></i> 开启</button>
		</s:if>
		<s:else>
			<s:if test='exam.epaper==null || exam.epaper.equals("")'>
				<p class="text-warning">尚未上传试卷。</p>
				<button type="submit" class="btn btn-warning btn-large"><i class="icon-off"></i> 开启</button>
			</s:if>
			<s:else>
				<button type="submit" class="btn btn-success btn-large"><i class="icon-off"></i> 开启</button>
			</s:else>
		</s:else>
	</s:else>
</form>

<%@ include file="footer.jsp" %>
<script type="text/javascript">
$("#datetimepicker").datetimepicker({
    format: "yyyy-mm-dd hh:ii",
    autoclose: true,
    todayBtn: true,
    minuteStep: 10,
    minView:0,
    pickerPosition:'bottom-left',
    language:'zh-CN'
});
</script>
