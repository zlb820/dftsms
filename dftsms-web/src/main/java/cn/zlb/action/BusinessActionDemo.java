package cn.zlb.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.zlb.dto.UserDto;
import cn.zlb.entity.TBusiness;
//import cn.zlb.service.BusinessServiceDemo;
import cn.zlb.tools.CommonUtils;
import net.sf.json.JSONObject;

/**
 * 商家模块 包含 注册 登录 
 * @author Bingo
 *
 */
public class BusinessActionDemo extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger=Logger.getLogger(BusinessActionDemo.class);
	HttpServletRequest request;
	private TBusiness tBusiness;
	 private UserDto userDto;
//	private BusinessServiceDemo businessService;
	 private String print;
	 //返回json格式  字符串格式 
	 private String jsonResult;
	 //json返回  map对象
	 private Map<String, Object> jsonMap;
	 private String jsonStr;
	 //上传文件 得到文件 文件名 文件类型
	 private File busImage;
	 private String busImageFileName;
	 private String busImageContentType;
	/**
	 * 1.0 商家注册
	 * 	商户注册必填信息包括：商家名、密码、性别、手机号、身份证号、店铺名
		商户注册选填信息包括：邮箱、商家图片
 		**商户涉及到上传图片**
	 */
	 
		//请求测试demo
//		public String businessRegist(){
//			//判断TBusiness ，后台校验逻辑
//			
//			//校验成功 注册逻辑
//			boolean bool=businessService.regist(tBusiness);
//			return SUCCESS;
//		}
		
		 
	/**
	 * 1.1 商家图片上传
	 * @return
	 */
		public String businessUploadPic(){
			System.out.println("文件上传");
			//获取真实路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/busPic");
			logger.info("上传文件存储路径= " +realPath);
			logger.info("上传文件名           = "+busImageFileName);
			//避免文件名重复，处理文件名
			busImageFileName=CommonUtils.uuid()+busImageFileName;
			logger.info("处理后的文件名   =  "+busImageFileName);
			//创建File对象
			File file=new File(realPath);
			if (!file.exists()) 
				file.mkdir();
			try {
				FileUtils.copyFile(busImage, new File(file,busImageFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
	 //ajax 测试demo
	 public String businessAjax(){
		 System.out.println("ajax 处理");
		 //获取 request json字符串
		 
		 System.out.println("jsonStr= "+jsonStr);
		 //-------------1.0 json 字符串返回格式
		   Map<String,Object> map = new HashMap<String,Object>();
		 map.put("name", "zlb820");
		 map.put("age","20");
		//将map对象转换成json类型数据
		 JSONObject json = JSONObject.fromObject(map);
		//给result赋值，传递给页面
		 jsonResult = json.toString(); 

  
		 
		/* 
		 //转换 json字符串为 json对象
		 Gson gson=new Gson();
		 UserDto userDto=gson.fromJson(jsonStr, UserDto.class);
		 System.out.println("userDto from Json ="+userDto);
		 
		 
		 //返回UserDto  两个对象
		 List<UserDto> list=new ArrayList<UserDto>();
		 list.add(new UserDto("zlb01", "zlb01"));
		 list.add(new UserDto("zlb02", "zlb02"));
		 
		 jsonResult=gson.toJson(list);
		 System.out.println("listStr to Json=  "+jsonResult);
		 */
		 
		 return SUCCESS;
	 }
	 
	 

	 
	 public String businessPrint( ){
		
		 System.out.println("useDto="+userDto);
		 return SUCCESS;
	}
	 


	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	
	
	 
	

	public String getJsonResult() {
		return jsonResult;
	}


	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}


	public TBusiness gettBusiness() {
		return tBusiness;
	}

	public void settBusiness(TBusiness tBusiness) {
		this.tBusiness = tBusiness;
	}
	

	public String getJsonStr() {
		return jsonStr;
	}


	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}


	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		
	}

	//图片上传

	public File getBusImage() {
		return busImage;
	}



	public void setBusImage(File busImage) {
		this.busImage = busImage;
	}



	public String getBusImageFileName() {
		return busImageFileName;
	}



	public void setBusImageFileName(String busImageFileName) {
		this.busImageFileName = busImageFileName;
	}



	public String getBusImageContentType() {
		return busImageContentType;
	}



	public void setBusImageContentType(String busImageContentType) {
		this.busImageContentType = busImageContentType;
	}
	
	
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}


	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}


	class Person{
		
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
}











