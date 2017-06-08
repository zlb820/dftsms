<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>index.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <br/>
   <p>文件上传</p>
   <form action='<c:url value="/goo/goods_UploadPic"></c:url>' enctype="multipart/form-data" method="post" >
   		<input type="file" name="gooImage"/><br>
   		<input type="file" name="gooImage"/>
        <br>
        <input type="submit" value="upload"/>
   
   </form>
   
   <br/>
   <%-- <p>print<p/>
     <form action='<c:url value="/business/business_Print"></c:url>'>
   		<input type="text" name="userDto.name"/>
        <br>
        <input type="submit" value="print"/>
   
   </form> --%>
  </body>
</html>
