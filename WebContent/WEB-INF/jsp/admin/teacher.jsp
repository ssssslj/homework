<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="header.jsp"%>

<div class="teacher">
	<div class="addTeacher">
		<form action="admin_addTeacher" method="post">

			<input name="number" style="width: 200px;" class="ui-input"
				type="text" placeholder="工号*" /> <input name="name"
				style="width: 200px;" class="ui-input" type="text" placeholder="姓名*" />
			<input name="password" type="text" style="width: 200px;"
				class="ui-input" placeholder="密码*" /> <input type="checkbox"
				id="checkbox1" name="admin" value="true"> <label
				for="checkbox1" class="ui-checkbox"></label><label for="checkbox1">&nbsp;管理员</label>
			<label style="display: inline;" for="formSubmit"
				class="ui-button ui-button-primary">添加</label> <input type="submit"
				id="formSubmit" hidden="true" class="clip">
		</form>
	</div>
	<div class="teachers">
		<table class="ui-table">
			<tr>
				<th scope="col">工号</th>
				<th scope="col">姓名</th>
				<th scope="col">是否为管理员</th>
				<th scope="col">编辑</th>
			</tr>
			<s:iterator value="teacherList" id="teacher">
				<tr>
					<td><s:property value="#teacher.number" /></td>
					<td><s:property value="#teacher.name" /></td>
					<td><s:property value="#teacher.admin" /></td>
					<td><a
						href="admin_modifyTeacher?id=<s:property  value="#teacher.id"/>"
						title="编辑"><i class="fa fa-pencil-square-o"
							style="color: #333333;"></i></a>&nbsp; <a
						href="admin_deleteTeacher?id=<s:property  value="#teacher.id"/>"
						title="删除"><i class="fa fa-trash" style="color: #333333;"></i></a>
					</td>
				</tr>
			</s:iterator>
			<tr align="center">
				<td colspan="4">
					<ul class="pagination">
						<li><s:if test="pageBean.page==1">
								<a href="javascript:void(0)">首页</a>
							</s:if> <s:else>
								<a href="admin_serachTeacher?pageBean.page=1">首页</a>
							</s:else></li>
						<li><s:if test="pageBean.page==1">
								<a href="javascript:void(0)">上一页</a>
							</s:if> <s:else>
								<a
									href="admin_serachTeacher?pageBean.page=<s:property value="pageBean.page-1"/>">上一页</a>
							</s:else></li>
						<li class="active"><a href="javascript:void(0)"><s:property
									value="pageBean.page" />/<s:property value="pageBean.totalPage" /></a>
						</li>
						<li><s:if test="pageBean.page==pageBean.totalPage">
								<a href="javascript:void(0)">下一页</a>
							</s:if> <s:else>
								<a
									href="admin_serachTeacher?pageBean.page=<s:property value="pageBean.page+1"/>">下一页</a>
							</s:else></li>
						<li><s:if test="pageBean.page==pageBean.totalPage">
								<a href="javascript:void(0)">尾页</a>
							</s:if> <s:else>
								<a
									href="admin_serachTeacher?pageBean.page=<s:property value="pageBean.totalPage"/>">尾页</a>
							</s:else></li>
				</td>
			</tr>

			</ul>


		</table>

	</div>
</div>
</div>
<%@ include file="footer.jsp"%>