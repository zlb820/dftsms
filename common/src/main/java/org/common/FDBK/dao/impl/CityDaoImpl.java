package org.common.FDBK.dao.impl;

import java.util.List;

import org.common.FDBK.dao.CityDao;
import org.common.FDBK.domain.City_addr;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class CityDaoImpl implements CityDao<City_addr> {
	// DAO组件进行持久化操作底层依赖的SessionFactory组件
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City_addr> getCitysByProvinceCode(String Pro_code) {
		String hql = "from City_addr c where c.aboutP_code=?0";
		// 创建查询
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString("0", Pro_code);
		return (List<City_addr>) query.list();
	}

}
