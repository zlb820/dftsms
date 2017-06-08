package org.common.RBAC.domain;

import javax.persistence.*;
/**
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Entity
@Table(name = "t_logininfo_ status")
public class LoginInfoStatus {
	// 无参数的构造器
	public LoginInfoStatus() {
	}

	@Column(name = "pk_login_sta_id")
	private String id;
	@Column(name = "login_sta_des")
	String staDes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStaDes() {
		return staDes;
	}

	public void setStaDes(String staDes) {
		this.staDes = staDes;
	}
}
