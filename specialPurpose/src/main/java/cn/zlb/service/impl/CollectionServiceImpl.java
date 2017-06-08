package cn.zlb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zlb.dao.CollectionDao;
import cn.zlb.service.CollectionService;
@Service("collectionService")
@Transactional
public class CollectionServiceImpl implements CollectionService {
	@Resource
	private CollectionDao collectionDao;
	@Override
	public boolean findCollection(String cusId, String stoId) {
		//String hql="from TCollection col where col.TCustomer.pkCusId=? and col.TStore.fkStoId=?"; 
		String hql="select count(*) from t_collection col where col.fk_customer=? and col.fk_store=?";
		return collectionDao.findCollection(cusId, stoId, hql);
	}
	public CollectionDao getCollectionDao() {
		return collectionDao;
	}
	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
	}

	
	
	
}
