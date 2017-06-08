<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="regist/RegistByEmailAndPass_NoAccountName">
		注册账号邮箱：<input type="text" name="email" /> <br>
		请输入密码：<input type="text" name="oneTimeInputPass" /> <br>
		请再次输入密码：<input type="text" name="SecondTimeInputPass" /> <br>
		输入账户名生成策略：<input type="text" name="nameGenerationStrategy" /> <br>
		输入账户名最小长度：<input type="text" name="nameMixLenth" /> <br>
		输入账户名最大长度：<input type="text" name="nameMaxLenth" /> <br>
		输入账户名的性别类型：<input type="text" name="nameIsGirl" /> <br>
		<br/><input type="submit" value="提交" />
	</form>
</body>
</html>