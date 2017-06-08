package cn.zlb.service;

import java.io.Serializable;
import java.util.List;

import cn.zlb.dao.StoreDao;
import cn.zlb.dto.StoreDto;
import cn.zlb.entity.TStore;
import cn.zlb.tools.Pager;

public interface StoreService  {
	/**
	 * 1.0 添加店铺
	 * @param store
	 * @return
	 */
	Serializable add(TStore store);
	/**
	 * 2.0 更新店铺信息
	 * @param store
	 */
	void update(TStore store);
	/**
	 * 3.0删除店铺
	 * @param store
	 */
	void delete(TStore store);
	 /**
	  * 4.0根据dto查找店铺
	  * @param stoDto
	  * @return
	  */
	TStore findStore(StoreDto stoDto);
	
	 /**
	  * 5.0dto模糊查询
	  * @param stoDao
	  * @return
	  */
	//List<TStore> serachStore(StoreDto stoDto);
	/**
	 * 6.0按照店铺名查询
	 * @param stoDto
	 * @return
	 */
	TStore findStoreByName(StoreDto stoDto);
	/**
	 * 6.1 按照店铺名模糊查询
	 * @param stoDto
	 * @return
	 */
	Pager<TStore> serachStoreByName(StoreDto stoDto,Pager<TStore> pages);
	/**
	 * 7.0评分区间查询
	 * @param stoDto
	 * @return
	 */
	Pager<TStore> serachStoreByScore(StoreDto stoDto,Pager<TStore> pages);
	/**
	 * 8.0上传图片
	 * @param stoDto
	 * @return
	 */
	public boolean addPicture(StoreDto stoDto);
	/**
	 * 9.0 查找最新店铺
	 */
	public List<TStore> findStoreRecent();
}
