<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<form class="exam-form form-inline" action="teacher_manage_notify_add" method="post">
	<h4>新增通知消息</h4>
	<input type="text" name="notice" placeholder="通知消息内容" />
	<button type="submit" class="btn btn-primary">添加</button>
</form>

<div class="container-fluid">
	<div class="row-fluid">
		<h4>已有通知消息</h4>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th class="span7">通知内容</th>
					<th class="span1">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="nlist" id="n" status="s">
					<tr>
						<td><s:property  value="n"/></td>
						<td>
							<a class="btn" href="teacher_manage_notify_del?nid=<s:property  value="#s.id"/>">删除</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>

<%@ include file="footer.jsp"%>