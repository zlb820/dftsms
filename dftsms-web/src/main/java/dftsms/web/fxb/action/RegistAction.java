package dftsms.web.fxb.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.common.RBAC.domain.simple.AccountSimple;
import org.common.miaodiyun.IdentifyCode.SendmailUtil;
import org.common.util.Result;
import org.common.util.ResultSimple;
import org.common.util.ResultUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import dftsms.web.fxb.action.base.AccountManagerBaseAction;
import dftsms.web.fxb.util.UtilOfFxb;

public class RegistAction extends AccountManagerBaseAction {
	private static final long serialVersionUID = 1L;
	Result<AccountSimple> registByPhoneAndPass_Result;
	Result<AccountSimple> registByPhoneAndPass_NoName_Result;
	Result<AccountSimple> registByEmailAndPass_Result;
	Result<AccountSimple> registByEmailAndPass_NoName_Result;
	String email;
	String accountName;
	String oneTimeInputPass;
	String SecondTimeInputPass;
	int nameGenerationStrategy;
	int nameMixLenth;
	int nameMaxLenth;
	boolean nameIsGirl;

	/**
	 * 通过邮箱注册账号
	 * 
	 * @return
	 */
	public String registByEmailAndPass() {
		ResultUtil<AccountSimple> resultUtil = new ResultUtil<AccountSimple>();
		// 创建参数检查工具
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		registByEmailAndPass_Result = utilOfCheck.checkParameterIsNull_returnResult(accountName, email,
				oneTimeInputPass, SecondTimeInputPass);
		if ("xxxx01".equals(registByEmailAndPass_Result.getCode())) {// 参数检查成功
			if (!oneTimeInputPass.equals(SecondTimeInputPass)) {// 如果两次密码输入不同
				registByEmailAndPass_Result = resultUtil.saveResult("000109", "密码两次输入不一致!", null);
				return Action.SUCCESS;
			}
			String timestamp = String.valueOf(new Date().getTime() / 1000);
			String activeCode = DigestUtils.md5Hex(accountName + email + oneTimeInputPass + timestamp);

			registByEmailAndPass_Result = accountManager.registByEmail(accountName, oneTimeInputPass, email, activeCode,
					timestamp);
			if ("000100".equals(registByEmailAndPass_Result.getCode())) {// 注册成功
				if (SendmailUtil.sendActiveEmail(email, registByEmailAndPass_Result.getData().getId(), registByEmailAndPass_Result.getData().getName(),
						activeCode)) {
					registByEmailAndPass_Result.setCode("000911");
					registByEmailAndPass_Result.setMessage("邮箱验证链接发送成功!");
				} else {
					accountManager.deleteAccountByID(registByEmailAndPass_Result.getData().getId());
					registByEmailAndPass_Result = resultUtil.saveResult("000912", "邮箱验证链接发送失败!", null);
				}
			}
		}

		return Action.SUCCESS;
	}

	/**
	 * 通过邮箱注册账号(账号名由系统生成)
	 * 
	 * @return
	 */
	public String registByEmailAndPass_SysCreateName() {
		ResultUtil<AccountSimple> resultUtil = new ResultUtil<AccountSimple>();
		// 创建参数检查工具
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		registByEmailAndPass_NoName_Result = utilOfCheck.checkParameterIsNull_returnResult(email, oneTimeInputPass,
				SecondTimeInputPass);
		if ("xxxx01".equals(registByEmailAndPass_NoName_Result.getCode())) {// 参数检查成功
			if (!oneTimeInputPass.equals(SecondTimeInputPass)) {// 如果两次密码输入不同
				registByEmailAndPass_NoName_Result = resultUtil.saveResult("000109", "密码两次输入不一致!", null);
				return Action.SUCCESS;
			}
			// 生成姓名
			boolean flagNameIsOnly = false;
			String nameOfSystemGeneration = null;
			// 检查必要参数是否合理
			ResultSimple tempResult = UtilOfFxb.checkAccountNameParameter(nameGenerationStrategy, nameMixLenth,
					nameMaxLenth, nameIsGirl);
			if (!"xxxx01".equals(tempResult.getCode())) {
				registByEmailAndPass_NoName_Result = resultUtil.saveResult(tempResult.getCode(),
						tempResult.getMessage(), null);
				return Action.SUCCESS;
			}
			while (!flagNameIsOnly) {
				// 根据生成策略生成账户名
				nameOfSystemGeneration = UtilOfFxb.createAccountName(nameGenerationStrategy, nameMixLenth, nameMaxLenth,
						nameIsGirl);
				if (!accountManager.verifyAccountNameIsExist_Flag(nameOfSystemGeneration)) {
					flagNameIsOnly = true;
				}
			}
			String timestamp = String.valueOf(new Date().getTime() / 1000);
			String activeCode = DigestUtils.md5Hex(nameOfSystemGeneration + email + oneTimeInputPass + timestamp);
			registByEmailAndPass_NoName_Result = accountManager.registByEmail(nameOfSystemGeneration, oneTimeInputPass,
					email, activeCode, timestamp);
			if ("000100".equals(registByEmailAndPass_NoName_Result.getCode())) {// 注册成功
				if (SendmailUtil.sendActiveEmail(email, registByEmailAndPass_NoName_Result.getData().getId(), registByEmailAndPass_NoName_Result.getData().getName(),
						activeCode)) {
					registByEmailAndPass_NoName_Result.setCode("000911");
					registByEmailAndPass_NoName_Result.setMessage("邮箱验证链接发送成功!");
				} else {
					accountManager.deleteAccountByID(registByEmailAndPass_NoName_Result.getData().getId());
					registByEmailAndPass_NoName_Result = resultUtil.saveResult("000912", "邮箱验证链接发送失败!", null);
				}
			}
		}
		return Action.SUCCESS;
	}

