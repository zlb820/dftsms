package cn.zlb.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.zlb.dao.StoreDao;
import cn.zlb.dto.StoreDto;
import cn.zlb.entity.TStore;
import cn.zlb.exception.StoreNotFoundException;
import cn.zlb.service.StoreService;
import cn.zlb.tools.Pager;

@Service("storeService")
@Transactional
public class StoreServiceImpl implements StoreService {
	@Resource
	private StoreDao storeDao;
	static Logger logger  =Logger.getLogger(StoreServiceImpl.class);
	@Override
	public Serializable add(TStore store) {
		return storeDao.add(store);
	}

	@Override
	public void update(TStore store) {
		 storeDao.saveOrUpdate(store);
	}

	@Override
	public void delete(TStore store) {
		storeDao.delete(store);
	}
	 /**
	  * 4.0根据dto查找店铺
	  * @param stoDto
	  * @return
	  */
	@Override
	public TStore findStore(StoreDto stoDto) {
		String hql="from TStore as sto where 1=1";
		if (stoDto.getFkStoId()!=null) 
			hql+="and sto.fkStoId = :fkStoId";
		if (stoDto.getStoName()!=null) 
			hql+="and sto.stoName=:stoName";
		if(stoDto.getStoStatus()!=null)
			hql+="and sto.stoStatus=:stoStatus";
		if (stoDto.getStoScore()!=null) 
			hql+="and sto.getScore=:stoScore";
		List<TStore> listSto=storeDao.findObjectByDto(hql, stoDto);
			if (listSto.size()==0) {
				throw new StoreNotFoundException("store不存在 ,请检查检索条件！！");
			}
			return listSto.get(0);
				
	}
	 /**
	  * 5.0dto模糊查询
	  * @param stoDao
	  * @return
	  */
	 
	/*public List<TStore> serachStore(StoreDto stoDto) {
		String hql="form TStore as sto where 1=1";
		if (stoDto.getStoName()!=null) 
			hql+="and sto.stoName like :stoName";
 
		List<TStore> listSto=storeDao.findObjectByDto(hql, stoDto);
			
		return listSto;
	}*/
	/**
	 * 6.0按照店铺名查询
	 * @param stoDto
	 * @return
	 */
	@Override
	public TStore findStoreByName(StoreDto stoDto) {
		String hql="from TStore as sto where 1=1";
		if (stoDto.getStoName()!=null) 
			hql+="and sto.stoName = :stoName";
  
		List<TStore> stoList=storeDao.findObjectByDto(hql, stoDto);
		if (stoList.size()==0) {
			throw new StoreNotFoundException("店铺不存在,请检查检索条件!!!");
		 
		}
		return stoList.get(0);
	}
	/**
	 * 6.1 按照店铺名模糊查询
	 * @param stoDto
	 * @return
	 */
	@Override
	public Pager<TStore> serachStoreByName(StoreDto stoDto,Pager<TStore> pages) {
		String hql="from TStore as sto where 1=1";
		if (stoDto.getStoName()!=null) 
			hql+="and sto.stoName like  '%"+stoDto.getStoName()+"%' ";
			
		Pager<TStore> pager=storeDao.query(hql, pages);
		 
			
		if (pager.getResultList().isEmpty()) {
			throw new StoreNotFoundException("店铺不存在,请检查检索条件!!!");
		 
		}
		return pager  ;
	}
	/**
	 * 7.0评分区间查询
	 * @param stoDto
	 * @return
	 */
	@Override
	public Pager<TStore> serachStoreByScore(StoreDto stoDto,Pager<TStore> pages) {
		String hql="from TStore  as sto where 1=1";
		if(stoDto.getStoScore()!=null)
			hql+="and  sto.stoScore >= ? and  sto.stoScore  <= ? order by sto.stoScore asc";
		logger.info("评分区间 hql := "+hql);
		Pager<TStore> pager= storeDao.serachStoreByScore(hql,stoDto,pages);
		if (pager.getResultList().isEmpty()) {
			throw new StoreNotFoundException("店铺不存在,请检查检索条件!!!");
		 
		}
		return  pager ;
	}
	/**
	 * 8.0添加图片
	 */
	@Override
	public boolean addPicture(StoreDto stoDto) {
		String hql="update TStore as sto set sto.TPictures.pkPicId=? where sto.fkStoId=?";
		int i=storeDao.updateByObject(hql, new Object[]{stoDto.getTPictures().getPkPicId(),stoDto.getFkStoId()});
		return i==1?true:false;
	}

	
	/**
	 * 9.0查找最新店铺
	 */
	@Override
	public List<TStore> findStoreRecent() {
		String hql="from TStore sto where  sto.stoStatus=?   order by sto.fkStoId";
		List<TStore> stoList=storeDao.findStoreRecent(hql);
		if (stoList.isEmpty()) {
			throw new StoreNotFoundException("没有找到用户收藏店铺");
		}
		return stoList;
	}
	
	
}
