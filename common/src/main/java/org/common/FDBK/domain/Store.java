package org.common.FDBK.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.common.RBAC.domain.Pictures;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_store")
public class Store {
	// 无参数的构造器
	public Store() {
	}

	/**
	 * 账号ID
	 */
	@Id
	@Column(name = "fk_sto_id")
	// 该主键生成器名为uuid，使用Hibernate的uuid策略，
	@GenericGenerator(name = "uuid", strategy = "uuid")
	// 指定使用fk_hilo主键生成器
	@GeneratedValue(generator = "uuid")
	private String id;
	@Column(name = "sto_name")
	String name;
	@Column(name = "sto_status")
	String status;
	@Column(name = "sto_score")
	String score;
	@OneToOne(targetEntity = Pictures.class)
	@JoinColumn(name = "fk_picture", referencedColumnName = "pk_pic_id", unique = true)
	Pictures picture;
	@OneToOne(targetEntity = Address.class)
	@JoinColumn(name = "fk_address", referencedColumnName = "pk_add_id", unique = true)
	Address address;
	@OneToOne(targetEntity = Goods.class)
	@JoinColumn(name = "fk_goods", referencedColumnName = "pk_goo_id", unique = true)
	Goods goods;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Pictures getPicture() {
		return picture;
	}

	public void setPicture(Pictures picture) {
		this.picture = picture;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
