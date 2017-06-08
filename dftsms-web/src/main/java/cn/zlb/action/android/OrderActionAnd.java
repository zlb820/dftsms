package cn.zlb.action.android;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.annotations.Source;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import cn.zlb.dto.android.NotFoundDto;
import cn.zlb.dto.android.order.BaseOrderDtoAnd;
import cn.zlb.dto.android.order.BaseOrderDtoAndAll;
import cn.zlb.dto.android.order.OrderDtoAndDetail;
import cn.zlb.dto.android.order.OrderNode;
import cn.zlb.dto.android.store.StoreDtoAndDetail;
import cn.zlb.dto.android.store.StoreNode;
import cn.zlb.entity.TOrder;
import cn.zlb.entity.TStore;
import cn.zlb.service.CommentService;
import cn.zlb.service.OrderService;
import cn.zlb.service.StoreService;

public class OrderActionAnd extends ActionSupport {
 
	private static final long serialVersionUID = 1L;
	@Resource
	private OrderService orderService;
	@Resource
	private StoreService storeService;
	@Resource
	private CommentService commentService;
	//用户id
	private String cusId;
	//订单状态
	private String ordStatus;
	//order withe store
	private BaseOrderDtoAnd orderInfo;
	//all orders
	private BaseOrderDtoAndAll<OrderDtoAndDetail> orderAll;
	//未找到 返回的json
	private NotFoundDto notFound ;
	/**
	 * 1.0用户订单首页数据
	 * 		>订单数据
	 * 		>推荐店铺数据
	 * @return
	 */
	public String orderDetail(){
	 
		
		//1.0 查找用户最近订单
				List<TOrder> ordList=orderService.findOrderByCusRecent(cusId);
				Object[] obj = new Object[2];
				obj[0]=orderInfoManage(ordList);
				
				//2.0查找最近店铺
				obj[1] = storeInfoManage();
				    
				orderInfo=new BaseOrderDtoAnd();
				orderInfo.setPageItems(obj);
				orderInfo.setPagestamp("1");
		Gson gson=new Gson();
		System.out.println(gson.toJson(orderInfo));
		return "orderResult";
	}
	 
	private StoreNode<StoreDtoAndDetail> storeInfoManage() {
		List<TStore> stoList=storeService.findStoreRecent();
		System.out.println("stoList id = "+stoList.get(0).getStoName());
		StoreDtoAndDetail stoDetail=null;
		List<StoreDtoAndDetail>  stoDetailList=new ArrayList<StoreDtoAndDetail>();
		StoreNode<StoreDtoAndDetail> stoNode=null;
		
		for (TStore store : stoList) {
			 
			stoDetail=new StoreDtoAndDetail();
			stoDetail.setStoID(store.getFkStoId());;
			stoDetail.setStoName(store.getStoName());
			stoDetail.setImgUrl(store.getTPictures().getPicUrl());
			stoDetail.setSaleValume(store.getTGood().getGooSales());
			stoDetail.setAddConcrete(store.getTAddress().getAddConcrete());
			stoDetail.setScore(commentService.findStoreLevel(store.getFkStoId())+"");
			
			stoDetailList.add(stoDetail);
		}
		
		   stoNode=new  StoreNode<StoreDtoAndDetail>();
		   stoNode.setList(stoDetailList);
		   
		return stoNode;
	}
	private  OrderNode<OrderDtoAndDetail> orderInfoManage(List<TOrder> ordList) {
		OrderDtoAndDetail ordDetail = null;
		List<OrderDtoAndDetail> ordDetailList=new ArrayList<OrderDtoAndDetail>();
		OrderNode<OrderDtoAndDetail> ordNode=null;
		for (TOrder order : ordList) {
			ordDetail=new OrderDtoAndDetail();
			ordDetail.setTypeId("31");
			ordDetail.setOrderTitle(order.getTStore().getStoName());
			ordDetail.setOrderState(order.getOrdStatus()+"");
			ordDetail.setOrderTime(order.getOrdTime().toString());
			ordDetail.setPrice(order.getOrdTotal());
			ordDetail.setPicUrl(order.getTStore().getTPictures().getPicUrl());
			ordDetail.setStoId(order.getTStore().getFkStoId());
			ordDetail.setOrdId(order.getPkOrdId());
			
			
			ordDetailList.add(ordDetail);
		}
		ordNode=new OrderNode<OrderDtoAndDetail>();
		ordNode.setOrders(ordDetailList);
		 
		return ordNode;
	}
	
	/**
	 * 2.0查看用户所有订单
	 * @return
	 */
	public String orderAll(){
		//1.0 找到用户所有订单
		List<TOrder> ordList=orderService.findOrderByCus(cusId);
		
	 
		//2.0 获取 orderDtoDetail 的集合 list
		List<OrderDtoAndDetail> orderDetailList=orderInfoManage(ordList).getOrders();
		
		//3.0   设置 pageitems  orderAll 对象需要new
		orderAll=new BaseOrderDtoAndAll<OrderDtoAndDetail>();
		orderAll.setPageItems(orderDetailList);
		 
			
		return "orderAllResult"; 
	}
	
	/**
	 * 3.0根据订单状态查询
	 * @return
	 */
	public String orderFindByStatus(){
		 //1.0 找到用户所有订单
		     List<TOrder> ordList=orderService.findOrderByStatus(ordStatus);
		  
		 //2.0 获取 orderDtoDetail 的集合 list
			 List<OrderDtoAndDetail> orderDetailList=orderInfoManage(ordList).getOrders();
				
		 //3.0   设置 pageitems  orderAll 对象需要new
			 orderAll=new BaseOrderDtoAndAll<OrderDtoAndDetail>();
			 orderAll.setPageItems(orderDetailList);
				 
					
				return "orderAllResult";
		 
	}
	
	//test
	public String orderTest(){
		notFound=NotFoundDto.newInstance("1","成功");
		orderAll=new BaseOrderDtoAndAll<OrderDtoAndDetail>();
		System.out.println("orderTest = " +notFound.getCode());
		return "notFoundResult";
	}
	
	
	
	
	
	
	
	
	public BaseOrderDtoAnd getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(BaseOrderDtoAnd orderInfo) {
		this.orderInfo = orderInfo;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public StoreService getStoreService() {
		return storeService;
	}
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public BaseOrderDtoAndAll<OrderDtoAndDetail> getOrderAll() {
		return orderAll;
	}

	public void setOrderAll(BaseOrderDtoAndAll<OrderDtoAndDetail> orderAll) {
		this.orderAll = orderAll;
	}

	public String getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}

	public NotFoundDto getNotFound() {
		return notFound;
	}

	public void setNotFound(NotFoundDto notFound) {
		this.notFound = notFound;
	}
	 
	
	
}
