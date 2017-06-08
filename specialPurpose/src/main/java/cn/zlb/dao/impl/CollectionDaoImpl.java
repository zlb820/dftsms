package cn.zlb.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.zlb.dao.CollectionDao;
import cn.zlb.entity.TCollection;
@Repository("collectionDao")
public class CollectionDaoImpl extends BaseDaoImpl<TCollection> implements CollectionDao  {

	@Override
	public boolean findCollection(String cusId, String stoId,String hql) {
		 Query query=getSession().createSQLQuery(hql);
		 query.setParameter(0, cusId);
		 query.setParameter(1, stoId);
		 
		Object obj = query.uniqueResult();
		 if ((Integer.parseInt(obj.toString()))>0) {
			 return true;
		}
		return false;
	}

}
