package org.common.FDBK.dao.impl;

import java.util.List;

import org.common.FDBK.dao.AddressDao;
import org.common.FDBK.domain.Address;
import org.common.RBAC.dao.impl.BaseDaoImpl;

public class AddressDaoImpl  extends BaseDaoImpl<Address> implements AddressDao{

	@Override
	public List<Address> getAddressByArea(String addArea) {
		String hql = "from Address a where a.areacounty = ?0";
		List<Address> result = find(hql, addArea);
		return result;
	}
	

}
