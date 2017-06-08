package cn.zlb.dto;

import java.util.List;

public class OrderAddDto<T> {
	private String cusId;
	private List<T> itemOrder;
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public List<T> getItemOrder() {
		return itemOrder;
	}
	public void setItemOrder(List<T> itemOrder) {
		this.itemOrder = itemOrder;
	}
	 
	
}
