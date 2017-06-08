package cn.zlb.dto;

import cn.zlb.entity.TPictures;
import cn.zlb.entity.TStore;
import cn.zlb.entity.TStoreCategory;

public class GoodsDto {
	private String gooName;
	private String gooPrice;
	private String maxPrice;
	private String gooCurrprice;
	private String pkGooId;
	private TStore TStore;
	private String pkCatId;
	private TPictures TPicturesByFkPictureBig;
	private TPictures TPicturesByFkPictureSmall;
	public GoodsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getGooName() {
		return gooName;
	}
	public void setGooName(String gooName) {
		this.gooName = gooName;
	}
	public String getGooPrice() {
		return gooPrice;
	}
	public void setGooPrice(String gooPrice) {
		this.gooPrice = gooPrice;
	}
	public String getGooCurrprice() {
		return gooCurrprice;
	}
	public void setGooCurrprice(String gooCurrprice) {
		this.gooCurrprice = gooCurrprice;
	}
	public String getPkGooId() {
		return pkGooId;
	}
	public void setPkGooId(String pkGooId) {
		this.pkGooId = pkGooId;
	}
	public TStore getTStore() {
		return TStore;
	}
	public void setTStore(TStore tStore) {
		TStore = tStore;
	}
	 
	public String getPkCatId() {
		return pkCatId;
	}
	public void setPkCatId(String pkCatId) {
		this.pkCatId = pkCatId;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public TPictures getTPicturesByFkPictureBig() {
		return TPicturesByFkPictureBig;
	}
	public void setTPicturesByFkPictureBig(TPictures tPicturesByFkPictureBig) {
		TPicturesByFkPictureBig = tPicturesByFkPictureBig;
	}
	public TPictures getTPicturesByFkPictureSmall() {
		return TPicturesByFkPictureSmall;
	}
	public void setTPicturesByFkPictureSmall(TPictures tPicturesByFkPictureSmall) {
		TPicturesByFkPictureSmall = tPicturesByFkPictureSmall;
	}
	
}
