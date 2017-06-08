package cn.zlb.entity;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * TGoods entity. @author MyEclipse Persistence Tools
 */

public class TGoods implements java.io.Serializable {

	// Fields
	@Expose
	private String pkGooId;
	
	private TStoreCategory TStoreCategory;
	private TStore TStore;
	//一对一
	private TPictures TPicturesByFkPictureBig;
	private TPictures TPicturesByFkPictureSmall;
	private String gooName;
	private String gooPrice;
	//销量
	private String gooSales;
	private String gooCurrprice;
	private String gooExtend01;
	private String gooExtend02;
	private String gooExtend03;
	private Set TCarts = new HashSet(0);
	private Set TItemorders = new HashSet(0);
	
	
	// Constructors

	/** default constructor */
	public TGoods() {
	}

	/** minimal constructor */
	public TGoods(String gooName, String gooPrice, String gooCurrprice) {
		this.gooName = gooName;
		this.gooPrice = gooPrice;
		this.gooCurrprice = gooCurrprice;
	}

	/** full constructor */
	

	// Property accessors

	public String getPkGooId() {
		return this.pkGooId;
	}

	public TGoods(String pkGooId, String gooName, String gooPrice, String gooSales, String gooCurrprice,
			String gooExtend01, String gooExtend02, String gooExtend03) {
		super();
		this.pkGooId = pkGooId;
		this.gooName = gooName;
		this.gooPrice = gooPrice;
		this.gooSales = gooSales;
		this.gooCurrprice = gooCurrprice;
		this.gooExtend01 = gooExtend01;
		this.gooExtend02 = gooExtend02;
		this.gooExtend03 = gooExtend03;
	}

	public void setPkGooId(String pkGooId) {
		this.pkGooId = pkGooId;
	}

	public TStoreCategory getTStoreCategory() {
		return this.TStoreCategory;
	}

	public void setTStoreCategory(TStoreCategory TStoreCategory) {
		this.TStoreCategory = TStoreCategory;
	}

	public TStore getTStore() {
		return this.TStore;
	}

	public void setTStore(TStore TStore) {
		this.TStore = TStore;
	}

	public TPictures getTPicturesByFkPictureBig() {
		return this.TPicturesByFkPictureBig;
	}

	public void setTPicturesByFkPictureBig(TPictures TPicturesByFkPictureBig) {
		this.TPicturesByFkPictureBig = TPicturesByFkPictureBig;
	}

	public TPictures getTPicturesByFkPictureSmall() {
		return this.TPicturesByFkPictureSmall;
	}

	public void setTPicturesByFkPictureSmall(TPictures TPicturesByFkPictureSmall) {
		this.TPicturesByFkPictureSmall = TPicturesByFkPictureSmall;
	}

	public String getGooName() {
		return this.gooName;
	}

	public void setGooName(String gooName) {
		this.gooName = gooName;
	}

	public String getGooPrice() {
		return this.gooPrice;
	}

	public void setGooPrice(String gooPrice) {
		this.gooPrice = gooPrice;
	}

	public String getGooCurrprice() {
		return this.gooCurrprice;
	}

	public void setGooCurrprice(String gooCurrprice) {
		this.gooCurrprice = gooCurrprice;
	}

	public String getGooExtend01() {
		return this.gooExtend01;
	}

	public void setGooExtend01(String gooExtend01) {
		this.gooExtend01 = gooExtend01;
	}

	public String getGooExtend02() {
		return this.gooExtend02;
	}

	public void setGooExtend02(String gooExtend02) {
		this.gooExtend02 = gooExtend02;
	}

	public String getGooExtend03() {
		return this.gooExtend03;
	}

	public void setGooExtend03(String gooExtend03) {
		this.gooExtend03 = gooExtend03;
	}

	public Set getTCarts() {
		return this.TCarts;
	}

	public void setTCarts(Set TCarts) {
		this.TCarts = TCarts;
	}

	public Set getTItemorders() {
		return this.TItemorders;
	}

	public void setTItemorders(Set TItemorders) {
		this.TItemorders = TItemorders;
	}

	public String getGooSales() {
		return gooSales;
	}

	public void setGooSales(String gooSales) {
		this.gooSales = gooSales;
	}
	
	

}