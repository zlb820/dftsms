package org.common.RBAC.dao;

import org.common.RBAC.dao.exception.AccountNotExistException;
import org.common.RBAC.dao.exception.EmailNotExistException;
import org.common.RBAC.dao.exception.NotExpectedException;
import org.common.RBAC.dao.exception.PasswordWrong;
import org.common.RBAC.dao.exception.PhonNotExistException;
import org.common.RBAC.domain.Account;
import org.common.RBAC.domain.Role;
import org.common.util.AccountStatus;
import org.common.util.EmailStatus;
import org.common.util.Sex;

public interface AccountDao extends BaseDao<Account> {
	/******************************** 1.0 通用部分 ********************************/

	/**
	 * 检查该用户名是否存在:账号存在返回该账号否则抛出异常
	 * 
	 * @param name
	 * @return
	 * @throws AccountNotExistException
	 */
	Account checkAccountIsExist(String name) throws AccountNotExistException;

	/**
	 * 检查该用户名是否存在：账号存在返回true
	 * 
	 * @param name
	 * @return
	 */
	Boolean checkAccountIsExistNoException(String name);

	/**
	 * 检查手机号是否已被注册：抛出异常
	 * 
	 * @param phone
	 * @return
	 * @throws PhonNotExistException
	 */
	Account checkPhonIsExist(String phone) throws PhonNotExistException;

	/**
	 * 检查手机号是否已被注册：手机号已被注册返回true
	 * 
	 * @param phone
	 * @return
	 */
	Boolean checkPhonIsExistNoException(String phone);

	/**
	 * 检查邮箱是否已被注册:抛出异常
	 * 
	 * @param eMail
	 * @return
	 * @throws EmailNotExistException
	 */
	Account checkEmailIsExist(String eMail) throws EmailNotExistException;

	/**
	 * 检查邮箱是否已被注册:邮箱已被注册返回true
	 * 
	 * @param eMail
	 * @return
	 */
	Boolean checkEmailIsExistNoException(String eMail);

	/******************************** 1.1 注册账户 ********************************/

	/**
	 * 通过手机号注册
	 * 
	 * @param name
	 * @param pass
	 * @param phone
	 * @return
	 * @throws NotExpectedException
	 */
	Account registByPhone(String name, String pass, String phone) throws NotExpectedException;

	/**
	 * 通过邮箱注册
	 * 
	 * @param name
	 * @param pass
	 * @param e_mail
	 * @param em_Acticode
	 * @param em_token_exptime
	 * @return
	 * @throws NotExpectedException
	 */
	Account registByEmail(String name, String pass, String e_mail, String em_Acticode, String em_token_exptime)
			throws NotExpectedException;

	/******************************** 1.2 注销账户 ********************************/

	/****************************** 1.3 修改账户信息 ******************************/
	
	/**
	 * 更新账号的用户名
	 * @param account
	 * @param name
	 */
	void updateAccount_Name(Account account,String name);

	/**
	 * 更新账号的密码(前提：获取到该账号的持久化对象)
	 * 
	 * @param account
	 * @param newPass
	 * @return
	 */
	void updateAccount_pass(Account account, String newPass);

	/**
	 * 更新账号的性别
	 * 
	 * @param account
	 * @param sex
	 * @return
	 */
	void updateAccount_sex(Account account, Sex sex);

	/**
	 * 更新账号的手机号
	 * 
	 * @param account
	 * @param newPhon
	 * @return
	 */
	void updateAccount_phon(Account account, String newPhon);

	/**
	 * 更新账号的邮箱地址
	 * 
	 * @param account
	 * @param newEmail
	 * @return
	 */
	void updateAccount_email(Account account, String newEmail);

	/**
	 * 更新账号的头像
	 * 
	 * @param account
	 * @param pic
	 * @return
	 */
	void updateAccount_pic(Account account, String picURL);

	/**
	 * 更新账号的状态
	 * 
	 * @param account
	 * @param accountStatus
	 * @return
	 */
	void updateAccount_status(Account account, AccountStatus accountStatus);

	/**
	 * 更新账号的角色
	 * 
	 * @param account
	 * @param role
	 * @return
	 */
	void updateAccount_role(Account account, Role role);

	/**
	 * 更新账号邮箱的状态
	 * 
	 * @param account
	 * @param emilStatus
	 */
	void updateAccount_emailStatus(Account account, EmailStatus emilStatus);

	/**
	 * 清除未激活邮箱的注册信息
	 * 
	 * @param account
	 */
	void clearEmailAciveMsg(Account account);

	/**
	 * 写入邮箱激活信息
	 * 
	 * @param account
	 * @param email
	 * @param aciveCode
	 * @param timeStamp
	 */
	void writeAccountEmailRegistMSG(Account account, String email, String aciveCode, String timeStamp);

	/****************************** 1.4 查询账户信息 ******************************/

	/******************************** 1.5 登录验证 ********************************/

	/**
	 * 根据用户名密码验证用户是否存在
	 * 
	 * @param name
	 * @param pass
	 * @return
	 * @throws AccountNotExistException
	 * @throws PasswordWrong
	 */
	Account verifyAccountByNameAndPass(String name, String pass) throws AccountNotExistException, PasswordWrong;

	/**
	 * 根据用户名密码验证用户是否存在
	 * 
	 * @param name
	 * @param pass
	 * @return
	 * @throws AccountNotExistException
	 * @throws PasswordWrong
	 */
	Account verifyAccountByPhoneAndPass(String phone, String pass) throws AccountNotExistException, PasswordWrong;

	/**
	 * 根据用户名密码验证用户是否存在
	 * 
	 * @param name
	 * @param pass
	 * @return
	 * @throws AccountNotExistException
	 * @throws PasswordWrong
	 */
	Account verifyAccountByEmailAndPass(String email, String pass) throws AccountNotExistException, PasswordWrong;

	/**
	 * 根据手机号登录，前提必须验证码已通过
	 * 
	 * @param phone
	 * @param flag
	 * @return
	 * @throws AccountNotExistException
	 */
	Account verifyAccountByPhoneAndIdentifyCode(String phone) throws AccountNotExistException;

}
