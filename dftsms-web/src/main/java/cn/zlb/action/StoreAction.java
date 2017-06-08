package cn.zlb.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.zlb.dto.StoreDto;
import cn.zlb.entity.TCheap;
import cn.zlb.entity.TPictures;
import cn.zlb.entity.TStore;
import cn.zlb.entity.TStoreCategory;
import cn.zlb.service.CategoryService;
import cn.zlb.service.CheapService;
import cn.zlb.service.GoodsService;
import cn.zlb.service.PictureService;
import cn.zlb.service.StoreService;
import cn.zlb.tools.CheapDto;
import cn.zlb.tools.CommonUtils;
import cn.zlb.tools.Pager;

public class StoreAction extends ActionSupport implements RequestAware,SessionAware {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(StoreAction.class);
	private Map<String, Object> request;
	private Map<String, Object> session;
	@Resource
	private StoreService storeService;
	@Resource
	private CheapService cheapService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private GoodsService goodsService;
	@Resource
	private PictureService pictureService;
	private TStore store;
	private StoreDto stoDto;
	private Long currentPage;
	//上传文件 得到文件 文件名 文件类型
	private File stoImage;
	private String stoImageFileName;
	private String stoImageContentType;
	/**
	 * 1.0 修改店铺
	 * @return
	 */
	public String storeModify(){
		storeService.update(store);
		request.put("msg","修改完成");
		return "success";
		
	}
	/**
	 * 2.0 查找店铺
	 * @return
	 */
	public String storeFindone(){
		//1-1  找出店铺
		TStore store=storeService.findStore(stoDto);
		
		//1-2   找出店铺优惠信息
		CheapDto cheDto=new CheapDto();
		cheDto.setFkStoId(store.getFkStoId());
		List<TCheap> cheList=cheapService.findCheap(cheDto);
		
		//1-3 找出店铺目录信息
		List<TStoreCategory> catList=categoryService.findAllCategory(store.getFkStoId());
		//set
		store.setTCheaps((Set) cheList);
		store.setTStoreCategories((Set) catList);
		
		//1-4 查找 店铺推荐的商品
			///////////////////////查找商品
		
		request.put("store", store);
		request.put("msg", "查询完毕");
		return "success";
	}
	
 
	
	
	/**
	 * 3.0根据 店铺名称 模糊查询
	 * @param stoDto store的 Dto对象 存储相关信息 由前台传入
	 * @return
	 */
	public String storeSearchByName(){
		//设置分页
		Pager<TStore> pages=new Pager<TStore>();
		pages.setCurrentPage(currentPage);
		
		//分页查询
		Pager<TStore> pager=storeService.serachStoreByName(stoDto, pages);
		
		request.put("pager", pager);
		
		return "success";
	}
	/**
	 * 4.0根据店铺 评分 模糊查询
	 * @return
	 */
	public String storeSearchByScore(){
		//设置分页
		Pager<TStore> pages=new Pager<TStore>();
		pages.setCurrentPage(currentPage);
		
		//分页查询
		Pager<TStore> pager=storeService.serachStoreByScore(stoDto, pages);
		
		request.put("pager", pager);
		return "success";
	}
	
	/**
	 * x.0上传图片  上传商家头像
	 */
	public String storeUploadPic(){
		System.out.println("文件上传");
		//1-获取真实路径
		String realPath=ServletActionContext.getServletContext().getRealPath("/pictures");
		logger.info("上传文件存储路径= " +realPath);
		logger.info("上传文件名           = "+stoImageFileName);
		
		//2-避免文件名重复，处理文件名
		stoImageFileName=CommonUtils.uuid()+"_"+stoImageFileName;
		logger.info("处理后的文件名   =  "+stoImageFileName);
		
		//3-创建File对象
		copyFile(realPath);
		
		//4-保存图片到数据库
		String picUrl="pictures/"+stoImageFileName;
		
		TPictures pic=new TPictures();
		pic.setPicUrl(picUrl);
		pic.setPicDes("店铺头像");
		pictureService.add(pic);
		
			//4-1 保存图片到 店铺数据表
		stoDto.setTPictures(pic);
		storeService.addPicture(stoDto);
		
		/////////////上传完后 查询商户信息 
		
		return storeFindone();
	}
	
	//  assist method
	private void copyFile(String realPath) {
		File file=new File(realPath);
		if (!file.exists()) 
			file.mkdir();
		try {
			FileUtils.copyFile(stoImage, new File(file,stoImageFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//setter

	@Override
	public void setSession(Map<String, Object> arg0) {
		
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
 
	public TStore getStore() {
		return store;
	}
 
	public void setStore(TStore store) {
		this.store = store;
	}

	public StoreDto getStoDto() {
		return stoDto;
	}

	public void setStoDto(StoreDto stoDto) {
		this.stoDto = stoDto;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public CheapService getCheapService() {
		return cheapService;
	}
	public void setCheapService(CheapService cheapService) {
		this.cheapService = cheapService;
	}
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public GoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	
	
	
}
