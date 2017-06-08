<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//模拟已经发送验证码 
		session.setAttribute("identifyCode", "0000");
		session.setAttribute("icPhone", "18230207737");
		session.setAttribute("icTimestamp", String.valueOf(new Date().getTime() / 1000));
	%>
	<form action="update/passwordByIdentifyCode">
		修改账户的ID：<input type="text" name="accountID" /> <br> 验证码：<input
			type="text" name="identifyCode"> <br /> 修改的新密码：<input
			type="text" name="newPassword" /> <br> 再次输入新密码：<input
			type="text" name="newPasswordSecondInput" /> <br> <input
			type="reset" value="重置" /><input type="submit" value="提交" />
	</form>
	<%
		//输出登录标识
		out.println("账户ID：" + session.getAttribute("fxb_AccountID"));
		out.println("角色ID：" + session.getAttribute("fxb_roleID"));
	%>
</body>
</html>