	/**
	 * 通过手机验证码注册账号
	 * 
	 * @return
	 */
	public String registbyPhoneAndPass() {
		System.out.println("注册");
		ResultUtil<AccountSimple> resultUtil = new ResultUtil<AccountSimple>();
		ActionContext atx = ActionContext.getContext();
		Map<String, Object> session = atx.getSession();
		String save_IdentifyCodeFlag = String.valueOf(session.get("IdentifyCodeFlag"));
		String save_icPhone = (String) session.get("icPhone");

		// 创建参数检查工具
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		registByPhoneAndPass_Result = utilOfCheck.checkParameterIsNull_returnResult(accountName, oneTimeInputPass,
				SecondTimeInputPass);
		if ("xxxx01".equals(registByPhoneAndPass_Result.getCode())) {// 参数检查成功
			if (!oneTimeInputPass.equals(SecondTimeInputPass)) {// 如果两次密码输入不同
				registByPhoneAndPass_Result = resultUtil.saveResult("000109", "密码两次输入不一致!", null);
				return Action.SUCCESS;
			}
			registByPhoneAndPass_Result = utilOfCheck.checkNeededElementIsNull_returnResult(save_icPhone,
					save_IdentifyCodeFlag);
			if ("xxxx04".equals(registByPhoneAndPass_Result.getCode())) {// 必要元素检查成功
				if (!"true".equals(save_IdentifyCodeFlag)) {// 验证码检测未通过
					registByPhoneAndPass_Result = resultUtil.saveResult("000110", "验证码未通过，注册失败!", null);
					return Action.SUCCESS;
				}
				// 生成姓名
				ResultSimple nameCheck = accountManager.verifyAccountNameIsExist(accountName);
				if ("000003".equals(nameCheck.getCode())) {
					registByPhoneAndPass_Result.setCode(nameCheck.getCode());
					registByPhoneAndPass_Result.setMessage(nameCheck.getMessage());
					return Action.SUCCESS;
				}
				registByPhoneAndPass_Result = accountManager.registeByPhone(accountName, oneTimeInputPass,
						(String) session.get("icPhone"));
				if ("000100".equals(registByPhoneAndPass_Result.getCode())) {
					session.remove("icPhone");
					session.remove("IdentifyCodeFlag");
				}

			}
		}
		return Action.SUCCESS;
	}

