package org.common.test.daoTest;

import javax.annotation.Resource;

import org.common.RBAC.domain.simple.AccountSimple;
import org.common.RBAC.service.AccountManager;
import org.common.test.util.AbstranctUnit;
import org.common.util.AccountStatus;
import org.common.util.ChangToJsonUtil;
import org.common.util.Result;
import org.common.util.Sex;
import org.junit.Ignore;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import static org.junit.Assert.*;
@Ignore
public class TestAccount extends AbstranctUnit {
	@Resource
	AccountManager accountManage;

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testVerifyAccount_coName_coPass() {
		// 英文账户名验证
		Result<AccountSimple> resultByEn_Name = accountManage.verifyAccount("fanxiaobin", "123");
		assertNotNull("账号密码验证失败!", resultByEn_Name);
		assertEquals("预期状态码不正确", "000500", resultByEn_Name.getCode());
		assertNotNull("验证通过,但返回数据为空", resultByEn_Name.getData());
		// 中文账户名验证
		Result<AccountSimple> resultByCh_Name = accountManage.verifyAccount("范晓宾", "123");
		assertNotNull("通过中文名来账号密码验证失败!", resultByCh_Name);
		assertEquals("预期状态码不正确", "000500", resultByCh_Name.getCode());
		assertNotNull("验证通过,但返回数据为空", resultByCh_Name.getData());
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testVerifyAccount_coName_wrpass() {
		// 密码错误验证
		Result<AccountSimple> result = accountManage.verifyAccount("fanxiaobin", "cuowu");
		assertNotNull(result);
		assertEquals("预期状态码不正确", "000502", result.getCode());
		// 密码为Null验证
		Result<AccountSimple> result_null = accountManage.verifyAccount("fanxiaobin", null);
		assertNotNull(result_null);
		assertEquals("预期状态码不正确", "000502", result_null.getCode());
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testVerifyAccount_wrName() {
		// TODO 测试超时,但是自己编码测试时间未超时
		// 账户不存在验证
		long startTime = System.currentTimeMillis(); // 获取开始时间
		Result<AccountSimple> result = accountManage.verifyAccount("cuowu", "cuowu");
		assertNotNull(result);
		assertEquals("预期状态码不正确", "000501", result.getCode());
		// 账户为Null验证
		Result<AccountSimple> result_ALL_null = accountManage.verifyAccount(null, null);
		assertNotNull(result_ALL_null);
		assertEquals("预期状态码不正确", "000501", result_ALL_null.getCode());
		Result<AccountSimple> result_Name_null = accountManage.verifyAccount(null, "123");
		assertNotNull(result_Name_null);
		assertEquals("预期状态码不正确", "000501", result_Name_null.getCode());
		long endTime = System.currentTimeMillis(); // 获取开始时间
		System.out.println("总秒数:" + (endTime - startTime));
	}

	@Test
	public void testRegiste_LackOfNecessaryElements() {
		// 账号为null
//		Result<AccountSimple> result_nameNull = accountManage.registeByPhone(null, "25", "12", Sex.女, "", "");
//		assertEquals("预期状态码不正确", "000105", result_nameNull.getCode());
//		// 密码为null
//		Result<AccountSimple> result_passNull = accountManage.registeByPhone("25", null, "12", Sex.女, "", "");
//		assertEquals("预期状态码不正确", "000105", result_passNull.getCode());
//		// 手机号为null
//		Result<AccountSimple> result_phoneNull = accountManage.registeByPhone("25", "12", null, Sex.女, "", "");
//		assertEquals("预期状态码不正确", "000105", result_phoneNull.getCode());
//		// 缺少所有的必要信息
//		Result<AccountSimple> result_LackOfAll = accountManage.registeByPhone("", "", "", Sex.女, "", "");
//		assertEquals("预期状态码不正确", "000105", result_LackOfAll.getCode());
//		// 缺少账户名
//		Result<AccountSimple> result_LackOfName = accountManage.registeByPhone("", "123", "18230207737", Sex.女, "", "");
//		assertEquals("预期状态码不正确", "000105", result_LackOfName.getCode());
//		// 缺少密码
//		Result<AccountSimple> result_LackOfPass = accountManage.registeByPhone("woaini", "", "18230207737", Sex.女, "", "");
//		assertEquals("预期状态码不正确", "000105", result_LackOfPass.getCode());
//		// 缺少手机号
//		Result<AccountSimple> result_LackOfPhon = accountManage.registeByPhone("ceshi", "123", "", Sex.女, "", "");
//		assertEquals("预期状态码不正确", "000105", result_LackOfPhon.getCode());
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testRegiste_AlreadyExist() {
//		// 账号已存在
//		Result<AccountSimple> result_AlreadyExist_en = accountManage.registeByPhone("fanxiaobin", "123", "18230207737", Sex.女,
//				"", "");
//		assertEquals("预期状态码不正确", "000101", result_AlreadyExist_en.getCode());
//		Result<AccountSimple> result_AlreadyExist_zh = accountManage.registeByPhone("范晓宾", "123", "18230207737", Sex.女, "",
//				"");
//		assertEquals("预期状态码不正确", "000101", result_AlreadyExist_zh.getCode());
//		// 手机号已被注册
//		Result<AccountSimple> result_PhoneIsAlreadyExist = accountManage.registeByPhone("phoneExist", "123", "18930207737",
//				Sex.女, "", "");
//		assertEquals("预期状态码不正确", "000107", result_PhoneIsAlreadyExist.getCode());
//		// 邮箱已被注册
//		Result<AccountSimple> result_EmailIsAlreadyExist = accountManage.registeByPhone("phoneExist", "123", "18930205737",
//				Sex.女, "577328725@qq.com", "");
//		assertEquals("预期状态码不正确", "000108", result_EmailIsAlreadyExist.getCode());
	}

	@Test
	public void testRegiste_wrPhon_wrEmail() {
		// 注册时手机号格式异常
//		Result<AccountSimple> result_wrPhon = accountManage.registeByPhone("fanxiaobin", "123", "152", Sex.女, "", "");
//		assertEquals("预期状态码不正确", "000106", result_wrPhon.getCode());
//		// 注册时邮箱格式异常
//		Result<AccountSimple> result_wrEmail = accountManage.registeByPhone("fanxiaobin", "123", "18230207737", Sex.女, "12543",
//				"");
//		assertEquals("预期状态码不正确", "000106", result_wrEmail.getCode());
	}

	@Test
	public void testRegist_coAllMessage() {
		// 注册时缺少邮箱地址和头像
//		Result<AccountSimple> result_NoEmailAndPic = accountManage.registeByPhone("fanxiaobin_1", "123", "18230801152", Sex.女,
//				"", "");
//		assertEquals("预期状态码不正确", "000100", result_NoEmailAndPic.getCode());
//		assertNotNull("注册成功,但返回数据为空", result_NoEmailAndPic.getData());
//		assertEquals("000500", accountManage.verifyAccount("fanxiaobin_1", "123").getCode());
//		// 注册时缺少邮箱地址
//		Result<AccountSimple> result_NoEmail = accountManage.registeByPhone("fanxiaobin_2", "123", "18230701152", Sex.女, "",
//				"URL_test");
//		assertEquals("预期状态码不正确", "000100", result_NoEmail.getCode());
//		assertNotNull("注册成功,但返回数据为空", result_NoEmail.getData());
//		assertEquals("000500", accountManage.verifyAccount("fanxiaobin_2", "123").getCode());
//		// 注册时缺少头像
//		Result<AccountSimple> result_NoPic = accountManage.registeByPhone("fanxiaobin_3", "123", "18230601152", Sex.女,
//				"577328725@qq.com", "");
//		assertEquals("预期状态码不正确", "000100", result_NoPic.getCode());
//		assertNotNull("注册成功,但返回数据为空", result_NoPic.getData());
//		assertEquals("000500", accountManage.verifyAccount("fanxiaobin_3", "123").getCode());
//		// 注册信息都不为空
//		Result<AccountSimple> result = accountManage.registeByPhone("fanxiaobin", "123", "18230501152", Sex.女,
//				"577328769@qq.com", "URL_test");
//		assertEquals("预期状态码不正确", "000100", result.getCode());
//		assertNotNull("注册成功,但返回数据为空", result.getData());
//		assertEquals("000500", accountManage.verifyAccount("fanxiaobin", "123").getCode());
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testUpdateByID_pass() {
		/*
		 * // 账号不存在测试 assertEquals("预期状态码不正确", "000300",
		 * accountManage.updatePassByID("000", "123456").getCode()); // 修改为无密码状态
		 * assertEquals("预期状态码不正确", "000302",
		 * accountManage.updatePassByID("002", "").getCode()); // 密码为null
		 * assertEquals("预期状态码不正确", "000302",
		 * accountManage.updatePassByID("002", null).getCode()); // 账号存在测试
		 * Result<AccountSimple> result = accountManage.updatePassByID("001",
		 * "ceshimima"); assertEquals("预期状态码不正确", "000301", result.getCode());
		 * assertNotNull("密码修改成功,但返回数据为空", result.data);
		 */
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testUpdateById_sex() {
		// 账号不存在测试
		assertEquals("预期状态码不正确", "000300", accountManage.updateSexByID("000", Sex.女).getCode());
		// 修改为男
		Result<AccountSimple> resultChangToMan = accountManage.updateSexByID("001", Sex.男);
		assertEquals("预期状态码不正确", "000304", resultChangToMan.getCode());
		assertNotNull("性别修改成功,但返回数据为空", resultChangToMan.getData());
		// 修改为女
		Result<AccountSimple> resultChangToWomen = accountManage.updateSexByID("001", Sex.女);
		assertEquals("预期状态码不正确", "000304", resultChangToWomen.getCode());
		assertNotNull("性别修改成功,但返回数据为空", resultChangToWomen.getData());
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testUpdateById_phon() {
		// 账号不存在测试
		assertEquals("预期状态码不正确", "000300", accountManage.updatePhonByID("000", "123").getCode());
		// 手机号格式不对
		assertEquals("预期状态码不正确", "000310", accountManage.updatePhonByID("001", "123").getCode());
		assertEquals("预期状态码不正确", "000310", accountManage.updatePhonByID("001", "").getCode());
		assertEquals("预期状态码不正确", "000310", accountManage.updatePhonByID("001", null).getCode());
		// 手机号已被注册
		assertEquals("预期状态码不正确", "000107", accountManage.updatePhonByID("001", "18930207737").getCode());
		// 手机号格式正确
		Result<AccountSimple> result = accountManage.updatePhonByID("001", "18930297737");
		assertEquals("预期状态码不正确", "000307", result.getCode());
		assertNotNull("手机号修改成功,但返回数据为空", result.getData());
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testUpdateByID_status() {
		// 账号不存在测试
		assertEquals("预期状态码不正确", "000300", accountManage.updateStatuByID("000", AccountStatus.关闭).getCode());
		// 账号修改为激活
		assertEquals("预期状态码不正确", "000311", accountManage.updateStatuByID("001", AccountStatus.关闭).getCode());
		// 账号修改为关闭
		Result<AccountSimple> resultShutDown = accountManage.updateStatuByID("001", AccountStatus.关闭);
		assertEquals("预期状态码不正确", "000311", resultShutDown.getCode());
		assertNotNull("账号状态修改成功,但返回数据为空", resultShutDown.getData());
		// 账号修改为激活
		Result<AccountSimple> resultActive = accountManage.updateStatuByID("001", AccountStatus.关闭);
		assertEquals("预期状态码不正确", "000311", resultActive.getCode());
		assertNotNull("账号状态修改成功,但返回数据为空", resultActive.getData());
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testUpdateByID_email() {
		// 账号不存在测试
		assertEquals("预期状态码不正确", "000300", accountManage.updateEmailByID("000", "ceshi").getCode());
		// 邮箱格式不正确
		assertEquals("预期状态码不正确", "000323", accountManage.updateEmailByID("001", "").getCode());
		assertEquals("预期状态码不正确", "000323", accountManage.updateEmailByID("001", null).getCode());
		assertEquals("预期状态码不正确", "000323", accountManage.updateEmailByID("001", "2542").getCode());
		// 邮箱已被注册
		assertEquals("预期状态码不正确", "000108", accountManage.updateEmailByID("001", "1475610721@qq.com").getCode());
		// 邮箱格式正确
		Result<AccountSimple> result = accountManage.updateEmailByID("001", "1478810721@qq.com");
		assertEquals("预期状态码不正确", "000314", result.getCode());
		System.out.println(ChangToJsonUtil.toJson(result));
		assertNotNull("邮箱修改成功,但返回数据为空", result.getData());
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testUpdateAccount() {
		// 记录原有数据
		AccountSimple account = accountManage.verifyAccount("fanxiaobin", "123").getData();
		String oldID = account.getId();
		String oldName = account.getName();
		String oldphon = account.getPhone();
		String email = account.getEmail();
		// String PicURL = account.getPicURL();
		// 创建修改账户
		AccountSimple accountUpdate = new AccountSimple();
		// ID错误，账户错误
		accountUpdate.setId("dgd");
		accountUpdate.setName("ddd");
		assertEquals("预期状态码不正确", "000321", accountManage.updateAccoount(accountUpdate, false).getCode());
		// ID正确，账户错误
		accountUpdate.setId(oldID);
		accountUpdate.setName("dddd");
		assertEquals("预期状态码不正确", "000318", accountManage.updateAccoount(accountUpdate, false).getCode());
		// ID错误，账户正确
		accountUpdate.setId("12356");
		accountUpdate.setName(oldName);
		assertEquals("预期状态码不正确", "000318", accountManage.updateAccoount(accountUpdate, false).getCode());
		// 设置正确ID和账户
		accountUpdate.setId(oldID);
		accountUpdate.setName(oldName);
		// 更新性别
		accountUpdate.setSex(Sex.男.ordinal());
		Result<AccountSimple> result_updateSexToMan = accountManage.updateAccoount(accountUpdate, true);
		assertEquals("预期状态码不正确", "000322", result_updateSexToMan.getCode());
		assertEquals("性别未修改", Sex.男.ordinal(), result_updateSexToMan.getData().getSex());
		assertEquals("不需要修改的信息被修改", "18930207737", result_updateSexToMan.getData().getPhone());
		accountUpdate.setSex(Sex.女.ordinal());
		Result<AccountSimple> result_updateSexToWomen = accountManage.updateAccoount(accountUpdate, true);
		assertEquals("预期状态码不正确", "000322", result_updateSexToWomen.getCode());
		assertEquals("性别未修改", Sex.女.ordinal(), result_updateSexToWomen.getData().getSex());
		assertEquals("不需要修改的信息被修改", "18930207737", result_updateSexToWomen.getData().getPhone());

		// 更新邮箱
		accountUpdate.setEmail("ceshiyouxiang@qq.com");
		Result<AccountSimple> result_updateEmail = accountManage.updateAccoount(accountUpdate, false);
		assertEquals("预期状态码不正确", "000322", result_updateEmail.getCode());
		assertNotEquals("邮箱未异常", email, result_updateEmail.getData().getEmail());
		assertEquals("邮箱修改异常", "ceshiyouxiang@qq.com", result_updateEmail.getData().getEmail());
		assertEquals("性别被修改", Sex.女.ordinal(), result_updateEmail.getData().getSex());
		assertEquals("不需要修改的信息被修改", "18930207737", result_updateEmail.getData().getPhone());

		// 更新手机号
		accountUpdate.setEmail(null);
		accountUpdate.setPhone("18263532478");
		Result<AccountSimple> result_updatePhon = accountManage.updateAccoount(accountUpdate, false);
		assertEquals("预期状态码不正确", "000322", result_updatePhon.getCode());
		assertNotEquals("手机号未修改", oldphon, result_updatePhon.getData().getPhone());
		assertEquals("手机号修改异常", "18263532478", result_updatePhon.getData().getPhone());
		assertEquals("不需要修改的信息被修改", "ceshiyouxiang@qq.com", result_updatePhon.getData().getEmail());

		// TODO 编写修改其它部分的测试代码
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testDeletAccountByName() {
		// 用户名不存在
		assertEquals("预期状态码不正确", "000201", accountManage.deleteAccountByName("ceshi").getCode());
		// 用户名存在
		assertEquals("预期状态码不正确", "000200", accountManage.deleteAccountByName("fanxiaobin").getCode());
		assertEquals("预期状态码不正确", "000501", accountManage.verifyAccount("fanxiaobin", "123").getCode());
	}

	@Test
	@DatabaseSetup("classpath:DbUnit_xml/RBAC_testData.xml")
	public void testDeletAccountByID() {
		// ID不存在
		assertEquals("预期状态码不正确", "000201", accountManage.deleteAccountByID("ceshi").getCode());
		// ID存在
		assertEquals("预期状态码不正确", "000200", accountManage.deleteAccountByID("001").getCode());
		assertEquals("预期状态码不正确", "000501", accountManage.verifyAccount("fanxiaobin", "123").getCode());
	}
}
