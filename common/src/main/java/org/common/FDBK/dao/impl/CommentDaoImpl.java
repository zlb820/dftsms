package org.common.FDBK.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.common.FDBK.dao.CommentDao;
import org.common.FDBK.domain.Comment;
import org.common.FDBK.domain.Store;
import org.common.RBAC.dao.exception.AccountNotExistException;
import org.common.RBAC.dao.exception.StoreNotExistException;
import org.common.RBAC.dao.impl.BaseDaoImpl;
import org.common.RBAC.domain.Account;

public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao{

	@Override
	public boolean addcomment(Serializable storeID, Serializable customerID, String comment, Float level) {
		Account a = getSessionFactory().getCurrentSession().get(Account.class, customerID);
		if (null == a) {
			throw new AccountNotExistException();
		}
		Store s = getSessionFactory().getCurrentSession().get(Store.class, storeID);
		if (null == s) {
			throw new StoreNotExistException();
		}
		boolean flag=false;
		Comment tmp=new Comment();
		tmp.setContent(comment);
		tmp.setLevel(level);
		tmp.setAccount(getSessionFactory().getCurrentSession().get(Account.class, customerID));
		tmp.setStore(getSessionFactory().getCurrentSession().get(Store.class, storeID));
		tmp.setTime(new Date());
		Serializable result=save(tmp);
		if (null != result) {
			flag=true;
		}
		return flag;
	}

	@Override
	public List<Comment> getStoreAllComment(Serializable storeID) {
		String hql="from Comment c where c.store.id = ?0";
		List<Comment> tmp=find(hql,storeID);
		return tmp;
	}

	@Override
	public List<Comment> getCustomerAllComment(Serializable customerID) {
		String hql="from Comment c where c.account.id = ?0";
		List<Comment> tmp=find(hql,customerID);
		return tmp;
	}
}
