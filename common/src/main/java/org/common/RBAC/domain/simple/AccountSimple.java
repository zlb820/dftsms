package org.common.RBAC.domain.simple;

import java.util.Date;

public class AccountSimple {
	private String id;
	private String name;
	private String phone;
	private int sex;
	private String email;
	private Date registDate;
	private int accountStatu;
	private int eMailStatu;
	private String roleID;
	private String picURL;
	
	public int geteMailStatu() {
		return eMailStatu;
	}

	public void seteMailStatu(int eMailStatu) {
		this.eMailStatu = eMailStatu;
	}

	public String getPicURL() {
		return picURL;
	}

	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public int getAccountStatu() {
		return accountStatu;
	}

	public void setAccountStatu(int accountStatu) {
		this.accountStatu = accountStatu;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
}
