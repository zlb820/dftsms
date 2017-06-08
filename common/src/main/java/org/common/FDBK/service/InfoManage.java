package org.common.FDBK.service;

import java.io.Serializable;
import java.util.List;

import org.common.FDBK.domain.Comment;
import org.common.FDBK.domain.DATA.ShopSimpleMSG;
import org.common.FDBK.domain.simple.CollectionSimpleMSG;
import org.common.util.ResultSimple;

public interface InfoManage {
	/**
	 * 获取当前县下面的商家的总数
	 */
	public Long getStoreByArea(String addCode);
	
	public List<ShopSimpleMSG> getIndexDataBySort(Double x, Double y, String addCode,String shopNodeId,String sort);
	public List<ShopSimpleMSG> getIndexDataByGoodName(Double x, Double y, String addCode,String shopNodeId,String goodName);

	/**
	 * 根据规则和位置将商家数据进行分页获取
	 * 
	 * @param x
	 * @param y
	 * @param addCode
	 * @param start
	 * @param num
	 * @param rule
	 * @param shopNodeId
	 * @return
	 */
	public List<ShopSimpleMSG> getIndexData(Double x, Double y, String addCode, int start, int num, int rule,
			String shopNodeId);

	/**
	 * 保存用户评论
	 * 
	 * @param storeID
	 * @param customerID
	 * @param comment
	 * @param level
	 * @return
	 */
	public ResultSimple writeComment(Serializable storeID, Serializable customerID, String comment, Float level);

	/**
	 * 获取该商家的所有评论
	 * 
	 * @param storeID
	 * @return
	 */
	public List<Comment> getStoreAllComment(Serializable storeID);

	/**
	 * 获取该用户的所有评论
	 * 
	 * @param storeID
	 * @return
	 */
	public List<Comment> getCustomerAllComment(Serializable accountID);
	
	public ResultSimple saveCollection(Serializable accountID, Serializable storeID);
	public ResultSimple delCollection(Serializable collectionID);
	public List<CollectionSimpleMSG> getAllCollection(Serializable accountID);
	public ResultSimple checkCollectionIsSave(Serializable accountID, Serializable storeID);
	
}
