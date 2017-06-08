package cn.zlb.entity;

/**
 * TLogininfo entity. @author MyEclipse Persistence Tools
 */

public class TLogininfo implements java.io.Serializable {

	// Fields

	private TLogininfoId id;
	private TCustomer TCustomer;

	// Constructors

	/** default constructor */
	public TLogininfo() {
	}

	/** minimal constructor */
	public TLogininfo(TLogininfoId id) {
		this.id = id;
	}

	/** full constructor */
	public TLogininfo(TLogininfoId id, TCustomer TCustomer) {
		this.id = id;
		this.TCustomer = TCustomer;
	}

	// Property accessors

	public TLogininfoId getId() {
		return this.id;
	}

	public void setId(TLogininfoId id) {
		this.id = id;
	}

	public TCustomer getTCustomer() {
		return this.TCustomer;
	}

	public void setTCustomer(TCustomer TCustomer) {
		this.TCustomer = TCustomer;
	}

}