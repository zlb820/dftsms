package cn.zlb.dto;

 

public class TItemorderDto {
	private String pkIteId;
	private Integer iteQuantity;
	private String iteSubtotal;
	private String gooId;
	private String gooCurrprice;
	 
	public String getPkIteId() {
		return pkIteId;
	}
	public void setPkIteId(String pkIteId) {
		this.pkIteId = pkIteId;
	}
	public Integer getIteQuantity() {
		return iteQuantity;
	}
	public void setIteQuantity(Integer iteQuantity) {
		this.iteQuantity = iteQuantity;
	}
	public String getIteSubtotal() {
		return iteSubtotal;
	}
	public void setIteSubtotal(String iteSubtotal) {
		this.iteSubtotal = iteSubtotal;
	}
	public TItemorderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getGooId() {
		return gooId;
	}
	public void setGooId(String gooId) {
		this.gooId = gooId;
	}
	public String getGooCurrprice() {
		return gooCurrprice;
	}
	public void setGooCurrprice(String gooCurrprice) {
		this.gooCurrprice = gooCurrprice;
	}
	
	
}
