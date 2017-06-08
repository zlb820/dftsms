package cn.zlb.dao;

import java.util.List;

import cn.zlb.dto.StoreDto;
import cn.zlb.entity.TStore;
import cn.zlb.tools.Pager;

public interface StoreDao extends BaseDao<TStore>  {
	/**
	 * 1.0 评分区间查找店铺
	 * @param stoDto
	 * @param pages
	 * @return
	 */
	Pager<TStore> serachStoreByScore(String hql,StoreDto stoDto, Pager<TStore> pages);
	/**
	 * 2.0最近店铺
	 */
	public List<TStore> findStoreRecent(String hql);
}
