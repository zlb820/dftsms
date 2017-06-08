package cn.zlb.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.zlb.dto.StoreDto;
import cn.zlb.entity.TStore;
import cn.zlb.exception.StoreNotFoundException;
import cn.zlb.tools.Pager;
@Repository("storeDaoDemo")
public class StoreDaoDemo {
	@Resource
	private SessionFactory sessionFactory;
	public Session getSession(){
		return sessionFactory.getCurrentSession();
		
	}
	
	/**
	 * 1.0 string 转 int 查询
	 * @param stoDto
	 *  
	 * @return
	 */
	public List<TStore> serachStoreByScore(StoreDto stoDto ) {
		String hql="from TStore  as sto where 1=1";
		if(stoDto.getStoScore()!=null)
			//hql+="and cast(sto.stoScore as 	int) >= ? and cast(sto.stoScore as 	int) <= ? order by sto.stoScore asc";
			hql+="and  sto.stoScore between ?  and ? order by sto.stoScore asc";
		Query query=getSession().createQuery(hql);
		query.setInteger(0, 4);
		query.setInteger(1, 10);
		List list=query.list();
		if (list.isEmpty()) {
			throw new StoreNotFoundException("店铺不存在,请检查检索条件!!!");
		 
		}
		return list ;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
