package cn.zlb.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TRole entity. @author MyEclipse Persistence Tools
 */

public class TRole implements java.io.Serializable {

	// Fields

	private String pkRoleId;
	private String rolDescribe;
	private Set TPowers = new HashSet(0);
	private Set TCustomers = new HashSet(0);

	// Constructors

	/** default constructor */
	public TRole() {
	}

	/** minimal constructor */
	public TRole(String rolDescribe) {
		this.rolDescribe = rolDescribe;
	}

	/** full constructor */
	public TRole(String rolDescribe, Set TPowers, Set TCustomers) {
		this.rolDescribe = rolDescribe;
		this.TPowers = TPowers;
		this.TCustomers = TCustomers;
	}

	// Property accessors

	public String getPkRoleId() {
		return this.pkRoleId;
	}

	public void setPkRoleId(String pkRoleId) {
		this.pkRoleId = pkRoleId;
	}

	public String getRolDescribe() {
		return this.rolDescribe;
	}

	public void setRolDescribe(String rolDescribe) {
		this.rolDescribe = rolDescribe;
	}

	public Set getTPowers() {
		return this.TPowers;
	}

	public void setTPowers(Set TPowers) {
		this.TPowers = TPowers;
	}

	public Set getTCustomers() {
		return this.TCustomers;
	}

	public void setTCustomers(Set TCustomers) {
		this.TCustomers = TCustomers;
	}

}