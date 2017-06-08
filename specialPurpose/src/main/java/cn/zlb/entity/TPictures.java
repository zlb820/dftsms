package cn.zlb.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TPictures entity. @author MyEclipse Persistence Tools
 */

public class TPictures implements java.io.Serializable {

	// Fields

	private String pkPicId;
	private String picUrl;
	private String picDes;
	private String picAttr;
	private TStore TStores;
	private TCustomer TCustomers; 
	private TBusiness TBusinesses ;

	// Constructors

	
	
	/** default constructor */
	public TPictures() {
	}

	public TPictures( String picUrl, String picDes, String picAttr, TStore tStores, TCustomer tCustomers,
			TBusiness tBusinesses) {
		super();
		 
		this.picUrl = picUrl;
		this.picDes = picDes;
		this.picAttr = picAttr;
		TStores = tStores;
		TCustomers = tCustomers;
		TBusinesses = tBusinesses;
	}

	public TStore getTStores() {
		return TStores;
	}

	public void setTStores(TStore tStores) {
		TStores = tStores;
	}

	public TCustomer getTCustomers() {
		return TCustomers;
	}

	public void setTCustomers(TCustomer tCustomers) {
		TCustomers = tCustomers;
	}

	public TBusiness getTBusinesses() {
		return TBusinesses;
	}

	public void setTBusinesses(TBusiness tBusinesses) {
		TBusinesses = tBusinesses;
	}

	/** minimal constructor */
	public TPictures(String picUrl) {
		this.picUrl = picUrl;
	}

	 

	// Property accessors

	public String getPkPicId() {
		return this.pkPicId;
	}

	public void setPkPicId(String pkPicId) {
		this.pkPicId = pkPicId;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPicDes() {
		return this.picDes;
	}

	public void setPicDes(String picDes) {
		this.picDes = picDes;
	}

	public String getPicAttr() {
		return this.picAttr;
	}

	public void setPicAttr(String picAttr) {
		this.picAttr = picAttr;
	}

  

}