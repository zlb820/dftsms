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
	session.setAttribute("icPhone", "18244442222");
	session.setAttribute("IdentifyCodeFlag", true);
	%>
	<form action="regist/RegistByPhoneAndPass_NoAccountName">
		请输入密码 ：<input type="text" name="OneTimeInputPass" /> <br>
		再次输入密码：<input type="text" name="SecondTimeInputPass" /> <br>
		输入账户名生成策略：<input type="text" name="nameGenerationStrategy" /> <br>
		输入账户名最小长度：<input type="text" name="nameMixLenth" /> <br>
		输入账户名最大长度：<input type="text" name="nameMaxLenth" /> <br>
		输入账户名的性别类型：<input type="text" name="nameIsGirl" /> <br> <input
			type="reset" value="重置"> <input type="submit" value="提交" />
	</form>
	<%
		out.println("已保存的手机号为："+session.getAttribute("icPhone"));
		out.println("已保存的验证标识为："+session.getAttribute("IdentifyCodeFlag"));
	%>
</body>
</html>