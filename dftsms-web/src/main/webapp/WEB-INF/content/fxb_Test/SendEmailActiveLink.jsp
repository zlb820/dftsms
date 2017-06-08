<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请求发送邮箱验证链接</title>
</head>
<body>
	<form action="IdentifyCode/sendEmailActiveLink">
		账号ID：<input type="text" name="accountID" /> 
		绑定邮箱：<input type="text" name="email" /> 
		<br/><input type="submit" value="提交" />
	</form>
</body>
</html>