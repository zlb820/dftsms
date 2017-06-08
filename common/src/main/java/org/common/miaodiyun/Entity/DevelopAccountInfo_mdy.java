package org.common.miaodiyun.Entity;

public class DevelopAccountInfo_mdy {
	String respCode;// 必选 请求状态码，取值00000（成功）。具体可参照《附：返回状态码列表》
	String accountSid;// 必选 开发者账号ID（ACCOUNTSID）。由32个英文字母和阿拉伯数字组成的开发者账号唯一标识符。
	String developerName; // 必选 开发者昵称。字母、数字、"-"、"_"或者中文，2-20位。
	String createTime; // 必选 开发者账号创建时间。
	String updateTime; // 必选 开发者信息的最新更新时间
	String email; // 必选 开发者绑定的邮箱地址
	String mobile; // 必选 开发者绑定的手机号。
	String activationStatus; // 必选 开发者账号状态。0：未激活，1：已激活。
	String status; // 必选 开发者认证状态。0：未认证，1：审核中，2：已审核，3：审核未通过。
	String balance; // 必选 开发者账户基本余额。
	String giftBlance;// 必选 开发者账户赠送余额。

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(String activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getGiftBlance() {
		return giftBlance;
	}

	public void setGiftBlance(String giftBlance) {
		this.giftBlance = giftBlance;
	}
}
