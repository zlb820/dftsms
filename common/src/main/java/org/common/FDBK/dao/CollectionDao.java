package org.common.FDBK.dao;

import java.io.Serializable;
import java.util.List;

import org.common.FDBK.domain.Collection;
import org.common.FDBK.domain.simple.CollectionSimpleMSG;
import org.common.RBAC.dao.BaseDao;
import org.common.RBAC.dao.exception.AccountNotExistException;
import org.common.RBAC.dao.exception.StoreNotExistException;

public interface CollectionDao extends BaseDao<Collection> {

	Boolean saveCollection(Serializable accountID, Serializable storeID)
			throws AccountNotExistException, StoreNotExistException;

	Boolean deleteCollection(Serializable collectionID);

	List<CollectionSimpleMSG> getAllCollection(Serializable accountID);

	boolean checkCoolectionIsExist(Serializable accountID, Serializable storeID);
}
