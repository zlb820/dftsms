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
	<%
		//模拟已经发送验证码
		session.setAttribute("identifyCode", "0000");
		session.setAttribute("icPhone", "18240207737");
		session.setAttribute("icTimestamp", String.valueOf(new Date().getTime() / 1000));
		out.println("模拟已成功发送验证码：");
		out.println("\t手机号为：18240207737");
		out.println("\t验证码为为：0000");
	%>
	<form action="login/ByPhoneAndIdentifyCode">
		手机号：<input type="text" name="phone" /> 验证码：<input type="text"
			name="identifyCode" /> <br /> <input type="submit" value="提交" />
	</form>
	<%
		//输出登录标识
		out.println("账户ID："+session.getAttribute("fxb_AccountID"));
		out.println("角色ID："+session.getAttribute("fxb_roleID"));
	%>
</body>
</html>