package cn.zlb.dto.android.good;

import java.util.List;
/**
 * 商品 DTO node
 * @author Bingo
 *
 * @param <T>
 */
public class GoodsNode<T> {
	private String sortName;
	private List<T> list;
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	
}
