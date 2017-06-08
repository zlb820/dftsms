package org.common.FDBK.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "province")
public class Province_addr {
	public Province_addr() {
	}

	@Id
	@Column(name = "PID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	int id;
	@Column(name = "Pcode")
	String p_code;
	@Column(name = "Pname")
	String p_name;

	public int getId() {
		return id;
	}

	public String getP_code() {
		return p_code;
	}

	public String getP_name() {
		return p_name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setP_code(String p_code) {
		this.p_code = p_code;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
}
