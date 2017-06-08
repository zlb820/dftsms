package org.common.FDBK.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.common.RBAC.domain.Pictures;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_business")
public class Business {
	public Business() {
	}
	/**
	 * 账号ID
	 */
	@Id
	@Column(name = "pk_bus_id")
	// 该主键生成器名为uuid，使用Hibernate的uuid策略，
	@GenericGenerator(name = "uuid", strategy = "uuid")
	// 指定使用fk_hilo主键生成器
	@GeneratedValue(generator = "uuid")
	private String id;
	@Column(name = "bus_name")
	private String name;
	@Column(name = "bus_pass")
	private String pass;
	@Column(name = "bus_email")
	private String email;
	@Column(name = "bus_phone")
	private String phone;
	@Column(name = "bus_identity")
	private String identity;
	@Column(name = "bus_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bus_time;
	/**
	 * 保存与图片的关联，这部分暂时还未设计好 级联操作为All，如果账户删除则图片也相应应该移除
	 */
	@OneToOne(targetEntity = Pictures.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_picture", referencedColumnName = "pk_pic_id", unique = true)
	Pictures picture;
	@Column(name = "bus_status")
	private int status;
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public Date getBus_time() {
		return bus_time;
	}
	public void setBus_time(Date bus_time) {
		this.bus_time = bus_time;
	}
	public Pictures getPicture() {
		return picture;
	}
	public void setPicture(Pictures picture) {
		this.picture = picture;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
