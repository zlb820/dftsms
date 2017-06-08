
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TableChange {

	/**
	 * 将tb_da_area表中的省份提取并插入到province表
	 */
	@Test
	public void testFist_ExportProvince() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		try {
			String sql = "select * from tb_da_area where parentid=0";
			ResultSet res = find(sql);
			int count = 0;
			while (res.next()) {
				count++;
				Connection connSeachAllPro = new ConnectionFactory().getConnectioin();
				String proCode = res.getInt("codeid") + "0000";
				String proName = res.getString("cityName");
				String sql2 = "insert into province(Pcode,Pname) values(?,?)";
				PreparedStatement pre2 = connSeachAllPro.prepareStatement(sql2);
				pre2.setString(1, proCode);
				pre2.setString(2, proName);
				pre2.execute();
				connSeachAllPro.close();
			}
			assertEquals("省份数量不对", 34, count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("testFist_ExportProvince方法执行时间为：" + (endTime - startTime) + "毫秒");
	}

	/**
	 * 将tb_da_area表中的市提取并插入到city表
	 */
	@Test
	public void testSecond_ExportCity() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		try {
			// 查询出说有省份，拿到省份的code
			String sql = "select * from tb_da_area where parentid=0";
			ResultSet res = find(sql);
			int count = 0;
			while (res.next()) {
				String proCode = res.getString("codeid");
				// 查询出所有父ID为该省的市
				String sql2 = "select * from tb_da_area where parentid=?";
				ResultSet res2 = find(sql2, proCode);
				Connection conn2 = new ConnectionFactory().getConnectioin();
				while (res2.next()) {
					count++;
					// 保存该省份下的所有市
					String cityCode = res2.getInt("codeid") + "00";
					String name = res2.getString("cityName");
					String sql3 = "insert into city(Ccode,Cname,ProvinceCode) values(?,?,?)";
					PreparedStatement pre2 = conn2.prepareStatement(sql3);
					pre2.setString(1, cityCode);
					pre2.setString(2, name);
					pre2.setString(3, proCode + "0000");
					pre2.execute();
				}
				conn2.close();
			}
			assertEquals("市的数量不对", 348, count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("testSecond_ExportCity方法执行时间为：" + (endTime - startTime) + "毫秒");
	}

	/**
	 * 将tb_da_area表中的县提取并插入到areacounty表<br/>
	 * 按照顺序流程，查询出所有省，然后根据每个省code遍历父code是该省的市，然后在检索到的每个市的情形下在检索每个父code是该市的县，然后在遍历以便实现插入操作，总体看来需要嵌套总共3个while循环
	 * 同样也可以采用将省和市删除的形式，这样剩下的都是县了。下面采用删除的方式进行的。
	 */
	@Test
	public void testThird_ExportAreacounty() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		try {
			// 查询出说有省份，拿到省份的code
			String sql = "select * from tb_da_area where parentid=0";
			ResultSet res = find(sql);
			Connection connDel = new ConnectionFactory().getConnectioin();
			while (res.next()) {
				String proCode = res.getString("codeid");
				// 删除所有市
				String sqldeleteAllCity = "delete from tb_da_area where parentid=?";
				PreparedStatement preDelAllcity = connDel.prepareStatement(sqldeleteAllCity);
				preDelAllcity.setString(1, proCode);
				preDelAllcity.execute();
			}
			// 删除所有省
			String sqldeleteAllPro = "delete from tb_da_area where parentid=0";
			PreparedStatement preDelAllPro = connDel.prepareStatement(sqldeleteAllPro);
			preDelAllPro.execute();
			connDel.close();
			// 查询出所有县并进行插入操作
			String sqlAllArea = "select * from tb_da_area";
			ResultSet resAllArea = find(sqlAllArea);
			int count = 0;
			Connection connInsert = new ConnectionFactory().getConnectioin();
			while (resAllArea.next()) {
				count++;
				String code = resAllArea.getString("codeid");
				String parentCode = resAllArea.getString("parentid") + "00";
				String name = resAllArea.getString("cityName");
				String sqlInsert = "insert into areacounty(Acode,Aname,CityCode) values(?,?,?);";
				PreparedStatement preInsert = connInsert.prepareStatement(sqlInsert);
				preInsert.setString(1, code);
				preInsert.setString(2, name);
				preInsert.setString(3, parentCode);
				preInsert.execute();
			}
			connInsert.close();
			assertEquals("获取的县的数量不正确", 3140, count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("testThird_ExportAreacounty方法执行时间为：" + (endTime - startTime) + "毫秒");
	}

	/**
	 * 执行查询操作
	 * 
	 * @param sql
	 * @return
	 */
	private ResultSet find(String sql) {
		ResultSet res = null;
		try {
			Connection conn = new ConnectionFactory().getConnectioin();

			PreparedStatement pre = conn.prepareStatement(sql);
			res = pre.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 执行带参数的查询
	 * 
	 * @param sql
	 * @param arg
	 * @return
	 */
	private ResultSet find(String sql, String arg) {
		ResultSet res = null;
		try {
			Connection conn = new ConnectionFactory().getConnectioin();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, arg);
			res = pre.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
