package org.common.test.util;
/**
 * 数据库配置信息对象
 * @author fxb fanxiaobin.fxb@qq.com
 */
public class DataBaseInfo {
	private String url = null;
	private String driver = null;
	private String account = null;
	private String password = null;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
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
}
