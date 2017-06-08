<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//模拟验证码已验证通过
		session.setAttribute("identifyCode", "0000");
		session.setAttribute("icTimestamp", String.valueOf(new Date().getTime() / 1000));
		session.setAttribute("IdentifyCodeFlag", "false");
		session.setAttribute("icPhone", "18230307738");
	%>
	<form action="IdentifyCode/VerifyIdentifyCode">
		验证码：<input type="text" name="inputCode" /> 
		<br/><input type="submit" value="提交" />
	</form>
</body>
</html>