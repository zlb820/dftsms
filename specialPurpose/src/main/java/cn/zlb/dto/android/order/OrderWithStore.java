package cn.zlb.dto.android.order;

import cn.zlb.dto.android.store.StoreDtoAndDetail;
import cn.zlb.dto.android.store.StoreNode;

public class OrderWithStore {
	private OrderNode<OrderDtoAndDetail> orderNode;
	private StoreNode<StoreDtoAndDetail> storeNode;
	public OrderNode<OrderDtoAndDetail> getOrderNode() {
		return orderNode;
	}
	public void setOrderNode(OrderNode<OrderDtoAndDetail> orderNode) {
		this.orderNode = orderNode;
	}
	public StoreNode<StoreDtoAndDetail> getStoreNode() {
		return storeNode;
	}
	public void setStoreNode(StoreNode<StoreDtoAndDetail> storeNode) {
		this.storeNode = storeNode;
	}
	
}
