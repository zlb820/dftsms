package cn.zlb.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zlb.dao.BusinessDao;
import cn.zlb.dto.BusinessDto;
import cn.zlb.entity.TAddress;
import cn.zlb.entity.TBusiness;
import cn.zlb.entity.TStore;
import cn.zlb.exception.LoginException;
import cn.zlb.exception.StoreNotFoundException;
import cn.zlb.service.BusinessService;
import cn.zlb.tools.CommonUtils;
import cn.zlb.tools.Pager;

@Service("businessService")
@Transactional
public class BusinessServiceImpl implements BusinessService{
	@Resource
	private BusinessDao	businessDao;
	/**
	 * 1.0添加 商户
	 */
	@Override
	public Serializable add(TBusiness business) {
		return businessDao.add(business);
	}
	
	/**
	 * 1.1注册
	 */
	
	@Override
	public void regist(TBusiness business,TAddress address) {
		//1-1
		business.setPkBusId(CommonUtils.uuid());
		business.setBusStatus(false);
		business.setBusTime(new Timestamp(System.currentTimeMillis()));
		//1-2 初始化店铺
		TStore store=new TStore();
		store.setStoName(business.getBusName()+"dp");
		store.setStoScore("1");
		store.setStoStatus(false);
		store.setTAddress(address);
		
		business.setTStore(store);
		add(business);
	}
	/**
	 * 1.2更新商户
	 */
	public void update(TBusiness business){
		businessDao.saveOrUpdate(business);
	}

	/**
	 * 2.0商户登录
	 * @param businessDao
	 */
	@Override
	public TBusiness login(TBusiness business) {
		TBusiness result=businessDao.login(business);
		if (result==null) {
			throw new LoginException("用户名或密码不存在！！！");
		}
		return result ;
		 
	}
	
	/**
	 * 3.0 更新商户信息
	 */
	@Override
	public void updateBusiness(TBusiness business) {
		 businessDao.update(business);
		
	}
	
	/**
	 * 4.0 删除商户
	 * @param business
	 */
	@Override
	public void deleteBusiness(TBusiness business) {
		businessDao.delete(business);
	}
	
	/**
	 * 5.0条件查询商户
	 */
	
	@Override
	public TBusiness findBusiness(BusinessDto busDto) {
		String hql="from TBusiness as bus where 1=1";
		if (busDto.getPkBusId()!=null) 
			hql+="and bus.pkBusId= :pkBusId";
		if (busDto.getBusName()!=null) 
			hql+="and bus.busName=:busName";
		if (busDto.getBusPhone()!=null)
			hql+="and bus.busPhone = :busPhone";
		if(busDto.getBusIdentity()!=null)
			hql+="and bus.busIdentity=:busIdentiey";
			
		List<TBusiness> listSto=businessDao.findObjectByDto(hql, busDto);
			if (listSto.size()==0) {
				throw new StoreNotFoundException("business 不存在 ,请检查检索条件！！");
			}
			return listSto.get(0);
	}
	
	/**
	 * 6.0模糊查询商户
	 */

	@Override
	public Pager<TBusiness> serachBusiness(BusinessDto busDto,Pager<TBusiness> pages) {
		//拼装hql
				String hql="from TBusiness bus where  1=1 ";
				Map<String, Object> params=new HashMap<String, Object>();
				if (busDto.getBusName()!=null)  
					hql+="and bus.busName like '%"+busDto.getBusName()+"%'";
				if (busDto.getBusPhone()!=null) 
					hql+="and bus.busPhone like '%"+busDto.getBusPhone()+"%'";
				if (busDto.getBusIdentity()!=null) 
					hql+="and bus.busIdentity like '%"+busDto.getBusIdentity()+"%'";
				if (busDto.getBusStatus()!=null) 
					hql+="and bus.busStatus  like '%"+busDto.getBusStatus()+"%'";
				if (busDto.getBusTime()!=null ){
					 
					hql+="and bus.busTime between :busTime and :endTime";
				}
				System.out.println("hql = "+hql );
			/*	if (busDto.getEndTime()!=null) {
					hql+="and bus.busTime <=:endTime  ";
				}*/
				
			  Pager<TBusiness> pager=businessDao.searchBusiness(hql, busDto, pages);
				return  pager;
	 
	}
	


	/**
	 * 7.0根据用户名查询
	 */
	@Override
	public TBusiness findBusinessByName(BusinessDto busDto ) {
		String hql="from TBusiness bus where bus.busName=?";
		return (TBusiness) businessDao.findObject(hql, new Object[]{busDto.getBusName()});
	}
	 /**
	  * 8.0根据手机号查询
	  */

	@Override
	public TBusiness findBusinessByPhone(BusinessDto busDto ) {
		String hql="from TBusiness bus where bus.busIdentity=?";
		return (TBusiness) businessDao.findObject(hql, new Object[]{busDto.getBusIdentity()});
	}
	/**
	 *    
	 * 9.0查询是否存在  用户
	 */
	 
	@Override
	public boolean checkBusName(String busName) {
		 String hql="from TBusiness bus where bus.busName= ? ";
		 TBusiness bus=(TBusiness) businessDao.findObject(hql, new Object[]{busName});
		 
		return bus==null?true:false;
	}
	@Override
	public boolean checkBusEmail(String busEmail) {
		 String hql="from TBusiness bus where bus.busEmail= ? ";
		 TBusiness bus=(TBusiness) businessDao.findObject(hql, new Object[]{busEmail});
		 
		return bus==null?true:false;
	}
	/**
	 * 10.0更改用户密码
	 * @param businessDao
	 * @param pager
	 */
	public boolean modifyBusName(BusinessDto busDto) {
		String hql="update TBusiness bus set bus.busPass =? where bus.pkBusId=? ";
		int i=businessDao.updateByObject(hql, new Object[]{busDto.getBusPass(),busDto.getPkBusId()});
		return i==1?true:false;
	}
	/**
	 * 10.1添加图片
	 */
	@Override
	public boolean addPicture(BusinessDto busDto ) {
		 String hql="update TBusiness bus set bus.TPictures.pkPicId =? where bus.pkBusId=?";
		 int i=businessDao.updateByObject(hql, new Object[]{busDto.getTPictures().getPkPicId(),busDto.getPkBusId()});
		return   i==1?true:false;
	}
	
	
	
	
	
	
	//setter
	public void setBusinessDao(BusinessDao businessDao,Pager<TBusiness> pager) {
		this.businessDao = businessDao;
	}




	



	
	
	
}
