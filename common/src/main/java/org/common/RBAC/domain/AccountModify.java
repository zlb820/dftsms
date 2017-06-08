package org.common.RBAC.domain;

import java.util.Date;

import javax.persistence.*;
/**
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Entity
@Table(name = "account_modify")
public class AccountModify {
	// 无参数的构造器
	public AccountModify() {
	}

	@Id
	@Column(name = "pk_accountMod_id")
	private String id;
	String mod_id;
	String mod_name;
	String mod_phone;
	String mod_email;
	@Temporal(TemporalType.TIMESTAMP)
	Date mod_reg_time;
	String mod_role;
	@Temporal(TemporalType.TIMESTAMP)
	String mod_time;
	String mod_ip;
	String mod_device;

	/********************** setter&getter方法 ************************/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMod_id() {
		return mod_id;
	}

	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}

	public String getMod_name() {
		return mod_name;
	}

	public void setMod_name(String mod_name) {
		this.mod_name = mod_name;
	}

	public String getMod_phone() {
		return mod_phone;
	}

	public void setMod_phone(String mod_phone) {
		this.mod_phone = mod_phone;
	}

	public String getMod_email() {
		return mod_email;
	}

	public void setMod_email(String mod_email) {
		this.mod_email = mod_email;
	}

	public Date getMod_reg_time() {
		return mod_reg_time;
	}

	public void setMod_reg_time(Date mod_reg_time) {
		this.mod_reg_time = mod_reg_time;
	}

	public String getMod_role() {
		return mod_role;
	}

	public void setMod_role(String mod_role) {
		this.mod_role = mod_role;
	}

	public String getMod_time() {
		return mod_time;
	}

	public void setMod_time(String mod_time) {
		this.mod_time = mod_time;
	}

	public String getMod_ip() {
		return mod_ip;
	}

	public void setMod_ip(String mod_ip) {
		this.mod_ip = mod_ip;
	}

	public String getMod_device() {
		return mod_device;
	}

	public void setMod_device(String mod_device) {
		this.mod_device = mod_device;
	}

}
