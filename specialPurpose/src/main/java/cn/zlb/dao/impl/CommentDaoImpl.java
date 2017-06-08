package cn.zlb.dao.impl;
import org.springframework.stereotype.Repository;

import cn.zlb.dao.CommentDao;
import cn.zlb.entity.TComment;
@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<TComment> implements CommentDao {

	@Override
	public int findStoreLevel(String hql,String stoId) {
		Object object=getSession().createQuery(hql).setParameter(0, stoId).uniqueResult();
		 
		return Integer.parseInt(object.toString());
	}
 
}
