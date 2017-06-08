package cn.zlb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zlb.dao.AddressDao;
import cn.zlb.dto.AddressDto;
import cn.zlb.entity.TAddress;
import cn.zlb.service.AddressService;
import cn.zlb.tools.CommonUtils;
@Transactional
@Service("addressService")
public class AddressServiceImpl implements AddressService {
	@Resource
	private AddressDao addressDao;

	/**
	 * 1.0保存地址
	 * @return
	 */
	public void save(TAddress address){
		address.setPkAddId(CommonUtils.uuid());
		addressDao.add(address);
		
	}
	 /**
	  * 2.0修改地址
	  * @return
	  */
	 
	public void modifyAddress(TAddress address){
		addressDao.saveOrUpdate( address);
		
	}
	
	/**
	 * 3.0查找店铺地址
	 * @param 店铺ID
	 * @return
	 */

	@Override
	public TAddress findAddress(String stoId) {
		String hql="from TAddress add where add.TStore.fkStoId=?";
		TAddress address= (TAddress) addressDao.findObject(hql, new Object[]{stoId} );
		return address;
		
	}
	
	
	
	//setter
	public AddressDao getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}


	
	
}
