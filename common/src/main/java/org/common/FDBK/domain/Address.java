package org.common.FDBK.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Entity
@Table(name = "t_address")
public class Address {
	// 无参数的构造器
	public Address() {
	}

	/**
	 * 账号ID
	 */
	@Id
	@Column(name = "pk_add_id")
	// 该主键生成器名为uuid，使用Hibernate的uuid策略，
	@GenericGenerator(name = "uuid", strategy = "uuid")
	// 指定使用fk_hilo主键生成器
	@GeneratedValue(generator = "uuid")
	private String id;
	@Column(name = "fk_add_area")
	String areacounty;
	@Column(name = "add_concrete")
	String concreteAddress;
	@Column(name = "")
	double add_X;
	@Column(name = "")
	double add_Y;
	@OneToOne(targetEntity = Store.class, mappedBy = "address")
	Store store;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreacounty() {
		return areacounty;
	}
	public void setAreacounty(String areacounty) {
		this.areacounty = areacounty;
	}
	public String getConcreteAddress() {
		return concreteAddress;
	}
	public void setConcreteAddress(String concreteAddress) {
		this.concreteAddress = concreteAddress;
	}
	public double getAdd_X() {
		return add_X;
	}
	public void setAdd_X(double add_X) {
		this.add_X = add_X;
	}
	public double getAdd_Y() {
		return add_Y;
	}
	public void setAdd_Y(double add_Y) {
		this.add_Y = add_Y;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
}
