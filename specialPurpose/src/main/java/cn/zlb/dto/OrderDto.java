package cn.zlb.dto;

import java.sql.Timestamp;

import cn.zlb.entity.TCustomer;

public class OrderDto {
	private String pkOrdId;
	private TCustomer TCustomer;
	private Timestamp ordTime;
	private byte ordStatus;
	private String ordTotal;
	public String getPkOrdId() {
		return pkOrdId;
	}
	public void setPkOrdId(String pkOrdId) {
		this.pkOrdId = pkOrdId;
	}
	public TCustomer getTCustomer() {
		return TCustomer;
	}
	public void setTCustomer(TCustomer tCustomer) {
		TCustomer = tCustomer;
	}
	public Timestamp getOrdTime() {
		return ordTime;
	}
	public void setOrdTime(Timestamp ordTime) {
		this.ordTime = ordTime;
	}
	public byte getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(byte ordStatus) {
		this.ordStatus = ordStatus;
	}
	
	
	public String getOrdTotal() {
		return ordTotal;
	}
	public void setOrdTotal(String ordTotal) {
		this.ordTotal = ordTotal;
	}
	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
