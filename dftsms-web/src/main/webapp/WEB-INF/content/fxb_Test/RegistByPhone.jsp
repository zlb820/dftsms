<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Android注册</title>
</head>
<body>
	<%
	session.setAttribute("icPhone", "18255556666");
	session.setAttribute("IdentifyCodeFlag", true);
	%>
	<form action="regist/RegistByPhoneAndPass">
		请输入账号名 ：<input type="text" name="accountName" /> <br>
		请输入密码 ：<input type="text" name="OneTimeInputPass" /> <br>
		再次输入密码：<input type="text" name="SecondTimeInputPass" /> <br>
		<input type="reset" value="重置"> <input type="submit" value="提交" />
	</form>
	<%
		out.println("已保存的手机号为："+session.getAttribute("icPhone"));
		out.println("已保存的验证标识为："+session.getAttribute("IdentifyCodeFlag"));
	%>
</body>
</html>