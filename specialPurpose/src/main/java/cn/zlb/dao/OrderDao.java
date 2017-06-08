package cn.zlb.dao;

import java.util.List;

import cn.zlb.entity.TOrder;

public interface OrderDao extends BaseDao<TOrder> {
	/**
	 * 1.0查找用户最近订单
	 * @param hql
	 * @param pkCusId
	 * @return
	 */
	List<TOrder> findOrderRecent(String hql,String pkCusId);
	/**
	 * 2.0按照订单状态查询
	 */
	List<TOrder> findOrderByStatus(String hql,String status);
 }
