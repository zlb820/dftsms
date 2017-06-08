package cn.zlb.tools.validate;

import java.util.HashMap;
import java.util.Map;

import cn.zlb.entity.TBusiness;
 
public class ValidateLogin {
	
	public ValidateLogin() {
		super();
	}

	@SuppressWarnings("null")
	public Map<String, String> checkLogin(TBusiness business){
		Map<String, String> error=new HashMap<String, String>();	
		/*
		 * 1.用户名的校验
		 * */
		String busName=business.getBusName();
		if(busName==null&&busName.trim().isEmpty()){
			error.put("busName", "用户名不能为空！");
		}else if(busName.length()<3||busName.length()>20){
			error.put("busName", "用户名必须在3-20之间！");
		} 
		
		/**
		 * 2.0密码的校验
		 * 
		 */
		String busPass=business.getBusPass();
		if (busPass==null && busPass.trim().isEmpty()) {
			error.put("loginpass", "密码不能为空！！");
		} else if(busPass.length()<3 || busPass.length()>20){
			error.put("loginpass", "密码长度2-20！！");
		}
		 
		
		return error;
		
		
	}
}
