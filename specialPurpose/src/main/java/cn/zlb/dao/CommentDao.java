package cn.zlb.dao;

import cn.zlb.entity.TComment;

public interface CommentDao extends BaseDao<TComment> {
	public int findStoreLevel(String hql,String stoId);
}
