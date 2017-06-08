package cn.zlb.service;

import java.io.Serializable;
import java.util.List;

import cn.zlb.dto.CategoryDto;
import cn.zlb.entity.TCheap;
import cn.zlb.entity.TStoreCategory;

/**
 * category service interface
 * @author Bingo
 *
 */
	
public interface CategoryService {
	/**
	 * 1.0 增加category信息
	 * @param cheap
	 * @return
	 */
	Serializable add(TStoreCategory category);
	/**
	 * 2.0更新category信息
	 * @param cheap
	 */
	void update(TStoreCategory category);
	/**
	 * 2.1更新 
	 */
	void saveOrUpdate(TStoreCategory category);
	/**
	 * 3.0 删除目录信息
	 * @param cheap
	 */
	void delete(TStoreCategory category);
	/**
	 * 4.0 查询目录信息
	 * @param catDto
	 * @return
	 */
	List<TStoreCategory> findCategory(CategoryDto  catDto);
	/**
	 * 5.0根据pid查询
	 * @param catDto
	 * @return
	 */
	List<TStoreCategory> findCategoryByPid(String catPid);
	public List<TStoreCategory> findCategoryWithPid(String catPid);
	/**
	 * 6.0查找所有的目录 
	 */
	List<TStoreCategory> findAllCategory(String stoId);
	/**
	 * 6.1 查找所有一级目录
	 */
	List<TStoreCategory> findAllPid(String stoId);
}







