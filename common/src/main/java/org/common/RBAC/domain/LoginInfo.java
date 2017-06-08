package org.common.RBAC.domain;

import java.util.Date;

import javax.persistence.*;
/**
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Entity
@Table(name = "t_logininfo")
public class LoginInfo {
	// 无参数的构造器
	public LoginInfo() {
	}

	@Column(name = "pk_logininfo_id")
	private String id;
	@Column(name = "cus_id")
	String account_id;
	@Column(name = "cus_name")
	String account_name;
	@Column(name = "cus _ip")
	String IP;
	@Column(name = "cus _device")
	String device;
	@Column(name = "cus _time")
	@Temporal(TemporalType.TIMESTAMP)
	Date registTime;

	@ManyToOne(targetEntity = LoginInfoStatus.class)
	@JoinColumn(name = "fk_login_sta")
	LoginInfoStatus loginInfo_status;

	/********************** setter&getter方法 ************************/
	public LoginInfoStatus getLoginInfo_status() {
		return loginInfo_status;
	}

	public void setLoginInfo_status(LoginInfoStatus loginInfo_status) {
		this.loginInfo_status = loginInfo_status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
}
