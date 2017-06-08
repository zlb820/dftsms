package cn.zlb.service;

import java.io.Serializable;
import java.util.List;

import cn.zlb.dto.TItemorderDto;
import cn.zlb.entity.TItemorder;
 

public interface ItemOrderService {
	/**
	 * 1.0 添加 订单项
	 * @param itemOrder
	 * @return
	 */
	Serializable add(TItemorder itemOrder);
	/**
	 * 2.0 更新订单项信息
	 * @param itemOrder
	 */
	void update(TItemorder itemOrder);
	/**
	 * 2.1saveOrUpdate
	 */
	void saveOrUpdate(TItemorder itemOrder);
	/**
	 * 3.0删除订单项
	 * @param itemOrder
	 */
	void delete(TItemorder itemOrder);
	/**
	 * 4.0 查看订单项
	 */
	TItemorder findItem(String iteId);
	/**
	 * 5.0修改订单项数量
	 */
	int modifyItem(TItemorderDto iteDto);
	/**
	 * 5.1 查找订单 by orderId （查找订单的所有的订单项）
	 */
	public List<TItemorder> findItemByOrderId(String orderId);
	/**
	 * 6.0修改订单项小计
	 */
	int modifyItemSubtotal(TItemorderDto iteDto);
}











