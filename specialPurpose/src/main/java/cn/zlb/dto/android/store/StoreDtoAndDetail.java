package cn.zlb.dto.android.store;
/**
 * 店铺详情 DTO
 * @author Bingo
 *
 */
public class StoreDtoAndDetail {
	private String styleId="11";
	private String stoID;
	private String stoName;
	private String score;
	private String saleValume;
	private String addConcrete;
	private String sort;
	private String imgUrl;
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getSaleValume() {
		return saleValume;
	}
	public void setSaleValume(String saleValume) {
		this.saleValume = saleValume;
	}
	public String getAddConcrete() {
		return addConcrete;
	}
	public void setAddConcrete(String addConcrete) {
		this.addConcrete = addConcrete;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = "http://47.94.19.44:8080" +imgUrl;
	}
	 
	
	
}
