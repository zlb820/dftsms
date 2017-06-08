package cn.zlb.dto.android.order;

import java.util.List;

public class OrderNode<T> {
	private String typeId ="32";
	
	private List<T> orders;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public List<T> getOrders() {
		return orders;
	}

	public void setOrders(List<T> orders) {
		this.orders = orders;
	}

 
	
}
