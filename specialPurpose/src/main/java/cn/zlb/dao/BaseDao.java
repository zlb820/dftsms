package cn.zlb.dao;

import java.util.List;
import java.util.Map;
import cn.zlb.tools.Pager;

/**
 * 通用方法基类接口
 * @author Bingo
 *
 * @param <T>
 */
public interface BaseDao<T> {
	
	/**
	 * 1.0 添加对象
	 */
		//add
	public T add(T t);
	
		//saveorupdate
	public T saveOrUpdate(T t);
	
	/**
	 * 2.0 更新对象
	 */
	public T update(T t);
	
	/**
	 * 3.0删除对象
	 */
	public T delete(T t);
	
	/**
	 * 4.0 根据 Object[] 参数 查询对象
	 */
	public Object findObject(String hql,Object[] obj);
	/**
	 * 4.1 根据object[]参数 查询
	 */
	public List<T> listByObject(String hql,Object[] obj);
	/**
	 * 5.0根据Dto查询
	 * @param hql
	 * @param args
	 * @param dto
	 * @return
	 */
	 
	List<T> findObjectByDto(String hql,Object dto);
	/**
	 * 5.1根据dto查询
	 * @param hql
	 * @param args
	 * @param dto
	 * @return
	 */
	List<T> listByDto(String hql, Object[] args, Object dto);
	/**
	 * 5.2根据map查询
	 * @param hql
	 * @param alias
	 *  
	 * @return
	 */
	public List<T> listByAlias(String hql,Map<String, Object> alias);
	 /**
	  * 6.0根据Dto模糊查询
	  * @param hql
	  * @param dto
	  * @return
	  */
	List<T> serachObject(String hql,Object dto);
	
	
	/**
	 * 7.0分页查询 对象
	 */
	public Pager<T> queryWithPager(String hql, Object[] args, Map<String, Object> alias,Pager<T> pages);
	
	/**
	 * 7.1 分页查询 各种构造
	 */
	
	public Pager<T> query(String hql,Pager<T> pages);
	public Pager<T> query(String hql, Object arg,Pager<T> pages);
	public Pager<T> query(String hql, Object[] args,Pager<T> pages);
	Pager<T> queryByDto(String hql, Object dto, Pager<T> pages);
	public Pager<T> queryByAlias(String hql, Map<String, Object> alias,Pager<T> pages);
	Pager<T> queryWithPager(String hql, Object[] args, Map<String, Object> alias, Object dto, Pager<T> pages);
	
	/**
	 * 8.0 更新
	 */
	int updateByObject(String hql);
	int updateByObject(String hql,Object obj);
	int updateByObject(String hql,Object[] objs);
}










