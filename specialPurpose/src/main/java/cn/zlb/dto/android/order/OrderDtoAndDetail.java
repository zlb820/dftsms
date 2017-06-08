package cn.zlb.dto.android.order;
/**
 * 用户订单 详情DTO
 * @author Bingo
 *
 */
public class OrderDtoAndDetail {
	private String  typeId;
	private String  picUrl;
	private String  orderState;
	private String  orderTitle;
	private String  orderTime;
	private String  price;
	private String ordId;
	private String stoId;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = "http://47.94.19.44:8080" +picUrl;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	public String getStoId() {
		return stoId;
	}
	public void setStoId(String stoId) {
		this.stoId = stoId;
	}
	 
	
	
	
}
