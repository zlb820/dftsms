package cn.zlb.service;

import java.io.Serializable;
import java.util.List;

import cn.zlb.dto.CommentDto;
import cn.zlb.entity.TComment;
import cn.zlb.tools.Pager;

public interface CommentService {
	/**
	 * 1.0 增加comment信息
	 * @param cheap
	 * @return
	 */
	Serializable add(TComment comment);
	/**
	 * 2.0更新comment信息
	 * @param cheap
	 */
	void update(TComment comment);
	/**
	 * 3.0 删除评论信息
	 * @param cheap
	 */
	void delete(TComment comment);
	/**
	 * 4.0查找comment信息
	 * @param cheDto
	 * @return
	 */
	Pager<TComment> findComment(CommentDto comDto,Pager<TComment> pages);
	/**
	 * 5.0模糊查询
	 * @param comDto
	 * @param pages
	 * @return
	 */
	Pager<TComment> searchComment(CommentDto comDto,Pager<TComment> pages);
	
	/**
	 * 6.0店铺评分
	 */
	public int findStoreLevel(String stoId );
}









