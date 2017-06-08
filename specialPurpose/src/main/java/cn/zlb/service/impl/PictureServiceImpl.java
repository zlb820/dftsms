package cn.zlb.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zlb.dao.PictureDao;
import cn.zlb.entity.TPictures;
import cn.zlb.service.PictureService;
import cn.zlb.tools.CommonUtils;
import cn.zlb.tools.Pager;

@Service("pictureService")	
@Transactional
public class PictureServiceImpl implements PictureService {
	@Resource
	private PictureDao pictureDao;
	/**
	 * 1.0添加图片
	 * @param 添加图片需要 自行 添加 UUID
	 */
	@Override
	public Serializable add(TPictures pic) {
		//pic.setPkPicId(CommonUtils.uuid());
		Serializable id=pictureDao.add(pic);
		return id;
	}
	/**
	 * 2.0更新图片
	 */
	@Override
	public TPictures update(TPictures pic) {
		
		return pictureDao.saveOrUpdate(pic);
	}
	/**
	 * 3.0分页插叙
	 */
	@Override
	public Pager<TPictures> findPicture(TPictures pic,Pager<TPictures> pages) {
		String hql="from TPictures pic where 1=1";
		Map<String, Object>map=new HashMap<String, Object>();
		if (pic.getPkPicId()!=null) {
			hql+="and pic.pkPicId=:pkPicId";
			map.put("pkPicId", pic.getPkPicId());
		}
		if (pic.getTStores().getFkStoId()!=null) {
			hql+="and pic.TStores.fkStoId=:fkStoId";
			map.put("fkStoId", pic.getTStores().getFkStoId());
		}
		if (pic.getTBusinesses().getPkBusId()!=null) {
			hql+="and pic.TBusinesses.pkBusId=:pkBusId";
			map.put("pkBusId", pic.getTBusinesses().getPkBusId());
		}
		Pager<TPictures> pager=pictureDao.queryByAlias(hql, map, pages);
		return pager;
	}
	/**
	 * 4.0list查询
	 */
	@Override
	public List<TPictures> findPicture(TPictures pic ) {
		String hql="from TPictures pic where 1=1";
		Map<String, Object>map=new HashMap<String, Object>();
		if (pic.getPkPicId()!=null) {
			hql+="and pic.pkPicId=:pkPicId";
			map.put("pkPicId", pic.getPkPicId());
		}
		if (pic.getTStores().getFkStoId()!=null) {
			hql+="and pic.TStores.fkStoId=:fkStoId";
			map.put("fkStoId", pic.getTStores().getFkStoId());
		}
		if (pic.getTBusinesses().getPkBusId()!=null) {
			hql+="and pic.TBusinesses.pkBusId=:pkBusId";
			map.put("pkBusId", pic.getTBusinesses().getPkBusId());
		}
		List<TPictures> list=(List<TPictures>) pictureDao.listByDto(hql, null, pic);
		return list;
	}
	//setter
	public PictureDao getPictureDao() {
		return pictureDao;
	}

	public void setPictureDao(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}
	
}
