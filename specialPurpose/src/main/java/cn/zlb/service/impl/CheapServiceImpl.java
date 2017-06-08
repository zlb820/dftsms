package cn.zlb.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zlb.dao.CheapDao;
import cn.zlb.entity.TCheap;
import cn.zlb.exception.CheapInfoNotFoundException;
import cn.zlb.service.CheapService;
import cn.zlb.tools.CheapDto;
@Service("cheapService")
@Transactional
public class CheapServiceImpl implements CheapService {
	@Resource
	private CheapDao cheapDao;
	/**
	 * 1.0 增加cheap信息
	 * @param cheap
	 * @return
	 */
	@Override
	public Serializable add(TCheap cheap) {
		return cheapDao.add(cheap);
	 
	}
	/**
	 * 2.0更新cheap信息
	 * @param cheap
	 */
	@Override
	public void update(TCheap cheap) {
		cheapDao.update(cheap);
	}
	/**
	 * 3.0 删除优惠信息
	 * @param cheap
	 */
	@Override
	public void delete(TCheap cheap) {
		cheapDao.delete(cheap);
	}
	/**
	 * 4.0查找cheap信息
	 * @param cheDto
	 * @return
	 */
	@Override
	public List<TCheap> findCheap(CheapDto cheDto) {
		String hql="from TCheap che where 1=1";
		if(cheDto.getPkCheId()!=null)
			hql+="and che.pkCheId =:pkCheId";
		if(cheDto.getCheNam()!=null)
			hql+="and che.cheNam= :cheNam";
		if (cheDto.getFkStoId()!=null) {
			hql+="and che.TStore.fkStoId=:fkStoId";
		}
		List<TCheap> list=cheapDao.findObjectByDto(hql, cheDto);
		if (list.isEmpty()) {
			throw new CheapInfoNotFoundException("优惠信息不存在,检查检索条件！！！");
		}
		return list;
	}
	 
	/**
	 * 5.0 模糊查询
	 * @param cheDto
	 * @return
	 */
	@Override
	public List<TCheap> searchCheap(CheapDto cheDto) {
		String hql="from TCheap che where 1=1";
		if(cheDto.getCheNam()!=null)
			hql+="and che.cheNam like '%"+cheDto.getCheNam()+"%'";
		
		List<TCheap> list=cheapDao.findObjectByDto(hql, null);
		if (list.isEmpty()) {
			throw new CheapInfoNotFoundException("优惠信息不存在,检查检索条件！！！");
		}
		return list;
		 
	}
	
}
