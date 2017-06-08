
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工厂,采用单例模式。<br>
 * 使用示例：<br>
 * ConnectionFactory.INSTANCE.getConnectioin();
 * 
 * @author fxb
 *
 */
public class ConnectionFactory {
	private Connection conn = null;

	public ConnectionFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回一个数据库链接。<br>
	 * 数据库链接每次返回前检查当前保存的链接是否可用,可用则直接返回,不可用在重新获取链接。
	 * @return 返回一个数据库链接
	 */
	public Connection getConnectioin() {
		boolean flag = false;// true表示链接关闭或者链接为空
		try {
			if (null != conn) {
				flag = conn.isClosed();
				if (flag) {
					flag = true;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (null == conn || flag) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ceshi2?useUnicode=true&characterEncoding=UTF-8",
						"root", "root");
				flag = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
