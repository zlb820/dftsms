package org.common.miaodiyun.Entity;

public class SMSInfo_mdy {
	String respCode;// String 必选 请求状态码，取值00000（成功：
					// 此步响应只表明客户的短信请求发送成功，不表明短信通道已经发送短信成功。） 具体可参照《附：返回状态码列表》
	String failCount;// String 必选 表示验证码通知短信发送失败的条数。
	String[] failList;// String 可选 失败列表，包含失败号码、失败原因。
	String smsId;// String 必选 短信标识符。一个由32个字符组成的短信唯一标识符。

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getFailCount() {
		return failCount;
	}

	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}


	public String[] getFailList() {
		return failList;
	}

	public void setFailList(String[] failList) {
		this.failList = failList;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
}
