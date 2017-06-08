package cn.zlb.dao.impl;
 import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
  * 实现 通用基类接口
  * @author Bingo
  */
import cn.zlb.dao.BaseDao;
import cn.zlb.dto.BusinessDto;
import cn.zlb.entity.TBusiness;
import cn.zlb.tools.Pager;

public class BaseDaoImpl<T> implements BaseDao<T> {
	@Resource
	private SessionFactory	sessionFactory;
	
	/**
	 * 添加对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T add(T t) {
		Serializable id=getSession().save(t);
		return (T) id;
	}
	/**
	 * 保存活着更新
	 */
	@Override
	public T saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
		return t;
	}
	/**
	 * 更新对象
	 */
	@Override
	public T update(T t) {
		getSession().update(t);
		return  t;
	}
	/**
	 * 善处对象
	 */
	@Override
	public T delete(T t) {
		getSession().delete(t);
		return t;
	}
	/**
	 * 根据 参数 查找对象
	 * @param hql      hql语句
	 * @param objects  查询参数
	 * @return		         查询到的对象	
	 */
	public Object findObject(String hql, Object[] objects) {
			
		return this.findObject(hql,objects,null);
	}

	/**
	 * 根据参数查找对象 内部实现方法
	 * @param hql 	
	 * @param objects	object 参数 可根据 setParameter()设置
	 * @param object	
	 * @return
	 */
	private Object findObject(String hql, Object[] objects, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		this.setAliasParameter(query, alias);
		this.setParameter(query,  objects);
		return query.uniqueResult();
	 
	}
	
		/**
		 * ---------------------分页查询  各种实现----------------------
		 */
	/**
	 * 无set 分页
	 * @param 
	 */
	@Override
	public Pager<T> query(String hql, Pager<T> pages) {
		return queryWithPager(hql, null, null, pages);
	}
	/**
	 * object 单一参数分页
	 */
	@Override
	public Pager<T> query(String hql, Object arg, Pager<T> pages) {
		 return query(hql, new Object[]{arg},pages);
	}
	/**
	 * Object[]{} 数组参数 分页
	 */
	@Override
	public Pager<T> query(String hql, Object[] args, Pager<T> pages) {
		return queryWithPager(hql, args, null, pages);
	}
	/**
	 * alias 分页
	 */
	@Override
	public Pager<T> queryByAlias(String hql, Map<String, Object> alias, Pager<T> pages) {
		return queryWithPager(hql, null,alias,pages);
	}
	
	/**
	 * dto 分页
	 */
	@Override
	public Pager<T> queryByDto(String hql,Object dto,Pager<T> pages) {
		return queryWithPager(hql, null,null,dto,pages);
	}
	/**
	 * Object[]{} and alias 综合分页
	 * @param hql  hql语句
	 * @param args 用于占位符查询
	 * @param alisa 用于parameters 查询	
	 * @param dto  用于properties查询
	 * @param pager 用于分页查询
	 * 
	 */
	
	@Override
	public Pager<T> queryWithPager(String hql, Object[] args, Map<String, Object> alias, Pager<T> pages) {
		Query query=getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		//给查询  设置分页参数
		setPage(query,pages);
		//查询结果
		List<T> list=query.list();
		//添加结果集到 pager
		pages.setResultList(list);
		
		//查询总记录
		getAllCount(hql, args, alias,null, pages);
		
		return pages;
	}
	@Override
	public Pager<T> queryWithPager(String hql, Object[] args, Map<String, Object> alias,Object dto, Pager<T> pages) {
		Query query=getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		setDtoParameter(query, dto);
		//给查询  设置分页参数
		setPage(query,pages);
		//查询结果
		List<T> list=query.list();
		//添加结果集到 pager
		pages.setResultList(list);
		
		//查询总记录
		getAllCount(hql, args, alias,dto, pages);
		
		return pages;
	}
	
	/**
	 * 分页查询 -01
	 * 查询总记录数
	 * @param hql
	 * @return
	 */
	 
	protected Long getAllCount(String hql ,Object[] args, Map<String, Object> alias,Object dto ,Pager<T> pages){
		String countHql=getCountHql(hql, true);
		Query query=getSession().createQuery(countHql);
		// 绑定查询参数
		setAliasParameter(query, alias);
		setParameter(query, args);
		setDtoParameter(query, dto);
		Long count=(Long) query.uniqueResult();
		
		//设置总记录数 和 总页数到 Pager
		pages.setAllCount(count);
		pages.calcuatePage();
		return count;
	}
	/**
	 * 分页查询 -02
	 * 格式化hql 获取查询总记录数的hql
	 * @param hql
	 * @param isHql
	 * @return
	 */
	protected String getCountHql(String hql,boolean isHql) {
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) "+e;
		if(isHql)
			c = c.replaceAll("fetch", "");
		return c;
	}
	
	/**
	 * 分页查询 -03
	 * 未查询参数设置 分页参数
	 * @param query
	 * @param pages
	 */
	protected void setPage(Query query, Pager<T> pages) {
		//获取 每页记录数 
		int pageSize=pages.getPageSize().intValue();
		//获取 查询开始记录 位置
		int queryStart=(pages.getCurrentPage().intValue()-1)*pageSize;
		System.out.println("pagesize= "+pageSize +"and queryStart = " + queryStart);
		//设置 query 查询起始位置 和 查询记录数
		query.setFirstResult(queryStart);
		query.setMaxResults(pageSize);
	}
	
	
	/**
	 * 更新 
	 */
	@Override
	public int updateByObject(String hql) {
		return updateByObject(hql, null);
	}
	@Override
	public int updateByObject(String hql, Object obj) {
		return updateByObject(hql, new Object[]{obj});
	}
	@Override
	public int updateByObject(String hql, Object[] objs) {
		Query query=getSession().createQuery(hql);
		setParameter(query, objs);
		int i=query.executeUpdate();
		return i;
	}
	
	
	//---------------------------------------------------------------------
	//----------------------------dto --------------
	/**
	 * 根据 Dto查询对象
	 * @param hql
	 * @param busDto
	 * @return
	 */
   
	@Override
	public List<T> findObjectByDto(String hql, Object dto) {
		return listByDto(hql, null, dto);
	}
 

	public List<T> listByDto(String hql, Object[] args, Object dto) {
		Query query = getSession().createQuery(hql);
		setDtoParameter(query, dto);
		setParameter(query, args);
		 
		return query.list();
	}
	public List<T> listByAlias(String hql,Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		 
		return query.list();
	}
	
	/**
	 * 根据dto模糊查询
	 */
	
	@Override
	public List<T> serachObject(String hql, Object dto) {
		return listByDto(hql, null, dto);
	}
	
	/**
	 * 根据 object[]参数 查询
	 */
	@Override
	public List<T> listByObject(String hql, Object[] obj) {
		
		return listByDto(hql, obj, null);
	}
	
	
	
	//-------------------------------辅助方法
	/**
	 * 给 query 设置 properties属性
	 * @param query
	 * @param dto
	 */
	private void setDtoParameter(Query query, Object dto) {
		if(dto!=null){
			query.setProperties(dto);
		}
	}

	/**
	 * 给sql  设置 collections查询参数   setParameterList
	 * @param query
	 * @param alias
	 */
	private void setAliasParameter(Query query, Map<String, Object> alias) {
		if(alias!=null) {
			Set<String> keys = alias.keySet();
			for(String key:keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {
					query.setParameterList(key, (Collection)val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}
	/**
	 * 给 sql 设置 参数   setParameter() 该方法 下标从0开始
	 * @param query
	 * @param args
	 */
	private void setParameter(Query query,Object[] args) {
		if(args!=null&&args.length>0) {
			int index = 0;
			for(Object arg:args) {
				query.setParameter(index++, arg);
			}
		}
	}
	
	

	/**
	 * 获取session
	 * @return
	 */
	public Session getSession(){
		
		return sessionFactory.getCurrentSession();
	}

	//---------------------getter() and setter()
 
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	

}
