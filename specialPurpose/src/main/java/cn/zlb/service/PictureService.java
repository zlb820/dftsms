package cn.zlb.service;

import java.io.Serializable;
import java.util.List;

import cn.zlb.entity.TPictures;
import cn.zlb.tools.Pager;

public interface PictureService {
	/**
	 * 1.0添加图片
	 */
	public Serializable add(TPictures pic);
	/**
	 * 2.0修改图片
	 */
	public TPictures update(TPictures pic);
	/**
	 * 3.0查找图片
	 */
	public Pager<TPictures> findPicture(TPictures pic,Pager<TPictures> pages);
	public List<TPictures> findPicture(TPictures pic);
}
