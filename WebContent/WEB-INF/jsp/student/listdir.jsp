<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="header2.jsp"%>
<div class="filelist">
	<div class="filelist1">
		<h4>已上传文件列表</h4>

		<table class="ui-table">
			<tr>
				<th scope="col">文件名</th>
				<th scope="col">文件大小</th>
				<th scope="col">上传时间</th>
			</tr>
			<s:iterator value="answerList" id="answer">
				<tr>
				<td><s:property  value="#answer.fileName"/></td>
				<td><s:property  value="#answer.fileSize"/> KB</td>
				<td><s:property  value="#answer.submitTime.toLocaleString()"/></td>
			</tr>
			</s:iterator>
		</table>

	</div>
</div>