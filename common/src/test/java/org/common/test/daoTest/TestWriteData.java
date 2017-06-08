package org.common.test.daoTest;

import javax.annotation.Resource;

import org.common.test.util.AbstranctUnit;
import org.common.test.util.DBUnit_Util;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("deprecation")
@Ignore
public class TestWriteData extends AbstranctUnit{
	@Resource
	 public DBUnit_Util dbUtil;
	/**
	 * 该测试方法的目的是将ALL_testData.xml数据文件中的数据永久性的写入到数据库中。
	 */
	@Test
	public void testWriteALL(){
		dbUtil.WriteInfoOfTestDataIntoDB("ALL_testData");
	}
}
