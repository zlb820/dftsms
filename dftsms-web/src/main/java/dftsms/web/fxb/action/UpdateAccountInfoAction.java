package dftsms.web.fxb.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.common.RBAC.domain.simple.AccountSimple;
import org.common.util.Result;
import org.common.util.Sex;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import dftsms.web.fxb.action.base.AccountManagerBaseAction;
import dftsms.web.fxb.util.UtilOfFxb;

public class UpdateAccountInfoAction extends AccountManagerBaseAction {
	private static final long serialVersionUID = 1L;
	Result<AccountSimple> updatePassByOldPassResult;
	Result<AccountSimple> updatePassByIdentifyCodeResult;
	Result<AccountSimple> updateSexByID;
	Result<AccountSimple> updatePhoneByID;
	Result<AccountSimple> updateNameByID;
	Result<AccountSimple> updatePicByID;

	String accountID;
	String newPassword;
	String newPasswordSecondInput;
	String oldPassword;
	String identifyCode;
	int sex;
	String accountName;
	File picture;
	String pictureContentType;
	String pictureFileName;
	String savePath;

	/**
	 * 修改用户头像
	 * 
	 * @return
	 */
	public String updatePicByID() {
		// 创建前期检查工具类
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		updatePicByID = utilOfCheck.checkParameterIsNull_returnResult(accountID);
		if ("xxxx01".equals(updatePicByID.getCode())) {// 参数检查通过
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				String imgLocalSaveURL = request.getSession().getServletContext()
						.getRealPath(getSavePath() + "/" + getPictureFileName());
				String imgURL = request.getSession().getServletContext().getContextPath() + getSavePath() + "/"
						+ getPictureFileName();
				FileOutputStream fos = new FileOutputStream(imgLocalSaveURL);
				FileInputStream fis = new FileInputStream(getPicture());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
				updatePicByID = accountManager.updateAccountPICByID(accountID, imgURL);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Action.SUCCESS;
	}

	/**
	 * 更改用户名
	 * 
	 * @return
	 */
	public String updateNameByID() {
		// 创建前期检查工具类
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		updateNameByID = utilOfCheck.checkParameterIsNull_returnResult(accountID, accountName);
		if ("xxxx01".equals(updateNameByID.getCode())) {// 参数检查通过
			updateNameByID = accountManager.updateAccountNameByID(accountID, accountName);
		}
		return Action.SUCCESS;
	}

	/**
	 * 根据ID修改账户手机号
	 * 
	 * @return
	 */
	public String updatePhoneByID() {
		ActionContext atx = ActionContext.getContext();
		Map<String, Object> session = atx.getSession();
		String save_icPhone = (String) session.get("icPhone");
		String save_identifyCode = (String) session.get("identifyCode");
		// 创建前期检查工具类
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		updatePhoneByID = utilOfCheck.checkParameterIsNull_returnResult(accountID, identifyCode);
		if ("xxxx01".equals(updatePhoneByID.getCode())) {// 参数检查通过
			updatePhoneByID = utilOfCheck.checkNeededElementIsNull_returnResult(save_icPhone, save_identifyCode);
			if ("xxxx04".equals(updatePhoneByID.getCode())) {// 必要元素检查成功
				if (save_identifyCode.equals(identifyCode)) {
					updatePhoneByID = accountManager.updatePhonByID(accountID, save_icPhone);
				} else {
					updatePhoneByID = new Result<>();
					updatePhoneByID.setCode("000327");
					updatePhoneByID.setMessage("修改手机号失败：未通过验证码验证!");
				}
			}
		}
		return Action.SUCCESS;
	}

	/**
	 * 根据ID修改账户性别
	 * 
	 * @return
	 */
	public String updateSexByID() {
		// 创建前期检查工具类
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		updateSexByID = utilOfCheck.checkParameterIsNull_returnResult(accountID);
		if ("xxxx01".equals(updateSexByID.getCode())) {// 参数检查通过
			if (1 == sex) {
				updateSexByID = accountManager.updateSexByID(accountID, Sex.女);
			} else {
				updateSexByID = accountManager.updateSexByID(accountID, Sex.男);
			}
		}
		return Action.SUCCESS;
	}

	/**
	 * 使用手机与验证码来更新密码
	 * 
	 * @return
	 */
	public String updatePassByIdentifyCode() {
		ActionContext atx = ActionContext.getContext();
		Map<String, Object> session = atx.getSession();
		// 获取Session中保存的时间戳和验证码
		String save_identifyCode = (String) session.get("identifyCode");
		String save_icTimestamp = (String) session.get("icTimestamp");
		// 创建前期检查工具类
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		updatePassByIdentifyCodeResult = utilOfCheck.checkParameterIsNull_returnResult(accountID, identifyCode,
				newPassword, newPasswordSecondInput);
		if ("xxxx01".equals(updatePassByIdentifyCodeResult.getCode())) {// 参数检查通过
			updatePassByIdentifyCodeResult = utilOfCheck.checkNeededElementIsNull_returnResult(save_icTimestamp,
					save_identifyCode);
			if ("xxxx04".equals(updatePassByIdentifyCodeResult.getCode())) {// 必要元素检查通过
				Long save_icTimestampOfLong = Long.valueOf(save_icTimestamp);
				if (!newPassword.equals(newPasswordSecondInput)) {
					Result<AccountSimple> temp = new Result<>();
					temp.setCode("000325");
					temp.setMessage("修改密码失败：两次输入新密码错误!");
					updatePassByIdentifyCodeResult = temp;
					return Action.SUCCESS;
				}
				if (null == identifyCode || 0 == identifyCode.length()) {
					updatePassByIdentifyCodeResult = new Result<AccountSimple>();
					updatePassByIdentifyCodeResult.setCode("000909");
					updatePassByIdentifyCodeResult.setMessage("接收到验证码为空，无法验证!");
					return Action.SUCCESS;
				}
				long TimesTimestampOfNow = UtilOfFxb.getTimestampOfLong();
				if ((TimesTimestampOfNow - save_icTimestampOfLong) > 300) {
					updatePassByIdentifyCodeResult = new Result<AccountSimple>();
					updatePassByIdentifyCodeResult.setCode("000908");
					updatePassByIdentifyCodeResult.setMessage("验证码已过期!");
					return Action.SUCCESS;
				}
				if (save_identifyCode.equals(identifyCode)) {// 验证码校验成功
					updatePassByIdentifyCodeResult = accountManager.updatePassByID(accountID, newPassword);
					if ("000301".equals(updatePassByIdentifyCodeResult.getCode())) {
						// 移除校验码时保存数据
						session.remove("IdentifyCodeFlag");
						session.remove("icPhone");
						session.remove("icTimestamp");
						session.remove("identifyCode");
					}
				} else {
					updatePassByIdentifyCodeResult = new Result<AccountSimple>();
					updatePassByIdentifyCodeResult.setCode("000907");
					updatePassByIdentifyCodeResult.setMessage("验证码错误!");
				}
			}
		}
		return Action.SUCCESS;
	}

	/**
	 * 通过原有密码以及ID，修改账户密码
	 * 
	 * @return
	 */
	public String updatePassByOldPass() {
		// 创建前期检查工具类
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		updatePassByOldPassResult = utilOfCheck.checkParameterIsNull_returnResult(accountID, oldPassword, newPassword,
				newPasswordSecondInput);
		if ("xxxx01".equals(updatePassByOldPassResult.getCode())) {// 参数检查通过
			if (!newPassword.equals(newPasswordSecondInput)) {
				Result<AccountSimple> temp = new Result<>();
				temp.setCode("000325");
				temp.setMessage("修改密码失败：两次输入新密码错误!");
				updatePassByOldPassResult = temp;
				return Action.SUCCESS;
			}
			updatePassByOldPassResult = accountManager.updatePassByID(accountID, newPassword, oldPassword);
		}
		return Action.SUCCESS;
	}

	/************************* getter和setter方法 *************************/

	public Result<AccountSimple> getUpdatePassResult() {
		return updatePassByOldPassResult;
	}

	public Result<AccountSimple> getUpdatePassByOldPassResult() {
		return updatePassByOldPassResult;
	}

	public void setUpdatePassByOldPassResult(Result<AccountSimple> updatePassByOldPassResult) {
		this.updatePassByOldPassResult = updatePassByOldPassResult;
	}

	public Result<AccountSimple> getUpdatePassByIdentifyCodeResult() {
		return updatePassByIdentifyCodeResult;
	}

	public void setUpdatePassByIdentifyCodeResult(Result<AccountSimple> updatePassByIdentifyCodeResult) {
		this.updatePassByIdentifyCodeResult = updatePassByIdentifyCodeResult;
	}

	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}

	public void setUpdatePassResult(Result<AccountSimple> updatePassResult) {
		this.updatePassByOldPassResult = updatePassResult;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordSecondInput() {
		return newPasswordSecondInput;
	}

	public void setNewPasswordSecondInput(String newPasswordSecondInput) {
		this.newPasswordSecondInput = newPasswordSecondInput;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Result<AccountSimple> getUpdateSexByID() {
		return updateSexByID;
	}

	public void setUpdateSexByID(Result<AccountSimple> updateSexByID) {
		this.updateSexByID = updateSexByID;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Result<AccountSimple> getUpdatePhoneByID() {
		return updatePhoneByID;
	}

	public void setUpdatePhoneByID(Result<AccountSimple> updatePhoneByID) {
		this.updatePhoneByID = updatePhoneByID;
	}

	public Result<AccountSimple> getUpdateNameByID() {
		return updateNameByID;
	}

	public void setUpdateNameByID(Result<AccountSimple> updateNameByID) {
		this.updateNameByID = updateNameByID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Result<AccountSimple> getUpdatePicByID() {
		return updatePicByID;
	}

	public void setUpdatePicByID(Result<AccountSimple> updatePicByID) {
		this.updatePicByID = updatePicByID;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
}
