package cn.zlb.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.zlb.dto.GoodsDto;
import cn.zlb.entity.TGoods;
import cn.zlb.entity.TPictures;
import cn.zlb.entity.TStore;
import cn.zlb.entity.TStoreCategory;
import cn.zlb.service.GoodsService;
import cn.zlb.service.PictureService;
import cn.zlb.tools.CommonUtils;
import cn.zlb.tools.Pager;

public class GoodsAction extends ActionSupport implements RequestAware, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(GoodsAction.class);
	private Map<String, Object> request;
	private Map<String, Object> session;
	@Resource
	private GoodsService goodsService;
	@Resource
	private PictureService pictureService;
	//目录id
	private String pkCatId;
	//当前页
	private Long currentPage;
	//商品
	private TGoods goods;
	//商品dto
	private GoodsDto gooDto;
	//商品名
	private String gooName;
	//商品id
	private String pkGooId;
	//店铺Id
	private String fkStoId;
	
	//文件上传
	private List<File> gooImage;
	private List<String> gooImageFileName;
	private List<String> gooImageContentType;
	/**
	 * 1.0根据目录查询
	 * @return
	 */
	public String goodsFindBycategory(){
		GoodsDto dto=new GoodsDto();
		dto.setPkCatId(pkCatId);
		
		Pager<TGoods> pages=new Pager<TGoods>();
		pages.setCurrentPage(currentPage);
		
		Pager<TGoods> pager=goodsService.findGoods(dto, pages);
		
		request.put("pager", pager);
		request.put("msg", "查询完毕");
		return "success";
	}
	/**
	 * 1.1 根据店铺 id 查询店铺的商品 分页查询
	 */
	public String goodsFindByStoreWithPager(){
		GoodsDto dto=new GoodsDto();
		dto.setPkCatId(fkStoId);
		
		Pager<TGoods> pages=new Pager<TGoods>();
		pages.setCurrentPage(currentPage);
		
		Pager<TGoods> pager=goodsService.findGoods(dto, pages);
		
		request.put("pager", pager);
		request.put("msg", "查询完毕");
		return "success";
		
	}
	
	/**
	 * 1.2 根据店铺 id 查询店铺的商品   普通查询
	 */
	public String goodsFindByStore(){
		GoodsDto dto=new GoodsDto();
		dto.setPkGooId(pkGooId);
		List<TGoods> gooList=goodsService.findGoodsByStoreId(dto);
		
		request.put("goods", gooList);
		request.put("msg", "查询完毕");
		return "success";
		
	}
	/**
	 * 2.0根据名称  模糊查询
	 * @return
	 */
	public String goodsFindByName(){
		Pager<TGoods> pages=new Pager<TGoods>();
		pages.setCurrentPage(currentPage);
		
		Pager<TGoods> pager=goodsService.searchGoodsByName(gooName, pages);
		
		request.put("pager", pager);
		return "success";
	}
	/**
	 * 3.0 根据评分查询
	 * @return
	 */
	public String goodsFindByScore(){
		Pager<TGoods> pages=new Pager<TGoods>();
		pages.setCurrentPage(currentPage);
		
		GoodsDto dto=new GoodsDto();
		dto.setMaxPrice(gooDto.getMaxPrice());
		dto.setGooCurrprice(gooDto.getGooCurrprice());
		Pager<TGoods> pager=goodsService.searchGoods(dto, pages);
		
		request.put("good", pager);
		return "success";
		 
	}
	
	/**
	 * 4.0根据商品 id 查询
	 * @return
	 */
	public String goodsFindById(){
	 
		
		TGoods good=goodsService.findGoodsById(pkGooId);
		
		request.put("good", good);
		return "success";
	}
	
	/**
	 * 5.0 添加商品
	 * @return
	 */
	public String goodsAdd(){
		//1-1 添加商品 店铺信息
		TStore store=new TStore();
		store.setFkStoId(fkStoId);
		
		// 1-2 添加商品 目录信息
		TStoreCategory cate=new TStoreCategory();
		cate.setPkCatId(pkCatId);
		
		//set
		goods.setTStoreCategory(cate);
		goods.setTStore(store);
		
		goodsService.add(goods);
		
		//1-3 存储 商品图片 大小图
		goodsUploadPic();
		return "";
	}
	
	/**
	 * 6.0 更改商品
	 * @return
	 */
	public String goodsUpdate(){
		//1-1 添加商品 店铺信息
		 TStore store=new TStore();
	     store.setFkStoId(fkStoId);
				
		 // 1-2 添加商品 目录信息
		 TStoreCategory cate=new TStoreCategory();
		 cate.setPkCatId(pkCatId);
				
		 //set
		 goods.setTStoreCategory(cate);
		 goods.setTStore(store);
				
		 goodsService.update(goods);
		 return "success";
	 
	}
	
	/**
	 * 7.0删除商品
	 * @return
	 */
	public String goodsDelete(){
		TGoods good=new TGoods();
		good.setPkGooId(pkGooId);
		goodsService.delete(good);
		return "";
	}
	
	
	/**
	 * 8.0图片文件上传
	 * @return
	 */
	public String goodsUploadPic(){
		logger.info("商品图片上传");
		String realPath=ServletActionContext.getServletContext().getRealPath("/pictures");
		logger.info("真实路径realpath:"+realPath);
		for (int i = 0; i < gooImage.size(); i++) {
			saveGooImage(realPath, gooImage.get(i), i);
		}
		
		
		//return  goodsFindByStore();
		return "";
	}
	/**
	 * 文件上传方法 
	 * @param realpath 存储文件的真实路径
	 * @param file		文件对象
	 * @param picAtr	文件的属性(大图 or 小图)
	 */
	protected void saveGooImage(String realpath,File file,int picAtr){
		
		//1- 获取文件名  并处理
		String gooImageName=gooImageFileName.get(picAtr);
		gooImageName=CommonUtils.uuid()+"_"+gooImageName;
		logger.info("处理后的文件名:"+gooImageName);
		
		//2- 存储文件
		copyFile(file,gooImageName,realpath);
		
		//3- 保存文件路径到 数据库
		//保存 图片 id到 图片 数据表
		String picUrl="pictures/"+gooImageName;
		
		//3-1 图片对象
		TPictures pic=new TPictures();
		pic.setPicUrl(picUrl);
		pic.setPkPicId(CommonUtils.uuid());
		
		//3-2 商品dto对象 用于 添加图片
		GoodsDto gooDto=new GoodsDto();
		//gooDto.setPkGooId(goods.getPkGooId());
		
		//3-3 判断 大小图 分别存储
		if (picAtr==0) {
			pic.setPicDes("商品大图");
			pic.setPicAttr(0+"");
			pictureService.add(pic);
			
			//存储 商品图片id 到 商品 表
			//gooDto.setTPicturesByFkPictureBig(pic);
			//goodsService.addPictureBig(gooDto);
		}else {
			pic.setPicDes("商品小图");
			pic.setPicAttr(1+"");
			pictureService.add(pic);
			
			//存储 商品图片id 到 商品 表
			//gooDto.setTPicturesByFkPictureSmall(pic);
			//goodsService.addPictureSmall(gooDto);
		}
				
		 
	}
	
	
	private void copyFile(File file, String gooImageName,String realPath) {
		//存储文件
		File fileS=new File(realPath);
		if (!fileS.exists()) 
			fileS.mkdir();
		try {
			FileUtils.copyFile(file, new File(fileS,gooImageName));
		} catch (IOException e) {
			 logger.error("图片文件上传错误"+this.getClass().getName());
		}
		
		
		
		
	}
	//setter
	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public GoodsDto getGooDto() {
		return gooDto;
	}

	public void setGooDto(GoodsDto gooDto) {
		this.gooDto = gooDto;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}
	public String getPkCatId() {
		return pkCatId;
	}
	public void setPkCatId(String pkCatId) {
		this.pkCatId = pkCatId;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public TGoods getGoods() {
		return goods;
	}
	public void setGoods(TGoods goods) {
		this.goods = goods;
	}
	public String getGooName() {
		return gooName;
	}
	public void setGooName(String gooName) {
		this.gooName = gooName;
	}
	public String getPkGooId() {
		return pkGooId;
	}
	public void setPkGooId(String pkGooId) {
		this.pkGooId = pkGooId;
	}
	public String getFkStoId() {
		return fkStoId;
	}
	public void setFkStoId(String fkStoId) {
		this.fkStoId = fkStoId;
	}
	public PictureService getPictureService() {
		return pictureService;
	}
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}
	public List<File> getGooImage() {
		return gooImage;
	}
	public void setGooImage(List<File> gooImage) {
		this.gooImage = gooImage;
	}
	public List<String> getGooImageFileName() {
		return gooImageFileName;
	}
	public void setGooImageFileName(List<String> gooImageFileName) {
		this.gooImageFileName = gooImageFileName;
	}
	public List<String> getGooImageContentType() {
		return gooImageContentType;
	}
	public void setGooImageContentType(List<String> gooImageContentType) {
		this.gooImageContentType = gooImageContentType;
	}
	
	

}
