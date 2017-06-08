package org.common.FDBK.dao;

import java.util.List;

public interface CityDao<T> {
	/**
	 * 根据省份ID获取其包含的所有市
	 * 
	 * @param Pro_code
	 * @return
	 */
	List<T> getCitysByProvinceCode(String Pro_code);
}
