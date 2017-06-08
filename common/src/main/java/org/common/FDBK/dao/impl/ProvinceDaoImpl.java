package org.common.FDBK.dao.impl;

import java.util.List;

import org.common.FDBK.dao.ProvinceDao;
import org.common.FDBK.domain.Province_addr;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class ProvinceDaoImpl implements ProvinceDao<Province_addr> {
	// DAO组件进行持久化操作底层依赖的SessionFactory组件
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Province_addr> getAllProvice() {
		String hql="from Province_addr p";
		// 创建查询
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		return (List<Province_addr>) query.list();
	}
}
