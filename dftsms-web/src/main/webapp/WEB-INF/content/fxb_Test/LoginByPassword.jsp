<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通过验证码登录账户</title>
</head>
<body>
	<form action="login/ByPass">
		账号名：<input type="text" name="account" /> 密码：<input type="text"
			name="password" /> <br /> <input type="submit" value="提交" />
	</form>
	<%
		//输出登录标识
		out.println("账户ID："+session.getAttribute("fxb_AccountID"));
		out.println("角色ID："+session.getAttribute("fxb_roleID"));
	%>
</body>
</html>