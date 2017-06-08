package com.miaodiyun;

import com.miaodiyun.util.Config;
import com.miaodiyun.util.HttpUtil;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS {
	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = Config.ACCOUNT_SID;
	// private static String to = "18230207737";
	// private static String smsContent =
	// "【地方特色美食平台】您的验证码为1234，请于5分钟内正确输入，如非本人操作，请忽略此短信。";

	/**
	 * 验证码通知短信
	 */
	public static String execute(String smsContent, String PhonNum) {
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + "&to=" + PhonNum + "&smsContent=" + smsContent
				+ HttpUtil.createCommonParam();

		// 提交请求
		String result = HttpUtil.post(url, body);
		// System.out.println("result:" + System.lineSeparator() + result);
		return result;
	}
}
