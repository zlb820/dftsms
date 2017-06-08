package cn.zlb.service;

import java.io.Serializable;
import java.util.List;

import cn.zlb.entity.TCheap;
import cn.zlb.tools.CheapDto;

public interface CheapService {
	/**
	 * 1.0 增加cheap信息
	 * @param cheap
	 * @return
	 */
	Serializable add(TCheap cheap);
	/**
	 * 2.0更新cheap信息
	 * @param cheap
	 */
	void update(TCheap cheap);
	/**
	 * 3.0 删除优惠信息
	 * @param cheap
	 */
	void delete(TCheap cheap);
	/**
	 * 4.0查找cheap信息
	 * @param cheDto
	 * @return
	 */
	List<TCheap> findCheap(CheapDto cheDto);
	/**
	 * 5.0 模糊查询
	 * @param cheDto
	 * @return
	 */
	List<TCheap> searchCheap(CheapDto cheDto);
	
}





