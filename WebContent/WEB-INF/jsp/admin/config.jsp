<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<jsp:useBean id="cfg" class="util.Config1" scope="session" />
<s:bean name="util.Config1" id="cfg1">
<s:param name="candelete"><jsp:getProperty property="candelete" name="cfg"/></s:param>
</s:bean>
<div>
	<form action="admin_config1" method="post">

		<div class="config_div_wrapper">
			<label class="config_label">后台任务间隔时间 </label>
			<div class="config_div_wrapper2">
				<input class="ui-input input3" id="input2" name="interval" value="<jsp:getProperty property="interval" name="cfg"/>" required>
				<p>指定扫描考试信息的间隔时间，单位为分钟</p>
			</div>
		</div>
		<div class="config_div_wrapper">
			<label class="config_label">分页查询记录条数 </label>
			<div class="config_div_wrapper2">
				<input class="ui-input input3" id="input2" name="pagesize" value="<jsp:getProperty property="pagesize" name="cfg"/>" required>
				<p>指定分页查询时每页显示记录数的默认值（查询页面中可以更改）</p>
			</div>
		</div>

		<div class="config_div_wrapper">
			<label class="config_label">手动开启考试时间阈值 </label>
			<div class="config_div_wrapper2">
				<input class="ui-input input3" id="input2" name="timegap" value="<jsp:getProperty property="timegap" name="cfg"/>" required>
				<p>指定手工开启考试时允许的最大提前量，单位为分钟</p>
			</div>
		</div>
		<div class="config_div_wrapper">
			<label class="config_label">上传文件字节数下限 </label>
			<div class="config_div_wrapper2">
				<input class="ui-input input3" id="input2" name="minfilesize" value="<jsp:getProperty property="minfilesize" name="cfg"/>" required>
				<p>指定上传文件的长度下限（字节），低于此值发出警报</p>
			</div>
		</div>
		<div class="config_div_wrapper">
			<label class="config_label">上传文件字数上限 </label>
			<div class="config_div_wrapper2">
				<input class="ui-input input3" id="input2" name="maxfilesize" value="<jsp:getProperty property="maxfilesize" name="cfg"/>" required>
				<p>指定上传文件的长度上限（字节），高于此值发出警报</p>
			</div>
		</div>
		<div class="config_div_wrapper2">
			<input type="checkbox" id="checkbox1" <s:if test="#cfg1.candelete">checked</s:if>    name="candelete"> <label
				for="checkbox1" class="ui-checkbox"></label><label for="checkbox1">&nbsp;教师可以清理和删除考试</label>
		</div>


		<div class="config_div_wrapper2" style="margin-top: 10px;">
			<label for="formSubmit" class="ui-button ui-button-primary">修改</label>
			<input type="submit" id="formSubmit" hidden="true" class="clip">
		</div>
	</form>
</div>

<%@ include file="footer.jsp"%>