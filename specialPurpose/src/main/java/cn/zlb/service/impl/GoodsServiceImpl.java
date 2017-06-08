package cn.zlb.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zlb.dao.GoodsDao;
import cn.zlb.dto.GoodsDto;
import cn.zlb.entity.TGoods;
import cn.zlb.service.GoodsService;
import cn.zlb.tools.CommonUtils;
import cn.zlb.tools.Pager;
@Service("goodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsDao goodsDao;
	Logger logger =Logger.getLogger(GoodsServiceImpl.class);
	@Override
	public Serializable add(TGoods goods) {
		goods.setPkGooId(CommonUtils.uuid());
		return goodsDao.add(goods);
	}

	@Override
	public void update(TGoods goods) {
		goodsDao.saveOrUpdate(goods);
	}

	@Override
	public void delete(TGoods goods) {
		goodsDao.delete(goods);
	}
	 /**
	  * 4.1 根据店铺 目录 or 店铺id 查找商品
	  */
	@Override
	public Pager<TGoods> findGoods(GoodsDto gooDto,Pager<TGoods> pages) {
		String hql="from TGoods goo where 1=1";
		Map<String,Object>map=new HashMap<String, Object>(); 
		if (gooDto.getTStore()!=null) {
			hql+="and goo.TStore.fkStoId =:fkStoId";
			map.put("fkStoId", gooDto.getTStore().getFkStoId());
			
		}
		if (gooDto.getPkCatId()!=null) {
			hql+="and goo.TStoreCategory.pkCatId =:pkCatId";
			map.put("pkCatId", gooDto.getPkCatId());
		}
		Pager<TGoods> pager=goodsDao.queryByAlias(hql, map, pages);
		return  pager;
	}
	
	/**
	 * 4.2 非分页查询
	 */
	@Override
	public List<TGoods> findGoodsByStoreId(GoodsDto gooDto) {
		String hql="from TGoods goo where 1=1";
		Map<String,Object>map=new HashMap<String, Object>(); 
		if (gooDto.getTStore()!=null) {
			hql+="and goo.TStore.fkStoId =:fkStoId";
			map.put("fkStoId", gooDto.getTStore().getFkStoId());
			
		}
		if (gooDto.getPkCatId()!=null) {
			hql+="and goo.TStoreCategory.pkCatId =:pkCatId";
			map.put("pkCatId", gooDto.getPkCatId());
		}
		List<TGoods> gooList=goodsDao.listByAlias(hql, map);
		return  gooList;
		
	}

	/**
	 * 5.0根据 商品名称 or 价格区间模糊查询
	 */
	@Override
	public Pager<TGoods> searchGoods(GoodsDto gooDto, Pager<TGoods> pages) {
		String hql="from TGoods goo where 1=1";
		Map<String,Object>params=new HashMap<String, Object>();
		if (gooDto.getGooName()!=null) {
			hql+="and goo.gooName like :gooName";
			params.put("gooName", "%"+gooDto.getGooName()+"%");
		}
		if (gooDto.getGooCurrprice()!=null&&gooDto.getMaxPrice()!=null) {
			hql+="and goo.gooCurrprice between :minPrice and :maxPrice  order by goo.gooCurrprice asc" ;
			params.put("minPrice",gooDto.getGooCurrprice());
			params.put("maxPrice", gooDto.getMaxPrice());
		}
		Pager<TGoods> pager=goodsDao.searchGoods(hql, gooDto, pages);
		return pager ;
	}
	/**
	 * 6.0 根据商品名称模糊查询
	 */
	@Override
	public Pager<TGoods> searchGoodsByName(String gooName, Pager<TGoods> pages) {
		String hql="from TGoods goo where 1=1";
		Map<String,Object>params=new HashMap<String, Object>();
		if (gooName!=null) {
			hql+="and goo.gooName like :gooName";
			params.put("gooName", "%"+gooName+"%");
		}
		Pager<TGoods> pager=goodsDao.queryByAlias(hql, params, pages);
		return pager;
	}
	/**
	 * 7.0根据商品id查询
	 */
	@Override
	public TGoods findGoodsById(String pkGooId ) {
		String hql="from TGoods goo where 1=1";
		if (pkGooId!=null) 
			hql+="and goo.pkGooId =?";
		TGoods goods=(TGoods) goodsDao.findObject(hql, new Object[]{pkGooId});
		return  goods;
	}
	/**
	 * 8.0添加商品大图
	 */
	public boolean addPictureBig(GoodsDto gooDto){
		String hql="update TGoods goo set goo.TPicturesByFkPictureBig.pkPicId=? where goo.pkGooId=?";
		int i=goodsDao.updateByObject(hql, new Object[]{gooDto.getTPicturesByFkPictureBig().getPkPicId(),gooDto.getPkGooId()});
		return i==1?true:false;
		
	}
	/**
	 * 8.0添加商品小图
	 */
	public boolean addPictureSmall(GoodsDto gooDto){
		String hql="update TGoods goo set goo.TPicturesByFkPictureSmall.pkPicId=? where goo.pkGooId=?";
		int i=goodsDao.updateByObject(hql, new Object[]{gooDto.getTPicturesByFkPictureSmall().getPkPicId(),gooDto.getPkGooId()});
		return i==1?true:false;
		
	}
	
}






