package cn.zlb.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TOrder entity. @author MyEclipse Persistence Tools
 */

public class TOrder implements java.io.Serializable {

	// Fields

	private String pkOrdId;
	private TCustomer TCustomer;
	private Timestamp ordTime;
	private String ordTotal="0";
	private byte ordStatus;
	private String ordAddress;
	private Set TItemorders = new HashSet(0);
	private TStore TStore;
	// Constructors

	/** default constructor */
	public TOrder() {
	}

	/** full constructor */
	public TOrder(TCustomer TCustomer, Timestamp ordTime, String ordTotal, byte ordStatus, String ordAddress,
			Set TItemorders) {
		this.TCustomer = TCustomer;
		this.ordTime = ordTime;
		this.ordTotal = ordTotal;
		this.ordStatus = ordStatus;
		this.ordAddress = ordAddress;
		this.TItemorders = TItemorders;
	}

	// Property accessors

	public String getPkOrdId() {
		return this.pkOrdId;
	}

	public void setPkOrdId(String pkOrdId) {
		this.pkOrdId = pkOrdId;
	}

	public TCustomer getTCustomer() {
		return this.TCustomer;
	}

	public void setTCustomer(TCustomer TCustomer) {
		this.TCustomer = TCustomer;
	}

	public Timestamp getOrdTime() {
		return this.ordTime;
	}

	public void setOrdTime(Timestamp ordTime) {
		this.ordTime = ordTime;
	}

	public String getOrdTotal() {
		return this.ordTotal;
	}

	public void setOrdTotal(String ordTotal) {
		this.ordTotal = ordTotal;
	}

	public byte getOrdStatus() {
		return this.ordStatus;
	}

	public void setOrdStatus(byte ordStatus) {
		this.ordStatus = ordStatus;
	}

	public String getOrdAddress() {
		return this.ordAddress;
	}

	public void setOrdAddress(String ordAddress) {
		this.ordAddress = ordAddress;
	}

	public Set getTItemorders() {
		return this.TItemorders;
	}

	public void setTItemorders(Set TItemorders) {
		this.TItemorders = TItemorders;
	}

	public TStore getTStore() {
		return TStore;
	}

	public void setTStore(TStore tStore) {
		TStore = tStore;
	}

}