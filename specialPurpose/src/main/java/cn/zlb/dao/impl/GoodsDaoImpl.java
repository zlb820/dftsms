package cn.zlb.dao.impl;
 

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.zlb.dao.GoodsDao;
import cn.zlb.dto.GoodsDto;
import cn.zlb.dto.StoreDto;
import cn.zlb.entity.TGoods;
import cn.zlb.entity.TStore;
import cn.zlb.tools.Pager;
 
@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl<TGoods> implements GoodsDao {
	/**
	 * 1.0search goods
	 */
	@Override
	public Pager<TGoods> searchGoods(String hql,GoodsDto gooDto, Pager<TGoods> pages) {
		Query query=getSession().createQuery(hql);
		if (gooDto.getGooName()!=null) {
			query.setParameter("gooName",  "%"+gooDto.getGooName()+"%");
		}
		if (gooDto.getGooCurrprice()!=null && gooDto.getMaxPrice()!=null) {
			
			query.setInteger("maxPrice",Integer.parseInt( gooDto.getMaxPrice()));
			query.setInteger("minPrice", Integer.parseInt(gooDto.getGooCurrprice()));
		}
		//给查询  设置分页参数
		setPage(query,pages);
		//查询结果
		List<TGoods> list=query.list();
		//添加结果集到 pager
		pages.setResultList(list);
		
		//查询总记录
		 getAllCountForPrice(hql, gooDto, pages);
		return pages;
	}

	private Long getAllCountForPrice(String hql ,GoodsDto gooDto,Pager<TGoods> pages){
		String countHql=getCountHql(hql, true);
		Query query=getSession().createQuery(countHql);
		// 绑定查询参数
		if (gooDto.getGooName()!=null) {
			query.setParameter("gooName",  "%"+gooDto.getGooName()+"%");
		}
		if (gooDto.getGooCurrprice()!=null && gooDto.getMaxPrice()!=null) {
			query.setInteger("maxPrice",Integer.parseInt( gooDto.getMaxPrice()));
			query.setInteger("minPrice", Integer.parseInt(gooDto.getGooCurrprice()));
		}
		Long count=(Long) query.uniqueResult();
		
		//设置总记录数 和 总页数到 Pager
		pages.setAllCount(count);
		pages.calcuatePage();
		return count;
	}

}
