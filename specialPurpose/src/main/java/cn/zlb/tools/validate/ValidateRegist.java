package cn.zlb.tools.validate;

import java.util.HashMap;
import java.util.Map;


import cn.zlb.entity.TBusiness;
import cn.zlb.service.BusinessService;
 

public class ValidateRegist {

	public Map<String,String> checkRegist(TBusiness business,BusinessService businessService){
		Map<String, String> error=new HashMap<String, String>();	
		/*
		 * 1.用户名的校验
		 * */
		String busName=business.getBusName();
		if(busName==null&&busName.trim().isEmpty()){
			error.put("busName", "用户名不能为空！");
		}else if(busName.length()<3||busName.length()>20){
			error.put("busName", "用户名必须在3-20之间！");
		}else if(!businessService.checkBusName(busName)){
			error.put("busName", "用户名已经注册！");
		}
		
		/**
		 * 2.0密码的校验
		 * 
		 */
		String busPass=business.getBusPass();
		if (busPass==null && busPass.trim().isEmpty()) {
			error.put("busPass", "密码不能为空！！");
		} else if(busPass.length()<3 || busPass.length()>20){
			error.put("busPass", "密码长度2-20！！");
		}
		
	 
		
		/**
		 * 4.0 email的校验
		 */
		String busEmail=business.getBusEmail();
		String regex="^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$";
		if(busEmail==null&&busEmail.trim().isEmpty()){
			error.put("busEmail", "email不能为空！");
		}else if(!busEmail.matches(regex)){
			error.put("busEmail", "email格式错误！！");
		}else if( !businessService.checkBusEmail(busEmail)){
			error.put("busEmail", "email已经注册！");
		}
		
	 
		
		return error;
		}
}
