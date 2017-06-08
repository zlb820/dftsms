package org.common.FDBK.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City_addr {
	public City_addr() {
	}

	@Id
	@Column(name = "CID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	int id;
	@Column(name="Ccode")
	String c_code;
	@Column(name="Cname")
	String c_name;
	@Column(name="ProvinceCode")
	String aboutP_code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getC_code() {
		return c_code;
	}
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getAboutP_code() {
		return aboutP_code;
	}
	public void setAboutP_code(String aboutP_code) {
		this.aboutP_code = aboutP_code;
	}
}
