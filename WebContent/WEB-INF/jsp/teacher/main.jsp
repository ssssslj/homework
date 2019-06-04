<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
	<div class="exam_container">
    	<div class="exam_before">
    		<strong class="exam_before_title">考前操作</strong>
    		<ul>
    			<li>可以新建考试：指定考试名称、开始时间等</li>
    			<li>可以编辑自己未创建的、未开始的考试，除了修改考试信息，还可以：上传试卷、学生名单导入、开启考试。
    			</li>
    		</ul>
    	</div>
        <div class="exam_now">
            <strong class="exam_now_title">考中管理</strong>
            <ul>
                <li>可以查看考试情况</li>
                <li>可以管理学生信息，手工添加个别学生信息</li>
                <li>可以解除学生登录锁定</li>
                <li>可以添加或删除通知消息</li>
            </ul>
        </div>
        <div class="exam_after">
            <strong class="exam_after_title">考后操作</strong>
            <ul>
                <li>主考教师（考试创建者）可以结束考试</li>
                <li>主考教师可以打包下载学生提交文件</li>
                <li>主考教师可以导出提交信息</li>
                <li>如果管理员设置，主考教师可以清理和删除考试</li>
            </ul>
        </div>
    </div>
<%@ include file="footer.jsp" %>
