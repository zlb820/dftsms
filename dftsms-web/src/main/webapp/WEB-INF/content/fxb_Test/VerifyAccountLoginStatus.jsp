<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证账号登录状态</title>
</head>
<body>
	<%
		//模拟已经发送验证码
		session.setAttribute("fxb_AccountID", "402881e85c0a183c015c0a1a869a0000");
	%>
	声明：<br/>
	初始情况下ID为《402881e85c0a183c015c0a1a869a0000》已经处于登录状态！
	<form action="verify/accountLoginStatus">
		验证账号ID：<input type="text" name="accountID" /> <br />
		<input type="submit" value="提交" />
	</form>
</body>
</html>