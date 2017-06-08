<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保存评论</title>
</head>
<body>
	<form action="data/commentSave">
		商家ID：<input type="text" name="storeID" /> <br/>
		用户ID：<input type="text" name="accountID" /> <br/>
		星级：<input type="text" name="level" /> <br/>
		评论内容：<input type="text" name="comment" /> <br/>
		<input type="submit" value="提交" />
	</form>
</body>
</html>