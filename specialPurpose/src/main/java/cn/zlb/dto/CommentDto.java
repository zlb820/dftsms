package cn.zlb.dto;

import cn.zlb.entity.TCustomer;
import cn.zlb.entity.TStore;

public class CommentDto {
	private TCustomer TCustomer;
	private Integer comLevel;
	private String comContent;
	private TStore TStore;
	public TCustomer getTCustomer() {
		return TCustomer;
	}
	public void setTCustomer(TCustomer tCustomer) {
		TCustomer = tCustomer;
	}
	public Integer getComLevel() {
		return comLevel;
	}
	public void setComLevel(Integer comLevel) {
		this.comLevel = comLevel;
	}
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public TStore getTStore() {
		return TStore;
	}
	public void setTStore(TStore tStore) {
		TStore = tStore;
	}
	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
