<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息的头像</title>
</head>
<body>
	<s:form action="update/pictureByID" enctype="multipart/form-data">
		<s:textfield name="accountID" label="文件标题" />
		<s:file name="picture" label="选择文件" />
		<s:submit value="上传" />
	</s:form>
</body>
</html>