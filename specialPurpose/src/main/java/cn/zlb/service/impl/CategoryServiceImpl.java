package cn.zlb.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zlb.dao.CategoryDao;
import cn.zlb.dto.CategoryDto;
import cn.zlb.entity.TStoreCategory;
import cn.zlb.exception.CategoryNotFoundException;
import cn.zlb.service.CategoryService;
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@Resource
	private CategoryDao categoryDao;
	static Logger logger=Logger.getLogger(CategoryServiceImpl.class);
	/**
	 * 1.0 增加category信息
	 * @param category
	 * @return
	 */
	@Override
	public Serializable add(TStoreCategory category) {
		return categoryDao.add(category);
	 
	}
	/**
	 * 2.0更新category信息
	 * @param category
	 */
	@Override
	public void update(TStoreCategory category) {
		categoryDao.update(category);
	}
	
	/**
	 * 2.1更新
	 */
	@Override
	public void saveOrUpdate(TStoreCategory category) {
		categoryDao.saveOrUpdate(category);
	}
	/**
	 * 3.0 删除目录信息
	 * @param category
	 */
	@Override
	public void delete(TStoreCategory category) {
		categoryDao.delete(category);
	}
	/**
	 * 4.0查询 目录
	 */
	@Override
	public List<TStoreCategory> findCategory(CategoryDto catDto) {
		String hql="from TStoreCategory cat where 1=1";
		if (catDto.getCatName()!=null)  
			hql+="and cat.catName = :catName";
		if(catDto.getStoId()!=null)
			hql+="and cat.TStore.fkStoId =:stoId";
			
		 List<TStoreCategory> catList=categoryDao.findObjectByDto(hql, catDto);
		 if (catList.isEmpty()) {
			 throw new CategoryNotFoundException("目录未找到！！！");
			
		}
		return catList;
	}
	/**
	 * 5.0根据pid查询
	 */
	@Override
	public List<TStoreCategory> findCategoryByPid(String catPid) {
		String hql="from TStoreCategory  cat where  cat.catPid = ?";
		List<TStoreCategory> catList=categoryDao.listByObject(hql, new Object[]{catPid});
		if (catList.isEmpty()) {
			throw new CategoryNotFoundException("目录未找到！！！");
		}
		return catList;
	}
	/**
	 * 查询店铺 二级目录
	 */
	@Override
	public List<TStoreCategory> findCategoryWithPid(String stoId) {
		String hql="from TStoreCategory  cat where  cat.TStore.fkStoId = ?";
		List<TStoreCategory> catList=categoryDao.listByObject(hql, new Object[]{stoId});
		if (catList.isEmpty()) {
			throw new CategoryNotFoundException("目录未找到！！！");
		}
		return catList;
	}
	
	/**
	 * 6.0查询目录
	 */
	@Override
	public List<TStoreCategory> findAllCategory(String stoId) {
		//1.0查询所有的一级目录
		List<TStoreCategory> listPid=findAllPid(stoId);
		//2.0 查询所有一级目录的 子目录
		for (TStoreCategory parent : listPid) {
			List<TStoreCategory> listChild=findCategoryByPid(parent.getPkCatId());
			parent.setListCategory(listChild);
		}
			return listPid;
	}
	
	/**
	 * 6.1 查找所有的一级目录
	 * @return
	 */
	public List<TStoreCategory> findAllPid(String stoId){
		String hql="from  TStoreCategory  cat where  cat.catDesc = 1 and cat.TStore.fkStoId=?";
		List<TStoreCategory> listPid=categoryDao.listByObject( hql,new Object[]{stoId});
		return listPid;
	}
	
	

	//setter
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	
	
}
