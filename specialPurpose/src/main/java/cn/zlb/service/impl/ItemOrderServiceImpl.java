package cn.zlb.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.TTCCLayout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zlb.dao.ItemOrderDao;
import cn.zlb.dto.TItemorderDto;
import cn.zlb.entity.TItemorder;
import cn.zlb.service.ItemOrderService;
import cn.zlb.tools.CommonUtils;
@Service("itemOrderService")
@Transactional
public class ItemOrderServiceImpl implements ItemOrderService {
	@Resource
	private ItemOrderDao itemOrderDao;
	@Override
	public Serializable add(TItemorder itemOrder) {
		itemOrder.setPkIteId(CommonUtils.uuid());
		return itemOrderDao.add(itemOrder);
	}

	@Override
	public void update(TItemorder itemOrder) {
	  itemOrderDao.saveOrUpdate(itemOrder);
	}

	

	@Override
	public void saveOrUpdate(TItemorder itemOrder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TItemorder itemOrder) {
		itemOrderDao.delete(itemOrder);
	}
	/**
	 * 5.0 查看订单项 根据订单项Id查询
	 */
	@Override
	public TItemorder findItem(String iteId) {
		String hql="from TItemorder ite where ite.pkIteId =?";
		 TItemorder item=(TItemorder) itemOrderDao.findObject(hql, new Object[]{iteId});
		return item;
	}
	/**
	 * 5.1查找订单 by orderId （查找订单的所有的订单项）
	 */
	public List<TItemorder> findItemByOrderId(String orderId){
		String hql="from TItemorder ite where ite.TOrder.pkOrdId =?";
		List<TItemorder> list=itemOrderDao.listByObject(hql, new Object[]{orderId});
		return list;
	}
	/**
	 * 6.0修改订单项数量  和小计
	 */
	@Override
	public int modifyItem(TItemorderDto iteDto) {
		String hql="update TItemorder ite set ite.iteQuantity=? ,ite.iteSubtotal=? where ite.pkIteId=?";
		int i=itemOrderDao.updateByObject(hql, new Object[]{iteDto.getIteQuantity(),iteDto.getIteSubtotal(),iteDto.getPkIteId()});
		return i;
	}
	/**
	 * 7.0修改订单项小计
	 */
	@Override
	public int modifyItemSubtotal(TItemorderDto iteDto) {
		String hql="update TItemorder ite set ite.iteSubtotal=? where ite.pkIteId=?";
		int i=itemOrderDao.updateByObject(hql, new Object[]{iteDto.getIteQuantity(),iteDto.getPkIteId()});
		return i;
	}

	public ItemOrderDao getItemOrder() {
		return itemOrderDao;
	}

	public void setItemOrder(ItemOrderDao itemOrderDao) {
		this.itemOrderDao = itemOrderDao;
	}
	
	
	
}
