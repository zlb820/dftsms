package cn.zlb.dto.android.order;
 
/**
 * android 订单首页数据，包含订单数据  以及 店铺数据 
 * @author Bingo
 *
 */
public class BaseOrderDtoAnd  {
	private String pagestamp;
	private Object[] pageItems=new Object[2];
	public String getPagestamp() {
		return pagestamp;
	}
	public void setPagestamp(String pagestamp) {
		this.pagestamp = pagestamp;
	}
	public Object[] getPageItems() {
		return pageItems;
	}
	public void setPageItems(Object[] pageItems) {
		this.pageItems = pageItems;
	}
	
	
	/*private T pageItems;
	public String getPagestamp() {
		return pagestamp;
	}
	public void setPagestamp(String pagestamp) {
		this.pagestamp = pagestamp;
	}
	public T getPageItems() {
		return pageItems;
	}
	public void setPageItems(T pageItems) {
		this.pageItems = pageItems;
	}*/
	 
	
}
