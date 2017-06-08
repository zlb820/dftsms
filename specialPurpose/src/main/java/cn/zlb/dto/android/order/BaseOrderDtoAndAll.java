package cn.zlb.dto.android.order;
 
import java.util.List;
/**
 * 用户的所有订单 DTO
 * @author Bingo
 *
 * @param <T>
 */
public class BaseOrderDtoAndAll<T> {
	private boolean isLogin=false;
	 
	private List<T> pageItems;
	
	
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public List<T> getPageItems() {
		return pageItems;
	}
	public void setPageItems(List<T> pageItems) {
		this.pageItems = pageItems;
	}
}
