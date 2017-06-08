package cn.zlb.dto.android.store;

import java.util.List;

public class StoreNode<T> {
	private String typeId="34";
	private List<T> list;
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
