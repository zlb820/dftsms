package org.common.test.daoTest;

import javax.annotation.Resource;

import org.common.RBAC.service.AccountManager;
import org.common.test.util.AbstranctUnit;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
@Ignore
public class Test_commons_00 extends AbstranctUnit {
	@Resource
	AccountManager accountManage;
	
	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testVerifyAccountName() {
		// 英文账号已注册
		assertEquals("000003", accountManage.verifyAccountNameIsExist("fanxiaobin").getCode());
		// 英文账号未被注册
		assertEquals("000004", accountManage.verifyAccountNameIsExist("ceshi").getCode());
		// 中文账号已注册
		assertEquals("000003", accountManage.verifyAccountNameIsExist("范晓宾").getCode());
		// 中文账号未被注册
		assertEquals("000004", accountManage.verifyAccountNameIsExist("测试").getCode());
		//账号为手机号
		assertEquals("000015", accountManage.verifyAccountNameIsExist("18230207738").getCode());
		//账号为邮箱
		assertEquals("000015", accountManage.verifyAccountNameIsExist("123456@qq.com").getCode());
		// 英文账号已注册
		assertTrue(accountManage.verifyAccountNameIsExist_Flag("fanxiaobin"));
		// 英文账号未被注册
		assertFalse(accountManage.verifyAccountNameIsExist_Flag("ceshi"));
		// 中文账号已注册
		assertTrue(accountManage.verifyAccountNameIsExist_Flag("范晓宾"));
		// 中文账号未被注册
		assertFalse(accountManage.verifyAccountNameIsExist_Flag("测试"));
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testVerifyAccountPhone() {
		// 手机号已注册
		assertEquals("000001", accountManage.verifyAccountPhoneIsExist("18930207737").getCode());
		// 手机号未注册
		assertEquals("000000", accountManage.verifyAccountPhoneIsExist("18830207737").getCode());
		// 手机号格式异常
		assertEquals("000002", accountManage.verifyAccountPhoneIsExist("18930207").getCode());
		// 手机号已注册
		assertTrue(accountManage.verifyAccountPhoneIsExist_Flag("18930207737"));
		// 手机号未注册
		assertFalse(accountManage.verifyAccountPhoneIsExist_Flag("18830207737"));
		// 手机号格式异常
		assertFalse(accountManage.verifyAccountPhoneIsExist_Flag("1893020"));
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testVerifyAccountEmailIs() {
		// 邮箱已被注册 
		assertEquals("000005", accountManage.verifyAccountEmailIsExist("577328725@qq.com").getCode());
		// 邮箱未被注册 
		assertEquals("000006", accountManage.verifyAccountEmailIsExist("577328724@qq.com").getCode());
		// 验证的邮箱格式异常 
		assertEquals("00007", accountManage.verifyAccountEmailIsExist("577328725@qq").getCode());
		// 邮箱已被注册
		assertTrue(accountManage.verifyAccountEmailIsExist_Flag("1475610721@qq.com"));
		// 邮箱未被注册 
		assertFalse(accountManage.verifyAccountEmailIsExist_Flag("1475610722@qq.com"));
		// 验证的邮箱格式异常 
		assertFalse(accountManage.verifyAccountEmailIsExist_Flag("1475610721@qq"));
	}
}
