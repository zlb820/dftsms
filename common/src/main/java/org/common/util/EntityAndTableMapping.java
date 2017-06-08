package org.common.util;

import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;

import taotai.NotEntityException;

/**
 * 该类的主要作用是获取实体类与数据库表之间名称的对应关系
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
public class EntityAndTableMapping<T> {
	/**
	 * 通过实体类来获取其对应的表名
	 * 
	 * @param entityName
	 * @param sessionFactorty
	 * @return 返回数据库表名
	 */
	public static String getTableNameByEntityName(String entityName, SessionFactory sessionFactorty) {
		checkClassIsEntity(entityName, sessionFactorty);
		String returnTableName = null;
		Map<String, ClassMetadata> metaMap = sessionFactorty.getAllClassMetadata();
		for (Object key : metaMap.keySet()) {
			AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap.get(key);
			String className = classMetadata.getEntityMetamodel().getName();
			if (className.equals(entityName)) {// 名称匹配
				return classMetadata.getTableName().toLowerCase();
			}
		}
		return returnTableName;
	}

	/**
	 * 通过表名获取对应的实体类全名
	 * 
	 * @param tableName
	 * @param sessionFactorty
	 * @return
	 */
	public static String getEntityNameByTableName(String tableName, SessionFactory sessionFactorty) {
		String returnEntityName = null;
		Map<String, ClassMetadata> metaMap = sessionFactorty.getAllClassMetadata();
		for (Object key : metaMap.keySet()) {
			AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap.get(key);
			String table = classMetadata.getTableName().toLowerCase();
			if (table.equals(tableName.toLowerCase())) {// 名称匹配
				return classMetadata.getEntityMetamodel().getName();
			}
		}
		return returnEntityName;
	}

	/**
	 * 检查是否是实体类
	 * 
	 * @param entityName
	 * @param sessionFactorty
	 */
	public static void checkClassIsEntity(String entityName, SessionFactory sessionFactorty) {
		boolean flag = false;// true表示该类是一个实体类
		Map<String, ClassMetadata> metaMap = sessionFactorty.getAllClassMetadata();
		for (Object key : metaMap.keySet()) {
			AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap.get(key);
			String className = classMetadata.getEntityMetamodel().getName();
			if (className.equals(entityName)) {// 名称匹配
				flag = true;
			}
		}
		if (false == flag) {
			throw new NotEntityException(entityName + "并不是一个实体类");
		}
	}
}
