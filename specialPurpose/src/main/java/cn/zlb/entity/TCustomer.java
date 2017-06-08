package cn.zlb.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TCustomer entity. @author MyEclipse Persistence Tools
 */

public class TCustomer implements java.io.Serializable {

	// Fields

	private String pkCusId;
	private TPictures TPictures;
	private byte[] cusName;
	private String cusPass;
	private Short cusSex;
	private String cusEmail;
	private String cusPhone;
	private Timestamp cusTime;
	private Short cusStatus;
	private Set TComments = new HashSet(0);
	private Set TLogininfos = new HashSet(0);
	private Set TOrders = new HashSet(0);
	private Set TRoles = new HashSet(0);
	private Set TCarts = new HashSet(0);

	// Constructors

	/** default constructor */
	public TCustomer() {
	}

	/** minimal constructor */
	public TCustomer(TPictures TPictures, byte[] cusName, String cusPass, Short cusSex, String cusEmail,
			String cusPhone, Timestamp cusTime, Short cusStatus) {
		this.TPictures = TPictures;
		this.cusName = cusName;
		this.cusPass = cusPass;
		this.cusSex = cusSex;
		this.cusEmail = cusEmail;
		this.cusPhone = cusPhone;
		this.cusTime = cusTime;
		this.cusStatus = cusStatus;
	}

	/** full constructor */
	public TCustomer(TPictures TPictures, byte[] cusName, String cusPass, Short cusSex, String cusEmail,
			String cusPhone, Timestamp cusTime, Short cusStatus, Set TComments, Set TLogininfos, Set TOrders,
			Set TRoles, Set TCarts) {
		this.TPictures = TPictures;
		this.cusName = cusName;
		this.cusPass = cusPass;
		this.cusSex = cusSex;
		this.cusEmail = cusEmail;
		this.cusPhone = cusPhone;
		this.cusTime = cusTime;
		this.cusStatus = cusStatus;
		this.TComments = TComments;
		this.TLogininfos = TLogininfos;
		this.TOrders = TOrders;
		this.TRoles = TRoles;
		this.TCarts = TCarts;
	}

	// Property accessors

	public String getPkCusId() {
		return this.pkCusId;
	}

	public void setPkCusId(String pkCusId) {
		this.pkCusId = pkCusId;
	}

	public TPictures getTPictures() {
		return this.TPictures;
	}

	public void setTPictures(TPictures TPictures) {
		this.TPictures = TPictures;
	}

	public byte[] getCusName() {
		return this.cusName;
	}

	public void setCusName(byte[] cusName) {
		this.cusName = cusName;
	}

	public String getCusPass() {
		return this.cusPass;
	}

	public void setCusPass(String cusPass) {
		this.cusPass = cusPass;
	}

	public Short getCusSex() {
		return this.cusSex;
	}

	public void setCusSex(Short cusSex) {
		this.cusSex = cusSex;
	}

	public String getCusEmail() {
		return this.cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusPhone() {
		return this.cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public Timestamp getCusTime() {
		return this.cusTime;
	}

	public void setCusTime(Timestamp cusTime) {
		this.cusTime = cusTime;
	}

	public Short getCusStatus() {
		return this.cusStatus;
	}

	public void setCusStatus(Short cusStatus) {
		this.cusStatus = cusStatus;
	}

	public Set getTComments() {
		return this.TComments;
	}

	public void setTComments(Set TComments) {
		this.TComments = TComments;
	}

	public Set getTLogininfos() {
		return this.TLogininfos;
	}

	public void setTLogininfos(Set TLogininfos) {
		this.TLogininfos = TLogininfos;
	}

	public Set getTOrders() {
		return this.TOrders;
	}

	public void setTOrders(Set TOrders) {
		this.TOrders = TOrders;
	}

	public Set getTRoles() {
		return this.TRoles;
	}

	public void setTRoles(Set TRoles) {
		this.TRoles = TRoles;
	}

	public Set getTCarts() {
		return this.TCarts;
	}

	public void setTCarts(Set TCarts) {
		this.TCarts = TCarts;
	}

}