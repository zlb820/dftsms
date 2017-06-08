package dftsms.web.fxb.action;

import java.util.Map;

import org.common.RBAC.domain.simple.AccountSimple;
import org.common.util.Result;
import org.common.util.ResultSimple;
import org.common.util.ResultUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import dftsms.web.fxb.action.base.AccountManagerBaseAction;
import dftsms.web.fxb.util.UtilOfFxb;

public class VerifyAccountAction extends AccountManagerBaseAction {
	private static final long serialVersionUID = 1L;
	Result<AccountSimple> verifyAccountResult;
	Result<AccountSimple> verifyPhoneAndIdentifyResult;
	ResultSimple verifyOnlyAccountResult;
	ResultSimple verifyOnlyPoneResult;
	ResultSimple verifyOnlyEmailResult;
	ResultSimple logOutResult;
	ResultSimple verifyAccountLoginStatusResult;
	String accountName;
	String accountPhone;
	String accountEmail;
	String account;
	String password;
	String phone;
	String identifyCode;
	String accountID;

	/**
	 * 注销登录
	 * 
	 * @return
	 */
	public String LogOut() {
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> session = ctx.getSession();
		if (null == session.get("fxb_AccountID")) {
			logOutResult = ResultUtil.saveResult("000507", "账号未登录，无需注销登录");
		} else {
			session.remove("fxb_AccountID");
			session.remove("fxb_roleID");
			logOutResult = ResultUtil.saveResult("000508", "账号注销登录成功");
		}
		return Action.SUCCESS;
	}

