package org.common.FDBK.domain.DATA;

public class ShopSimpleMSG {
	private String styleId;
	private String stoID;
	private String stoName;
	private Float score;
	private int saleValume;
	private String URL;
	private String addConcrete;
	private Double distance;
	private String phone;
	private String sort;
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getStoID() {
		return stoID;
	}
	public void setStoID(String stoID) {
		this.stoID = stoID;
	}
	public String getStoName() {
		return stoName;
	}
	public void setStoName(String stoName) {
		this.stoName = stoName;
	}
	
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public int getSaleValume() {
		return saleValume;
	}
	public void setSaleValume(int saleValume) {
		this.saleValume = saleValume;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getAddConcrete() {
		return addConcrete;
	}
	public void setAddConcrete(String addConcrete) {
		this.addConcrete = addConcrete;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
