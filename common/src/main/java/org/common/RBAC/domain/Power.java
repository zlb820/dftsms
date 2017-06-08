package org.common.RBAC.domain;

import javax.persistence.*;

/**
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Entity
@Table(name="t_power")
public class Power {
	// 无参数的构造器
	public Power() {
	}
	// TODO 由于在Role中使用HashSet保存Power对象，所以需要重写hashCode和equals两个方法
	@Id
	@Column(name="pk_power_id")
	private String id;
	String pow_describe;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPow_describe() {
		return pow_describe;
	}

	public void setPow_describe(String pow_describe) {
		this.pow_describe = pow_describe;
	}
}
