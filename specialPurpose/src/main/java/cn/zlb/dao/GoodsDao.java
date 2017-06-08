package cn.zlb.dao;

import cn.zlb.dto.GoodsDto;
import cn.zlb.entity.TGoods;
import cn.zlb.tools.Pager;

public interface GoodsDao extends BaseDao<TGoods>{
	/**
	 * 1.0 serachGoods 包含 名称查询和 价格区间查询
	 * 
	 */
	
	public Pager<TGoods> searchGoods(String hql,GoodsDto gooDto, Pager<TGoods> pages);
}
