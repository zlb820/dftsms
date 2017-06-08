package dftsms.web.fxb.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.common.RBAC.domain.simple.AccountSimple;
import org.common.miaodiyun.IdentifyCode.SendmailUtil;
import org.common.util.CreateAccountName;
import org.common.util.Result;
import org.common.util.ResultSimple;
import org.common.util.ResultUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import dftsms.web.fxb.action.base.IdentifyCodeBaseAction;
import dftsms.web.fxb.util.UtilOfFxb;

public class IdentifyCodeAction extends IdentifyCodeBaseAction {
	private static final long serialVersionUID = 1L;
	ResultSimple resultOfSend;
	ResultSimple resultVerify;
	ResultSimple resultVerifyEmail;
	ResultSimple resultOfSendEmail;
	String phone;// 要发送验证码的手机号
	String inputCode;// 输入的验证码
	String email;
	String accountID;
	String activeCode;

	/**
	 * 请求发送邮箱验证码
	 * 
	 * @return
	 */
	public String sendEmailActiveLink() {
		resultOfSendEmail = UtilOfFxb.checkParameterIsNull(email, accountID);
		if ("xxxx01".equals(resultOfSendEmail.getCode())) {
			resultOfSendEmail = accountManager.verifyAccountEmailIsExist(email);
			if ("000006".equals(resultOfSendEmail.getCode())) {// 邮箱未被注册
				if (accountManager.updateClearEmailActiveMSG(accountID)) {// 清理原有激活信息
					resultOfSendEmail = sendLink(email, accountID);
				} else {
					resultOfSendEmail = ResultUtil.saveResult("000012", "未激活邮箱清理异常");
				}
			}
			if ("000005".equals(resultOfSendEmail.getCode())) {// 邮箱已被注册，检测邮箱的激活状态
				Result<AccountSimple> tmpResult = accountManager.verifyAccountEmailActiveStatus(email);
				if ("000010".equals(tmpResult.getCode())) {// 邮箱未被激活
					if (accountManager.updateClearEmailActiveMSG(tmpResult.getData().getId())) {// 未激活邮箱被清理
						resultOfSendEmail = sendLink(email, accountID);
					} else {
						resultOfSendEmail = ResultUtil.saveResult("000012", "未激活邮箱清理异常");
					}
				}
			}
		}
		return Action.SUCCESS;
	}

	private ResultSimple sendLink(String email, String accountID) {
		String timestamp = String.valueOf(new Date().getTime() / 1000);
		String activeCode = DigestUtils.md5Hex(accountID + email + timestamp);
		ResultSimple result = accountManager.updateWriteEmailActiveMSG(accountID, email, activeCode, timestamp);
		if ("000014".equals(result.getCode())) {
			if (SendmailUtil.sendActiveEmail(email, accountID, "用户", activeCode)) {
				result = ResultUtil.saveResult("000911", "邮箱验证链接发送成功!");
			} else {
				result = ResultUtil.saveResult("000912", "邮箱验证链接发送失败!");
			}
		}
		return result;
	}

	/**
	 * 验证邮箱激活链接
	 * 
	 * @return
	 */
	public String verifyEmailActiveLink() {
		resultVerifyEmail = UtilOfFxb.checkParameterIsNull(email, accountID, activeCode);
		if ("xxxx01".equals(resultVerifyEmail.getCode())) {
			resultVerifyEmail = accountManager.updateAccountEmailStatus(accountID, email, activeCode);
		}
		return Action.SUCCESS;
	}

	/**
	 * 请求发送手机验证码
	 * 
	 * @return
	 */
	public String sentIdentifyCode() {
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> session = ctx.getSession();

		// 创建前其检查工具类
		UtilOfFxb<AccountSimple> utilOfchek = new UtilOfFxb<>();
		Result<AccountSimple> result = utilOfchek.checkParameterIsNull_returnResult(phone);
		resultOfSend=new ResultSimple();
		resultOfSend.setCode(result.getCode());
		resultOfSend.setMessage(result.getMessage());
		if ("xxxx01".equals(resultOfSend.getCode())) {// 参数检查通过
			// 生成验证码
			String code = String.valueOf(CreateAccountName.getNum(1000, 9999));
			resultOfSend = sendIdentifyCodeManager.sendIdentifyCode(code, phone);
			if ("000900".equals(resultOfSend.getCode())) {
				// 验证码发送成功
				session.put("identifyCode", code);
				session.put("icTimestamp", UtilOfFxb.getTimestamp());
				session.put("icPhone", phone);
				session.put("IdentifyCodeFlag", false);
			}
		}
		return Action.SUCCESS;
	}

	/**
	 * 验证手机验证码
	 * 
	 * @return
	 */
	public String verifyIdentifyCode() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		// 获取Session中保存的时间戳和验证码
		String save_identifyCode = (String) session.get("identifyCode");
		Long save_icTimestamp = Long.valueOf((String) session.get("icTimestamp"));

		if (null == save_identifyCode || null == save_icTimestamp || 0 == save_identifyCode.length()) {
			resultVerify = ResultUtil.saveResult("xxxx03", "请求顺序错误，导致此次请求无法执行!");
			return Action.SUCCESS;
		}

		if (null == inputCode || 0 == inputCode.length()) {
			resultVerify = ResultUtil.saveResult("000909", "接收到验证码为空，无法验证!");
			return Action.SUCCESS;
		}
		long TimesTimestampOfNow = UtilOfFxb.getTimestampOfLong();
		if ((TimesTimestampOfNow - save_icTimestamp) > 300) {
			resultVerify = ResultUtil.saveResult("000908", "验证码已过期!");
			return Action.SUCCESS;
		}
		if (save_identifyCode.equals(inputCode)) {
			session.remove("identifyCode");
			session.remove("icTimestamp");
			session.put("IdentifyCodeFlag", true);
			resultVerify = ResultUtil.saveResult("000910", "验证码验证成功!");
		} else {
			resultVerify = ResultUtil.saveResult("000907", "验证码错误!");
		}
		return Action.SUCCESS;
	}

	/************************* getter和setter方法 *************************/

	public ResultSimple getResultVerify() {
		return resultVerify;
	}

	public ResultSimple getResultVerifyEmail() {
		return resultVerifyEmail;
	}

	public void setResultVerifyEmail(ResultSimple resultVerifyEmail) {
		this.resultVerifyEmail = resultVerifyEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public void setResultVerify(ResultSimple resultVerify) {
		this.resultVerify = resultVerify;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public ResultSimple getResultOfSend() {
		return resultOfSend;
	}

	public void setResultOfSend(ResultSimple resultOfSend) {
		this.resultOfSend = resultOfSend;
	}

	public String getPhone() {
		return phone;
	}

	public ResultSimple getResultOfSendEmail() {
		return resultOfSendEmail;
	}

	public void setResultOfSendEmail(ResultSimple resultOfSendEmail) {
		this.resultOfSendEmail = resultOfSendEmail;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
