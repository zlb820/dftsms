package org.common.FDBK.dao;

import java.util.List;

import org.common.FDBK.domain.Address;
import org.common.RBAC.dao.BaseDao;

public interface AddressDao extends BaseDao<Address> {
	List<Address> getAddressByArea(String addArea);
}
