package cn.zlb.dao;
import java.util.List;

import cn.zlb.dto.BusinessDto;
/**
 * Business Dao  商家dao interface 继承自 BaseDao interface
 * @author Bingo
 */
import cn.zlb.entity.TBusiness;
import cn.zlb.tools.Pager;

public interface BusinessDao  extends BaseDao<TBusiness> {
	 /**
	  * 通用的方法由 BaseDao 以及BaseDaoImpl实现
	  * BusinessDao 定义 特定的方法
	  */
	
	
	/**
	 * 1.0商户登录
	 * @param business
	 * @return
	 */
	TBusiness login(TBusiness business);
 
	/**
	 * 2.0 模糊查询
	 */
	Pager<TBusiness> searchBusiness(String hql,BusinessDto dto,Pager<TBusiness> pages);
	/**
	 * 3.0
	 */
}
