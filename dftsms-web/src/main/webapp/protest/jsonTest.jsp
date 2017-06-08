<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jsonTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
   <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
   <script type="text/javascript">
   	console.log(getJson());
   	$(function(){
   			
   		//文档准备完毕后执行
   		submit();
   	
   	});
   	
   	//submit函数
   	function submit(){
   		$(".submit").bind("click",function(){
   		    var jsonStr = JSON.stringify(getJson())
   			console.log(jsonStr);
   			 
   			//ajax方法
   			$.ajax({

                    type:"post",

                    url:"/dftsms_project/business_Ajax",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名

                    data:{jsonStr:jsonStr},

                    dataType:"json",//设置需要返回的数据类型

                    success:function(data){

                        alert("jsonStr:"+data);
                        //解析json字符串
                       var result=JSON.parse(data);
                        alert("businessAjax-name="+result.name);
 
 						
                    },

                    error:function(){

                        alert("系统异常，请稍后重试！");

                    }//这里不要加","

                });
   			/* $.ajax({
   				 
   				type:"POST",
   				dataType:"json",
   				url:"/dftsms_project/business_Ajax",
   				data:{jsonStr:JSON.stringify(getJson())},
   				success:function(result){
   					alert("jsonStr:"+result);
   				
   				},
   				error:function(){
   					alert("error");
   				}
   				
   			
   			
   			});
   		
   		 */
   		});
   	}
   //获取 json
   	function getJson(){
   		var json={"name":"zlb","pass":"zlb"};
   		return json;
  
   	}
   
   
   </script>
  <body>
    <input type="button" class="submit" value="ajax 提交" />
  </body>
</html>
