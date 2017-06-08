package org.common.test.util;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;

/**
 * 将DBUnit操作数据库的过程进行封装，这样测试类只需要继承该类就可以完成备份和还原数据库的操作。<br/>
 * 继承该类后只需要在测试类中使用该抽象类的dbUtil属性来写入数据表
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:daoContext.xml", "classpath:beans.xml" })
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public abstract class AbstranctUnit {
	// @Resource
	// public DBUnit_Util dbUtil;

	// @Before
	// public void setUp() {
	// }
	//
	// @After
	// public void tearDown() {
	// }
}
