<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<div class="modify">
	<strong class="modify_title">修改教师账号</strong>
		<form action="admin_updateTeacher" method="post">
		<input  hidden="" name="id" value="<s:property value="id"/>" >
			<div class="config_div_wrapper">
				<label class="config_label">工号 </label>
				<div class="config_div_wrapper2">
					<input class="ui-input input3" id="userName" name="number" value="<s:property value="number"/>" >
				</div>
			</div>
			<div class="config_div_wrapper">
				<label class="config_label">姓名 </label>
				<div class="config_div_wrapper2">
					<input class="ui-input input3" id="userName" name="name"  value="<s:property value="name"/>">
				</div>
			</div>
			<div class="config_div_wrapper">
				<label class="config_label">密码 </label>
				<div class="config_div_wrapper2">
					<input class="ui-input input3" type="password" name="password">
				</div>
			</div>


			<div class="config_div_wrapper2">
				<input type="checkbox" id="checkbox1" name="admin" value="true" <s:if test="admin">checked</s:if> > <label
					for="checkbox1" class="ui-checkbox"></label><label for="checkbox1">&nbsp;管理员</label>
			</div>


			<div class="config_div_wrapper2" style="margin-top: 10px;">
				<label for="formSubmit" class="ui-button ui-button-primary">修改</label>
				<input type="submit" id="formSubmit" hidden="true" class="clip">
			</div>
		</form>
</div>

<%@ include file="footer.jsp"%>