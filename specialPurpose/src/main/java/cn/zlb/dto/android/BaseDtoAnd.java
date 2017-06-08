package cn.zlb.dto.android;

import java.util.List;

public class BaseDtoAnd<T> {
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
