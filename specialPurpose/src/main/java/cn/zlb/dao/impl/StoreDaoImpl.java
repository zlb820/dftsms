package cn.zlb.dao.impl;
 
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.zlb.dao.StoreDao;
import cn.zlb.dto.StoreDto;
import cn.zlb.entity.TBusiness;
import cn.zlb.entity.TStore;
import cn.zlb.tools.Pager;
@Repository("storeDao")
public class StoreDaoImpl extends BaseDaoImpl<TStore> implements StoreDao {
	/**
	 * 1.0 评分区间 查找店铺
	 */
	@Override
	public Pager<TStore> serachStoreByScore(String hql,StoreDto stoDto, Pager<TStore> pages) {
		Query query=getSession().createQuery(hql);
		if (stoDto.getStoScore()!=null&&stoDto.getStoMaxScore()!=null) {
			query.setInteger(0, Integer.parseInt(stoDto.getStoScore()));
			query.setInteger(1, Integer.parseInt(stoDto.getStoMaxScore()));
		}
		//给查询  设置分页参数
		setPage(query,pages);
		//查询结果
		List<TStore> list=query.list();
		//添加结果集到 pager
		pages.setResultList(list);
		
		//查询总记录
		 getAllCountForScore(hql, stoDto, pages);
		return pages;
	}
	private Long getAllCountForScore(String hql ,StoreDto stoDto,Pager<TStore> pages){
		String countHql=getCountHql(hql, true);
		Query query=getSession().createQuery(countHql);
		// 绑定查询参数
		if (stoDto.getStoScore()!=null&&stoDto.getStoMaxScore()!=null) {
			query.setInteger(0, Integer.parseInt(stoDto.getStoScore()));
			query.setInteger(1, Integer.parseInt(stoDto.getStoMaxScore()));
		}
		Long count=(Long) query.uniqueResult();
		
		//设置总记录数 和 总页数到 Pager
		pages.setAllCount(count);
		pages.calcuatePage();
		return count;
	}
	 
	/**
	 * 2.0最近店铺
	 */
	
	@Override
	public List<TStore> findStoreRecent(String hql) {
		Query query=getSession().createQuery(hql);
		query.setParameter(0, true);
		query.setMaxResults(4);
		return query.list();
	}
}