	/**
	 * 根据手机号及验证码登录
	 * 
	 * @return
	 */
	public String loginByPhoneAndIdentifyCode() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		// 获取Session中保存的时间戳和验证码
		String save_identifyCode = (String) session.get("identifyCode");
		String save_icTimestamp = (String) session.get("icTimestamp");
		// 创建前其检查工具类
		UtilOfFxb<AccountSimple> utilOfchek = new UtilOfFxb<>();
		verifyPhoneAndIdentifyResult = utilOfchek.checkParameterIsNull_returnResult(phone, identifyCode);
		if ("xxxx01".equals(verifyPhoneAndIdentifyResult.getCode())) {// 参数检查通过
			verifyPhoneAndIdentifyResult = utilOfchek.checkNeededElementIsNull_returnResult(save_identifyCode,
					save_icTimestamp);
			if ("xxxx04".equals(verifyPhoneAndIdentifyResult.getCode())) {// 必要标识检查通过
				Long save_icTimestampOflong = Long.valueOf(save_icTimestamp);
				long TimesTimestampOfNow = UtilOfFxb.getTimestampOfLong();
				if ((TimesTimestampOfNow - save_icTimestampOflong) > 300) {
					verifyPhoneAndIdentifyResult = new Result<AccountSimple>();
					verifyPhoneAndIdentifyResult.setCode("000908");
					verifyPhoneAndIdentifyResult.setMessage("验证码已过期!");
					return Action.SUCCESS;
				}
				if (save_identifyCode.equals(identifyCode)) {// 验证码校验成功
					if (null == phone || !phone.equals(session.get("icPhone"))) {
						verifyPhoneAndIdentifyResult = new Result<AccountSimple>();
						verifyPhoneAndIdentifyResult.setCode("000505");
						verifyPhoneAndIdentifyResult.setMessage("登录手机号异常!");
					} else {
						verifyPhoneAndIdentifyResult = accountManager.loginByPhoneAndIdentifyCode(phone);
						if ("000500".equals(verifyPhoneAndIdentifyResult.getCode())) {
							// 移除校验码时保存数据
							session.remove("IdentifyCodeFlag");
							session.remove("icPhone");
							session.remove("icTimestamp");
							session.remove("identifyCode");
							// 添加登录成功后标识
							session.put("fxb_AccountID", verifyPhoneAndIdentifyResult.getData().getId());
							session.put("fxb_roleID", verifyPhoneAndIdentifyResult.getData().getRoleID());
						}
					}
				} else {
					verifyPhoneAndIdentifyResult = new Result<AccountSimple>();
					verifyPhoneAndIdentifyResult.setCode("000907");
					verifyPhoneAndIdentifyResult.setMessage("验证码错误!");
				}
			}
		}
		return Action.SUCCESS;
	}

	/**
	 * 根据手机号及验证码登录（前提必须通过验证码验证）
	 * 
	 * @return
	 */
	@Deprecated
	public String loginByPhoneAndIdentifyCode_NoIdentifyCheck() {
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> session = ctx.getSession();
		if (null != session.get("icPhone")) {// 检查校验手机号是否异常
			if (((String) session.get("icPhone")).equals(phone)) {
				if (null != session.get("IdentifyCodeFlag")) {// 检查验证码是否校验成功
					if (((String) session.get("IdentifyCodeFlag")).equals("true")) {
						verifyPhoneAndIdentifyResult = accountManager.loginByPhoneAndIdentifyCode(phone);
						if ("000500".equals(verifyPhoneAndIdentifyResult.getCode())) {
							// 移除校验码时保存数据
							session.remove("IdentifyCodeFlag");
							session.remove("icPhone");
							// 添加登录成功后标识
							session.put("fxb_AccountID", verifyPhoneAndIdentifyResult.getData().getId());
							session.put("fxb_roleID", verifyPhoneAndIdentifyResult.getData().getRoleID());
						}
					} else {
						verifyPhoneAndIdentifyResult = new Result<AccountSimple>();
						verifyPhoneAndIdentifyResult.setCode("000506");
						verifyPhoneAndIdentifyResult.setMessage("验证码未通过，登录失败!");
					}
				} else {
					verifyPhoneAndIdentifyResult = new Result<AccountSimple>();
					verifyPhoneAndIdentifyResult.setCode("000506");
					verifyPhoneAndIdentifyResult.setMessage("验证码未通过，登录失败!");
				}
			} else {
				verifyPhoneAndIdentifyResult = new Result<AccountSimple>();
				verifyPhoneAndIdentifyResult.setCode("000505");
				verifyPhoneAndIdentifyResult.setMessage("登录手机号异常!");
			}
		} else {
			verifyPhoneAndIdentifyResult = new Result<AccountSimple>();
			verifyPhoneAndIdentifyResult.setCode("000505");
			verifyPhoneAndIdentifyResult.setMessage("登录手机号异常!");
		}
		return Action.SUCCESS;
	}

	/**
	 * 根据用户的账户名(自动识别邮箱、手机号账号)与密码进行验证<br>
	 * 需要传递过来的参数：account
	 * 
	 * @return
	 */
	public String verifyAccountByPass() {
		verifyAccountResult = new UtilOfFxb<AccountSimple>().checkParameterIsNull_returnResult(account, password);
		if ("xxxx01".equals(verifyAccountResult.getCode())) {// 检查通过
			ActionContext ctx = ActionContext.getContext();
			Map<String, Object> session = ctx.getSession();
			verifyAccountResult = accountManager.verifyAccount(account, password);
			if ("000500".equals(verifyAccountResult.getCode())) {
				// 添加登录成功后标识
				session.put("fxb_AccountID", verifyAccountResult.getData().getId());
				session.put("fxb_roleID", verifyAccountResult.getData().getRoleID());
			}
		}
		return Action.SUCCESS;
	}

	/**
	 * 检查用户的账户名是否已被注册
	 * 
	 * @return
	 */
	public String verifyOnlyAccountNameIsExist() {
		// 参数检查
		verifyOnlyAccountResult = UtilOfFxb.checkParameterIsNull(accountName);
		if ("xxxx01".equals(verifyOnlyAccountResult.getCode())) {// 参数检查通过
			verifyOnlyAccountResult = accountManager.verifyAccountNameIsExist(accountName);
		}
		return Action.SUCCESS;
	}

	/**
	 * 检查用户的手机号是否已被注册
	 * 
	 * @return
	 */
	public String verifyOnlyAccountPhoneIsExist() {
		verifyOnlyPoneResult = UtilOfFxb.checkParameterIsNull(accountPhone);
		if ("xxxx01".equals(verifyOnlyPoneResult.getCode())) {// 参数检查通过
			verifyOnlyPoneResult = accountManager.verifyAccountPhoneIsExist(accountPhone);
		}
		return Action.SUCCESS;
	}

	/**
	 * 检查用户的邮箱是否已被注册
	 * 
	 * @return
	 */
	public String verifyOnlyAccountEmailIsExist() {
		verifyOnlyEmailResult = UtilOfFxb.checkParameterIsNull(accountEmail);
		if ("xxxx01".equals(verifyOnlyEmailResult.getCode())) {// 参数检查通过
			verifyOnlyEmailResult = accountManager.verifyAccountEmailIsExist(accountEmail);
		}
		return Action.SUCCESS;
	}

	/**
	 * 验证账号的登录状态
	 * 
	 * @return
	 */
	public String verifyAccountLoginStatus() {
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> session = ctx.getSession();
		String saveAccountID = (String) session.get("fxb_AccountID");
		// 创建前其检查工具类
		verifyAccountLoginStatusResult = UtilOfFxb.checkParameterIsNull(accountID);
		if ("xxxx01".equals(verifyAccountLoginStatusResult.getCode())) {// 参数检测成功
			verifyAccountLoginStatusResult = UtilOfFxb.checkNeededElementIsNull(saveAccountID);
			if ("xxxx04".equals(verifyAccountLoginStatusResult.getCode())) {// 必要标识检查通过
				if (accountID.equals(saveAccountID)) {
					verifyAccountLoginStatusResult = new ResultSimple();
					verifyAccountLoginStatusResult.setCode("000009");
					verifyAccountLoginStatusResult.setMessage("登录状态：已登录");
				} else {
					verifyAccountLoginStatusResult = new ResultSimple();
					verifyAccountLoginStatusResult.setCode("000008");
					verifyAccountLoginStatusResult.setMessage("登录状态：未登录");
				}
			} else {
				verifyAccountLoginStatusResult = new ResultSimple();
				verifyAccountLoginStatusResult.setCode("000008");
				verifyAccountLoginStatusResult.setMessage("登录状态：未登录");
			}
		}

		return Action.SUCCESS;
	}

	/************************* getter和setter方法 *************************/

	public Result<AccountSimple> getVerifyAccountResult() {
		return verifyAccountResult;
	}

	public void setVerifyAccountResult(Result<AccountSimple> verifyAccountResult) {
		this.verifyAccountResult = verifyAccountResult;
	}

	public ResultSimple getVerifyOnlyAccountResult() {
		return verifyOnlyAccountResult;
	}

	public void setVerifyOnlyAccountResult(ResultSimple verifyOnlyAccountResult) {
		this.verifyOnlyAccountResult = verifyOnlyAccountResult;
	}

	public ResultSimple getVerifyOnlyPoneResult() {
		return verifyOnlyPoneResult;
	}

	public void setVerifyOnlyPoneResult(ResultSimple verifyOnlyPoneResult) {
		this.verifyOnlyPoneResult = verifyOnlyPoneResult;
	}

	public ResultSimple getVerifyOnlyEmailResult() {
		return verifyOnlyEmailResult;
	}

	public void setVerifyOnlyEmailResult(ResultSimple verifyOnlyEmailResult) {
		this.verifyOnlyEmailResult = verifyOnlyEmailResult;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Result<AccountSimple> getVerifyPhoneAndIdentifyResult() {
		return verifyPhoneAndIdentifyResult;
	}

	public void setVerifyPhoneAndIdentifyResult(Result<AccountSimple> verifyPhoneAndIdentifyResult) {
		this.verifyPhoneAndIdentifyResult = verifyPhoneAndIdentifyResult;
	}

	public ResultSimple getLogOutResult() {
		return logOutResult;
	}

	public void setLogOutResult(ResultSimple logOutResult) {
		this.logOutResult = logOutResult;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public ResultSimple getVerifyAccountLoginStatusResult() {
		return verifyAccountLoginStatusResult;
	}

	public void setVerifyAccountLoginStatusResult(ResultSimple verifyAccountLoginStatusResult) {
		this.verifyAccountLoginStatusResult = verifyAccountLoginStatusResult;
	}
}
