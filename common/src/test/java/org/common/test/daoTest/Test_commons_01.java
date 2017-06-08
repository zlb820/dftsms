package org.common.test.daoTest;

import javax.annotation.Resource;

import org.common.RBAC.domain.simple.AccountSimple;
import org.common.RBAC.service.AccountManager;
import org.common.test.util.AbstranctUnit;
import org.common.util.Result;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class Test_commons_01 extends AbstranctUnit {
	@Resource
	AccountManager accountManage;

	@Test
	@DatabaseSetup(type = DatabaseOperation.REFRESH, value = "classpath:DbUnit_xml/RBAC_testData.xml")
	public void testRegistByPhone() {
		// 手机号为null或长度为0 状态码为：000105
		assertEquals("000105", accountManage.registeByPhone("ceshi", "ceshi", "").getCode());
		assertEquals("000105", accountManage.registeByPhone("ceshi", "ceshi", null).getCode());
		// 账号为null或长度为0 状态码为：000105
		assertEquals("000105", accountManage.registeByPhone("", "ceshi", "ceshi").getCode());
		assertEquals("000105", accountManage.registeByPhone(null, "ceshi", "ceshi").getCode());
		// 密码为null或长度为0 状态码为：000105
		assertEquals("000105", accountManage.registeByPhone("ceshi", "", "ceshi").getCode());
		assertEquals("000105", accountManage.registeByPhone("ceshi", null, "ceshi").getCode());
		// 手机号已注册 状态码为：000107
		assertEquals("000107", accountManage.registeByPhone("ceshi", "ceshi", "18930207737").getCode());
		// 手机号格式不正确 状态码为：000106
		assertEquals("000106", accountManage.registeByPhone("ceshi", "ceshi", "189302077").getCode());
		// 账号已注册 状态码为：000101
		assertEquals("000101", accountManage.registeByPhone("范晓宾", "ceshi", "182302077").getCode());
		// 账号为手机号 状态码为：000102
		assertEquals("000102", accountManage.registeByPhone("18230207737", "ceshi", "182302077").getCode());
		// 账号为邮箱 状态码为：000103
		assertEquals("000103", accountManage.registeByPhone("577328725@qq.com", "ceshi", "182302077").getCode());
		// 注册信息正常 状态码为：000100
		Result<AccountSimple> result = accountManage.registeByPhone("ceshi_account", "ceshi_pass", "18230207787");
		assertEquals("000100", result.getCode());
		assertNotNull(result.getData().getId());
		assertEquals("ceshi_account", result.getData().getName());
		assertEquals("18230207787", result.getData().getPhone());
	}

	@Test
	@DatabaseSetup(type = DatabaseOperation.REFRESH, value = "classpath:DbUnit_xml/RBAC_testData.xml")
	public void testRegistByEmail() {
		long start=new Date().getTime();
		// 邮箱为null或长度为0 状态码为：000105
		assertEquals("000105", accountManage.registByEmail("ceshi", "ceshi", "", "ceshi", "ceshi").getCode());
		assertEquals("000105", accountManage.registByEmail("ceshi", "ceshi", null, "ceshi", "ceshi").getCode());
		// 账号为null或长度为0 状态码为：000105
		assertEquals("000105", accountManage.registByEmail("", "ceshi", "ceshi", "ceshi", "ceshi").getCode());
		assertEquals("000105", accountManage.registByEmail(null, "ceshi", "ceshi", "ceshi", "ceshi").getCode());
		// 密码为null或长度为0 状态码为：000105
		assertEquals("000105", accountManage.registByEmail("ceshi", "", "ceshi", "ceshi", "ceshi").getCode());
		assertEquals("000105", accountManage.registByEmail("ceshi", null, "ceshi", "ceshi", "ceshi").getCode());
		// 激活码为null或长度为0 状态码为：000105
		assertEquals("000105", accountManage.registByEmail("ceshi", "ceshi", "ceshi", "", "ceshi").getCode());
		assertEquals("000105", accountManage.registByEmail("ceshi", "ceshi", "ceshi", null, "ceshi").getCode());
		// 时间戳为null或长度为0 状态码为：000105
		assertEquals("000105", accountManage.registByEmail("ceshi", "ceshi", "ceshi", "ceshi", "").getCode());
		assertEquals("000105", accountManage.registByEmail("ceshi", "ceshi", "ceshi", "ceshi", null).getCode());
		// 邮箱已注册 状态码为：000108
		assertEquals("000108", accountManage.registByEmail("ceshi", "ceshi", "1475610721@qq.com", "ceshi", "ceshi").getCode());
		// 邮箱格式不正确 状态码为：000106
		assertEquals("000106", accountManage.registByEmail("ceshi", "ceshi", "1475610721.com", "ceshi", "ceshi").getCode());
		// 账号已注册 状态码为：000101
		assertEquals("000101", accountManage.registByEmail("fanxiaobin", "ceshi", "11475610721@qq.com", "ceshi", "ceshi").getCode());
		// 账号为手机号 状态码为：000102
		assertEquals("000102", accountManage.registByEmail("18230207798", "ceshi", "11475610721@qq.com", "ceshi", "ceshi").getCode());
		// 账号为邮箱 状态码为：000103
		assertEquals("000103", accountManage.registByEmail("577328725@qq.com", "ceshi", "11475610721@qq.com", "ceshi", "ceshi").getCode());
		// 注册信息正常 状态码为：000100
		Result<AccountSimple> result = accountManage.registByEmail("ceshi_account", "ceshi_pass", "11475610721@qq.com", "ceshi", "ceshi");
		assertEquals("000100", result.getCode());
		assertNotNull(result.getData().getId());
		assertEquals("ceshi_account", result.getData().getName());
		assertEquals("11475610721@qq.com", result.getData().getEmail());
		long end=new Date().getTime();
		System.out.println("单元测试执行时间为："+(end-start)+"毫秒");
	}
}
