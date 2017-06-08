package cn.zlb.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToBytesTransformer;

import cn.zlb.dto.OrderDto;
import cn.zlb.entity.TItemorder;
import cn.zlb.entity.TOrder;
import cn.zlb.tools.Pager;

public interface OrderService {
	/**
	 * 1.0 添加 订单
	 * @param order
	 * @return
	 */
	Serializable add(TOrder order);
	/**
	 * 2.0 更新订单信息
	 * @param order
	 */
	void update(TOrder order);
	/**
	 * 2.1saveOrUpdate
	 */
	void saveOrUpdate(TOrder order);
	/**
	 * 3.0删除订单
	 * @param order
	 */
	void delete(TOrder order);
	/**
	 * 4.0 查看订单 详情
	 */
	TOrder findOrderById(String ordId);
	/**
	 * 5.0 修改订单总计
	 */
	int modifyOrderTotal(OrderDto ordDto);
	/**
	 * 6.0修改订单状态
	 */
	int modifyOrderStatus(OrderDto ordDto);
	/**
	 * 7.0 查询用户订单
	 */
	Pager<TOrder> findOrderByCus(String pkCusId,Pager<TOrder> pages);
	/**
	 * 7.1查询用户订单 非分页
	 */
	List<TOrder> findOrderByCus(String cusId);
	/**
	 * 7.1查询用户最近订单
	 */
	List<TOrder> findOrderByCusRecent(String pkCusId);
	public List<TOrder> findOrderByStatus(String cusId);
	/**
	 * 8.0查看订单状态
	 * @param ordId
	 * @return
	 */
	String findOrdStatus(String ordId);
	/**
	 * 9.0删除用户订单
	 * 把订单中 用户字段 更改为0
	 */
	boolean deleteOrderByCus(String ordId,String cusId);
}












