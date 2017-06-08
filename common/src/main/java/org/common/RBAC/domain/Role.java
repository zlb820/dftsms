package org.common.RBAC.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Entity
@Table(name = "t_role")
public class Role {
	// 无参数的构造器
	public Role() {
	}

	// TODO 由于在Account中使用HashSet保存Role对象，所以需要重写hashCode和equals两个方法
	@Id
	@Column(name = "pk_role_id")
	// 该主键生成器名为uuid，使用Hibernate的uuid策略，
	@GenericGenerator(name = "uuid", strategy = "uuid")
	// 指定使用fk_hilo主键生成器
	@GeneratedValue(generator = "uuid")
	private String id;
	String rol_describe;

	@ManyToMany(targetEntity = Power.class)
	@JoinTable(name = "t_role_power", 
	joinColumns = @JoinColumn(name = "fk_role", referencedColumnName = "pk_role_id"),
	inverseJoinColumns = @JoinColumn(name = "fk_power", referencedColumnName = "pk_power_id"))
	Set<Power> powers = new HashSet<>();

	public String getId() {
		return id;
	}

	public Set<Power> getPowers() {
		return powers;
	}

	public void setPowers(Set<Power> powers) {
		this.powers = powers;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRol_describe() {
		return rol_describe;
	}

	public void setRol_describe(String rol_describe) {
		this.rol_describe = rol_describe;
	}
}
