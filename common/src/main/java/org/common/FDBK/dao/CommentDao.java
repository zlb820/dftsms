package org.common.FDBK.dao;

import java.io.Serializable;
import java.util.List;

import org.common.FDBK.domain.Comment;
import org.common.RBAC.dao.BaseDao;
import org.common.RBAC.dao.exception.AccountNotExistException;
import org.common.RBAC.dao.exception.StoreNotExistException;

public interface CommentDao extends BaseDao<Comment> {
	/**
	 * 添加评论
	 * 
	 * @param storeID
	 * @param customerID
	 * @param comment
	 * @param level
	 * @return
	 */
	boolean addcomment(Serializable storeID, Serializable customerID, String comment, Float level)
			throws AccountNotExistException, StoreNotExistException;

	/**
	 * 获取商家所有信息
	 * 
	 * @param storeID
	 * @return
	 */
	List<Comment> getStoreAllComment(Serializable storeID);

	/**
	 * 获取用户的所有评论
	 * 
	 * @param customerID
	 * @return
	 */
	List<Comment> getCustomerAllComment(Serializable customerID);

}
