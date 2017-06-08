package org.common.FDBK.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.common.FDBK.dao.IndexDataDao;
import org.common.FDBK.domain.DATA.ShopSimpleMSG;
import org.common.RBAC.dao.impl.BaseDaoImpl;
import org.common.RBAC.domain.SysInfo;
import org.common.util.ReadPropertyOf;
import org.hibernate.Query;

public class IndexDataDaoImpl extends BaseDaoImpl<ShopSimpleMSG> implements IndexDataDao {

	/**
	 * 根据商品名称模糊查询
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List<ShopSimpleMSG> getDataByGoodName(Double x, Double y, String addCode, String shopNodeId,
			String goodName) {
		List tmp = getSessionFactory().getCurrentSession().createSQLQuery(sqlCreateOfGoodsearch(x, y, addCode, goodName))
				.list();
		List<ShopSimpleMSG> result = new ArrayList<>();
		SysInfo sysInfo = ReadPropertyOf.loadPropertiesOfSys();
		// String forword = "http://" + sysInfo.getServerAddress() + ":" +
		// sysInfo.getServerPort();
		String forword = "http://" + sysInfo.getServerAddress();
		for (Object object : tmp) {
			ShopSimpleMSG shopMSG = new ShopSimpleMSG();
			Object[] row = (Object[]) object;
			shopMSG.setStyleId(shopNodeId);
			shopMSG.setStoID(String.valueOf(row[0]));
			shopMSG.setStoName(String.valueOf(row[1]));
			shopMSG.setScore(Float.valueOf(String.valueOf(row[2])));
			shopMSG.setSaleValume(Integer.valueOf(String.valueOf(row[3])));
			shopMSG.setURL(forword + String.valueOf(row[4]));
			shopMSG.setAddConcrete(String.valueOf(row[5]));
			shopMSG.setDistance((Double) row[6]);
			shopMSG.setPhone(String.valueOf(row[7]));
			shopMSG.setSort(String.valueOf(row[8]));
			result.add(shopMSG);
		}
		return result;
	}
	private String sqlCreateOfGoodsearch(Double x, Double y, String addCode, String goodName) {
		// asc或desc（即升级或降序）
		StringBuffer str = new StringBuffer();
		str.append(
				"SELECT fk_sto_id AS stoID,sto_name AS stoName,sto_score AS score,goo_sales AS saleValume,pic_url AS URL,add_concrete AS addConcrete,");
		// 计算距离的核心
		str.append(" st_distance (point (" + x + ", " + y + "),point(add_x,add_y) ) * 111195 as distance, ");
		str.append(" bus_phone as phone,cat_name as cat_name ");
		str.append("FROM t_store,t_goods,t_pictures,t_address,t_business,t_store_category ");
		// 多表连接
		str.append(
				"WHERE t_store.fk_picture=t_pictures.pk_pic_id AND t_store.fk_address=t_address.pk_add_id AND t_business.pk_bus_id=t_store.fk_sto_id AND t_store_category.pk_cat_id=t_store.fk_category ");
		// 添加地址过滤条件
		str.append("AND t_address.fk_add_area=" + addCode);
		str.append(" AND t_goods.goo_name like '%"+goodName+"%'");
		return str.toString();
	}
	
	/***************************************************************************/
	/**
	 * 根据分类反馈
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List<ShopSimpleMSG> getIndexDataBySort(Double x, Double y, String addCode, String shopNodeId, String sort) {
		List tmp = getSessionFactory().getCurrentSession().createSQLQuery(sqlCreateOfSort(x, y, addCode, sort))
				.list();
		List<ShopSimpleMSG> result = new ArrayList<>();
		SysInfo sysInfo = ReadPropertyOf.loadPropertiesOfSys();
		// String forword = "http://" + sysInfo.getServerAddress() + ":" +
		// sysInfo.getServerPort();
		String forword = "http://" + sysInfo.getServerAddress();
		for (Object object : tmp) {
			ShopSimpleMSG shopMSG = new ShopSimpleMSG();
			Object[] row = (Object[]) object;
			shopMSG.setStyleId(shopNodeId);
			shopMSG.setStoID(String.valueOf(row[0]));
			shopMSG.setStoName(String.valueOf(row[1]));
			shopMSG.setScore(Float.valueOf(String.valueOf(row[2])));
			shopMSG.setSaleValume(Integer.valueOf(String.valueOf(row[3])));
			shopMSG.setURL(forword + String.valueOf(row[4]));
			shopMSG.setAddConcrete(String.valueOf(row[5]));
			shopMSG.setDistance((Double) row[6]);
			shopMSG.setPhone(String.valueOf(row[7]));
			shopMSG.setSort(String.valueOf(row[8]));
			result.add(shopMSG);
		}
		return result;
	}
	
	private String sqlCreateOfSort(Double x, Double y, String addCode,  String sort) {
		// asc或desc（即升级或降序）
		StringBuffer str = new StringBuffer();
		str.append(
				"SELECT fk_sto_id AS stoID,sto_name AS stoName,sto_score AS score,goo_sales AS saleValume,pic_url AS URL,add_concrete AS addConcrete,");
		// 计算距离的核心
		str.append(" st_distance (point (" + x + ", " + y + "),point(add_x,add_y) ) * 111195 as distance, ");
		str.append(" bus_phone as phone,cat_name as cat_name ");
		str.append("FROM t_store,t_goods,t_pictures,t_address,t_business,t_store_category ");
		// 多表连接
		str.append(
				"WHERE t_store.fk_goods=t_goods.pk_goo_id AND t_store.fk_picture=t_pictures.pk_pic_id AND t_store.fk_address=t_address.pk_add_id AND t_business.pk_bus_id=t_store.fk_sto_id AND t_store_category.pk_cat_id=t_store.fk_category ");
		// 添加地址过滤条件
		str.append("AND t_address.fk_add_area=" + addCode);
		str.append(" AND t_store_category.pk_cat_id=" + sort);
		return str.toString();
	}
	
	
	/* 根据综合元素进行反馈 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List<ShopSimpleMSG> getIndexData(Double x, Double y, String addCode, int start, int num, int rule,
			String shopNodeId) {
		/*
		 * String sql = "SELECT fk_sto_id AS stoID," +
		 * "sto_name AS stoName,sto_score AS score,goo_sales AS saleValume,"
		 * + "pic_url AS URL,add_concrete AS addConcrete," +
		 * " st_distance (point (" + x + ", " + y +
		 * "),point(add_x,add_y) ) * 111195 as distance " +
		 * "FROM t_store,t_goods,t_pictures,t_address " +
		 * "WHERE t_store.fk_goods=t_goods.pk_goo_id " +
		 * "AND t_store.fk_picture=t_pictures.pk_pic_id " +
		 * "AND t_store.fk_address=t_address.pk_add_id AND t_address.fk_add_area="
		 * + addCode;
		 */
		List tmp = getSessionFactory().getCurrentSession().createSQLQuery(sqlCreate(x, y, addCode, start, num, rule))
				.list();
		List<ShopSimpleMSG> result = new ArrayList<>();
		SysInfo sysInfo = ReadPropertyOf.loadPropertiesOfSys();
		// String forword = "http://" + sysInfo.getServerAddress() + ":" +
		// sysInfo.getServerPort();
		String forword = "http://" + sysInfo.getServerAddress();
		for (Object object : tmp) {
			ShopSimpleMSG shopMSG = new ShopSimpleMSG();
			Object[] row = (Object[]) object;
			shopMSG.setStyleId(shopNodeId);
			shopMSG.setStoID(String.valueOf(row[0]));
			shopMSG.setStoName(String.valueOf(row[1]));
			shopMSG.setScore(Float.valueOf(String.valueOf(row[2])));
			shopMSG.setSaleValume(Integer.valueOf(String.valueOf(row[3])));
			shopMSG.setURL(forword + String.valueOf(row[4]));
			shopMSG.setAddConcrete(String.valueOf(row[5]));
			shopMSG.setDistance((Double) row[6]);
			shopMSG.setPhone(String.valueOf(row[7]));
			shopMSG.setSort(String.valueOf(row[8]));
			result.add(shopMSG);
		}
		return result;
	}
	

	/**
	 * 根据需求组拼SQL语句
	 * 
	 * @param x
	 * @param y
	 * @param addCode
	 * @param start
	 * @param num
	 * @param rule
	 * @return
	 */
	private String sqlCreate(Double x, Double y, String addCode, int start, int num, int rule) {
		// asc或desc（即升级或降序）
		StringBuffer str = new StringBuffer();
		str.append(
				"SELECT fk_sto_id AS stoID,sto_name AS stoName,sto_score AS score,goo_sales AS saleValume,pic_url AS URL,add_concrete AS addConcrete,");
		// 计算距离的核心
		str.append(" st_distance (point (" + x + ", " + y + "),point(add_x,add_y) ) * 111195 as distance, ");
		str.append(" bus_phone as phone,cat_name as cat_name ");
		str.append("FROM t_store,t_goods,t_pictures,t_address,t_business,t_store_category ");
		// 多表连接
		str.append(
				"WHERE t_store.fk_goods=t_goods.pk_goo_id AND t_store.fk_picture=t_pictures.pk_pic_id AND t_store.fk_address=t_address.pk_add_id AND t_business.pk_bus_id=t_store.fk_sto_id AND t_store_category.pk_cat_id=t_store.fk_category ");
		// 添加地址过滤条件
		str.append("AND t_address.fk_add_area=" + addCode);
		switch (rule) {
		case 1:
			str.append(" ORDER BY distance asc ");
			break;
		case 2:
			str.append(" ORDER BY distance desc ");
			break;
		case 3:
			str.append(" ORDER BY score asc ");
			break;
		case 4:
			str.append(" ORDER BY score desc ");
			break;
		case 5:
			str.append(" ORDER BY saleValume asc ");
			break;
		case 6:
			str.append(" ORDER BY saleValume desc ");
			break;
		default:
			break;
		}

		// 设置偏移量及起始位置
		str.append(" LIMIT " + (start - 1) * num + "," + num);
		return str.toString();
	}

	@Override
	public long getInDexDataByAddress(String addCode) {
		String hql = "select count(*) from Address a where a.areacounty=?";
		Query q = getSessionFactory().getCurrentSession().createQuery(hql);
		q.setString(0, addCode);
		List<?> l = q.list();
		// 返回查询得到的实体总数
		if (l != null && l.size() == 1) {
			return (Long) l.get(0);
		}
		return 0;
	}

}
