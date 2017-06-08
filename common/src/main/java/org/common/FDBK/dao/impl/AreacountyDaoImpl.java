package org.common.FDBK.dao.impl;

import java.util.List;

import org.common.FDBK.dao.AreacountyDao;
import org.common.FDBK.domain.Areacounty_addr;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class AreacountyDaoImpl implements AreacountyDao<Areacounty_addr> {
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
	public List<Areacounty_addr> getAllAreacountyByCityCode(String cityCode) {
		String hql = "from Areacounty_addr a where a.aboutC_code=?0";
		// 创建查询
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString("0", cityCode);
		return (List<Areacounty_addr>) query.list();
	}
}
