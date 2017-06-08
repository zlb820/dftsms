package cn.zlb.service;

import java.io.Serializable;
import java.util.List;

import cn.zlb.dto.GoodsDto;
import cn.zlb.entity.TGoods;
import cn.zlb.tools.Pager;
 

public interface GoodsService {
	/**
	 * 1.0 添加店铺
	 * @param goods
	 * @return
	 */
	Serializable add(TGoods goods);
	/**
	 * 2.0 更新店铺信息
	 * @param goods
	 */
	void update(TGoods goods);
	/**
	 * 3.0删除商品
	 * @param goods
	 */
	void delete(TGoods goods);
	/**
	 * 4.0查找店铺商品  仅限  店铺ID查找 or 目录id查找
	 * @param gooDto
	 * @return
	 */
	Pager<TGoods> findGoods(GoodsDto gooDto,Pager<TGoods> pages);
	/**
	 * 4.1 查找店铺商品  仅限  店铺ID查找 or 目录id查找
	 * @param gooDto
	 * @return
	 */
	List<TGoods> findGoodsByStoreId(GoodsDto dto);
	/**
	 * 5.0模糊查询商品 分页 仅限 根据  商品名称 或者 商品价格区间查找
	 * @param goodsDto  
	 * @param pages
	 * @return
	 */
	Pager<TGoods> searchGoods(GoodsDto  goodsDto,Pager<TGoods> pages);
	/**
	 * 6.0根据商品名称模糊查询
	 * @param gooName
	 * @param pages
	 * @return
	 */
	Pager<TGoods> searchGoodsByName(String gooName,Pager<TGoods> pages);
	/**
	 * 6.1根据id查询goods
	 * @param gooDto
	 * @return
	 */
	TGoods findGoodsById(String pkGooId);
	
	/**
	 * 8.0添加商品大图
	 */
	public boolean addPictureBig(GoodsDto gooDto);
	/**
	 * 8.0添加商品小图
	 */
	public boolean addPictureSmall(GoodsDto gooDto);
	
	
}




















