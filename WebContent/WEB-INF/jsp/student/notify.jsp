<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:iterator value="#application.notify">
	<p><s:property /></p>
</s:iterator>