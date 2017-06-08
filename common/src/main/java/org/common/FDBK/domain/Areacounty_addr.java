package org.common.FDBK.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "areacounty")
public class Areacounty_addr {
	public Areacounty_addr() {
	}

	@Id
	@Column(name = "AID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	int id;
	@Column(name="Acode")
	String a_code;
	@Column(name="Aname")
	String a_name;
	@Column(name="CityCode")
	String aboutC_code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getA_code() {
		return a_code;
	}
	public void setA_code(String a_code) {
		this.a_code = a_code;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public String getAboutC_code() {
		return aboutC_code;
	}
	public void setAboutC_code(String aboutC_code) {
		this.aboutC_code = aboutC_code;
	}
}
