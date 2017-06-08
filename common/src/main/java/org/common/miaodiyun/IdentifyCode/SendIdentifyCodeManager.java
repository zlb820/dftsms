package org.common.miaodiyun.IdentifyCode;

import org.common.miaodiyun.Entity.DevelopAccountInfo_mdy;
import org.common.miaodiyun.Entity.SMSInfo_mdy;
import org.common.util.ChangToJsonUtil;
import org.common.util.ResultSimple;
import org.common.util.ResultUtil;

import com.miaodiyun.AccountInfo;
import com.miaodiyun.IndustrySMS;

public class SendIdentifyCodeManager {
	/**
	 * 获取到短信的账户信息
	 * 
	 * @return
	 */
	private DevelopAccountInfo_mdy getDevelopInfo() {
		return ChangToJsonUtil.fromJson(AccountInfo.execute(), DevelopAccountInfo_mdy.class);
	}

	private SMSInfo_mdy sedIdentifyCodeInfo(String code,String PhonNum) {
		String result=IndustrySMS.execute(code,PhonNum);
		return ChangToJsonUtil.fromJson(result, SMSInfo_mdy.class);
	}

	/**
	 * 发送验证码<br/>
	 * 要求：验证码的长度不得小于2，并且不得大于6
	 * 
	 * @param code
	 * @return
	 */
	public ResultSimple sendIdentifyCode(String code,String PhonNum) {
		DevelopAccountInfo_mdy developInfo = getDevelopInfo();
		// 检查账户是否异常
		if ("00000".equals(developInfo.getRespCode()) || "0".equals(developInfo.getStatus())) {
			// 检查账户余额是否不足，当前是使用的是赠送服务
			if (Integer.valueOf(developInfo.getGiftBlance()) < 50) {
				return ResultUtil.saveResult("000901", "验证码发送失败：短信账户余额不足!");
			} else {
				// 检查验证码是否异常
				if (null == code || code.length() <= 2 || code.length() > 6) {
					return ResultUtil.saveResult("000906", "验证码发送失败：验证码格式不正确!");
				}
				StringBuffer str = new StringBuffer("【地方特色美食平台】您的验证码为");
				str.append(code);
				str.append("，请于5分钟内正确输入，如非本人操作，请忽略此短信。");
				SMSInfo_mdy result = sedIdentifyCodeInfo(str.toString(),PhonNum);
				if ("00000".equals(result.getRespCode())) {
					return ResultUtil.saveResult("000900", "验证码发送成功!");
				} else if ("00025".equals(result.getRespCode())) {
					return ResultUtil.saveResult("000902", "验证码发送失败：手机号格式不正确!");
				} else if ("00026".equals(result.getRespCode())) {
					return ResultUtil.saveResult("000903", "验证码发送失败：手机号不存在!");
				} else {
					return ResultUtil.saveResult("000905", "验证码发送失败：非预期异常!");
				}
			}

		} else {
			return ResultUtil.saveResult("000904", "验证码发送失败：开发者账户异常!");
		}
	}

}
