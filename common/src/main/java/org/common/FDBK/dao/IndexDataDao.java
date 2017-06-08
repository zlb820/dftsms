package org.common.FDBK.dao;

import java.util.List;

import org.common.FDBK.domain.DATA.ShopSimpleMSG;

public interface IndexDataDao {
	/**
	 * 通过县ID分页获取商家信息，并根据XY坐标计算出距离。<br/>
	 * 1：根据距离由近到远<br/>
	 * 2：根据距离由远到近 <br/>
	 * 3：根据商家评分由低到高 <br/>
	 * 4：根据商家评分由高到低<br/>
	 * 5：根据商家销量由低到高 <br/>
	 * 6：根据商家销量由高到低<br/>
	 * 默认：数据库检索顺序<br/>
	 * 
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param addCode
	 *            县Code
	 * @param start
	 *            开始位置
	 * @param num
	 *            检索数量
	 * @param rule
	 *            检索规则
	 * @return
	 */
	public List<ShopSimpleMSG> getIndexData(Double x, Double y, String addCode, int start, int num, int rule,String shopNodeId);

	/**
	 * 通过县ID获取商家总数
	 * 
	 * @param addCode
	 * @return
	 */
	public long getInDexDataByAddress(String addCode);
	
	public List<ShopSimpleMSG> getIndexDataBySort(Double x, Double y, String addCode,String shopNodeId,String sort);
	
	public List<ShopSimpleMSG> getDataByGoodName(Double x, Double y, String addCode,String shopNodeId,String goodName);
}
