package cn.zlb.dao.impl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
/**
 * 实现BusinessDao 接口
 * @author Bingo
 */
import org.springframework.stereotype.Repository;

import cn.zlb.dao.BusinessDao;
import cn.zlb.dto.BusinessDto;
/**
 * 继承自 BaseDaoImpl
 * @author Bingo
 */
import cn.zlb.entity.TBusiness;
import cn.zlb.tools.Pager;
@Repository("businessDao")
public class BusinessDaoImpl extends BaseDaoImpl<TBusiness> implements BusinessDao{
	/**
	 * 1.0商户登录
	 */
	@Override
	public TBusiness login(TBusiness business) {
		//第一种登录 用户名密码校验登录
		/*String hql="from TBusiness bus where bus.busName=? and bus.busPass=?";
		return  (TBusiness) super.findObject(hql,new Object[]{business.getBusName(),business.getBusPass()});*/
		
		//第二种登录 用户名or 手机or 邮箱 验证登录 .add(Restrictions.eq("busPass", business.getBusPass()))
		 Criteria cri= getSession().createCriteria(TBusiness.class).add(Restrictions.disjunction()
				 .add(Restrictions.eq("busName",business.getBusName()))
				 .add(Restrictions.eq("busPass", business.getBusPass()))
				 .add(Restrictions.eq("busEmail", business.getBusEmail())))
				 .add(Restrictions.eq("busPass",  business.getBusPass())
				 );
		  
		 return  (TBusiness) cri.uniqueResult();
	}
	/**
	 * 2.0 模糊查询
	 */

	@Override
	public Pager<TBusiness> searchBusiness(String hql,BusinessDto dto, Pager<TBusiness> pages) {
		Query query=getSession().createQuery(hql);
		query.setDate("busTime", dto.getBusTime());
		 query.setDate("endTime", dto.getEndTime());
		//给查询  设置分页参数
		setPage(query,pages);
		//查询结果
		List<TBusiness> list=query.list();
		//添加结果集到 pager
		pages.setResultList(list);
		
		//查询总记录
		getAllCount(hql, null, null,dto, pages);
		return pages;
	}
	
	/**
	 * 实现 自己特定的 方法
	 */
}
