<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息的手机号</title>
</head>
<body>
	<%
		session.setAttribute("icPhone", "18244442222");
		session.setAttribute("identifyCode", "1234");
	%>
	<form action="update/phoneByID">
		账户ID：<input type="text" name="accountID" /> <br /> 
		验证码：<input type="text" name="identifyCode" /><br />
		<input type="submit" value="提交" />
	</form>
	<%
		out.println("已保存的手机号为："+session.getAttribute("icPhone"));
		out.println("已保存的验证码为："+session.getAttribute("identifyCode"));
	%>
</body>
</html>