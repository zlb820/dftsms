package org.common.RBAC.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.common.RBAC.dao.AccountDao;
import org.common.RBAC.dao.exception.AccountNotExistException;
import org.common.RBAC.dao.exception.EmailNotExistException;
import org.common.RBAC.dao.exception.NotExpectedException;
import org.common.RBAC.dao.exception.PasswordWrong;
import org.common.RBAC.dao.exception.PhonNotExistException;
import org.common.RBAC.domain.Account;
import org.common.RBAC.domain.Pictures;
import org.common.RBAC.domain.Role;
import org.common.util.AccountStatus;
import org.common.util.EmailStatus;
import org.common.util.Sex;

public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {
	/******************************** 1.0 通用部分 ********************************/
	@Override
	public Account checkAccountIsExist(String name) {
		String hql = "from Account a where a.name = ?0";
		List<Account> result = find(hql, name);
		if (result.isEmpty()) {
			throw new AccountNotExistException();
		}
		return result.iterator().next();
	}

	@Override
	public Boolean checkAccountIsExistNoException(String name) {
		Boolean flag = false;
		String hql = "from Account a where a.name = ?0";
		List<Account> result = find(hql, name);
		if (!result.isEmpty()) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Account checkPhonIsExist(String phone) throws PhonNotExistException {
		String hql = "from Account a where a.phone = ?0";
		List<Account> result = find(hql, phone);
		if (result.isEmpty()) {
			throw new PhonNotExistException();
		}
		return result.iterator().next();
	}

	@Override
	public Boolean checkPhonIsExistNoException(String phone) {
		Boolean flag = false;
		String hql = "from Account a where a.phone = ?0";
		List<Account> result = find(hql, phone);
		if (!result.isEmpty()) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Account checkEmailIsExist(String eMail) throws EmailNotExistException {
		String hql = "from Account a where a.email = ?0";
		List<Account> result = find(hql, eMail);
		if (result.isEmpty()) {
			throw new EmailNotExistException();
		}
		return result.iterator().next();
	}

	@Override
	public Boolean checkEmailIsExistNoException(String eMail) {
		Boolean flag = false;
		String hql = "from Account a where a.email = ?0";
		List<Account> result = find(hql, eMail);
		if (!result.isEmpty()) {
			flag = true;
		}
		return flag;
	}

	/******************************** 1.1 注册账户 ********************************/

	@Override
	public Account registByPhone(String name, String pass, String phone) {
		Account a = new Account();
		a.setName(name);
		a.setPass(pass);
		a.setPhone(phone);
		a.setRegistTime(new Date());
		a.setStatus(AccountStatus.激活.ordinal());
		 Pictures p = new Pictures();
		 p.setPic_url("/dftsms-web/PIC/fxb.png");
		 a.setPicture(p);
		Serializable temp = save(a);
		if (null == temp) {
			throw new NotExpectedException("注册异常");
		}
		return getByID(Account.class, temp);
	}

	@Override
	public Account registByEmail(String name, String pass, String e_mail, String em_Acticode, String em_token_exptime) {
		Account a = new Account();
		a.setName(name);
		a.setPass(pass);
		Pictures p = new Pictures();
		p.setPic_url("/dftsms-web/PIC/fxb.png");
		a.setPicture(p);
		a.setEmail(e_mail);
		a.setRegistTime(new Date());
		a.setStatus(AccountStatus.激活.ordinal());
		a.setEm_ActiCode(em_Acticode);
		a.setEm_token_exptime(em_token_exptime);
		Serializable temp = save(a);
		if (null == temp) {
			throw new NotExpectedException("注册异常");
		}
		return getByID(Account.class, temp);
	}

	/****************************** 1.3 修改账户信息 ******************************/

	@Override
	public void updateAccount_Name(Account account, String name) {
		account.setName(name);
	}

	@Override
	public void updateAccount_pass(Account account, String newPass) {
		account.setPass(newPass);
	}

	@Override
	public void updateAccount_sex(Account account, Sex sex) {
		account.setSex(sex.ordinal());
	}

	@Override
	public void updateAccount_phon(Account account, String newPhon) {
		account.setPhone(newPhon);
	}

	@Override
	public void updateAccount_email(Account account, String newEmail) {
		account.setEmail(newEmail);
	}

	@Override
	public void updateAccount_pic(Account account, String picURL) {
		account.getPicture().setPic_url(picURL);
	}

	@Override
	public void updateAccount_status(Account account, AccountStatus accountStatus) {
		account.setStatus(accountStatus.ordinal());
	}

	@Override
	public void updateAccount_role(Account account, Role role) {
		account.setRoles(role);
	}

	@Override
	public void updateAccount_emailStatus(Account account, EmailStatus emilStatus) {
		account.setEm_status(emilStatus.ordinal());
	}

	@Override
	public void clearEmailAciveMsg(Account account) {
		account.setEmail("");
		account.setEm_ActiCode("");
		account.setEm_status(EmailStatus.未激活.ordinal());
		account.setEm_token_exptime("");
	}

	@Override
	public void writeAccountEmailRegistMSG(Account account, String email, String aciveCode, String timeStamp) {
		account.setEmail(email);
		account.setEm_ActiCode(aciveCode);
		account.setEm_token_exptime(timeStamp);
	}

	/******************************** 1.5 登录验证 ********************************/

	@Override
	public Account verifyAccountByNameAndPass(String name, String pass) {
		boolean flag = checkAccountIsExistNoException(name);
		List<Account> result;
		if (flag) {
			String hql = "from Account a where a.name = ?0 and a.pass=?1";
			result = find(hql, name, pass);
			if (result.isEmpty()) {
				throw new PasswordWrong();
			}
		} else {
			throw new AccountNotExistException();
		}
		return result.iterator().next();
	}

	@Override
	public Account verifyAccountByPhoneAndPass(String phone, String pass)
			throws AccountNotExistException, PasswordWrong {
		List<Account> result;
		String hql = "from Account a where a.phone = ?0 and a.pass=?1";
		result = find(hql, phone, pass);
		if (result.isEmpty()) {
			String hqlPhone = "from Account a where a.phone = ?0";
			List<Account> resultPhone;
			resultPhone = find(hqlPhone, phone);
			if (resultPhone.isEmpty()) {// 手机号不存在
				throw new AccountNotExistException();
			} else {
				throw new PasswordWrong();
			}
		}
		return result.iterator().next();
	}

	@Override
	public Account verifyAccountByEmailAndPass(String email, String pass)
			throws AccountNotExistException, PasswordWrong {
		List<Account> result;
		String hql = "from Account a where a.email = ?0 and a.pass=?1";
		result = find(hql, email, pass);
		if (result.isEmpty()) {
			String hqlPhone = "from Account a where a.email = ?0";
			List<Account> resultPhone;
			resultPhone = find(hqlPhone, email);
			if (resultPhone.isEmpty()) {// 邮箱不存在
				throw new AccountNotExistException();
			} else {
				throw new PasswordWrong();
			}
		}
		return result.iterator().next();
	}

	@Override
	public Account verifyAccountByPhoneAndIdentifyCode(String phone) throws AccountNotExistException {
		List<Account> result;
		String hql = "from Account a where a.phone = ?0";
		result = find(hql, phone);
		if (result.isEmpty()) {
			String hqlPhone = "from Account a where a.phone = ?0";
			List<Account> resultPhone;
			resultPhone = find(hqlPhone, phone);
			if (resultPhone.isEmpty()) {// 手机号不存在
				throw new AccountNotExistException();
			} else {
				throw new PasswordWrong();
			}
		}
		return result.iterator().next();
	}
}
