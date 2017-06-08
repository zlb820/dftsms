package cn.zlb.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TPower entity. @author MyEclipse Persistence Tools
 */

public class TPower implements java.io.Serializable {

	// Fields

	private String pkPowId;
	private String powDescribe;
	private Set TRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public TPower() {
	}

	/** minimal constructor */
	public TPower(String powDescribe) {
		this.powDescribe = powDescribe;
	}

	/** full constructor */
	public TPower(String powDescribe, Set TRoles) {
		this.powDescribe = powDescribe;
		this.TRoles = TRoles;
	}

	// Property accessors

	public String getPkPowId() {
		return this.pkPowId;
	}

	public void setPkPowId(String pkPowId) {
		this.pkPowId = pkPowId;
	}

	public String getPowDescribe() {
		return this.powDescribe;
	}

	public void setPowDescribe(String powDescribe) {
		this.powDescribe = powDescribe;
	}

	public Set getTRoles() {
		return this.TRoles;
	}

	public void setTRoles(Set TRoles) {
		this.TRoles = TRoles;
	}

}