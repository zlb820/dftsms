package org.common.FDBK.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.common.FDBK.dao.CollectionDao;
import org.common.FDBK.domain.Collection;
import org.common.FDBK.domain.Store;
import org.common.FDBK.domain.simple.CollectionSimpleMSG;
import org.common.RBAC.dao.exception.AccountNotExistException;
import org.common.RBAC.dao.exception.StoreNotExistException;
import org.common.RBAC.dao.impl.BaseDaoImpl;
import org.common.RBAC.domain.Account;

public class CollectionDaoImpl extends BaseDaoImpl<Collection> implements CollectionDao {

	@Override
	public boolean checkCoolectionIsExist(Serializable accountID, Serializable storeID) {
		String hql = "from Collection c where c.account.id = ?0 and c.store.id = ?1";
		List<Collection> tmp = find(hql, accountID, storeID);
		if (tmp.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean saveCollection(Serializable accountID, Serializable storeID) {
		Account a = getSessionFactory().getCurrentSession().get(Account.class, accountID);
		if (null == a) {
			throw new AccountNotExistException();
		}
		Store s = getSessionFactory().getCurrentSession().get(Store.class, storeID);
		if (null == s) {
			throw new StoreNotExistException();
		}
		boolean flag = false;
		Collection tmp = new Collection();
		tmp.setAccount(a);
		tmp.setStore(s);
		tmp.setTime(new Date());
		Serializable result = save(tmp);
		if (null != result) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Boolean deleteCollection(Serializable collectionID) {
		boolean flag = false;
		Collection tmp = getSessionFactory().getCurrentSession().get(Collection.class, collectionID);
		delete(tmp);
		if (null == getSessionFactory().getCurrentSession().get(Collection.class, collectionID)) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<CollectionSimpleMSG> getAllCollection(Serializable accountID) {
		String hql = "from Collection c where c.account.id = ?0";
		List<Collection> tmp = find(hql, accountID);
		List<CollectionSimpleMSG> result = new ArrayList<>();
		Iterator<Collection> it = tmp.iterator();
		while (it.hasNext()) {
			CollectionSimpleMSG temp = new CollectionSimpleMSG();
			Collection a = it.next();
			temp.setAddConcrete(a.getStore().getAddress().getConcreteAddress());
			temp.setScore(Integer.valueOf(a.getStore().getScore()));
			temp.setStoID(a.getStore().getId());
			temp.setStoName(a.getStore().getName());
			temp.setURL(a.getStore().getPicture().getPic_url());
			temp.setCollectionID(a.getId());
			temp.setTime(a.getTime());
			result.add(temp);
		}
		return result;
	}

}
