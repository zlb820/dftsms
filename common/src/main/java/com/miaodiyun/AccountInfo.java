package com.miaodiyun;

import com.miaodiyun.util.Config;
import com.miaodiyun.util.HttpUtil;

/**
 * 获取开发者账号信息接口调用示例
 * 
 * @ClassName: AccountInfo
 * @Description: 获取开发者账号信息接口调用示例
 *
 */
public class AccountInfo {
	private static String operation = "/query/accountInfo";

	private static String accountSid = Config.ACCOUNT_SID;

	/**
	 * 获取开发者账号信息
	 */
	public static String execute() {
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + HttpUtil.createCommonParam();

		// 提交请求
		String result = HttpUtil.post(url, body);
		// System.out.println("result:" + System.lineSeparator() + result);
		return result;
	}

//	@Test
//	public void testOnThis() {
//		String temp = execute();
//		Gson gson = new Gson();
//		DevelopAccountInfo_mdy info = gson.fromJson(temp, DevelopAccountInfo_mdy.class);
//		System.out.println(info.getAccountSid());
//		if (Integer.valueOf(info.getGiftBlance()) > 50) {
//			System.out.println("我进来了");
//		}
//	}
}
