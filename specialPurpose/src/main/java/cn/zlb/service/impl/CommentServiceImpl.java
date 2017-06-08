package cn.zlb.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zlb.dao.CommentDao;
import cn.zlb.dto.CommentDto;
import cn.zlb.entity.TComment;
import cn.zlb.exception.CommentNotFoundException;
import cn.zlb.service.CommentService;
import cn.zlb.tools.Pager;
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
	@Resource
	private CommentDao commentDao;
	static Logger logger=Logger.getLogger(CommentServiceImpl.class);
	/**
	 * 1.0 增加comment信息
	 * @param cheap
	 * @return
	 */
	@Override
	public Serializable add(TComment comment) {
		 return commentDao.add(comment);
	}
	/**
	 * 2.0更新comment信息
	 * @param cheap
	 */
	@Override
	public void update(TComment comment) {
		commentDao.update(comment);
	}
	/**
	 * 3.0 删除评论信息
	 * @param cheap
	 */
	@Override
	public void delete(TComment comment) {
		commentDao.delete(comment);
	}
	/**
	 * 4.0查找comment信息
	 * @param cheDto
	 * @return
	 */
	@Override
	public Pager<TComment> findComment(CommentDto comDto, Pager<TComment> pages) {
		String hql="from TComment com where 1=1";
		Map<String, Object> params=new HashMap<String, Object>();
		if(comDto.getTStore()!=null){
			hql+="and com.TStore.id= :stoId";
			params.put("stoId", comDto.getTStore().getFkStoId());}
		if (comDto.getTCustomer()!=null) {
			hql="and com.TCustomer.id = :cusId";
			params.put("cusId", comDto.getTCustomer().getPkCusId()); }
		if (comDto.getComLevel()!=null) {
			hql="and com.comLevel= :comLevel";
			params.put("comLevel", comDto.getComLevel()); }
		Pager<TComment> pager=commentDao.queryByAlias(hql, params, pages);
		if (pager.getResultList().isEmpty()) {
			throw new CommentNotFoundException("没有任何评论！！！");
		}
		return pager;
	}
	/**
	 * 5.0模糊查询  
	 * @param comDto
	 * @param pages
	 * @return
	 */
	@Override
	public Pager<TComment> searchComment(CommentDto comDto, Pager<TComment> pages) {
		String hql="from TComment com where 1=1";
		Map<String, Object> params=new HashMap<String, Object>();
		if (comDto.getComContent()!=null) {
			hql+="and com.comContent like :comContent";
			params.put("comContent", "%"+comDto.getComContent()+"%"); }
		logger.info("hql = "+hql +" and " +"params comComent= " + params.get("comContent"));
		Pager<TComment> pager=commentDao.queryByAlias(hql, params, pages);
		if (pager.getResultList().isEmpty()) {
			throw new CommentNotFoundException("没有任何评论！！！");
		}
		return pager;
	}
	
	/**
	 * 6.0店铺评分
	 * @return
	 */
	@Override
	public int findStoreLevel(String stoId ) {
		int i=0;
		String hql="select max(com.comLevel)  from  TComment com  where  com.TStore.fkStoId=?";
		System.out.println("commentService stoId =" +hql);
		 i=commentDao.findStoreLevel(hql, stoId);
		return i;
	}
	
	
	
	
	
	
	
	
	//setter
	public CommentDao getCommentDao() {
		return commentDao;
	}
	
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	
	
}
