<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息的用户名</title>
</head>
<body>
	<form action="update/accountNameByID">
		账户ID：<input type="text" name="accountID" /> <br /> 
		用户名：<input type="text" name="accountName" /><br />
		<input type="submit" value="提交" /><br/>
	</form>
</body>
</html>