package org.common.FDBK.domain.DATA;

import java.util.List;

public class IndexData<T> {
	private String code;
	private String msg;
	private int PageCount;// 总页数
	private int currentPage;// 当前页数
	private int onePageNum;// 单页数量
	private int orderingRule;// 排序规则
	private List<T> data;
	private List<LunBoTu> lunBoTu;//轮播图
	private List<Shangjia> baokuan;//爆款..
	private List<Shangjia> pinpai;//品牌..
	
	public List<LunBoTu> getLunBoTu() {
		return lunBoTu;
	}

	public void setLunBoTu(List<LunBoTu> lunBoTu) {
		this.lunBoTu = lunBoTu;
	}

	public List<Shangjia> getBaokuan() {
		return baokuan;
	}

	public void setBaokuan(List<Shangjia> baokuan) {
		this.baokuan = baokuan;
	}

	public List<Shangjia> getPinpai() {
		return pinpai;
	}

	public void setPinpai(List<Shangjia> pinpai) {
		this.pinpai = pinpai;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getPageCount() {
		return PageCount;
	}

	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getOnePageNum() {
		return onePageNum;
	}

	public void setOnePageNum(int onePageNum) {
		this.onePageNum = onePageNum;
	}

	public int getOrderingRule() {
		return orderingRule;
	}

	public void setOrderingRule(int orderingRule) {
		this.orderingRule = orderingRule;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
