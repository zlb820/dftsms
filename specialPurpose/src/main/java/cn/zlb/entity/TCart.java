package cn.zlb.entity;

/**
 * TCart entity. @author MyEclipse Persistence Tools
 */

public class TCart implements java.io.Serializable {

	// Fields

	private String pkCarId;
	private TGoods TGoods;
	private TCustomer TCustomer;
	private Integer carQuantity;

	// Constructors

	/** default constructor */
	public TCart() {
	}

	/** full constructor */
	public TCart(TGoods TGoods, TCustomer TCustomer, Integer carQuantity) {
		this.TGoods = TGoods;
		this.TCustomer = TCustomer;
		this.carQuantity = carQuantity;
	}

	// Property accessors

	public String getPkCarId() {
		return this.pkCarId;
	}

	public void setPkCarId(String pkCarId) {
		this.pkCarId = pkCarId;
	}

	public TGoods getTGoods() {
		return this.TGoods;
	}

	public void setTGoods(TGoods TGoods) {
		this.TGoods = TGoods;
	}

	public TCustomer getTCustomer() {
		return this.TCustomer;
	}

	public void setTCustomer(TCustomer TCustomer) {
		this.TCustomer = TCustomer;
	}

	public Integer getCarQuantity() {
		return this.carQuantity;
	}

	public void setCarQuantity(Integer carQuantity) {
		this.carQuantity = carQuantity;
	}

}