	/**
	 * 通过手机验证码注册账号(账号名由系统生成)
	 * 
	 * @return
	 */
	public String registbyPhoneAndPass_SysCreateName() {
		System.out.println("注册---》");
		ResultUtil<AccountSimple> resultUtil = new ResultUtil<AccountSimple>();
		ActionContext atx = ActionContext.getContext();
		Map<String, Object> session = atx.getSession();
		String save_IdentifyCodeFlag = String.valueOf(session.get("IdentifyCodeFlag"));
		String save_icPhone = (String) session.get("icPhone");

		// 创建参数检查工具
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		registByPhoneAndPass_NoName_Result = utilOfCheck.checkParameterIsNull_returnResult(oneTimeInputPass,
				SecondTimeInputPass);
		if ("xxxx01".equals(registByPhoneAndPass_NoName_Result.getCode())) {// 参数检查成功
			if (!oneTimeInputPass.equals(SecondTimeInputPass)) {// 如果两次密码输入不同
				registByPhoneAndPass_NoName_Result = resultUtil.saveResult("000109", "密码两次输入不一致!", null);
				return Action.SUCCESS;
			}
			registByPhoneAndPass_NoName_Result = utilOfCheck.checkNeededElementIsNull_returnResult(save_icPhone,
					save_IdentifyCodeFlag);
			if ("xxxx04".equals(registByPhoneAndPass_NoName_Result.getCode())) {// 必要元素检查成功
				if (!"true".equals(save_IdentifyCodeFlag)) {// 验证码检测未通过
					registByPhoneAndPass_NoName_Result = resultUtil.saveResult("000110", "验证码未通过，注册失败!", null);
					return Action.SUCCESS;
				}
				// 生成姓名
				boolean flagNameIsOnly = false;
				String nameOfSystemGeneration = null;
				// 检查必要参数是否合理
				ResultSimple tempResult = UtilOfFxb.checkAccountNameParameter(nameGenerationStrategy, nameMixLenth,
						nameMaxLenth, nameIsGirl);
				if (!"xxxx01".equals(tempResult.getCode())) {
					registByPhoneAndPass_NoName_Result = resultUtil.saveResult(tempResult.getCode(),
							tempResult.getMessage(), null);
					return Action.SUCCESS;
				}
				while (!flagNameIsOnly) {
					// 根据生成策略生成账户名
					nameOfSystemGeneration = UtilOfFxb.createAccountName(nameGenerationStrategy, nameMixLenth,
							nameMaxLenth, nameIsGirl);
					if (!accountManager.verifyAccountNameIsExist_Flag(nameOfSystemGeneration)) {
						flagNameIsOnly = true;
					}
				}
				registByPhoneAndPass_NoName_Result = accountManager.registeByPhone(nameOfSystemGeneration,
						oneTimeInputPass, (String) session.get("icPhone"));
				if ("000100".equals(registByPhoneAndPass_NoName_Result.getCode())) {
					session.remove("icPhone");
					session.remove("IdentifyCodeFlag");
				}

			}
		}
		return Action.SUCCESS;
	}

	/************************* getter和setter方法 *************************/
	public int getNameMixLenth() {
		return nameMixLenth;
	}

	public void setNameMixLenth(int nameMixLenth) {
		this.nameMixLenth = nameMixLenth;
	}

	public int getNameMaxLenth() {
		return nameMaxLenth;
	}

	public void setNameMaxLenth(int nameMaxLenth) {
		this.nameMaxLenth = nameMaxLenth;
	}

	public boolean isNameIsGirl() {
		return nameIsGirl;
	}

	public void setNameIsGirl(boolean nameIsGirl) {
		this.nameIsGirl = nameIsGirl;
	}

	public int getNameGenerationStrategy() {
		return nameGenerationStrategy;
	}

	public void setNameGenerationStrategy(int nameGenerationStrategy) {
		this.nameGenerationStrategy = nameGenerationStrategy;
	}

	public String getSecondTimeInputPass() {
		return SecondTimeInputPass;
	}

	public void setSecondTimeInputPass(String secondTimeInputPass) {
		SecondTimeInputPass = secondTimeInputPass;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOneTimeInputPass() {
		return oneTimeInputPass;
	}

	public void setOneTimeInputPass(String oneTimeInputPass) {
		this.oneTimeInputPass = oneTimeInputPass;
	}

	public Result<AccountSimple> getRegistByPhoneAndPass_NoName_Result() {
		return registByPhoneAndPass_NoName_Result;
	}

	public void setRegistByPhoneAndPass_NoName_Result(Result<AccountSimple> registByPhoneAndPass_NoName_Result) {
		this.registByPhoneAndPass_NoName_Result = registByPhoneAndPass_NoName_Result;
	}

	public Result<AccountSimple> getRegistByPhoneAndPass_Result() {
		return registByPhoneAndPass_Result;
	}

	public Result<AccountSimple> getRegistByEmailAndPass_Result() {
		return registByEmailAndPass_Result;
	}

	public void setRegistByEmailAndPass_Result(Result<AccountSimple> registByEmailAndPass_Result) {
		this.registByEmailAndPass_Result = registByEmailAndPass_Result;
	}

	public Result<AccountSimple> getRegistByEmailAndPass_NoName_Result() {
		return registByEmailAndPass_NoName_Result;
	}

	public void setRegistByEmailAndPass_NoName_Result(Result<AccountSimple> registByEmailAndPass_NoName_Result) {
		this.registByEmailAndPass_NoName_Result = registByEmailAndPass_NoName_Result;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRegistByPhoneAndPass_Result(Result<AccountSimple> registByPhoneAndPass_Result) {
		this.registByPhoneAndPass_Result = registByPhoneAndPass_Result;
	}
}
