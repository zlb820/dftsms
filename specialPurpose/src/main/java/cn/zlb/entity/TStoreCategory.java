package cn.zlb.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TStoreCategory entity. @author MyEclipse Persistence Tools
 */

public class TStoreCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pkCatId;
	private TStore TStore;
	private String catName;
	private String catPid;
	private Integer catDesc;
	private Set<TStoreCategory> TGoodses = new HashSet<TStoreCategory>(0);
	//子目录
	private List<TStoreCategory> listCategory;
	
	// Constructors

	/** default constructor */
	public TStoreCategory() {
	}

	/** full constructor */
	public TStoreCategory(TStore TStore, String catName, String catPid, Integer catDesc, Set TGoodses) {
		this.TStore = TStore;
		this.catName = catName;
		this.catPid = catPid;
		this.catDesc = catDesc;
		this.TGoodses = TGoodses;
	}

	// Property accessors

	public String getPkCatId() {
		return this.pkCatId;
	}

	public void setPkCatId(String pkCatId) {
		this.pkCatId = pkCatId;
	}

	public TStore getTStore() {
		return this.TStore;
	}

	public void setTStore(TStore TStore) {
		this.TStore = TStore;
	}

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatPid() {
		return this.catPid;
	}

	public void setCatPid(String catPid) {
		this.catPid = catPid;
	}

	public Integer getCatDesc() {
		return this.catDesc;
	}

	public void setCatDesc(Integer catDesc) {
		this.catDesc = catDesc;
	}
	

	public Set<TStoreCategory> getTGoodses() {
		return TGoodses;
	}

	public void setTGoodses(Set<TStoreCategory> tGoodses) {
		TGoodses = tGoodses;
	}

	public List<TStoreCategory> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<TStoreCategory> listCategory) {
		this.listCategory = listCategory;
	}
	

}