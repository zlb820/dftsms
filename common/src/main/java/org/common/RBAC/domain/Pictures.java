package org.common.RBAC.domain;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Entity
@Table(name = "t_pictures")
public class Pictures {
	// 无参数的构造器
	public Pictures() {
	}

	@Id
	@Column(name = "pk_pic_id")
	// 该主键生成器名为uuid，使用Hibernate的uuid策略，
	@GenericGenerator(name = "uuid", strategy = "uuid")
	// 指定使用fk_hilo主键生成器
	@GeneratedValue(generator = "uuid")
	private String id;
	String pic_url;
	String pic_des;
	String pic_attr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getPic_des() {
		return pic_des;
	}

	public void setPic_des(String pic_des) {
		this.pic_des = pic_des;
	}

	public String getPic_attr() {
		return pic_attr;
	}

	public void setPic_attr(String pic_attr) {
		this.pic_attr = pic_attr;
	}
}