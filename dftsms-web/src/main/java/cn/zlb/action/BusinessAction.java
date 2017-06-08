package cn.zlb.action;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.zlb.dto.BusinessDto;
import cn.zlb.dto.StoreDto;
import cn.zlb.dto.android.business.BusFailedDto;
import cn.zlb.dto.android.business.BusinessDtoAnd;
import cn.zlb.entity.TAddress;
import cn.zlb.entity.TBusiness;
import cn.zlb.entity.TPictures;
import cn.zlb.entity.TStore;
import cn.zlb.service.AddressService;
import cn.zlb.service.BusinessService;
import cn.zlb.service.CollectionService;
import cn.zlb.service.CommentService;
import cn.zlb.service.PictureService;
import cn.zlb.service.StoreService;
import cn.zlb.tools.CommonUtils;
import cn.zlb.tools.validate.ValidateLogin;
import cn.zlb.tools.validate.ValidateRegist;

@SuppressWarnings("serial")
public class BusinessAction extends ActionSupport implements RequestAware,SessionAware{
	static Logger logger=Logger.getLogger(BusinessAction.class);
     private Map<String, Object> request;
     private Map<String, Object> session;
     @Resource
     private BusinessService businessService;
     @Resource
     private PictureService pictureService;
     @Resource
     private AddressService addressService;
     @Resource 
     private StoreService storeService;
     @Resource
     private CommentService commentService;
     @Resource
     private CollectionService collectionService;
	 //上传文件 得到文件 文件名 文件类型
	 private File busImage;
	 private String busImageFileName;
	 private String busImageContentType;
	 
	 //
	 private TBusiness business;
	 private BusinessDto busDto;
	 private TAddress address;
	 //商户id
	 private String busId;
	 //用户id
	 private String cusId;
	 private BusinessDtoAnd busInfo;
	 //失败
	 private BusFailedDto busFailed;
	/**
	 * 1.0商户注册
	 */
	public String businessRegist(){
			//1-校验
		Map<String, String> error=new ValidateRegist().checkRegist(business, businessService);
		if (!error.isEmpty()) {
			request.put("error", error);
			request.put("business", business);
			return "error";
		}
		//2- 校验通过 存储 ，在注册的同时 初始化 店铺信息
	
		businessService.regist(business,address);
		
		//2-1 在注册的同时 初始化 店铺地址信息
		addressService.save(address);
		//3-返回
		request.put("msg", "注册成功");
		return "success";
	}
	
	 /**
	  * 2.0 商户登录
	  */
	public String businessLogin(){
		//1- 校验
		Map<String, String> error=new ValidateLogin().checkLogin(business);
		if (!error.isEmpty()) {
			request.put("error", error);
			request.put("msg", "用户名或密码错误!");
			request.put("business", business);
			return "error";
		}
		//2- 校验无误   数据库查询
		TBusiness  busResult=businessService.login(business);
		
		/*
		 * 3- 判断数据库查询结果 
		 * 1.查询结果是否为空,空则返回错误信息
		 * 2.不为空则查看是否激活，未激活返回错误信息
		 * 3.成功，返回成功信息
		 */
		if (busResult==null) {
			request.put("msg", "用户名或密码错误！" );
			request.put("business", business);
			return "error";
		}else {
			if (!busResult.getBusStatus()) {
				request.put("msg", "用户为激活");
				request.put("business", business);
				return "error";
			}else {
				 //4-校验正确
				session.put("sesbusiness", busResult);
				return "success";
			}
		}
		 
		 
	}
	/**
	 * 3.0 退出登录
	 */
	public String businessLoginOut(){
		session.remove("sesbusiness");
		return "success";
	}
	
