package cn.zlb.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TStore entity. @author MyEclipse Persistence Tools
 */

public class TStore implements java.io.Serializable {

	// Fields

	private String fkStoId;
	//图片一对一
	private TPictures TPictures;
	//一对一共享主键
	private TBusiness TBusiness;
	
	private String stoName;
	private Boolean stoStatus;
	private String stoScore;
	private TStoreCategory storeCategory;
	private Set TStoreCategories = new HashSet(0);
	private Set TOrders=new HashSet(0);
	private Set TGoodses = new HashSet(0);
	private Set TComments = new HashSet(0);
	private Set TCheaps = new HashSet(0);
	private TAddress TAddress;
	private TGoods TGood;
	// Constructors

	/** default constructor */
	public TStore() {
	}

	/** minimal constructor */
	public TStore(TBusiness TBusiness, String stoName, Boolean stoStatus, String stoScore) {
		this.TBusiness = TBusiness;
		this.stoName = stoName;
		this.stoStatus = stoStatus;
		this.stoScore = stoScore;
	}

	/** full constructor */
	public TStore(TPictures TPictures, TBusiness TBusiness, String stoName, Boolean stoStatus, String stoScore,
			  Set TStoreCategories, Set TGoodses, Set TComments, Set TCheaps) {
		this.TPictures = TPictures;
		this.TBusiness = TBusiness;
		this.stoName = stoName;
		this.stoStatus = stoStatus;
		this.stoScore = stoScore;
		this.TStoreCategories = TStoreCategories;
		this.TGoodses = TGoodses;
		this.TComments = TComments;
		this.TCheaps = TCheaps;
	}

	// Property accessors

	public String getFkStoId() {
		return this.fkStoId;
	}

	public void setFkStoId(String fkStoId) {
		this.fkStoId = fkStoId;
	}

	public TPictures getTPictures() {
		return this.TPictures;
	}

	public void setTPictures(TPictures TPictures) {
		this.TPictures = TPictures;
	}

	public TBusiness getTBusiness() {
		return this.TBusiness;
	}

	public void setTBusiness(TBusiness TBusiness) {
		this.TBusiness = TBusiness;
	}

	public String getStoName() {
		return this.stoName;
	}

	public void setStoName(String stoName) {
		this.stoName = stoName;
	}

	public Boolean getStoStatus() {
		return this.stoStatus;
	}

	public void setStoStatus(Boolean stoStatus) {
		this.stoStatus = stoStatus;
	}

	public String getStoScore() {
		return this.stoScore;
	}

	public void setStoScore(String stoScore) {
		this.stoScore = stoScore;
	}

 

	public Set getTStoreCategories() {
		return this.TStoreCategories;
	}

	public void setTStoreCategories(Set TStoreCategories) {
		this.TStoreCategories = TStoreCategories;
	}

	public Set getTGoodses() {
		return this.TGoodses;
	}

	public void setTGoodses(Set TGoodses) {
		this.TGoodses = TGoodses;
	}

	public Set getTComments() {
		return this.TComments;
	}

	public void setTComments(Set TComments) {
		this.TComments = TComments;
	}

	public Set getTCheaps() {
		return this.TCheaps;
	}

	public void setTCheaps(Set TCheaps) {
		this.TCheaps = TCheaps;
	}
	

	public Set getTOrders() {
		return TOrders;
	}

	public void setTOrders(Set tOrders) {
		TOrders = tOrders;
	}
	
	public TAddress getTAddress() {
		return TAddress;
	}

	public void setTAddress(TAddress tAddress) {
		TAddress = tAddress;
	}
	
	
	public TGoods getTGood() {
		return TGood;
	}

	public void setTGood(TGoods tGood) {
		TGood = tGood;
	}

	
	public TStoreCategory getStoreCategory() {
		return storeCategory;
	}

	public void setStoreCategory(TStoreCategory storeCategory) {
		this.storeCategory = storeCategory;
	}

	@Override
	public String toString() {
		return "TStore [fkStoId=" + fkStoId + ", stoName=" + stoName + ", stoStatus="
				+ stoStatus + ", stoScore=" + stoScore + "]";
	}

	 
	 

	 
	

}