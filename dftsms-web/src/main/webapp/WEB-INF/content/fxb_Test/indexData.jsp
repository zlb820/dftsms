<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="data/index">
		请输入县所属的Code：<input type="text" name="areacountyCode" /> <br/>
		单页显示数量：<input type="text" name="numOfShow" /> <br/>
		是否是主页：<input type="text" name="isIndex" /> <br/>
		请求的页数：<input type="text" name="pagestamp" /> <br/>
		总页数：<input type="text" name="pageCount" /> <br/>
		排序规则：<input type="text" name="rule" /> <br/>
		x坐标：<input type="text" name="x" /> <br/>
		y坐标：<input type="text" name="y" /> 
		shopNodeStyleId：<input type="text" name="shopNodeStyleId" /> 
		<br/><input type="reset" value="重置"><input type="submit" value="提交" />
	</form>
</body>
</html>