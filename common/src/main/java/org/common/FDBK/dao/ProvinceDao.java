package org.common.FDBK.dao;

import java.util.List;

public interface ProvinceDao<T> {
	/**
	 * 获取所有省份
	 * @return
	 */
	List<T> getAllProvice();
}
