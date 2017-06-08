package org.common.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取数据库配置信息
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
public class ReadPropertyOfDB {
	private static Properties prop = new Properties();

	/**
	 * 这是一个工具类，所以禁止随意生成实例对象
	 */
	private ReadPropertyOfDB() {
	}

	/**
	 * 
	 * @return 返回一个包含数据库配置信息的对象
	 */
	public static DataBaseInfo loadProperties() {
		DataBaseInfo dbInfo = new DataBaseInfo();
		try {
			InputStream in = ReadPropertyOfDB.class.getClassLoader().getResourceAsStream("database.properties");
			prop.load(in);
			dbInfo.setUrl(prop.getProperty("url"));
			dbInfo.setDriver(prop.getProperty("driver"));
			dbInfo.setAccount(prop.getProperty("account"));
			dbInfo.setPassword(prop.getProperty("password"));
			// 下面是带有默认参数，测试后使用，系统整体完成时使用。
			// dbInfo.setUrl(prop.getProperty("url",
			// "jdbc:mysql://localhost:3306/dftsms_test"));
			// dbInfo.setDriver(prop.getProperty("driver",
			// "com.mysql.jdbc.Driver"));
			// dbInfo.setAccount(prop.getProperty("account","root"));
			// dbInfo.setPassword(prop.getProperty("password","root"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dbInfo;
	}

}
