package cn.zlb.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zlb.dao.ItemOrderDao;
import cn.zlb.dao.OrderDao;
import cn.zlb.dto.OrderDto;
import cn.zlb.entity.TItemorder;
import cn.zlb.entity.TOrder;
import cn.zlb.exception.OrderNotFoundException;
import cn.zlb.service.ItemOrderService;
import cn.zlb.service.OrderService;
import cn.zlb.tools.CommonUtils;
import cn.zlb.tools.Pager;
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{
	@Resource
	private OrderDao orderDao;
	private ItemOrderService itemOrderService;
	
	
	
	@Override
	public Serializable add(TOrder order) {
		 
		return orderDao.add(order);
	}

	@Override
	public void update(TOrder order) {
		orderDao.update(order);
	}

	@Override
	public void saveOrUpdate(TOrder order) {
		orderDao.saveOrUpdate(order);
	}

	@Override
	public void delete(TOrder order) {
		orderDao.delete(order);
	}
	
	
	
	/**
	 * 4.0 查看订单详情  （包含订单所有订单项）
	 */
	@Override
	public TOrder findOrderById(String ordId) {
		//1-1 找到order
		String hql="from TOrder ord where ord.pkOrdId =?";
		TOrder order=(TOrder) orderDao.findObject(hql, new Object[]{ordId});
		//1-2 找到order 所有 订单项
	    //List<TItemorder> itemList= itemOrderService.findItemByOrderId(ordId);
	    //set
	    
	   
		return order;
	}
	
	/**
	 * 5.0 修改订单总计
	 */
	@Override
	public int modifyOrderTotal(OrderDto ordDto) {
		String hql="update TOrder ord set ord.ordTotal =? where ord.pkOrdId=?";
		int i=orderDao.updateByObject(hql, new Object[]{ordDto.getOrdTotal(),ordDto.getPkOrdId()});
		return i;
	}
	
	/**
	 * 6.0修改订单状态
	 */
	@Override
	public int modifyOrderStatus(OrderDto ordDto) {
		String hql="update TOrder ord set ord.ordStatus =? where ord.pkOrdId=? ";
		int i=orderDao.updateByObject(hql,new Object[]{ordDto.getOrdStatus(),ordDto.getPkOrdId()});
		return i;
	}
	
	/**
	 * 7.0 查询用户order
	 * @param 用户id
	 */

	@Override
	public Pager<TOrder> findOrderByCus(String pkCusId,Pager<TOrder> pages) {
		String hql="from TOrder ord where ord.TCustomer.pkCusId =?";
		Pager<TOrder> pager=orderDao.query(hql, new Object[]{pkCusId}, pages);
		if (pager.getResultList()==null) {
			throw new OrderNotFoundException("订单未找到");
		}
		return pager;
	}
	
	/**
	 * 7.1 查询用户所有订单
	 */
	@Override
	public List<TOrder> findOrderByCus(String cusId) {
		String hql="from TOrder ord where ord.TCustomer.pkCusId =?";
		List<TOrder> ordList=orderDao.listByObject(hql, new Object[]{cusId});
		 
		return ordList;
	}
	
	/**
	 * 7.1 按照订单状态查询
	 */
	@Override
	public List<TOrder> findOrderByStatus(String status) {
		String hql="from TOrder ord where ord.ordStatus =?";
		List<TOrder> ordList=orderDao.findOrderByStatus(hql,status) ;
		 
		return ordList;
	}

	/**
	 * 7.1查询用户最近订单
	 */
		
	
	@Override
	public List<TOrder> findOrderByCusRecent(String pkCusId) {
		String hql="from TOrder ord where ord.TCustomer.pkCusId =? order by ord.ordTime desc";
		List<TOrder> ordList=orderDao.findOrderRecent(hql,pkCusId);
		 
		return ordList;
	}

	/**
	 * 8.0 查找用户订单状态
	 */
	
	
	@Override
	public String findOrdStatus(String ordId) {
		//1-1 找到order
		String hql="select ordStatus from TOrder ord where ord.pkOrdId =?";
		String status= Byte.toString((byte) orderDao.findObject(hql, new Object[]{ordId}));
 
	   
		return status;
	}
	/**
	 * 9.0 删除用户订单
	 */
	@Override
	public boolean deleteOrderByCus(String ordId, String cusId) {
		String hql="update TOrder ord set ord.TCustomer.pkCusId =? where ord.pkOrdId=? ";
		int i=orderDao.updateByObject(hql,new Object[]{cusId,ordId});
		return i==0?false:true;
	}

	//setter
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public ItemOrderService getItemOrderService() {
		return itemOrderService;
	}

	public void setItemOrderService(ItemOrderService itemOrderService) {
		this.itemOrderService = itemOrderService;
	}

 
	
	
}
