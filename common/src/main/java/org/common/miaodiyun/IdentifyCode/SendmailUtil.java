package org.common.miaodiyun.IdentifyCode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.common.RBAC.domain.SysInfo;
import org.common.util.ReadPropertyOf;

public class SendmailUtil {

	private static String createActiveLink(String emAddress, String accountID, String activeCode) {
		StringBuffer link = new StringBuffer();
		SysInfo sysInfo = ReadPropertyOf.loadPropertiesOfSys();
//		link.append("http://" + sysInfo.getServerAddress() + ":" + sysInfo.getServerPort() + "/dftsms-web/IdentifyCode/VerifyEmailActiveLink");
		link.append("http://" + sysInfo.getServerAddress() + "/dftsms-web/IdentifyCode/VerifyEmailActiveLink");
		link.append("?email=" + emAddress);
		link.append("&accountID=" + accountID);
		link.append("&activeCode=" + activeCode);
		return link.toString();
	}

	private static String createEmailMSG(String emAddress, String accountID, String accountName, String activeCode) {
		StringBuffer sf = new StringBuffer();
		sf.append("亲爱的" + accountName + "，您好：\n");
		sf.append("\t欢迎您注册地方特色美食综合服务平台，请点击下面的链接完成邮箱绑定：\n");
		// 激活链接生成
		sf.append(createActiveLink(emAddress, accountID, activeCode));
		sf.append("\n注意：链接有效期为1小时,如果链接失效，请重新索取。\n");
		sf.append("如果您并未发过此请求，则可能是因为其他用户在尝试重设密码时误输入了您的电子邮件地址而使您收到这封邮件,");
		sf.append("那么您完全可以忽略此邮件，无需进一步采取任何操作。\n");
		sf.append("感谢您的使用！\n\n\n");
		sf.append("地方特色美食综合服务平台服务中心\n");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		sf.append(sdf.format(new Date()));
		return sf.toString();
	}

	public static Boolean sendActiveEmail(String emAddress, String accountID, String accountName, String activeCode) {
		try {
			Properties properties = new Properties();
			properties.put("mail.transport.protocol", "smtp");// 连接协议
			properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
			properties.put("mail.smtp.port", 465);// 端口号
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接
															// ---一般都使用
			properties.put("mail.debug", "true");// 设置是否显示debug信息 true
													// 会在控制台显示相关信息
			// 得到回话对象
			Session session = Session.getInstance(properties);
			// 获取邮件对象
			Message message = new MimeMessage(session);
			// 设置发件人邮箱地址
			message.setFrom(new InternetAddress("dftsms_fxb@qq.com"));
			// 设置收件人地址
			message.setRecipients(RecipientType.TO, new InternetAddress[] { new InternetAddress(emAddress) });
			// 设置邮件标题
			message.setSubject("地方特色美食综合服务平台激活邮件");
			// 设置邮件内容
			message.setText(createEmailMSG(emAddress, accountID, accountName, activeCode));
			// 得到邮差对象
			Transport transport = session.getTransport();
			// 连接自己的邮箱账户
			transport.connect("dftsms_fxb@qq.com", "hfyfcssxtnpagiae");// 密码为刚才得到的授权码
			// 发送邮件
			transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}