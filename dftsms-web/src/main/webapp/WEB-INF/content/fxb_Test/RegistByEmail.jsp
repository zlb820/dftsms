<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="regist/RegistByEmailAndPass">
		注册账号名称：<input type="text" name="accountName" /> <br>
		注册账号邮箱：<input type="text" name="email" /> <br>
		请输入密码：<input type="text" name="oneTimeInputPass" /> <br>
		请再次输入密码：<input type="text" name="SecondTimeInputPass" /> 
		<br/><input type="submit" value="提交" />
	</form>
</body>
</html>