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
@Table(name = "t_goods")
public class Goods {
	@Id
	@Column(name = "pk_goo_id")
	// 该主键生成器名为uuid，使用Hibernate的uuid策略，
	@GenericGenerator(name = "uuid", strategy = "uuid")
	// 指定使用fk_hilo主键生成器
	@GeneratedValue(generator = "uuid")
	private String id;
	@Column(name = "goo_name")
	String name;
	@Column(name = "goo_price")
	String price;
	@Column(name = "goo_currprice")
	String currprice;
	@Column(name = "fk_store")
	String stroe;
	//销售额
	@Column(name="goo_sales")
	int saleVolume;
	@Column(name = "fk_store_category")
	String store_category;
	@OneToOne(targetEntity = Pictures.class)
	@JoinColumn(name = "fk_picture_big", referencedColumnName = "pk_pic_id")
	Pictures picBig;
	@OneToOne(targetEntity = Pictures.class)
	@JoinColumn(name = "fk_picture_small", referencedColumnName = "pk_pic_id")
	Pictures smallBig;
	@Column(name = "goo_extend01")
	String goo_extend01;
	@Column(name = "goo_extend02")
	String goo_extend02;
	@Column(name = "goo_extend03")
	String goo_extend03;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCurrprice() {
		return currprice;
	}
	public void setCurrprice(String currprice) {
		this.currprice = currprice;
	}
	public String getStroe() {
		return stroe;
	}
	public void setStroe(String stroe) {
		this.stroe = stroe;
	}
	public String getStore_category() {
		return store_category;
	}
	public void setStore_category(String store_category) {
		this.store_category = store_category;
	}
	public Pictures getPicBig() {
		return picBig;
	}
	public void setPicBig(Pictures picBig) {
		this.picBig = picBig;
	}
	public Pictures getSmallBig() {
		return smallBig;
	}
	public void setSmallBig(Pictures smallBig) {
		this.smallBig = smallBig;
	}
	public String getGoo_extend01() {
		return goo_extend01;
	}
	public void setGoo_extend01(String goo_extend01) {
		this.goo_extend01 = goo_extend01;
	}
	public String getGoo_extend02() {
		return goo_extend02;
	}
	public void setGoo_extend02(String goo_extend02) {
		this.goo_extend02 = goo_extend02;
	}
	public String getGoo_extend03() {
		return goo_extend03;
	}
	public void setGoo_extend03(String goo_extend03) {
		this.goo_extend03 = goo_extend03;
	}
}
