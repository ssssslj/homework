<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="header2.jsp"%>
	
<div >
	<div class="download">
		<h3>试卷下载</h3><br>
		
		<s:if test="exam.paper==null">
			<p class="exam-info">本次考试没有电子试卷。</p>
		</s:if>
		<s:else>
			<p><s:property value="exam.paper"/> </p><br>
			<a href="student_downloadExamPaper?exam.id=<s:property value="exam.id"/>">下载</a>
		</s:else>

	</div>
	
	<div class="uploadfile">
		<h3>答案上传</h3>
		<br />
		<p>请按照考试要求将答案文件打包，再次进行上传。同名文件多次上传将会覆盖。</p>
		<form class="form-inline" action="student_uploadAnswer"
			enctype="multipart/form-data" method="post">
			<input type="file" name="answer" /> 
			<input type="submit" class="btn_submit" value="上传" />
		</form>
	</div>
	
</div>
<%@ include file="footer.jsp"%>