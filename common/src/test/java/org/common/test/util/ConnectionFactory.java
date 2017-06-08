package org.common.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库工具类主要是为了获取数据库连接，从而方便测试使用。<br>
 * 采用单例模式，使用示例：<br>
 * ConnectionFactory.INSTANCE.getConnectioin();
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Deprecated
public enum ConnectionFactory {
	INSTANCE;
	private Connection conn = null;
	private DataBaseInfo dbInfo = null;

	// 构造函数，加载数据库驱动
	private ConnectionFactory() {
		dbInfo = ReadPropertyOfDB.loadProperties();
		try {
			Class.forName(dbInfo.getDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回一个数据库链接。<br>
	 * 数据库链接每次返回前检查当前保存的链接是否可用,可用则直接返回,不可用在重新获取链接。
	 * 
	 * @return 返回一个数据库链接
	 */
	public Connection getConnectioin() {
		boolean flag = false;// true表示链接关闭或者链接为空
		try {
			if (null != conn) {
				flag = conn.isClosed();
				if (flag) {
					// 数据库连接已经关闭
					flag = true;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (null == conn || flag) {
			try {
				conn = DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getAccount(), dbInfo.getPassword());
				flag = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (null != conn && !flag) {
			Statement sta = null;
			try {// 设置编码为UTF-8,防止数据库中文乱码
				sta = conn.createStatement();
				String sql = "set names utf8;";
				if (sta.execute(sql)) {
					System.out.println("防止数据库中文乱码：设置失败");
				} else {
					System.out.println("防止数据库中文乱码：设置成功");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
