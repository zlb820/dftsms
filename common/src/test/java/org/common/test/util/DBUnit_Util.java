package org.common.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.xml.sax.InputSource;

/**
 * 该工具类，可以方便的备份、写入和还原数据库表。<br/>
 * 备份方法包含：backupAllTable、backUPTables、backUpOneTable<br/>
 * 写入方法包含：WriteInfoOfTestDataIntoDB<br/>
 * 还原方法包含：resume<br/>
 * 清理方法包含：destroy<br/>
 * 正确的使用流程：<br/>
 * 备份 -> 写入数据 -> 测试 -> 还原数据库 -> 清理<br/>
 * 注：其中只有测试这一环节是非固定的，其它都应该按照这个顺序进行使用。
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Deprecated
public class DBUnit_Util {
	Connection conn;
	IDatabaseConnection dbunitConn;

	private File backUPTempFile;

	public DBUnit_Util() throws DatabaseUnitException {
		conn = ConnectionFactory.INSTANCE.getConnectioin();
		dbunitConn = new DatabaseConnection(conn);
	}

	/**
	 * 根据数据文件生成对应的IDataSet<br/>
	 * 约束：数据文件都放在固定的包中,其路径为DbUnit_xml包下
	 * 
	 * @param tableNameNOtInclude_xml
	 *            不包含后缀的数据文件的名称。
	 * @return
	 * @throws DataSetException
	 */
	protected IDataSet createDateSet(String tableNameNOtInclude_xml) throws DataSetException {
		InputStream streamOfIn = DBUnit_Util.class.getClassLoader()
				.getResourceAsStream("DbUnit_xml/" + tableNameNOtInclude_xml + ".xml");
		IDataSet dataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(streamOfIn)));
		return dataSet;
	}

	/**
	 * 备份数据库中的所有文件
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws DataSetException
	 */
	public void backupAllTable() throws SQLException, IOException, DataSetException {
		// 根据con创建相应的dataset,这个dataset包含了所有的表
		IDataSet ds = dbunitConn.createDataSet();
		writeBackupFile(ds);
	}

	/**
	 * 将备份的表写入文件中
	 * 
	 * @param ds
	 * @throws IOException
	 * @throws DataSetException
	 */
	private void writeBackupFile(IDataSet ds) throws IOException, DataSetException {
		// 将ds中的数据通过FlatXmlDataSet的格式写到文件中
		closeForeignKeyConstraint();
		backUPTempFile = File.createTempFile("backUpOfTables", "xml");
		FlatXmlDataSet.write(ds, new FileWriter(backUPTempFile), "UTF-8");
		// openForeignKeyConstraint();
	}

	/**
	 * 备份多张表
	 * 
	 * @param tableNames
	 * @throws DataSetException
	 * @throws IOException
	 */
	public void backUPTables(String[] tableNames) throws DataSetException, IOException {
		QueryDataSet ds = new QueryDataSet(dbunitConn);
		for (String string : tableNames) {
			ds.addTable(string);
		}
		writeBackupFile(ds);
	}

	/**
	 * 备份一张表
	 * 
	 * @param tableName
	 * @throws DataSetException
	 * @throws IOException
	 */
	public void backUpOneTable(String tableName) throws DataSetException, IOException {
		backUPTables(new String[] { tableName });
	}

	/**
	 * 还原数据文件
	 * 
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws DatabaseUnitException
	 */
	public void resume() throws SQLException, FileNotFoundException, DatabaseUnitException {
		// 禁用外键约束
		closeForeignKeyConstraint();
		conn.setAutoCommit(true);
		// 根据备份文件创建dataset
		IDataSet ds = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(new FileInputStream(backUPTempFile))));
		// 将数据库中的数据清空,并将数据插入到数据库中（还原）
		System.out.println("x:-------------------------");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitConn, ds);
		System.out.println("4:-------------------------");
		openForeignKeyConstraint();
	}

	/**
	 * 将测试数据写入到数据库中
	 * 
	 * @param tableNameNOtInclude_xml
	 */
	public void WriteInfoOfTestDataIntoDB(String tableNameNOtInclude_xml) {
		try {
			IDataSet dataSet = createDateSet(tableNameNOtInclude_xml);
			DatabaseOperation.CLEAN_INSERT.execute(dbunitConn, dataSet);
		} catch (DatabaseUnitException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清理工作：关闭数据库的链接
	 */
	public void destroy() {
		try {
			if (dbunitConn != null) {
				dbunitConn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打开外键约束
	 * 
	 */
	protected void openForeignKeyConstraint() {
		try {
			conn.prepareStatement("set @@session.foreign_key_checks = 1").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 禁用外键约束
	 */
	protected void closeForeignKeyConstraint() {
		try {
			conn.prepareStatement("set @@session.foreign_key_checks = 0").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
