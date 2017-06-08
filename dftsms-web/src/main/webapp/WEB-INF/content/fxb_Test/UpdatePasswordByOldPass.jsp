<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="update/passwordByOldPass">
		修改账户的ID：<input type="text" name="accountID" /> <br>
		原来密码：<input type="text" name="oldPassword" /> <br>
		修改的新密码：<input type="text" name="newPassword" /> <br>
		再次输入新密码：<input type="text" name="newPasswordSecondInput" /> <br>
		<br/><input type="submit" value="提交" />
	</form>
</body>
</html>