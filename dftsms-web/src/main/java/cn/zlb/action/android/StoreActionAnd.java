package cn.zlb.action.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.zlb.dto.GoodsDto;
import cn.zlb.dto.android.BaseDtoAnd;
import cn.zlb.dto.android.good.GoodAnalyze;
import cn.zlb.dto.android.good.GoodDtoAndDetail;
import cn.zlb.dto.android.good.GoodsNode;
import cn.zlb.entity.TCart;
import cn.zlb.entity.TCheap;
import cn.zlb.entity.TGoods;
import cn.zlb.entity.TStore;
import cn.zlb.entity.TStoreCategory;
import cn.zlb.service.CategoryService;
import cn.zlb.service.GoodsService;
import cn.zlb.service.StoreService;
//import cn.zlb.springtest.goods.GoodDtoTest;
import cn.zlb.tools.CheapDto;
import cn.zlb.tools.JsonUtils;

public class StoreActionAnd extends ActionSupport implements RequestAware, SessionAware {

	private static final long serialVersionUID = 1L;
	// 返回给and客户端 结果
	private BaseDtoAnd<GoodsNode<GoodDtoAndDetail>> storeInfo;
	// 客户端提交数据
	private String stoId;

	// service
	@Resource
	private CategoryService categoryService;
	@Resource
	private StoreService storeService;
	@Resource
	private GoodsService goodService;

	private Map request;
	private Map session;
	private final Logger logger = Logger.getLogger(StoreActionAnd.class);

	/**
	 * 1.0 获取店铺商品信息
	 * 
	 * @return
	 */
	public String stoDetail() {
		// 1-0 获取参数 gson 转化
		// GoodAnalyze goodAnalyze=JsonUtils.getInstance().fromJson(stoStr,
		// GoodAnalyze.class);
		// 1-1 找出店铺

		// TStore store=storeService.findStore(stoDto);

		// 1-2 找出店铺优惠信息
		/*
		 * CheapDto cheDto=new CheapDto();
		 * cheDto.setFkStoId(store.getFkStoId()); List<TCheap>
		 * cheList=cheapService.findCheap(cheDto);
		 */

		// 1-3 找出店铺目录信息
		List<TStoreCategory> categoryWithPids = categoryService.findCategoryWithPid("001");
		// 1-4 获取店铺商品信息
		List<GoodsNode<GoodDtoAndDetail>> nodeList = storeInfoManage(categoryWithPids);
		// 1-5 设置信息
		storeInfo = new BaseDtoAnd<GoodsNode<GoodDtoAndDetail>>();
		storeInfo.setPageItems(nodeList);
		if (session.get("user") != null) {
			storeInfo.setLogin(true);
		}
		logger.info("storeInfo = " + JsonUtils.getInstance().toJson(storeInfo));
		System.out.println("storeInfo = " + JsonUtils.getInstance().toJson(storeInfo));

		return "storeResult";
	}

	/**
	 * 1.0 -1 店铺商品信息组织
	 * 
	 * @param categoryWithPids
	 * @return
	 */
	private List<GoodsNode<GoodDtoAndDetail>> storeInfoManage(List<TStoreCategory> categoryWithPids) {
		GoodsDto gooDto = new GoodsDto();
		GoodDtoAndDetail detail = null;
		List<GoodDtoAndDetail> detailList = null;
		GoodsNode<GoodDtoAndDetail> node = null;
		List<GoodsNode<GoodDtoAndDetail>> nodeList = new ArrayList<GoodsNode<GoodDtoAndDetail>>();
		for (TStoreCategory cate : categoryWithPids) {
			// 1-3-1 根据二级目录找出 二级目录下的所有商品
			gooDto.setPkCatId(cate.getPkCatId());
			List<TGoods> goods = goodService.findGoodsByStoreId(gooDto);

			detailList = new ArrayList<GoodDtoAndDetail>();
			for (TGoods goo : goods) {
				detail = new GoodDtoAndDetail();
				detail.setId(goo.getPkGooId());
				detail.setImgUrl(goo.getTPicturesByFkPictureSmall().getPicUrl());
				detail.setName(goo.getGooName());
				detail.setPrice(goo.getGooPrice());
				detail.setSellCount(goo.getGooSales());

				detailList.add(detail);

			}
			node = new GoodsNode<GoodDtoAndDetail>();
			node.setSortName(cate.getCatName());
			node.setList(detailList);

			nodeList.add(node);

		}
		return nodeList;
	}

	// test
	/*
	 * public String stoDetailTest(){ storeInfo=GoodDtoTest.baseDtoAnd(); return
	 * "storeResult"; }
	 */

	// setter

	public String getStoId() {
		return stoId;
	}

	public BaseDtoAnd<GoodsNode<GoodDtoAndDetail>> getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(BaseDtoAnd<GoodsNode<GoodDtoAndDetail>> storeInfo) {
		this.storeInfo = storeInfo;
	}

	public void setStoId(String stoId) {
		this.stoId = stoId;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public GoodsService getGoodService() {
		return goodService;
	}

	public void setGoodService(GoodsService goodService) {
		this.goodService = goodService;
	}

	public Map getRequest() {
		return request;
	}

	public void setRequest(Map request) {
		this.request = request;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

}
