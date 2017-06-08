package org.common.FDBK.dao;

import java.util.List;

public interface AreacountyDao<T> {
	List<T> getAllAreacountyByCityCode(String cityCode);
}
