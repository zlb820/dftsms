package org.common.FDBK.domain.DATA.Android;

public class AndroidIndexData<T> {
	private boolean isLogin;
	private int pagestamp;
	private T pageItems;
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	
	public int getPagestamp() {
		return pagestamp;
	}
	public void setPagestamp(int pagestamp) {
		this.pagestamp = pagestamp;
	}
	public T getPageItems() {
		return pageItems;
	}
	public void setPageItems(T pageItems) {
		this.pageItems = pageItems;
	}
}
