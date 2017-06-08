package org.common.FDBK.service.impl;

import java.io.Serializable;
import java.util.List;

import org.common.FDBK.dao.CollectionDao;
import org.common.FDBK.dao.CommentDao;
import org.common.FDBK.dao.IndexDataDao;
import org.common.FDBK.domain.Comment;
import org.common.FDBK.domain.DATA.ShopSimpleMSG;
import org.common.FDBK.domain.simple.CollectionSimpleMSG;
import org.common.FDBK.service.InfoManage;
import org.common.RBAC.dao.exception.AccountNotExistException;
import org.common.RBAC.dao.exception.StoreNotExistException;
import org.common.util.ResultSimple;
import org.common.util.ResultUtil;

public class InfoManageImpl implements InfoManage {
	// 注入
	IndexDataDao indexDataDao;
	CommentDao commentDao;
	CollectionDao collectionDao;

	public CollectionDao getCollectionDao() {
		return collectionDao;
	}

	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
	}

	public IndexDataDao getIndexDataDao() {
		return indexDataDao;
	}

	public void setIndexDataDao(IndexDataDao indexDataDao) {
		this.indexDataDao = indexDataDao;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public ResultSimple checkCollectionIsSave(Serializable accountID, Serializable storeID) {
		boolean flag = collectionDao.checkCoolectionIsExist(accountID, storeID);
		if (flag) {
			return ResultUtil.saveResult("000800", "店铺已被已收藏!");
		}
		return ResultUtil.saveResult("000801", "店铺未被收藏!");
	}

	@Override
	public ResultSimple saveCollection(Serializable accountID, Serializable storeID) {
		boolean flag_check = collectionDao.checkCoolectionIsExist(accountID, storeID);
		if(flag_check){
			return ResultUtil.saveResult("000800", "店铺已被已收藏!");
		}
		try {
			boolean flag = collectionDao.saveCollection(accountID, storeID);
			if (flag) {
				return ResultUtil.saveResult("000804", "店铺收藏成功!");
			}
		} catch (AccountNotExistException e) {
			return ResultUtil.saveResult("000803", "账户不存在!");
		} catch (StoreNotExistException e) {
			return ResultUtil.saveResult("000802", "店铺不存在!");
		}
		return ResultUtil.saveResult("000805", "店铺收藏失败!");
	}

	@Override
	public ResultSimple delCollection(Serializable collectionID) {
		boolean flag = collectionDao.deleteCollection(collectionID);
		if (flag) {
			return ResultUtil.saveResult("000806", "取消店铺收藏成功!");
		}
		return ResultUtil.saveResult("000807", "取消店铺收藏失败!");
	}

	@Override
	public List<CollectionSimpleMSG> getAllCollection(Serializable accountID) {
		return collectionDao.getAllCollection(accountID);
	}

	@Override
	public List<ShopSimpleMSG> getIndexDataByGoodName(Double x, Double y, String addCode, String shopNodeId,
			String goodName) {
		return indexDataDao.getDataByGoodName(x, y, addCode, shopNodeId, goodName);
	}

	@Override
	public List<ShopSimpleMSG> getIndexDataBySort(Double x, Double y, String addCode, String shopNodeId, String sort) {
		return indexDataDao.getIndexDataBySort(x, y, addCode, shopNodeId, sort);
	}

	@Override
	public Long getStoreByArea(String addCode) {
		return indexDataDao.getInDexDataByAddress(addCode);
	}

	@Override
	public List<ShopSimpleMSG> getIndexData(Double x, Double y, String addCode, int start, int num, int rule,
			String shopNodeId) {
		return indexDataDao.getIndexData(x, y, addCode, start, num, rule, shopNodeId);
	}

	@Override
	public ResultSimple writeComment(Serializable storeID, Serializable customerID, String comment, Float level) {
		try {
			boolean flag = commentDao.addcomment(storeID, customerID, comment, level);
			if (flag) {
				return ResultUtil.saveResult("000809", "评论成功!");
			}
		} catch (AccountNotExistException e) {
			return ResultUtil.saveResult("000803", "账户不存在!");
		} catch (StoreNotExistException e) {
			return ResultUtil.saveResult("000802", "店铺不存在!");
		}
		return ResultUtil.saveResult("000808", "评论失败!");
	}

	@Override
	public List<Comment> getStoreAllComment(Serializable storeID) {
		return commentDao.getStoreAllComment(storeID);
	}

	@Override
	public List<Comment> getCustomerAllComment(Serializable accountID) {
		return commentDao.getCustomerAllComment(accountID);
	}

}