	/**
	 *4.0修改密码 
	 */
	public String businessModify(){
		boolean modResult=businessService.modifyBusName(busDto);
		if (!modResult) {
			request.put("msg", "修改失败");
			return "error";
		}
		return  "success";
	}
	/**
	 * 5.0查询用户信息
	 */
	public String businessFind(){
		 
		TBusiness busResult=businessService.findBusiness(busDto);
		return "success";
	}
	/**
	 * 5-1 查询用户信息 and
	 * @return
	 */
	public String businessFindById(){
		BusinessDto dto=new BusinessDto();
		dto.setPkBusId(busId);
		
		//1-1查找 商家信息
		TBusiness busResult=businessService.findBusiness(dto);
		
		//1-2查找 商家店铺信息
		StoreDto stoDto=new StoreDto();
		stoDto.setFkStoId(busId);
		TStore store =storeService.findStore(stoDto);
		
		if (busResult==null || store==null) {
			busFailed.setBusId(busResult.getPkBusId());
			return "busFailedResult";
		}else{
			busInfo=new BusinessDtoAnd();
			busInfo.setBusId(busResult.getPkBusId()); 
			busInfo.setBusEmail(busResult.getBusEmail());
			busInfo.setBusName(busResult.getBusName());
			busInfo.setBusPhone(busResult.getBusPhone());
			if (!busResult.getBusStatus()) {
				busInfo.setBusStatus("0");
			}
			busInfo.setBusStatus("1");
			busInfo.setBusTime(busResult.getBusTime()+"");
			busInfo.setBusPic(busResult.getTPictures().getPicUrl());
			
			//设置店铺信息
			busInfo.setSellCount(store.getTGood().getGooSales());
			//店铺销量
			busInfo.setBusAddress(store.getTAddress().getAddConcrete());
			//店铺星级
			busInfo.setBusLevel(commentService.findStoreLevel(store.getFkStoId())+"");
			//店铺 是否被当前用户收藏
			if (collectionService.findCollection(cusId, busId)) {
				busInfo.setBusStar("1");
			}else{
			busInfo.setBusStar("0");}
			busInfo.setStoName(store.getStoName());
			return "busResult";
		}
	}
	/**
	 * x.0上传图片  上传商家头像
	 * @param busDto
	 */
	public String businessUploadPic(){
		logger.info("文件上传");
		//1-获取真实路径
		String realPath=ServletActionContext.getServletContext().getRealPath("/pictures");
		logger.info("上传文件存储路径= " +realPath);
		logger.info("上传文件名           = "+busImageFileName);
		
		//2-避免文件名重复，处理文件名
		busImageFileName=CommonUtils.uuid()+"_"+busImageFileName;
		logger.info("处理后的文件名   =  "+busImageFileName);
		
		//3-创建File对象
		copyFile(realPath);
		
		//4-保存图片到数据库
		String picUrl="pictures/"+busImageFileName;
		
		TPictures pic=new TPictures();
		pic.setPicUrl(picUrl);
		pic.setPicDes("商户头像");
		pictureService.add(pic);
			//4-1 保存图片到商户数据表
		busDto.setTPictures(pic);
		businessService.addPicture(busDto);
		
		/////////////上传完后 查询商户信息 
		return businessFind();
	}
	
	
	//  assist method
	private void copyFile(String realPath) {
		File file=new File(realPath);
		if (!file.exists()) 
			file.mkdir();
		try {
			FileUtils.copyFile(busImage, new File(file,busImageFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//------------------getter setter---------------------

	

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

	public BusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}

	public TBusiness getBusiness() {
		return business;
	}

	public void setBusiness(TBusiness business) {
		this.business = business;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public BusinessDto getBusDto() {
		return busDto;
	}

	public void setBusDto(BusinessDto busDto) {
		this.busDto = busDto;
	}

	public PictureService getPictureService() {
		return pictureService;
	}

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public TAddress getAddress() {
		return address;
	}

	public void setAddress(TAddress address) {
		this.address = address;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public BusinessDtoAnd getBusInfo() {
		return busInfo;
	}

	public void setBusInfo(BusinessDtoAnd busInfo) {
		this.busInfo = busInfo;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		BusinessAction.logger = logger;
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public BusFailedDto getBusFailed() {
		return busFailed;
	}

	public void setBusFailed(BusFailedDto busFailed) {
		this.busFailed = busFailed;
	}

	public CollectionService getCollectionService() {
		return collectionService;
	}

	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	
	
	
}
