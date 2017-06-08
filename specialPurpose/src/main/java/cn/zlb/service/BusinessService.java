package cn.zlb.service;

import java.io.Serializable;
import java.util.List;

import cn.zlb.dto.BusinessDto;
import cn.zlb.entity.TAddress;
import cn.zlb.entity.TBusiness;
import cn.zlb.tools.Pager;

public interface BusinessService {
	/**
	 * 1.0 添加商户or 注册商户
	 */
	Serializable add(TBusiness business);
	/**
	 * 2.0注册
	 */
	void regist(TBusiness business,TAddress address);
	/**
	 *1.0商户登录 
	 * @param business
	 * @return
	 */
	TBusiness login(TBusiness business);
	
	/**
	 * 3.0 更新商户
	 * @param business
	 */
	void updateBusiness(TBusiness business);
	
	/**
	 * 4.0删除商户
	 * @param business
	 */
	void deleteBusiness(TBusiness business);
	
	/**
	 * 5.0 
	 * @param busDto
	 * @return 
	 */
	 TBusiness findBusiness(BusinessDto busDto);
	
	/**
	 * 6.0模糊查询商户
	 * @param busDto
	 * @return
	 */
	Pager<TBusiness> serachBusiness(BusinessDto busDto,Pager<TBusiness> pager);
	
	/**
	 * 7.0 根据商户名查询
	 */
	TBusiness findBusinessByName(BusinessDto busDto);
	/**
	 * 8.0根据手机号查询
	 */
	TBusiness findBusinessByPhone(BusinessDto busDto );
	
	/**
	 * 9.0查询是否存在  用户
	 */
	boolean checkBusName(String busName);
	boolean checkBusEmail(String busEmail);
	/**
	 * 10.0修改用户密码
	 * @param busDto
	 */
	boolean modifyBusName(BusinessDto busDto);
	
	/**
	 * 10.1添加图片
	 */
	boolean addPicture(BusinessDto busDto);
	
}












