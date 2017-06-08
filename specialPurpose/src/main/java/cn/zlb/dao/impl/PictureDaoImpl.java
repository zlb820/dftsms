package cn.zlb.dao.impl;

import org.springframework.stereotype.Repository;

import cn.zlb.dao.PictureDao;
import cn.zlb.entity.TPictures;

@Repository("pictureDao")
public class PictureDaoImpl extends BaseDaoImpl<TPictures> implements PictureDao  {
	
}
