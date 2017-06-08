package org.common.RBAC.service;

import java.io.Serializable;

import org.common.RBAC.domain.simple.AccountSimple;
import org.common.util.AccountStatus;
import org.common.util.Result;
import org.common.util.ResultSimple;
import org.common.util.Sex;

public interface AccountManager {
	/******************************** 1.0 通用部分 ********************************/

	/**
	 * 验证账户名是否已经存在
	 * 
	 * @param name
	 * @return
	 */
	ResultSimple verifyAccountNameIsExist(String name);

	boolean verifyAccountNameIsExist_Flag(String name);

	/**
	 * 验证手机号是否已经被注册
	 * 
	 * @param phone
	 * @return
	 */
	ResultSimple verifyAccountPhoneIsExist(String phone);

	boolean verifyAccountPhoneIsExist_Flag(String phone);

	/**
	 * 验证邮箱是否已经被注册
	 * 
	 * @param email
	 * @return
	 */
	ResultSimple verifyAccountEmailIsExist(String email);

	boolean verifyAccountEmailIsExist_Flag(String email);

	/**
	 * 检测邮箱的激活状态
	 * 
	 * @param email
	 * @return
	 */
	Result<AccountSimple> verifyAccountEmailActiveStatus(String email);

	/******************************** 1.1 注册账户 ********************************/
	/**
	 * 通过手机号注册账户
	 * 
	 * @param name
	 * @param pass
	 * @param phone
	 * @return
	 */
	Result<AccountSimple> registeByPhone(String name, String pass, String phone);

	/**
	 * 通过邮箱注册账户
	 * 
	 * @param name
	 * @param pass
	 * @param e_mail
	 * @param em_Acticode
	 * @param em_token_exptime
	 * @return
	 */
	Result<AccountSimple> registByEmail(String name, String pass, String e_mail, String em_Acticode,
			String em_token_exptime);

	/******************************** 1.2 注销账户 ********************************/

	/**
	 * 根据ID删除用户
	 * 
	 * @param ID
	 * @return
	 */
	ResultSimple deleteAccountByID(Serializable ID);

	/**
	 * 根据用户名删除用户
	 * 
	 * @param name
	 * @return
	 */
	ResultSimple deleteAccountByName(String name);

	/****************************** 1.3 修改账户信息 ******************************/
	
	Result<AccountSimple> updateAccountPICByID(Serializable id,String picURL);
	/**
	 * 修改用户名
	 * @param id
	 * @param name
	 * @return
	 */
	Result<AccountSimple> updateAccountNameByID(Serializable id,String name);

	/**
	 * 通过ID修改密码
	 * 
	 * @param id
	 * @param newPass
	 * @return
	 */
	Result<AccountSimple> updatePassByID(Serializable id, String newPass);

	/**
	 * 通过ID及原密码修改密码
	 * 
	 * @param id
	 * @param newPass
	 * @param oldPassInput
	 * @return
	 */
	Result<AccountSimple> updatePassByID(Serializable id, String newPass, String oldPassInput);

	/**
	 * 通过ID修改性别
	 * 
	 * @param id
	 * @param sex
	 * @return
	 */
	Result<AccountSimple> updateSexByID(Serializable id, Sex sex);

	/**
	 * 通过ID修改手机号
	 * 
	 * @param id
	 * @param newPhoneNum
	 * @return
	 */
	Result<AccountSimple> updatePhonByID(Serializable id, String newPhoneNum);

	/**
	 * 通过ID修改角色
	 * 
	 * @param id
	 * @param RoleId
	 * @return
	 */
	Result<AccountSimple> updateRoleByID(Serializable id, Serializable RoleId);

	/**
	 * 通过ID修改账号状态
	 * 
	 * @param id
	 * @param accountStatus
	 * @return
	 */
	Result<AccountSimple> updateStatuByID(Serializable id, AccountStatus accountStatus);

	/**
	 * 通过ID修改头像图片
	 * 
	 * @param id
	 * @param PicURL
	 * @return
	 */
	Result<AccountSimple> updatePicByID(Serializable id, String PicURL);

	/**
	 * 通过ID修改邮箱地址
	 * 
	 * @param id
	 * @param eMail
	 * @return
	 */
	Result<AccountSimple> updateEmailByID(Serializable id, String eMail);

	/**
	 * 将需要修改的信息进行封装并统一修改</br>
	 * 第二个参数添加的原因是如果原来是个女的，性别采用int类型，默认值是0，如果不进行区分就会在无意间变成男</br>
	 * 注意：禁止修改属性无法修改，<b>密码,账号状态以及角色</b>也无法通过这种方式进行修改
	 * 
	 * @param account
	 * @param IfUpdateSex
	 *            true表示修改，此时会将account中指定的性别进行修改
	 * @return
	 */
	Result<AccountSimple> updateAccoount(AccountSimple account, Boolean IfUpdateSex);

	/**
	 * 通过ID修改邮箱的注册状态
	 * 
	 * @param id
	 * @param email
	 * @param activeCode
	 * @return
	 */
	ResultSimple updateAccountEmailStatus(Serializable id, String email, String activeCode);

	/**
	 * 通过ID清除邮箱的注册信息
	 * 
	 * @param id
	 * @return
	 */
	boolean updateClearEmailActiveMSG(Serializable id);

	/**
	 * 通过ID写入邮箱的注册信息
	 * 
	 * @param id
	 * @param email
	 * @param aciveCode
	 * @param timeStamp
	 * @return
	 */
	public ResultSimple updateWriteEmailActiveMSG(Serializable id, String email, String aciveCode, String timeStamp);

	/******************************** 1.5 登录验证 ********************************/

	/**
	 * 验证该账号及密码
	 * 
	 * @param name
	 * @param pass
	 * @return
	 */
	Result<AccountSimple> verifyAccount(String name, String pass);

	/**
	 * 根据手机号及验证码登录
	 * 
	 * 
	 */
	Result<AccountSimple> loginByPhoneAndIdentifyCode(String phone);
}
