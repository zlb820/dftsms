package org.common.RBAC.domain;

import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Entity
@Table(name = "t_customer")
public class Account {
	// 无参数的构造器
	public Account() {
	}

	/**
	 * 账号ID
	 */
	@Id
	@Column(name = "pk_cus_id")
	// 该主键生成器名为uuid，使用Hibernate的uuid策略，
	@GenericGenerator(name = "uuid", strategy = "uuid")
	// 指定使用fk_hilo主键生成器
	@GeneratedValue(generator = "uuid")
	private String id;
	/**
	 * 账号名称
	 */
	@Column(name = "cus_name")
	String name;
	/**
	 * 账号密码
	 */
	@Column(name = "cus_pass")
	String pass;
	/**
	 * 性别
	 */
	@Column(name = "cus_sex")
	int sex;
	/**
	 * 账号绑定邮箱
	 */
	@Column(name = "cus_email")
	String email;
	/**
	 * 账号绑定手机号
	 */
	@Column(name = "cus_phone")
	String phone;
	@Column(name = "cus_pic")
	String cus_pic;
	/**
	 * 账号注册时间
	 */
	@Column(name = "cus_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registTime;
	/**
	 * 账号状态
	 */
	@Column(name = "cus_status")
	private int status;
	/**
	 * 保存与图片的关联，这部分暂时还未设计好 级联操作为All，如果账户删除则图片也相应应该移除
	 */
	@OneToOne(targetEntity = Pictures.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_picture", referencedColumnName = "pk_pic_id", unique = true)
	Pictures picture;
	// TODO 账号持久化类与图片管理还没有完成

	// 定义该Account实体所有关联的Role实体
	@ManyToOne(targetEntity = Role.class, cascade = CascadeType.ALL)
	// 映射连接表为t_customer_role
	@JoinTable(name = "t_customer_role",
			// 定义连接表中名为fk_customer的外键列，该外键列参照当前实体对应表的主键列
			joinColumns = @JoinColumn(name = "fk_customer", referencedColumnName = "pk_cus_id"),
			// 定义连接表中名为fk_role的外键列，
			// 该外键列参照当前实体的关联实体对应表的主键列
			inverseJoinColumns = @JoinColumn(name = "fk_role", referencedColumnName = "pk_role_id"))
	Role roles;
	/**
	 * 邮箱的激活状态
	 */
	@Column(name = "em_status")
	private int em_status;
	/**
	 * 邮箱激活码
	 */
	@Column(name = "em_ActiCode")
	private String em_ActiCode;
	/**
	 * 邮箱注册时间(主要用于验证邮箱注册是否超时)
	 */
	@Column(name = "em_token_exptime")
	private String em_token_exptime;

	/********************** setter&getter方法 ************************/

	public String getId() {
		return id;
	}

	public int getEm_status() {
		return em_status;
	}

	public void setEm_status(int em_status) {
		this.em_status = em_status;
	}

	public Pictures getPicture() {
		return picture;
	}

	public void setPicture(Pictures picture) {
		this.picture = picture;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEm_ActiCode() {
		return em_ActiCode;
	}

	public void setEm_ActiCode(String em_ActiCode) {
		this.em_ActiCode = em_ActiCode;
	}

	public String getEm_token_exptime() {
		return em_token_exptime;
	}

	public void setEm_token_exptime(String em_token_exptime) {
		this.em_token_exptime = em_token_exptime;
	}

	public String getCus_pic() {
		return cus_pic;
	}

	public void setCus_pic(String cus_pic) {
		this.cus_pic = cus_pic;
	}

}
