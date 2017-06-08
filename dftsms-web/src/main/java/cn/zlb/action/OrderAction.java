package cn.zlb.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.zlb.dto.OrderAddDto;
import cn.zlb.dto.OrderDto;
import cn.zlb.dto.TItemorderDto;
import cn.zlb.dto.android.NotFoundDto;
import cn.zlb.dto.android.order.MesAndCod;
import cn.zlb.dto.android.order.OrderSuccess;
import cn.zlb.dto.android.order.orderDetail.ItemOrderDetail;
import cn.zlb.dto.android.order.orderDetail.OrderDetailDetail;
import cn.zlb.dto.android.order.orderDetail.OrderDetailDtoAnd;
import cn.zlb.entity.TCustomer;
import cn.zlb.entity.TGoods;
import cn.zlb.entity.TItemorder;
import cn.zlb.entity.TOrder;
import cn.zlb.entity.TStore;
import cn.zlb.service.GoodsService;
import cn.zlb.service.ItemOrderService;
import cn.zlb.service.OrderService;
import cn.zlb.service.StoreService;
import cn.zlb.tools.CommonUtils;
import cn.zlb.tools.Pager;
import net.sf.json.util.JSONUtils;
import cn.zlb.dto.StoreDto;;
public class OrderAction extends ActionSupport implements RequestAware, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object>request;
	private Map<String, Object>session;
	@Resource
	private OrderService orderService;
	@Resource
	private ItemOrderService itemOrderService;
	@Resource
	private GoodsService goodsService;
	@Resource
	private StoreService storeService;
	//店铺id
	private String fkStoId;
	//客户ID
	private String pkCusId;
	//订单状态
	private int ordStatus;
	//订单
	private TOrder order;
	//订单id
	private String pkOrdId;
	//订单项
	private String itemOrderJson; 
	//当前页码
	private Long currentPage;
	//商品json字符串
	private String jsonGooStr;
	
	static Gson gson=new Gson();
	
	//android 返回message
	private MesAndCod  cMes;
	private NotFoundDto notFound ;
	private NotFoundDto mes;
	
	//订单详情 json
	private OrderDetailDtoAnd<OrderDetailDetail<ItemOrderDetail>> orderDetail;
	private static Logger logger=Logger.getLogger(OrderAction.class);
	/**
	 * 1.0提交订单
	 */
	public String orderAdd(){
		System.out.println("提交订单");
		System.out.println("itemOrderJson:  = "+itemOrderJson);
		//   解析itemOrderJson字符串获得订单项信息
		OrderAddDto<TItemorderDto> orderAddDto=gson.fromJson(itemOrderJson,new TypeToken<OrderAddDto<TItemorderDto>>(){}.getType());
		List<TItemorderDto> itemList=orderAddDto.getItemOrder();
		//1-1 创建 order
		TCustomer customer=new TCustomer();
		customer.setPkCusId(orderAddDto.getCusId());
		 
		TOrder order=new TOrder();
		order.setPkOrdId(CommonUtils.uuid());
		order.setOrdStatus((byte) 1);
		order.setOrdTime(new Timestamp(System.currentTimeMillis()));
		order.setTCustomer(customer);
	
		
		
		//1-1-2 计算订单 总价
		BigDecimal total=new BigDecimal("0");
		for (TItemorderDto item  : itemList) {
			//获取总价格
			total=total.add(new BigDecimal(item.getIteSubtotal()+""));
		}
		order.setOrdTotal(total.toString());
		//1-1-3 添加 store属性
			 //获取店铺
			 TStore store=goodsService.findGoodsById(itemList.get(0).getGooId()).getTStore();
			 order.setTStore(store);
			 	
		//1-1-3 存储订单
		orderService.add(order);
		
		//------------------------------------------
		//1-2 添加提交的itemOrder
			
		
	
		 	//存储订单项
		List<TItemorder> itemOrderList=new ArrayList<TItemorder>();
		
		for (TItemorderDto item  : itemList) {
			TItemorder itemOrder=new TItemorder();
			//itemOrder添加基本属性
			itemOrder.setGooCurrprice(item.getGooCurrprice());
			itemOrder.setIteQuantity(item.getIteQuantity());
			itemOrder.setIteSubtotal(item.getIteSubtotal());
			itemOrder.setTOrder(order);
			//itemOrder 添加商品
			TGoods good=goodsService.findGoodsById(item.getGooId());
			itemOrder.setTGoods(good);
			//itemOrder  添加商品图片属性
			itemOrder.setGooImageL(good.getTPicturesByFkPictureSmall().getPicUrl());
			//添加 order
			itemOrder.setTOrder(order);
			//存储itemOrder 
			itemOrderService.add(itemOrder);
			//获得itemOrder集合
			itemOrderList.add(itemOrder);
			
		}
		 
		
		 	Set itemOrderSet=new HashSet(itemOrderList);
		 	order.setTItemorders(itemOrderSet); 
		 //1-4 保存订单
		
		
		System.out.println("添加订单:-----------------------------");
		System.out.println("orderid = "+order.getPkOrdId());
		//request.put("order", order);
		
		mes=NotFoundDto.SUCCESS(order.getPkOrdId());
		logger.info(gson.toJson(mes));
		return "mesResult";
	}
	
	/**
	 * 2.-1  支付确认
	 */ 
	public String orderPayment(){
		//1-1 查询订单状态
		String status=orderService.findOrdStatus(pkOrdId);
		System.out.println("order status = "+status);
		int statu =Integer.parseInt(status);
		System.out.println("order status int  = "+status);
		if (statu!=1) {
			//订单不是付款状态，不能确认订单
			// request.put("msg", "订单不能确认!!");
				mes=NotFoundDto.FAILED(pkOrdId);
				return "mesResult";
		}else{
		OrderDto ord=new OrderDto();
		ord.setOrdStatus((byte) 3);
		ord.setPkOrdId(pkOrdId);
		//1-2 更改状态
		orderService.modifyOrderStatus(ord);
		
		//request.put("msg", "交易成功!!!");
		mes=NotFoundDto.SUCCESS(pkOrdId);
		return "mesResult";
		}
	}
	/**
	 * 2.0确认订单
	 */ 
	public String orderConfirm(){
		//1-1 查询订单状态
		String status=orderService.findOrdStatus(pkOrdId);
		System.out.println("order status = "+status);
		int statu =Integer.parseInt(status);
		System.out.println("order status int  = "+status);
		if (statu!=3) {
			//订单不是付款状态，不能确认订单
			// request.put("msg", "订单不能确认!!");
				mes=NotFoundDto.FAILED(pkOrdId);
				return "mesResult";
		}else{
		OrderDto ord=new OrderDto();
		ord.setOrdStatus((byte) 4);
		ord.setPkOrdId(pkOrdId);
		//1-2 更改状态
		orderService.modifyOrderStatus(ord);
		
		//request.put("msg", "交易成功!!!");
		mes=NotFoundDto.SUCCESS(pkOrdId);
		return "mesResult";
		}
	}
	/**
	 * 3.0取消订单
	 */
	public String orderCancel(){
		//1-1 查询订单状态
		String status=orderService.findOrdStatus(pkOrdId);
		int statu =Integer.parseInt(status);
		if (statu!=1) {
			//订单不是未支付状态，不能取消订单
			 //request.put("msg", "订单不能取消!!");
				mes=NotFoundDto.FAILED(pkOrdId);
				return "mesResult";
			}
		OrderDto ord=new OrderDto();
		ord.setOrdStatus((byte) 5);
		ord.setPkOrdId(pkOrdId);
		//1-2 更改状态
		orderService.modifyOrderStatus(ord);
				
		//request.put("msg", "取消成功!!!");
		mes=NotFoundDto.SUCCESS(pkOrdId);
		return "mesResult";
	}
	/**
	 *4.0 商家接单
	 */
	public String orderAccept(){
		//1-1 查询订单状态
		String status=orderService.findOrdStatus(pkOrdId);
		int statu =Integer.parseInt(status);
		if (statu!=2) {
			//订单不是支付状态，不能接受订单
		//request.put("msg", "用户未支付!!");
			 return " ";
		}
		OrderDto ord=new OrderDto();
	    ord.setOrdStatus((byte) 3);
		ord.setPkOrdId(pkOrdId);
		//1-2 更改状态
		orderService.modifyOrderStatus(ord);
						
		request.put("msg", "接单成功!!!");
		return " ";
	}
	/**
	 * 5.0删除订单
	 */
	public String orderDelete(){
		//1-1 查询订单状态
		 String status=orderService.findOrdStatus(pkOrdId);
		 int statu =Integer.parseInt(status);
		 if (statu!=5) {
		 //订单不是 取消状态，不能删除订单
		// request.put("msg", "未取消订单， 不能删除!!");
			mes=NotFoundDto.FAILED(pkOrdId);
			return "mesResult";
			 }
		 
		 //1-2 删除订单
		 orderService.deleteOrderByCus(pkOrdId, pkCusId);
								
		// request.put("msg", "取消成功!!!");
			mes=NotFoundDto.SUCCESS(pkCusId);
			return "mesResult";		
	}
	/**
	 * 6.0查看订单详情
	 */
	
	public String orderFindById(){
		orderDetail=new OrderDetailDtoAnd<OrderDetailDetail<ItemOrderDetail>>();
		
		//1.0找到用户订单
		TOrder order=orderService.findOrderById(pkOrdId);
		
		//2.0找到order 所属的 itemOrder
		List<TItemorder> itemList=itemOrderService.findItemByOrderId(pkOrdId);
		if (order==null || itemList.isEmpty()) {
			mes=NotFoundDto.FAILED(pkOrdId);
			return "mesResult";
		}
		//获取orderDD
		OrderDetailDetail<ItemOrderDetail> orderDD = orderDetailManage(order, itemList);
		//
		orderDD.setCode("1");
		orderDetail.setPageItems(orderDD);
		
		//request.put("order", orderDetail);
		  
		return "orderResult";
	}
	private OrderDetailDetail<ItemOrderDetail> orderDetailManage(TOrder order, List<TItemorder> itemList) {
		//1.0-1  orderDetail 信息
		OrderDetailDetail<ItemOrderDetail> orderDD=new OrderDetailDetail<ItemOrderDetail>();
		orderDD.setStorId(order.getTStore().getFkStoId());
		orderDD.setShopImg(order.getTStore().getTPictures().getPicUrl());
		orderDD.setShopName(order.getTStore().getStoName());
		orderDD.setOrderState(order.getOrdStatus()+"");
		orderDD.setOrderNum(order.getPkOrdId());
		orderDD.setTime(order.getOrdTime()+"");
		orderDD.setAllPrice(order.getOrdTotal());
		
		//1.0-2 判断订单状态设置 payPrice （已经支付的金额）
		if ((order.getOrdStatus()+"").equals(1+"")) {
			orderDD.setPayPrice(0+"");
		}else{
			orderDD.setPayPrice(order.getOrdTotal());
		}
		
			//3.0遍历设置
			ItemOrderDetail itemOrder=null;
			List<ItemOrderDetail> itemOrderList=new ArrayList<ItemOrderDetail>();
			for (TItemorder item : itemList) {
				itemOrder=new ItemOrderDetail();
				itemOrder.setCount(item.getIteQuantity()+"");
				itemOrder.setPrice(item.getIteSubtotal());
				itemOrder.setGoodName(item.getTGoods().getGooName());
				
				//添加 到list
				itemOrderList.add(itemOrder);
			}

				orderDD.setList(itemOrderList);
				return orderDD;
		}
	/**
	 * 7.0 查询用户订单
	 */
	public String orderFindByCusId(){
		Pager<TOrder> pages=new Pager<TOrder>();
		pages.setCurrentPage(currentPage);
		
		// 1-1 查询用户订单order
		Pager<TOrder> pager=orderService.findOrderByCus(pkCusId, pages);
		
		request.put("pager", pager);
		return "";
	}
	
	/**
	 * 8.0 扫码支付
	 */
	/*private PayUtils payUtils;
	public String orderPay(){
		//1-1 根据ordId查询订单
		TOrder order=orderService.findOrderById(pkOrdId);
		
		//1-2 查询店铺
		StoreDto stoDto=new StoreDto();
		stoDto.setFkStoId(order.getTStore().getFkStoId());
		System.out.println("店铺ID = "+stoDto.getFkStoId());
		TStore store=storeService.findStore(stoDto);
		
		//1-3 set
		order.setTStore(store);
		ServletActionContext context = null;
		payUtils.test_trade_precreate(order,context);
		
		return "qrcode";
	}*/
	
	//test
	public String orderMes(){
		//cMes=new OrderSuccess();
		//notFound=NotFoundDto.newInstance("1","成功");
		//System.out.println("cMes =" +gson.toJson(cMes));
		//mes=NotFoundDto.SUCCESS();
		mes=NotFoundDto.FAILED(pkOrdId);
		return "mesResult"; 
		
	}
	//setter
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}
 
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public ItemOrderService getItemOrderService() {
		return itemOrderService;
	}
	public void setItemOrderService(ItemOrderService itemOrderService) {
		this.itemOrderService = itemOrderService;
	}
	public GoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	public String getFkStoId() {
		return fkStoId;
	}
	public void setFkStoId(String fkStoId) {
		this.fkStoId = fkStoId;
	}
	public String getPkCusId() {
		return pkCusId;
	}
	public void setPkCusId(String pkCusId) {
		this.pkCusId = pkCusId;
	}
	public int getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(int ordStatus) {
		this.ordStatus = ordStatus;
	}
	public TOrder getOrder() {
		return order;
	}
	public void setOrder(TOrder order) {
		this.order = order;
	}
	public String getPkOrdId() {
		return pkOrdId;
	}
	public void setPkOrdId(String pkOrdId) {
		this.pkOrdId = pkOrdId;
	}
	public String getItemOrderJson() {
		return itemOrderJson;
	}
	public void setItemOrderJson(String itemOrderJson) {
		this.itemOrderJson = itemOrderJson;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public String getJsonGooStr() {
		return jsonGooStr;
	}
	public void setJsonGooStr(String jsonGooStr) {
		this.jsonGooStr = jsonGooStr;
	}
	/*public PayUtils getPayUtils() {
		return payUtils;
	}
	public void setPayUtils(PayUtils payUtils) {
		this.payUtils = payUtils;
	}*/
	public Map<String, Object> getRequest() {
		return request;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public StoreService getStoreService() {
		return storeService;
	}
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	public static Gson getGson() {
		return gson;
	}
	public static void setGson(Gson gson) {
		OrderAction.gson = gson;
	}
	public MesAndCod getcMes() {
		return cMes;
	}
	public void setcMes(MesAndCod cMes) {
		this.cMes = cMes;
	}
	public NotFoundDto getNotFound() {
		return notFound;
	}
	public void setNotFound(NotFoundDto notFound) {
		this.notFound = notFound;
	}
	public NotFoundDto getMes() {
		return mes;
	}
	public void setMes(NotFoundDto mes) {
		this.mes = mes;
	}
	public OrderDetailDtoAnd<OrderDetailDetail<ItemOrderDetail>> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(OrderDetailDtoAnd<OrderDetailDetail<ItemOrderDetail>> orderDetail) {
		this.orderDetail = orderDetail;
	}
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		OrderAction.logger = logger;
	}
	
	
	

}
