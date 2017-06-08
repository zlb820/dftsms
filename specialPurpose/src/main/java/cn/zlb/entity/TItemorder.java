package cn.zlb.entity;

/**
 * TItemorder entity. @author MyEclipse Persistence Tools
 */

public class TItemorder implements java.io.Serializable {

	// Fields

	private String pkIteId;
	private TGoods TGoods;
	private TOrder TOrder;
	private Integer iteQuantity;
	private String iteSubtotal;
	private String gooCurrprice;
	private String gooImageL;

	// Constructors

	/** default constructor */
	public TItemorder() {
	}

	/** full constructor */
	public TItemorder(TGoods TGoods, TOrder TOrder, Integer iteQuantity, String iteSubtotal, String gooCurrprice,
			String gooImageL) {
		this.TGoods = TGoods;
		this.TOrder = TOrder;
		this.iteQuantity = iteQuantity;
		this.iteSubtotal = iteSubtotal;
		this.gooCurrprice = gooCurrprice;
		this.gooImageL = gooImageL;
	}

	// Property accessors

	public String getPkIteId() {
		return this.pkIteId;
	}

	public void setPkIteId(String pkIteId) {
		this.pkIteId = pkIteId;
	}

	public TGoods getTGoods() {
		return this.TGoods;
	}

	public void setTGoods(TGoods TGoods) {
		this.TGoods = TGoods;
	}

	public TOrder getTOrder() {
		return this.TOrder;
	}

	public void setTOrder(TOrder TOrder) {
		this.TOrder = TOrder;
	}

	public Integer getIteQuantity() {
		return this.iteQuantity;
	}

	public void setIteQuantity(Integer iteQuantity) {
		this.iteQuantity = iteQuantity;
	}

	public String getIteSubtotal() {
		return this.iteSubtotal;
	}

	public void setIteSubtotal(String iteSubtotal) {
		this.iteSubtotal = iteSubtotal;
	}

	public String getGooCurrprice() {
		return this.gooCurrprice;
	}

	public void setGooCurrprice(String gooCurrprice) {
		this.gooCurrprice = gooCurrprice;
	}

	public String getGooImageL() {
		return this.gooImageL;
	}

	public void setGooImageL(String gooImageL) {
		this.gooImageL = gooImageL;
	}

	@Override
	public String toString() {
		return "TItemorder [pkIteId=" + pkIteId + ", iteQuantity=" + iteQuantity + ", iteSubtotal=" + iteSubtotal
				+ ", gooCurrprice=" + gooCurrprice + "]";
	}

	
	
}