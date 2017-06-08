package cn.zlb.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.zlb.dao.OrderDao;
import cn.zlb.entity.TOrder;
@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<TOrder> implements OrderDao {
	/**
	 * 1.0查找用户最近订单
	 * @param hql
	 * @param pkCusId
	 * @return
	 */
	@Override
	public List<TOrder> findOrderRecent(String hql,String pkCusId) {
		 Query query=getSession().createQuery(hql);
		 query.setParameter(0, pkCusId);
		 query.setMaxResults(4);
		 
		return query.list();
	}

	/**
	 * 2.0按照订单状态查询
	 */
	@Override
	public List<TOrder> findOrderByStatus(String hql,String status) {
		Query query=getSession().createQuery(hql);
		query.setByte(0,(byte)Integer.parseInt(status) );
		
		return query.list();
	}

	
}
