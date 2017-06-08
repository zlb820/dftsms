package org.common.RBAC.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.common.RBAC.dao.AccountDao;
import org.common.RBAC.dao.exception.AccountNotExistException;
import org.common.RBAC.dao.exception.EmailNotExistException;
import org.common.RBAC.dao.exception.NotExpectedException;
import org.common.RBAC.dao.exception.PasswordWrong;
import org.common.RBAC.domain.Account;
import org.common.RBAC.domain.simple.AccountSimple;
import org.common.RBAC.service.AccountManager;
import org.common.util.AccountStatus;
import org.common.util.EmailStatus;
import org.common.util.PO_to_Simple;
import org.common.util.Result;
import org.common.util.ResultSimple;
import org.common.util.ResultUtil;
import org.common.util.Sex;

public class AccountManagerImpl implements AccountManager {
	// 注入AccountDAO实例
	AccountDao accountDao;
	// 注入ResultUtil实例
	ResultUtil<AccountSimple> resultUtil;

	public ResultUtil<AccountSimple> getResultUtil() {
		return resultUtil;
	}

	public void setResultUtil(ResultUtil<AccountSimple> resultUtil) {
		this.resultUtil = resultUtil;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	/******************************** 工具方法 ********************************/

	/**
	 * 检验手机号是否符合规则
	 * 
	 * @param phoneNum
	 * @return
	 */
	private Boolean verifyPhone(String phoneNum) {
		Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		Matcher m = p.matcher(phoneNum);
		return m.matches();
	}

	/**
	 * 检验邮箱地址是否符合规则
	 * 
	 * @param email
	 * @return
	 */
	private Boolean verifyEmail(String email) {
		if (0 == email.length()) {
			return false;
		}
		String check = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}

	/******************************** 1.0 通用部分 ********************************/

	@Override
	public ResultSimple verifyAccountNameIsExist(String name) {
		// 检查账号是否是手机号或邮箱
		if (verifyPhone(name) || verifyEmail(name)) {
			return ResultUtil.saveResult("000015", "账号为手机号或邮箱!");
		}
		// 检查账号是否存在
		boolean flag = accountDao.checkAccountIsExistNoException(name);
		if (flag) {
			return ResultUtil.saveResult("000003", "账号已被注册!");
		} else {
			return ResultUtil.saveResult("000004", "账号未被注册!");
		}
	}

	@Override
	public boolean verifyAccountNameIsExist_Flag(String name) {
		return accountDao.checkAccountIsExistNoException(name);
	}

	@Override
	public ResultSimple verifyAccountPhoneIsExist(String phone) {
		// 检查手机号是否已被注册
		if (!verifyPhone(phone)) {
			return ResultUtil.saveResult("000002", "验证的手机号格式异常!");
		}

		boolean flag = accountDao.checkPhonIsExistNoException(phone);
		if (flag) {
			return ResultUtil.saveResult("000001", "手机号已被注册!");
		} else {
			return ResultUtil.saveResult("000000", "手机号未被注册!");
		}
	}

	@Override
	public boolean verifyAccountPhoneIsExist_Flag(String phone) {
		return accountDao.checkPhonIsExistNoException(phone);
	}

	@Override
	public ResultSimple verifyAccountEmailIsExist(String email) {
		// 检查邮箱是否已被注册
		if (!verifyEmail(email)) {
			return ResultUtil.saveResult("00007", "验证的邮箱格式异常!");
		}
		boolean flag = accountDao.checkEmailIsExistNoException(email);
		if (flag) {
			return ResultUtil.saveResult("000005", "邮箱已被注册!");
		} else {
			return ResultUtil.saveResult("000006", "邮箱未被注册!");
		}
	}

	@Override
	public boolean verifyAccountEmailIsExist_Flag(String email) {
		return accountDao.checkEmailIsExistNoException(email);
	}

	@Override
	public Result<AccountSimple> verifyAccountEmailActiveStatus(String email) {
		Result<AccountSimple> result = new Result<>();
		try {
			Account account = accountDao.checkEmailIsExist(email);
			if (1 == account.getEm_status()) {
				result.setCode("000011");
				result.setMessage("邮箱已被激活");
			} else {
				result.setCode("000010");
				result.setMessage("邮箱未被激活");
				result.setData(PO_to_Simple.AccountToSimple(account));
			}
		} catch (EmailNotExistException e) {
			result.setCode("000006");
			result.setMessage("邮箱未被注册");
		}

		return result;
	}

	/******************************** 1.1 注册账户 ********************************/

	@Override
	public Result<AccountSimple> registeByPhone(String name, String pass, String phone) {
		// 必要信息检查（账号名和密码是必须包含的）
		if (null == pass || null == name || null == phone || 0 == name.length() || 0 == pass.length()
				|| 0 == phone.length()) {
			return resultUtil.saveResult("000105", "缺少必要信息，注册失败!", null);
		}
		// 检查账户名称是否为邮箱地址
		if (verifyEmail(name)) {
			return resultUtil.saveResult("000103", "账户名为邮箱，注册失败!", null);
		}
		// 检查账户名称是否为手机号
		if (verifyPhone(name)) {
			return resultUtil.saveResult("000102", "账户名为手机号，注册失败!", null);
		}
		// 检查账号是否存在
		if (accountDao.checkAccountIsExistNoException(name)) {
			return resultUtil.saveResult("000101", "账户已存在，注册失败!", null);
		}

		// 检查手机号是否符合规则
		if (!verifyPhone(phone)) {
			return resultUtil.saveResult("000106", "信息不符合格式，注册失败!", null);
		}
		// 检查手机号是否已被注册
		if (accountDao.checkPhonIsExistNoException(phone)) {
			return resultUtil.saveResult("000107", "手机号已被注册!", null);
		}
		// 注册账号
		Account tmp;
		try {
			tmp = accountDao.registByPhone(name, pass, phone);
		} catch (NotExpectedException e) {
			return resultUtil.saveResult("000104", "系统异常导致账户注册失败!", null);
		}
		return resultUtil.saveResult("000100", "账户注册成功", PO_to_Simple.AccountToSimple(tmp));
	}

	@Override
	public Result<AccountSimple> registByEmail(String name, String pass, String e_mail, String em_Acticode,
			String em_token_exptime) {
		// 必要信息检查（账号名和密码是必须包含的）
		if (null == pass || null == name || null == e_mail || null == em_Acticode || null == em_token_exptime
				|| 0 == em_Acticode.length() || 0 == em_token_exptime.length() || 0 == name.length()
				|| 0 == pass.length() || 0 == e_mail.length()) {
			return resultUtil.saveResult("000105", "缺少必要信息，注册失败!", null);
		}
		// 检查账户名称是否为邮箱地址
		if (verifyEmail(name)) {
			return resultUtil.saveResult("000103", "账户名为邮箱，注册失败!", null);
		}
		// 检查账户名称是否为手机号
		if (verifyPhone(name)) {
			return resultUtil.saveResult("000102", "账户名为手机号，注册失败!", null);
		}
		// 检查账号是否存在
		if (accountDao.checkAccountIsExistNoException(name)) {
			return resultUtil.saveResult("000101", "账户已存在，注册失败!", null);
		}
		// 检查邮箱地址是否符合规则
		if (e_mail.length() != 0 && !verifyEmail(e_mail)) {
			return resultUtil.saveResult("000106", "信息不符合格式，注册失败!", null);
		}
		// 检查邮箱是否已被注册
		if (e_mail.length() != 0 && accountDao.checkEmailIsExistNoException(e_mail)) {
			return resultUtil.saveResult("000108", "邮箱已被注册!", null);
		}

		// 注册账号
		Account tmp;
		try {
			tmp = accountDao.registByEmail(name, pass, e_mail, em_Acticode, em_token_exptime);
		} catch (NotExpectedException e) {
			return resultUtil.saveResult("000104", "系统异常导致账户注册失败!", null);
		}
		return resultUtil.saveResult("000100", "账户注册成功", PO_to_Simple.AccountToSimple(tmp));
	}

	/******************************** 1.2 注销账户 ********************************/

	@Override
	public ResultSimple deleteAccountByID(Serializable ID) {
		Account account = accountDao.getByID(Account.class, ID);
		if (null == account) {
			return ResultUtil.saveResult("000201", "账户注销失败：账户不存在!");
		} else {
			String name = account.getName();
			accountDao.delete(account);
			// 验证账户是否被删除
			if (accountDao.checkAccountIsExistNoException(name)) {
				return ResultUtil.saveResult("000202", "账户注销失败：账户未注销!");
			}
		}
		return ResultUtil.saveResult("000200", "账户注销成功!");
	}

	@Override
	public ResultSimple deleteAccountByName(String name) {

		try {
			Account account = accountDao.checkAccountIsExist(name);
			String id = account.getId();
			accountDao.deleteByID(Account.class, id);
			// 验证账户是否被删除
			if (accountDao.checkAccountIsExistNoException(name)) {
				return ResultUtil.saveResult("000202", "账户注销失败：账户未注销!");
			}
		} catch (AccountNotExistException e) {
			return ResultUtil.saveResult("000201", "账户注销失败：账户不存在!");
		}

		return ResultUtil.saveResult("000200", "账户注销成功!");
	}

	/****************************** 1.3 修改账户信息 ******************************/
	
	@Override
	public Result<AccountSimple> updateAccountNameByID(Serializable id, String name) {

		// 检查账户名称是否为邮箱地址
		if (verifyEmail(name)) {
			return resultUtil.saveResult("000103", "账户名为邮箱，注册失败!", null);
		}
		// 检查账户名称是否为手机号
		if (verifyPhone(name)) {
			return resultUtil.saveResult("000102", "账户名为手机号，注册失败!", null);
		}
		// 检查账号是否存在
		if (accountDao.checkAccountIsExistNoException(name)) {
			return resultUtil.saveResult("000101", "账户已存在，注册失败!", null);
		}
		Account account = accountDao.getByID(Account.class, id);
		if (null == account) {
			return resultUtil.saveResult("000300", "修改失败:账户ID不存在!", null);
		}
		accountDao.updateAccount_Name(account, name);
		// 验证是否修改成功
		Account test = accountDao.getByID(Account.class, id);
		if (test.getName() == null || test.getName() != name) {
			if (name.equals(test.getName())) {
				return resultUtil.saveResult("000329", "修改失败：用户名未修改!", null);
			} else {
				return resultUtil.saveResult("000328", "修改失败:用户名修改异常!", null);
			}
		}
		return resultUtil.saveResult("000330", "账号用户名修改成功!", PO_to_Simple.AccountToSimple(test));
	}

	@Override
	public Result<AccountSimple> updateAccountPICByID(Serializable id, String picURL) {
		Account account = accountDao.getByID(Account.class, id);
		if (null == account) {
			return resultUtil.saveResult("000300", "修改失败:账户ID不存在!", null);
		}
		accountDao.updateAccount_pic(account, picURL);
		// 验证是否修改成功
		Account test = accountDao.getByID(Account.class, id);
		if (test.getPicture().getPic_url()==null|| test.getPicture().getPic_url() != picURL) {
			if (picURL.equals(test.getName())) {
				return resultUtil.saveResult("000331", "修改失败：头像未修改!", null);
			} else {
				return resultUtil.saveResult("000332", "修改失败：头像修改异常!", null);
			}
		}
		return resultUtil.saveResult("000333", "账号修改成功!", PO_to_Simple.AccountToSimple(test));
	}

	@Override
	public Result<AccountSimple> updatePassByID(Serializable id, String newPass) {
		Account account = accountDao.getByID(Account.class, id);
		if (null == account) {
			return resultUtil.saveResult("000300", "修改失败:账户ID不存在!", null);
		}
		if (null == newPass || newPass.length() == 0) {
			return resultUtil.saveResult("000302", "修改失败:密码未修改!", null);
		}
		// 记录原有密码
		String oldPass = account.getPass();
		accountDao.updateAccount_pass(account, newPass);
		// 验证是否修改成功
		Account test = accountDao.getByID(Account.class, id);
		if (test.getPass() == null || test.getPass() != newPass) {
			if (oldPass.equals(test.getPass())) {
				return resultUtil.saveResult("000302", "修改失败:密码未修改!", null);
			} else {
				return resultUtil.saveResult("000303", "修改失败:密码修改异常!", null);
			}
		}
		// TODO 密码修改成功，写入修改记录
		return resultUtil.saveResult("000301", "账户密码修改成功!", PO_to_Simple.AccountToSimple(test));
	}

	@Override
	public Result<AccountSimple> updatePassByID(Serializable id, String newPass, String oldPassInput) {
		Account account = accountDao.getByID(Account.class, id);
		if (null == account) {
			return resultUtil.saveResult("000300", "修改失败:账户ID不存在!", null);
		}
		if (null == newPass || newPass.length() == 0) {
			return resultUtil.saveResult("000302", "修改失败:密码未修改!", null);
		}
		// 记录原有密码
		String oldPass = account.getPass();
		if (!oldPass.equals(oldPassInput)) {
			return resultUtil.saveResult("000324", "修改密码失败:输入原密码错误!", null);
		}
		accountDao.updateAccount_pass(account, newPass);
		// 验证是否修改成功
		Account test = accountDao.getByID(Account.class, id);
		if (test.getPass() == null || test.getPass() != newPass) {
			if (oldPass.equals(test.getPass())) {
				return resultUtil.saveResult("000302", "修改失败:密码未修改!", null);
			} else {
				return resultUtil.saveResult("000303", "修改失败:密码修改异常!", null);
			}
		}
		// TODO 密码修改成功，写入修改记录
		return resultUtil.saveResult("000301", "账户密码修改成功!", PO_to_Simple.AccountToSimple(test));
	}

	@Override
	public Result<AccountSimple> updateSexByID(Serializable id, Sex sex) {
		Account account = accountDao.getByID(Account.class, id);
		if (null == account) {
			return resultUtil.saveResult("000300", "修改失败:账户ID不存在", null);
		}
		// 记录原性别
		int oldSex = account.getSex();
		accountDao.updateAccount_sex(account, sex);
		// 验证是否修改成功
		Account test = accountDao.getByID(Account.class, id);
		if (test.getSex() != sex.ordinal()) {
			if (oldSex == test.getSex()) {
				return resultUtil.saveResult("000305", "修改失败:性别未修改!", null);
			} else {
				return resultUtil.saveResult("000306", "修改失败:性别修改异常!", null);
			}
		}
		// TODO 性别修改成功，写入修改记录
		return resultUtil.saveResult("000304", "账号性别修改成功!", PO_to_Simple.AccountToSimple(test));
	}

	@Override
	public Result<AccountSimple> updatePhonByID(Serializable id, String newPhoneNum) {
		Account account = accountDao.getByID(Account.class, id);
		if (null == account) {
			return resultUtil.saveResult("000300", "修改失败:账户ID不存在!", null);
		}
		// 检验更改手机号是否符合规范
		if (null == newPhoneNum || !verifyPhone(newPhoneNum)) {
			return resultUtil.saveResult("000310", "修改失败：手机号格式异常!", null);
		}
		// 检查手机号是否已被注册
		if (accountDao.checkPhonIsExistNoException(newPhoneNum)) {
			return resultUtil.saveResult("000107", "手机号已被注册!", null);
		}

		// 记录原手机号
		String oldPhon = account.getPhone();
		accountDao.updateAccount_phon(account, newPhoneNum);
		// 验证是否修改成功
		Account test = accountDao.getByID(Account.class, id);
		if (null == test.getPhone() || test.getPhone() != newPhoneNum) {
			if (oldPhon.equals(test.getPhone())) {
				return resultUtil.saveResult("000308", "修改失败:手机号未修改!", null);
			} else {
				return resultUtil.saveResult("000309", "修改失败:手机号修改异常!", null);
			}
		}
		// TODO 手机号修改成功，写入修改记录
		return resultUtil.saveResult("000307", "账号手机号修改成功!", PO_to_Simple.AccountToSimple(test));
	}

	@Override
	public Result<AccountSimple> updateRoleByID(Serializable id, Serializable RoleId) {
		Account account = accountDao.getByID(Account.class, id);
		if (null == account) {
			return resultUtil.saveResult("000300", "修改失败:账户ID不存在", null);
		}
		// TODO 修改角色暂未完成
		return null;
	}

	@Override
	public Result<AccountSimple> updateStatuByID(Serializable id, AccountStatus accountStatus) {
		Account account = accountDao.getByID(Account.class, id);
		if (null == account) {
			return resultUtil.saveResult("000300", "修改失败:账户ID不存在!", null);
		}
		// 记录原来账号状态
		int oldAccountStatus = account.getStatus();
		accountDao.updateAccount_status(account, accountStatus);
		// 验证是否修改成功
		Account test = accountDao.getByID(Account.class, id);
		if (test.getStatus() != accountStatus.ordinal()) {
			if (oldAccountStatus == test.getStatus()) {
				resultUtil.saveResult("000312", "修改失败:账号状态未修改!", null);
			} else {
				resultUtil.saveResult("000313", "修改失败:账号状态修改异常!", null);
			}
		}
		// TODO 账号状态修改成功，写入修改记录
		return resultUtil.saveResult("000311", "账号状态修改成功!", PO_to_Simple.AccountToSimple(test));
	}

	@Override
	public Result<AccountSimple> updatePicByID(Serializable id, String PicURL) {
		Account account = accountDao.getByID(Account.class, id);
		if (null == account) {
			return resultUtil.saveResult("000300", "修改失败:账户ID不存在!", null);
		}

		// TODO 修改图片暂未完成
		return null;
	}

	@Override
	public Result<AccountSimple> updateEmailByID(Serializable id, String eMail) {
		Account account = accountDao.getByID(Account.class, id);
		if (null == account) {
			return resultUtil.saveResult("000300", "修改失败:账户ID不存在!", null);
		}
		if (null == eMail || !verifyEmail(eMail)) {
			return resultUtil.saveResult("000323", "修改失败：邮箱格式异常!", null);
		}
		// 检查邮箱是否已被注册
		if (eMail.length() != 0 && accountDao.checkEmailIsExistNoException(eMail)) {
			return resultUtil.saveResult("000108", "邮箱已被注册!", null);
		}
		// 记录原有邮箱地址
		String oldEmail = account.getEmail();
		Boolean oldIsNull = true;// true表示原有的Email为空
		if (!oldEmail.isEmpty()) {
			oldIsNull = false;
		}
		accountDao.updateAccount_email(account, eMail);
		// 验证是否修改成功
		Account test = accountDao.getByID(Account.class, id);
		if (oldIsNull) {// 原有邮箱为空的状况
			if (null == test.getEmail()) {
				return resultUtil.saveResult("000315", "修改失败:账号邮箱未修改!", null);
			}
		} else {
			if (null == test.getEmail() || test.getEmail() != eMail) {
				if (oldEmail.equals(test.getEmail())) {
					return resultUtil.saveResult("000315", "修改失败:账号邮箱未修改!", null);
				} else {
					return resultUtil.saveResult("000316", "修改失败:账号邮箱修改异常!", null);
				}
			}
		}
		return resultUtil.saveResult("000314", "账号邮箱修改成功!", PO_to_Simple.AccountToSimple(test));
	}

	@Override
	public Result<AccountSimple> updateAccoount(AccountSimple account, Boolean IfUpdateSex) {
		Boolean flagIDIsExist = true;
		Boolean flagAccountNameIsExist = true;
		// 检查ID是否存在
		Account getAccountByID = accountDao.getByID(Account.class, account.getId());
		if (null == getAccountByID) {
			flagIDIsExist = false;
		}
		// 检查账号是否存在
		Account getAccountByName = null;
		try {
			getAccountByName = accountDao.checkAccountIsExist(account.getName());
			if (flagIDIsExist && !getAccountByName.getId().equals(account.getId())) {// 账号虽然存在,但是ID不匹配
				return resultUtil.saveResult("000318", "修改失败:修改包含禁止修改信息", null);
			}
		} catch (AccountNotExistException e) {
			flagAccountNameIsExist = false;
		}
		// 账号密码都不存在的情况
		if (!flagIDIsExist && !flagAccountNameIsExist) {
			return resultUtil.saveResult("000321", "修改失败:账号不存在", null);
		}
		// 账号密码都正确
		if (flagAccountNameIsExist && flagIDIsExist) {
			if (account.getId() == getAccountByName.getId() && account.getName() == getAccountByID.getName()) {
				return UpdateAccount_Part(getAccountByID, account, IfUpdateSex);
			}
			return resultUtil.saveResult("000318", "修改失败:修改包含禁止修改信息", null);
		}
		// 执行到这里表明，账号和密码有其中之一是不存在的，表明被修改了。
		return resultUtil.saveResult("000318", "修改失败:修改包含禁止修改信息", null);
	}

	/**
	 * 在账号和密码完全正确的情况下进行检查并更新
	 * 
	 * @param oldAccount
	 * @param newAccount
	 * @return
	 */
	private Result<AccountSimple> UpdateAccount_Part(Account oldAccount, AccountSimple newAccount,
			Boolean IfUpdateSex) {
		// 检查注册时间是否被修改
		if (!oldAccount.getRegistTime().equals(newAccount.getRegistDate())) {
			resultUtil.saveResult("000318", "修改失败:修改包含禁止修改信息", null);
		}

		// 检查性别是否修改
		if (IfUpdateSex) {
			Sex sex;
			if (newAccount.getSex() == 0) {
				sex = Sex.男;
			} else {
				sex = Sex.女;
			}
			String code = updateSexByID(oldAccount.getId(), sex).getCode();
			if (!"000304".equals(code)) {// 性别修改失败
				if ("000305".equals(code)) {// 性别未修改
					return resultUtil.saveResult("000319", "修改失败:账号信息未修改!", null);
				} else if ("000306".equals(code)) {
					return resultUtil.saveResult("000320", "修改失败:账号信息修改异常!", null);
				}
			}
		}
		// 检查手机号是否修改
		if (null != newAccount.getPhone() && !oldAccount.getPhone().equals(newAccount.getPhone())) {
			// 检查是否手机号是否符合规则
			if (!verifyPhone(newAccount.getPhone())) {
				return resultUtil.saveResult("000310", "修改失败：手机号格式异常!", null);
			}
			// 检查手机号是否已被注册
			if (accountDao.checkPhonIsExistNoException(newAccount.getPhone())) {
				return resultUtil.saveResult("000107", "手机号已被注册!", null);
			}
			String code = updatePhonByID(oldAccount.getId(), newAccount.getPhone()).getCode();
			if (!"000307".equals(code)) {// 手机号修改失败
				if ("000308".equals(code)) {// 手机号未修改
					return resultUtil.saveResult("000319", "修改失败:账号信息未修改!", null);
				} else if ("000309".equals(code)) {
					return resultUtil.saveResult("000320", "修改失败:账号信息修改异常!", null);
				}
			}
		}
		// 检查邮箱是否修改
		if (null != newAccount.getEmail()) {
			// TODO 检查新邮箱地址是否符合标准
			// 优化可能，下面两段代码是完全一样的
			// 检查邮箱是否已被注册
			if (newAccount.getEmail().length() != 0 && accountDao.checkEmailIsExistNoException(newAccount.getEmail())) {
				return resultUtil.saveResult("000108", "邮箱已被注册!", null);
			}
			if (null != oldAccount.getEmail()) {
				if (!oldAccount.getEmail().equals(newAccount.getEmail())) {// 邮箱有改动
					Result<AccountSimple> result = updateEmailByID(oldAccount.getId(), newAccount.getEmail());
					if (!"000314".equals(result.getCode())) {// 邮箱修改失败
						if ("000315".equals(result.getCode())) {// 邮箱未修改
							return resultUtil.saveResult("000319", "修改失败:账号信息未修改!", null);
						} else if ("000316".equals(result.getCode())) {
							return resultUtil.saveResult("000320", "修改失败:账号信息修改异常!", null);
						} else if ("000323".equals(result.getCode())) {
							return result;
						}
					}
				}
			} else {// 原邮箱地址为空，直接更新
				Result<AccountSimple> result = updateEmailByID(oldAccount.getId(), newAccount.getEmail());
				if (!"000314".equals(result.getCode())) {// 邮箱修改失败
					if ("000315".equals(result.getCode())) {// 邮箱未修改
						return resultUtil.saveResult("000319", "修改失败:账号信息未修改!", null);
					} else if ("000316".equals(result.getCode())) {
						return resultUtil.saveResult("000320", "修改失败:账号信息修改异常!", null);
					} else if ("000323".equals(result.getCode())) {
						return result;
					}
				}
			}
		}
		// 检测头像是否修改
		// TODO 更新图片

		return resultUtil.saveResult("000322", "账号信息修改成功!", PO_to_Simple.AccountToSimple(oldAccount));
	}

	@Override
	public ResultSimple updateAccountEmailStatus(Serializable id, String email, String activeCode) {
		ResultSimple result = null;
		// 检查ID是否存在
		Account getAccountByID = accountDao.getByID(Account.class, id);
		if (null == getAccountByID || !getAccountByID.getEmail().equals(email)
				|| !getAccountByID.getEm_ActiCode().equals(activeCode)) {
			result = ResultUtil.saveResult("000913", "邮箱验证链接错误!");
			return result;
		}
		// 检查验证链接是否超时
		long timesTimestampOfNow = new Date().getTime() / 1000;
		long save_icTimestamp = Long.valueOf(getAccountByID.getEm_token_exptime());
		if ((timesTimestampOfNow - save_icTimestamp) > 3600) {
			result = ResultUtil.saveResult("000914", "邮箱验证链接失效!");
			return result;
		}
		accountDao.updateAccount_emailStatus(getAccountByID, EmailStatus.激活成功);
		return ResultUtil.saveResult("000915", "邮箱验证链接验证成功!");
	}

	@Override
	public boolean updateClearEmailActiveMSG(Serializable id) {
		boolean flag = false;
		// 检查ID是否存在
		Account getAccountByID = accountDao.getByID(Account.class, id);
		if (null != getAccountByID) {
			flag = true;
			accountDao.clearEmailAciveMsg(getAccountByID);
		}
		return flag;
	}

	@Override
	public ResultSimple updateWriteEmailActiveMSG(Serializable id, String email, String aciveCode, String timeStamp) {
		ResultSimple result;
		// 检查ID是否存在
		Account getAccountByID = accountDao.getByID(Account.class, id);
		if (null != getAccountByID) {
			accountDao.writeAccountEmailRegistMSG(getAccountByID, email, aciveCode, timeStamp);
			result = ResultUtil.saveResult("000014", "激活信息写入成功");
			return result;
		}
		result = ResultUtil.saveResult("000013", "激活信息写入异常");
		return result;
	}

	/******************************** 1.5 登录验证 ********************************/

	@Override
	public Result<AccountSimple> verifyAccount(String name, String pass) {
		Account result = null;
		try {
			if (verifyPhone(name)) {
				result = accountDao.verifyAccountByPhoneAndPass(name, pass);
			} else if (verifyEmail(name)) {
				result = accountDao.verifyAccountByEmailAndPass(name, pass);
				if (1 != result.getEm_status()) {
					return resultUtil.saveResult("000510", "邮箱未激活，无法进行登陆!", null);
				}
			} else {
				result = accountDao.verifyAccountByNameAndPass(name, pass);
			}
		} catch (AccountNotExistException e) {
			return resultUtil.saveResult("000501", "账户验证失败：账号不存在!", null);
		} catch (PasswordWrong e) {
			return resultUtil.saveResult("000502", "账户验证失败：密码错误!", null);
		}
		if (null == result) {
			return resultUtil.saveResult("000503", "系统异常导致账号验证失败!", null);
		}
		// 检查账号的状态
		if (1 != result.getStatus()) {
			return resultUtil.saveResult("000509", "账号处于未激活状态，登陆失败!", null);
		}
		return resultUtil.saveResult("000500", "账号验证成功!", PO_to_Simple.AccountToSimple(result));
	}

	@Override
	public Result<AccountSimple> loginByPhoneAndIdentifyCode(String phone) {
		if (verifyPhone(phone)) {
			Account result = accountDao.checkPhonIsExist(phone);
			if (null == result) {
				return resultUtil.saveResult("000503", "系统异常导致账号验证失败!", null);
			}
			// 检查账号的状态
			if (1 != result.getStatus()) {
				return resultUtil.saveResult("000509", "账号处于未激活状态，登陆失败!", null);
			}
			return resultUtil.saveResult("000500", "账号验证成功!", PO_to_Simple.AccountToSimple(result));
		} else {
			return resultUtil.saveResult("000505", "登录手机号异常!", null);
		}
	}
}
