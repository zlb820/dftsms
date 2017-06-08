package org.common.test.daoTest;

import javax.annotation.Resource;

import org.common.FDBK.service.InfoManage;
import org.common.test.util.AbstranctUnit;
import org.junit.Ignore;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
@Ignore
public class TestInfo extends AbstranctUnit {
	@Resource
	InfoManage infoManage;

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/FDBK_testData.xml")
	public void test() {
		// infoManage.getStoreByArea_SortByDistance();
	}
}
