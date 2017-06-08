package cn.zlb.entity;

import java.security.Timestamp;

public class TCollection {
	private String pkColId;
	private TCustomer TCustomer;
	private TStore TStore;
	private Timestamp colTime;
	
	public Timestamp getColTime() {
		return colTime;
	}
	public void setColTime(Timestamp colTime) {
		this.colTime = colTime;
	}
	public String getPkColId() {
		return pkColId;
	}
	public void setPkColId(String pkColId) {
		this.pkColId = pkColId;
	}
	public TCustomer getTCustomer() {
		return TCustomer;
	}
	public void setTCustomer(TCustomer tCustomer) {
		TCustomer = tCustomer;
	}
	public TStore getTStore() {
		return TStore;
	}
	public void setTStore(TStore tStore) {
		TStore = tStore;
	}
	
	
}
